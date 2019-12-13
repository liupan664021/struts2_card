package com.dao.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.IBaseDao;
import com.db_util.JdbcPoolUtils;
import com.model.user.User;

public class UserDao implements IBaseDao<User>{

	@Override
	public int insert(User user) {
		String sql = "insert into user(userName, userPassword, userRealName) values(?,?,?);";
		return JdbcPoolUtils.dbCUD(sql, user.getUserName(),user.getUserPassword(),user.getUserRealName());
	}

	@Override
	public int insertList(List<User> list) {
		int res = 0;
		for(User user :list) {
			if(insert(user)==1) ++res;
		}
		return res;
	}

	@Override
	public int update(User o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteList(int... ids) {
		int res = 0;
		for(int id:ids) {
			if(delete(id) == 1) ++res;
		}
		return res;
	}

	@Override
	public int delete(User user) {
		String sql = "delete from user where userName=? and userPassword=? and userRealName=?;";
		int res = JdbcPoolUtils.dbCUD(sql, user.getUserName(),user.getUserPassword(),user.getUserRealName());
		return res;
	}

	@Override
	public int delete(int id) {
		String sql = "delete from user where userId=?;";
		int row = JdbcPoolUtils.dbCUD(sql, id);
		return row;
	}

	@Override
	public User findById(int id) {
		String sql = "select * from user where userId=?;";
		Connection connection = null;
		ResultSet rs = null;
		User newUser = null;
		try {
			connection = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.query(connection, sql, id);
			if(rs.next()) {
				newUser = new User();
				newUser.setUserName(rs.getString("userName"));
				newUser.setUserPassword(rs.getString("userPassword"));
				newUser.setUserRealName(rs.getString("userRealName"));
			}
			//JdbcPoolUtils.closeConnection(connection, null, rs);
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			JdbcPoolUtils.closeConnection(connection, null, rs);
		}
		return newUser;
	}

	@Override
	public User find(User user) {
		Connection connection = null; 
		String sql = "select * from user where userName=? and userPassword=?;";
		ResultSet rs = null; 
		User newUser = null;
		try {
			connection = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.query(connection, sql, user.getUserName(),user.getUserPassword());
			if(rs.next()) {
				newUser = new User();
				newUser.setUserName(rs.getString("userName"));
				newUser.setUserPassword(rs.getString("userPassword"));
				newUser.setUserRealName(rs.getString("userRealName"));
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}finally {
			JdbcPoolUtils.closeConnection(connection, null, rs);
		}
		return newUser;
	}

	@Override
	public List<User> findAll() {
		List<User> res = new ArrayList<User>();
		String sql = "select * from user;";
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.query(connection,sql);
			while(rs.next()) {
				User user = new User(rs.getString("userName"), rs.getString("userPassword"), rs.getString("userRealName"));
				res.add(user);
			}
			//JdbcPoolUtils.closeConnection(connection, null, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcPoolUtils.closeConnection(connection, null, rs);
		}
		return res;
	}

	@Override
	public List<User> findByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
