package com.rl.ecps.model;

public class EbConfigFtp {
    private Long serialNo;

    private Short purpose;

    private String ftpHost;

    private Integer ftpPort;

    private String ftpUsername;

    private String ftpPassword;

    private String baseDir;

    private Short autoStart;

    public Long getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Long serialNo) {
        this.serialNo = serialNo;
    }

    public Short getPurpose() {
        return purpose;
    }

    public void setPurpose(Short purpose) {
        this.purpose = purpose;
    }

    public String getFtpHost() {
        return ftpHost;
    }

    public void setFtpHost(String ftpHost) {
        this.ftpHost = ftpHost;
    }

    public Integer getFtpPort() {
        return ftpPort;
    }

    public void setFtpPort(Integer ftpPort) {
        this.ftpPort = ftpPort;
    }

    public String getFtpUsername() {
        return ftpUsername;
    }

    public void setFtpUsername(String ftpUsername) {
        this.ftpUsername = ftpUsername;
    }

    public String getFtpPassword() {
        return ftpPassword;
    }

    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword;
    }

    public String getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    public Short getAutoStart() {
        return autoStart;
    }

    public void setAutoStart(Short autoStart) {
        this.autoStart = autoStart;
    }
}