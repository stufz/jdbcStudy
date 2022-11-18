package com.fz.jdbc.statement_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * Author:fz
 * Date:2022/11/13 17:42
 */
public class Statement_ {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        //让用户输入管理员名和密码

        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入管理员的名字:");
        String admin_name = scanner.nextLine();//next()：当接收到空格或者'就是表示结束
        System.out.print("请输入管理员的密码:");
        String admin_pwd = scanner.nextLine();

        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        Statement statement = connection.createStatement();

        String sql = "select name,pwd from admin where name='"+admin_name+"' and pwd='"+admin_pwd+"'";
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()){//如果查询到一条记录，说明该管理员存在
            System.out.println("恭喜，登录成功");

        }else {
            System.out.println("对不起，登录失败");
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}
