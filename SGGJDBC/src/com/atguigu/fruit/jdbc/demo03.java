package com.atguigu.fruit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//更新操作
public class demo03 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Fruit fruit = new Fruit(49,"猕猴桃",25,35,"猕猴桃啊");
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/fruitstock";
        String user = "root";
        String pwd = "123456";
        Connection conn = DriverManager.getConnection(url, user, pwd);
        System.out.println("conn = " + conn);
        String sql="update t_fruit set fname = ?,price = ?,fcount = ?,remark = ? where fid = ?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setString(1,fruit.getFname());
        psmt.setInt(2,fruit.getPrice());
        psmt.setInt(3,fruit.getFcount());
        psmt.setString(4,fruit.getRemark());
        psmt.setInt(5,fruit.getFid());
        //psmt.setString(1,fruit.getFname());
        int count = psmt.executeUpdate();
        System.out.println(count > 0 ? "修改成功" : "修改失败");
        psmt.close();
        conn.close();
    }
}
