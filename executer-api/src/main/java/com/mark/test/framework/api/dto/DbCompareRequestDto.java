package com.mark.test.framework.api.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * Created by mark .
 * Data   : 2017/11/29
 * Author : mark
 * Desc   :
 */

public class DbCompareRequestDto implements Serializable {

    private String sourceDbName;
    private String sourceDbIp;
    private String sourceDbPort;
    private String sourceDbUser;
    private String sourceDbPasswd;
    private String targetDbName;
    private String targetDbIp;
    private String targetDbPort;
    private String targetDbUser;
    private String targetDbPasswd;
    private String tableType;

    public String getSourceDbName() {
        return sourceDbName;
    }

    public void setSourceDbName(String sourceDbName) {
        this.sourceDbName = sourceDbName;
    }

    public String getTargetDbName() {
        return targetDbName;
    }

    public void setTargetDbName(String targetDbName) {
        this.targetDbName = targetDbName;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getSourceDbPort() {
        return sourceDbPort;
    }

    public void setSourceDbPort(String sourceDbPort) {
        this.sourceDbPort = sourceDbPort;
    }

    public String getSourceDbUser() {
        return sourceDbUser;
    }

    public void setSourceDbUser(String sourceDbUser) {
        this.sourceDbUser = sourceDbUser;
    }

    public String getSourceDbPasswd() {
        return sourceDbPasswd;
    }

    public void setSourceDbPasswd(String sourceDbPasswd) {
        this.sourceDbPasswd = sourceDbPasswd;
    }

    public String getTargetDbPort() {
        return targetDbPort;
    }

    public void setTargetDbPort(String targetDbPort) {
        this.targetDbPort = targetDbPort;
    }

    public String getTargetDbUser() {
        return targetDbUser;
    }

    public void setTargetDbUser(String targetDbUser) {
        this.targetDbUser = targetDbUser;
    }

    public String getTargetDbPasswd() {
        return targetDbPasswd;
    }

    public void setTargetDbPasswd(String targetDbPasswd) {
        this.targetDbPasswd = targetDbPasswd;
    }

    public String getSourceDbIp() {
        return sourceDbIp;
    }

    public void setSourceDbIp(String sourceDbIp) {
        this.sourceDbIp = sourceDbIp;
    }

    public String getTargetDbIp() {
        return targetDbIp;
    }

    public void setTargetDbIp(String targetDbIp) {
        this.targetDbIp = targetDbIp;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this);
    }
}
