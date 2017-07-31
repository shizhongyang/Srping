<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
    <h1>添加用户</h1>  
    <form action="/Spring-MVC-model/user/addBook" name="userForm" method="post">  
        标题 ：<input type="text" name="title">  
        <input type="submit" value="添加" >  
    </form> 
</body>
</html>