package com.dao.user.test;

import org.junit.Test;

import com.dao.user.UserDao;
import com.model.user.User;

public class UserDaoTest {
	
	@Test
	public void findTest() {
		UserDao userDao = new UserDao();
		User user = new User();
		user.setUserName("admin");
		user.setUserPassword("admin");
		User user2 = userDao.find(user);
		System.out.println(user2.getUserName());
	}
}
