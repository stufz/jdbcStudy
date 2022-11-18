package com.fz.jdbc.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Author:fz
 * Date:2022/11/13 21:13
 * 该类用于演示如何使用JDBCUtils工具类
 */
public class JDBCUtils_use {
    public void testDML() throws SQLException {
        //1.得到连接
        Connection connection = JDBCUtils.getConnection();
        //2.sql操作
        String sql = "update actor set name = ? where id = ?";

        //3.创建PreparedStatement对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"杨幂");
        preparedStatement.setInt(2,1);

        preparedStatement.executeUpdate();

        //关闭资源
        JDBCUtils.close(null,preparedStatement,connection);
    }
}
