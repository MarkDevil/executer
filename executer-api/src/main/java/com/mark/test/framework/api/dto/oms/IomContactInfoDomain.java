package com.mark.test.framework.api.dto.oms;

/**
 * 
 * 
 * Description:iom模块联系人信息
 * @author mark
 * @date 2017年4月14日 下午3:16:38
 *
 */
public class IomContactInfoDomain {
    private String inDate;

    private String contactId;

    private String custId;

    private String relation;

    private String name;

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}