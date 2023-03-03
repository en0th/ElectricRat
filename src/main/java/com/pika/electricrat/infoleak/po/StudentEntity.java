package com.pika.electricrat.infoleak.po;

import com.pika.electricrat.web.db.BaseEntity;

public class StudentEntity implements BaseEntity {
    private int id;
    private long number;

    public StudentEntity() {
    }

    public StudentEntity(long number, String password) {
        this.number = number;
        this.password = password;
    }

    private String name;
    private String password;

    @Override
    public String toString() {
        return "StudentEntity{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getSqlTableName() {
        return "sys_students";
    }
}
