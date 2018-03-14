package com.mark.test.framework.server.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

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
     * 向socket服务器发送消息
     * @param msg
     * @throws IOException
     */
    public void sendMsg(String msg){
        Socket socket = new Socket();
        SocketAddress socketAddress = new InetSocketAddress("localhost", 8877);
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            socket.connect(socketAddress,15000);
            //端口复用
            socket.setReuseAddress(true);
            socket.setSoLinger(true,30);
            outputStream = socket.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(msg);
            bufferedWriter.flush();
            logger.info("消息已经发送");

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert bufferedWriter != null;
                bufferedWriter.close();
                outputStreamWriter.close();
                outputStream.close();
                if (!socket.isClosed()){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        String msg = "11111111<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<tx><HEAD><TRANSRNO>GH0005</TRANSRNO><BANKCODE>781393010054</BANKCODE><TRANSORGNO>XOL201802140000000005043</TRANSORGNO><SERIALNUMBER>XOL201802140000000005043</SERIALNUMBER><TRANSRDATE>20180214190000</TRANSRDATE></HEAD><BODY><IDCODE>320525196801112536</IDCODE></BODY></tx>";
        SocketClient socketClient = new SocketClient();
        for (int i = 0; i < 100 ; i++) {
            socketClient.sendMsg(msg);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
