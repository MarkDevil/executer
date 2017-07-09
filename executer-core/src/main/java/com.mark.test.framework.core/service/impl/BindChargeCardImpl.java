package com.mark.test.framework.core.service.impl;

import com.mark.test.framework.api.dto.AccountInfo;
import com.mark.test.framework.api.dto.BusinessApply;
import com.mark.test.framework.api.dto.OpenAgreementDetermine;
import com.mark.test.framework.core.dao.AccountInfoMapper;
import com.mark.test.framework.core.dao.BusinessApplyMapper;
import com.mark.test.framework.core.dao.OpenAgreementDetermineMapper;
import com.mark.test.framework.core.service.IBindChargeCard;
import com.mark.test.framework.utils.CommUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by mark on 2017/5/27.
 */
@Service("bindChargeCardImpl")
@Transactional
public class BindChargeCardImpl implements IBindChargeCard {
    Logger logger = LoggerFactory.getLogger(BindChargeCardImpl.class);

    private String customerid;
    private String customername;

    @Autowired
    private BusinessApplyMapper businessApplyMapper;

    @Autowired
    private AccountInfoMapper accountInfoMapper;

    @Autowired
    private OpenAgreementDetermineMapper openAgreementDetermineMapper;



    /**
     * 给指定用户绑定银行卡
     * @param applyno
     */
    public boolean bindChargeCard(String applyno,String bankCardNo) {
        BusinessApply businessApply = this.getBussinessApply(applyno);
        logger.info("查询到的申请单数据为: {}",businessApply.toString());
        customerid = businessApply.getCustomerid();
        customername = businessApply.getCustomername();
        if (checkAccountInfo(customerid)){
            logger.info("该用户已经绑过卡");
            updateBankCardStatus("2",customerid);
            return true;
        }else {
            int flag = accountInfoMapper.insert(
                    this.populateAccoutInfo(customerid,"001",bankCardNo,
                            customername,"中国邮政储蓄银行","0")
            );
            int flag1 = accountInfoMapper.insert(
                    this.populateAccoutInfo(customerid,"002",bankCardNo,
                            customername,"0403","1")
            );
            int flag2 = openAgreementDetermineMapper.insert(
                    this.populateOpenAgreement(customerid,bankCardNo,
                            businessApply.getPayLoanBrh())
            );
            if (flag>0 && flag1 >0 && flag2 >0){
                logger.info("Insert bankCard successfully");
                return true;
            }else {
                return false;
            }
        }

    }

    /**
     * 根据单号判断数据是否存在
     * @param userid
     * @return
     */
    public boolean checkAccountInfo(String userid){
        List<AccountInfo> accountInfos = accountInfoMapper.selectAccountsByUserId(userid);
        return accountInfos != null;
    }

    /**
     * 清除无用数据
     * @param userid
     * @return
     */
    public boolean cleanAccountInfo(String userid){
        int flag = accountInfoMapper.deleteByUserid(userid);
        int flag1 = openAgreementDetermineMapper.deleteByUserId(userid);
        return (flag>0) && (flag1>0);
    }

    /**
     * 根据订单号获取数据
      * @param applyNo
     * @return
     */
    public BusinessApply getBussinessApply(String applyNo){
        try {
            BusinessApply businessApply = businessApplyMapper.selectByPrimaryKey(applyNo);
            if (null == businessApply){
                throw new RuntimeException("Get accountInfo failed !");
            }
            return businessApply;
        }catch (Exception ex){
            logger.error(Arrays.toString(ex.getStackTrace()));
            return null;
        }

    }


    /**
     * 根据单号
     * @param userid
     * @return
     */
    public boolean updateBankCardStatus(String status,String userid) {
        logger.info("将订单状态更新为: {}",status);
        int ret = accountInfoMapper.updateStatusByUserid(status,userid);
        return ret > 0;

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
    public AccountInfo populateAccoutInfo(String userid,String accountType,String accountno,
                                          String accountName,String bankname,String cardType){
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setSerialno(CommUtils.getRandomNo("ser"));
        accountInfo.setUserid(userid);
        accountInfo.setAccounttype(accountType);
        accountInfo.setAccountno(accountno);
        accountInfo.setAccountname(accountName);
        accountInfo.setAccountbelong(bankname);
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
        accountInfo.setProvince("北京市");
        accountInfo.setCity("北京市");
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
     * 代扣协议表数据
     * @param userid
     * @return
     */
    public OpenAgreementDetermine populateOpenAgreement(String userid,String bankCardNo,String source){
        OpenAgreementDetermine openAgreementDetermine = new OpenAgreementDetermine();
        openAgreementDetermine.setReqno(CommUtils.getRandomNo("req"));
        openAgreementDetermine.setOrderno(CommUtils.getRandomNo("ord"));
        openAgreementDetermine.setCustomerid(userid);
        openAgreementDetermine.setSmsseq("1212");
        openAgreementDetermine.setAuthcode("2222");
        openAgreementDetermine.setCustaccountid("0000");
        openAgreementDetermine.setHoldername("测试");
        openAgreementDetermine.setBankcardno(bankCardNo);
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
