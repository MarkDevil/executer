package com.mark.test.framework.api.dto;

public class FTPConfig {

	/** 主机ip **/
    private String ftpServer;
    /** 端口号 **/
    private String ftpPort;
    /** ftp用户名 **/
    private String ftpUserName;
    /** ftp密码 **/
    private String ftpPassword;
    /** ftp中的目录 **/
    private String ftpPath;
    /** ftp中文件后缀 **/
    private String ftpFileSuffix;
    /** ftp处理后，备份路径 （为空时，不备份）**/
    private String toPath;

	public String getFtpServer() {
		return ftpServer;
	}
	public void setFtpServer(String ftpServer) {
		this.ftpServer = ftpServer;
	}
	public String getFtpPort() {
		return ftpPort;
	}
	public void setFtpPort(String ftpPort) {
		this.ftpPort = ftpPort;
	}
	public String getFtpUserName() {
		return ftpUserName;
	}
	public void setFtpUserName(String ftpUserName) {
		this.ftpUserName = ftpUserName;
	}
	public String getFtpPassword() {
		return ftpPassword;
	}
	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}
	public String getFtpPath() {
		return ftpPath;
	}
	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
	}
	public String getFtpFileSuffix() {
		return ftpFileSuffix;
	}
	public void setFtpFileSuffix(String ftpFileSuffix) {
		this.ftpFileSuffix = ftpFileSuffix;
	}
	public String getToPath() {
		return toPath;
	}
	public void setToPath(String toPath) {
		this.toPath = toPath;
	}
}
