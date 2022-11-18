package com.fz.jdbc.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Author:fz
 * Date:2022/11/14 14:15
 * 演示C3P0的使用
 */
public class C3P0_ {
  @Test
  //方式1：相关参数，在程序中指定user,url,password
  public void testC3P0_01() throws IOException, PropertyVetoException, SQLException {
    //1.创建一个数据源对象
    ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    //2.通过配置文件mysql.properties获取相关参数
    Properties properties = new Properties();
    properties.load(new FileInputStream("src\\mysql.properties"));
    String user = properties.getProperty("user");
    String password = properties.getProperty("password");
    String url = properties.getProperty("url");
    String driver = properties.getProperty("driver");

    //给数据源comboPolledDataSource设置相关的参数
    //注意：连接的管理是由comboPooledDataSource来管理
    comboPooledDataSource.setDriverClass(driver);
    comboPooledDataSource.setJdbcUrl(url);
    comboPooledDataSource.setUser(user);
    comboPooledDataSource.setPassword(password);

    //设置初始化连接数
    comboPooledDataSource.setInitialPoolSize(10);
    //最大连接数
    comboPooledDataSource.setMaxPoolSize(50);
    long start = System.currentTimeMillis();
    for (int i = 0; i < 5000; i++) {
      Connection connection = comboPooledDataSource.getConnection();
      //System.out.println("连接成功");
      connection.close();
    }
    long end = System.currentTimeMillis();
    System.out.println("使用连接池后的耗时="+(end-start));

  }
  //第二种方式，使用配置文件模板来完成
  //将C3P0提供的配置文件C3P0.config.xml拷贝到src目录下
  //该文件指定了连接数据库和连接池的配置
  @Test
  public void testC3P0_02() throws SQLException {
    ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
    long start = System.currentTimeMillis();
    for (int i = 0; i < 500000; i++) {
      Connection connection = comboPooledDataSource.getConnection();
      connection.close();
    }
    //System.out.println("连接成功");
    long end = System.currentTimeMillis();
    System.out.println("使用连接池后的耗时="+(end-start));//612
  }
}
