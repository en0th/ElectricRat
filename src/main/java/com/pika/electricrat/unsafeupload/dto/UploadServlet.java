package com.pika.electricrat.unsafeupload.dto;

import com.pika.electricrat.unsafeupload.bo.Impl.FileServerImpl;
import com.pika.electricrat.unsafeupload.po.FileEntity;
import com.pika.electricrat.util.ImageVerificationCode;
import com.pika.electricrat.web.Annotation.Api;
import com.pika.electricrat.web.Annotation.RequestMethodType;
import com.pika.electricrat.web.servlet.BaseServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/uploadAction/*")
@MultipartConfig
public class UploadServlet extends BaseServlet {
    FileServerImpl fsi = new FileServerImpl();
    private String uploadPath(HttpServletRequest request){
        return request.getServletContext().getRealPath(FileServerImpl.UPLOAD_DIRECTORY);
    };

    @Api({RequestMethodType.POST})
    public Map<?, ?> image(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return uploadFile(request.getPart("image_file"), uploadPath(request));
    }

    @Api({RequestMethodType.POST})
    public Map<?, ?> imageMIME(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part file = request.getPart("image_file");
        for(String i : FileServerImpl.IMAGE_FILE_TYPE){
            if (file.getContentType().equals("image/"+i)){
                return uploadFile(file, uploadPath(request));
            }
        }
        HashMap<String, Object> data= new HashMap<>();
        data.put("uploadStatus", false);
        return data;
    }
    @Api({RequestMethodType.POST})
    public Map<?, ?> imageBlackList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part file = request.getPart("image_file");
        String fileName = file.getSubmittedFileName();
//        String suffixName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println(suffixName);
        for(String i : FileServerImpl.BLACK_FILE_TYPE) {
            if (suffixName.equals(i)){
                HashMap<String, Object> data= new HashMap<>();
                data.put("uploadStatus", false);
                return data;
            }
        }
        return uploadFile(file, uploadPath(request));
    }
    private HashMap<String, Object> uploadFile(Part imageFile,String filePath){
        HashMap<String, Object> data= new HashMap<>();
        try {
            String fileName = imageFile.getSubmittedFileName();
            long fileSize = imageFile.getSize();
            if(fileSize > FileServerImpl.MAX_FILE_SIZE){data.put("uploadStatus", false);return data;}
            String fileType = imageFile.getContentType();

            File file = new File(filePath);
            if (!file.exists() && !file.isDirectory()){
                file.mkdir();
            }

            imageFile.write(filePath+"\\"+fileName);
            HashMap<String, Object> fileObject = fsi.uploadFile(new FileEntity(fileName, fileType, (filePath+"\\"+fileName),
                    System.currentTimeMillis(), fileSize, (new ImageVerificationCode()).GetRandom(8)));
            if (fileObject.isEmpty()){
                data.put("uploadStatus", false);
                return data;
            }
            data.put("file", fileObject);
            data.put("uploadStatus", true);
        } catch (Exception e){
            data.put("uploadStatus", false);
            data.put("msg", e.getMessage());
        }
        return data;
    }
    /*TODO
    *  压缩解压*/

}
