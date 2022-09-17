package com.atguigu.fruit.controller;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.impl.FruitDAOImpl;
import com.atguigu.fruit.pojo.Fruit;

import javax.xml.transform.Source;
import java.util.List;
import java.util.Scanner;

public class Menu{
    Scanner input = new Scanner(System.in);
    FruitDAO fruitDAO = new FruitDAOImpl();
    public int showMainMenu(){
        System.out.println("===========欢迎进入水果库存系统===========");
        System.out.println("1.显示全部水果库存信息");
        System.out.println("2.添加水果库存信息");
        System.out.println("3.查看特定水果库存信息");
        System.out.println("4.水果下架");
        System.out.println("5.退出");
        System.out.print("请选择:");
        Scanner input = new Scanner(System.in);
        int slt = input.nextInt();
        return slt;

    }
    public void showFruitList(){
        List<Fruit> fruitList = fruitDAO.getFruitList();
        System.out.println("========================================");
        System.out.println("编号\t名称\t单价\t库存\t备注");
        if(fruitList==null || fruitList.size()<=0){
            System.out.println("库存为空");
        }else{
            for(int i = 0;i<fruitList.size();i++){
                Fruit fruit = fruitList.get(i);
                System.out.println(fruit);
            }
        }
        System.out.println("========================================");
    }

    public void addFruit(){//添加水果库存信息  业务方法:添加库存记录
        System.out.println("请输入水果名称");
        String fname = input.next();
        Fruit fruit = fruitDAO.getFruitByFname(fname);
        if(fruit==null){//说明库存中没有这个名称的水果
            System.out.println("请输入水果单价:");
            int price = input.nextInt();
            System.out.println("请输入水果库存量:");
            int fcount = input.nextInt();
            System.out.println("请输入水果备注:");
            String remark = input.next();
            System.out.println();
            fruit = new Fruit(0,fname,price,fcount,remark);
            fruitDAO.addFruit(fruit);
        }else{
            System.out.println("请输入追加的库存量");
            int fcount = input.nextInt();
            fruit.setFcount(fruit.getFcount()+fcount);
            fruitDAO.updateFruit(fruit);
        }
        System.out.println("添加成功");
    }

    public void showFruitInfo(){
        System.out.println("请输入水果名称");
        String fname = input.next();
        Fruit fruit = fruitDAO.getFruitByFname(fname);
        if(fruit==null){
            System.out.println("没有找到指定的水果库存记录");
        }else{
            System.out.println("========================================");
            System.out.println("编号\t名称\t单价\t库存\t备注");
            System.out.println(fruit);
            System.out.println("========================================");

        }
    }

    public void delFruit(){
        System.out.println("请输入水果名称:");
        String fname = input.next();
        Fruit fruit = fruitDAO.getFruitByFname(fname);
        if(fruit==null){
            System.out.println("没有对应的水果信息");
        }else{
            fruitDAO.delFruit(fname);
            System.out.println(fname+"下架成功");
        }
    }
    public boolean exit(){
        System.out.print("是否确认退出?(y/n):");
        String slt = input.next();
        return !"y".equalsIgnoreCase(slt);//忽略大小写进行比较 相同则true 用!取反
    }

}
