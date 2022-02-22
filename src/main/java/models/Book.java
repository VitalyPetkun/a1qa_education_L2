package models;

import lombok.Getter;

import java.util.Date;

@Getter
public class Book implements BaseModel {

    public String author;
    public String title;
    public String genre;
    public double price;
    public Date publish_date;
    public String description;
    public String id;
    public String text;

    public String getId() {
        return id;
    }
}
