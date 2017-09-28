package com.mark.test.framework.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by mark .
 * Data   : 2017/4/21
 * Author : mark
 * Desc   :
 */

public class SignUtils {

//    测试环境可使用下面的key和secret：
//    yanfa
//    0b6bd65156c9457eb0ea5ad3d5a21f9e
//    所有请求都使用 POST 方式发送

    /**
     * 签名算法
     * @param params
     * @param secret
     * @return
     * @throws Throwable
     */
    public static String sign(Map params, String secret) throws Throwable {

        //第一步：参数按名称排序
        String[] keys = (String[]) params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        //第二步：把所有参数名和参数值串在一起，在前后加secret
        StringBuilder query = new StringBuilder();
        query.append(secret);

        for (String key : keys) {
            String value = (String) params.get(key);
            if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
                query.append(key).append(value);
            }
        }

        query.append(secret);

        // 第三步：计算 MD5
        byte[] bytes;
        bytes = encryptMD5(query.toString());

        //第四步：把二进制转化为大写的十六进制
        return byte2hex(bytes);
    }

    private static byte[] encryptMD5(String data) throws Throwable {

        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(data.getBytes("utf-8"));
        return messageDigest.digest();
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    /**
     * 签名外部请求,计算密码
     * @param params
     * @param secret
     * @return
     * @throws Throwable
     */
    public static String signOuter(Map<String,Object> params, String secret) throws Throwable {
        //第一步：参数按名称排序
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        //第二步：把所有参数名和参数值串在一起，在前后加secret
        StringBuilder query = new StringBuilder();
        query.append(secret);
        for (String key : keys) {
            String value = params.get(key) == null ? null : params.get(key).toString();
            if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value) && !(params.get(key) instanceof JSONArray) && !(params.get(key) instanceof JSONObject)) {
                query.append(key).append(value);
            }
        }
        query.append(secret);
        // 第三步：计算 MD5
        byte[] bytes;
        bytes = encryptMD5(query.toString());
        //第四步：把二进制转化为大写的十六进制
        return byte2hex(bytes);
    }
}
