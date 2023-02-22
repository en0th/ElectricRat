package com.pika.electricrat.burteforce.dao.Impl;

import com.pika.electricrat.burteforce.dao.UserDao;
import com.pika.electricrat.burteforce.po.UserEntity;
import com.pika.electricrat.util.JdbcUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public UserEntity add(UserEntity entity) {
        return null;
    }

    @Override
    public UserEntity find(UserEntity entity) {
        try {
            String sql = "select * from sys_account where username=? and password=?";
            return jt.queryForObject(sql, new BeanPropertyRowMapper<>(UserEntity.class), entity.getUsername(), entity.getPassword());
        } catch (DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserEntity delect(UserEntity entity) {
        return null;
    }

    @Override
    public UserEntity update(UserEntity entity) {
        return null;
    }

    @Override
    public List<UserEntity> findAll(List<UserEntity> entityList) {
        return null;
    }

    @Override
    public List<UserEntity> addByList(List<UserEntity> entityList) {
        return null;
    }

    @Override
    public List<UserEntity> delectByList(List<UserEntity> entityList) {
        return null;
    }

    @Override
    public List<UserEntity> updateByList(List<UserEntity> entityList) {
        return null;
    }
}
