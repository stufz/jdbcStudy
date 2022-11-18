package com.fz.jdbc.utils;

import com.fz.jdbc.datasource.Actor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DBUtilsByDruid_use {
    @Test
    //使用apach-DBUtils工具类+druid完成对表的crud操作
    public void testQueryMany() throws SQLException {
        //1.得到连接
        Connection connection = JDBCUtilsByDruid.getConnection();
        //2.使用DBUtils类和接口，先引入DBUtils相关的jar
        //3.创建QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        //4.可以执行相关的方法，返回ArrayList
        String sql = "select * from actor where id >=?";
        //query就是执行一个sql语句，得到resultSet ----》封装到ArrayLisst集合返回
        //connection:连接
        //sql:要执行的sql语句
        //new BeanListHandler<>(Actor.class) 在将resultset取出--》Actor-->封装到ArrayList
        //底层使用反射机制 去获取Actor的属性，然后去封装
        //1 是给sql语句中的？赋值，是可变参数
        //底层得到的resultset，会在query后关闭，关闭preparedstatement
        List<Actor> list = queryRunner.query(connection, sql, new BeanListHandler<>(Actor.class), 1);
        for (Actor actor : list) {
            System.out.println(actor);
        }
        //释放资源
        JDBCUtilsByDruid.close(null, null, connection);
    }

    @Test
    //演示apache-dbutils_druid完成，返回的结果是单行记录
    public void testQueryOne() throws SQLException {
        //1.得到连接
        Connection connection = JDBCUtilsByDruid.getConnection();
        //2.使用DBUtils类和接口，先引入DBUtils相关的jar
        //3.创建QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        //4.可以执行相关的方法，返回ArrayList
        String sql = "select * from actor where id =?";

        //因为我们返回的单行记录，单个对象，使用的Handler是BeanHandler
        Actor actor = queryRunner.query(connection, sql, new BeanHandler<>(Actor.class), 1);
        System.out.println(actor);

        //释放资源
        JDBCUtilsByDruid.close(null, null, connection);
    }

    @Test
    //演示apache-dbutils_druid完成，返回的结果是单行单列记录，Object
    public void testQueryOneOne() throws SQLException {
        //1.得到连接
        Connection connection = JDBCUtilsByDruid.getConnection();
        //2.使用DBUtils类和接口，先引入DBUtils相关的jar
        //3.创建QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        //4.可以执行相关的方法，返回ArrayList
        String sql = "select name from actor where id =?";

        //因为我们返回的单行记录，单个对象，使用的Handler是BeanHandler
        Object obj = queryRunner.query(connection, sql, new ScalarHandler(), 1);
        System.out.println(obj);

        //释放资源
        JDBCUtilsByDruid.close(null, null, connection);


    }

    @Test
    //演示apache-dbutils+druid完成dml
    public void testdml() throws SQLException {
        //1.得到连接
        Connection connection = JDBCUtilsByDruid.getConnection();
        //2.使用DBUtils类和接口，先引入DBUtils相关的jar
        //3.创建QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        String sql = "update actor set name =?where id=?";
        //执行dml操作是queryRunner.update()

        //返回的rows表示受影响的行数，0表示无影响行数
        int rows = queryRunner.update(connection, sql, "杨幂", 1);
        System.out.println(rows > 0 ? "执行成功" : "执行失败");

        //释放资源
        JDBCUtilsByDruid.close(null, null, connection);
    }
}