package com.atguigu.fruit.dao.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BaseDAO<T> {
   public final String url = "jdbc:mysql://localhost:3306/fruitstock";
   public final String user = "root";
   public final String pwd = "123456";
   public final String Driver = "com.mysql.cj.jdbc.Driver";

   public Connection conn;
   public ResultSet rs;
   public PreparedStatement psmt;

   private Class entityClass;

   public BaseDAO(){
//      getClass()获取Class对象 当前我们执行的是new FruitDAOImpl()
//创建的是FruitDAOIMPL的实例 类构造方法首先会调用父类(BaseDAO)的无参构造方法

      Type genericType = getClass().getGenericSuperclass();
//      ParameterizedType 参数化类型
      Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
//      获取<T>中的T的真实的类型
      Type actualType = actualTypeArguments[0];
      try {
         entityClass = Class.forName(actualType.getTypeName());
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }
   }
   public Connection getConn(){//获取连接的方法
      try{
         Class.forName(Driver);
         return DriverManager.getConnection(url,user,pwd);
      }catch (ClassNotFoundException | SQLException e){
         e.printStackTrace();
      }
      return null;
   }

   public void close(ResultSet rs, PreparedStatement psmt, Connection conn){
      try{
         if(rs !=null){
            rs.close();
         }
         if(psmt !=null){
            psmt.close();
         }
         if(conn!=null && !conn.isClosed()){
            conn.close();
         }
      }catch (SQLException e){
         e.printStackTrace();
      }
   }

   //通过反射技术给obj对象的property属性赋propertyValue值
   private  void setValue(Object obj,String property,Object propertyValue){
      Class clazz = obj.getClass();
      try {
         //获取property这个字符串对应的属性名 比如"fid"去找"obj"对象中的fid属性
         Field field = clazz.getDeclaredField(property);
         if(field!=null){
            field.setAccessible(true);
            field.set(obj,propertyValue);
         }
      } catch (NoSuchFieldException | IllegalAccessException e) {
         e.printStackTrace();
      }
   }

   public List<T> executeQuery(String sql, Object...params){
      //执行查询返回list
      List<T> list = new ArrayList<>();
      try{
         conn = getConn();
         psmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
         setParams(psmt,params);
         rs = psmt.executeQuery();
//         通过rs可以获取结果集的元数据
         ResultSetMetaData rsmd = rs.getMetaData();
         int columnCount = rsmd.getColumnCount();
         while (rs.next()) {
            T entity = (T) entityClass.newInstance();
            for(int i = 0; i<columnCount;i++){
               String columnName = rsmd.getColumnName(i+1);
               Object columnValue = rs.getObject(i+1);
               setValue(entity,columnName,columnValue);
            }
            list.add(entity);
         }
      }catch (SQLException e){
         e.printStackTrace();
      } catch (InstantiationException e) {
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         throw new RuntimeException(e);
      } finally {
         close(rs,psmt,conn);
      }
      return list;
   }

   public T load(String sql,Object...params){
      //执行查询 返回单个实体对象
      try{
         conn = getConn();
         psmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
         setParams(psmt,params);
         rs = psmt.executeQuery();
//         通过rs可以获取结果集的元数据
         ResultSetMetaData rsmd = rs.getMetaData();
         int columnCount = rsmd.getColumnCount();
         if(rs.next()) {
            T entity = (T) entityClass.newInstance();
            for(int i = 0; i<columnCount;i++){
               String columnName = rsmd.getColumnName(i+1);
               Object columnValue = rs.getObject(i+1);
               setValue(entity,columnName,columnValue);
            }
            return entity;
         }
      }catch (SQLException e){
         e.printStackTrace();
      } catch (InstantiationException e) {
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         throw new RuntimeException(e);
      } finally {
         close(rs,psmt,conn);
      }
      return null;
   }

   public Object[] executeComplexQuery(String sql,Object...params){
      try{
         conn = getConn();
         psmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
         setParams(psmt,params);
         rs = psmt.executeQuery();
//         通过rs可以获取结果集的元数据
         ResultSetMetaData rsmd = rs.getMetaData();
         int columnCount = rsmd.getColumnCount();
         Object[] columnValueArr = new Object[columnCount];
         if(rs.next()) {
            for(int i = 0; i<columnCount;i++){
               Object columnValue = rs.getObject(i+1);
               columnValueArr[i] = columnValue;
            }
            return columnValueArr;
         }
      }catch (SQLException e){
         e.printStackTrace();
      } finally {
         close(rs,psmt,conn);
      }
      return null;
   }
   public int executeUpdate(String sql, Object...params) {
      //返回影响行数
      boolean insertFlag = false;
      insertFlag = sql.trim().toUpperCase().startsWith("INSERT");
      try{
         conn = getConn();
         if(insertFlag){
            psmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
         }else{
            psmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
         }
         //psmt = conn.prepareStatement(sql);
//         if(params!=null && params.length>0) {
//            for (int i = 0; i < params.length; i++) {
//               psmt.setObject(i + 1, params[i]);
//            }
//         }
         setParams(psmt,params);
         int count = psmt.executeUpdate();
         rs = psmt.getGeneratedKeys();
         if(rs.next()){
            return ((Long)rs.getLong(1)).intValue();
         }
         return  count;
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         close(rs,psmt,conn);
      }
      return 0;
   }
   public void setParams(PreparedStatement psmt,Object...params) throws SQLException{
//      给预处理对象设置参数
      if(params!=null && params.length>0){
         for(int i =0; i<params.length; i++){
            psmt.setObject(i+1,params[i]);
         }
      }
   }
}
