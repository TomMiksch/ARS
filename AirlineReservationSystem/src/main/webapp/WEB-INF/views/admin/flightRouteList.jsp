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
	<h2>List of Flight Routes</h2>
	<table>
		<tr>
			<td>Aircraft</td>
			<td>First Class Price</td>
			<td>Business Class Price</td>
			<td>Economy Class Price</td>
			<td>Origin</td>
			<td>Destination</td>
			<td>Start Time</td>
			<td>End Time</td>
			<td>Frequency</td>
			<td>Beginning</td>
			<td>Ending</td>
		</tr>
		<c:forEach items="${flightRoutes}" var="flightRoute">
			<tr>
				<td>${flightRoute.aircraft}</td>
				<td>${flightRoute.firstClassPrice}</td>
				<td>${flightRoute.businessClassPrice}</td>
				<td>${flightRoute.economyClassPrice}</td>
				<td>${flightRoute.origin}</td>
				<td>${flightRoute.destination}</td>
				<td>${flightRoute.startTime}</td>
				<td>${flightRoute.endTime}</td>
				<td>${flightRoute.frequency}</td>
				<td>${flightRoute.beginDate}</td>
				<td>${flightRoute.endDate}</td>
				<td><a
					href="<c:url value='delete-${flightRoute.id}-flightRoute' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<a href="<c:url value='addFlightRoute' />">Add Flight Route</a>
	<br /> Go back to
	<a href="<c:url value='home' />">home</a>
</body>
</html>