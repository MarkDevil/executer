package com.mark.test.framework.core.task;

import com.mark.test.framework.core.constat.DbFactoryC;
import com.mark.test.framework.utils.DbFactory;
import com.mark.test.framework.utils.FileUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by MingfengMa .
 * Data   : 2017/7/12
 * Author : mark
 * Desc   :
 */

public class SynDataBaseTask implements Job {

    Logger logger = LoggerFactory.getLogger(SynDataBaseTask.class);
    final String filepath = "/Users/mark/tool/shell";
    private DbFactory dbins = new DbFactoryC().buildDbInstance("local");

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        this.synDatBase();
    }

    /**
     * 执行指定的sql语句
     */
    private void synDatBase(){
        List<String> retlist = FileUtils.getFileList(filepath);
        logger.info("Found file list: {}", retlist.toString());
        for (String filepath: retlist) {
            logger.info("Found file path is {}",filepath);
            List<String> sqls = FileUtils.readSqlFile(filepath);
            for (String sql:sqls){
                logger.info("\n [返回sql]: {}",sql);
                dbins.queryForList(sql);
            }

        }
    }

    public void run(){
        SynDataBaseTask.this.synDatBase();
    }



}
