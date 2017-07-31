<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>添加读者</h1>
	<%-- <form action="/Spring-MVC-model/user/addReader" name="userForm"
		method="post">
		menu：<input type="text" name="meno"><br> 
		姓名：<input
			type="text" name="name"> <br> 
			<input type="submit"
			value="添加">
	</form> --%>


	<form:form action="${pageContext.request.contextPath}/user/addReader"
		method="post" modelAttribute="reader" enctype="multipart/form-data">
        meno:<form:input path="meno" />
		<form:errors path="meno"></form:errors>
		<br>
                  姓名：<form:input path="name" />
		<form:errors path="name"></form:errors>
		<br/>
       <label>头 像</label><input type="file" name="file"/><br/>  

		<input type="submit" value="Submit" />

	</form:form>
</body>
</html>