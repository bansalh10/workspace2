<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Flight Information</title>
</head>
<body>
	<div class="container">
		<div class="row">
		<a href="login.html" style="float: right; margin-right: 10px">Logout</a>
		<h4 style="float: left">Welcome ${user.username}</h4>
			<div class="col-sm-6 col-md-4 col-md-offset-4" style="margin-left:250px">
				<h1 class="text-center login-title">Flight Information</h1>
				<form:form commandName="userchoices" class="form-group">
					<table>
						<tr>
							<td><label for="textinput1">Departure Location</label></td>
							<td><form:select path="flight.departureLocation"
									required="required" class="form-control">
									<option value="DEL">Delhi(DEL)</option>
									<option value="MUB">Mumbai(MUB)</option>
									<option value="BGR">Banglore(BGR)</option>
								</form:select></td>
						</tr>
						<tr>
							<td><label for="textinput2">Arrival Location</label></td>
							<td><form:select path="flight.arrivalLocation"
									required="required" class="form-control">
									<option value="MUB">Mumbai(MUB)</option>
									<option value="BGR">Banglore(BGR)</option>
									<option value="DEL">Delhi(DEL)</option>
								</form:select></td>
						</tr>
						<tr>
							<td><label for="textinput3">Date of flight</label></td>
							<td><form:input type="date" path="flight.validTill"
									required="required" class="form-control" /></td>
						</tr>
						<tr>
							<td><label for="textinput4">Plane Class</label></td>
							<td><form:select path="flight.planeClass"
									required="required" class="form-control">
									<option value="e">Economy
										</options>
									<option value="b">Business
										</options>
								</form:select></td>
						</tr>
						<tr>
							<td><label for="textinput5">Sorting Prefrence</label></td>
							<td><form:select path="userprefrence" required="required"
									class="form-control">
									<option value="fare">Fare
										</options>
									<option value="flightDuration">Flight Duration
										</options>
								</form:select></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit" value="Find Flight" /></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	</div>
	<div class="container" style="margin-left: 150px">
		<div class="row">
			<table width="75%" border="1px"
				style="text-align: center;">
				<tr>
					<th>Sno.</th>
					<th>Flight Number</th>
					<th>Departure Location</th>
					<th>Arrival Location</th>
					<th>Date</th>
					<th>Time</th>
					<th>Flight Duration</th>
					<th>Flight Fare</th>
					<th>Availability</th>
				</tr>
				<%
					int i = 0;
				%>
				<c:forEach items="${list}" var="flight">
					<tr style="text-align: center">
						<td><%=++i%></td>
						<td>${flight.flightNumber}</td>
						<td>${flight.departureLocation}</td>
						<td>${flight.arrivalLocation}</td>
						<td>${flight.validTill}</td>
						<td>${flight.flightTime}</td>
						<td>${flight.flightDuration}</td>
						<td>${flight.fare}</td>
						<td>${flight.seatAvaliability}</td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>
</body>
</html>