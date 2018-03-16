package com.mark.test.framework.util;


import com.squareup.okhttp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Created by mark .
 * Data   : 2017/9/28
 * Author : mark
 * Desc   :
 */

public class HttpUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    private static final MediaType JSONTYPE = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType XMLTYPE = MediaType.parse("text/xml; charset=utf-8");


    /**
     * post 请求body为xml
     * @param url
     * @param param
     * @return
     */
    public static String postXml(String url,String param){
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(XMLTYPE,param);
        Request request = new Request.Builder().url(url)
                .post(requestBody)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            ResponseBody responseBody = response.body();
            logger.info("返回信息为{}",responseBody.string());
            return responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

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
                logger.info(String.format("[Escapse Time ] : %s ms ，message is %s", escapseTime,res));
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
            logger.info(String.format("%n[Escapse Time ] : %s ms %n[Return message ] : %s" ,
                    escapseTime,response.toString()));
            if (response.isSuccessful()){
                return response.toString();
            }else {
                return null;
            }
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
        com.squareup.okhttp.RequestBody formbody = requestBody.build();
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
}
