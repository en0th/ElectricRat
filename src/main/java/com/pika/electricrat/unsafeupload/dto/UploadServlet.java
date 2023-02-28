package com.pika.electricrat.unsafeupload.dto;

import com.pika.electricrat.fileinclude.FileIncludeServlet;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
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
    @Api({RequestMethodType.POST})
    public Map<?, ?> imageWhiteList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part file = request.getPart("image_file");
        String fileName = file.getSubmittedFileName();
        for(String i : FileServerImpl.IMAGE_FILE_TYPE){
            if (file.getContentType().equals("image/"+i)){
                HashMap<String, Object> data= new HashMap<>();
                data.put("uploadStatus", false);
                return data;
            }
        }

        String suffixName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        for(String i : FileServerImpl.White_FILE_TYPE) {
            if (suffixName.equals(i)){
                return uploadFile(file, uploadPath(request));
            }
        }
        HashMap<String, Object> data= new HashMap<>();
        data.put("uploadStatus", false);
        return data;
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

    @Api({RequestMethodType.POST})
    public Map<?, ?> moveFile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> data= new HashMap<>();
        int fileID = Integer.parseInt(request.getParameter("fileID")); // 源文件路径
        String newFileName = request.getParameter("newFileName"); // 目标文件路径

        String sourceFilePath = fsi.getFilePathById(fileID); // 源文件路径
        String targetFilePath = request.getServletContext().getRealPath(FileIncludeServlet.FORBIDDEN_PATH)+"/"+newFileName; // 目标文件路径

        File sourceFile = new File(sourceFilePath);
        File targetFile = new File(targetFilePath);
        if (!sourceFile.exists()) {
            data.put("moveFileStatus", false);
            return data;
        }
        Path move = Files.move(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        assert move.equals(targetFile.toPath());
        boolean res = move.equals(targetFile.toPath());
        if (res){
            fsi.updateFilePath(fileID, newFileName, targetFilePath);
        }
        data.put("moveFileStatus", res);
        return data;
    }
    /*TODO
     *  压缩解压*/
}
