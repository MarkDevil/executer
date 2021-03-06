package com.mark.test.framework.api.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.Date;


public class GwTransfers {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.request_no
     *
     * @mbggenerated
     */
    private String requestNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.channel_code
     *
     * @mbggenerated
     */
    private String channelCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.gw_request_no
     *
     * @mbggenerated
     */
    private String gwRequestNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.main_account_no
     *
     * @mbggenerated
     */
    private String mainAccountNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.pay_account_no
     *
     * @mbggenerated
     */
    private String payAccountNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.tran_type
     *
     * @mbggenerated
     */
    private String tranType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.status
     *
     * @mbggenerated
     */
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.gw_status
     *
     * @mbggenerated
     */
    private String gwStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.recv_account_no
     *
     * @mbggenerated
     */
    private String recvAccountNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.recv_account_name
     *
     * @mbggenerated
     */
    private String recvAccountName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.tran_amount
     *
     * @mbggenerated
     */
    private BigDecimal tranAmount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.err_code
     *
     * @mbggenerated
     */
    private String errCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.err_msg
     *
     * @mbggenerated
     */
    private String errMsg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.request_time
     *
     * @mbggenerated
     */
    private Date requestTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.finish_time
     *
     * @mbggenerated
     */
    private Date finishTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.recv_bankcode
     *
     * @mbggenerated
     */
    private String recvBankcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.recv_tgfi
     *
     * @mbggenerated
     */
    private String recvTgfi;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gw_transfers.recv_banknm
     *
     * @mbggenerated
     */
    private String recvBanknm;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gw_transfers
     *
     * @mbggenerated
     */
    public GwTransfers(Long id, String requestNo, String channelCode, String gwRequestNo, String mainAccountNo, String payAccountNo, String tranType, String status, String gwStatus, String recvAccountNo, String recvAccountName, BigDecimal tranAmount, String remark, String errCode, String errMsg, Date createTime, Date updateTime, Date requestTime, Date finishTime, String recvBankcode, String recvTgfi, String recvBanknm) {
        this.id = id;
        this.requestNo = requestNo;
        this.channelCode = channelCode;
        this.gwRequestNo = gwRequestNo;
        this.mainAccountNo = mainAccountNo;
        this.payAccountNo = payAccountNo;
        this.tranType = tranType;
        this.status = status;
        this.gwStatus = gwStatus;
        this.recvAccountNo = recvAccountNo;
        this.recvAccountName = recvAccountName;
        this.tranAmount = tranAmount;
        this.remark = remark;
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.requestTime = requestTime;
        this.finishTime = finishTime;
        this.recvBankcode = recvBankcode;
        this.recvTgfi = recvTgfi;
        this.recvBanknm = recvBanknm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.id
     *
     * @return the value of gw_transfers.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.request_no
     *
     * @return the value of gw_transfers.request_no
     *
     * @mbggenerated
     */
    public String getRequestNo() {
        return requestNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.channel_code
     *
     * @return the value of gw_transfers.channel_code
     *
     * @mbggenerated
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.gw_request_no
     *
     * @return the value of gw_transfers.gw_request_no
     *
     * @mbggenerated
     */
    public String getGwRequestNo() {
        return gwRequestNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.main_account_no
     *
     * @return the value of gw_transfers.main_account_no
     *
     * @mbggenerated
     */
    public String getMainAccountNo() {
        return mainAccountNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.pay_account_no
     *
     * @return the value of gw_transfers.pay_account_no
     *
     * @mbggenerated
     */
    public String getPayAccountNo() {
        return payAccountNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.tran_type
     *
     * @return the value of gw_transfers.tran_type
     *
     * @mbggenerated
     */
    public String getTranType() {
        return tranType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.status
     *
     * @return the value of gw_transfers.status
     *
     * @mbggenerated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.gw_status
     *
     * @return the value of gw_transfers.gw_status
     *
     * @mbggenerated
     */
    public String getGwStatus() {
        return gwStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.recv_account_no
     *
     * @return the value of gw_transfers.recv_account_no
     *
     * @mbggenerated
     */
    public String getRecvAccountNo() {
        return recvAccountNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.recv_account_name
     *
     * @return the value of gw_transfers.recv_account_name
     *
     * @mbggenerated
     */
    public String getRecvAccountName() {
        return recvAccountName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.tran_amount
     *
     * @return the value of gw_transfers.tran_amount
     *
     * @mbggenerated
     */
    public BigDecimal getTranAmount() {
        return tranAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.remark
     *
     * @return the value of gw_transfers.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.err_code
     *
     * @return the value of gw_transfers.err_code
     *
     * @mbggenerated
     */
    public String getErrCode() {
        return errCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.err_msg
     *
     * @return the value of gw_transfers.err_msg
     *
     * @mbggenerated
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.create_time
     *
     * @return the value of gw_transfers.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.update_time
     *
     * @return the value of gw_transfers.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.request_time
     *
     * @return the value of gw_transfers.request_time
     *
     * @mbggenerated
     */
    public Date getRequestTime() {
        return requestTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.finish_time
     *
     * @return the value of gw_transfers.finish_time
     *
     * @mbggenerated
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.recv_bankcode
     *
     * @return the value of gw_transfers.recv_bankcode
     *
     * @mbggenerated
     */
    public String getRecvBankcode() {
        return recvBankcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.recv_tgfi
     *
     * @return the value of gw_transfers.recv_tgfi
     *
     * @mbggenerated
     */
    public String getRecvTgfi() {
        return recvTgfi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gw_transfers.recv_banknm
     *
     * @return the value of gw_transfers.recv_banknm
     *
     * @mbggenerated
     */
    public String getRecvBanknm() {
        return recvBanknm;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(GwTransfers.this);
    }
}