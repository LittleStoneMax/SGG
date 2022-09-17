package com.atguigu.fruit.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

//添加操作
public class demo02Batch {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //如果要执行批处理任务 需要添加一个参数:rewriteBatchedStatements=true
        //通过驱动管理器获取连接对象 url:统一资源定位符 表示和数据库通信的地址
        String url = "jdbc:mysql://localhost:3306/fruitstock?rewriteBatchedStatements=true";
        String user = "root";
        String pwd = "123456";
        Connection conn = DriverManager.getConnection(url, user, pwd);
        //System.out.println("conn = " + conn);
        String sql="insert into t_fruit values(0,?,?,?,?)";
        PreparedStatement psmt = conn.prepareStatement(sql);
        for (int i = 0; i < 0; i++) {
            psmt.setString(1, "猕猴桃" + 1);
            psmt.setInt(2, 15);
            psmt.setInt(3, 100);
            psmt.setString(4, "神奇水果");
            //批处理操作:psmt.addBatch()
            psmt.addBatch();
            if(i%1000==0){
                psmt.executeUpdate();
                psmt.clearBatch();
            }
        }
        // 执行更新(增删改) 返回影响行数
        int[] count = psmt.executeBatch();
        for(int i = 0;i<count.length;i++){
            System.out.println(count[1]);
        }
//        System.out.println(Arrays.toString(count));
        //释放资源
        psmt.close();
        conn.close();
    }
}
