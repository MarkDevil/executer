package com.mark.test.framework.web.service;

import com.mark.test.framework.web.dao.GwTransfersMapper;
import com.mark.test.framework.web.dto.GwTransfers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MingfengMa .
 * Data : 2016/9/30
 * Project : executer
 * Desc :
 */
@Service
public class GwTransferServiceImpl implements GwTransferService {

    @Autowired
    private GwTransfersMapper gwTransfermaper;

    public GwTransfers queryGwTransfers(long id) {

        return gwTransfermaper.selectByPrimaryKey(id);
    }
}
