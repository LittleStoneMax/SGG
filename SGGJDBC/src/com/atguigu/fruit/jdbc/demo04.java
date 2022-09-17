package com.atguigu.fruit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//删除操作
public class demo04 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/fruitstock";
        String user = "root";
        String pwd = "123456";
        Connection conn = DriverManager.getConnection(url, user, pwd);
        System.out.println("conn = " + conn);
        String sql="delete from t_fruit where fid = ?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1,49);
        //psmt.setString(1,fruit.getFname());
        int count = psmt.executeUpdate();
        System.out.println(count > 0 ? "删除成功" : "删除失败");
        psmt.close();
        conn.close();
    }



}
