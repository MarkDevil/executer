package com.mark.test.framework.server.mq;
/**
 * Created by mark .
 * Data   : 2017/11/10
 * Author : mark
 * Desc   :
 */

public class RecvMsg {
    private final static String QUEUE_NAME = "hello";


//    public static void main(String[] argv) throws Exception {
//
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("192.168.51.161");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//
//        QueueingConsumer consumer = new QueueingConsumer(channel);
//        channel.basicConsume(QUEUE_NAME, true, consumer);
//
//        while (true) {
//            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//            String message = new String(delivery.getBody());
//            System.out.println(" [x] Received '" + message + "'");
//
//        }
//    }
}
