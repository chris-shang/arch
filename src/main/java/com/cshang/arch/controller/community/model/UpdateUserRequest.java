package com.cshang.arch.controller.community.model;

public class UpdateUserRequest {

    private String userName;
    private String password;
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void applyTo(User user) {
        Password passwordObj = new Password();
        passwordObj.setClearPassword(password);
        user.setEmail(email);
        user.setUserName(userName);
        user.setPassword(passwordObj);
    }

}
