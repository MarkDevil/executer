package com.mark.test.framework.core.task;

import com.mark.test.framework.core.constat.DbFactoryC;
import com.mark.test.framework.core.service.impl.DbCompareImpl;
import com.mark.test.framework.util.DbFactory;
import com.mark.test.framework.util.loaddata.FileUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

/**
 * Created by mark .
 * Data   : 2017/7/12
 * Author : mark
 * Desc   :
 */

public class SynDataBaseTask implements Job {


    private DbCompareImpl dbCompare;
    private Logger logger = LoggerFactory.getLogger(SynDataBaseTask.class);
    private final String filepath = "/Users/mark/tool/shell";
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

    @Scheduled(fixedRate = 10000)
    private void sysDataBase(){
        logger.info("执行同步定时任务task .....");
    }

    public void run(){
        SynDataBaseTask.this.synDatBase();
    }



}
