package com.example.imal;

public class User {

    private String name;
    private String email;
    private String password;

    public User( String name ) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
