<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="http://twitter.github.com/bootstrap/1.4.0/bootstrap.min.css">
	<link rel="stylesheet" href='<c:url value="/resources/css/site.css" />'>
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
            <h2>Home</h2>
            	<jsp:include page="_message.jsp"/>
			<p>
			<c:choose>
				<c:when test="${SESSION_CURRENT_USER != null}">
					<div class="alert-message block-message info">
				        <p>
							Hi <strong>${SESSION_CURRENT_USER.username}</strong>, your session was created at:
							<fmt:formatDate value="${SESSION_CURRENT_USER.created_at }" pattern="yyyy-MM-dd HH:mm:ss"/>
							<a class="btn small" href="<c:url value="/logout"/>">Sign out</a> 
						</p>
			        </div>
					
					<p>
						<a class="btn primary" href="<c:url value="/tweet"/>">Normal Tweet</a>
						| <a class="btn primary" href="<c:url value="/new/tweet"/>">REST API - New Tweet </a>
						| <a class="btn primary" href="<c:url value="/tweets.json"/>">REST API - Show Tweets </a>
					</p>
				</c:when>
				<c:otherwise>
					<a class="btn primary" class="btn primary" href="<c:url value="/register"/>">Register</a> | <a class="btn primary" href="<c:url value="/login"/>">Sign in</a>	to tweet	
				</c:otherwise>
			</c:choose>
			</p>
			
			<p style="display:none;"><a class="btn primary" href='<c:url value="/compare?input1=S&input2=B"></c:url>'>Compare S &amp; B</a></p>

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
      
    </div> <!-- /container -->
    
</body>
</html>
