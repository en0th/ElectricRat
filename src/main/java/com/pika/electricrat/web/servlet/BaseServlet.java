package com.pika.electricrat.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pika.electricrat.web.Annotation.Api;
import com.pika.electricrat.web.Annotation.RequestMethodType;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String method_name = uri.substring((uri.lastIndexOf("/")) + 1);
        try {
            Method method = this.getClass().getMethod(method_name, HttpServletRequest.class, HttpServletResponse.class);
            if(method.isAnnotationPresent(Api.class)){
                String reqMethod = req.getMethod();
                Api api = method.getAnnotation(Api.class);
                for (RequestMethodType r : api.value()){
                    if (reqMethod.equals(String.valueOf(r))){
                        Map<?,?> data = (Map<?,?>) method.invoke(this, req, resp);
                        resp.setContentType("application/json;charset=utf-8");
                        ObjectMapper objectMapper = new ObjectMapper();
                        resp.getWriter().write(objectMapper.writeValueAsString(data));
                        return;
                    }
                }
                resp.setStatus(400);
                return;
            } else {
                method.invoke(this, req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
        }
    }
}
