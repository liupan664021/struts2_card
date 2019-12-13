<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	上传Excel文件
	<s:form action="/card/upload" method="post" enctype="multipart/form-data">
		<s:file name="file" label="提交文件" /><br/>
		<s:submit value="提交"></s:submit>
	</s:form>
</body>
</html>