package com.example.black.Classes;

public class User {
    public String name;
    public String surName;
    public String address;
    public String moreInfo;
    public String phoneNumber;
    public String password;

    public User(String name, String surName, String address,String moreInfo, String phoneNumber, String password) {
        this.name = name;
        this.surName = surName;
        this.address = address;
        this.moreInfo = moreInfo;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public User (){

    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getAddress() {
        return address;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
