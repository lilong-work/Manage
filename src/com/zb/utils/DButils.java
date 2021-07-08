package com.zb.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class DButils {
    //线程级别的局部变量，只在同一个线程内有效
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
    private static DruidDataSource druidDataSource = new DruidDataSource();
    private static String url;
    private static String pwd;
    private static String uid;
    private static String driverClassName;

    static {
        //静态代码块
//        File file =new File("jdbc.properties");
//        FileReader fr=null;
        Properties pro = new Properties();

        try {

            //方法一
//            fr = new FileReader(file);
//            pro.load(fr);
            //方法二
            //好处就是不用写绝对路径，直接在src下面
            pro.load(DButils.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            url=pro.getProperty("url");
            uid=pro.getProperty("uid");
            pwd=pro.getProperty("pwd");
            driverClassName=pro.getProperty("driverClassName");
            druidDataSource.setDriverClassName(driverClassName);
            druidDataSource.setUrl(url);
            druidDataSource.setUsername(uid);
            druidDataSource.setPassword(pwd);
            druidDataSource.setInitialSize(10);//初始化连接数量
            druidDataSource.setMaxActive(10);//最大并发连接数，默认为8

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
//            try {
//                fr.close();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
        }
    }

    /**
     * 连接数据库
     * @return 实现Connection接口的一个实现类对象
     * @throws Exception
     */
    public static Connection getConnection()  {
        Connection conn=null;
        try {
            //第二次的时候虽然不为空，但是调用close并没有关掉，而是他的isclose属性为true，但是过一段时间它会做一些处理自动将这个为ture的属性变为false
            if(threadLocal.get()!=null&&threadLocal.get().isClosed()==false) {
                conn=threadLocal.get();
            }else {
                conn=createConnection();
                threadLocal.set(conn);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }
    //创建连接
    private static Connection createConnection() {
        //return DriverManager.getConnection(url, uid, pwd);
        Connection conn = null;
        try {
            System.out.println("--------DbUtils getConn()--------");
            conn=druidDataSource.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn ;
    }
    //释放连接
    public static void release(){
        if(getConnection() != null){
            try{
				getConnection().close();  //因为现在暂时只用到了一个线程，所以关闭了没有释放资源，就会出问题
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    //开始事务
    public static void startTransaction() throws SQLException {
        getConnection().setAutoCommit(false);
    }
    //提交事务
    public static void commitTransaction() throws SQLException {
        getConnection().commit();
    }
    //回滚
    public static void commitRollback() throws SQLException {
        getConnection().rollback();
    }

//	/**
//	 * 释放资源
//	 * @param rs
//	 * @param st
//	 * @param conn
//	 */
//	public static void release(ResultSet rs,Statement st,Connection conn){
//		if(rs != null){
//			try{
//				rs.close();
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//		if(st != null){
//			try{
//				st.close();
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//		if(conn != null){
//			try{
//				conn.close();
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//	}
//	public static void release(Statement st,Connection conn){
//		if(st != null){
//			try{
//				st.close();
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//		if(conn != null){
//			try{
//				conn.close();
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//	}



/**
 * 测试连接
 * @param args
 * @throws Exception
 */
	public static void main(String[] args) throws Exception {
//    MyRunable runable = new MyRunable();
//    Thread thread = new Thread(runable);
//    Thread thread1 = new Thread(runable);
//    thread.start();
//    thread1.start();
        System.out.println(getConnection());
    }
}
//
//
//}
//class MyRunable implements Runnable{
//
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		for(int i=0;i<6;i++) {
//			Connection conn=null;
//			try {
//				conn = DButils.getConnection();
//				System.out.println(conn);
//			}finally{
//				if(conn!=null) {
//					DButils.release(conn);
//				}
//			}
//
//		}
//	}
//
//}