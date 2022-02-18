package models;

import lombok.Getter;
import utils.PropertiesManager;
import java.text.SimpleDateFormat;

@Getter
public class SessionModel {

    private final String DATA_FORMAT = PropertiesManager.getConfigValue("dataFormatForTestTime");

    private String sessionKey;
    private String createdTime;
    private String buildNumber;

    public SessionModel() {
    }

    public void setSessionKey(long sessionKey) {
        this.sessionKey = String.valueOf(sessionKey);
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = new SimpleDateFormat(DATA_FORMAT).format(createdTime);
    }

    public void setBuildNumber(int buildNumber) {
        this.buildNumber = String.valueOf(buildNumber);
    }
}
