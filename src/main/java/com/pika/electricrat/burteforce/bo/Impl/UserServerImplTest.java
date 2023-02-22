package com.pika.electricrat.burteforce.bo.Impl;

import com.pika.electricrat.burteforce.bo.UserServer;
import com.pika.electricrat.burteforce.dao.Impl.ImageCodeDaoImpl;
import com.pika.electricrat.burteforce.dao.Impl.UserDaoImpl;
import com.pika.electricrat.burteforce.po.UserEntity;
import com.pika.electricrat.util.ImageVerificationCode;
import com.pika.electricrat.web.Annotation.RequestMethodType;
import org.junit.Test;

import java.util.Map;

public class UserServerImplTest implements UserServer {
    UserDaoImpl ud = new UserDaoImpl();
    ImageCodeDaoImpl icd = new ImageCodeDaoImpl();


    @Override
    public Boolean login(String username, String password) {
        UserEntity entity = new UserEntity(username, password);
        return ud.find(entity) != null;
    }

    @Test
    public void getImageVerificationCode(){
        ImageVerificationCode image = new ImageVerificationCode();
        Map<String, String> data = image.getImage();
        System.out.println(data.get("image"));
    }
    @Test
    public void VerificationImageCodeEasy(){
        RequestMethodType[] value = {RequestMethodType.GET, RequestMethodType.POST};
        for (RequestMethodType v : value){
            System.out.println(String.valueOf(v).equals("GET"));
        }
    }
}
