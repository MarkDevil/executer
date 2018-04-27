package com.mark.test.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.nio.file.*;
import java.util.Properties;

/**
 * Created by mark .
 * Data   : 2018/4/27
 */

public class FileMonitorUtils {
    private static Logger logger = LoggerFactory.getLogger(FileMonitorUtils.class);
    private static String filename = "test.properties";
    private static ClassPathResource classPathResource = new ClassPathResource(filename);
    private static Properties properties;
    private static WatchService watchService;


    static {
        try {
            watchService = FileSystems.getDefault().newWatchService();
            Paths.get(classPathResource.getFile().getParent()).register(watchService,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE);
            properties = PropertiesLoaderUtils.loadProperties(classPathResource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread watchThread = new Thread(()-> {
            while (true){
                try {
                    WatchKey watchKey = watchService.take();
                    for (WatchEvent watchEvent:watchKey.pollEvents()){
                        if (watchEvent.context().toString().equalsIgnoreCase(filename)){
                            logger.info("触发重新加载事件");
                            properties = PropertiesLoaderUtils.loadProperties(classPathResource);
                            logger.info(properties.toString());
                            break;
                        }
                    }
                    boolean flag = watchKey.reset();
                    if (!flag){
                        break;
                    }
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }

            }
        });
        watchThread.setDaemon(false);
        watchThread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                watchService.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }


    public static void main(String[] args) {
        new FileMonitorUtils();
    }

}
