package com.mark.test.framework.server.mq;

import com.rabbitmq.client.Channel;

/**
 * Created by mark .
 * Data   : 2017/11/10
 * Author : mark
 * Desc   :
 */

public class SendMsg {
    private static final String EXCHANGE_NAME = "logs";
    private final static String QUEUE_NAME = "hello";
    private static Channel channel = null;

//    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
//            ConnectionFactory factory = new ConnectionFactory();
//            factory.setHost("localhost");
//            Connection connection = factory.newConnection();
//            channel = connection.createChannel();
//
//            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//            while (true){
//                Thread.sleep(2000);
//                String message = "Hello World!";
//                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//                System.out.println(" [x] Sent '" + message + "'");
//
//            }
////            channel.close();
////            connection.close();
//
//
//    }
}
