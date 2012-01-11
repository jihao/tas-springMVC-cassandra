<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Tweet</title>
</head>
<body>
	<p>
		Hi ${SESSION_CURRENT_USER.username}, your account was created at:
		<fmt:formatDate value="${SESSION_CURRENT_USER.created_at }" pattern="yyyy-MM-dd HH:mm:ss"/> 
	</p>
	<p>Tweet</p>
	${error}
	<form action="tweet" method="post">
		<label for="message">I'd like to say</label>
		<textarea id="message" rows="5" cols="40" name="message"></textarea><br>
		<input type="submit" value="Tweet">
	</form>
	
	<div id="tweetList">
		<c:forEach items="${tweetList}" varStatus="status" var="tweet">
			<div id="${status.index }">
				<div>
					<input type="hidden" value="${tweet.id_key}"></input>
					${tweet.message}
				</div>
				${tweet.user.username}
				
			</div>
		</c:forEach>
	</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>