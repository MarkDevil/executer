package com.mark.test.framework.core.service.impl;

import com.mark.test.framework.core.dao.AccountInfoMapper;
import com.mark.test.framework.core.dto.AccountInfo;
import com.mark.test.framework.core.service.IBindChargeCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by Houbank_QA on 2017/5/27.
 */
@Service
public class BindChargeCardImpl implements IBindChargeCard {
    Logger logger = LoggerFactory.getLogger(BindChargeCardImpl.class);

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    public void bindChargeCard(String applyNo) {
        AccountInfo accountInfo = accountInfoMapper.selectByPrimaryKey("A00421003011705270003");
        logger.info(String.valueOf(accountInfo));

    }

    public void updateBankCardStatus(String applyNo) {

    }
}
