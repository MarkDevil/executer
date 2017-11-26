package com.mark.test.framework.core.utils;

/**
 * Created by mark .
 * Data   : 2017/11/26
 * Author : mark
 * Desc   :
 */

public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    /**
     * 设置数据源类型
     * @param dataSourceType
     */
    public static void setDataSourceType(String dataSourceType){
        contextHolder.set(dataSourceType);
    }

    /**
     * 获取数据源类型
     * @return
     */
    public static String getDataSourceType() {
        return contextHolder.get();
    }

    /**
     * 清楚数据源类型
     */
    public static void clearDataSourceType() {
        contextHolder.remove();
    }


}
