package com.mark.test.framework.core.service.impl;

import com.mark.test.framework.core.dao.GwTransfersMapper;
import com.mark.test.framework.core.dto.GwTransfers;
import com.mark.test.framework.core.service.GwTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MingfengMa .
 * Data : 2016/9/30
 * Project : executer
 * Desc :
 */
@Service("GwTransferServiceImpl")
public class GwTransferServiceImpl implements GwTransferService {

    @Autowired(required = false)
    private GwTransfersMapper gwTransfermaper;

    public GwTransfers queryGwTransfers(long id) {
        return gwTransfermaper.selectByPrimaryKey(id);
    }

}
