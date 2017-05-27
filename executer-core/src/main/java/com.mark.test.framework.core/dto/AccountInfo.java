package com.mark.test.framework.core.dto;

public class AccountInfo {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.SERIALNO
     *
     * @mbggenerated
     */
    private String serialno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.USERID
     *
     * @mbggenerated
     */
    private String userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.ACCOUNTTYPE
     *
     * @mbggenerated
     */
    private String accounttype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.ACCOUNTNO
     *
     * @mbggenerated
     */
    private String accountno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.ACCOUNTNAME
     *
     * @mbggenerated
     */
    private String accountname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.ACCOUNTBELONG
     *
     * @mbggenerated
     */
    private String accountbelong;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.STATUS
     *
     * @mbggenerated
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.LIMITAMOUNT
     *
     * @mbggenerated
     */
    private String limitamount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.INPUTTIME
     *
     * @mbggenerated
     */
    private String inputtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.UPDATETIME
     *
     * @mbggenerated
     */
    private String updatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.BELONGAREA
     *
     * @mbggenerated
     */
    private String belongarea;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.CHECKNUMBER
     *
     * @mbggenerated
     */
    private String checknumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.ACCOUNTNO2
     *
     * @mbggenerated
     */
    private String accountno2;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.VERIFYTYPE
     *
     * @mbggenerated
     */
    private String verifytype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.ISRETURNCARD
     *
     * @mbggenerated
     */
    private String isreturncard;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.SUBACCOUNTBELONG
     *
     * @mbggenerated
     */
    private String subaccountbelong;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.PROVINCE
     *
     * @mbggenerated
     */
    private String province;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.CITY
     *
     * @mbggenerated
     */
    private String city;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.channelCardId
     *
     * @mbggenerated
     */
    private String channelcardid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.PHONENO
     *
     * @mbggenerated
     */
    private String phoneno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.LoanapplyID
     *
     * @mbggenerated
     */
    private String loanapplyid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.BankBranch
     *
     * @mbggenerated
     */
    private String bankbranch;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.BankBranch_Second
     *
     * @mbggenerated
     */
    private String bankbranchSecond;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.source
     *
     * @mbggenerated
     */
    private String source;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.LoanCardFlag
     *
     * @mbggenerated
     */
    private String loancardflag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_info.NybankAcctNo
     *
     * @mbggenerated
     */
    private String nybankacctno;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_info
     *
     * @mbggenerated
     */
    public AccountInfo(String serialno, String userid, String accounttype, String accountno, String accountname, String accountbelong, String status, String limitamount, String inputtime, String updatetime, String belongarea, String checknumber, String accountno2, String verifytype, String isreturncard, String subaccountbelong, String province, String city, String channelcardid, String phoneno, String loanapplyid, String bankbranch, String bankbranchSecond, String source, String loancardflag, String nybankacctno) {
        this.serialno = serialno;
        this.userid = userid;
        this.accounttype = accounttype;
        this.accountno = accountno;
        this.accountname = accountname;
        this.accountbelong = accountbelong;
        this.status = status;
        this.limitamount = limitamount;
        this.inputtime = inputtime;
        this.updatetime = updatetime;
        this.belongarea = belongarea;
        this.checknumber = checknumber;
        this.accountno2 = accountno2;
        this.verifytype = verifytype;
        this.isreturncard = isreturncard;
        this.subaccountbelong = subaccountbelong;
        this.province = province;
        this.city = city;
        this.channelcardid = channelcardid;
        this.phoneno = phoneno;
        this.loanapplyid = loanapplyid;
        this.bankbranch = bankbranch;
        this.bankbranchSecond = bankbranchSecond;
        this.source = source;
        this.loancardflag = loancardflag;
        this.nybankacctno = nybankacctno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.SERIALNO
     *
     * @return the value of account_info.SERIALNO
     *
     * @mbggenerated
     */
    public String getSerialno() {
        return serialno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.USERID
     *
     * @return the value of account_info.USERID
     *
     * @mbggenerated
     */
    public String getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.ACCOUNTTYPE
     *
     * @return the value of account_info.ACCOUNTTYPE
     *
     * @mbggenerated
     */
    public String getAccounttype() {
        return accounttype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.ACCOUNTNO
     *
     * @return the value of account_info.ACCOUNTNO
     *
     * @mbggenerated
     */
    public String getAccountno() {
        return accountno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.ACCOUNTNAME
     *
     * @return the value of account_info.ACCOUNTNAME
     *
     * @mbggenerated
     */
    public String getAccountname() {
        return accountname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.ACCOUNTBELONG
     *
     * @return the value of account_info.ACCOUNTBELONG
     *
     * @mbggenerated
     */
    public String getAccountbelong() {
        return accountbelong;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.STATUS
     *
     * @return the value of account_info.STATUS
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.LIMITAMOUNT
     *
     * @return the value of account_info.LIMITAMOUNT
     *
     * @mbggenerated
     */
    public String getLimitamount() {
        return limitamount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.INPUTTIME
     *
     * @return the value of account_info.INPUTTIME
     *
     * @mbggenerated
     */
    public String getInputtime() {
        return inputtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.UPDATETIME
     *
     * @return the value of account_info.UPDATETIME
     *
     * @mbggenerated
     */
    public String getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.BELONGAREA
     *
     * @return the value of account_info.BELONGAREA
     *
     * @mbggenerated
     */
    public String getBelongarea() {
        return belongarea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.CHECKNUMBER
     *
     * @return the value of account_info.CHECKNUMBER
     *
     * @mbggenerated
     */
    public String getChecknumber() {
        return checknumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.ACCOUNTNO2
     *
     * @return the value of account_info.ACCOUNTNO2
     *
     * @mbggenerated
     */
    public String getAccountno2() {
        return accountno2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.VERIFYTYPE
     *
     * @return the value of account_info.VERIFYTYPE
     *
     * @mbggenerated
     */
    public String getVerifytype() {
        return verifytype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.ISRETURNCARD
     *
     * @return the value of account_info.ISRETURNCARD
     *
     * @mbggenerated
     */
    public String getIsreturncard() {
        return isreturncard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.SUBACCOUNTBELONG
     *
     * @return the value of account_info.SUBACCOUNTBELONG
     *
     * @mbggenerated
     */
    public String getSubaccountbelong() {
        return subaccountbelong;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.PROVINCE
     *
     * @return the value of account_info.PROVINCE
     *
     * @mbggenerated
     */
    public String getProvince() {
        return province;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.CITY
     *
     * @return the value of account_info.CITY
     *
     * @mbggenerated
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.channelCardId
     *
     * @return the value of account_info.channelCardId
     *
     * @mbggenerated
     */
    public String getChannelcardid() {
        return channelcardid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.PHONENO
     *
     * @return the value of account_info.PHONENO
     *
     * @mbggenerated
     */
    public String getPhoneno() {
        return phoneno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.LoanapplyID
     *
     * @return the value of account_info.LoanapplyID
     *
     * @mbggenerated
     */
    public String getLoanapplyid() {
        return loanapplyid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.BankBranch
     *
     * @return the value of account_info.BankBranch
     *
     * @mbggenerated
     */
    public String getBankbranch() {
        return bankbranch;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.BankBranch_Second
     *
     * @return the value of account_info.BankBranch_Second
     *
     * @mbggenerated
     */
    public String getBankbranchSecond() {
        return bankbranchSecond;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.source
     *
     * @return the value of account_info.source
     *
     * @mbggenerated
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.LoanCardFlag
     *
     * @return the value of account_info.LoanCardFlag
     *
     * @mbggenerated
     */
    public String getLoancardflag() {
        return loancardflag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_info.NybankAcctNo
     *
     * @return the value of account_info.NybankAcctNo
     *
     * @mbggenerated
     */
    public String getNybankacctno() {
        return nybankacctno;
    }
}