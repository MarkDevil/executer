package com.mark.test.framework.core.constat;

import com.mark.test.framework.api.dto.SQLConnectionDTO;
import com.mark.test.framework.utils.MySQLDb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by MingfengMa .
 * Data   : 2017/7/4
 * Author : mark
 * Desc   :
 */
@Configuration
public class DbFactory {

    private static Logger logger = LoggerFactory.getLogger(DbFactory.class);

    private static SQLConnectionDTO dbinstance = new SQLConnectionDTO();

    @Value("${driver_87}")
    private static String mysqldriver;

    @Value("${url_87}")
    private static String url87;

    @Value("${username_87}")
    private static String user87;

    @Value("${password_87}")
    private static String passwd87;


    /**
     * 根据指定数据生成数据库连接对象
     * @param dbServer
     * @return
     */
    public static MySQLDb buildDbInstance(String dbServer){
        switch (dbServer) {
            case "psbc":
                dbinstance.setDriver("com.mysql.jdbc.Driver");
                dbinstance.setUrl("jdbc:mysql://192.168.18.45:3306/hb?characterEncoding=utf8&useSSL=false");
                dbinstance.setUserName("root");
                dbinstance.setPassword("root");
                logger.info("创建中邮数据库连接对象成功 :{}",dbinstance.toString());
                break;
            case "xib":
                dbinstance.setDriver(mysqldriver);
                dbinstance.setUrl(url87);
                dbinstance.setUserName(user87);
                dbinstance.setPassword(passwd87);
                logger.info("创建厦门数据库连接对象成功 :{}",dbinstance.toString());
                break;
            case "dev":
                dbinstance.setDriver("com.mysql.jdbc.Driver");
                dbinstance.setUrl("jdbc:mysql://10.150.20.91:3306/hb?characterEncoding=utf8&useSSL=false");
                dbinstance.setUserName("devbj");
                dbinstance.setPassword("gb8tVSJCSw!kUPnE");
                logger.info("创建开发数据库连接对象成功 :{}",dbinstance.toString());
                break;
            default:
                logger.error("Input server parameter is incorrect: {}", dbServer);
                break;
        }

        return new MySQLDb(dbinstance);
    }


    public static void main(String[] args) {
        DbFactory.buildDbInstance("psbc");
        DbFactory.buildDbInstance("xib");
        DbFactory.buildDbInstance("");

    }
}
