package com.fz.jdbc.transaction;

import com.fz.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Author:fz
 * Date:2022/11/13 23:19
 * 演示JDBC中使用事务
 */
public class Transaction_ {
        @Test
        public void noTransaction() throws SQLException {
            //1.得到连接
            Connection connection = JDBCUtils.getConnection();
            //2.sql操作
            String sql = "update account set balance = balance-100 where id = 1";
            String sql2 = "update account set balance = balance+200 where id = 2";
            //3.创建PreparedStatement对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();//默认执行一条就提交一条，所以引出事务

            //int i = 1/0;
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql2);
            preparedStatement1.executeUpdate();

            //关闭资源
            preparedStatement.close();
            preparedStatement1.close();
            connection.close();
        }
        @Test
        public void hasTransaction() throws SQLException {
            Connection connection = null;
            String sql = "update account set balance = balance-100 where id = 1";
            String sql2 ="update account set balance = balance+200 where id = 2";

            PreparedStatement preparedStatement = null;
            try {
                //1.得到连接
                connection = JDBCUtils.getConnection();//connection默认自动提交
                //把conncetion设置为不自动提交
                connection.setAutoCommit(false);//开启了事务
                //2.sql操作
                //3.创建PreparedStatement对象
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();//默认执行一条就提交一条，所以引出事务
                //int i = 1/0;
                preparedStatement = connection.prepareStatement(sql2);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                //这里可以进行回滚，即撤销执行的SQL
                //默认回滚到事务开始的状态
                System.out.println("执行发生了异常，撤销执行的sql");
                try{
                    connection.rollback();
                }catch (SQLException throwables){
                    throwables.printStackTrace();
                }
                e.printStackTrace();
            } finally {
                JDBCUtils.close(null,preparedStatement,connection);
            }


        }


    }





