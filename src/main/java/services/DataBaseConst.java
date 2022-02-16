package services;

import utils.PropertiesManager;

public enum DataBaseConst {

    URL(PropertiesManager.getConfigValue("dbUrl")),
    HOST(PropertiesManager.getConfigValue("dbHost")),
    PORT(PropertiesManager.getConfigValue("dbPort")),
    DATA_BASE_NAME(PropertiesManager.getConfigValue("dbName")),
    USER(PropertiesManager.getConfigValue("dbUser")),
    PASSWORD(PropertiesManager.getConfigValue("dbPassword")),
    TEST_TABLE("test"),
    TEST_NAME("name"),
    TEST_STATUS_ID("status_id"),
    TEST_METHOD_NAME("method_name"),
    TEST_PROJECT_ID("project_id"),
    TEST_SESSION_ID("session_id"),
    TEST_START_TIME("start_time"),
    TEST_END_TIME("end_time"),
    TEST_ENV("env"),
    TEST_BROWSER("browser"),
    TEST_AUTHOR_ID("author_id"),
    AUTHOR_TABLE("author"),
    AUTHOR_NAME("name"),
    AUTHOR_LOGIN("login"),
    AUTHOR_EMAIL("email"),
    SESSION_TABLE("session"),
    SESSION_SESSION_KEY("session_key"),
    SESSION_CREATED_TIME("created_time"),
    SESSION_BUILD_NUMBER("build_number"),
    PROJECT_TABLE("project"),
    PROJECT_NAME("name"),
    STATUS_TABLE("status"),
    STATUS_NAME("name");

    private String constant;

    DataBaseConst(String constant) {
        this.constant = constant;
    }

    public String getConst() {
        return constant;
    }
}