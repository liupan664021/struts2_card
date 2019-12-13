package com.action.card;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.db_util.DbToExcel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Scope;
import com.opensymphony.xwork2.inject.Scoped;

@ParentPackage("struts-default")
@Namespace("/card")
@Scoped(Scope.REQUEST)
public class DownloadAction extends ActionSupport {
	/**
	 * auto generated.
	 */
	private static final long serialVersionUID = 1L;
	private String contentType;
	private long contentLength;
	private String contentDisposition;
	private InputStream inputStream;
	private String fileName="lp.xls";
	
	@Action(
		value="download",
		results = {
				@Result(name = "success", type = "stream")
		}
			)
	public String download() throws Exception{
		String[] fieldList = {"id","name","sex","department","mobile","phone","email","address"};
		String[] titles = {"id","name","sex","department","mobile","phone","email","address"};
		HttpSession session = ServletActionContext.getRequest().getSession();
		String condition = (String)session.getAttribute("condition");
		String order = (String)session.getAttribute("order");
		int len = fieldList.length;
		String sql = "";
		if(condition!=null) {
			
			for(int i=0; i<len-1; i++) {
				sql += fieldList[i] + " like '%" + condition + "'% or ";
			}
			sql += " like '%" + fieldList[len-1] + "%'";
		}
		
		
		contentType = "application/octet-stream";
		String name = URLEncoder.encode(fileName, "UTF-8");
		contentDisposition = "attachment;filename=" + fileName;
		String fileNameFrom = ServletActionContext.getServletContext().getRealPath("/download/" + fileName);
		File file = new File(fileNameFrom);
		if(!file.exists()) {
			file.getParentFile().mkdirs();
		}
		DbToExcel.dBToExcel("card", fieldList, titles, sql, order, fileNameFrom);
		inputStream = new FileInputStream(file);
		contentLength = inputStream.available();
		return SUCCESS;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
