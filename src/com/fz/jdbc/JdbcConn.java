package com.fz.jdbc;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Author:fz
 * Date:2022/8/30 14:24
 * java连接mysql的5种方式
 */
public class JdbcConn {

    //方式1
    @Test
    public void connect01()throws SQLException{
        Driver driver = new com.mysql.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/fz_db03";
        //将用户名和密码放入到Properties对象
        Properties properties = new Properties();
        //说明，user和password是规定好，后面的值根据实际情况写
        properties.setProperty("user","root");
        properties.setProperty("password","root");

        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }

    //方式2
    @Test
    public void connect02() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //使用反射加载Driver类,动态加载，更加灵活
        Class<?>aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();
        String url  = "jdbc:mysql://localhost:3306/fz_db03";
        //将用户名和密码放入到Properties对象
        Properties properties = new Properties();
        //说明，user和password是规定好，后面的值根据实际情况写
        properties.setProperty("user","root");
        properties.setProperty("password","root");

        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }
    //方式3 使用DriverManager 替代 Driver 进行统一管理
    @Test
    public void connect03() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        //使用反射加载Driver
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver)aClass.newInstance();
        //创建 url和user 和password
        String url = "jdbc:mysql://localhost:3306/fz_db03";
        String user = "root";
        String password = "root";
        DriverManager.registerDriver(driver);//注册Driver驱动
        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println(connection);
    }
    //方式4：使用Class.forName，自动完成注册驱动(推荐使用)
    @Test
    public void connect04() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        //创建 url和user 和password
        String url = "jdbc:mysql://localhost:3306/fz_db03";
        String user = "root";
        String password = "root";

        /**static {
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            } catch (SQLException var1) {
                throw new RuntimeException("Can't register driver!");
            }
        }
        */
        Connection connection = DriverManager.getConnection(url,user,password);
        System.out.println(connection);
    }
    //方式5：在方式4的基础上改进，增加配置文件，让连接更灵活
    @Test
    public void connect05() throws IOException, ClassNotFoundException, SQLException {
        //通过properties对象获取配置文件的信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }



  }

