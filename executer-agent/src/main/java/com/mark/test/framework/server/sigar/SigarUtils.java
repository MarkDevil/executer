package com.mark.test.framework.server.sigar;

import org.hyperic.sigar.Sigar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by mark .
 * Data   : 2018/2/24
 *
 * 使用sigar的java项目，将项目.jar放入linux机器，使用java -jar 运行时会报错 java.lang.UnsatisfiedLinkError
 * 需要将so库(libsigar-amd64-linux.so,libsigar-x86-linux.so)放在linux机器某文件夹下 运行时命令：
    java  -Djava.library.path=/usr/local/sigarlib/ -jar yacebao-agent.jar
    其中用/usr/local/sigarlib/替换路径。
 */

public class SigarUtils {
    private static Logger logger = LoggerFactory.getLogger(SigarUtils.class);
    private static String sigarpath = "/Users/mark/tool/sigar/";
    public static Sigar sigar = initSigar(sigarpath);

    /**
     * 初始化Sigar配置
     * @return
     */
    private static Sigar initSigar(String sigarPath) {
        try {
            //此处只为得到依赖库文件的目录，可根据实际项目自定义
            String classPath = new File(sigarPath).getPath();
            String path = System.getProperty("java.library.path");
            logger.info(classPath);
            //为防止java.library.path重复加，此处判断了一下
            if (!path.contains(classPath)) {
                if (isOSWin()) {
                    path += ";" + classPath;
                } else {
                    path += ":" + classPath;
                    logger.info("path: {}", path);
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
