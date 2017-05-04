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
				<li class="active"><a
					href="<c:url value='../gohome' />?<c:out value = "${pageContext.request.queryString}" />">Home</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Actions <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value='/home' />">Logout</a></li>
						<li><a
							href="<c:url value='addUser' />?<c:out value = "${pageContext.request.queryString}" />">Add
								New User</a></li>
						<li><a
							href="<c:url value='/reset' />?<c:out value = "${pageContext.request.queryString}" />">Reset
								Password</a></li>
					</ul></li>
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
			<h2>List of Aircraft</h2>
		</div>
	</div>
	<table class="table table-hover table-bordered">
		<thead>
			<tr>
				<th>Aircraft Symbol</th>
				<th>Aircraft Type</th>
				<th>First Class Seats</th>
				<th>Business Class Seats</th>
				<th>Economy Class Seats</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${aircrafts}" var="aircraft">
				<tr>
					<th>${aircraft.symbol}</th>
					<th>${aircraft.aircraftType}</th>
					<th>${aircraft.firstClassSeats}</th>
					<th>${aircraft.businessClassSeats}</th>
					<th>${aircraft.economyClassSeats}</th>
					<th><a class="btn btn-danger"
						href="<c:url value='delete-${aircraft.id}-aircraft' />?<c:out value = "${pageContext.request.queryString}" />">Delete</a></th>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />
	<a class="btn btn-primary"
		href="<c:url value='addAircraft' />?<c:out value = "${pageContext.request.queryString}" />">Add
		Aircraft</a>
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