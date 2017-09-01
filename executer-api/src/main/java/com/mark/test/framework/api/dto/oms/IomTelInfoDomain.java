package com.mark.test.framework.api.dto.oms;

/**
 * 
 * 
 * Description:iom模块联系电话信息
 * @author dafang
 * @date 2017年4月14日 下午3:19:01
 *
 */
public class IomTelInfoDomain {
    private String inDate;

    private String telId;

    private String objectType;

    private String objectId;

    private String telType;

    private String tel;

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getTelId() {
        return telId;
    }

    public void setTelId(String telId) {
        this.telId = telId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getTelType() {
        return telType;
    }

    public void setTelType(String telType) {
        this.telType = telType;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}