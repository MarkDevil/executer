package com.mark.test.framework.core.service;

import java.util.List;
import java.util.Map;

/**
 * Created by mark .
 * Data   : 2018/3/8
 */
public interface IZooKeeperService {
    void startMonitor();
    List<Map<String,Object>> getNodeStats(String zkPath,String node);
    String[] getNodes(String zkPath);
}
