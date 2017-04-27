<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Home</title>

<style>
.error {
	color: #ff0000;
}
</style>

</head>

<body>
	<h2>What would you like to do?</h2>
	<br />Users:
	<a href="<c:url value='userList' />">List</a>
	<a href="<c:url value='addUser' />">Add</a>
	<br />Aircrafts:
	<a href="<c:url value='aircraftList' />">List</a>
	<a href="<c:url value='addAircraft' />">Add</a>
	<br />Flight Routes:
	<a href="<c:url value='flightRouteList' />">List</a>
	<a href="<c:url value='addFlightRoute' />">Add</a>
	<br />Flights:
	<a href="<c:url value='flightList' />">List</a>
	<br />Bookings:
	<a href="<c:url value='bookingList' />">List</a>
</body>
</html>