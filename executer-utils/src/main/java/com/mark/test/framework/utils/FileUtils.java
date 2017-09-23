package com.mark.test.framework.utils;


import com.google.common.collect.Lists;
import com.mark.test.framework.api.dto.SQLConnectionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MingfengMa .
 * Data   : 2017/6/26
 * Author : mark
 * Desc   :
 */

public class FileUtils {

    public final static Logger logger  = LoggerFactory.getLogger(FileUtils.class);
    public static List<String> arraylist = Lists.newLinkedList();
    public static List<String> directoryList = Lists.newLinkedList();

    /**
     * 获取指定目录下所有文件
     * @param path
     * @return
     */
    public static List<String> getFileList(String path){
        File file = new File(path);
        if (file.exists()){
            File[] files = file.listFiles();
            assert files != null;
            for (File file1:files) {
                if (file1.isDirectory()){
                    logger.debug("Found directory : {}",file1.getName());
                    directoryList.add(file1.getName());
                    getFileList(file1.getAbsolutePath());
                }else {
                    if (file1.getName().endsWith(".sql")){
                        logger.info("Found file :{}",file1.getName());
                        arraylist.add(file1.getAbsolutePath());
                    }
                }
            }
        }else {
            throw new RuntimeException("Directory is not exited");
        }
//        logger.info("获取到的文件列表是:{}",directoryList);
        return arraylist;
    }


    /***
     * 从sql文件中获取sql语句
     * @param sqlFile
     * @return
     */
    public static List<String> readSqlFile(String sqlFile) {
        FileReader in = null;
        List<String> sqlList = new ArrayList<String>();
        char[] buff = new char[2048];
        String[] sqlArr;
        StringBuilder str = new StringBuilder();
        int count;
        try {
            in = new FileReader(sqlFile);
            while ((count = in.read(buff)) != -1) {
                str.append(new String(buff, 0, count));
            }
            sqlArr = str.toString().split(";");
            logger.info("\n 分割后的数据: \n {}", Arrays.toString(sqlArr));
            for (String sql : sqlArr) {
                sql = sql.trim();
                logger.info("\n 数组中的数据: \n {}",sql);
                if (sql.equals("")){
                    continue;
                }
                sqlList.add(sql);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert in != null;
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sqlList;
    }

    public static void main(String[] args) {
        String hbadmin_changelist = "/Users/mark/hbFinancial/hbadmin-change-list";
        String testPath = "/Users/mark/tool/shell";
        SQLConnectionDTO sqlConnectionDTO = new SQLConnectionDTO();
        sqlConnectionDTO.setUrl("jdbc:mysql://localhost:3307/hb?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull");
        sqlConnectionDTO.setDriver("com.mysql.jdbc.Driver");
        sqlConnectionDTO.setUserName("root");
        sqlConnectionDTO.setPassword("root");
        DbFactory mySQLDb = new DbFactory(sqlConnectionDTO);
        List<String> retlist = FileUtils.getFileList(testPath);
        logger.info("\nFound file list: \n {} \n", retlist.toString());
        mySQLDb.executeSqlFile(retlist);
//        for (String filepath: retlist) {
//            List<String> sqls = FileUtils.readSqlFile(filepath);
//            logger.debug("\n [文件目录为]：\n {} , \n [返回的SQL]: \n {}",filepath,sqls.toString());
//            for (String sql:sqls){
//                logger.info("\n [返回sql]: {}",sql);
//            }
//        }


    }
}
