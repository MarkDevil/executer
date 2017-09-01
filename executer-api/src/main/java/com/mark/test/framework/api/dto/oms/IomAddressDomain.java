package com.mark.test.framework.api.dto.oms;

/**
 * 
 * 
 * Description:iom模块地址封装对象
 * @author dafang
 * @date 2017年4月14日 下午1:57:45
 *
 */
public class IomAddressDomain {
    private String inDate;

    private String addressId;

    private String custId;

    private String objectType;

    private String addressType;

    private String address;

    private String valid;

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}