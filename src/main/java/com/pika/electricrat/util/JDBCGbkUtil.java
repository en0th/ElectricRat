package com.pika.electricrat.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCGbkUtil {
    public static Connection ds;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties pro = new Properties();
            InputStream is = JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            pro.load(is);
            ds = DriverManager.getConnection(pro.getProperty("url"), pro.getProperty("username"), pro.getProperty("password"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return ds;
    }

    public static Statement getStatement() throws SQLException {
        return ds.createStatement();
    }
}
