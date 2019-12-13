<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#info{color:#BBBBBB}
</style>
</head>
<body>
	<h2>用户注册</h2>
	<form action="register" method="post">
			<table>

				<tr>
					<td align="right">用户名*:</td><td><input type="text" name="user.userName" id="username"></td>
					<td id="info">用户名由字母或下划线开头，后接数字、字母和下划线</td>
				</tr>
				<tr>
					<td align="right">密码*:</td><td><input type="password" name="user.userPassword" id="password"></td>
					<td id="info">密码大于等于六位</td>
				</tr>
				<tr>
					<td align="right">再次输入密码*:</td><td><input type="password" name="userPassword" id="password2"></td>
					<td id="info">请再次输入密码，保证前后两次输入一致</td>
				</tr>
				<tr>
					<td align="right">真实姓名*:</td><td><input type="text" name="user.userRealName" id="password2"></td>
					<td id="info">请输入您的真实姓名</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="2"><input type="submit" value="注册">&nbsp<input type="reset" value="取消"></td>
				</tr>
			</table>
		</form>
</body>
</html>