package com.revature.yolp.models;

public class User {
    private String id;
    private String username;
    private String password;

    private String fullName;

    private String email;
    private boolean isAdmin = false;


    public User() {

    }

    public User(String id, String username, String password, String fullName, String email, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public User(String id, String username, String password, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(String role) {
        this.isAdmin = isAdmin;
    }

    public String toFileString() {
        return id + ":" + username + ":" + password + ":" + isAdmin + "\n";
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin='" + isAdmin + '\'' +
                '}';
    }
}
