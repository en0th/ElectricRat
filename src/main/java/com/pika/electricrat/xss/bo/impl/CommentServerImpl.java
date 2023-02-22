package com.pika.electricrat.xss.bo.impl;

import com.pika.electricrat.xss.bo.CommentServer;
import com.pika.electricrat.xss.dao.impl.CommentDaoImpl;
import com.pika.electricrat.xss.po.CommentEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentServerImpl implements CommentServer {
    CommentDaoImpl cdi = new CommentDaoImpl();
    public Boolean addComment(String content) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        CommentEntity entity = new CommentEntity(content, System.currentTimeMillis());
        CommentEntity commentEntity = cdi.add(entity);
        return commentEntity != null;
    }
    public Boolean addComment(String content, String author) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        CommentEntity entity = new CommentEntity(content, author, System.currentTimeMillis());
        CommentEntity commentEntity = cdi.add(entity);
        return commentEntity != null;
    }

    public HashMap<Integer, String> getAllComment(){
        HashMap<Integer, String> data = new HashMap<>();
        List<CommentEntity> commentEntities = cdi.queryAll();
        for (CommentEntity c : commentEntities){
            data.put(c.getId(),c.getContent());
        }
        return data;
    }

    public List<Map<String, Object>> getAllCommentList(){
        return cdi.queryAllMap();
    }

    public Boolean deleteCommentByID(int id){
        return cdi.delect(new CommentEntity(id)) != null;
    }
}
