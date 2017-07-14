package com.mark.test.framework.core.service;

/**
 * Created by mark on 2017/5/27.
 */
public interface IBindChargeCard {
    /**
     * 绑定代扣卡
     * @param applyNo
     */
    void bindChargeCard(String applyNo,String bankCardNo);

    /**
     * 更新卡状态
     * @param userid
     */
    boolean updateBankCardStatus(String status,String userid);
}
