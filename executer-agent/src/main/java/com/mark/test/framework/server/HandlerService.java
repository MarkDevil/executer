package com.mark.test.framework.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by mark .
 * Data   : 2017/8/11
 * Author : mark
 * Desc   :
 */

public class HandlerService implements Runnable {
    private Logger logger = LoggerFactory.getLogger(HandlerService.class);
    private Socket client = null;

    public HandlerService(Socket client){
        this.client = client;

    }

    @Override
    public void run() {
        try {
            logger.info("处理线程启动成功");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            logger.info("处理消息 ： {}",bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
