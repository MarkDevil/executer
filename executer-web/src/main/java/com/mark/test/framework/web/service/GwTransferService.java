package com.mark.test.framework.web.service;

import com.mark.test.framework.web.dto.GwTransfers;

/**
 * Created by MingfengMa .
 * Data : 2016/9/30
 * Project : executer
 * Desc :
 */
public interface GwTransferService {
    /**
    * @Author MingfengMa
    * @Date 2016/9/30
    * @param
    * @Description 查询接口
    *
    */
    GwTransfers queryGwTransfers(long id);
}
