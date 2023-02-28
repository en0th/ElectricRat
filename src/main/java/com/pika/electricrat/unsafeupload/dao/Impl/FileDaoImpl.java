package com.pika.electricrat.unsafeupload.dao.Impl;

import com.pika.electricrat.unsafeupload.dao.FileDao;
import com.pika.electricrat.unsafeupload.po.FileEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileDaoImpl implements FileDao {
    FileEntity fe = new FileEntity();

    @Override
    public FileEntity add(FileEntity entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return FileDao.super.add(entity);
    }

    @Override
    public FileEntity find(FileEntity entity) {
        try {
            return jt.queryForObject("select * from " + fe.getSqlTableName() + " where unid=?",
                    new BeanPropertyRowMapper<>(FileEntity.class), entity.getUnid());
        }catch (Exception e){
            return null;
        }
    }

    public FileEntity queryById(FileEntity entity){
        try {
            return jt.queryForObject("select * from " + fe.getSqlTableName() + " where id=?",
                    new BeanPropertyRowMapper<>(FileEntity.class), entity.getId());
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public FileEntity delect(FileEntity entity) {
        return null;
    }

    @Override
    public FileEntity update(FileEntity entity) {
        try {
            int res = jt.update("update " + fe.getSqlTableName() + " set fileName=?,filePath=? where id=?",
                    entity.getFileName(),entity.getFilePath(), entity.getId());
            if (res<1){
                return null;
            }
            return queryById(entity);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<FileEntity> findAll(List<FileEntity> entityList) {
        return null;
    }

    @Override
    public List<FileEntity> addByList(List<FileEntity> entityList) {
        return null;
    }

    @Override
    public List<FileEntity> delectByList(List<FileEntity> entityList) {
        return null;
    }

    @Override
    public List<FileEntity> updateByList(List<FileEntity> entityList) {
        return null;
    }
    public List<Map<String, Object>> queryAllMap(){
        return jt.query("select * from " + fe.getSqlTableName(), new ResultSetExtractor<List<Map<String, Object>>>() {
            @Override
            public List<Map<String, Object>> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Map<String, Object>> data = new ArrayList<>();
                while (rs.next()){
                    Map<String, Object> item = new HashMap<>();
                    item.put("id", rs.getInt("id"));
                    item.put("fileName", rs.getString("fileName"));
                    item.put("fileType", rs.getString("fileType"));
                    data.add(item);
                }
                return data;
            }
        });
    }
}
