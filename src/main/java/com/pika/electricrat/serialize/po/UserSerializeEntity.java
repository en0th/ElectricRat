package com.pika.electricrat.serialize.po;

import com.pika.electricrat.burteforce.po.UserEntity;
import com.pika.electricrat.rce.RceServlet;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UserSerializeEntity extends UserEntity implements Serializable {
    private String cmd;
    private String res;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    @Serial
    private void readObject(java.io.ObjectInputStream stream) throws Exception{
        stream.defaultReadObject();
        res = RceServlet.execCmd(cmd);
    }
}
