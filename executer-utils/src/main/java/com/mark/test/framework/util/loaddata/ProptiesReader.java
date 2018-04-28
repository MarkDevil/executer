package com.mark.test.framework.util.loaddata;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by MingfengMa .
 * Data   : 2017/6/20
 * Author : mark
 * Desc   :
 */

public class ProptiesReader{

    private Logger logger = LoggerFactory.getLogger(ProptiesReader.class);
    private String properPath;
    private String filename;
    private Properties properties = new Properties();

    public ProptiesReader(String filepath, String filename){
        this.properPath = filepath;
        this.filename = filename;
    }

    /**
     * 加载指定配置文件
     * @return
     */
    public ProptiesReader loadData() {
        this.readPropFile();
        return this;
    }


    private Properties readPropFile(){
        if (properPath== null || filename == null){
            logger.error("读取文件路径有误");
        }
        try {
            assert filename != null;
            properties.load(new FileInputStream(new File(properPath + filename)));
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object[] getProperties(){
        Object[] objects = Objects.requireNonNull(this.readPropFile()).entrySet().toArray();
        logger.info("获取到的属性为：{}", Arrays.toString(objects));
        return objects;
    }

    /**
     * 修改文件并保存
     *
     */
    public void setProperValue(String key, String value){
        assert key!=null && value!=null;
        if (properties.containsKey(key)){
            logger.info("key : {} , value: {}",key,value);
            properties.setProperty(key,value);
            try {
                properties.store(new FileWriter(new File(properPath + filename)),"new file");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ProptiesReader proptiesReader = new ProptiesReader("/Users/mark/Desktop/","test.properties");
        proptiesReader.loadData().setProperValue("password_87","yyyyyyy");
//        proptiesReader.getProperties();


    }


}
