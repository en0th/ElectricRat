package com.pika.electricrat.csrf.po;

import com.pika.electricrat.web.db.BaseEntity;

public class UserInfoEntity implements BaseEntity {
    private int id;
    private String phone;
    private String address;
    private int userID;

    public UserInfoEntity() {
    }

    public UserInfoEntity(String phone, String address) {
        this.phone = phone;
        this.address = address;
    }

    public UserInfoEntity(String phone, String address, int userID) {
        this.phone = phone;
        this.address = address;
        this.userID = userID;
    }

    public UserInfoEntity(int userID) {
        this.userID = userID;
    }

    public UserInfoEntity(int id, String phone, String address, int userID) {
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "UserInfoEntity{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", userID=" + userID +
                '}';
    }

    @Override
    public String getSqlTableName() {
        return "sys_userinfo";
    }
}
