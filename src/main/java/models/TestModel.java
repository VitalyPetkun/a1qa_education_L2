package models;

import lombok.Data;
import utils.PropertiesManager;
import java.text.SimpleDateFormat;
import static services.dataBaseUnionReporting.DataBaseUnionReportingValues.DATA_FORMAT_FOR_TEST_RESULT;

@Data
public class TestModel {

    private final String DATA_FORMAT = PropertiesManager.getConfigValue(DATA_FORMAT_FOR_TEST_RESULT.getValue());

    private String name;
    private int statusId;
    private int projectId;
    private int sessionId;
    private String startTime;
    private String endTime;
    private String env;
    private String browser;
    private int authorId;

    public void setStartTime(long startTime) {
        this.startTime = new SimpleDateFormat(DATA_FORMAT).format(startTime);
    }

    public void setEndTime(long endTime) {
        this.endTime = new SimpleDateFormat(DATA_FORMAT).format(endTime);
    }
}
