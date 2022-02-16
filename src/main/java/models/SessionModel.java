package models;

import utils.PropertiesManager;

import java.text.SimpleDateFormat;

public class SessionModel {

    private final String DATA_FORMAT = PropertiesManager.getConfigValue("dataFormatForTestTime");

    private String sessionKey;
    private String createdTime;
    private String buildNumber;

    public SessionModel() {
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(long sessionKey) {
        this.sessionKey = String.valueOf(sessionKey);
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = new SimpleDateFormat(DATA_FORMAT).format(createdTime);
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(int buildNumber) {
        this.buildNumber = String.valueOf(buildNumber);
    }
}
