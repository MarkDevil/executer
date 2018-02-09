package com.mark.test.framework.core.service.impl;

import com.mark.test.framework.core.service.IConfigureService;
import com.mark.test.framework.util.loaddata.ProptiesReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by mark .
 * Data   : 2018/2/9
 * @Author : mark
  :
 */
@Service
public class ConfigureServiceImpl implements IConfigureService{

    Logger logger = LoggerFactory.getLogger(ConfigureServiceImpl.class);



    @Override
    public Object[] getProperties(String filePath, String fileName) {
        ProptiesReader proptiesReader = new ProptiesReader(filePath,fileName);
        return proptiesReader.getProperties();
    }

    @Override
    public void setPropertyValue(String filePath,String fileName,String key, String value) {
        ProptiesReader proptiesReader = new ProptiesReader(filePath,fileName);
        proptiesReader.loadData().setProperValue(key,value);
    }
}
