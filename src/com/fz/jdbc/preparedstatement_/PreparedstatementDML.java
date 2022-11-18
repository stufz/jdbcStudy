package com.fz.jdbc.preparedstatement_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

/**
 * Author:fz
 * Date:2022/11/13 20:28
 */
public class PreparedstatementDML {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入管理员的名字:");
        String admin_name = scanner.nextLine();//next()：当接收到空格或者'就是表示结束
        System.out.print("请输入管理员新的密码:");
        String admin_pwd = scanner.nextLine();

        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        //SQL语句的?相当于占位符
        //String sql = "select name,pwd from admin where name= ? and pwd = ?";
        String sql = "delete from admin where name = ?";
        //preparedStatement 对象实现了PreparedStatement接口的实现类的对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //preparedStatement.setString(1,admin_pwd);
        preparedStatement.setString(1,admin_name);

        int rows = preparedStatement.executeUpdate();
        System.out.println(rows>0?"成功":"失败");
        preparedStatement.close();
        connection.close();
    }
}
