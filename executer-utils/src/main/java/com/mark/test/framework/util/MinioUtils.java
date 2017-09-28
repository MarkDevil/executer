package com.mark.test.framework.util;


import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by mark .
 * Data   : 2017/9/27
 * Author : mark
 * Desc   :
 */
public class MinioUtils {
    private static Logger logger = LoggerFactory.getLogger(MinioUtils.class);
    private static String endpoint = "http://127.0.0.1:9000";
    private static String accessKey = "F10C4ID3EWO81SESR52H";
    private static String secretKey = "6za8Z1PTT21NfB+912KuRIpP1Fss+zHu7FOPzFLL";
    private static MinioClient minioClient;

    static {
        try {
            minioClient = new MinioClient(endpoint,accessKey,secretKey);
        } catch (InvalidEndpointException e) {
            e.printStackTrace();
        } catch (InvalidPortException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传操作
     * @param bucket
     * @param fileobj
     */
    private static void fileUpload(String bucket, File fileobj){
        if (StringUtils.isEmpty(bucket)){
            throw new RuntimeException("Bucket is null");
        }
        try {
            if (!minioClient.bucketExists(bucket)){
                minioClient.makeBucket(bucket);
            }else {
                minioClient.putObject(bucket,fileobj.getName(),fileobj.getAbsolutePath());
            }

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoResponseException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (RegionConflictException e) {
            e.printStackTrace();
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取bucket下的所有文件名
     * @param bucketname
     */
    public static void getFileListUnderBucket(String bucketname){
        try {
            boolean flag = minioClient.bucketExists(bucketname);
            if (!flag){
                logger.info("error");
            }
            Iterable<Result<Item>> resultIterable = minioClient.listObjects(bucketname);
            logger.info("result : {}",resultIterable.iterator().toString());

            for (Result<Item> itemResult :resultIterable ) {
                Item item = itemResult.get();
                logger.info(item.objectName());
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoResponseException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        String bucketname = "mark-test";
        File file = new File("/Users/Shared/gitWorkspace/executer/test.json");
//        MinioUtils.fileUpload(bucketname,file);
        MinioUtils.getFileListUnderBucket(bucketname);
    }
}
