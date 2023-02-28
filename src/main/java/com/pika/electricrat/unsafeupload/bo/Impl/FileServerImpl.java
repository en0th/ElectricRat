package com.pika.electricrat.unsafeupload.bo.Impl;

import com.pika.electricrat.unsafeupload.bo.FileServer;
import com.pika.electricrat.unsafeupload.dao.Impl.FileDaoImpl;
import com.pika.electricrat.unsafeupload.po.FileEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class FileServerImpl implements FileServer {
    FileDaoImpl fdi = new FileDaoImpl();
    // 上传文件存储目录
    public static final String UPLOAD_DIRECTORY = "/upload";
    public static final String[] IMAGE_FILE_TYPE = {"png", "jpg", "gif"};
    public static final String[] BLACK_FILE_TYPE = {".html", ".htm", ".phtml", ".jsp", ".jspa", ".jspx", ".jsw", ".jsv", ".jspf", ".jtml"};
    public static final String[] White_FILE_TYPE = {".png", ".jpg", ".gif"};

    // 上传配置
    public static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    public static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    public static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    public HashMap<String, Object> uploadFile(FileEntity entity) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        HashMap<String, Object> data = new HashMap<>();
        FileEntity fileObject = fdi.add(entity);
        if (fileObject != null){
            data.put("id", fileObject.getId());
            data.put("url",UPLOAD_DIRECTORY + "/" + fileObject.getFileName());
            data.put("type", fileObject.getFileType());
            data.put("name", fileObject.getFileName());
        }
        return data;
    }

    public HashMap<String, Object> getFiles(){
        HashMap<String, Object> data = new HashMap<>();
        data.put("data", fdi.queryAllMap());
        return data;
    }

    public String getFilePathById(int id){
        return fdi.queryById(new FileEntity(id)).getFilePath();
    }

    public boolean updateFilePath(int id,String newFileName, String filePath){
        return fdi.update(new FileEntity(id,newFileName,filePath)) != null;
    }
}
