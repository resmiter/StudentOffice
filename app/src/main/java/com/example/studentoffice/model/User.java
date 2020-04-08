package com.example.studentoffice.model;

public class User {
    private static User user;
    private String type;
    private String email;
    private String password;
    private String accessToken;
    private String refreshToken;

    private User(){
        this.type = "";
    }

    public static void setUser(User user) {
        User.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void clear() {
        this.type = "";
        this.email = null;
        this.password = null;
        this.accessToken = null;
        this.refreshToken = null;
    }

    public static User getUser(){
        if (user == null) {
            user = new User();
        }
        return user;
    }
}