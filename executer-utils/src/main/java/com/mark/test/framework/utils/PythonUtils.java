package com.mark.test.framework.utils;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by mark .
 * Data   : 2017/8/1
 * Author : mark
 * Desc   :
 */

public class PythonUtils {

    private static Logger logger = LoggerFactory.getLogger(PythonUtils.class);

    /**
     * 执行cmd shell命令
     * @param cmd
     */
    private static void runPy(String cmd){
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            assert process != null;

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine())!= null){
                logger.info(line);
            }
            bufferedReader.close();
            process.waitFor();

        } catch (IOException e) {
            logger.error("执行python命令失败,请检查");
            e.printStackTrace();
        } catch (InterruptedException e) {
            logger.error("执行python命令失败,请检查");
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
//        PythonUtils.runPy("python /Users/mark/tool/datax/bin/datax.py /Users/Shared/gitWorkspace/executer/test.json");
        logger.info(PythonUtils.class.getResource("/shell/syscChangeList.sh").getPath());
        PythonUtils.runPy("sh " + PythonUtils.class.getResource("/shell/syscChangeList.sh").getPath());
    }
}
