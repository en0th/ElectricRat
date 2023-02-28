package com.pika.electricrat.fileinclude;

import com.pika.electricrat.web.servlet.BaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;
import java.net.URL;

@WebServlet("/fileInclude/*")
public class FileIncludeServlet extends BaseServlet {
    public static final String FORBIDDEN_PATH = "pages/fileinclude/templates";

    public void includeLocalFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/fileIncludeAction/IncludeServlet").forward(request, response);
    }

    public void includeRemotePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getParameter("url");
        if (url == null){
            response.setStatus(404);
            return;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        String line = null;
        while ((line = reader.readLine()) != null) {
            response.getWriter().println(line);
        }
        reader.close();
    }


}
