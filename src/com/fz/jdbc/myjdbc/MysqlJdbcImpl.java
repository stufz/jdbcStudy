package com.fz.jdbc.myjdbc;

/**
 * Author:fz
 * Date:2022/8/29 13:21
 *
 * mysql厂商开发
 */
public class MysqlJdbcImpl implements JdbcInterface{

    @Override
    public Object getConnection() {
        System.out.println("得到mysql连接");
        return null;
    }

    @Override
    public void crud() {
        System.out.println("实现增删改查");
    }

    @Override
    public void close() {
        System.out.println("关闭mysql连接");
    }
}
