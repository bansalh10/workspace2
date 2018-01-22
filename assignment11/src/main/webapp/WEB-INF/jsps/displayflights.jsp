<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="assets/css/bootstrap.css">
<title>Available Flights</title>
</head>
<body>
<div class="jumbotron text-center">
    <h1>Flight Search Portal</h1>
    </div>
     <a href="logout.html" style="float: right; margin-right: 10px">Logout</a>
     
    <br>
		<div class='table-responsive'>
			<table class="table">
<tr><th>S.NO</th><th>FlightNo.</th>
<!-- <th>DepLoc</th><th>ArrLoc</th><th>ValidTill</th> -->
<th>FlightTime</th><th>Duration</th><th>Fare</th>
<!-- <th>Class</th> -->
</tr>
<% int i=1; %>
<c:forEach items="${flights}" var="flight" >
<tr >
<td><%= i++ %></td>
<td>${ flight.flightNumber }</td>
<%-- <td>${ flight.depLoc }</td> --%>
<%-- <td>${ flight.arrLoc }</td> --%>
<%-- <td>${flight.validTill }</td> --%>
<td>${flight.flightTime }</td>
<td>${flight.flightDuration }</td>
<td>${flight.fare }</td>
<%-- <td>${flight.flightclass.flightClass }</td> --%>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>