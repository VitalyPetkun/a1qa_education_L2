package services;

public enum PathToFiles {

    EXPECTED_POST("src\\test\\resources\\expectedPost.json"),
    ACTUAL_USER("src\\test\\resources\\actualUser.json"),
    EXPECTED_USER("src\\test\\resources\\expectedUser.json");

    private String path;

    PathToFiles(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
