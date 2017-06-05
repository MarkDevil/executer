package com.mark.test.framework.core.service.impl;

import com.mark.test.framework.core.dao.AccountInfoMapper;
import com.mark.test.framework.core.dao.OpenAgreementDetermineMapper;
import com.mark.test.framework.core.dto.AccountInfo;
import com.mark.test.framework.core.dto.OpenAgreementDetermine;
import com.mark.test.framework.core.service.IBindChargeCard;
import com.mark.test.framework.utils.CommUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by mark on 2017/5/27.
 */
@Service("BindChargeCardImpl")
public class BindChargeCardImpl implements IBindChargeCard {
    Logger logger = LoggerFactory.getLogger(BindChargeCardImpl.class);

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Autowired
    private OpenAgreementDetermineMapper openAgreementDetermineMapper;

    /**
     * 给指定用户绑定银行卡
     * @param applyno
     */
    public void bindChargeCard(String applyno) {
        AccountInfo accountInfo = this.getAccountInfo(applyno);
        int flag = accountInfoMapper.insert(this.populateAccoutInfo(accountInfo.getUserid(),"0","",""));
        int flag1 = accountInfoMapper.insert(this.populateAccoutInfo(accountInfo.getUserid(),"1","",""));
        int flag2 = openAgreementDetermineMapper.insert(this.populateOpenAgreement(accountInfo.getUserid(),""));
        if (flag>0 && flag1 >0 && flag2 >0){
            logger.info("Insert bankCard successfully");
        }else {
            throw new RuntimeException("Bind card failed");
        }
    }

    /**
     * 根据单号判断数据是否存在
     * @param applyNo
     * @return
     */
    public AccountInfo getAccountInfo(String applyNo){
        AccountInfo accountInfo = accountInfoMapper.selectByPrimaryKey(applyNo);
        if (accountInfo == null){
            throw new RuntimeException("Get accountInfo failed !");
        }
        logger.info(String.valueOf(accountInfo));
        return accountInfo;
    }



    public void updateBankCardStatus(String applyNo) {

    }


    /**
     * # 插入银行卡信息,借款卡
     INSERT INTO `hb`.`account_info` (`SERIALNO`, `USERID`, `ACCOUNTTYPE`, `ACCOUNTNO`, `ACCOUNTNAME`, `ACCOUNTBELONG`, `STATUS`, `LIMITAMOUNT`, `INPUTTIME`, `UPDATETIME`, `BELONGAREA`, `CHECKNUMBER`, `ACCOUNTNO2`, `VERIFYTYPE`, `ISRETURNCARD`, `SUBACCOUNTBELONG`, `PROVINCE`, `CITY`, `channelCardId`, `PHONENO`, `LoanapplyID`, `BankBranch`, `BankBranch_Second`, `source`, `LoanCardFlag`, `NybankAcctNo`)
     VALUES
     ('20171145054101661', '2017052200000003', '001', '6210985200018837056', '孙源龙', '中国邮政储蓄银行', '2', NULL, NULL, NULL, NULL,
     NULL, NULL, NULL, '0\n        ', NULL, '上海市', '上海市', '54931', '13732292939', NULL, "北京大望路支行", NULL, '0', '0', NULL);
     # 代扣卡
     INSERT INTO `hb`.`account_info` (`SERIALNO`, `USERID`, `ACCOUNTTYPE`, `ACCOUNTNO`, `ACCOUNTNAME`, `ACCOUNTBELONG`, `STATUS`, `LIMITAMOUNT`, `INPUTTIME`, `UPDATETIME`, `BELONGAREA`, `CHECKNUMBER`, `ACCOUNTNO2`, `VERIFYTYPE`, `ISRETURNCARD`, `SUBACCOUNTBELONG`, `PROVINCE`, `CITY`, `channelCardId`, `PHONENO`, `LoanapplyID`, `BankBranch`, `BankBranch_Second`, `source`, `LoanCardFlag`, `NybankAcctNo`)
     VALUES
     ('20171145054101662', '2017052200000003', '002', '6210985200018837066', '孙源龙', '0403', '2', NULL, NULL, NULL, NULL,
     NULL, NULL, '103', '1', NULL, NULL, NULL, NULL, '13732292939', NULL, "北京西二旗支行", NULL, '0', '1', NULL);

     # 插入扣款协议
     INSERT INTO hb.open_agreement_determine (reqNo, orderNo, customerId, smsSeq, authCode, custAccountId, holderName, bankCardNo, status, treatyNo, failureDetails, errorCode, sendDate, returnDate, updateDate, source)
     VALUES ('20171145054101663','121212121912466','2017052200000003','1212','121','0000','测试','6210985200018837066',1,'201612121',NULL ,NULL ,current_timestamp,current_timestamp,current_timestamp,'FK011');
     */

    /**
     * 根据用户id组装请求借款卡
     * cardType 0:借款卡 1:代扣卡
     * @param userid
     * @return
     */
    public AccountInfo populateAccoutInfo(String userid,String accountType,String bank,String cardType){
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setSerialno(CommUtils.getRandomNo("ser"));
        accountInfo.setUserid(userid);
        accountInfo.setAccounttype(accountType);
        accountInfo.setAccountno("6210985200018837055");
        accountInfo.setAccountname("马铭锋");
        accountInfo.setAccountbelong(bank);
        accountInfo.setStatus("2");
        accountInfo.setLimitamount(null);
        accountInfo.setInputtime(null);
        accountInfo.setUpdatetime(null);
        accountInfo.setBelongarea(null);
        accountInfo.setChecknumber(null);
        accountInfo.setAccountno2(null);
        accountInfo.setVerifytype(null);
        accountInfo.setIsreturncard("0");
        accountInfo.setSubaccountbelong(null);
        accountInfo.setProvince("上海市");
        accountInfo.setCity("上海市");
        accountInfo.setChannelcardid("54931");
        accountInfo.setPhoneno("13732292939");
        accountInfo.setLoanapplyid(null);
        accountInfo.setBankbranch("北京大望路支行");
        accountInfo.setBankbranchSecond(null);
        accountInfo.setSource("0");
        accountInfo.setLoancardflag(cardType);
        accountInfo.setNybankacctno(null);
        return accountInfo;
    }


    /**
     * 代扣协议
     * @param userid
     * @return
     */
    public OpenAgreementDetermine populateOpenAgreement(String userid,String source){
        OpenAgreementDetermine openAgreementDetermine = new OpenAgreementDetermine();
        openAgreementDetermine.setReqno(CommUtils.getRandomNo("req"));
        openAgreementDetermine.setOrderno(CommUtils.getRandomNo("ord"));
        openAgreementDetermine.setCustomerid(userid);
        openAgreementDetermine.setSmsseq("1212");
        openAgreementDetermine.setAuthcode("2222");
        openAgreementDetermine.setCustaccountid("0000");
        openAgreementDetermine.setHoldername("测试");
        openAgreementDetermine.setBankcardno("6210985200018837055");
        openAgreementDetermine.setStatus("1");
        openAgreementDetermine.setTreatyno("20161192");
        openAgreementDetermine.setFailuredetails(null);
        openAgreementDetermine.setErrorcode(null);
        openAgreementDetermine.setSenddate(new Date());
        openAgreementDetermine.setReturndate(new Date());
        openAgreementDetermine.setUpdatedate(new Date());
        openAgreementDetermine.setSource(source);
        return openAgreementDetermine;
    }
}
