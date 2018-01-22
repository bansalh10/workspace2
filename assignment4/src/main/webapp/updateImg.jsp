<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %> 
<html>
<head>
<title>Update</title>
</head>
<body>
<form name="imgUpdate" method="post" action="UpdateImgServlet">
<input type="hidden" name="id" value="${ param.id }">
<table >
<tr>
<td>
Image Name
</td>
<td>
<input type="text" name="imgname">
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
</table>
</form>
</body>
</html>