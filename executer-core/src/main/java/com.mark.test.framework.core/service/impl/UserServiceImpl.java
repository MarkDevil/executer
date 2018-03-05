package com.mark.test.framework.core.service.impl;

import com.mark.test.framework.api.dto.UserDto;
import com.mark.test.framework.core.annotation.DataSource;
import com.mark.test.framework.core.dao.UserInfoMapper;
import com.mark.test.framework.core.service.IUser;
import com.mark.test.framework.core.utils.DataSourceContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mark .
 * Data   : 2017/11/26
 * Author : mark
 * Desc   :
 */
@Service
@DataSource(value = "testDataSource")
public class UserServiceImpl implements IUser{

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserInfoMapper userInfoMapper;


    @Autowired(required = false)
    public UserServiceImpl(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public boolean isExsitUser(String name) {
        logger.info("DataSource : {}", DataSourceContextHolder.getDataSourceType());
        return this.getUser(name) != null;
    }

    @Override
    public UserDto getUser(String name) {
        logger.info("DataSource : {}", DataSourceContextHolder.getDataSourceType());
        return userInfoMapper.selectByName(name);
    }

}
