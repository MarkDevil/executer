package com.mark.test.framework.core.utils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by mark .
 * Data   : 2017/11/26
 * Author : mark
 * Desc   :
 */

public class DynamicDataSource extends AbstractRoutingDataSource{


    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceType();
    }
}
