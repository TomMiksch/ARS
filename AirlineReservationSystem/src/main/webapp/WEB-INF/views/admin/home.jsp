<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Home</title>

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
				<li class="active"><a
					href="<c:url value='/admin/home' />?<c:out value = "${pageContext.request.queryString}" />">Home</a></li>
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
			<h2>What would you like to do?</h2>
		</div>
	</div>
	<div class="container">
		<div class="accordion" id="mainView">
			<div class="panel">
				<a class="btn btn-info btn-lg" data-toggle="collapse"
					data-target="#collapsible-1" data-parent="mainView">Users</a>
				<div id="collapsible-1" class="collapse">
					<table class="table table-hover">
						<tbody>
							<tr>
								<td><a
									href="<c:url value='userList' />?<c:out value = "${pageContext.request.queryString}" />"
									role="button">List Current Users</a></td>
							</tr>
							<tr>
								<td><a
									href="<c:url value='addUser' />?<c:out value = "${pageContext.request.queryString}" />"
									role="button">Add New User (Employee/Administrator Only)</a></td>
							</tr>
					</table>
				</div>
			</div>
			<div class="panel">
				<a class="btn btn-info btn-lg" data-toggle="collapse"
					data-target="#collapsible-2" data-parent="mainView">Aircraft</a>
				<div id="collapsible-2" class="collapse">
					<table class="table table-hover">
						<tbody>
							<tr>
								<td><a
									href="<c:url value='aircraftList' />?<c:out value = "${pageContext.request.queryString}" />"
									role="button">List Current Aircraft</a></td>
							</tr>
							<tr>
								<td><a
									href="<c:url value='addAircraft' />?<c:out value = "${pageContext.request.queryString}" />"
									role="button">Add New Aircraft</a></td>
							</tr>
					</table>
				</div>
			</div>
			<div class="panel">
				<a class="btn btn-info btn-lg" data-toggle="collapse"
					data-target="#collapsible-3" data-parent="mainView">Flight
					Routes</a>
				<div id="collapsible-3" class="collapse">
					<table class="table table-hover">
						<tbody>
							<tr>
								<td><a
									href="<c:url value='flightRouteList' />?<c:out value = "${pageContext.request.queryString}" />"
									role="button">List Current Flight Routes</a></td>
							</tr>
							<tr>
								<td><a
									href="<c:url value='addFlightRoute' />?<c:out value = "${pageContext.request.queryString}" />"
									role="button">Add New Flight Route</a></td>
							</tr>
							<tr>
								<td><a
									href="<c:url value='editFlights' />?<c:out value = "${pageContext.request.queryString}" />"
									role="button">Edit Existing Flight Routes</a></td>
							</tr>
					</table>
				</div>
			</div>
			<div class="panel">
				<a class="btn btn-info btn-lg" data-toggle="collapse"
					data-target="#collapsible-4" data-parent="mainView">Bookings</a>
				<div id="collapsible-4" class="collapse">
					<table class="table table-hover">
						<tbody>
							<tr>
								<td><a
									href="<c:url value='bookingList' />?<c:out value = "${pageContext.request.queryString}" />"
									role="button">List Bookings Pending Verification</a></td>
							</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- Add in some spacing at the bottom. -->
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