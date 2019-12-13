package com.db_util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcPoolUtils {
	private static DataSource dataSource = new ComboPooledDataSource();
	
	
	public static Connection getConnection(){
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * String url = "jdbc:mysql://localhost:3306/" + "card" + "?serverTimezone=UTC";
		 * Connection connection = null; try {
		 * Class.forName("com.mysql.cj.jdbc.Driver"); connection =
		 * DriverManager.getConnection(url,"root","root"); } catch (Exception e) { //
		 * TODO: handle exception }
		 */
		return connection;
	}
	
	public static void closeConnection(Connection conn, Statement stat, ResultSet rs){
		try {
			if(rs!=null) rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if(stat!=null)
					try {
						stat.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} finally {
				if(conn!=null)
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
	}
	public static int dbCUD(String sql, Object... objects){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int row = 0;
		try {
			connection = JdbcPoolUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for(int i=0; i<objects.length; i++) {
				preparedStatement.setObject(i+1, objects[i]);
			}
			row = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			JdbcPoolUtils.closeConnection(connection, preparedStatement, null);
		}
		return row;
	}
	
	public static ResultSet query(Connection connection, String sql, Object... objects) {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = JdbcPoolUtils.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for(int i=0; i<objects.length;i++) {
				preparedStatement.setObject(i+1, objects[i]);
			}
			rs = preparedStatement.executeQuery();
			//JdbcPoolUtils.closeConnection(connection,preparedStatement,rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
}
