package com.mark.test.framework.core.service;


import com.mark.test.framework.api.dto.GwTransfers;

/**
 * Created by mark .
 * Data : 2016/9/30
 * Project : executer
 * Desc :
 */
public interface GwTransferService {
    /**
    * @Author mark
    * @Date 2016/9/30
    * @param
    * @Description 查询接口
    *
    */
    GwTransfers queryGwTransfers(long id);
}
