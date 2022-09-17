package com.atguigu.fruit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//添加操作
public class demo02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //通过驱动管理器获取连接对象 url:统一资源定位符 表示和数据库通信的地址
        String url = "jdbc:mysql://localhost:3306/fruitstock";
        String user = "root";
        String pwd = "123456";
        Connection conn = DriverManager.getConnection(url, user, pwd);
        System.out.println("conn = " + conn);
        //编写SQL语句
        String sql="insert into t_fruit values(0,?,?,?,?)";
        //创建预处理命令对象
        PreparedStatement psmt = conn.prepareStatement(sql);
        //填充参数
        psmt.setString(1,"葡萄");
        psmt.setInt(2,15);
        psmt.setInt(3,100);
        psmt.setString(4,"神奇水果");
        //执行更新(增删改) 返回影响行数
        int count = psmt.executeUpdate();
        System.out.println(count > 0 ? "添加成功":"添加失败");
        //释放资源
        psmt.close();
        conn.close();
    }
}
