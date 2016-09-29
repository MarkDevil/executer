package com.mark.test.framework.core.service.impl;

import com.mark.test.framework.core.dto.GwTransfers;
import com.mark.test.framework.core.service.intf.GwTransfersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MingfengMa .
 * Data : 2016/9/28
 * Project : executer
 * Desc :
 */
@Service("GatewayService")
public class GatewayService implements GwTransfersMapper {

    @Autowired
    GwTransfersMapper gwTransfersMapper;

    public int deleteByPrimaryKey(Long id) {

        return 0;
    }

    public int insert(GwTransfers record) {
        return 0;
    }

    public int insertSelective(GwTransfers record) {
        return 0;
    }

    public GwTransfers selectByPrimaryKey(Long id) {
        return gwTransfersMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(GwTransfers record) {
        return 0;
    }

    public int updateByPrimaryKey(GwTransfers record) {
        return 0;
    }
}
