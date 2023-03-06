package com.pika.electricrat.xss.dao.impl;

import com.pika.electricrat.xss.dao.CommentDao;
import com.pika.electricrat.xss.po.CommentEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentDaoImpl implements CommentDao {
    CommentEntity ce = new CommentEntity();
    @Override
    public CommentEntity add(CommentEntity entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return CommentDao.super.add(entity);
    }

    @Override
    public CommentEntity find(CommentEntity entity) {
        return jt.queryForObject("select * from " + ce.getSqlTableName() + " where create_time=?", new BeanPropertyRowMapper<>(CommentEntity.class), entity.getCreate_time());
    }

    @Override
    public CommentEntity delect(CommentEntity entity) {
        try {
            jt.update("delete from " + ce.getSqlTableName() + " where id=?",entity.getId());
        } catch (Exception e){
            return null;
        }
        return entity;
    }

    @Override
    public CommentEntity update(CommentEntity entity) {
        return null;
    }

    @Override
    public List<CommentEntity> findAll() {
        return null;
    }

    @Override
    public List<CommentEntity> addByList(List<CommentEntity> entityList) {
        return null;
    }

    @Override
    public List<CommentEntity> delectByList(List<CommentEntity> entityList) {
        return null;
    }

    @Override
    public List<CommentEntity> updateByList(List<CommentEntity> entityList) {
        return null;
    }

    public List<CommentEntity> queryAll(){
        return jt.query("select * from " + ce.getSqlTableName(), new BeanPropertyRowMapper<>(CommentEntity.class));
    }

    public List<Map<String, Object>> queryAllMap(){
        return jt.query("select * from " + ce.getSqlTableName(), new ResultSetExtractor<List<Map<String, Object>>>() {
            @Override
            public List<Map<String, Object>> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Map<String, Object>> data = new ArrayList<>();
                while (rs.next()){
                    Map<String, Object> item = new HashMap<>();
                    item.put("id", rs.getInt("id"));
                    item.put("content", rs.getString("content"));
                    item.put("create_time", rs.getLong("create_time"));
                    item.put("author", rs.getString("author"));
                    data.add(item);
                }
                return data;
            }
        });
    }
}
