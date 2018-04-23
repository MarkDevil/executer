package com.mark.test.framework.core.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;

/**
 * Created by mark .
 * Data   : 2018/4/20
 */

public class DbCompareResponseDto {
    private String ip;
    private List<String> tables;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
