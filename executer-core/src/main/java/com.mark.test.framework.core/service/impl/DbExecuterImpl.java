package com.mark.test.framework.core.service.impl;

import com.mark.test.framework.api.dto.SQLConnectionDTO;
import com.mark.test.framework.core.service.IDbExecuter;
import com.mark.test.framework.utils.DbFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by mark .
 * Data   : 2017/7/27
 * Author : mark
 * Desc   :
 */
@Service
public class DbExecuterImpl implements IDbExecuter{
    private Logger logger = LoggerFactory.getLogger(DbExecuterImpl.class);

    private DbFactory mySQLDb;

    @Override
    public boolean runsql(Map connectionDTO, String sql) {
        try {
            mySQLDb = new DbFactory(this.buildDbDto(connectionDTO));
            mySQLDb.execute(sql);
        }catch (Exception ex){
            logger.info("执行sql: {} 失败. {} ", sql,ex);
            return false;
        }
        return true;
    }

    @Override
    public List<Map<String, Object>> query(Map connectionDTO, String sql) {
        List<Map<String,Object>> retlist;
        try {
            mySQLDb = new DbFactory(this.buildDbDto(connectionDTO));
            retlist = mySQLDb.queryForList(sql);
        }catch (Exception ex){
            logger.info("执行sql: {} 失败. {} ", sql,ex);
            return null;
        }
        return retlist;
    }

    private SQLConnectionDTO buildDbDto(Map reqPara){
        SQLConnectionDTO sqlConnectionDTO = new SQLConnectionDTO();
        sqlConnectionDTO.setDriver("com.mysql.jdbc.Driver");
        sqlConnectionDTO.setDatabaseType((String) reqPara.get("dbType1"));
        sqlConnectionDTO.setUserName((String) reqPara.get("username1"));
        sqlConnectionDTO.setPassword((String) reqPara.get("passwd1"));
        sqlConnectionDTO.setUrl("jdbc:mysql://" +
                reqPara.get("ip1") + ":"+
                reqPara.get("port1")+ "/"+
                reqPara.get("dbname1")+"?characterEncoding=utf8&useSSL=false");
        return sqlConnectionDTO;
    }
}
