package models;

import lombok.Getter;
import utils.PropertiesManager;

import java.text.SimpleDateFormat;

import static services.dataBaseUnionReporting.DataBaseUnionReportingValues.DATA_FORMAT_FOR_TEST_RESULT;

@Getter
public class SessionModel {

    private final String DATA_FORMAT = PropertiesManager.getConfigValue(DATA_FORMAT_FOR_TEST_RESULT.getValue());

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
