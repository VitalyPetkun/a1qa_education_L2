package services;

public enum DataBaseConst {

    URL("dbUrl"),
    HOST("dbHost"),
    PORT("dbPort"),
    DATA_BASE_NAME("dbName"),
    USER("dbUser"),
    PASSWORD("dbPassword"),
    DRIVER("jdbcDriver");

    private String constant;

    DataBaseConst(String constant) {
        this.constant = constant;
    }

    public String getConst() {
        return constant;
    }
}
