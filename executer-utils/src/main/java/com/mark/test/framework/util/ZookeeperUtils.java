package com.mark.test.framework.util;

import org.apache.commons.lang3.StringUtils;
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
 * @Author : mark
 * Desc   :
 */

public class ZookeeperUtils {
    private static Logger logger = LoggerFactory.getLogger(ZookeeperUtils.class);
    private static String zkRootPath = "/";
    private static CuratorFramework client;

    private ZookeeperUtils(String hosts) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder().
                connectString(hosts).retryPolicy(retryPolicy).build();
        client.start();
    }


    public static CuratorFramework getZKClient(){
        return client;
    }


    /**
     * 创建节点
     * @param zkPath
     * @return
     */
    public boolean createNode(String zkPath){
        String retMsg;
        try {
            logger.info("检查节点是否存在" + String.valueOf(client.checkExists().forPath(this.getPath(zkPath))));
            if (client.checkExists().forPath(zkPath) != null){
                logger.warn("节点已存在，进行查询数据");
                return false;
            }
            retMsg = client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(zkPath,"mark-test,192.168.51.161".getBytes());
            logger.info("创建节点返回日志信息：{}",retMsg);
            if (retMsg!= null){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }



    public void setNode(String zkPath){
        try {
            client.setData().forPath(this.getPath(zkPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除所有数据
     * @param zkPath
     */
    public void deleteNode(String zkPath){
        try {
            client.delete().deletingChildrenIfNeeded().forPath(this.getPath(zkPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 列出指定路径的节点
     * @param zkPath
     * @return
     */
    public List<String> listNode(String zkPath){
        List<String> retlist;
        try {
            retlist = client.getChildren().forPath(this.getPath(zkPath));
            logger.info("子节点是：{}",retlist);
            return retlist;
        } catch (KeeperException.NoNodeException e) {
            logger.warn("{} 节点未找到",zkPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取路径
     * @param path
     * @return
     */
    private String getPath(String path) {
        path = path == null ? "/" : path.trim();
        if (!StringUtils.startsWith(path, "/")) {
            path = "/" + path;
        }
        return path;
    }

    public static void main(String[] args) throws Exception {

        ZookeeperUtils zookeeperUtils = new ZookeeperUtils("192.168.18.45:2181");
        zookeeperUtils.listNode("/dubbo");

    }
}
