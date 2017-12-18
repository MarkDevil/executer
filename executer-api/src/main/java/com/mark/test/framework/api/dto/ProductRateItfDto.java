package com.mark.test.framework.api.dto;

import java.util.List;
import java.util.Map;

/**
 * Created by mark .
 * Data   : 2017/12/14
 * Author : mark
 * Desc   :
 */

public class ProductRateItfDto {
    private String productName;
    private List<Map<String,Object>> rateList;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<Map<String, Object>> getRateList() {
        return rateList;
    }

    public void setRateList(List<Map<String, Object>> rateList) {
        this.rateList = rateList;
    }
}
