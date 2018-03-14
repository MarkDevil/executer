package com.mark.test.framework.server.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by mark .
 * Data   : 2017/8/14
 * Author : mark
 * Desc   :
 */

public class SocketClent {
    static Logger logger = LoggerFactory.getLogger(SocketClent.class);
    private static Socket socket;
    private static volatile SocketClent socketClent;



    private SocketClent(){
        try {
            if (socket == null){
                socket = new Socket("localhost", 8877);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized SocketClent getInstance(){
        if (socketClent == null){
            synchronized (SocketClent.class){
                if (socketClent == null){
                    socketClent = new SocketClent();
                }
            }
        }
        return socketClent;
    }

    /**
     * 关闭
     */
    public void closeClient(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 向socket服务器发送消息
     * @param msg
     * @throws IOException
     */
    public void sendMsg(String msg) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write(msg);
        logger.info("消息已经发送");
        bufferedWriter.flush();
        bufferedWriter.close();
        if (!socket.isClosed()){
            socket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        String msg = "00000604<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<tx>" +
                "  <HEAD>" +
                "    <TRANSRNO>GH0004</TRANSRNO>" +
                "    <BANKCODE>0302</BANKCODE>" +
                "    <TRANSORGNO>1102</TRANSORGNO>" +
                "    <SERIALNUMBER>11000223</SERIALNUMBER>" +
                "    <TRANSRDATE>192</TRANSRDATE>" +
                "  </HEAD>" +
                "  <BODY>" +
                "    <SEQ>XAS07058</SEQ>" +
                "    <CERTID>110101190001011535</CERTID>" +
                "  </BODY>" +
                "</tx>";
        SocketClent socketClent = SocketClent.getInstance();
        SocketClent socketClent1 = SocketClent.getInstance();
        logger.info(String.valueOf(socketClent.equals(socketClent1)));
        socketClent.sendMsg(msg);
    }
}
