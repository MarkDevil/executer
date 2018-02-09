package com.mark.test.framework.util.loaddata;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by MingfengMa .
 * Data   : 2017/4/13
 * Author : mark
 * Desc   :
 */

public class XmlReader implements IReader {

    Logger logger = LoggerFactory.getLogger(XmlReader.class);


    @Override
    public <T> T loadData(String filename, Class claz) {
        if (filename == null || claz == null){
            throw new IllegalArgumentException("输入参数有误");
        }

        return null;
    }

    @Override
    public <T> T loadData(String filename) {
        return null;
    }
}
