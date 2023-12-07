package com.example.shop.Domain;

public class UserResponse {
    private String fullname;
    private String email;
    private String address;
    private String yourphone;
    private String password;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getYourphone() {
        return yourphone;
    }

    public void setYourphone(String yourphone) {
        this.yourphone = yourphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
