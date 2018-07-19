package com.mark.test.framework.util;


import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * mingfeng.ma
 * 2018/6/28
 */
public class OkHttpsUtil {
    private static Logger logger = LoggerFactory.getLogger(OkHttpsUtil.class);
    private static HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
    private static Cookie loginSession;

    private OkHttpsUtil() {
    }

    public static class OkhttpsUtilFactory {
        static OkHttpsUtil instance = new OkHttpsUtil();
    }


    private OkHttpClient okHttpClient = new OkHttpClient().newBuilder().
            sslSocketFactory(createSSLSocketFactory()).
            hostnameVerifier(new TrustAllHostnameVerifier()).
            addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder().
                            addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36").
                            build();
                    return chain.proceed(request);
                }
            }).
            cookieJar(new CookieJar() {
                @Override
                public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                    for (Cookie cok : list) {
                        if (cok.name().equalsIgnoreCase("session")) {
                            loginSession = Cookie.parse(httpUrl, cok.toString());
                            assert loginSession != null;
                            logger.info("保存session的cookies：{}", loginSession.toString());
                        }
                    }
                    Set<Cookie> cookieSet = new HashSet<>(list);
                    if (!list.contains(loginSession)){
                        cookieSet.add(loginSession);
                    }
                    cookieSet.remove(null);
                    logger.info("添加后cookie:{}",cookieSet);
                    ArrayList retlist = new ArrayList(cookieSet);
                    cookieStore.put(httpUrl.host(), retlist);
                    logger.info("保存返回全部的cookie数据：{}", cookieStore.toString());
                }

                @Override
                public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                    List<Cookie> cookies = cookieStore.get(httpUrl.host());
                    logger.info("读取请求中的的cookie数据：{}", cookieStore.toString());
                    return cookies != null ? cookies : new ArrayList<>();
                }
            }).build();

    /**
     * 获取单例对象
     * @return
     */
    private static OkHttpClient getOkHttpClient() {
        return OkhttpsUtilFactory.instance.okHttpClient;
    }


    /**
     * get请求
     *
     * @param url
     * @param
     * @return
     */
    public static Response get(String url) {
        Request request = new Request.Builder().url(url).get().build();
        Response response = null;
        try {
            response = OkHttpsUtil.getOkHttpClient().newCall(request).execute();
            assert response.code() == 200;
//            logger.info("{},{},{}", response.code(),response.headers(),response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static Response postForm(String url, Headers headers, Map<String, Object> map) {
        Request request = new Request.Builder().headers(headers).url(url).get().build();
        Response response = null;
        try {
            response = OkHttpsUtil.getOkHttpClient().newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * post string
     *
     * @param url
     * @param map
     * @return
     */
    public static Response postString(String url, Map<String, Object> map) {
        FormBody.Builder formBody = new FormBody.Builder();
        for (String key : map.keySet()) {
            formBody.add(key, String.valueOf(map.get(key)));
        }
        Request request = new Request.Builder().url(url)
                .post(formBody.build()).build();
        Response response = null;
        try {
            response = OkHttpsUtil.getOkHttpClient().newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * post json请求
     *
     * @param url
     * @param headers
     * @param map
     * @return
     */
    public static Response postJson(String url, Headers headers, Map<String, Object> map) {
        Request request = new Request.Builder().headers(headers).url(url).
                post(RequestBody.create(MediaType.parse("application/json"), map.toString())).build();
        Response response = null;
        try {
            response = OkHttpsUtil.getOkHttpClient().newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * post multiPart形式
     *
     * @param url
     * @param file
     * @param params
     * @return
     */
    public static Response postMultiPart(String url, File file, Map<String, Object> params) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (params.isEmpty()) {
            throw new RuntimeException("请求参数为空，请检查");
        } else {
            builder.addFormDataPart("uploadFile", file.getName(), requestBody);
            RequestBody multipartBody = builder.build();
            Request request = new Request.Builder().url(url).
                    post(multipartBody).
                    build();
            logger.debug("post multiPart请求参数:{}", request.headers().toString());
            Response response = null;
            try {
                response = OkHttpsUtil.getOkHttpClient().newCall(request).execute();
                logger.info("response 返回数据: {}", response.code());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

    }


    private static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

    public static void main(String[] args) {
        logger.info(String.valueOf(OkHttpsUtil.getOkHttpClient().equals(OkHttpsUtil.getOkHttpClient())));
    }

}
