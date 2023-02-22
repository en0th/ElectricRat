package com.pika.electricrat.xss.po;

import com.pika.electricrat.web.db.BaseEntity;

public class CommentEntity implements BaseEntity {
    private String content;
    private String author;
    private long create_time;
    private int id;

    public CommentEntity() {
    }

    public CommentEntity(int id) {
        this.id = id;
    }

    public CommentEntity(String content) {
        this.content = content;
    }

    public CommentEntity(String content, long create_time) {
        this.content = content;
        this.create_time = create_time;
    }

    public CommentEntity(String content, String author, long create_time) {
        this.content = content;
        this.author = author;
        this.create_time = create_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", create_time=" + create_time +
                ", id=" + id +
                '}';
    }

    @Override
    public String getSqlTableName() {
        return "sys_comments";
    }
}
