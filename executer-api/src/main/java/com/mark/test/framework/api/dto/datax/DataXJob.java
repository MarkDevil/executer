package com.mark.test.framework.api.dto.datax;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mark .
 * Data   : 2017/8/1
 * Author : mark
 * Desc   :
 */

public class DataXJob {
    private List<DataXRW> content;
    private DataXSetting setting;


    public List<DataXRW> getContent() {
        return content;
    }

    public void setContent(List<DataXRW> content) {
        this.content = content;
    }

    public DataXSetting getSetting() {
        return setting;
    }

    public void setSetting(DataXSetting setting) {
        this.setting = setting;
    }

    public static void main(String[] args) throws IOException {
        //设置setting
        Map<String,Object> channl = new HashMap<String, Object>();
        channl.put("channel","10");
        DataXSetting dataXSetting = new DataXSetting();
        dataXSetting.setSpeed(channl);

        //parameter
        List<String> colW = new ArrayList<String>();
        colW.add("*");
        List<String> jdbcurlW = new ArrayList<String>();
        jdbcurlW.add("jdbc:mysql://localhost:3307/test56?useUnicode=true&characterEncoding=utf8");
        List<String> tableW = new ArrayList<String>();
        tableW.add("account_detail_copy");
        //connection
        List<DataXconnection> conn = new ArrayList<DataXconnection>();
        DataXconnection dataXconnection = new DataXconnection();
        dataXconnection.setJdbcUrl(jdbcurlW);
        dataXconnection.setTable(tableW);
        conn.add(dataXconnection);

        DataXParameterW dataXParameterW = new DataXParameterW();
        dataXParameterW.setUsername("root");
        dataXParameterW.setPassword("root");
        dataXParameterW.setColumn(colW);
        dataXParameterW.setConnection(conn);
        dataXParameterW.setWriteMode("replace");

        //writer
        DataXWriter dataXWriter = new DataXWriter();
        dataXWriter.setName("mysqlwriter");
        dataXWriter.setParameter(dataXParameterW);

        //reader parameter
        List<String> colR = new ArrayList<String>();
        colR.add("*");
        List<String> jdbcUrlR = new ArrayList<String>();
        jdbcUrlR.add("jdbc:mysql://10.150.20.87:3306/hb?useUnicode=true&characterEncoding=utf8");
        List<String> tableR = new ArrayList<String>();
        tableR.add("account_detail_copy");
        List<DataXconnection> conn1 = new ArrayList<DataXconnection>();
        DataXconnection dataXconnection1 = new DataXconnection();
        dataXconnection1.setTable(tableR);
        dataXconnection1.setJdbcUrl(jdbcUrlR);
        conn1.add(dataXconnection1);

        DataXParameterR dataXParameterR = new DataXParameterR();
        dataXParameterR.setConnection(conn1);
        dataXParameterR.setColumn(colR);
        dataXParameterR.setUsername("root");
        dataXParameterR.setPassword("root");

        //reader
        DataXReader dataXReader = new DataXReader();
        dataXReader.setName("mysqlreader");
        dataXReader.setParameter(dataXParameterR);

        DataXRW dataXRW = new DataXRW();
        dataXRW.setReader(dataXReader);
        dataXRW.setWriter(dataXWriter);

        List<DataXRW> dataXRWList = new ArrayList<DataXRW>();
        dataXRWList.add(dataXRW);

        DataXJob dataXJob = new DataXJob();
        dataXJob.setContent(dataXRWList);
        dataXJob.setSetting(dataXSetting);
        Map<String,Object> jobobj = new HashMap<String, Object>();
        jobobj.put("job",dataXJob);
        String ret = JSON.toJSONString(jobobj);
        System.out.println(ret);

        File file = new File("./test.json");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        Writer writer = new OutputStreamWriter(fileOutputStream,"utf-8");
        writer.write(ret);
        writer.flush();
        writer.close();
    }
}
