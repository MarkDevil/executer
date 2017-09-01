package com.mark.test.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MingfengMa .
 * Data   : 2017/5/13
 * Author : mark
 * Desc   :
 */

public class CommUtils {

    static Logger logger = LoggerFactory.getLogger(CommUtils.class);

    /**
     * @Author MingfengMa
     * @Date 2016/4/22
     * @param
     * @Description  获取放款单号
     *
     */
    public static String getLoanNo(){

        String nowdate = CommUtils.getNowDate(1);
        String loanNo = "loanmmf" + nowdate + Math.round(Math.random()*1000000);
        logger.info("random number :" + loanNo);
        return loanNo;
    }

    /**
     * @Author MingfengMa
     * @Date 2016/4/22
     * @param
     * @Description 获取订单号
     *
     */
    public static String getOrderNo(){
        String nowdate = CommUtils.getNowDate(1);
        return "ordermmf" + nowdate + Math.round(Math.random()*1000000);
    }

    /**
     * 获取指定类型数据
     * @param type
     * @return
     */
    public static String getRandomNo(String type){
        String nowdate = CommUtils.getNowDate(1);
        return type + "mmf"+ nowdate + Math.round(Math.random()*1000000);
    }

    /**
     * @Author MingfengMa
     * @Date 2016/4/22
     * @param type 1:返回当前时间
     *            2：返回当前时间戳
     * @Description 获取当前时间
     *
     */
    public static String getNowDate(int type){
        Date date = new Date();
        if (type == 1) {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");
            return df.format(date);
        }else if(type == 2){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            return sdf.format(date);
        } else if (type == 3) {
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return df.format(date);
        }else if (type == 4){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.format(date);
        }else if (type == 5){
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            return df.format(date);
        } else {
            return null;
        }
    }
}
