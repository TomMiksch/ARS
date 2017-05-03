<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Flight Route</title>

<style>
.error {
	color: #ff0000;
}

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

tr.spaceUnder>td {
	padding-bottom: 1em;
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
						<li><a href="<c:url value='/reset' />?<c:out value = "${pageContext.request.queryString}" />">Reset Password</a></li>
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
			<h2>Edit Flight Route</h2>
		</div>
	</div>
	<form:form method="POST" modelAttribute="flightRoute">
		<form:input type="hidden" path="id" id="id" />
		<table>
			<tr class="spaceUnder">
				<td><label for="symbol">Flight Route: </label></td>
				<td><form:select path="symbol" items="${flightID}" /></td>
			</tr>
			<tr class="spaceUnder">
				<td><label for="firstClassPrice">First Class Price: </label></td>
				<td><form:input path="firstClassPrice" id="firstClassPrice" /></td>
				<td><form:errors path="firstClassPrice" cssClass="error" /></td>
			</tr>
			<tr class="spaceUnder">
				<td><label for="businessClassPrice">Business Class
						Price: </label></td>
				<td><form:input path="businessClassPrice"
						id="businessClassPrice" /></td>
				<td><form:errors path="businessClassPrice" cssClass="error" /></td>
			</tr>
			<tr class="spaceUnder">
				<td><label for="economyClassPrice">Economy Class Price:
				</label></td>
				<td><form:input path="economyClassPrice" id="economyClassPrice" /></td>
				<td><form:errors path="economyClassPrice" cssClass="error" /></td>
			</tr>
			<tr class="spaceUnder">
				<td><input class="btn btn-primary" type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
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