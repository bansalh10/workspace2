<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SignUp</title>
</head>
<body>
<hr>
<form name="signupform" method="post" action="SignupServlet">
<table>
<tr>
<td>
Username
</td>
<td>
<input type="text" name="username">
</td>
</tr>
<tr>
<td>
Password
</td>
<td>
<input type="password" name="password">
</td>
</tr>
<tr>
<td>
EmailId
</td>
<td>
<input type="text" name="emailid">
</td>
</tr>
<tr>
<td>
&nbsp;
</td>
<td>
<button type="submit">Signup</button>&nbsp;
<button type="reset">Cancel</button>
</td>
</tr>
</table>
</form>
</body>
</html>