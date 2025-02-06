package com.example.demo.Authentication;

import com.example.demo.Users.Role;

public class RegisterRequest {
    public RegisterRequest() {
    }

    private String userName;
    private String email;
    private String password;
    private Role role;

    public RegisterRequest(String userName, String email, Role role, String password) {
        this.userName = userName;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
