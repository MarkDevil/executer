package com.mark.test.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created by mark .
 * Data   : 2018/3/30
 * @author mark
 */

public class Base64Utils {

    Logger logger = LoggerFactory.getLogger(Base64Utils.class);

    /**
     * base64加密
     * @param conext
     * @return
     */
    public static String encode64(String conext){
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(conext.getBytes());
    }

    /**
     * base64解密
     * @param conext
     * @return
     */
    public static String decode64(String conext){
        BASE64Decoder base64Decoder = new BASE64Decoder();
        String ret = null;
        try {
            ret = new String(base64Decoder.decodeBuffer(conext));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }


}
