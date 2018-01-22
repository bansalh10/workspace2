<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %> 
<html>
<body>
<h1 style="text-align: center;">IMAGE MANAGEMENT UTILITY</h1>
<br>
<hr>
<form name="imgupload" method="post" action="ImgUploadServlet" enctype="multipart/form-data">
<input type="file" name="fileupload" />&nbsp;
<button type="submit" name="uploadbtn">Upload</button>
</form>
<a href="LogoutServlet" style="right: 10%; top: 10%;" >Logout</a>
<table border="1" style="width: 100%;">
<tr><th>S.NO</th><th>NAME</th><th>SIZE</th><th>PREVIEW</th><th>ACTION</th></tr>
<% int i=1; %>
<c:forEach items="${ sessionScope.imagelist }" var="img" >
<tr>
<td><%= i++ %></td>
<td>${ img.imgName }</td>
<td>${ img.imgSize }</td>
<td>
<img  src="DisplayPhoto?id=${ img.imgId }" height="200" width="200">
</td>
<td><form method="post" action="updateImg.jsp">
<input type="hidden" name="id" value="${ img.imgId }">
    <input type="submit" value="Update" />
</form>&nbsp;
<form method="post" action="DeleteImgServlet">
<input type="hidden" name="id" value="${ img.imgId }">
    <input type="submit" value="Delete" />
</form></td>
</tr>
</c:forEach>
</table>q
</body>
</html>
