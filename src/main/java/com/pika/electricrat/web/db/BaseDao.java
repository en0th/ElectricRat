package com.pika.electricrat.web.db;

import com.pika.electricrat.util.JdbcUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public interface BaseDao<T> {
    JdbcTemplate jt = new JdbcTemplate(JdbcUtil.getDataSource());

    default T add(T entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> entityClass = entity.getClass();
        Field[] fields = entityClass.getDeclaredFields();
        String table_name = (String) entityClass.getMethod("getSqlTableName").invoke(entity);
        StringBuilder k_list = new StringBuilder();
        StringBuilder v_list = new StringBuilder();
        ArrayList<Object> o_list = new ArrayList<>(fields.length);
        for(Field f : fields){
            f.setAccessible(true);
            String o = String.valueOf(f.get(entity));
            String fName = f.getName();
            k_list.append(fName).append(",");
            v_list.append("?").append(",");
            o_list.add(o);
        }
        k_list.delete(k_list.length()-1, k_list.length());
        v_list.delete(v_list.length()-1, v_list.length());
        String sql = "INSERT INTO " + table_name + " (" + k_list + ") VALUES (" + v_list + ")";
        if (jt.update(sql,o_list.toArray())>0){
            return find(entity);
        }
        return null;
    }
    T find(T entity);
    T delect(T entity);
    T update(T entity);
    List<T> findAll();
    List<T> addByList(List<T> entityList);
    List<T> delectByList(List<T> entityList);
    List<T> updateByList(List<T> entityList);
}
