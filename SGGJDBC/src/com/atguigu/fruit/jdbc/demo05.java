package com.atguigu.fruit.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//查询全部库存
public class demo05 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/fruitstock";
        String user = "root";
        String pwd = "123456";
        Connection conn = DriverManager.getConnection(url, user, pwd);
        System.out.println("conn = " + conn);
        String sql="select * from t_fruit";
        PreparedStatement psmt = conn.prepareStatement(sql);
        //执行查询 返回结果集
        ResultSet rs = psmt.executeQuery();
        //解析结果集
        List<Fruit> fruitList = new ArrayList<>();
        while(rs.next()){
            int fid = rs.getInt(1);//读取当前第一列的数据
            //int fid = rs.getInt("fid");//取列名也可
            String fname = rs.getString("fname");
            int price = rs.getInt(3);
            int fcount = rs.getInt(4);
            String remark = rs.getString(5);
            Fruit fruit = new Fruit(fid,fname,price,fcount,remark);
            fruitList.add(fruit);
        }

        rs.close();
        psmt.close();
        conn.close();
        fruitList.forEach(System.out::println);
    }
}
