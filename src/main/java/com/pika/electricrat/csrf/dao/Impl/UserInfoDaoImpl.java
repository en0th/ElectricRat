package com.pika.electricrat.csrf.dao.Impl;

import com.pika.electricrat.csrf.dao.UserInfoDao;
import com.pika.electricrat.csrf.po.UserInfoEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class UserInfoDaoImpl implements UserInfoDao {
    private final String table_name = new UserInfoEntity().getSqlTableName();
    @Override
    public UserInfoEntity add(UserInfoEntity entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return UserInfoDao.super.add(entity);
    }

    @Override
    public UserInfoEntity find(UserInfoEntity entity) {
        try {
            UserInfoEntity userInfo = jt.queryForObject("select * from "+ table_name+" where userID=?", new BeanPropertyRowMapper<>(UserInfoEntity.class), entity.getUserID());
            return userInfo;
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public UserInfoEntity delect(UserInfoEntity entity) {
        return null;
    }

    @Override
    public UserInfoEntity update(UserInfoEntity entity) {
        try {
            jt.update("update "+ table_name+" set phone=?,address=? where userID=?", entity.getPhone(), entity.getAddress(), entity.getUserID());
        } catch (Exception e){
            return null;
        }
        return find(entity);
    }

    @Override
    public List<UserInfoEntity> findAll() {
        try{
            return jt.query("select * from "+ table_name, new BeanPropertyRowMapper<>(UserInfoEntity.class));
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public List<UserInfoEntity> addByList(List<UserInfoEntity> entityList) {
        return null;
    }

    @Override
    public List<UserInfoEntity> delectByList(List<UserInfoEntity> entityList) {
        return null;
    }

    @Override
    public List<UserInfoEntity> updateByList(List<UserInfoEntity> entityList) {
        return null;
    }
}
