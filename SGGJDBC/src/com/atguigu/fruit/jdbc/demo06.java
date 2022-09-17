package com.atguigu.fruit.jdbc;

import java.sql.*;

//查询指定fid的库存记录
public class demo06 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/fruitstock";
        String user = "root";
        String pwd = "123456";
        Connection conn = DriverManager.getConnection(url, user, pwd);
        System.out.println("conn = " + conn);
        String sql="select * from t_fruit where fid = ?";
        //创建预处理命令对象
        PreparedStatement psmt = conn.prepareStatement(sql);
        //填充参数
        psmt.setInt(1,8);
        //执行查询 返回结果集
        ResultSet rs = psmt.executeQuery();
        //解析结果集
        if(rs.next()){
            int fid = rs.getInt(1);//读取当前第一列的数据
            //int fid = rs.getInt("fid");//取列名也可
            String fname = rs.getString("fname");
            int price = rs.getInt(3);
            int fcount = rs.getInt(4);
            String remark = rs.getString(5);
            Fruit fruit = new Fruit(fid,fname,price,fcount,remark);
            System.out.println("fruit = " + fruit);
        }

        rs.close();
        psmt.close();
        conn.close();

    }
}
