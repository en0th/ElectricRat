package com.pika.electricrat.serialize;

import com.pika.electricrat.serialize.po.UserSerializeEntity;
import com.pika.electricrat.web.servlet.BaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;
import java.util.Base64;


@WebServlet("/serializeAction/*")
public class SerializeServlet extends BaseServlet {

    public void serializeView(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/plan;charset=utf-8");
        try {
            Cookie hasCookie = checkCookie(request);
            if (hasCookie != null){
                if (hasCookie.getValue().equals("deleteMe")){
                    response.getWriter().append("请更新 Cookie rememberMe 并进行请求。");
                    return;
                }
                byte[] decode = Base64.getDecoder().decode(hasCookie.getValue());

                ByteArrayInputStream bytes = new ByteArrayInputStream(decode);
                ObjectInputStream in = new ObjectInputStream(bytes);
                Object o = in.readObject();
                response.getWriter().append(((UserSerializeEntity)o).getRes());
                in.close();
            } else {
                response.getWriter().append("请携带 Cookie rememberMe 进行请求。");
            }
        } catch (Exception e){
            e.printStackTrace();
            response.setStatus(500);
        }
    }

    public void deserialize(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plan;charset=utf-8");
        String cmd = request.getParameter("cmd");
        ServletOutputStream out = response.getOutputStream();
        UserSerializeEntity entity = new UserSerializeEntity();
        entity.setCmd(cmd!=null?cmd:"whoami");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(outputStream);
        oo.writeObject(entity);
        byte[] res = Base64.getEncoder().encode(outputStream.toByteArray());
        out.write(res);
    }

    public void getRememberMe(HttpServletRequest request, HttpServletResponse response) {
        Cookie hasCookie = checkCookie(request);
        if (hasCookie != null)return;
        Cookie cookie = new Cookie("rememberMe", "deleteMe");
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public void setRememberMe(HttpServletRequest request, HttpServletResponse response) {
        String obj = request.getParameter("obj");
        Cookie hasCookie = checkCookie(request);
        if (hasCookie != null){
            hasCookie.setValue(obj);
            response.addCookie(hasCookie);
        }
    }

    public Cookie checkCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            for (Cookie i : cookies) {
                System.out.println(i.getName());
                if (i.getName().equals("rememberMe") && i.getValue() != null && i.getValue().length() > 0) {
                    return i;
                } else {
                    return null;
                }
            }
        }
        return null;
    }
}
