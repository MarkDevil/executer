package com.mark.test.framework.core.service.impl;

import com.mark.test.framework.core.service.IZooKeeperService;

import java.util.List;
import java.util.Map;

/**
 * Created by mark .
 * Data   : 2018/3/8
 */

public class ZooKeeperServiceImpl implements IZooKeeperService{
    @Override
    public void startMonitor() {

    }

    @Override
    public List<Map<String, Object>> getNodeStats(String zkPath, String node) {
        return null;
    }

    @Override
    public String[] getNodes(String zkPath) {
        return new String[0];
    }


}
