package com.pika.electricrat.sqli.dto;

import com.pika.electricrat.burteforce.po.UserEntity;
import com.pika.electricrat.csrf.po.UserInfoEntity;
import com.pika.electricrat.sqli.dao.UserGbkDaoImpl;
import com.pika.electricrat.web.Annotation.Api;
import com.pika.electricrat.web.Annotation.RequestMethodType;
import com.pika.electricrat.web.servlet.BaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/sqli/*")
public class SqliServlet extends BaseServlet {
    UserGbkDaoImpl ugdi = new UserGbkDaoImpl();

    public SqliServlet() throws SQLException {
    }

    @Api({RequestMethodType.GET})
    public Map<?, ?> showAllUser(HttpServletRequest request, HttpServletResponse response){
        HashMap<String, Object> data = new HashMap<>();
        data.put("user", ugdi.findAllUser());
        return data;
    }

    @Api({RequestMethodType.GET, RequestMethodType.POST})
    public Map<?, ?> showUserById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        HashMap<String, Object> data = new HashMap<>();
        data.put("user", ugdi.findUserById(id));
        return data;
    }

    @Api({RequestMethodType.GET, RequestMethodType.POST})
    public Map<?, ?> showUserByUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int type = Integer.parseInt(request.getParameter("type") != null?request.getParameter("type"):"0");
        HashMap<String, Object> data = new HashMap<>();
        switch (type){
            case 1 -> data.put("user", ugdi.findUserByUsername(username));
            case 2 -> data.put("user", ugdi.findUserByUsernameFuzzy(username));
            case 3 -> data.put("user", ugdi.findUserByUsernameMultiple(username));
            case 4 -> {
                HashMap<String, Object> user = ugdi.findUserByUsername(username);
                user.remove("msg");
                data.put("user", user);
            }
            default -> {
                ugdi.findUserByUsername(username);
                data.put("user", null);}
        }
        return data;
    }

    @Api({RequestMethodType.POST})
    public Map<?, ?> login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HashMap<String, Object> user = ugdi.findUser(username, password);
        if (!user.isEmpty() && user.get("msg") == "ok"){
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        }
        return user;
    }

    @Api({RequestMethodType.POST})
    public Map<?, ?> loginFilter(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return ugdi.findUserFilter(username, password);
    }

    @Api({RequestMethodType.POST})
    public Map<?, ?> register(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        return ugdi.addUser(new UserEntity(username, password), new UserInfoEntity(phone, address));
    }

    @Api({RequestMethodType.GET})
    public Map<?, ?> loginOut(HttpServletRequest request, HttpServletResponse response){
        HashMap<String, Object> data = new HashMap<>();
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        data.put("loginOutStatus", true);
        return data;
    }

    @Api({RequestMethodType.GET})
    public Map<?, ?> getCurrentUserInfo(HttpServletRequest request, HttpServletResponse response){
        HashMap<String, Object> data = new HashMap<>();
        HttpSession session = request.getSession();
        boolean isLogin = session.getAttribute("user") != null;
        data.put("loginStatus", isLogin);
        if (isLogin){
            HashMap<String, Object> user = (HashMap<String, Object>) session.getAttribute("user");
            HashMap<String, Object> user_info = ugdi.getUserInfoByUserID((int) user.get("id"));
            data.put("userInfo", user_info);
        }
        return data;
    }
    @Api({RequestMethodType.POST})
    public Map<?, ?> updateCurrentUserInfo(HttpServletRequest request, HttpServletResponse response){
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        HashMap<String, Object> data = new HashMap<>();
        HttpSession session = request.getSession();
        boolean isLogin = session.getAttribute("user") != null;
        data.put("loginStatus", isLogin);
        if (isLogin){
            HashMap<String, Object> user = (HashMap<String, Object>) session.getAttribute("user");
            data.put("updateStatus", ugdi.updateUserInfoByUserID((int) user.get("id"), phone, address));
        }
        return data;
    }

    @Api({RequestMethodType.GET})
    public Map<?, ?> getAllComment(HttpServletRequest request, HttpServletResponse response){
        return ugdi.queryCommentAll();
    }
    @Api({RequestMethodType.POST})
    public Map<?, ?> addComment(HttpServletRequest request, HttpServletResponse response){
        String content = request.getParameter("content");
        return ugdi.addComment(content);
    }
    @Api({RequestMethodType.POST, RequestMethodType.GET})
    public Map<?, ?> delComment(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        return ugdi.delCommentByID(id);
    }
}
