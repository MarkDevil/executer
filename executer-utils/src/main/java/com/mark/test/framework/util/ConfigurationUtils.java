package com.mark.test.framework.util;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;
import java.util.Set;

/**
 * Created by mark .
 * Data   : 2018/4/28
 * @author mark
 */

public class ConfigurationUtils {
    private static Logger logger = LoggerFactory.getLogger(ConfigurationUtils.class);
    private Configuration configuration;
    private FileBasedConfigurationBuilder<PropertiesConfiguration> fileBasedBuilderProperties;


    /**
     * 初始化配置
     * @param file
     * @return
     */
    public ConfigurationUtils initConfig(File file){
        try {
            Configurations configurations = new Configurations();
            fileBasedBuilderProperties = configurations.propertiesBuilder(file);
            configuration = fileBasedBuilderProperties.getConfiguration();
            return this;
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置配置文件的值
     * @param kv
     * @return
     */
    public void setKv(Map<String, Object> kv){
        Set<Map.Entry<String,Object>> entries = kv.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            String val = entry.getValue().toString();
            logger.info("设置的见值key：{}，value:{}",key,val);
            if (configuration.containsKey(key)){
                configuration.setProperty(key,val);
            }else {
                configuration.addProperty(key, val);
            }
            try {
                fileBasedBuilderProperties.save();
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据key获取配置信息
     * @param key
     * @return
     */
    public String getKv(String key){
        assert (key!=null);
        String ret = (String) configuration.getProperty(key);
        logger.info("返回的value数据为：{}",ret);
        return ret;
    }


    public static void main(String[] args) {
        Map<String,Object> setMap = com.google.common.collect.Maps.newHashMap();
        setMap.put("oracle","192.168.18.999");
        ConfigurationUtils configurationUtils = new ConfigurationUtils();
        configurationUtils.initConfig(new File("/Users/Shared/gitWorkspace/executer/executer-utils/src/main/resources/test.properties"));
        logger.info(configurationUtils.getKv("oracle"));
    }
}
