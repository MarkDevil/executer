package com.mark.test.framework.utils;

import com.google.common.collect.Maps;
import com.squareup.okhttp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Mark .
 * Data : 2016/11/28
 * Desc :
 */
public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private static final MediaType JSONTYPE = MediaType.parse("application/json; charset=utf-8");

    /**
     * post请求body为JSON
     * @param url
     * @param param
     * @return
     */
    public static String postJson(String url, String param){
        logger.info(String.format("HTTP POST request to %s with parameter %s", url, param));
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = RequestBody.create(JSONTYPE, param);
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent","OkHttp Headers.java")
                .post(body)
                .build();
        try {
            long startTime = System.currentTimeMillis();
            Response response = okHttpClient.newCall(request).execute();
            ResponseBody res = response.body();
            if (res != null){
                long escapseTime = System.currentTimeMillis() - startTime;
                logger.info(String.format("[Escapse Time ] : %s ms", escapseTime));
                logger.info("Response msg is ",res);
                String resContent = res.string();
                System.out.println(resContent);
                return resContent;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * GET请求
     * @param url
     * @return
     */
    public static String get(String url) throws IOException {

        if (url == null){
            try {
                throw new Exception("Input parameter is incorrect");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        OkHttpClient okHttpClient = new OkHttpClient();
        assert url != null;
        Request request = new Request.Builder().url(url).get().build();
        try {
            long startTime = System.currentTimeMillis();
            Response response = okHttpClient.newCall(request).execute();
            long escapseTime = System.currentTimeMillis() - startTime;
            logger.info(String.format(
                        "\n  [Escapse Time ] : %s ms \n  [Return message ] : %s" ,
                        escapseTime,response.toString()));
            return response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 提交表单
     * @param url
     * @param params
     * @return
     */
    public static String postForm(String url, Map<String,String> params){

        OkHttpClient okHttpClient = new OkHttpClient();
        FormEncodingBuilder requestBody = new FormEncodingBuilder();
        for (String key : params.keySet()){
            String value = params.get(key);
            requestBody.add(key,value);
        }
        logger.info("Print requestBody: {}",requestBody);
        RequestBody formbody = requestBody.build();
        assert url != null;
        Request request = new Request.Builder()
                .url(url)
                .post(formbody)
                .build();
        logger.info("请求参数："+ request.toString());
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()){
                return response.body().string();
            }else {
                throw new IOException("request failed" + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Map<String,String> req = Maps.newLinkedHashMap();
        req.put("11","22");
        postForm("http://www.baidu.com",req);
    }
}