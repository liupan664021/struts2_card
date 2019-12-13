package com.dao.card;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Result;

import com.dao.IBaseDao;
import com.db_util.JdbcPoolUtils;
import com.model.card.Card;
import com.sun.org.apache.bcel.internal.generic.Select;

public class CardDao implements IBaseDao<Card> {

	@Override
	public int insert(Card card) {
		String sql = "insert into card values(null,?,?,?,?,?,?,?,?);";
		//Connection connection = JdbcPoolUtils.getConnection();
		int row = JdbcPoolUtils.dbCUD(sql, card.getName(),card.getSex(),card.getDepartment(),card.getMobile(),
				card.getPhone(),card.getEmail(),card.getAddress(),"0");
		return row;
	}

	@Override
	public int insertList(List<Card> list) {
		int cnt = 0;
		for(Card card:list) {
			if(insert(card) == 1) ++cnt;
		}
		return cnt;
	}

	@Override
	public int update(Card card) {
		String sql = "update card set name=?, sex=?, department=?, mobile=?,"
				+ " phone=?, email=?, address=? where id=?;";
		Object[] objects = {
				card.getName(),
				card.getSex(),
				card.getDepartment(),
				card.getMobile(),
				card.getPhone(),
				card.getEmail(),
				card.getAddress(),
				card.getId()
		};
		int res = JdbcPoolUtils.dbCUD(sql, objects);
		return res;
	}

	@Override
	public int deleteList(int... ids) {
		int cnt = 0;
		for(int id:ids) {
			if(delete(id) == 1) ++cnt;
		}
		return cnt;
	}

	@Override
	public int delete(Card card) {
		return delete(card.getId());
	}

	@Override
	public int delete(int id) {
		String sql = "delete from card where id=?;";
		return JdbcPoolUtils.dbCUD(sql, id);
	}

	@Override
	public Card findById(int id) {
		String sql = "select * from card where id=?";
		Connection connection = null;
		Card card = null;
		ResultSet rs = null;
		
		try {
			connection = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.query(connection, sql, id);
			if(rs == null) {
				return card;
			}
			while(rs.next()) {
				String flag = rs.getString("flag");
				if(flag == "1") continue;
				card = new Card();
				card.setId(id);
				card.setName(rs.getString("name"));
				card.setSex(rs.getString("sex"));
				card.setDepartment(rs.getString("department"));
				card.setMobile(rs.getString("mobile"));
				card.setPhone(rs.getString("phone"));
				card.setEmail(rs.getString("email"));
				card.setAddress(rs.getString("address"));
				card.setFlag(rs.getString("flag"));
			}
			//JdbcPoolUtils.closeConnection(connection, null, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcPoolUtils.closeConnection(connection, null, rs);
		}
		return card;
	}

	@Override
	public Card find(Card card) {
		return findById(card.getId());
	}

	@Override
	public List<Card> findAll() {
		String sql = "select * from card";
		List<Card> list = new ArrayList<Card>();
		Connection connection = null;
		Card card = null;
		ResultSet rs = null;
		try {
			connection = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.query(connection, sql);
			if(rs == null) {
				return null;
			}
			while(rs.next()) {
				card = new Card();
				card.setId(rs.getInt("id"));
				card.setName(rs.getString("name"));
				card.setSex(rs.getString("sex"));
				card.setDepartment(rs.getString("department"));
				card.setMobile(rs.getString("mobile"));
				card.setPhone(rs.getString("phone"));
				card.setEmail(rs.getString("email"));
				card.setAddress(rs.getString("address"));
				card.setFlag(rs.getString("flag"));
				list.add(card);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcPoolUtils.closeConnection(connection, null, rs);
		}
		return list;
	}

	@Override
	public List<Card> findByCondition(String condition) {
		List<Card> list = new ArrayList<Card>();
		String sql = "select * from card ";
//		String sql = "select * from card where name like '%?%' or sex like '%?%' or department like '%?%' or"
//				+ " mobile like '%?%' or phone like '%?%' or email like '%?%' or address like '%?%';";
		if(condition!=null && !condition.equals("")) {
			sql += "where";
			String[] fields = {"name","sex","department","mobile","phone","email","address"};
			for(int i=0; i<fields.length-1; i++) {
				sql = sql + fields[i] + "like '%" + condition + "%' or"; 
			}
			sql += "like '%" + fields[fields.length-1] + "%'";
		}
		sql += ";";
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = JdbcPoolUtils.getConnection();
			rs = JdbcPoolUtils.query(connection, sql);
			if(rs == null) {
				return list;
			}
			while(rs.next()) {
				Card card = new Card();
				card.setId(rs.getInt("id"));
				card.setName(rs.getString("name"));
				card.setSex(rs.getString("sex"));
				card.setDepartment(rs.getString("department"));
				card.setMobile(rs.getString("mobile"));
				card.setPhone(rs.getString("phone"));
				card.setEmail(rs.getString("email"));
				card.setAddress(rs.getString("address"));
				card.setFlag(rs.getString("flag"));
				list.add(card);
			}
			//JdbcPoolUtils.closeConnection(connection, null, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcPoolUtils.closeConnection(connection, null, rs);
		}
		return list;
	}
	
	public Card findById(int id, String flag) {
		Card card = findById(id);
		if(card.getFlag().equals(flag)) return card; else return null;
	}
	
	public Card find(Card card, String flag) {
		Card card2 = find(card);
		if(card2.getFlag().equals(flag)) return card2; else return null;
	}
	
	public List<Card> findAll(String flag){
		List<Card> list = new ArrayList<Card>();
		List<Card> l = findAll();
		for(Card card :l) {
			if(card.getFlag().equals(flag)) {
				list.add(card);
			}
		}
		return list;
	}
	
	public List<Card> findByCondition(String condition, String flag){
		List<Card> res = new ArrayList<Card>();
		List<Card> list = findByCondition(condition);
		for(Card card :list) {
			if(card.getFlag().equals(flag)) res.add(card);
		}
		return res;
	}
	public int updateFlag(int id, String flag) throws SQLException {
		String sql = "update card set flag=? where id=?";
		int res = JdbcPoolUtils.dbCUD(sql, flag, id);
		return res;
	}
	public int retrieve(int ...ids) throws SQLException{
		int res = 0;
		for(int id:ids) {
			res += updateFlag(id, "1");
		}
		return res;
	}
	public int revert(int... ids) throws SQLException{
		int res = 0;
		for(int id:ids) {
			res += updateFlag(id, "0");
		}
		return res;
	}
}
