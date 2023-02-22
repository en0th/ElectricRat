package com.pika.electricrat.csrf.bo.Impl;

import com.pika.electricrat.csrf.bo.UserInfoServer;
import com.pika.electricrat.csrf.dao.Impl.UserInfoDaoImpl;
import com.pika.electricrat.csrf.po.UserInfoEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap;
import java.util.HashMap;

public class UserInfoServerImpl implements UserInfoServer {
    UserInfoDaoImpl uidi = new UserInfoDaoImpl();
    public HashMap<String,String> findByUserId(int id) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        HashMap<String, String> data = new HashMap<>();
        UserInfoEntity user_info = uidi.find(new UserInfoEntity(id));
        System.out.println(uidi);
        if (user_info != null){
            data.put("phone", user_info.getPhone());
            data.put("address", user_info.getAddress());
        } else {
            UserInfoEntity entity = uidi.add(new UserInfoEntity("默认", "默认", id));
            data.put("phone", entity.getPhone());
            data.put("address", entity.getAddress());
        }
        return data;
    }

    public boolean updateByUserId(int id, String phone,String address){
        return uidi.update(new UserInfoEntity(phone,address,id)) != null;
    }
}
