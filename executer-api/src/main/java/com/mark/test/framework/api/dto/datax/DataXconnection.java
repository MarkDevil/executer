package com.mark.test.framework.api.dto.datax;

import java.util.List;

/**
 * Created by mark .
 * Data   : 2017/8/1
 * Author : mark
 * Desc   :
 */

public class DataXconnection {
    private List<String> table;
    private List<String> jdbcUrl;

    public List<String> getTable() {
        return table;
    }

    public void setTable(List<String> table) {
        this.table = table;
    }

    public List<String> getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(List<String> jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }
}
