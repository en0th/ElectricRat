package com.pika.electricrat.dir;

import com.pika.electricrat.unsafeupload.bo.Impl.FileServerImpl;
import com.pika.electricrat.util.ImageVerificationCode;
import com.pika.electricrat.web.Annotation.Api;
import com.pika.electricrat.web.Annotation.RequestMethodType;
import com.pika.electricrat.web.servlet.BaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

@WebServlet("/fileAction/*")
public class FileActionServlet extends BaseServlet {
    FileServerImpl fsi = new FileServerImpl();

    private String uploadPath(HttpServletRequest request){
        return request.getServletContext().getRealPath(FileServerImpl.UPLOAD_DIRECTORY);
    };

    public void getFile(HttpServletRequest request, HttpServletResponse response){
        String fileName = request.getParameter("fileName");
        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        response.setHeader("content-disposition", "attachment;filename=" + ((new ImageVerificationCode()).GetRandom(10) + suffix));
        try {
            FileInputStream in = new FileInputStream(uploadPath(request) + "\\" + fileName);
            ServletOutputStream out = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int length=-1;
            while((length=in.read(buffer))!=-1) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Api({RequestMethodType.GET})
    public Map<?, ?> showFiles(HttpServletRequest request, HttpServletResponse response){
        return fsi.getFiles();
    }
}
