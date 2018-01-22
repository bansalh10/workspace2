<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="assets/css/bootstrap.css">
<title>FlightSearch</title>
</head>
<body>
<div class="jumbotron text-center">
    <h1>Flight Search Portal</h1>
    </div>
    <div class="container">
		<div class="row">
    <span  style="float: left">Welcome ${ user.username}</span>
    <a href="logout.html" style="float: right; margin-right: 10px">Logout</a>
    <div class="col-sm-6 col-md-4 col-md-offset-4" style="margin-left:250px">
    <p>Please fill the details to search</p>
    <form:form commandName="flightdetails" class="form-group">
    <table>
    <tr>
    <td>
    <form:label path="flight.depLoc">Departure Location</form:label>

    </td>
    <td>
    <form:input  list="datalist" autocomplete="off"  placeholder="select" path="flight.depLoc" class="form-control"/>
   <form:errors path="flight.depLoc"></form:errors> 
    <datalist id="datalist" >
    <option value="" label="select"/>
    <c:forEach items="${locations}" var="location">
     <option value="${location.key }" label="${location.value }" /> 
    </c:forEach>
<!--                     <option value="DEL" label="Delhi" /> -->
<!--                     <option value="MUB" label="Mumbai" /> -->
<!--                     <option value="BNL" label="Barnala" /> -->

            </datalist>
    </td>
    </tr>
    <tr>
    <td>
    <form:label path="flight.arrLoc">Arrival Location</form:label>
    </td>
    <td>
<%--     <p>${flightdetails.flight.arrLoc}</p> --%>
<form:input class="form-control"  list="datalist" autocomplete="off"  path="flight.arrLoc" placeholder="select"  />
   <form:errors path="flight.arrLoc"></form:errors> 
    </td>
    </tr>
    <tr>
    <td>
    <form:label path="flightClass.flightClass">Flight Class</form:label>
    </td>
    <td>
    <form:select class="form-control" path="flightClass.flightClass">
    <form:option style="display:none" value="none" selected="selected" disabled="disabled" label="select"></form:option>
    <form:option value="E" label="E"></form:option>
    <form:option value="B" label="B"></form:option>
    <form:option value="EB" label="EB"></form:option>
    </form:select>
    </td>
    </tr>
    <tr>
    <td>
    <form:label path="flight.validTill">Date(yyyy-MM-dd)</form:label>
    </td>
    <td>
    <form:input class="form-control" type="date" path="flight.validTill"/>
    <form:errors path="flight.validTill"></form:errors>
    </td>
    </tr>
      <tr>
    <td>
    <form:label path="outputPreference">Preference</form:label>
    </td>
    <td>
    <form:select class="form-control" path="outputPreference" placeholder="select">
<%--     <form:option style="display:none" value="none" selected="selected" disabled="disabled" label="select"></form:option> --%>
    <form:option value="F" label="FARE"></form:option>
    <form:option value="FD" label="FARE,Duration"></form:option>

    </form:select>
    </td>
    </tr>
    <tr>
<td></td>
<td><input type="submit" value="Search"></td>
</tr>
    </table>
    </form:form>
    </div>
    </div>
    </div>
    
</body>
</html>