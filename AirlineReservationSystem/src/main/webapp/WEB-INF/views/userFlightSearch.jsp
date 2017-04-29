<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Airline Reservation System</title>

<style>
tr:first-child {
	font-weight: bold;
	background-color: #C6C9C4;
}
</style>

<script>
	var help1 = localStorage.getItem("email");
	var help2 = localStorage.getItem("seatClass");
	var help3 = localStorage.getItem("seats");
	document.cookie = "email=" + help1;
	document.cookie = "seatClass=" + help2;
	document.cookie = "seats=" + help3;

	function getCookie(cname) {
		var name = cname + "=";
		var ca = document.cookie.split(';');
		for (var i = 0; i < ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0) == ' ') {
				c = c.substring(1);
			}
			if (c.indexOf(name) == 0) {
				return c.substring(name.length, c.length);
			}
		}
		return "";
	}
</script>

</head>

<body>
	<h2>Flights</h2>
	<table>
		<tr>
			<td>Date</td>
			<td>First Class Price</td>
			<td>Business Class Price</td>
			<td>Economy Class Price</td>
			<td>Origin</td>
			<td>Destination</td>
			<td>Start Time</td>
			<td>End Time</td>
		</tr>
		<c:forEach items="${flightRoutes}" var="flight">
			<tr>
				<td>${flight.date}</td>
				<td>${flight.firstClassPrice}</td>
				<td>${flight.businessClassPrice}</td>
				<td>${flight.economyClassPrice}</td>
				<td>${flight.origin}</td>
				<td>${flight.destination}</td>
				<td>${flight.startTime}</td>
				<td>${flight.endTime}</td>
				<td><a id="booknow"
					href="<c:url value='book-${flight.id}-booking/${flight.flightClass}/${flight.seats}?${pageContext.request.queryString}' />">Book
						Flight</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />Go back to
	<a href="<c:url value='hellouser' />">home</a>
</body>
</html>