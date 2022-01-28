package models;

import lombok.Getter;

@Getter
public class User {

    private int id;
    private String login;
    private String password;
    private String token;
}
