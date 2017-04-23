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

</head>

<body>
	<h2>Booked Flights</h2>
	<table>
		<tr>
			<td>User Email</td>
			<td>Flight Number</td>
			<td>Class</td>
			<td>Passengers</td>
		</tr>
		<c:forEach items="${booking}" var="bookings">
			<tr>
				<td>${bookings.userEmail}</td>
				<td>${bookings.flightNumber}</td>
				<td>${bookings.seatClass}</td>
				<td>${bookings.seats}</td>
				<td><a
					href="<c:url value='delete-${bookings.id}-booking' />">Delete Booking</a></td>
				
			</tr>
		</c:forEach>
	</table>
	<br />Go back to
	<a href="<c:url value='home' />">home</a>
</body>
</html>