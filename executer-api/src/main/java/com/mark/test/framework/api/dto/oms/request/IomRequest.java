package com.mark.test.framework.api.dto.oms.request;


import com.mark.test.framework.api.dto.oms.*;

import java.util.List;

/**
 * 
 * Description:iom模块请求对象
 * @author
 * @date 2017年4月14日 下午2:13:20
 * 
 */
public class IomRequest {

    List<IomReqApplicationDomain> businessapply;

    List<IomCustomerInfoDomain> customer;

    List<IomCardInfoDomain> bankcard;

    List<IomAddressDomain> address;

    List<IomTelInfoDomain> telephone;

    List<IomRepayPlanDomain> repayplan;

    List<IomContactInfoDomain> contract;

    public List<IomReqApplicationDomain> getBusinessapply() {
        return businessapply;
    }

    public void setBusinessapply(List<IomReqApplicationDomain> businessapply) {
        this.businessapply = businessapply;
    }

    public List<IomCustomerInfoDomain> getCustomer() {
        return customer;
    }

    public void setCustomer(List<IomCustomerInfoDomain> customer) {
        this.customer = customer;
    }

    public List<IomCardInfoDomain> getBankcard() {
        return bankcard;
    }

    public void setBankcard(List<IomCardInfoDomain> bankcard) {
        this.bankcard = bankcard;
    }

    public List<IomAddressDomain> getAddress() {
        return address;
    }

    public void setAddress(List<IomAddressDomain> address) {
        this.address = address;
    }

    public List<IomTelInfoDomain> getTelephone() {
        return telephone;
    }

    public void setTelephone(List<IomTelInfoDomain> telephone) {
        this.telephone = telephone;
    }

    public List<IomRepayPlanDomain> getRepayplan() {
        return repayplan;
    }

    public void setRepayplan(List<IomRepayPlanDomain> repayplan) {
        this.repayplan = repayplan;
    }

    public List<IomContactInfoDomain> getContract() {
        return contract;
    }

    public void setContract(List<IomContactInfoDomain> contract) {
        this.contract = contract;
    }
}
