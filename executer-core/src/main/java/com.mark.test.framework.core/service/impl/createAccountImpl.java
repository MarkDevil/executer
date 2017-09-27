package com.mark.test.framework.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mark.test.framework.api.dto.CreateAccDto;
import com.mark.test.framework.api.dto.RepayPlanRequest;
import com.mark.test.framework.api.dto.oms.*;
import com.mark.test.framework.api.dto.oms.request.IomReqApplicationDomain;
import com.mark.test.framework.api.dto.oms.request.IomRequest;
import com.mark.test.framework.core.service.ICreateAccount;
import com.mark.test.framework.utils.CommUtils;
import com.mark.test.framework.utils.HttpUtil;
import com.mark.test.framework.utils.MapUtil;
import com.mark.test.framework.utils.SignUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by mark .
 * Data   : 2017/8/25
 * Author : mark
 * Desc   :
 */
@Service
public class createAccountImpl implements ICreateAccount {
    private Logger logger = LoggerFactory.getLogger(createAccountImpl.class);

    private List<IomAddressDomain> addressDomainList = Lists.newArrayList();
    private List<IomReqApplicationDomain> businessapplyList = Lists.newArrayList();
    private List<IomCustomerInfoDomain> customerList = Lists.newArrayList();
    private List<IomCardInfoDomain> bankcardList = Lists.newArrayList();
    private List<IomTelInfoDomain> telephoneList = Lists.newArrayList();
    private List<IomContactInfoDomain> contractList = Lists.newArrayList();


    @Value(value = "${plas_url_createacc}")
    private String plasUrl;

    @Value(value = "${bankapi_repayment_trial}")
    private String repaymentUrl;

    @Value(value = "${lasStartBatchUrl}")
    private String lasStartBatchUrl;


    @Override
    public void createAcc(CreateAccDto createAccDto) {
        IomRequest iomRequest = this.generateIomRequest(createAccDto);
        logger.info("请求贷后建账接口参数iomRequest：{}",iomRequest);
        String request = JSONObject.toJSONString(iomRequest);
        logger.info("请求贷后建账接口参数request：{}",request);
        try {
            String ret = HttpUtil.postJson(plasUrl,request);
            logger.info("Return message : {}",ret);
            JSONObject retobj = JSONObject.parseObject(ret);
            String retcode = JSONObject.parseObject(ret).get("respCode").toString();
            if (!retcode.equals("000")){
                throw new RuntimeException(String.valueOf(retobj.get("respMessage")));
            }
        }catch (Exception ex){
            throw new RuntimeException("createAccount failed");
        }
    }

