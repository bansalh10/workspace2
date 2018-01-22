<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %> 
<html>
<head>
  
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>FNOL</title>
   <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
  </head>
<body>
<div class="panel panel-default">
  <div class="panel-heading text-center"><h1>FNOL Processed Data</h1></div>
<div class="panel-body">
<div class="container">
<form name="searchform" method="post" action="searchfnol">
<div class="col-sm-8">
    <input class="form-control" type="text" name="name" placeholder="search">
</div>
<div class="col-sm-8">
    <input class="form-control" type="date" name="date" >
</div>
<button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-search"></span>Search</button>
<button type="reset" class="btn btn-info">Cancel</button>
</form>
</div>
<hr>
<div class='table-responsive'>
<table  class='table' >
<tr>
<th>S.NO</th>
<th>FnolName</th>
<th>Name</th>
<th>Email</th>
<th>Date</th>
<th>Time</th>
<th>SSN</th>
<th>HappinessIndex</th>
<th>CreditScoreVariation</th>
<th>IsFraud</th>
</tr>
<% int i=0; %>
<c:forEach items="${ sessionScope.fnolList }" var="fnol" >
<tr>
<td><%= ++i %></td>
<td>${ fnol.fnolId }</td>
<td>${ fnol.name }</td>
<td>${ fnol.email }</td>
<td>${ fnol.date }</td>
<td>${ fnol.time }</td>
<td>${ fnol.ssn }</td>
<td>${ fnol.happinessIndex }</td>
<td>${ fnol.creditScoreVariation }</td>
<td>${ fnol.isFraud }</td>
</tr>
</c:forEach>

</table>
</div>
  </div>
</div>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
