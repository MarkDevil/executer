package com.mark.test.framework.web.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.util.Properties;

/**
 * Created by mark .
 * Data   : 2018/4/27
 */
@Service
public class FileMonitorUtils {
    private static Logger logger = LoggerFactory.getLogger(FileMonitorUtils.class);
    private static String filename = "db.properties";
    private static String filepath = "/Users/Shared/gitWorkspace/executer/executer-core/src/main/resources/";
//    private static ClassPathResource classPathResource = new ClassPathResource(filename);
    private static Properties properties = new Properties();
    private static WatchService watchService;


    static {
        try {
            watchService = FileSystems.getDefault().newWatchService();
            Paths.get(filepath).register(watchService,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE);
            properties.load(new InputStreamReader(new FileInputStream(filepath +filename)));
            logger.info(properties.getProperty("password_test").toString());
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
//                            properties = PropertiesLoaderUtils.loadProperties(classPathResource);
                            properties.load(new InputStreamReader(new FileInputStream(filepath + filename)));
                            logger.info(properties.getProperty("password_test").toString());
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