    @Override
    public boolean startBatch() {
        try {
            HttpUtil.get(lasStartBatchUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private IomRequest generateIomRequest(CreateAccDto createAccDto){
        String serialno = CommUtils.getRandomNo("sn");
        String customerid = CommUtils.getRandomNo("cu");
        String cardId = CommUtils.getRandomNo("ca");
        String addressId = CommUtils.getRandomNo("ad");
        String TelId = CommUtils.getRandomNo("tel");
        String ContactId = CommUtils.getRandomNo("ct");
        String today = CommUtils.getNowDate(5);

        IomReqApplicationDomain iomReqApplicationDomain = new IomReqApplicationDomain();
        IomCustomerInfoDomain iomCustomerInfoDomain = new IomCustomerInfoDomain();
        IomCardInfoDomain iomCardInfoDomain = new IomCardInfoDomain();
        IomAddressDomain iomAddressDomain = new IomAddressDomain();
        IomTelInfoDomain iomTelInfoDomain = new IomTelInfoDomain();
        IomContactInfoDomain iomContactInfoDomain = new IomContactInfoDomain();

        iomReqApplicationDomain.setInDate(today);
        iomReqApplicationDomain.setSerialno(serialno);
        iomReqApplicationDomain.setCustId(customerid);
        iomReqApplicationDomain.setTerm((short) createAccDto.getTerm());
        iomReqApplicationDomain.setApplDate(today);
        iomReqApplicationDomain.setContractAmt(new BigDecimal(10000));
        iomReqApplicationDomain.setLoanDate(today);
        iomReqApplicationDomain.setLoanAmt(new BigDecimal(9000));
        iomReqApplicationDomain.setPrincipal(new BigDecimal(10000));
        iomReqApplicationDomain.setFee01(new BigDecimal(1000));
        iomReqApplicationDomain.setFee02(new BigDecimal(0));
        iomReqApplicationDomain.setFee03(new BigDecimal(0));
        iomReqApplicationDomain.setGpsFee(new BigDecimal(0));
        iomReqApplicationDomain.setAssessFee(new BigDecimal(0));
        iomReqApplicationDomain.setViolateDeposit(new BigDecimal(0));
        iomReqApplicationDomain.setConsultFee(new BigDecimal(0));
        iomReqApplicationDomain.setDeposits(new BigDecimal(0));
        iomReqApplicationDomain.setLoanPenalty(new BigDecimal(0));
        iomReqApplicationDomain.setOtFee01(new BigDecimal(0));
        iomReqApplicationDomain.setOtFee02(new BigDecimal(0));
        iomReqApplicationDomain.setOtFee03(new BigDecimal(0));
        iomReqApplicationDomain.setOtFee04(new BigDecimal(0));
        iomReqApplicationDomain.setOtFee05(new BigDecimal(0));
        iomReqApplicationDomain.setIntRate(new BigDecimal(9));
        iomReqApplicationDomain.setPenaltyIntRate(new BigDecimal(0.15).setScale(2, RoundingMode.HALF_UP));
        iomReqApplicationDomain.setGraceDays((short) 1);
        iomReqApplicationDomain.setMaxPutoutDays("3");
        iomReqApplicationDomain.setRepayMethod("RPT000010");
        iomReqApplicationDomain.setMethodFlag("0");
        iomReqApplicationDomain.setBusinessType("A0007");
//      测试放款渠道中邮 FK011 和 厦门国际FK012
        iomReqApplicationDomain.setProductName("公积金");
        iomReqApplicationDomain.setPayLoanBrh(createAccDto.getPayLoanBrh());
        iomReqApplicationDomain.setBusinessResource("1");
        iomReqApplicationDomain.setInputOrgId("2015072200000003");
        iomReqApplicationDomain.setProcFlg("0");
        iomReqApplicationDomain.setInputOrgName("中邮");
        iomReqApplicationDomain.setContractOrgId("2015072200000003");
        iomReqApplicationDomain.setContractOrgName("中邮");
        iomReqApplicationDomain.setOwnerOrgId("2015072200000003");
        iomReqApplicationDomain.setOwnerOrgName("北京");
        iomReqApplicationDomain.setPlatDamagesFee((Map) new HashMap().put("1","1.5"));
        iomReqApplicationDomain.setInvestorDamagesFee((Map) new HashMap().put("1","1.5"));
        iomReqApplicationDomain.setOtherFeesList((Map) new HashMap().put("businessRiskSum",".00"));
        businessapplyList.add(iomReqApplicationDomain);


        iomCustomerInfoDomain.setInDate(today);
        iomCustomerInfoDomain.setCustId(customerid);
        iomCustomerInfoDomain.setName("马铭锋");
        iomCustomerInfoDomain.setIdType("01");
        iomCustomerInfoDomain.setIdNum("51152619911214091X");
        iomCustomerInfoDomain.setSex("1");
        iomCustomerInfoDomain.setBirthday("1988/02/02");
        iomCustomerInfoDomain.setMarried("20");
        iomCustomerInfoDomain.setEdLevel("3");
        iomCustomerInfoDomain.setCaCompany("厦门");
        iomCustomerInfoDomain.setCaType("40");
        iomCustomerInfoDomain.setCaIndustryType("G");
        iomCustomerInfoDomain.setCaDepartment("厦门");
        iomCustomerInfoDomain.setCaDuty("厦门");
        iomCustomerInfoDomain.setCaOfficialRank("10");
        iomCustomerInfoDomain.setCaEnterTime("1991/09/12");
        iomCustomerInfoDomain.setCaPayType("PT0002");
        iomCustomerInfoDomain.setCaPayDay("15");
        iomCustomerInfoDomain.setCaWage(new BigDecimal(30000));
        iomCustomerInfoDomain.setCompType("40");
        iomCustomerInfoDomain.setCompTimeFounded("2009/05/25");
        iomCustomerInfoDomain.setCompShareholdingRatio("100");
        iomCustomerInfoDomain.setCompPremisesType("河北省廊坊市文安县西三官村");
        iomCustomerInfoDomain.setCompEmployeeAmount(Integer.getInteger("20"));
        iomCustomerInfoDomain.setCompProfits(new BigDecimal("800000"));
        iomCustomerInfoDomain.setCarPrice(new BigDecimal("203800"));
        iomCustomerInfoDomain.setCarLicense("Q39815");
        iomCustomerInfoDomain.setHouseType("LS0004");
        iomCustomerInfoDomain.setHouseAddress("beijing");
        iomCustomerInfoDomain.setHouseHasLoan("1");
        iomCustomerInfoDomain.setHouseBuildingArea(new BigDecimal("86"));
        iomCustomerInfoDomain.setHouseBuyTime("2008/08/01");
        iomCustomerInfoDomain.setHousePrice(new BigDecimal("50"));
        iomCustomerInfoDomain.setMemo("测试数据");
        customerList.add(iomCustomerInfoDomain);

        iomCardInfoDomain.setInDate(today);
        iomCardInfoDomain.setCustId(customerid);
        iomCardInfoDomain.setCardId(cardId);
        iomCardInfoDomain.setCardNo("622908553566782415");
        iomCardInfoDomain.setAliasCode("3091000");
        iomCardInfoDomain.setAgmtType(Integer.valueOf("1"));
        iomCardInfoDomain.setTreatyNo("20160729558882");
        iomCardInfoDomain.setAgmtStartDate("20170501");
        iomCardInfoDomain.setAgmtEndDate("20180501");
        iomCardInfoDomain.setPayLoanBrh("0");
        bankcardList.add(iomCardInfoDomain);

        iomAddressDomain.setInDate(today);
        iomAddressDomain.setCustId(customerid);
        iomAddressDomain.setAddressId(addressId);
        iomAddressDomain.setObjectType("借款人");
        iomAddressDomain.setAddressType("02");
        iomAddressDomain.setAddress("北京");
        iomAddressDomain.setValid("0");
        addressDomainList.add(iomAddressDomain);

        iomTelInfoDomain.setInDate(today);
        iomTelInfoDomain.setTelId(TelId);
        iomTelInfoDomain.setObjectType("联系人");
        iomTelInfoDomain.setObjectId(customerid);
        iomTelInfoDomain.setTelType("01");
        iomTelInfoDomain.setTel("18604722914");
        telephoneList.add(iomTelInfoDomain);

        iomContactInfoDomain.setInDate(today);
        iomContactInfoDomain.setCustId(customerid);
        iomContactInfoDomain.setContactId(ContactId);
        iomContactInfoDomain.setRelation("0503");
        iomContactInfoDomain.setName("测试1");
        contractList.add(iomContactInfoDomain);

        List<IomRepayPlanDomain> repayplanList = this.genRepayPlanList(serialno,today);

        IomRequest iomRequest = new IomRequest();
        iomRequest.setAddress(addressDomainList);
        iomRequest.setBusinessapply(businessapplyList);
        iomRequest.setCustomer(customerList);
        iomRequest.setBankcard(bankcardList);
        iomRequest.setTelephone(telephoneList);
        iomRequest.setRepayplan(repayplanList);
        iomRequest.setContract(contractList);

        return iomRequest;
    }

    /**
     * 调用网关生成还款计划
     * @return
     */
    public List<IomRepayPlanDomain> genRepayPlanList(String serialno,String today) {
        List<IomRepayPlanDomain> iomRepayPlanDomainList = Lists.newArrayList();
        Map<String,BigDecimal> l50rateJson = new HashMap<String, BigDecimal>();
        Map<String,BigDecimal> l55rateJson = new HashMap<String, BigDecimal>();
        Map<String,BigDecimal> bankEarlyRepaymentRateJson = new HashMap<String, BigDecimal>();
        RepayPlanRequest repayPlanRequest = new RepayPlanRequest();
        try {
            repayPlanRequest.setApplyNo("3444444444");
            repayPlanRequest.setBusinessType("A0042");
            repayPlanRequest.setSignTerm(Integer.valueOf("36"));
            repayPlanRequest.setL070Rate(new BigDecimal("0.015"));
            repayPlanRequest.setL080Rate(new BigDecimal("0.0150"));
//          缴费方式
            repayPlanRequest.setMethodFlag("1");
            repayPlanRequest.setMaxPutoutDays(1);
            repayPlanRequest.setSignSum(new BigDecimal("50000"));
            repayPlanRequest.setRepaymentMethod("RPT000010");
            repayPlanRequest.setLoanDate(new Date().toLocaleString());
            repayPlanRequest.setL080RateFlag("1");
            repayPlanRequest.setChannel("FK020");
            repayPlanRequest.setSignYearRate(new BigDecimal("0.0015"));
            repayPlanRequest.setL050RateJson(l50rateJson);
            repayPlanRequest.setL055RateJson(l55rateJson);
            repayPlanRequest.setType("0");
            repayPlanRequest.setBankEarlyRepaymentRateJson(bankEarlyRepaymentRateJson);
            String sign = SignUtils.signOuter(MapUtil.beanToMap(repayPlanRequest),"a6e237fc-5a53-4e29-a961-813de44a9929");
            logger.info("生成签名为 : " + sign);
            repayPlanRequest.setSign(sign);
            repayPlanRequest.setKey("bankApi");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.error("生成签名出错,请检查!");
        }
        String param = JSONObject.toJSONString(repayPlanRequest);
        String retStr = HttpUtil.postJson(repaymentUrl,param);
        JSONObject ret = JSONObject.parseObject(retStr);
        logger.info("Bank api return message is :{}",ret);
        if (ret.get("code").equals("0000")){
            List<Map<String, Object>> retlist = (List<Map<String,Object>>) ret.get("data");
            for (Map<String,Object> object:retlist){
                try {
                    IomRepayPlanDomain iomRepayPlanDomain = new IomRepayPlanDomain();
                    BeanUtils.copyProperties(iomRepayPlanDomain,object);
                    iomRepayPlanDomain.setInDate(today);
                    iomRepayPlanDomain.setSerialno(serialno);
                    iomRepayPlanDomain.setParkingFee(new BigDecimal("0"));
                    iomRepayPlanDomain.setGpsmonthFlow(new BigDecimal("0"));
                    iomRepayPlanDomain.setResprincipal((BigDecimal) object.get("resPrincipal"));
                    logger.info("iomRepayPlanDomain:{}",iomRepayPlanDomain);
                    iomRepayPlanDomainList.add(iomRepayPlanDomain);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            logger.info("iomRepayPlanDomainList : {}", Arrays.toString(iomRepayPlanDomainList.toArray()));
            return iomRepayPlanDomainList;
        }
        return null;

    }
}
