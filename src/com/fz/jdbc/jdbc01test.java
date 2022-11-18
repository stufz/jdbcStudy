package com.fz.jdbc;

import com.mysql.jdbc.Driver;//刚开始引入的是java.sql.Driver报错

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Author:fz
 * Date:2022/11/13 15:18
 */
public class jdbc01test {
  public static void main(String[] args) throws SQLException {
    //1.加载Driver驱动
    Driver driver = new Driver();
    //2.创建连接
    String url = "jdbc:mysql://localhost:3306/fz_db03";
    Properties properties = new Properties();
    properties.setProperty("user","root");
    properties.setProperty("password","root");

    // Connection connection = new Connection(url,properties);语句写错,应该用driver的connect方法
    Connection connection = driver.connect(url,properties);

    //执行sql
    String sql = "insert into actor values(null,'刘诗诗','女','1985-01-09','6096')";
    //Statement statement = new Statement(); 语句写错，应该用connect的createStatement方法
    //用于执行静态SQL语句并返回其生成的结果的对象。
    Statement statement = connection.createStatement();
    int rows = statement.executeUpdate(sql);
    System.out.println(rows>0?"成功":"失败");

  }
}
