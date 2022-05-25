package com.example.taserfan.Model;

public class AuthenticateData {
    private String email;
    private String password;

    public AuthenticateData(String email, String passwd) {
        this.email = email;
        this.password = passwd;
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
}
