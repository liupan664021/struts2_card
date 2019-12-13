package com.action.card;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.db_util.DbToExcel;
import com.opensymphony.xwork2.inject.Scope;
import com.opensymphony.xwork2.inject.Scoped;

@ParentPackage("struts-default")
@Namespace("/card")
@Scoped(Scope.REQUEST)
public class UploadAction {
	private File file;
	private String fileFileName;
	private String fileContentType;
	
	@Action(
			value = "upload",
			results= {@Result(name = "success", location = "/find", type = "redirectAction")}
			)
	public String upload() throws Exception{
		ServletContext servletContext = ServletActionContext.getServletContext();
		String dir = servletContext.getRealPath("/upload");
		File newFile = new File(dir,fileFileName);
		FileUtils.copyFile(file,newFile);
		String[] fieldList = {"name","sex","department","mobile","phone","email","address"};
		DbToExcel.excelToDb(dir+"\\"+fileFileName, "card", fieldList, 7);
		return "success";
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileName) {
		this.fileFileName = fileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContent) {
		this.fileContentType = fileContent;
	}
	
	
}
