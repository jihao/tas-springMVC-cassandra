<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="http://twitter.github.com/bootstrap/1.4.0/bootstrap.min.css">
<link rel="stylesheet" href='<c:url value="/resources/css/site.css" />'>
<title>Tweet</title>
</head>
<body>
<div class="container">

      <div class="content">
        <div class="page-header">
          <h1>tas-springMVC-cassandra <small></small></h1>
          <h1><small>a simple twitter alike system (tas) build upon springMVC and cassandra</small></h1>
        </div>
        <div class="row">
          <div class="span10">
            <h2>REST API - Tweet.json</h2>
            <jsp:include page="_message.jsp"/>
			<div class="alert-message block-message info">
		        <p>
					Hi <strong>${SESSION_CURRENT_USER.username}</strong>, your session was created at:
					<fmt:formatDate value="${SESSION_CURRENT_USER.created_at }" pattern="yyyy-MM-dd HH:mm:ss"/> 
				</p>
	        </div>
	      
	<form action="<c:url value="/new/tweet.json"></c:url>" method="post">
	<fieldset>
		  <div class="clearfix">
            <label for="message">I'd like to say</label>
            <div class="input">
              <textarea id="message" name="message" rows="5" style="width: 400px;"></textarea>
            </div>
          </div>
          <div class="actions">
		    <input type="submit" class="btn primary" value="Tweet">
		  </div>
	</fieldset>
	</form>
	
	<div id="tweetList">
		<table class="bordered-table">
		<tr><th>#</th><th>time uuid key</th><th>message</th><th>user</th></tr>
		<c:forEach items="${tweetList}" varStatus="status" var="tweet">
			<tr>
				<td>${status.index}</td><td>${tweet.id_key}</td><td>${tweet.message}</td><td>${tweet.user.username}</td>
			</tr>
		</c:forEach>
		</table>
	</div>
	
          </div>
          <div class="span4">
            <h3>Sidebar</h3>
             <p>
				<a href='<c:url value="/home"/>' class="btn primary">home</a>
			</p>
          </div>
        </div>
      </div>

      <jsp:include page="_footer.jsp"/>
    
</div>
	
</body>
</html>