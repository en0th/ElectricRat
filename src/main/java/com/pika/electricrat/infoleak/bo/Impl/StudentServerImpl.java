package com.pika.electricrat.infoleak.bo.Impl;

import com.pika.electricrat.infoleak.bo.StudentServer;
import com.pika.electricrat.infoleak.dao.Impl.StudentDaoImpl;
import com.pika.electricrat.infoleak.po.StudentEntity;

public class StudentServerImpl implements StudentServer {
    StudentDaoImpl sdi = new StudentDaoImpl();

    public Boolean login(long number, String password) {
        StudentEntity entity = new StudentEntity(number, password);
        return sdi.find(entity) != null;
    }
}
