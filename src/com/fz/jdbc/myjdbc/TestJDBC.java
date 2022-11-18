package com.fz.jdbc.myjdbc;

/**
 * Author:fz
 * Date:2022/8/29 13:24
 */
public class TestJDBC {
  public static void main(String[] args) {
    JdbcInterface jdbcInterface = new MysqlJdbcImpl();
    jdbcInterface.getConnection();//通过接口来调用实现类[动态绑定机制]
    jdbcInterface.crud();
    jdbcInterface.close();

  }
}
