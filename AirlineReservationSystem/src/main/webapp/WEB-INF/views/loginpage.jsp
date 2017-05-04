<%-- 
    Document   : loginpage
    Created on : Mar 7, 2017, 8:21:17 PM
    Author     : Tom
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<title>Login</title>
<style>
.error {
	color: #FF0000;
}

body {
	padding-top: 65px;
	background-image:
		url("https://www.scienceabc.com/wp-content/uploads/2015/10/airplane.jpg");
	background-size: cover;
	-webkit-filter: blur(0px);
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

.overlay {
	background-color: rgba(100, 100, 100, 0.5);
	height: 500px;
}

font {
	color: gray;
}

p.copyRight {
	color: gray;
	text-align: center;
}

label {
	display: block;
	width: 100px;
}

.black-link {
	color: #303030;
	font-size: 110%;
	text-decoration: underline;
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
				<li class="active"><a href="<c:url value='/gohome' />">Home</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Actions <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value='/loginpage' />">Login</a></li>
						<li><a href="<c:url value='/register' />">Create New
								Account</a></li>
						<li><a
							href="<c:url value='/reset' />?<c:out value = "${pageContext.request.queryString}" />">Reset
								Password</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value='/loginpage' />"><span
						class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</ul>
		</div>
	</nav>
	<h1>Log in</h1>
	<div class="container">
		<div class="row">
			<div class="col-md-5 overlay">
				<form:form method="POST" modelAttribute="user" id="info">
					<form:input type="hidden" path="id" id="id" />
					<form:errors path="*" cssClass="error" id="formErrors" />
					<br />
					<br />
					<!-- Email. -->
					<label for="emailAddress">Email: </label>
					<form:input path="emailAddress" id="emailAddress" />
					<br />
					<br />
					<!-- Password -->
					<label for="password">Password: </label>
					<form:password path="password" id="password" />
					<br />
					<br />
					<input class="btn btn-primary" type="submit" value="Log In"
						id="submitButton" />
				</form:form>
				<br /> <br /> <a class="black-link"
					href="<c:url value='/register' />">Create New Account</a> <br /> <br />
				<a class="black-link"
					href="<c:url value='/reset' />?<c:out value = "${pageContext.request.queryString}" />">Reset
					Password</a>
			</div>
		</div>
	</div>

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
