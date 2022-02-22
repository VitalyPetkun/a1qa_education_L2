package models;

import lombok.Getter;
import utils.PropertiesManager;

import java.text.SimpleDateFormat;

import static services.dataBaseUnionReporting.DataBaseUnionReportingValues.DATA_FORMAT_FOR_TEST_RESULT;

@Getter
public class TestModel {

    private final String DATA_FORMAT = PropertiesManager.getConfigValue(DATA_FORMAT_FOR_TEST_RESULT.getValue());

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

    public void setName(String name) {
        this.name = name;
    }

    public void setStatusId(int statusId) {
        this.statusId = String.valueOf(statusId);
    }

    public void setProjectId(int projectId) {
        this.projectId = String.valueOf(projectId);
    }

    public void setSessionId(int sessionId) {
        this.sessionId = String.valueOf(sessionId);
    }

    public void setStartTime(long startTime) {
        this.startTime = new SimpleDateFormat(DATA_FORMAT).format(startTime);
    }

    public void setEndTime(long endTime) {
        this.endTime = new SimpleDateFormat(DATA_FORMAT).format(endTime);
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public void setAuthorId(int authorId) {
        this.authorId = String.valueOf(authorId);
    }
}
