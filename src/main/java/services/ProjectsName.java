package services;

public enum ProjectsName {

    PROJECTS_NEXAGE("Nexage"),
    PROJECTS_USERINYERFACE("Userinyerface");

    private String name;

    ProjectsName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
