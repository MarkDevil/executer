package com.mark.test.framework.util;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * automation
 * mingfeng.ma
 * bitmain
 * 2018/7/13
 */
public class SshUtils {
    private static Logger logger = LoggerFactory.getLogger(SshUtils.class);
    private static JSch jSch;
    private static Session session;
    private static String rsaPrivateKey = "/Users/bitmain/.ssh/id_rsa";

    private SshUtils() {
        jSch = new JSch();
        try {
            session = jSch.getSession("mingfeng.ma", "10.39.1.2");
            session.setPassword("Crystal@08102");
            session.setConfig("StrictHostKeyChecking","no");
            session.connect(5000);
            if (session.isConnected()){
                logger.info("会话创建成功");
            }
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    private static class SshFactory {
        private static SshUtils sshUtils = new SshUtils();
    }

    public static SshUtils getInstance() {
        return SshFactory.sshUtils;
    }

    public String executeShell() {
        try {
            ChannelShell channel = (ChannelShell) session.openChannel("shell");
            channel.connect(5000);
            InputStream inputStream = channel.getInputStream();
            OutputStream outputStream = channel.getOutputStream();
            String cmd1 = "1\r\n";
            outputStream.write(cmd1.getBytes());
            String cmd2 = "1\r\n";
            outputStream.write(cmd2.getBytes());
            String cmd3 = "1\r\n";
            outputStream.write(cmd3.getBytes());
            String cmd4 = "ls\r\n";
            outputStream.write(cmd4.getBytes());
            outputStream.write("exit\r\n".getBytes());
            outputStream.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String msg = null;
            while((msg = in.readLine())!=null){
                logger.info("返回信息为：{}",msg);
            }
            in.close();
        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }finally {
            session.disconnect();
        }
        return "";
    }



    //exec command 结果返回对象
    public static class ResInfo {
        private int exitStuts;//返回状态码 （在linux中可以通过 echo $? 可知每步执行令执行的状态码）
        private StringBuffer outRes;//标准正确输出流内容
        private StringBuffer errRes;//标准错误输出流内容

        public int getExitStuts() {
            return exitStuts;
        }

        public void setExitStuts(int exitStuts) {
            this.exitStuts = exitStuts;
        }

        public StringBuffer getOutRes() {
            return outRes;
        }

        public void setOutRes(StringBuffer outRes) {
            this.outRes = outRes;
        }

        public StringBuffer getErrRes() {
            return errRes;
        }

        public void setErrRes(StringBuffer errRes) {
            this.errRes = errRes;
        }
    }

    public static void main(String[] args) {
        logger.info(String.valueOf(SshUtils.getInstance().equals(SshUtils.getInstance())));
        SshUtils.getInstance().executeShell();

    }

}
