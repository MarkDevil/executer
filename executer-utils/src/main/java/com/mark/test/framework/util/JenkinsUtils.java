package com.mark.test.framework.util;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.JenkinsTriggerHelper;
import com.offbytwo.jenkins.model.BuildResult;
import com.offbytwo.jenkins.model.BuildWithDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by mark .
 * Data   : 2018/4/24
 */

public class JenkinsUtils {
    private static Logger logger = LoggerFactory.getLogger(JenkinsUtils.class);
    private static JenkinsServer jenkinsServer;
    private static JenkinsTriggerHelper jenkinsTriggerHelper;

    static {
        try {
            jenkinsServer  = new JenkinsServer(new URI("http://192.168.18.85:8888/"),"mmf007","123456");
            jenkinsTriggerHelper = new JenkinsTriggerHelper(jenkinsServer);
        } catch (URISyntaxException e) {
            logger.error("创建jenkins客户端失败");
            e.printStackTrace();
        }
    }

    /**
     * 创建Jenkins Job
     * @param jobname
     * @return
     */
    public static boolean createJob(String jobname){
        assert jobname!=null;
        return false;
    }

    /**
     * 触发Jenkins Job 进行构建
     * @param jobname
     * @return
     */
    public static boolean triggerJob(String jobname){
        assert jobname!=null;
        BuildWithDetails buildWithDetails;
        BuildResult buildResult;
        try {
            buildWithDetails = jenkinsTriggerHelper.triggerJobAndWaitUntilFinished(jobname);
            buildResult = buildWithDetails.getResult();
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
