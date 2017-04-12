package com.mark.test.framework.core.dto;

/**
 * Created by MingfengMa .
 * Data   : 2017/4/12
 * Author : mark
 * Desc   :
 */

public class TestRequestDto {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("id : ").append(id).append("\t");
        stringBuffer.append("name : ").append(name);
        return String.valueOf(stringBuffer);
    }
}
