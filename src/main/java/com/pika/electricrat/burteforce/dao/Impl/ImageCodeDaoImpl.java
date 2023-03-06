package com.pika.electricrat.burteforce.dao.Impl;

import com.pika.electricrat.burteforce.dao.ImageCodeDao;
import com.pika.electricrat.burteforce.po.ImageCodeEntity;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ImageCodeDaoImpl implements ImageCodeDao {
    @Override
    public ImageCodeEntity add(ImageCodeEntity entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return ImageCodeDao.super.add(entity);
    }

    @Override
    public ImageCodeEntity find(ImageCodeEntity entity) {
        try{
            String sql = "select * from " + entity.getSqlTableName() + " where text=?";
            return jt.queryForObject(sql, new BeanPropertyRowMapper<>(ImageCodeEntity.class), entity.getText());
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ImageCodeEntity delect(ImageCodeEntity entity) {
        return null;
    }

    @Override
    public ImageCodeEntity update(ImageCodeEntity entity) {
        return null;
    }

    @Override
    public List<ImageCodeEntity> findAll() {
        return null;
    }

    @Override
    public List<ImageCodeEntity> addByList(List<ImageCodeEntity> entityList) {
        return null;
    }

    @Override
    public List<ImageCodeEntity> delectByList(List<ImageCodeEntity> entityList) {
        return null;
    }

    @Override
    public List<ImageCodeEntity> updateByList(List<ImageCodeEntity> entityList) {
        return null;
    }

    public void detectExpire(){
        String sql = "delete from " + (new ImageCodeEntity().getSqlTableName()) + " where expire_time <= ? ";
        jt.update(sql, System.currentTimeMillis());
    }
}
