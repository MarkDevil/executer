package com.mark.test.framework.api.dto;

/**
 * Created by MingfengMa .
 * Data   : 2017/8/25
 * Author : mark
 * Desc   :
 */

public class CreateAccDto {
    private int term;
    private int loanAmount;
    private String productName;
    private String payLoanBrh;

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getLoanamount() {
        return loanAmount;
    }

    public void setLoanamount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPayLoanBrh() {
        return payLoanBrh;
    }

    public void setPayLoanBrh(String payLoanBrh) {
        this.payLoanBrh = payLoanBrh;
    }

}
