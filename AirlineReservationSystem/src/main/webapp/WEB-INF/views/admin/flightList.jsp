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
	<h2>List of Flights</h2>
	<table>
		<tr>
			<td>Date</td>
			<td>Aircraft</td>
			<td>Aircraft</td>
			<td>First Class Price</td>
			<td>Business Class Price</td>
			<td>Economy Class Price</td>
			<td>Origin</td>
			<td>Destination</td>
			<td>Start Time</td>
			<td>End Time</td>
		</tr>
		<c:forEach items="${flights}" var="flight">
			<tr>
				<td>${flight.date}</td>
				<td>${flight.aircraft}</td>
				<td>${flight.aircraft}</td>
				<td>${flight.firstClassPrice}</td>
				<td>${flight.businessClassPrice}</td>
				<td>${flight.economyClassPrice}</td>
				<td>${flight.origin}</td>
				<td>${flight.destination}</td>
				<td>${flight.startTime}</td>
				<td>${flight.endTime}</td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<br /> Go back to
	<a href="<c:url value='home' />">home</a>
</body>
</html>