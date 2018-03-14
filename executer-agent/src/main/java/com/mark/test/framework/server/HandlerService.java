package com.mark.test.framework.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;

    public HandlerService(Socket client){
        this.client = client;

    }

    @Override
    public void run() {
        try {
            logger.info("处理线程 {} 启动成功",Thread.currentThread().getName());
            bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String data;
            StringBuilder rest = new StringBuilder();
            while ((data = bufferedReader.readLine())!=null){
                rest.append(data);
            }
            logger.info("处理消息 ： {}",rest);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
