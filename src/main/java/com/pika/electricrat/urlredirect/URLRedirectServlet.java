package com.pika.electricrat.urlredirect;

import com.pika.electricrat.web.servlet.BaseServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/redirect/*")
public class URLRedirectServlet extends BaseServlet {
    public void gotoURL(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = request.getParameter("url");
        String path = request.getContextPath();

        // 获得当前服务器基本地址 http://localhost:8080
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        if (url != null && url.startsWith(basePath) && url.contains(path)) {
            response.sendRedirect(url);
        } else {
            response.setStatus(404);
        }
    }
}
