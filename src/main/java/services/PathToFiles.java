package services;

public enum PathToFiles {

    CURRENT_USER("src\\test\\resources\\currentUser.json"),
    NEW_USER("src\\test\\resources\\newUser.json");

    private String path;

    PathToFiles(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
