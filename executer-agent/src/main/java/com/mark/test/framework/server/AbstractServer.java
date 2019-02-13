package com.mark.test.framework.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by mark .
 * Data   : 2017/8/11
 * Author : mark
 * Desc   : 抽象服务器
 */

public abstract class AbstractServer {
    private Logger logger = LoggerFactory.getLogger(AbstractServer.class);
    private ExecutorService executorService = Executors.newCachedThreadPool();
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
                this.printStartFinished();
                Socket client = socket.accept();
                HandlerService handlerService = new HandlerService(client);
                Future future = executorService.submit(handlerService);
                while (true){
                    if (future.isDone()){
                        logger.info("线程" + Thread.currentThread().getName() + "执行成功,返回结果为:{}",future.get());
                        break;
                    }
                }
            }
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
