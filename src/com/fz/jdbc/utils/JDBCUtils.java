package com.fz.jdbc.utils;

import com.mysql.jdbc.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Author:fz
 * Date:2022/11/13 20:59
 * 这是一个工具类，要完成mysql的连接和关闭
 */
public class JDBCUtils {
  //定义相关的属性,因为只需要一份，做成static
  private static String user;
  private static String password;
  private static String url;
  private static String driver;

  //在static代码块中初始化
  static{

    try {
      Properties properties = new Properties();
      properties.load(new FileInputStream("src\\mysql.properties"));
      user = properties.getProperty("user");
      password = properties.getProperty("password");
      url = properties.getProperty("url");
      driver = properties.getProperty("driver");
    } catch (IOException e) {
      //1.将编译异常转成运行异常
      //2.这是调用者，可以选择捕获该异常，也可以选择默认处理该异常
      throw new RuntimeException(e);
    }

  }
  //连接数据库，返回Connection
  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url,user,password);
  }

  //关闭相关资源
  //1.ResultSet结果集
  //2.Statement 或者PreparedStatement
  //3.Connection
  //4.如果需要关闭资源，就传入对象，否则传入null
  public static void close(ResultSet set, Statement statement, Connection connection){
    try {
      if (set!=null){
        set.close();
      }
      if (statement!=null){
        statement.close();
      }
      if (connection!=null){
        connection.close();
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
