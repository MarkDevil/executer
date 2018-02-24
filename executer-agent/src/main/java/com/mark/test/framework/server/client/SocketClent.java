package com.mark.test.framework.server.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by mark .
 * Data   : 2017/8/14
 * Author : mark
 * Desc   :
 */

public class SocketClent {
    Logger logger = LoggerFactory.getLogger(SocketClent.class);

    public static void init(){
        try {
            Socket socket = new Socket("127.0.0.1", 11112);

            //由系统标准输入设备构造BufferedReader对象
            PrintWriter os=new PrintWriter(socket.getOutputStream());
            //由Socket对象得到输出流，并构造PrintWriter对象
            BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //由Socket对象得到输入流，并构造相应的BufferedReader对象

            String readline;
            while(true){
                BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
                //从系统标准输入读入一字符串
                readline=sin.readLine();
                //若从标准输入读入的字符串为 "bye"则停止循环
                os.println(readline);
                //将从系统标准输入读入的字符串输出到Server
                os.write(readline);
                os.flush();
                //刷新输出流，使Server马上收到该字符串
                System.out.println("Client:"+readline);
                //在系统标准输出上打印读入的字符串
                System.out.println("Server:"+is.readLine());
                //从Server读入一字符串，并打印到标准输出上
//                readline=sin.readLine(); //从系统标准输入读入一字符串
            } //继续循环

        }catch(Exception e) {
            //出错，则打印出错信息
            System.out.println("Error"+e);
        }
    }

    public void sendMsg(String msg){

    }

//    public static void main(String[] args) {
//        init();
//    }
}
