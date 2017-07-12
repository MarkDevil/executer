package com.mark.test.framework.core.constat;

import com.mark.test.framework.api.dto.SQLConnectionDTO;
import com.mark.test.framework.utils.MySQLDb;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by MingfengMa .
 * Data   : 2017/7/4
 * Author : mark
 * Desc   :
 */
@Component
public class DbFactory {

    private static Logger logger = LoggerFactory.getLogger(DbFactory.class);

    @Value("${driver_87}")
    private String mysqldriver;

    @Value("${url_87}")
    private String url87;

    @Value("${username_87}")
    private String user87;

    @Value("${password_87}")
    private String passwd87;

    @Autowired
    public BasicDataSource db;


    @PostConstruct
    public void init(){
        logger.info("DbFactory init database : {},{}",new String[]{mysqldriver,url87});
    }


    /**
     * 根据指定数据生成数据库连接对象
     * @param dbServer
     * @return
     */
    public MySQLDb buildDbInstance(String dbServer){

        SQLConnectionDTO dbinstance = new SQLConnectionDTO();
        switch (dbServer) {
            case "psbc":
                dbinstance.setDriver("com.mysql.jdbc.Driver");
                dbinstance.setUrl("jdbc:mysql://192.168.18.45:3306/hb?characterEncoding=utf8&useSSL=false");
                dbinstance.setUserName("root");
                dbinstance.setPassword("root");
                logger.info("创建中邮数据库信息 :{}",dbinstance.toString());
                break;
            case "xib":
                dbinstance.setDriver(mysqldriver);
                dbinstance.setUrl(url87);
                dbinstance.setUserName(user87);
                dbinstance.setPassword(passwd87);
                logger.info("创建厦门数据库信息 :{}",dbinstance.toString());
                break;
            case "dev":
                dbinstance.setDriver("com.mysql.jdbc.Driver");
                dbinstance.setUrl("jdbc:mysql://10.150.20.91:3306/hb?characterEncoding=utf8&useSSL=false");
                dbinstance.setUserName("devbj");
                dbinstance.setPassword("gb8tVSJCSw!kUPnE");
                logger.info("开发数据库信息 :{}",dbinstance.toString());
                break;
            case "local":
                dbinstance.setDriver("com.mysql.jdbc.Driver");
                dbinstance.setUrl("jdbc:mysql://localhost:3306/marktest?characterEncoding=utf8&useSSL=false");
                dbinstance.setUserName("root");
                dbinstance.setPassword("root");
                logger.info("本地测试数据库信息 :{}",dbinstance.toString());
                break;
            default:
                logger.error("Input server parameter is incorrect: {}", dbServer);
                break;
        }

        return new MySQLDb(dbinstance);
    }

}
