package com.mark.test.framework.core.service;

import com.mark.test.framework.api.dto.DbCompareRequestDto;

import java.util.List;

/**
 * Created by mark .
 * Data   : 2017/11/29
 * Author : mark
 * Desc   :
 */
public interface IDbCompare {

    List<String> compareDb(DbCompareRequestDto dbCompareRequestDto);
}
