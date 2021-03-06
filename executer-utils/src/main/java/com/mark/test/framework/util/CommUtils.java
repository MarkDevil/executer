package com.mark.test.framework.util;

import com.offbytwo.jenkins.JenkinsServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mark .
 * Data   : 2017/5/13
 * Author : mark
 * Desc   :
 */

public class CommUtils {

    static Logger logger = LoggerFactory.getLogger(CommUtils.class);

    /**
     * @Author mark
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
     * @Author mark
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
     * @Author mark
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


    public static <T> List<T> compare(T[] t1, T[] t2) {
        List<T> list1 = Arrays.asList(t1);
        List<T> list2 = new ArrayList<T>();
        for (T t : t2) {
            if (!list1.contains(t)) {
                list2.add(t);
            }
        }
        return list2;
    }

    /**
     * 生成UUID值
     * @return
     */
    public static String getUUId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    /**
     *
     * @param jobname
     * @return
     */
    public static String getJenkinsJob(String jobname) throws URISyntaxException, IOException {
        JenkinsServer jenkinsServer = new JenkinsServer(new URI("http://localshot:8888/"),"mmf007","123456");
        logger.info(String.valueOf(jenkinsServer.getJob(jobname).toString()));
        return "ok";
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
    }

}
