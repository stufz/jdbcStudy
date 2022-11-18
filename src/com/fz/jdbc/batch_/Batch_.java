package com.fz.jdbc.batch_;

import com.fz.jdbc.utils.JDBCUtils;
import org.junit.Test;
import org.junit.internal.builders.JUnit3Builder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Author:fz
 * Date:2022/11/14 11:08
 * 演示Java中的批量处理sql语句
 */
public class Batch_ {
  @Test
  //传统方法，添加5000条数据到admin2
  public void noBatch() throws Exception {
    Connection connection = JDBCUtils.getConnection();
    String sql = "insert into admin2 values(null,?,?)";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    System.out.println("开始执行");
    long start = System.currentTimeMillis();
    for (int i = 0; i < 5000; i++) {
      preparedStatement.setString(1,"jack"+i);
      preparedStatement.setString(2,"666");
      preparedStatement.executeUpdate();
    }
    long end = System.currentTimeMillis();
    System.out.println("传统方法耗时="+(end-start));//112489

    JDBCUtils.close(null,preparedStatement,connection);
  }
  @Test
  //使用批量方式添加数据
  public void batch() throws Exception{
    Connection connection = JDBCUtils.getConnection();
    String sql = "insert into admin2 values(null,?,?)";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    System.out.println("开始执行");
    long start = System.currentTimeMillis();
    for (int i = 0; i < 5000; i++) {
      preparedStatement.setString(1,"jack"+i);
      preparedStatement.setString(2,"666");
      //将sql语句加入到批处理包
      preparedStatement.addBatch();
      //当有1000条记录时再执行
      if((i+1)%1000==0){
        preparedStatement.executeBatch();
        //清空一次
        preparedStatement.clearBatch();
      }
      //preparedStatement.executeUpdate();
    }
    long end = System.currentTimeMillis();
    System.out.println("传统方法耗时="+(end-start));//547

    JDBCUtils.close(null,preparedStatement,connection);
  }
}
