package services;

import utils.PropertiesManager;

public enum ConfigVariables {

    VITALY_PETKUN_NAME(PropertiesManager.getConfigValue("authorName")),
    VITALY_PETKUN_LOGIN(PropertiesManager.getConfigValue("authorLogin")),
    VITALY_PETKUN_EMAIL(PropertiesManager.getConfigValue("authorEmail")),
    PROJECT_NAME(PropertiesManager.getConfigValue("projectName")),
    ENV(System.getenv().get(PropertiesManager.getConfigValue("env"))),
    TIME_ZONE(PropertiesManager.getConfigValue("timeZone"));

    private String variable;

    ConfigVariables(String variable) {
        this.variable = variable;
    }

    public String getVariable() {
        return variable;
    }
}
