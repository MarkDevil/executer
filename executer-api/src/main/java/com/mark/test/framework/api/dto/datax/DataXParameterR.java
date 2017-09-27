package com.mark.test.framework.api.dto.datax;

import java.util.List;

/**
 * Created by mark .
 * Data   : 2017/8/1
 * Author : mark
 * Desc   :
 */

public class DataXParameterR {
    private List<DataXconnection> connection;
    private List<String> column;
    private String username;
    private String password;

    public List<DataXconnection> getConnection() {
        return connection;
    }

    public void setConnection(List<DataXconnection> connection) {
        this.connection = connection;
    }

    public List<String> getColumn() {
        return column;
    }

    public void setColumn(List<String> column) {
        this.column = column;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
