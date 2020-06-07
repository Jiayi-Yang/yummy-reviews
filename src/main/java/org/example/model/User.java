package org.example.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class User {
    public  User(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "user_name")
    private String username;
    @Column(name = "created_on")
    private Timestamp createdOn;

    public long getUserId() {
        return userId+super.hashCode();
    }

    public void setUserId (long userId){
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail (String email){
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String username){
        this.username = username;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Timestamp createdOn){
        this.createdOn = createdOn;
    }
}

