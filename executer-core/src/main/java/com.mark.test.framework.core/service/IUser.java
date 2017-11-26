package com.mark.test.framework.core.service;

import com.mark.test.framework.api.dto.UserDto;

/**
 * Created by mark .
 * Data   : 2017/11/26
 * Author : mark
 * Desc   :
 */
public interface IUser {
    boolean isExsitUser(String name);
    UserDto getUser(String name);
}
