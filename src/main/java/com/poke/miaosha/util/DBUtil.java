package com.poke.miaosha.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @ClassName DBUtil
 * @Description //TODO
 * @Author poke
 * @Date 2020/3/2 2:48 上午
 */
public class DBUtil {
    private static Properties props;

    static {
        try {
            InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("application.properties");
            props = new Properties();
            props.load(in);
            in.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() throws Exception{
        String url = props.getProperty("spring.datasource.url");
        String username = props.getProperty("spring.datasource.username");
        String password = props.getProperty("spring.datasource.password");
        String driver = props.getProperty("spring.datasource.driver-class-name");
        Class.forName(driver);
        return DriverManager.getConnection(url,username, password);
    }
}
