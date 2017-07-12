package com.mark.test.framework.api.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by MingfengMa .
 * Data   : 2017/6/26
 * Author : mark
 * Desc   :
 */

public class SQLConnectionDTO {
    private String databaseType;
    private String driver;
    private String url;
    private String userName;
    private String password;

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(SQLConnectionDTO.this);
    }
}
