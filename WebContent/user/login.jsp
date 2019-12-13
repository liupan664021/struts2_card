<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>用户登录系统</h2>
	<s:fielderror cssStyle="color:red"/>
	<form action="login" method="post">
			<table>
				
				<tr>
					<td>用户名:</td><td><input type="text" name="user.userName"></td>
				</tr>
				<tr>
					<td>密码:</td><td><input type="password" name="user.userPassword"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="登录">&nbsp<input type="submit" formaction="<%=path%>/user/register.jsp" value="注册"></td>
				</tr>
			</table>
		</form>
</body>
</html>