package com.pika.electricrat.sqli.dao;

import com.pika.electricrat.burteforce.po.UserEntity;
import com.pika.electricrat.csrf.po.UserInfoEntity;
import com.pika.electricrat.util.JDBCGbkUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserGbkDaoImpl{
    Statement s = JDBCGbkUtil.getStatement();

    public UserGbkDaoImpl() throws SQLException {
    }

    public HashMap<String, Object> findUserById(String id){
        return query("select * from sys_account where id=" + id);
    }

    public HashMap<String, Object> findUser(String username, String password){
        return query("select * from sys_account where username='" + username + "' and password='" + password + "'");
    }
    public HashMap<String, Object> findUserByUsername(String username){
        return query("select * from sys_account where username='" + username + "'");
    }

    public HashMap<String, Object> findUserFilter(String username, String password){
        username = username.replaceAll("'+", "\\\\'");
        password = password.replaceAll("'+", "\\\\'");
        return query("select * from sys_account where username='" + username + "' and password='" + password + "'");
    }

    public HashMap<String, Object> findUserByUsernameFuzzy(String username){
        return query("select * from sys_account where username like '%" + username + "%'");
    }

    public HashMap<String, Object> findUserByUsernameMultiple(String username){
        return query("select * from sys_account where (username=1 or username='" + username + "')");
    }
    public HashMap<String, Object> findUserInfoByUserID(int userId){
        return queryUserInfo("select * from sys_userinfo where userID=" + userId);
    }
    public List<HashMap<String, Object>> findAllUser(){
        return queryAll("select * from sys_account");
    }


    public HashMap<String, Object> query(String sql){
        System.out.println(sql);
        HashMap<String, Object> data = new HashMap<>();
        try {
            ResultSet rs = s.executeQuery(sql);
            rs.next();
            data.put("id", rs.getInt("id"));
            data.put("username", rs.getString("username"));
            data.put("msg", "ok");
        } catch (SQLException e){
            e.printStackTrace();
            data.put("msg", e.getMessage());
        }
        return data;
    }

    public List<HashMap<String, Object>> queryAll(String sql){
        List<HashMap<String, Object>> data = new ArrayList<>();
        try {
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()){
                HashMap<String, Object> d_list = new HashMap<>();
                d_list.put("id", rs.getInt("id"));
                d_list.put("username", rs.getString("username"));
                data.add(d_list);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return data;
    }

    public HashMap<String, Object> queryUserInfo(String sql){
        System.out.println(sql);
        HashMap<String, Object> data = new HashMap<>();
        try {
            ResultSet rs = s.executeQuery(sql);
            rs.next();
            data.put("phone", rs.getString("phone"));
            data.put("address", rs.getString("address"));
            data.put("msg", "ok");
        } catch (SQLException e){
            e.printStackTrace();
            data.put("msg", e.getMessage());
        }
        return data;
    }
    public HashMap<String, Object> queryCommentAll(){
        String sql = "select * from sys_comments;";
        HashMap<String, Object> data = new HashMap<>();
        try {
            ResultSet rs = s.executeQuery(sql);
            List<HashMap<String, Object>> items = new ArrayList<>();
            while (rs.next()){
                HashMap<String, Object> item = new HashMap<>();
                item.put("id", rs.getString("id"));
                item.put("content", rs.getString("content"));
                items.add(item);
            }
            data.put("comments", items);
            data.put("msg", "ok");
        } catch (SQLException e){
            e.printStackTrace();
            data.put("msg", e.getMessage());
        }
        return data;
    }
    public HashMap<String, Object> addUser(UserEntity user, UserInfoEntity userInfo) {
        HashMap<String, Object> data = new HashMap<>();
        try{
            int i = s.executeUpdate("insert into sys_account (username,password) values ('" + user.getUsername() + "', '" + user.getPassword() + "')");
            if (i < 1){
                data.put("msg", "error");
                return data;
            }
            HashMap<String, Object> userObj = this.findUserByUsername(user.getUsername());
            data.put("username", userObj.get("username"));
            int j = s.executeUpdate("insert into sys_userinfo (phone,address,userID) values ('" + userInfo.getPhone() + "', '" + userInfo.getAddress() + "', " + userObj.get("id") + ");");
            if (j < 1){
                data.put("msg", "error");
                return data;
            }
            System.out.println(userInfo);
            HashMap<String, Object> userInfoObj = this.findUserInfoByUserID((int) userObj.get("id"));
            System.out.println(userInfoObj);
            if (userInfoObj.isEmpty()){
                data.put("msg", "error");
                return data;
            }
            data.put("phone", userInfoObj.get("phone"));
            data.put("address", userInfoObj.get("address"));
            data.put("msg", "ok");
        }catch (SQLException e){
            e.printStackTrace();
            data.put("msg", e.getMessage());
        }
        return data;
    }
    public HashMap<String, Object> addComment(String content){
        HashMap<String, Object> data = new HashMap<>();
        try{
            int i = s.executeUpdate("insert into sys_comments (content) values ('" + content + "');");
            if (i < 1){
                data.put("msg", "error");
                return data;
            }
            data.put("msg", "ok");
        } catch (SQLException e){
            data.put("msg", e);
        }
        return data;
    }
    public HashMap<String, Object> delCommentByID(String content){
        HashMap<String, Object> data = new HashMap<>();
        try{
            int i = s.executeUpdate("delete from sys_comments where id=" + content);
            if (i < 1){
                data.put("msg", "error");
                return data;
            }
            data.put("msg", "ok");
        } catch (SQLException e){
            data.put("msg", e);
        }
        return data;
    }
    public HashMap<String, Object> getUserInfoByUserID(int userID){
        HashMap<String, Object> info = this.findUserInfoByUserID(userID);
        HashMap<String, Object> user =  this.findUserById(String.valueOf(userID));
        info.put("username", user.get("username"));
        return info;
    }
    public boolean updateUserInfoByUserID(int userID, String phone, String address){
        try {
            int i = s.executeUpdate("update sys_userinfo set phone='" + phone + "',address='" + address + "' where userID=" + userID);
            return i >= 1;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
