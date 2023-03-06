package com.pika.electricrat.infoleak.dao.Impl;

import com.pika.electricrat.burteforce.po.UserEntity;
import com.pika.electricrat.infoleak.dao.StudentDao;
import com.pika.electricrat.infoleak.po.StudentEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public StudentEntity add(StudentEntity entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return StudentDao.super.add(entity);
    }

    @Override
    public StudentEntity find(StudentEntity entity) {
        try {
            String sql = "select * from " + entity.getSqlTableName() + " where number=? and password=?";
            return jt.queryForObject(sql, new BeanPropertyRowMapper<>(StudentEntity.class), entity.getNumber(), entity.getPassword());
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public StudentEntity delect(StudentEntity entity) {
        return null;
    }

    @Override
    public StudentEntity update(StudentEntity entity) {
        return null;
    }

    @Override
    public List<StudentEntity> findAll() {
        return null;
    }

    @Override
    public List<StudentEntity> addByList(List<StudentEntity> entityList) {
        return null;
    }

    @Override
    public List<StudentEntity> delectByList(List<StudentEntity> entityList) {
        return null;
    }

    @Override
    public List<StudentEntity> updateByList(List<StudentEntity> entityList) {
        return null;
    }
}
