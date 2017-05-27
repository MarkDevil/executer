package com.mark.test.framework.core.service;

/**
 * Created by Houbank_QA on 2017/5/27.
 */
public interface IBindChargeCard {
    /**
     * 绑定代扣卡
     * @param applyNo
     */
    void bindChargeCard(String applyNo);

    /**
     * 更新卡状态
     * @param applyNo
     */
    void updateBankCardStatus(String applyNo);
}
