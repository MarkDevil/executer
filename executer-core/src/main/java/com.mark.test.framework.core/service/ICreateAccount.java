package com.mark.test.framework.core.service;

import com.mark.test.framework.api.dto.CreateAccDto;

/**
 * Created by mark .
 * Data   : 2017/8/25
 * Author : mark
 * Desc   :
 */
public interface ICreateAccount {
    void createAcc(CreateAccDto createAccDto);

    boolean startBatch();
}
