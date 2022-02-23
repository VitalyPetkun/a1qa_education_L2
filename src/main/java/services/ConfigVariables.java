package services;

public enum ConfigVariables {

    PROJECT_NAME("projectName"),
    ENV("env"),
    TIME_ZONE("timeZone");

    private String variable;

    ConfigVariables(String variable) {
        this.variable = variable;
    }

    public String getVariable() {
        return variable;
    }
}
