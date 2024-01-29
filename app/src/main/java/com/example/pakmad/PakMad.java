package com.example.pakmad;

public class PakMad {
    private String entity;
    private String username;
    private String password;
    private String description;
    private int id;

    public PakMad(String entity, String username, String password, String description, int id) {
        this.entity = entity;
        this.username = username;
        this.password = password;
        this.description = description;
        this.id = id;
    }

    public PakMad(String entity, String username, String password, String description) {
        this.entity = entity;
        this.username = username;
        this.password = password;
        this.description = description;
    }

    public String getEntity() {
        return entity;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }
}
