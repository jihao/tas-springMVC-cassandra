<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h2>a simple twitter alike system (tas) build upon springMVC and cassandra</h2>

<p>${flash_message }</p>

<p>
<c:choose>
	<c:when test="${SESSION_CURRENT_USER != null}">
		${ SESSION_CURRENT_USER.username }? <a href="<c:url value="logout"/>">Sign out</a>
	</c:when>
	<c:otherwise>
		<a href="<c:url value="register"/>">Register</a> | <a href="<c:url value="login"/>">Sign in</a>		
	</c:otherwise>
</c:choose>
</p>
<p><a href="<c:url value="tweet"/>">Tweet</a></p>

<p style="display:none;"><a href='<c:url value="compare?input1=S&input2=B"></c:url>'>Compare S &amp; B</a></p>
<jsp:include page="_footer.jsp"/>

</body>
</html>
