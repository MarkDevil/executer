package com.mark.test.framework.core.service;

/**
 * Created by mark .
 * Data   : 2018/2/9
 *
 * @Author : mark
 * Desc   :
 */
public interface IConfigureService {
    Object[] getProperties(String filePath,String fileName);
    void setPropertyValue(String filePath,String fileName,String key,String value);
}
