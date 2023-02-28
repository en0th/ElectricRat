package com.pika.electricrat.unsafeupload.po;

import com.pika.electricrat.web.db.BaseEntity;

public class FileEntity implements BaseEntity {
    private int id;
    private String fileName;
    private String fileType;
    private String filePath;
    private long create_time;
    private long fileSize;
    private String unid;

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public FileEntity() {
    }

    public FileEntity(int id, String fileName, String filePath) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public FileEntity(int id, String filePath) {
        this.id = id;
        this.filePath = filePath;
    }

    public FileEntity(int id) {
        this.id = id;
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid;
    }

    public FileEntity(String fileName, String fileType, String filePath, long create_time, long fileSize, String unid) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
        this.create_time = create_time;
        this.fileSize = fileSize;
        this.unid = unid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "FileEntity{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", filePath='" + filePath + '\'' +
                ", create_time=" + create_time +
                '}';
    }

    @Override
    public String getSqlTableName() {
        return "sys_upload_files";
    }
}
