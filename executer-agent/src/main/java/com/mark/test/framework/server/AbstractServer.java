package com.mark.test.framework.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by MingfengMa .
 * Data   : 2017/8/11
 * Author : mark
 * Desc   : 模板方法
 */

public abstract class AbstractServer {
    private Logger logger = LoggerFactory.getLogger(AbstractServer.class);
    private void printStartlog(){
        logger.info("Start server ...");
    }

    private void printStartFinished(){
        logger.info("Start finished server ...");
    }

    public abstract void start();

    void startServer(int port) {
        this.printStartlog();
        try {
            ServerSocket socket = new ServerSocket(port);
            while (true){
                Socket client = socket.accept();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stopServer(){}



}
