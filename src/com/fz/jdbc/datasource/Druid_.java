package com.fz.jdbc.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Author:fz
 * Date:2022/11/14 15:46
 * 测试druid的使用
 */
public class Druid_ {
    public static void main(String[] args) throws Exception {
        //1.加入druid jar包
        //2.加入配置文件 druid.properties,将该文件拷贝项目的src目录
        //3.创建Properties对象，读取配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\druid.properties"));
        //4.创建一个指定参数的数据库连接池
        long start = System.currentTimeMillis();
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        for (int i = 0; i < 500000; i++) {
            Connection connection = dataSource.getConnection();
            //System.out.println("连接成功");
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("总耗时="+(end-start));//851
    }

}
