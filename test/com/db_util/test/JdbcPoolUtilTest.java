package com.db_util.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.db_util.JdbcPoolUtils;

public class JdbcPoolUtilTest {
	
	@Test
	public void getConnectionTest() {
		Connection connection = JdbcPoolUtils.getConnection();
		if(connection == null) {
			System.out.println("null");
		}
	}
	
	@Test
	public void queryTest() {
		String sql = "select * from user;";
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.query(connection, sql);
			if(rs == null) {
				System.out.println("null");
				return;
			}
			while(rs.next()) {
				System.out.println(rs.getString("userName"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
