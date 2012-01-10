<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h2>a simple twitter alike system (tas) build upon struts2 and cassandra</h2>

<p>
<c:choose>
	<c:when test="SESSION_CURRENT_USER">
		<a href="<c:url value="logout"/>">Sign out</a>
	</c:when>
	<c:otherwise>
		<a href="<c:url value="registerUI"/>">Register</a> | <a href="<c:url value="loginUI"/>">Sign in</a>		
	</c:otherwise>
</c:choose>
</p>
<p><a href="<c:url value="tweetUI"/>">Tweet</a></p>


<jsp:include page="_footer.jsp"/>

</body>
</html>
