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
	<h2>List of Aircraft</h2>
	<table>
		<tr>
			<td>Aircraft Symbol</td>
			<td>Aircraft Type</td>
			<td>First Class Seats</td>
			<td>Business Class Seats</td>
			<td>Economy Class Seats</td>
		</tr>
		<c:forEach items="${aircrafts}" var="aircraft">
			<tr>
				<td>${aircraft.symbol}</td>
				<td>${aircraft.aircraftType}</td>
				<td>${aircraft.firstClassSeats}</td>
				<td>${aircraft.businessClassSeats}</td>
				<td>${aircraft.economyClassSeats}</td>
				<td><a href="<c:url value='delete-${aircraft.id}-aircraft' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<a href="<c:url value='addAircraft' />">Add Aircraft</a>
	<br /> Go back to
	<a href="<c:url value='home' />">home</a>
</body>
</html>