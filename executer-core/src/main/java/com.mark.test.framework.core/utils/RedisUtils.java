package com.mark.test.framework.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by mark .
 * Data   : 2018/1/25
 * @Author : mark
 * Desc   :
 */
@Component
public class RedisUtils {
    private static Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 存入redis
     * @param key
     * @param object
     * @return
     */
    public boolean setValue(String key, Object object){
        try {
            redisTemplate.opsForValue().set(key,object);
            return true;
        }catch (Exception ex){
            logger.error("存入缓存失败{}",ex.getCause().toString());
            return false;
        }
    }

    /**
     * 根据key获取值
     * @param key
     * @return
     */
    public Object get(String key){
        return key == null? null:redisTemplate.opsForValue().get(key);
    }


    public static void main(String[] args) {
        RedisUtils redisUtils = new RedisUtils();
        redisUtils.setValue("ip","192.168.51.161");
        logger.info(redisUtils.get("ip").toString());
    }




}
