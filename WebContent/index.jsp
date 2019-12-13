<%@page import="org.apache.struts2.ServletActionContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% String path = request.getContextPath() + "/"; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>名片管理系统</h1>
	<a href="<%=path%>user/login.jsp">用户管理子系统</a><br/>
	<a href="<%=path%>card/find">名片管理子系统</a>
</body>
</html>