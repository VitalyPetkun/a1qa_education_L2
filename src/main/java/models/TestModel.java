package models;

import utils.PropertiesManager;
import java.text.SimpleDateFormat;

public class TestModel {

    private final String DATA_FORMAT = PropertiesManager.getConfigValue("dataFormatForTestTime");

    private String name;
    private String statusId;
    private String projectId;
    private String sessionId;
    private String startTime;
    private String endTime;
    private String env;
    private String browser;
    private String authorId;

    public TestModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = String.valueOf(statusId);
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = String.valueOf(projectId);
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = String.valueOf(sessionId);
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = new SimpleDateFormat(DATA_FORMAT).format(startTime);
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = new SimpleDateFormat(DATA_FORMAT).format(endTime);
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = String.valueOf(authorId);
    }
}
