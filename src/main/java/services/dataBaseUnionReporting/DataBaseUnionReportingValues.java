package services.dataBaseUnionReporting;

public enum DataBaseUnionReportingValues {

    DATA_FORMAT_FOR_TEST_RESULT("dataFormatForTestResult"),
    MIN_ID("minId"),
    ID_QUANTITY_LIMITATION("idQuantityLimitation");

    private String value;

    DataBaseUnionReportingValues(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
