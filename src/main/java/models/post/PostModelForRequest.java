package models.post;

import lombok.Data;

@Data
public class PostModelForRequest {

    private String title;
    private String body;
    private int userId;
}
