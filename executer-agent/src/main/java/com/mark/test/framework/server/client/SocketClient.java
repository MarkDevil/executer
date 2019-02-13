package com.mark.test.framework.server.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
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


    /**
     * NIO socket客户端
     *
     * @param msg 发送的信息
     */
    public void sendMsgWithNIO(String msg) {
        SocketChannel socketChannel = null;
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8877);
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(inetSocketAddress);
            while (!socketChannel.finishConnect()) {
                logger.info("Haven't connect to server");
                Thread.sleep(1000);
            }
            logger.info("Connected to remote server {}", inetSocketAddress.getAddress());
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(msg.getBytes());
            buffer.flip();
            while (buffer.hasRemaining()) {
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
     *
     * @param msg
     * @throws IOException
     */
    public void sendMsg(String msg) {
        Socket socket = new Socket();
        SocketAddress socketAddress = new InetSocketAddress("localhost", 8877);
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;
        try {
            socket.connect(socketAddress, 15000);
//            //端口复用
//            socket.setReuseAddress(true);
//            socket.setSoLinger(true,30);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write(msg);
            bufferedWriter.flush();
            logger.info("消息已经发送");
            socket.shutdownOutput();
            while (true){
                logger.info("等待响应");
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String retmsg = bufferedReader.readLine();
                if(!retmsg.isEmpty()){
                    logger.info("服务端返回信息为：{}", retmsg);
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bufferedWriter != null;
                bufferedWriter.close();
                if (!socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String msg = "00001000<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<tx><HEAD><TRANSRNO>GH0005</TRANSRNO><BANKCODE>Test</BANKCODE><TRANSORGNO>xxxxxxxx</TRANSORGNO><SERIALNUMBER>XOL201802140000000005043</SERIALNUMBER><TRANSRDATE>20180214190000</TRANSRDATE></HEAD><BODY><IDCODE>320525196801112536</IDCODE></BODY></tx>";
        SocketClient socketClient = new SocketClient();
        socketClient.sendMsg(msg);


    }
}
