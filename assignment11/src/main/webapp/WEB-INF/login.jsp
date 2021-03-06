<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" /> -->
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Flight Search</title>
</head>
<body>
</body>
<div class="container" style="margin-top: 125px;">
	<div class="row">
		<div class="col-sm-6 col-md-4 col-md-offset-4">
			<h1 class="text-center login-title">Flight Search Utility</h1>
			<div class="account-wall">
				<form:form commandName="user" class="form-signin">
					<label for="textinput1">Username</label>
					<form:input path="username" class="form-control"
						autofocus="autofocus" />
					<br>
					<label for="textinput2">Password</label>
					<form:input type="password" path="password" class="form-control" />
					<br>
					<input type="submit" class="btn" value="login"
						class="btn btn-lg btn-primary btn-block btn-signin"
						class="text-center login-title" />
				</form:form>
			</div>
		</div>
	</div>
</div>

</html>