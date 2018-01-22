<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %> 
<html>
<head>
<title>LoginPage</title>
</head>
<body>
<h1 style="text-align: center;">Login Page</h1>
<form name="loginform" method="post" action="CurrentUserData">
<table >
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
&nbsp;
</td>
<td>
<button type="submit">Submit</button>&nbsp;
<button type="reset">Cancel</button>
</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>Create new account<a href="signup.html">Click here</a></td>
</tr>
</table>
</form>
</body>
</html>