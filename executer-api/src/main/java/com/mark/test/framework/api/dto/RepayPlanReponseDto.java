package com.mark.test.framework.api.dto;

import java.math.BigDecimal;

public class RepayPlanReponseDto {

    private String applyNo;

    private Integer term;

    private BigDecimal payAmt;

    private BigDecimal interest;

    private BigDecimal principal;

    private BigDecimal penalty;

    private BigDecimal riskAmt;

    private BigDecimal serviceAmt;

    private BigDecimal returnServiceAmt;

    private String repayDate;

    private BigDecimal lumpRepayAmt;

    private BigDecimal earlyRepayAmt;

    private BigDecimal resPrincipal;

    private BigDecimal forFeitPenalty;

    private BigDecimal parkingAmt;

    private String payLoanBrh;

    private BigDecimal gpsMonthFlow;
    
    private BigDecimal hbEarlyRepayAmt;

    private BigDecimal bankEarlyRepayAmt;
    
    public BigDecimal getHbEarlyRepayAmt() {
		return hbEarlyRepayAmt;
	}

	public void setHbEarlyRepayAmt(BigDecimal hbEarlyRepayAmt) {
		this.hbEarlyRepayAmt = hbEarlyRepayAmt;
	}

	public BigDecimal getBankEarlyRepayAmt() {
		return bankEarlyRepayAmt;
	}

	public void setBankEarlyRepayAmt(BigDecimal bankEarlyRepayAmt) {
		this.bankEarlyRepayAmt = bankEarlyRepayAmt;
	}


    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo == null ? null : applyNo.trim();
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public BigDecimal getPayAmt() {
        return payAmt;
    }

    public void setPayAmt(BigDecimal payAmt) {
        this.payAmt = payAmt;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getPenalty() {
        return penalty;
    }

    public void setPenalty(BigDecimal penalty) {
        this.penalty = penalty;
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

    public BigDecimal getReturnServiceAmt() {
        return returnServiceAmt;
    }

    public void setReturnServiceAmt(BigDecimal returnServiceAmt) {
        this.returnServiceAmt = returnServiceAmt;
    }

    public String getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(String repayDate) {
        this.repayDate = repayDate == null ? null : repayDate.trim();
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

    public BigDecimal getResPrincipal() {
        return resPrincipal;
    }

    public void setResPrincipal(BigDecimal resPrincipal) {
        this.resPrincipal = resPrincipal;
    }

    public BigDecimal getForFeitPenalty() {
        return forFeitPenalty;
    }

    public void setForFeitPenalty(BigDecimal forFeitPenalty) {
        this.forFeitPenalty = forFeitPenalty;
    }

    public BigDecimal getParkingAmt() {
        return parkingAmt;
    }

    public void setParkingAmt(BigDecimal parkingAmt) {
        this.parkingAmt = parkingAmt;
    }

    public String getPayLoanBrh() {
        return payLoanBrh;
    }

    public void setPayLoanBrh(String payLoanBrh) {
        this.payLoanBrh = payLoanBrh == null ? null : payLoanBrh.trim();
    }

    public BigDecimal getGpsMonthFlow() {
        return gpsMonthFlow;
    }

    public void setGpsMonthFlow(BigDecimal gpsMonthFlow) {
        this.gpsMonthFlow = gpsMonthFlow;
    }

}