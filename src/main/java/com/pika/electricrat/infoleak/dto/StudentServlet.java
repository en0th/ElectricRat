package com.pika.electricrat.infoleak.dto;

import com.pika.electricrat.infoleak.bo.Impl.StudentServerImpl;
import com.pika.electricrat.web.Annotation.Api;
import com.pika.electricrat.web.Annotation.RequestMethodType;
import com.pika.electricrat.web.servlet.BaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/infoleak/*")
public class StudentServlet extends BaseServlet {
    StudentServerImpl ssi = new StudentServerImpl();
    @Api({RequestMethodType.POST})
    public Map<?, ?> studentLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long number = Long.parseLong(request.getParameter("number"));
        String password = request.getParameter("password");

        HashMap<String, Boolean> data = new HashMap<>();
        data.put("loginStatus", ssi.login(number,password));
        return data;
    }
}
