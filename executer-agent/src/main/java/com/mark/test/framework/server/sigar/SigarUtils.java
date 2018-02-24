package com.mark.test.framework.server.sigar;

import org.hyperic.sigar.Sigar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by mark .
 * Data   : 2018/2/24
 */

public class SigarUtils {
    private static Logger logger = LoggerFactory.getLogger(SigarUtils.class);
    public static Sigar sigar = initSigar();

    /**
     * 初始化Sigar配置
     * @return
     */
    private static Sigar initSigar() {
        try {
            //此处只为得到依赖库文件的目录，可根据实际项目自定义
            String file = String.valueOf(SigarUtils.class.getClassLoader().getResource("sigar/libsigar-amd64-linux.so"));
            logger.info(file);
            File classPath = new File(file).getParentFile();

            String path = System.getProperty("java.library.path");
            String sigarLibPath = classPath.getCanonicalPath();
            //为防止java.library.path重复加，此处判断了一下
            if (!path.contains(sigarLibPath)) {
                if (isOSWin()) {
                    path += ";" + sigarLibPath;
                } else {
                    path += ":" + sigarLibPath;
                }
                System.setProperty("java.library.path", path);
            }
            return new Sigar();
        } catch (Exception e) {
            logger.info("发生异常：{}",e);
            return null;
        }
    }

    private static boolean isOSWin(){//OS 版本判断
        String os = System.getProperty("os.name").toLowerCase();
        logger.info("系统信息为：{}",os);
        return os.contains("win");
    }

}
