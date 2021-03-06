package com.mark.test.framework.core.service.impl;

import com.mark.test.framework.api.dto.GwTransfers;
import com.mark.test.framework.core.dao.GwTransfersMapper;
import com.mark.test.framework.core.service.GwTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mark .
 * Data : 2016/9/30
 * Project : executer
 * Desc :
 */
@Service("gwTransferServiceImpl")
public class GwTransferServiceImpl implements GwTransferService {

    @Autowired(required = false)
    private GwTransfersMapper gwTransfermaper;

    @Override
    public GwTransfers queryGwTransfers(long id) {
        return gwTransfermaper.selectByPrimaryKey(id);
    }

}
