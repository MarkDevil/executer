package com.mark.test.framework.core.constat;

/**
 * Created by mark .
 * Data   : 2017/4/21
 * Author : mark
 * Desc   :
 */

public class BaseInfo {
    //业务标识
    private String key = "yanfa";
    //测试秘钥
    private String secret = "0b6bd65156c9457eb0ea5ad3d5a21f9e";

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
