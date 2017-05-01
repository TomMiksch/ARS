<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Airline Reservation System</title>

<style>
h1 {
	text-align: center;
}

h2 {
	text-align: center;
}

h3 {
	text-align: center;
}

font {
	color: gray;
}

p.copyRight {
	color: gray;
	text-align: center;
}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">Airline Reservation System</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="<c:url value='/admin/home' />?<c:out value = "${pageContext.request.queryString}" />">Home</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a>Hello, ${firstName}!</a></li>
				<li><a href="<c:url value='/home' />"><span
						class="glyphicon glyphicon-log-out"></span> Logout</a></li>
			</ul>
		</div>
	</nav>
	<div class="jumbotron">
		<div class="container">
			<h2>Booked Flights</h2>
		</div>
	</div>
	<table class="table table-hover table-bordered">
		<thead>
			<tr>
				<th>User Email</th>
				<th>Flight Number</th>
				<th>Class</th>
				<th>Passengers</th>
				<th>Confirm</th>
				<th>Dismiss</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${booking}" var="bookings">
				<tr>
					<th>${bookings.userEmail}</th>
					<th>${bookings.flightNumber}</th>
					<th>${bookings.seatClass}</th>
					<th>${bookings.seats}</th>
					<th><a class="btn btn-success"
						href="<c:url value='confirm-${bookings.userEmail}/${bookings.id}' />?<c:out value = "${pageContext.request.queryString}" />">Confirm</a></th>
					<th><a class="btn btn-danger"
						href="<c:url value='delete-${bookings.id}-booking' />?<c:out value = "${pageContext.request.queryString}" />">Dismiss</a></th>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<br>
	<br>
	<br>
	<footer class="navbar navbar-inverse navbar-fixed-bottom">
		<p class="copyRight">
			Mallory Tollefson, Thomas Miksch, Peter Li<br />&#9400; Iowa Air
			2017
		</p>
	</footer>
</body>
</html>