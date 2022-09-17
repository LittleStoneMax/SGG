package com.atguigu.fruit.jdbc;
//JDBC sun发布的一个java程序和水浒库之间通信的规范(接口)
//各大数据库厂商去实现JDBC规范（实现类） 这些实现类打包成压缩包 即jar包
/*
1.添加jar
2.加载驱动
3.通过驱动管理器获取连接对象
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class demo01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Connection connection = DriverManager.getConnection(url, user, pwd);
        //3:准备三个参数 url user pwd
        String url = "jdbc:mysql://localhost:3306/fruitstock";
        String user = "root";
        String pwd = "123456";
        Connection conn = DriverManager.getConnection(url, user, pwd);
        System.out.println("conn = " + conn);
    }
}
