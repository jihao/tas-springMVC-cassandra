<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Sign In</title>
</head>
<body>
	<p>Sign In</p>
	<p>${flash_message}</p>
	<p>${error}</p>
	<form action="login" method="post">
		<label for="username">Name</label><input id="username" type="text" name="user.username"><br>
		<label for="password">Password</label><input id="password" type="password" name="user.password"><br>
		<label for="remember">Remember me</label><input id="remember" type="checkbox" name="remember">
		
		<input type="submit">
	</form>
	
<jsp:include page="_footer.jsp"/>
</body>
</html>