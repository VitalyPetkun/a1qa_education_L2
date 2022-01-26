package models.post;

import lombok.Getter;

@Getter
public class PostModelForRequest {

    private int userId;
    private String title;
    private String body;

    public PostModelForRequest(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }
}
