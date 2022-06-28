package com.example.eventtrackingapp;

public class User {

    private long uID;
    private String name;
    private String email;
    private String phoneNumber;
    private String userName;
    private String password;

    public User(long uID, String name, String email, String phoneNumber, String userName, String password) {
        this.uID = uID;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
    }

    public User(String name, String email, String phoneNumber, String userName, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
    }

    public User() {

    }

    public long getuID() {
        return uID;
    }

    public void setuID(long uID) {
        this.uID = uID;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

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

    @Override
    public String toString() {
        return "_id: " + uID + "\nName: " + name + "\nEmail: " + email +
                "\nPhone Number: " + phoneNumber + "\nUsername: " + userName
                + "\nPassword: " + password;
    }

}
