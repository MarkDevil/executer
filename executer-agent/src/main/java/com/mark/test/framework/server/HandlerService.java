package com.mark.test.framework.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

/**
 * Created by mark .
 * Data   : 2017/8/11
 * Author : mark
 * Desc   : 线程池处理Socket请求
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
            logger.info("接收到的消息为 ： {}",rest);
            bufferedWriter =new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bufferedWriter.write("服务端返回信息");
            bufferedWriter.flush();
            client.shutdownOutput();
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
