package com.pika.electricrat.xxe;

import com.pika.electricrat.web.servlet.BaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

@WebServlet("/xxe/*")
public class XXEServlet extends BaseServlet {
    public void readXML(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result="";
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputStream ist = request.getInputStream();
            Document doc = db.parse(ist);
            String username = doc.getElementsByTagName("username").item(0).getTextContent();
            String password = doc.getElementsByTagName("password").item(0).getTextContent();
            int isLogin = username.equals("admin") && password.equals("123456") ? 1 : 0;
            result = String.format("<result><code>%d</code><msg>%s</msg></result>",isLogin,username);
        } catch (Exception e) {
            e.printStackTrace();
            result = String.format("<result><code>%d</code><msg>%s</msg></result>",3,e.getMessage());
        }
        response.setContentType("text/xml;charset=UTF-8");
        response.getWriter().append(result);
    }

    /*TODO
    *  提供模板xml*/
}
