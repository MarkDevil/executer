package com.mark.test.framework.core.service.impl;

import com.mark.test.framework.util.ConfigurationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

/**
 * Created by mark .
 * Data   : 2018/2/9
 * @Author : mark
  :
 */
@Service
public class ConfigureServiceImpl extends AbstractConfigureServiceImpl {

    Logger logger = LoggerFactory.getLogger(ConfigureServiceImpl.class);
    ConfigurationUtils configurationUtils = new ConfigurationUtils();



    public void setProperty(File file, Map<String,Object> kv){
        configurationUtils.initConfig(file).setKv(kv);
    }

    public String getProperty(String key){
        return configurationUtils.getKv(key);
    }


}
