package com.atguigu.fruit.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.base.BaseDAO;
import com.atguigu.fruit.pojo.Fruit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {

    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select *from t_fruit");
//        List<Fruit> fruitList = new ArrayList<>();
//        try {
//            conn = getConn();
//            String sql = "select *from t_fruit";
//            psmt = conn.prepareStatement(sql);
//            rs = psmt.executeQuery();
//            while (rs.next()) {
//                int fid = rs.getInt(1);//读取当前第一列的数据
//                //int fid = rs.getInt("fid");//取列名也可
//                String fname = rs.getString("fname");
//                int price = rs.getInt(3);
//                int fcount = rs.getInt(4);
//                String remark = rs.getString(5);
//                Fruit fruit = new Fruit(fid, fname, price, fcount, remark);
//                fruitList.add(fruit);
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            close(rs, psmt, conn);
//        }
//        return fruitList;
    }
    @Override
    public Fruit getFruitByFname(String fname) {
        return super.load("select *from t_fruit where fname like ?",fname);
//        try{
//            conn = getConn();
//            String sql = "select *from t_fruit where fname like ?";
//            psmt = conn.prepareStatement(sql);
//            psmt.setString(1,fname);
//            rs = psmt.executeQuery();
//            if(rs.next()) {
//                int fid = rs.getInt(1);
//                int price = rs.getInt(3);
//                int fcount = rs.getInt(4);
//                String remark = rs.getString(5);
//                return new Fruit(fid, fname, price, fcount, remark);
//            }
//        }catch(SQLException e){
//            e.printStackTrace();
//        }finally {
//            close(rs,psmt,conn);
//        }
//        return null;
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        String sql = "insert into t_fruit values(0,?,?,?,?)";

        return super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark())>0;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fcount = ? where fid = ?";
        return super.executeUpdate(sql,fruit.getFcount(),fruit.getFid())>0;
    }

    @Override
    public boolean delFruit(String fname) {
        String sql = "delete from t_fruit where fname like ?";
        return super.executeUpdate(sql,fname)>0;
    }

}
