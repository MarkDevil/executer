package com.mark.test.framework.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mark .
 * Data   : 2017/8/11
 * @Author : mark
 * Desc   :
 */

public class EchoServerImpl extends AbstractServer{
    private static Logger logger = LoggerFactory.getLogger(EchoServerImpl.class);

    @Override
    public void start() {
        logger.info("I replace the message");
    }

    public static void main(String[] args) {
        EchoServerImpl echoServer = new EchoServerImpl();
        echoServer.startServer(8877);
    }
}
