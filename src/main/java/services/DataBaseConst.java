package services;

import utils.PropertiesManager;

public enum DataBaseConst {

    URL(PropertiesManager.getConfigValue("dbUrl")),
    HOST(PropertiesManager.getConfigValue("dbHost")),
    PORT(PropertiesManager.getConfigValue("dbPort")),
    DATA_BASE_NAME(PropertiesManager.getConfigValue("dbName")),
    USER(PropertiesManager.getConfigValue("dbUser")),
    PASSWORD(PropertiesManager.getConfigValue("dbPassword"));

    private String constant;

    DataBaseConst(String constant) {
        this.constant = constant;
    }

    public String getConst() {
        return constant;
    }
}
