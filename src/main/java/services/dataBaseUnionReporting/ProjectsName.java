package services.dataBaseUnionReporting;

public enum ProjectsName {

    PROJECT_USERINYERFACE("Userinyerface");

    private String name;

    ProjectsName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
