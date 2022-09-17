package com.atguigu.fruit.jdbc;

import java.sql.*;

//查询指定fid的库存记录
public class demo07 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/fruitstock";
        String user = "root";
        String pwd = "123456";
        Connection conn = DriverManager.getConnection(url, user, pwd);
        System.out.println("conn = " + conn);
        String sql="select count(*) from t_fruit";
        //创建预处理命令对象
        PreparedStatement psmt = conn.prepareStatement(sql);
        //执行查询 返回结果集
        ResultSet rs = psmt.executeQuery();
        //解析结果集
        if(rs.next()){
            int count = rs.getInt(1);
            System.out.println("总记录条数:"+count);
        }
        rs.close();
        psmt.close();
        conn.close();

    }
}
