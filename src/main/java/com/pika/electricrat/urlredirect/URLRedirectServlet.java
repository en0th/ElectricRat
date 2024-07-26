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

        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        if (url != null && url.startsWith(basePath) && url.contains(path)) {
            response.sendRedirect(url);
        } else {
            response.sendError(500);
        }
    }
}
