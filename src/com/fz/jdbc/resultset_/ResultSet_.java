package com.fz.jdbc.resultset_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * Author:fz
 * Date:2022/11/13 17:15
 */
public class ResultSet_ {
  public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
    Properties properties = new Properties();
    properties.load(new FileInputStream("src\\mysql.properties"));
    String user = properties.getProperty("user");
    String password = properties.getProperty("password");
    String driver = properties.getProperty("driver");
    String url = properties.getProperty("url");
    Class.forName(driver);
    Connection connection = DriverManager.getConnection(url, user, password);

    Statement statement = connection.createStatement();

    String sql = "select id,name,sex,borndate from actor";
    //执行给定的SQL语句，该语句返回单个ResultSet对象
    ResultSet resultSet = statement.executeQuery(sql);

    //使用while循环取出数据
    while (resultSet.next()){//让光标向后移动，如果没有更多行，返回false
      int id = resultSet.getInt(1);//获取该行的第一列
      String name = resultSet.getString(2);
      String sex = resultSet.getString(3);
      Date date = resultSet.getDate(4);
      System.out.println(id+'\t'+name+'\t'+sex+'\t'+date);

    }

    resultSet.close();
    statement.close();
    connection.close();

  }
}
