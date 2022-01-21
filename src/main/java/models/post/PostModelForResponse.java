package models.post;

import lombok.Getter;

@Getter
public class PostModelForResponse {

    private int userId;
    private int id;
    private String title;
    private String body;
}
