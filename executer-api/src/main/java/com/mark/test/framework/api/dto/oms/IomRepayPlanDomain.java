package com.mark.test.framework.api.dto.oms;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.math.BigDecimal;

/**
 * 
 * 
 * Description:iom模块还款计划domain
 * @author dafang
 * @date 2017年4月14日 下午3:18:09
 *
 */
public class IomRepayPlanDomain {
    private String inDate;

    private String serialno;

    private Short term;

    private String repayDate;

    private BigDecimal principal;

    private BigDecimal interest;

    private BigDecimal riskAmt;

    private BigDecimal parkingFee;

    private BigDecimal serviceAmt;

    private BigDecimal payAmt;

    private BigDecimal resprincipal;

    private BigDecimal returnServiceAmt;

    private BigDecimal lumpRepayAmt;

    private BigDecimal earlyRepayAmt;

    private BigDecimal gpsmonthFlow;

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public Short getTerm() {
        return term;
    }

    public void setTerm(Short term) {
        this.term = term;
    }

    public String getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(String repayDate) {
        this.repayDate = repayDate;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getRiskAmt() {
        return riskAmt;
    }

    public void setRiskAmt(BigDecimal riskAmt) {
        this.riskAmt = riskAmt;
    }

    public BigDecimal getServiceAmt() {
        return serviceAmt;
    }

    public void setServiceAmt(BigDecimal serviceAmt) {
        this.serviceAmt = serviceAmt;
    }

    public BigDecimal getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(BigDecimal payAmt) {
        this.payAmt = payAmt;
    }

    public BigDecimal getReturnServiceAmt() {
        return returnServiceAmt;
    }

    public void setReturnServiceAmt(BigDecimal returnServiceAmt) {
        this.returnServiceAmt = returnServiceAmt;
    }

    public BigDecimal getLumpRepayAmt() {
        return lumpRepayAmt;
    }

    public void setLumpRepayAmt(BigDecimal lumpRepayAmt) {
        this.lumpRepayAmt = lumpRepayAmt;
    }

    public BigDecimal getEarlyRepayAmt() {
        return earlyRepayAmt;
    }

    public void setEarlyRepayAmt(BigDecimal earlyRepayAmt) {
        this.earlyRepayAmt = earlyRepayAmt;
    }

    public BigDecimal getParkingFee() { return parkingFee; }

    public void setParkingFee(BigDecimal parkingFee) {
        this.parkingFee = parkingFee;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public BigDecimal getGpsmonthFlow() {
        return gpsmonthFlow;
    }

    public void setGpsmonthFlow(BigDecimal gpsmonthFlow) {
        this.gpsmonthFlow = gpsmonthFlow;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(IomRepayPlanDomain.this);
    }


    public BigDecimal getResprincipal() {
        return resprincipal;
    }

    public void setResprincipal(BigDecimal resprincipal) {
        this.resprincipal = resprincipal;
    }
}