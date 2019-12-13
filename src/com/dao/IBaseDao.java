package com.dao;

import java.util.List;

public interface IBaseDao<T> {
	public int insert(T o);
	public int insertList(List<T> list);
	public int update(T o);
	public int deleteList(int... ids);
	public int delete(T o);
	public int delete(int id);
	public T findById(int id);
	public T find(T o);
	public List<T> findAll();
	public List<T> findByCondition(String condition);
}
