package com.omg.evn.util.dbutil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.hibernate.Session;

/**
 * 
 * @ClassName: DBUtil 
 * @Description: TODO() 
 * @author: wt
 * @date: 2013-12-9 下午1:54:24
 * @最后修改人：wt
 * @最后修改时间：2013-12-9 下午1:54:24
 */
public class DBUtil {
	
	public static void main(String[] args) {
		Connection conn = null;//连接对象
		PreparedStatement ps = null;//声明
		ResultSet rs = null;//记录集
		
		try {
			String sql = "";
			conn = DBUtil.getConnection();//获取数据库连接
			DBUtil.beginTransaction(conn);//开启事务
			/*String sql = "select * from user";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("id:"+rs.getString(1)+","+rs.getString(2));
			}*/
			/*sql = "insert user (name) value ('小明2')";
			ps = conn.prepareStatement(sql);
			int rows = ps.executeUpdate();
			if (rows > 0) {
				System.out.println("保存成功");
			}else {
				System.out.println("保存失败");
			}*/
			DBUtil.commitTransaction(conn);//提交事务
		} catch (Exception e) {
			DBUtil.rollbackTransaction(conn);//回滚事务
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);//关闭记录集
			DBUtil.close(ps);//关闭声明
			DBUtil.close(conn);//关闭连接对象
		}
	}
	
	/**
	 * 获取数据库连接
	 */
	public static Connection getConnection(){
		Connection conn = null;
		try {
			String driverClass = null;
			String url = null;
			String username = null;
			String password = null;
			//从db-config.properties中获取数据库配置
			Properties p = new Properties();
			p.load(ClassLoader.getSystemResourceAsStream("db-config.properties"));
			driverClass = p.getProperty("driverClass");
			url = p.getProperty("url");
			username = p.getProperty("username");
			password = p.getProperty("password");	
			Class.forName(driverClass);
			/*Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8";
			String username = "root";
			String password = "admin";*/
			conn =DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 开启事务
	 */
	public static void beginTransaction(Connection conn){
		if(conn != null){
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 提交事务
	 */
	public static void commitTransaction(Connection conn){
		if(conn != null){
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 事务回滚
	 */
	public static void rollbackTransaction(Connection conn){
		if(conn != null){
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 重置事务为默认状态
	 */
	public static void resetConnection(Connection conn){
		if(conn != null){
			try {
				conn.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭hibernate session
	 */
	public static void close(Session session){
		if(session != null){
			try {
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * 关闭数据库连接
	 */
	public static void close(Connection conn){
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭Statement
	 */
	public static void close(Statement ps){
		if(ps != null) {
			try {
				ps.close();//关闭Statement
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭ResultSet
	 */
	public static void close(ResultSet rs){
		if(rs != null) {
			try {
				rs.close();//关闭ResultSet
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
