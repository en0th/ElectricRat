package com.pika.electricrat.burteforce.po;

import com.pika.electricrat.web.db.BaseEntity;

public class ImageCodeEntity implements BaseEntity {
    private String id;
    private long create_time;
    private long expire_time;
    private String text;

    public ImageCodeEntity() {
    }

    public ImageCodeEntity(String text) {
        this.text = text;
    }

    public ImageCodeEntity(String id, long create_time, long expire_time, String text) {
        this.id = id;
        this.create_time = create_time;
        this.expire_time = expire_time;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public long getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(long expire_time) {
        this.expire_time = expire_time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ImageCodeEntity{" +
                "id='" + id + '\'' +
                ", create_time=" + create_time +
                ", expire_time=" + expire_time +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public String getSqlTableName() {
        return "sys_image_code";
    }
}
