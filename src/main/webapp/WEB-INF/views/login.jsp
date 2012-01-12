<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="http://twitter.github.com/bootstrap/1.4.0/bootstrap.min.css">
<link rel="stylesheet" href='<c:url value="/resources/css/site.css" />'>
<title>Sign In</title>
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
            <h2>Sign In</h2>
			<jsp:include page="_message.jsp"/>
			
			<form action="login" method="post">
				<fieldset>
				  <div class="clearfix">
		            <label for="username">Name</label>
		            <div class="input">
		             <input id="username" type="text" name="user.username">
		            </div>
		          </div>
		          <div class="clearfix">
		            <label for="password">Password</label>
		            <div class="input">
		             <input id="password" type="password" name="user.password">
		            </div>
		          </div>
		          <div class="clearfix">
		            <label for="remember"></label>
		            <div class="input">
		             <input id="remember" type="checkbox" name="remember">
		             <span>Remember me</span>
		            </div>
		          </div>
				<div class="actions">
		            <input type="submit" class="btn primary" value="Sign In">&nbsp;<button type="reset" class="btn">Cancel</button>
		        </div>
				
				</fieldset>
			</form>
	
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