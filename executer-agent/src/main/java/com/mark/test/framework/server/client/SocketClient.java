package com.mark.test.framework.server.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by mark .
 * Data   : 2017/8/14
 * Author : mark
 * Desc   :
 */

public class SocketClient {
    static Logger logger = LoggerFactory.getLogger(SocketClient.class);

//    private static Socket socket;
//    private static volatile SocketClient socketClient;


//    private SocketClient(){
//        try {
//            if (socket == null){
//                socket = new Socket();
//                SocketAddress socketAddress = new InetSocketAddress("192.168.18.51", 8877);
//                socket.connect(socketAddress,5000);
//                socket.setReuseAddress(true);
//                socket.setSoTimeout(8000);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static synchronized SocketClient getInstance(){
//        if (socketClient == null){
//            synchronized (SocketClient.class){
//                if (socketClient == null){
//                    socketClient = new SocketClient();
//                }
//            }
//        }
//        return socketClient;
//    }

    /**
     * 关闭
     */
//    public void closeClient(){
//        try {
//            socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * NIO socket客户端
     * @param msg 发送的信息
     */
    public void sendMsgWithNIO(String msg){
        SocketChannel socketChannel = null;
        InetSocketAddress inetSocketAddress = new InetSocketAddress("192.168.18.51",8877);
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(inetSocketAddress);
            while (!socketChannel.finishConnect()){
                logger.info("Haven't connect to server");
                Thread.sleep(1000);
            }
            logger.info("Connected to remote server {}",inetSocketAddress.getAddress());
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(msg.getBytes());
            buffer.flip();
            while (buffer.hasRemaining()){
                socketChannel.write(buffer);
            }
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                assert socketChannel != null;
                socketChannel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                assert socketChannel != null;
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 向socket服务器发送消息
     * @param msg
     * @throws IOException
     */
    public void sendMsg(String msg){
        Socket socket = new Socket();
        SocketAddress socketAddress = new InetSocketAddress("192.168.18.51", 8877);
        BufferedWriter bufferedWriter = null;
        try {
            socket.connect(socketAddress,15000);
//            //端口复用
//            socket.setReuseAddress(true);
//            socket.setSoLinger(true,30);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write(msg);
            bufferedWriter.flush();
            logger.info("消息已经发送");

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert bufferedWriter != null;
                bufferedWriter.close();
                if (!socket.isClosed()){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String msg = "00001000<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<tx><HEAD><TRANSRNO>GH0005</TRANSRNO><BANKCODE>Test</BANKCODE><TRANSORGNO>XOL201802140000000005043</TRANSORGNO><SERIALNUMBER>XOL201802140000000005043</SERIALNUMBER><TRANSRDATE>20180214190000</TRANSRDATE></HEAD><BODY><IDCODE>320525196801112536</IDCODE></BODY></tx>";
        SocketClient socketClient = new SocketClient();
        socketClient.sendMsg(msg);


    }
}
