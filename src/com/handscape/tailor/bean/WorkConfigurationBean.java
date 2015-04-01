package com.handscape.tailor.bean;

/**
 * Created with IntelliJ IDEA.
 * User: 黄川
 * Date Time: 2015/1/3016:45
 */

public class WorkConfigurationBean {
    private String id;
    private String workName;//项目名称
    private String port;//监听端口
    private String workSpace;//工作目录
    private String baseUrl;//引擎地址
    private String defaultUrl;//默认地址
    private String base64Mode;//是否base64编码
    private String debugMode;//是否是调试模式

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getWorkSpace() {
        return workSpace;
    }

    public void setWorkSpace(String workSpace) {
        this.workSpace = workSpace;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public String getBase64Mode() {
        return base64Mode;
    }

    public void setBase64Mode(String base64Mode) {
        this.base64Mode = base64Mode;
    }

    public String getDebugMode() {
        return debugMode;
    }

    public void setDebugMode(String debugMode) {
        this.debugMode = debugMode;
    }
}
