<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% String path = request.getContextPath() + "/"; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
function selectAll(){
	var a = f2.checkList.length;
	if(a!=undefined){
		for(i=0; i<a; i++){
			f2.checkList[i].checked = true;
		}
	}else{
		f2.checkList.checked = true;
	}
}

function unSelectAll(){
	var a = f2.checkList.length;
	if(a!=undefined){
		for(i=0; i<a; i++){
			f2.checkList[i].checked = false;
		}
	}else{
		f2.checkList.checked = false;
	}
}
	
	function delChosed(){
		document.f2.action="<%=path%>recycle/deleteList";
		document.f2.submit();
	}
	
	function moveToList(){
		document.f2.action="<%=path%>recycle/recoverList";
		document.f2.submit();
	}
	
	function find(){
		location.href="<%=path%>recycle/find"
	}
	function back(){
		location.href="<%=path%>card/list.jsp"
	}
	
	function deleteconfirm(id){
		if(confirm("要删除记录吗")){
			location.href="<%=path %>recycle/delete?id="+id;
		}
	}
</script>
</head>
<body>
	<h3 align="center">回收站</h3>
	<s:form method="post" name="f2">
		<table align="center" >
			<tr>
				
				<td><input type="button" value="全选" onclick="selectAll()"/></td>
				<td><input type="button" value="取消全选" onclick="unSelectAll()"/></td>
				<td><input type="button" value="彻底删除所选" onclick="delChosed()"/></td>
				<td><input type="button" value="将所选还原" onclick="moveToList()"/></td>
				<td><input type="button" value="返回查询页面" onclick="back()"/></td>
				<td><input type="button" value="查询" onclick="find()"/></td>
			</tr>
		</table>
		<table width="70%" border="0" cellpadding="3" cellspacing="1" align="center">
			<tr bgcolor="#8899cc">
				<td></td><td>编号</td><td>姓名</td><td>性别</td><td>单位</td><td>手机</td><td>电话</td><td>Email</td><td>通讯地址</td><td>操作</td>
			</tr>
			<s:iterator var="card" value="listCard" status="list">
				<tr>
					<td><input type="checkbox" name="checkList" value="${card.id}"/></td>
					<td><s:property value="#card.id"/></td>
					<td><s:property value="#card.name"/></td>
					<td><s:property value="#card.sex"/></td>
					<td><s:property value="#card.department"/></td>
					<td><s:property value="#card.mobile"/></td>
					<td><s:property value="#card.phone"/></td>
					<td><s:property value="#card.email"/></td>
					<td><s:property value="#card.address"/></td>
					<td><a href="<%=path%>recycle/recover?id=${card.id}">还原</a>
						<a href="javascript:deleteconfirm('${card.id}')">删除</a>
					</td>
				</tr>
			</s:iterator>
		</table>
	</s:form>
</body>
</html>