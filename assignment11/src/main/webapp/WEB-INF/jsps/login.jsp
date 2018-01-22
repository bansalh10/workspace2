<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="<c:url value='/assets/css/bootstrap.css'/>"></link>
<title>LoginPage</title>
</head>
<body>
<div class="jumbotron text-center">
    <h1>Flight Search Portal</h1>
    
</div>
<div class="container" style="margin-top: 125px;">
	<div class="row">
		<div class="col-sm-6 col-md-4 col-md-offset-4">
<h1 class="text-center login-title">Login Page</h1>
<c:if test="${errormsg }"><h5 class="text-center login-title">Invalid username or password</h5></c:if>
<div class="account-wall">
<form:form    commandName="user" class="form-signin">

<label for="textinput1">Username</label>
<form:input class="form-control" name="username" path="username" />
<form:errors path="username"></form:errors>

<br>
<label for="textinput2">Password</label>
<form:input type="password" name="password" path="password" class="form-control" />
<form:errors path="password"></form:errors>
<br>


<input  type="submit" value="Login" class="btn" class="btn btn-lg btn-primary btn-block btn-signin"
						class="text-center login-title" >
<a href="signup.html">Not a user?Click here to SignUp</a>
</form:form>
</div>
</div>
</div>
</div>
</body>
</html>