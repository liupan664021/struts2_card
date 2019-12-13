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
		function insert(){
			location.href = "<%=path%>card/insert.jsp";
		}
		function retrieve(){
			location.href = "<%=path%>recycle/find";
		}
		function upload(){
			location.href = "<%=path%>card/upload.jsp";
		}
		function download(){
			location.href = "<%=path%>card/download";
		}
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
		function moveToRetrieve(){
			if(confirm("确定要将选择的记录移动到回收站吗")){
				document.f2.action="<%=path%>card/retrieve";
				document.f2.submit();
			}
		}
		function deleteconfirm(id){
			if(confirm("要删除记录吗")){
				location.href="<%=path %>card/delete?id="+id;
			}
		}
		function delChosed(){
			if(confirm("要删除所选吗？")){
				document.f2.action="<%=path%>card/retrieve";
				document.f2.submit();
			}
		}
	</script>

</head>
<body>
	<h3 align="center">名片浏览系统</h3>
	<form action="<%=path%>card/find" method="post">
		<div align="center">
			名片搜索:<input name="condition" type="text" /><input type="submit" value="查询"/>
		</div>
	</form>
	<s:form method="post" name="f2">
		<table align="center" >
			<tr>
				<td><input type="button" value="添加" onclick="insert()"/></td>
				<td><input type="button" value="全选" onclick="selectAll()"/></td>
				<td><input type="button" value="取消全选" onclick="unSelectAll()"/></td>
				<td><input type="button" value="彻底删除所选" onclick="delChosed()"/></td>
				<td><input type="button" value="将所选移动到回收站" onclick="moveToRetrieve()"/></td>
				<td><input type="button" value="导入名片" onclick="upload()"/></td>
				<td><input type="button" value="导出查询结果" onclick="download()"/></td>
				<td><input type="button" value="进入回收站" onclick="retrieve()"/></td>
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
					<td><a href="<%=path%>card/findupdate?id=${card.id}">修改</a>
						<a href="javascript:deleteconfirm('${card.id}')">删除</a>
					</td>
				</tr>
			</s:iterator>
		</table>
	</s:form>
</body>
</html>