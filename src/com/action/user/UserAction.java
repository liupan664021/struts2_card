package com.action.user;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.dao.user.UserDao;
import com.model.user.User;
import com.opensymphony.xwork2.ActionSupport;


@ParentPackage("struts-default")
@Namespace("/")
@Results({
	@Result(name="success", location = "/card/list.jsp"),
	@Result(name="input", location = "/user/login.jsp")
})
public class UserAction extends ActionSupport{
	private User user;
	private String userPassword;
	
	@Action("/login")
	public String userLogin() throws Exception{
		
		UserDao userDao = new UserDao();
		User user2 = userDao.find(user);
		if(user2==null) {
			addFieldError("user.userName", "该用户未注册，请先注册");
			return "input";
		}else {
			return "success";
		}
	}
	
	public String userRegister() throws Exception{
		UserDao userDao = new UserDao();
		User newUser = userDao.find(user);
		int res = 0;
		if(newUser!=null) {
			addFieldError("user.userName", "该用户名已经被注册了");
			return "input";
		}else {
			newUser = new User();
			newUser.setUserName(user.getUserName());
			newUser.setUserPassword(user.getUserPassword());
			newUser.setUserRealName(user.getUserRealName());
			res = userDao.insert(newUser);
		}
		return res == 1 ? "success" : "input";
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
