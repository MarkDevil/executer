package com.mark.test.framework.api.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

/**
 * Created by mark .
 * Data   : 2017/12/12
 * Author : mark
 * Desc   :
 */

public class ScheduleRequestDto {
    private String jobClassName;
    private String identify;
    private String triggerType;
    private Map<String,Object> interval;

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    public Map<String, Object> getInterval() {
        return interval;
    }

    public void setInterval(Map<String, Object> interval) {
        this.interval = interval;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
