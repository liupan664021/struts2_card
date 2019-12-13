package com.action.card;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.dao.card.CardDao;
import com.dao.user.UserDao;
import com.model.card.Card;
import com.opensymphony.xwork2.inject.Scope;
import com.opensymphony.xwork2.inject.Scoped;

@ParentPackage("struts-default")
@Namespace("/card")
@Scoped(Scope.REQUEST)
public class CardAction {
	private CardDao cardDao = new CardDao();
	private Card card;
	private String condition;
	private String[] checkList;
	private int id;
	private String order;
	private long pageNo;
	private long recordCount;
	private long pageCount;
	private List<Card> listCard;
	private String msg;
	private HttpSession session;
	
	@Action(
			value="insert",
			results = {@Result(name = "success", location = "/find", type = "redirectAction")}
	)
	public String insert() throws Exception{
		cardDao.insert(card);
		
		msg = "插入一条记录成功1";
		return "success";
	}
	
	
	@Action(
			value="insertList",
			results = {@Result(name = "success", location = "/find", type = "redirectAction")}
	)
	public String insertList() throws Exception{
		int n = cardDao.insertList(listCard);
		msg = "插入了一组（" + n + "条）记录成功！";
		return "success";
	}
	
	@Action(
			value="delete",
			results = {@Result(name = "success", location = "/find", type = "redirectAction")}
	)
	public String delete() throws Exception{
		cardDao.delete(id);
		msg = "删除一条记录成功！";
		return "success";
	}
	
	@Action(
			value="deleteList",
			results = {@Result(name = "success", location = "/find", type = "redirectAction")}
	)
	public String  deleteList() throws Exception{
		int[] ids = new int[checkList.length];
		for(int i=0; i<checkList.length; i++) {
			ids[i] = Integer.parseInt(checkList[i]);
		}
		int n = cardDao.deleteList(ids);
		msg = "删除了一组（" + n + "条）记录成功";
		return "success";
	}
	
	@Action(
			value="find",
			results = {@Result(name = "success", location = "/card/list.jsp", type = "dispatcher")}
	)
	public String find() throws Exception{
		listCard = cardDao.findByCondition(condition, "0");
		//session = ServletActionContext.getRequest().getSession();
		//session.setAttribute("condition", condition);
		//session.setAttribute("order", order);
		return "success";
	}
	
	@Action(
			value="findupdate",
			results = {@Result(name = "success", location = "/card/update.jsp", type = "dispatcher")}
	)
	public String findUpdate() throws Exception{
		card = cardDao.findById(id, "0");
		return "success";
	}
	
	@Action(
			value="retrieve",
			results = {@Result(name = "success", location = "/find", type = "redirectAction")}
	)
	public String retrieve() throws Exception{
		int[] ids = new int[checkList.length];
		for(int i=0; i<checkList.length; i++) {
			ids[i] = Integer.parseInt(checkList[i]);
		}
		cardDao.retrieve(ids);
		return "success";
	}
	
	@Action(
			value="update",
			results = {@Result(name = "success", location = "/find", type = "redirectAction")}
	)
	public String update() throws Exception{
		cardDao.update(card);
		return "success";
	}


	public Card getCard() {
		return card;
	}


	public void setCard(Card card) {
		this.card = card;
	}


	public String getCondition() {
		return condition;
	}


	public void setCondition(String condition) {
		this.condition = condition;
	}


	public String[] getCheckList() {
		return checkList;
	}


	public void setCheckList(String[] checkList) {
		this.checkList = checkList;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getOrder() {
		return order;
	}


	public void setOrder(String order) {
		this.order = order;
	}


	public List<Card> getListCard() {
		return listCard;
	}


	public void setListCard(List<Card> listCard) {
		this.listCard = listCard;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public HttpSession getSession() {
		return session;
	}


	public void setSession(HttpSession session) {
		this.session = session;
	}
}
