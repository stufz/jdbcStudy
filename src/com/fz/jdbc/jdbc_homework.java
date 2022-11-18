package com.fz.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Author:fz
 * Date:2022/11/13 16:20
 * 使用方式5
 * 1.创建news表
 * 2.使用jdbc添加5条数据
 * 3.修改id=1的记录，将connect改成一个新的消息
 * 4.删除id=3的记录
 */
public class jdbc_homework {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        //String sql = "insert into news values(null,'学习使我快乐')";
        //String sql = "insert into news values(null,'我爱学Java')";
        //String sql = "insert into news values(null,'我现在在学jdbc')";
        //String sql = "insert into news values(null,'我接下来要学习Javaweb')";
        //String sql = "insert into news values(null,'每天进步一点点')";
        // String sql = "update news set content = '我现在在写jdbc作业'where id=1";
        String sql = "delete from news where id=3";
        Statement statement = connection.createStatement();
        int rows = statement.executeUpdate(sql);
        System.out.println(rows>0?"成功":"失败");

        statement.close();
        connection.close();
    }
}
