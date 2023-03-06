package com.pika.electricrat.spel;

import com.pika.electricrat.web.servlet.BaseServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@WebServlet("/spel/*")
public class SpelServlet extends BaseServlet {
    public void spelView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InterruptedException {
        String apply = request.getHeader("apply");
//        String spel = "T(java.lang.Runtime).getRuntime().exec("calc")";
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(apply);

        // 为了更好的演示，这里将执行的结果返回给前端
        Process p = (Process) expression.getValue();
        assert p != null;
        InputStream is = p.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        String line;
        StringBuilder text = new StringBuilder();
        while((line = reader.readLine())!= null){
            text.append(line).append("\n");
        }
        p.waitFor();
        is.close();
        reader.close();
        p.destroy();
        response.getWriter().append(text.toString());
    }
}
