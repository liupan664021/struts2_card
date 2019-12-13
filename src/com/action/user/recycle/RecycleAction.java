package com.action.user.recycle;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.dao.card.CardDao;
import com.model.card.Card;
import com.opensymphony.xwork2.inject.Scope;
import com.opensymphony.xwork2.inject.Scoped;

@ParentPackage("struts-default")
@Namespace("/recycle")
@Scoped(Scope.REQUEST)
public class RecycleAction {
	
	private int id;
	private CardDao cardDao = new CardDao();
	private Card card;
	private String[] checkList;
	private List<Card> listCard;
	
	@Action(
			value = "find",
			results = {
					@Result(name = "success", location = "/card/retrieve.jsp", type = "dispatcher")
			}
	)
	public String find() throws Exception{
		listCard = cardDao.findAll("1");
		return "success";
	}
	
	@Action(
			value = "deleteList",
			results = {
					@Result(name = "success", location = "/find", type = "redirectAction")
			}
	)
	public String deleteList() throws Exception{
		int[] ids = new int[checkList.length];
		for(int i=0; i<ids.length; i++) {
			ids[i] = Integer.parseInt(checkList[i]);
		}
		cardDao.deleteList(ids);
		return "success";
	}
	
	@Action(
			value = "delete",
			results = {
					@Result(name = "success", location = "/find", type = "redirectAction")
			}
	)
	public String delete() throws Exception{
		cardDao.delete(id);
		return "success";
	}
	
	@Action(
			value = "recover",
			results = {
					@Result(name = "success", location = "/find", type = "redirectAction")
			}
	)
	public String recover() throws Exception{
		cardDao.updateFlag(id, "0");
		return "success";
	}
	
	@Action(
			value = "recoverList",
			results = {
					@Result(name = "success", location = "/find", type = "redirectAction")
			}
	)
	public String recoverList() throws Exception{
		for(String str : checkList) {
			cardDao.updateFlag(Integer.parseInt(str),"0");
		}
		return "success";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CardDao getCardDao() {
		return cardDao;
	}

	public void setCardDao(CardDao cardDao) {
		this.cardDao = cardDao;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public String[] getCheckList() {
		return checkList;
	}

	public void setCheckList(String[] checkedList) {
		this.checkList = checkedList;
	}

	public List<Card> getListCard() {
		return listCard;
	}

	public void setListCard(List<Card> listCard) {
		this.listCard = listCard;
	}
	
}
