package com.pika.electricrat.xss.dto;

import com.pika.electricrat.burteforce.bo.Impl.UserServerImpl;
import com.pika.electricrat.web.Annotation.Api;
import com.pika.electricrat.web.Annotation.RequestMethodType;
import com.pika.electricrat.web.servlet.BaseServlet;
import com.pika.electricrat.xss.bo.impl.CommentServerImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/xss/*")
public class XssServlet extends BaseServlet {
    UserServerImpl udi = new UserServerImpl();
    CommentServerImpl csi = new CommentServerImpl();

    @Api({RequestMethodType.POST})
    public Map<?, ?> login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HashMap<String, Boolean> data = new HashMap<>();
        Boolean login_res = udi.login(username, password);
        data.put("loginStatus", login_res);
        if (login_res){
            Cookie cookie = new Cookie("token", UUID.randomUUID().toString());
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return data;
    }

    @Api({RequestMethodType.GET})
    public Map<?, ?> loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        HashMap<String, Boolean> data = new HashMap<>();
        for (Cookie c : cookies){
            if (c.getName().compareTo("token") == 0){
                c.setPath("/");
                c.setMaxAge(0);
                response.addCookie(c);
            }
        }
        data.put("loginOutStatus", true);
        return data;
    }

    @Api({RequestMethodType.POST})
    public Map<?, ?> commitComment(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        String content = request.getParameter("content");
        String author = request.getParameter("author");
        Boolean status = author != null ? csi.addComment(content, author) : csi.addComment(content);
        HashMap<String, Boolean> data = new HashMap<>();
        data.put("commitCommentStatus", status);
        return data;
    }

    @Api({RequestMethodType.GET})
    public Map<?, ?> showAllComment(HttpServletRequest request, HttpServletResponse response){
        HashMap<String, HashMap<Integer, String>> data = new HashMap<>();
        data.put("comments", csi.getAllComment());
        return data;
    }

    @Api({RequestMethodType.GET})
    public Map<?, ?> deleteComment(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
        Boolean status = csi.deleteCommentByID(id);
        HashMap<String, Boolean> data = new HashMap<>();
        data.put("deleteCommentStatus", status);
        return data;
    }

    public void getMarkData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String content = request.getParameter("content");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("我不在乎你输入的是什么，就算是：" + content);
    }

    @Api({RequestMethodType.GET})
    public Map<?, ?> showAllCommentByMap(HttpServletRequest request, HttpServletResponse response){
        HashMap<String, List<Map<String, Object>>> data = new HashMap<>();
        data.put("comments", csi.getAllCommentList());
        return data;
    }
}
