<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Aircraft</title>

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
<script>
	var AircraftInfo = {
		Airbus_A330 : {
			id : "Airbus A330",
			firstClass : 10,
			businessClass : 50,
			economy : 250
		},
		Airbus_A350 : {
			id : "Airbus A350",
			firstClass : 15,
			businessClass : 50,
			economy : 280
		},
		Airbus_A380 : {
			id : "Airbus A380",
			firstClass : 12,
			businessClass : 60,
			economy : 399
		},
		BOEING_737 : {
			id : "Boeing 737",
			firstClass : 0,
			businessClass : 20,
			economy : 195
		},
		BOEING_747 : {
			id : "Boeing 747",
			firstClass : 10,
			businessClass : 70,
			economy : 386
		},
		BOEING_757 : {
			id : "Boeing 757",
			firstClass : 12,
			businessClass : 60,
			economy : 360
		},
		BOEING_767 : {
			id : "Boeing 767",
			firstClass : 20,
			businessClass : 50,
			economy : 175
		},
		BOEING_777 : {
			id : "Boeing 777",
			firstClass : 20,
			businessClass : 60,
			economy : 240
		},
		BOEING_787 : {
			id : "Boeing 787",
			firstClass : 20,
			businessClass : 60,
			economy : 320
		}
	};

	window.onload = function() {
		displaySeatNumbers();
	};

	function displaySeatNumbers() {
		var selectBox = document.getElementById("aircraftSelect");
		var selectedValue = selectBox.options[selectBox.selectedIndex].value;
		for ( var aircraftInfo in AircraftInfo) {
			if (selectedValue == AircraftInfo[aircraftInfo].id) {
				document.getElementById('firstClassSeats').value = AircraftInfo[aircraftInfo].firstClass;
				document.getElementById('businessClassSeats').value = AircraftInfo[aircraftInfo].businessClass;
				document.getElementById('economyClassSeats').value = AircraftInfo[aircraftInfo].economy;
			}
		}
	}
</script>

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
			<h2>Add New Aircraft</h2>
		</div>
	</div>
	<form:form method="POST" modelAttribute="aircraft">
		<form:input type="hidden" path="id" id="id" />
		<table>
			<tr class="spaceUnder">
				<td><label for="aircraftType">Aircraft Type: </label></td>
				<td><form:select id="aircraftSelect" path="aircraftType"
						items="${aircraftTypes}" onchange="displaySeatNumbers()" /></td>
			</tr>
			<tr class="spaceUnder">
				<td><label for="firstClassSeats">Number of First Class
						Seats: </label></td>
				<td><form:input path="firstClassSeats" id="firstClassSeats"
						readonly="true" /></td>
			</tr>
			<tr class="spaceUnder">
				<td><label for="businessClassSeats">Number of Business
						Class Seats: </label></td>
				<td><form:input path="businessClassSeats"
						id="businessClassSeats" readonly="true" /></td>
			</tr>
			<tr class="spaceUnder">
				<td><label for="economyClassSeats">Number of Economy
						Class Seats: </label></td>
				<td><form:input path="economyClassSeats" id="economyClassSeats"
						readonly="true" /></td>
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