package org.example.model;

import java.sql.Timestamp;

public class User {
    public User(){}
    private long userId;
    private String email;
    private String password;
    private String username;
    private Timestamp createdOn;
    public void setUserId (long userId){
        this.userId = userId;
    }
    public void setEmail (String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public void setUserName(String username){
        this.username = username;
    }
    public void setCreatedOn(Timestamp createdOn){
        this.createdOn = createdOn;
    }
}
