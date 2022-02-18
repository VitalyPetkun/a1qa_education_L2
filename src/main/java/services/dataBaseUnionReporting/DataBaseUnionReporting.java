package services.dataBaseUnionReporting;

public enum DataBaseUnionReporting {

    TEST_TABLE("test"),
    TEST_ID("id"),
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
    AUTHOR_ID("id"),
    AUTHOR_NAME("name"),
    AUTHOR_LOGIN("login"),
    AUTHOR_EMAIL("email"),
    SESSION_TABLE("session"),
    SESSION_ID("id"),
    SESSION_SESSION_KEY("session_key"),
    SESSION_CREATED_TIME("created_time"),
    SESSION_BUILD_NUMBER("build_number"),
    PROJECT_TABLE("project"),
    PROJECT_ID("id"),
    PROJECT_NAME("name"),
    STATUS_TABLE("status"),
    STATUS_ID("id"),
    STATUS_NAME("name");

    private String value;

    DataBaseUnionReporting(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}