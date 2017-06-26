package com.mark.test.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MingfengMa .
 * Data   : 2017/6/26
 * Author : mark
 * Desc   :
 */

public class FileUtils {

    static Logger logger  = LoggerFactory.getLogger(FileUtils.class);


    /***
     * 从sql文件中获取sql语句
     * @param sqlFile
     * @return
     */
    public static List<String> ReadSqlFile(String sqlFile) {
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
            for (String aSqlArr : sqlArr) {
                aSqlArr = aSqlArr.trim();
                if (aSqlArr.startsWith("/") || aSqlArr.startsWith("-") || aSqlArr.equals("")){
                    continue;
                }
                logger.info("\nSql : {} \n",aSqlArr);
                sqlList.add(aSqlArr+ ";");
            }
            logger.info("Get sqls : {}",sqlList.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlList;
    }

    public static void main(String[] args) {
        List<String> sqls = FileUtils.ReadSqlFile("/Users/mark/hbFinancial/testSql");
        if (!sqls.equals("")){

        }
    }
}
