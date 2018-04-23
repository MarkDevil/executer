package com.mark.test.framework.core.service.impl;

import com.google.common.collect.Lists;
import com.mark.test.framework.api.dto.DbCompareRequestDto;
import com.mark.test.framework.api.dto.SQLConnectionDTO;
import com.mark.test.framework.core.dto.DbCompareResponseDto;
import com.mark.test.framework.core.service.IDbCompare;
import com.mark.test.framework.util.DbFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by mark .
 * Data   : 2017/11/29
 * Desc   :
 * @author mark
 */
@Service
public class DbCompareImpl implements IDbCompare{

    private static Logger logger = LoggerFactory.getLogger(DbCompareImpl.class);
    private DbCompareResponseDto sourceResponse = new DbCompareResponseDto();
    private DbCompareResponseDto targetResponse = new DbCompareResponseDto();
    private List<DbCompareResponseDto> retlist = Lists.newArrayList();

    @Override
    public List<DbCompareResponseDto> compareDb(DbCompareRequestDto dbCompareRequestDto) {
        List<String> targetmissTables = Lists.newLinkedList();
        List<String> sourcemissTables = Lists.newLinkedList();

        assert dbCompareRequestDto != null;
        List<Map<String,Object>> sourceList = this.queryTables(dbCompareRequestDto,"source");
        List<Map<String,Object>> targetList = this.queryTables(dbCompareRequestDto,"target");
        if (sourceList.size() != targetList.size()){
            logger.info("源数据库与目标数据库数据表长度不相同");
        }
        List<String> sourceTableList = this.parseMapToList(sourceList);
        List<String> targetTableList = this.parseMapToList(targetList);

        logger.info("查询到source表为: {}",sourceTableList.size());
        logger.info("查询到target表为: {}",targetTableList.size());

        for (String sourcetable:sourceTableList){
            if (!targetTableList.contains(sourcetable)){
                targetmissTables.add(sourcetable);
            }
        }
        for (String targetTable : targetTableList){
            if (!sourceTableList.contains(targetTable)){
                sourcemissTables.add(targetTable);
            }
        }
        String sourceIp = dbCompareRequestDto.getSourceDbIp();
        String targetIp = dbCompareRequestDto.getTargetDbIp();
        logger.info("\n【目标数据库】"+ targetIp +"缺少相应的数据库表：{}",targetmissTables);
        logger.info("\n【源数据库】"+ sourceIp + "缺少相应的数据库表：{}",sourcemissTables);
        sourceResponse.setIp(sourceIp);
        sourceResponse.setTables(sourcemissTables);
        targetResponse.setIp(targetIp);
        targetResponse.setTables(targetmissTables);
        retlist.add(sourceResponse);
        retlist.add(targetResponse);
        return retlist;
    }

    /**
     * 根据请求
     * @param dbCompareRequestDto
     * @return
     */
    private List<Map<String, Object>> queryTables(DbCompareRequestDto dbCompareRequestDto, String type){
        List<Map<String,Object>> retlist;
        SQLConnectionDTO sourceDbCfg = new SQLConnectionDTO();
        sourceDbCfg.setUrl("jdbc:mysql://"+ dbCompareRequestDto.getSourceDbIp() +":"+
                dbCompareRequestDto.getSourceDbPort() + "/"+ dbCompareRequestDto.getSourceDbName());
        sourceDbCfg.setUserName(dbCompareRequestDto.getSourceDbUser());
        sourceDbCfg.setPassword(dbCompareRequestDto.getSourceDbPasswd());
        DbFactory sourceDb = new DbFactory(sourceDbCfg);

        SQLConnectionDTO targetDbCfg = new SQLConnectionDTO();
        targetDbCfg.setUrl("jdbc:mysql://"+ dbCompareRequestDto.getTargetDbIp() +":"+
                dbCompareRequestDto.getTargetDbPort() + "/"+ dbCompareRequestDto.getTargetDbName());
        targetDbCfg.setUserName(dbCompareRequestDto.getTargetDbUser());
        targetDbCfg.setPassword(dbCompareRequestDto.getTargetDbPasswd());
        DbFactory targetDb = new DbFactory(targetDbCfg);

        String queryTable = String.format("" +
                "select table_name " +
                "from information_schema.tables " +
                "where table_schema='%s' and table_type='base table'",dbCompareRequestDto.getSourceDbName());

        if ("source".equalsIgnoreCase(type)){
            retlist = sourceDb.queryForList(queryTable);
        }else {
            retlist = targetDb.queryForList(queryTable);
        }
        try {
            sourceDb.getConnection().close();
            targetDb.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retlist;
    }

    /**
     * 取map中的值
     * @param inList
     * @return
     */
    private List<String> parseMapToList(List<Map<String, Object>> inList){
        List<String> retlist = Lists.newArrayList();
        for (Map<String,Object> sourceMap :inList){
            Object[] sourceTables = sourceMap.values().toArray();
            if (sourceTables.length == 1){
                String sourceTable = sourceTables[0].toString();
                retlist.add(sourceTable);
            }
        }
        return retlist;
    }

}
