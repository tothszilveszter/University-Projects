package com.projects.FirstProject.models;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class User extends AbstractEntity{

    @NotNull
    private String userEmail;
    @NotNull
    private String password;

    private UserType userType;

    public User(String userEmail, String password, UserType userType) {
        this.userEmail = userEmail;
        this.password = password;
        this.userType = userType;
    }

    public User(){}

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), password);
    }
}
