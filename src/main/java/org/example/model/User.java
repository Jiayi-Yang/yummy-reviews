package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    public User(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "username")
    private String username;
    @Column(name = "created_on")
    private Timestamp createdOn;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<Rating> ratings;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles = new HashSet<>();

    public void addRole(Role role){
        this.roles.add(role);
//        role.getUsers().add(this);
    }
    public void removeRole(Role role){
        this.roles.remove(role);
//        role.getUsers().remove(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(username, user.username) &&
                Objects.equals(createdOn, user.createdOn) &&
                Objects.equals(ratings, user.ratings);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(userId, email, password, username, createdOn);
//    }

    public long getUserId() {
        return userId;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Timestamp createdOn){
        this.createdOn = createdOn;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}

