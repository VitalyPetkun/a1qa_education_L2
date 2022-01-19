package model;

public class PostModelForRequest {

    private int userId;

    private String title;
    private String body;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String setRandomTitle() {
        String newTitle = null;

        for (int i = 0; i <= 10 + (int) (Math.random() * 100); i++) {
            newTitle += String.valueOf(Character.toChars((int) (Math.random() * (123 - 97)) + 97));
        }

        this.setTitle(newTitle);

        return newTitle;
    }

    public String setRandomBody() {
        String newBody = null;

        for (int i = 0; i <= 10 + (int) (Math.random() * 100); i++) {
            newBody += String.valueOf(Character.toChars((int) (Math.random() * (123 - 97)) + 97));
        }

        this.setBody(newBody);

        return newBody;
    }
}
