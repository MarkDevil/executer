package com.mark.test.framework.core.service.intf;

import java.util.List;
import java.util.Map;

/**
 * Created by MingfengMa .
 * Data : 2016/9/28
 * Project : executer
 * Desc :
 */
public interface IGatewayService {

    boolean updateGwTransferOrder(String requestNo);

    boolean updateGwPaymentOrder(String requestNo);

    List<Map<String,Object>> queryGwTransferOrder(String requestNo);

    List<Map<String,Object>> queryGwPaymentOrder(String requestNo);

}
