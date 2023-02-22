package com.pika.electricrat.spel;

import com.pika.electricrat.web.servlet.BaseServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.io.IOException;

@WebServlet("/spel/*")
public class SpelServlet extends BaseServlet {
    public void spelView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String apply = request.getHeader("apply");
//        String spel = "T(java.lang.Runtime).getRuntime().exec("calc")";
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(apply);
        System.out.println(expression.getValue().toString());
        response.getWriter().append(expression.getValue().toString());
    }
}
