package com.pika.electricrat.burteforce.bo.Impl;

import com.pika.electricrat.burteforce.bo.UserServer;
import com.pika.electricrat.burteforce.dao.Impl.ImageCodeDaoImpl;
import com.pika.electricrat.burteforce.dao.Impl.UserDaoImpl;
import com.pika.electricrat.burteforce.po.ImageCodeEntity;
import com.pika.electricrat.burteforce.po.UserEntity;
import com.pika.electricrat.util.ImageVerificationCode;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class UserServerImpl implements UserServer {
    UserDaoImpl ud = new UserDaoImpl();
    ImageCodeDaoImpl icd = new ImageCodeDaoImpl();
    ImageVerificationCode image = new ImageVerificationCode();


    @Override
    public Boolean login(String username, String password) {
        UserEntity entity = new UserEntity(username, password);
        return ud.find(entity) != null;
    }

    public UserEntity loginResEntity(String username, String password) {
        UserEntity entity = new UserEntity(username, password);
        return ud.find(entity);
    }

    public String getImageVerificationCode() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Map<String, String> data = image.getImage();
        long now_time = System.currentTimeMillis();
        ImageCodeEntity codeDao = new ImageCodeEntity(data.get("id"), now_time, (now_time + 1000*60*5), (data.get("code")));
        icd.add(codeDao);
        icd.detectExpire();
        return data.get("image");
    }

    public Boolean verificationImageCodeEasy(String text){
        ImageCodeEntity entity = new ImageCodeEntity(text);
        ImageCodeEntity res = icd.find(entity);
        return res != null;
    }

    public String getVerificationToken(){
        return image.GetRandom(32);
    }
}
