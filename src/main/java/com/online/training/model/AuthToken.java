package com.online.training.model;

public class AuthToken {

    private String token;
    private String username;
    private Long id;
    private String role;

    public AuthToken(){}

    public AuthToken(String token, String username, Long id, String role) {
        this.token = token;
        this.username = username;
        this.id = id;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
