package com.pika.electricrat.fileinclude;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/fileIncludeAction/IncludeServlet")
public class IncludeServlet extends HttpServlet {
    public String currentFilePath(HttpServletRequest request){
        return request.getServletContext().getRealPath(FileIncludeServlet.FORBIDDEN_PATH);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 如果是普通文件就直接获取，如果是jsp就进行getRequestDispatcher include请注意传入的路径不能超出项目路径。
        response.setContentType("text/html;charset=utf-8");
        String filePath = request.getParameter("filePath");
        if (filePath == null){
            response.setStatus(404);
            return;
        }
        String realFilePath = currentFilePath(request) + File.separator + filePath;
        File file = new File(realFilePath);
        if (file.getName().endsWith(".jsp")) {
            String name = "/" + FileIncludeServlet.FORBIDDEN_PATH + "/" + filePath;
            request.getRequestDispatcher(name).include(request, response);
        } else {
            FileInputStream fis = new FileInputStream(file);
            OutputStream out = response.getOutputStream();

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            fis.close();
            out.flush();
            out.close();
        }
    }
}
