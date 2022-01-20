package models.post;

public class PostModelForRequest {

    private String title;
    private String body;

    private int userId;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String setRandomTitle() {
        String newTitle = null;

        for (int i = 0; i <= 10 + (int) (Math.random() * 100); i++) {
            newTitle += String.valueOf(Character.toChars((int) (Math.random() * (127- 32)) + 32));
        }

        this.setTitle(newTitle);

        return newTitle;
    }

    public String setRandomBody() {
        String newBody = null;

        for (int i = 0; i <= 10 + (int) (Math.random() * 100); i++) {
            newBody += String.valueOf(Character.toChars((int) (Math.random() * (127 - 32)) + 32));
        }

        this.setBody(newBody);

        return newBody;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
