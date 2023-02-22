package com.pika.electricrat.burteforce.dto;

import com.mysql.cj.Session;
import com.pika.electricrat.burteforce.bo.Impl.UserServerImpl;
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

@WebServlet("/bruteforce/user/*")
public class UserServlet extends BaseServlet {
    UserServerImpl udi = new UserServerImpl();

    @Api({RequestMethodType.POST})
    public Map<?, ?> login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HashMap<String, Boolean> data = new HashMap<>();
        data.put("loginStatus", udi.login(username,password));
        return data;
    }
    @Api({RequestMethodType.GET})
    public Map<?, ?> getCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        HashMap<String, String> data = new HashMap<>();
        data.put("image", udi.getImageVerificationCode());
        return data;
    }
    @Api({RequestMethodType.POST})
    public Map<?, ?> loginTakeCode(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");

        HashMap<String, Boolean> data = new HashMap<>();
        Boolean v = udi.verificationImageCodeEasy(code);
        if (!v){
            data.put("VerificationStatus", false);
            return data;
        }
        data.put("VerificationStatus", true);
        data.put("loginStatus", udi.login(username,password));
        return data;
    }

    @Api({RequestMethodType.POST})
    public Map<?, ?> loginWithToken(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String token = request.getParameter("token");
        HttpSession session = request.getSession();
        String v_token = session.getAttribute("token") != null ? String.valueOf(session.getAttribute("token")) : null;
        HashMap<String, Boolean> data = new HashMap<>();
        if (token == null || !token.equals(v_token)){
            data.put("VerificationStatus", false);
            return data;
        }
        session.removeAttribute("token");
        data.put("VerificationStatus", true);
        data.put("loginStatus", udi.login(username,password));
        return data;
    }

    @Api({RequestMethodType.GET})
    public Map<?, ?> getToken(HttpServletRequest request, HttpServletResponse response){
        String token = udi.getVerificationToken();
        HttpSession session = request.getSession();
        session.setAttribute("token", token);
        HashMap<String, String> data = new HashMap<>();
        data.put("token", token);
        return data;
    }
}
