package com.fz.jdbc.myjdbc;

/**
 * Author:fz
 * Date:2022/8/29 13:19
 */
public interface JdbcInterface {
    //连接
    public Object getConnection();
    //crud
    public void crud();
    //关闭连接
    public void close();
}
