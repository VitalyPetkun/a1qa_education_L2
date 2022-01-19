package model;

public class PostModelForResponse {

    private int userId;
    private int id;

    private String title;
    private String body;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Object getValue(String key) {
        switch (key) {
            case ("id"):
                return this.getId();
            case ("userId"):
                return this.getUserId();
            case ("title"):
                return this.getTitle();
            case ("body"):
                return this.getBody();
        }

        return null;
    }
}
