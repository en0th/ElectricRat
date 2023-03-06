package com.pika.electricrat.csrf.bo.Impl;

import com.pika.electricrat.burteforce.dao.Impl.UserDaoImpl;
import com.pika.electricrat.burteforce.po.UserEntity;
import com.pika.electricrat.csrf.bo.UserInfoServer;
import com.pika.electricrat.csrf.dao.Impl.UserInfoDaoImpl;
import com.pika.electricrat.csrf.po.UserInfoEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInfoServerImpl implements UserInfoServer {
    UserInfoDaoImpl uidi = new UserInfoDaoImpl();
    UserDaoImpl udi = new UserDaoImpl();
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

    public Map<String,Object> findAll(String type, int id){
        HashMap<String, Object> data = new HashMap<>();
        List<UserInfoEntity> all = uidi.findAll();
        for (UserInfoEntity userInfoEntity : all) {
            if (type.equals("admin")){
                data.put(udi.findByID(new UserEntity(userInfoEntity.getUserID())).getUsername(), userInfoEntity);
            } else {
                if (id == userInfoEntity.getUserID()){
                    data.put(udi.findByID(new UserEntity(id)).getUsername(), userInfoEntity);
                    break;
                }
            }
        }
        return data;
    }
}
