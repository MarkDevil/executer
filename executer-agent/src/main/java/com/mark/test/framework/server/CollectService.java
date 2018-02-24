package com.mark.test.framework.server;

import com.mark.test.framework.server.sigar.SigarUtils;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by mark .
 * Data   : 2018/2/24
 * @author mark
 */

public class CollectService {

    private static Logger logger = LoggerFactory.getLogger(CollectService.class);
    private static Sigar sigarin = SigarUtils.sigar;

    public static void main(String[] args) throws SigarException {
        if (args.length <=0){
            System.out.println("输入参数异常");
        }
        logger.info(Arrays.toString(sigarin.getCpuInfoList()));
        logger.info(String.valueOf(sigarin.getCpu()));

    }


}
