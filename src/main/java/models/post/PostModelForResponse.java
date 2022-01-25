package models.post;

import lombok.Getter;
import models.BaseModel;

@Getter
public class PostModelForResponse implements BaseModel {

    private int id;
    private int userId;
    private String title;
    private String body;

    public int getId() {
        return id;
    }
}
