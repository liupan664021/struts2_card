<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String path = request.getContextPath() + "/"; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>添加名片</h3>
	<s:form action="/card/insert" method="post">
		<s:textfield name="card.name" label="姓名"/>
		<s:radio name="card.sex" list="#{'男':'男','女':'女'}" label="性别" value="'男'"/>
		<s:textfield name="card.department" label="单位"/>
		<s:textfield name="card.mobile" label="手机"/>
		<s:textfield name="card.phone" label="电话"/>
		<s:textfield name="card.email" label="Email"/>
		<s:textfield name="card.address" label="地址"/>
		<table>
			<tr>
				<td><s:submit value="提交" theme="simple"/></td>
				<td><s:reset value="取消" theme="simple"/></td>
			</tr>
		</table>
	</s:form>
	
</body>
</html>