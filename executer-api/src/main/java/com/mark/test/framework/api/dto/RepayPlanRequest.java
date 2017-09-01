package com.mark.test.framework.api.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by qll on 2017/4/12.
 */
public class RepayPlanRequest {
    @NotBlank(message="applyNo不能为空")
    private String applyNo;//申请单号
    private String businessType;//产品类型
    private Integer signTerm;//签约期数
    private BigDecimal l070Rate;//风险准备金率
    private BigDecimal l080Rate;//融资服务费率
    private String methodFlag;//缴费方式“0”期缴“1”趸缴
    private Integer maxPutoutDays;//起息日
    private BigDecimal signSum;//签约金额
    private String repaymentMethod;//还款方式 RPT000010	等额本息,RPT000040	按月还息到期还本
    @NotBlank(message="loanDate不能为空")
    private String loanDate;//放款日期
    @NotBlank(message="l080RateFlag不能为空")
    private String l080RateFlag;//是否退还 融资服务费
    @NotBlank(message="channel不能为空")
    private String channel;//放款渠道
    private BigDecimal signYearRate;//年利率
    private Map<String,BigDecimal> l050RateJson;//投资人收取违约金利率
    private Map<String,BigDecimal> l055RateJson;//平台收取违约金利率
    @NotBlank(message="type不能为空")
    private String type;//保存db标识符 “0”试算“1”正式
    private Map<String,BigDecimal> bankEarlyRepaymentRateJson;//银行提前还款违约金利率
    private String key;
    private String sign;

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public Integer getSignTerm() {
        return signTerm;
    }

    public void setSignTerm(Integer signTerm) {
        this.signTerm = signTerm;
    }

    public void setMaxPutoutDays(Integer maxPutoutDays) {
        this.maxPutoutDays = maxPutoutDays;
    }

    public BigDecimal getL070Rate() {
        return l070Rate;
    }

    public void setL070Rate(BigDecimal l070Rate) {
        this.l070Rate = l070Rate;
    }

    public BigDecimal getL080Rate() {
        return l080Rate;
    }

    public void setL080Rate(BigDecimal l080Rate) {
        this.l080Rate = l080Rate;
    }

    public String getMethodFlag() {
        return methodFlag;
    }

    public void setMethodFlag(String methodFlag) {
        this.methodFlag = methodFlag;
    }

    public Integer getMaxPutoutDays() {
        return maxPutoutDays;
    }

    public BigDecimal getSignSum() {
        return signSum;
    }

    public void setSignSum(BigDecimal signSum) {
        this.signSum = signSum;
    }

    public String getRepaymentMethod() {
        return repaymentMethod;
    }

    public void setRepaymentMethod(String repaymentMethod) {
        this.repaymentMethod = repaymentMethod;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getL080RateFlag() {
        return l080RateFlag;
    }

    public void setL080RateFlag(String l080RateFlag) {
        this.l080RateFlag = l080RateFlag;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public BigDecimal getSignYearRate() {
        return signYearRate;
    }

    public void setSignYearRate(BigDecimal signYearRate) {
        this.signYearRate = signYearRate;
    }

    public Map<String, BigDecimal> getL050RateJson() {
        return l050RateJson;
    }

    public void setL050RateJson(Map<String, BigDecimal> l050RateJson) {
        this.l050RateJson = l050RateJson;
    }

    public Map<String, BigDecimal> getL055RateJson() {
        return l055RateJson;
    }

    public void setL055RateJson(Map<String, BigDecimal> l055RateJson) {
        this.l055RateJson = l055RateJson;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, BigDecimal> getBankEarlyRepaymentRateJson() {
        return bankEarlyRepaymentRateJson;
    }

    public void setBankEarlyRepaymentRateJson(Map<String, BigDecimal> bankEarlyRepaymentRateJson) {
        this.bankEarlyRepaymentRateJson = bankEarlyRepaymentRateJson;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(RepayPlanRequest.this);
    }
}
