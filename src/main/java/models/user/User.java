package models.user;

import lombok.Getter;
import models.BaseModel;

@Getter
public class User implements BaseModel {

    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public int getId() {
        return id;
    }
}
