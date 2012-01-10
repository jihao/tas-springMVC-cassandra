<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Register</title>
</head>
<body>
	<h2>
		<s:property value="user.username" />
	</h2>
	<h2>
		<s:property value="user.created_at" />
	</h2>
	<h2>
		<s:property value="user.tweets.size" />
	</h2>
	
	<p>Register</p>

	<s:form action="register">

		<s:textfield name="user.username" label="Name" />
		<s:password name="user.password" label="Password" />
		<s:password name="password2" label="Confirm Password" />

		<s:submit value="Submit" />

	</s:form>
	
<jsp:include page="_footer.jsp"/>
</body>
</html>