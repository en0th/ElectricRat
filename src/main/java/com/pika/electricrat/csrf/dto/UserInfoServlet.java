package com.pika.electricrat.csrf.dto;

import com.pika.electricrat.burteforce.bo.Impl.UserServerImpl;
import com.pika.electricrat.burteforce.po.UserEntity;
import com.pika.electricrat.csrf.bo.Impl.UserInfoServerImpl;
import com.pika.electricrat.web.Annotation.Api;
import com.pika.electricrat.web.Annotation.RequestMethodType;
import com.pika.electricrat.web.servlet.BaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebServlet("/csrf/*")
public class UserInfoServlet extends BaseServlet {
    UserServerImpl udi = new UserServerImpl();
    UserInfoServerImpl uisi = new UserInfoServerImpl();

    @Api({RequestMethodType.POST})
    public Map<?, ?> loginWithCookie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HashMap<String, Boolean> data = new HashMap<>();
        UserEntity login_res = udi.loginResEntity(username, password);
        data.put("loginStatus", login_res!=null);
        if (login_res!=null){
            String key = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("token", key);

            cookie.setPath("/");
            response.addCookie(cookie);
            HttpSession session = request.getSession();
            session.setAttribute(key, login_res);
            return data;
        }
        return data;
    }

    @Api({RequestMethodType.GET})
    public Map<?, ?> loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        HashMap<String, Boolean> data = new HashMap<>();
        for (Cookie c : cookies){
            if (c.getName().compareTo("token") == 0){
                HttpSession session = request.getSession();
                session.removeAttribute(c.getValue());
                c.setPath("/");
                c.setMaxAge(0);
                response.addCookie(c);
            }
        }
        data.put("loginOutStatus", true);
        return data;
    }

    @Api({RequestMethodType.GET})
    public Map<?, ?> showInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        HashMap<String, Object> map = checkLogin(request, response);
        boolean isLogin = (boolean) map.get("loginStatus");

        HashMap<String, Object> data = new HashMap<>();
        data.put("loginStatus", isLogin);
        if (!isLogin) return data;
        UserEntity user = (UserEntity) map.get("user");
        HashMap<String, String> userinfo = uisi.findByUserId(user.getId());
        data.put("userData", userinfo);
        data.put("userName", user.getUsername());
        return data;
    }

    @Api({RequestMethodType.POST, RequestMethodType.GET})
    public Map<?, ?> editInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> map = checkLogin(request, response);
        boolean isLogin = (boolean) map.get("loginStatus");

        HashMap<String, Object> data = new HashMap<>();
        data.put("loginStatus", isLogin);
        if (!isLogin) return data;
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        UserEntity user = (UserEntity) map.get("user");
        data.put("updateStatus", uisi.updateByUserId(user.getId(),phone,address));
        return data;
    }

    @Api({RequestMethodType.POST, RequestMethodType.GET})
    public Map<?, ?> editInfoWithToken(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("csrf_token");
        HttpSession session = request.getSession();
        String v_token = session.getAttribute("csrf_token") != null ? String.valueOf(session.getAttribute("csrf_token")) : null;
        HashMap<String, Boolean> data = new HashMap<>();
        if (token == null || !token.equals(v_token)){
            data.put("VerificationStatus", false);
            return data;
        }
        return editInfo(request,response);
    }

    private HashMap<String, Object> checkLogin(HttpServletRequest request, HttpServletResponse response){
        HashMap<String, Object> data = new HashMap<>();
        boolean isLogin = false;
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().compareTo("token") == 0) {
                HttpSession session = request.getSession();
                UserEntity user = (UserEntity) session.getAttribute(c.getValue());
                isLogin = true;
                data.put("user", user);
            }
        }
        data.put("loginStatus", isLogin);
        return data;
    }

    @Api({RequestMethodType.GET})
    public Map<?, ?> getToken(HttpServletRequest request, HttpServletResponse response){
        String token = udi.getVerificationToken();
        HttpSession session = request.getSession();
        session.setAttribute("csrf_token", token);
        HashMap<String, String> data = new HashMap<>();
        data.put("csrf_token", token);
        return data;
    }
}
