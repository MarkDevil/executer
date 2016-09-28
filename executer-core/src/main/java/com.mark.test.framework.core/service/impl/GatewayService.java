package com.mark.test.framework.core.service.impl;

import com.mark.test.framework.core.service.intf.IGatewayService;

import java.util.List;
import java.util.Map;

/**
 * Created by MingfengMa .
 * Data : 2016/9/28
 * Project : executer
 * Desc :
 */
public class GatewayService implements IGatewayService {

    public boolean updateGwTransferOrder(String requestNo) {
        return false;
    }

    public boolean updateGwPaymentOrder(String requestNo) {
        return false;
    }

    public List<Map<String, Object>> queryGwTransferOrder(String requestNo) {
        return null;
    }

    public List<Map<String, Object>> queryGwPaymentOrder(String requestNo) {
        return null;
    }
}
