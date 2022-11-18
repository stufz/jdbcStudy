package com.fz.jdbc.datasource;

import com.fz.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Author:fz
 * Date:2022/11/14 13:48
 */
public class ConQuestion {
  @Test
  public void testCon() throws SQLException {
    System.out.println("开始连接");
    long start = System.currentTimeMillis();
    for (int i = 0; i < 5000; i++) {
      Connection connection = JDBCUtils.getConnection();

      JDBCUtils.close(null,null,connection);
    }
    long end = System.currentTimeMillis();
    System.out.println("传统方式5000次耗时="+(end-start));
  }
}
