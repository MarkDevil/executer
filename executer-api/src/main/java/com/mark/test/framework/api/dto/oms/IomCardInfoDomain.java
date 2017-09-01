package com.mark.test.framework.api.dto.oms;

/**
 * 
 * 
 * Description:iom模块银行卡信息domain
 * @author mark
 * @date 2017年4月14日 下午3:16:04
 *
 */
public class IomCardInfoDomain {
    private String inDate;

    private String cardId;

    private String cardNo;

    private String custId;

    private String aliasCode;

    private Integer agmtType;

    private String treatyNo;

    private String agmtStartDate;

    private String agmtEndDate;

    private String payLoanBrh;

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getAliasCode() {
        return aliasCode;
    }

    public void setAliasCode(String aliasCode) {
        this.aliasCode = aliasCode;
    }

    public Integer getAgmtType() {
        return agmtType;
    }

    public void setAgmtType(Integer agmtType) {
        this.agmtType = agmtType;
    }

    public String getTreatyNo() {
        return treatyNo;
    }

    public void setTreatyNo(String treatyNo) {
        this.treatyNo = treatyNo;
    }

    public String getAgmtStartDate() {
        return agmtStartDate;
    }

    public void setAgmtStartDate(String agmtStartDate) {
        this.agmtStartDate = agmtStartDate;
    }

    public String getAgmtEndDate() {
        return agmtEndDate;
    }

    public void setAgmtEndDate(String agmtEndDate) {
        this.agmtEndDate = agmtEndDate;
    }

    public String getPayLoanBrh() {
        return payLoanBrh;
    }

    public void setPayLoanBrh(String payLoanBrh) {
        this.payLoanBrh = payLoanBrh;
    }
}