package com.pika.electricrat.serialize.po;

import org.apache.commons.collections.Transformer;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class UserSerializeEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -5286038984112444851L;
    protected static String res;

    protected final Transformer valueTransformer;

    public UserSerializeEntity(Transformer valueTransformer) {
        this.valueTransformer = valueTransformer;
    }

    @Serial
    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException, InterruptedException {
        stream.defaultReadObject();
        // 为了做回显，正常业务不会这么写。
        Process p = (Process) checkValue(new Object());
        InputStream is = p.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        String line;
        StringBuilder text = new StringBuilder();
        while((line = reader.readLine())!= null){
            text.append(line).append("\n");
        }
        p.waitFor();
        is.close();
        reader.close();
        p.destroy();
        res =  text.toString();
    }

    protected Object checkValue(Object value){
        return valueTransformer.transform(value);
    }

    @Override
    public String toString() {
        return "UserSerializeEntity{"+res+"}";
    }
}
