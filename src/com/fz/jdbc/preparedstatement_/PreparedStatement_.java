package com.fz.jdbc.preparedstatement_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * Author:fz
 * Date:2022/11/13 17:59
 */

public class PreparedStatement_ {
  public static void main(String[] args) throws Exception {
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
    //SQL语句的?相当于占位符
    String sql = "select name,pwd from admin where name= ? and pwd = ?";
    //preparedStatement 对象实现了PreparedStatement接口的实现类的对象
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1,admin_name);
    preparedStatement.setString(2,admin_pwd);

    //执行select语句，使用executeQuery
    ResultSet resultSet = preparedStatement.executeQuery();
    if (resultSet.next()){//如果查询到一条记录，说明该管理员存在
      System.out.println("恭喜，登录成功");

    }else {
      System.out.println("对不起，登录失败");
    }
    resultSet.close();
    preparedStatement.close();
    connection.close();
  }

}
