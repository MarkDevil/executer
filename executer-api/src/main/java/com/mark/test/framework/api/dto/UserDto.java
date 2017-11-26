package com.mark.test.framework.api.dto;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Created by mark .
 * Data   : 2017/11/26
 * Author : mark
 * Desc   :
 */

public class UserDto {
    private int id;
    private String name;
    private String passwd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
