package com.mark.test.framework.util;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by mark .
 * Data   : 2017/8/8
 * Author : mark
 * Desc   :
 */

public class ZookeeperUtils {
    private static Logger logger = LoggerFactory.getLogger(ZookeeperUtils.class);
    private static String zkPath = "/test";
    private static CuratorFramework client;

    private ZookeeperUtils() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder().
                connectString("192.168.51.161:2181,192.168.51.161:2182,192.168.51.161:2183").retryPolicy(retryPolicy).
                sessionTimeoutMs(1000 * 6).connectionTimeoutMs(1000 * 6).build();
        client.start();
    }



    public boolean createNode(){
        String retMsg;
        try {
            logger.info("检查节点是否存在" + String.valueOf(client.checkExists().forPath(zkPath)));
            if (client.checkExists().forPath(zkPath) != null){
                logger.warn("节点已存在，进行查询数据");
                return false;
            }
            retMsg = client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(zkPath,"mark-test,192.168.51.161".getBytes());
            logger.info("创建节点返回日志信息：{}",retMsg);
            if (retMsg!= null){
                return true;
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public String getNode(){
        byte[] childs;
        try {
            childs = client.getData().forPath(zkPath);
            String retmsg = new String(childs);
            logger.info("获取到的节点数据为：{}",retmsg);
            return retmsg;
        } catch (Exception e) {
            logger.warn("当前节点不存在 {}, {}",e.getCause(),e.getMessage());
        }
        return null;
    }

    public void setNode(String nodename){
        try {
            client.setData().forPath(zkPath + nodename,"Value changed".getBytes());
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteNode(String nodename){
        try {
            client.delete().deletingChildrenIfNeeded().forPath(zkPath + nodename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> listNode(){
        List<String> retlist;
        try {
            retlist = client.getChildren().forPath(zkPath);
            logger.info("子节点是：{}",retlist);
            return retlist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        ZookeeperUtils zookeeperUtils = new ZookeeperUtils();
        zookeeperUtils.listNode();
        zookeeperUtils.setNode("/test10000000000");
        logger.info(new String(client.getData().forPath(zkPath + "/test10000000000")));


    }
}
