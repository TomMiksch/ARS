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
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	var flightTimes = [ "00:00", "00:30", "01:00", "01:30", "02:00", "02:30",
			"03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00",
			"06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30",
			"10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00",
			"13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30",
			"17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00",
			"20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30" ];

	window.onload = function() {
		displayDestinations();

		// Populate the start time box.
		var startTimeBox = document.getElementById("startTime");
		for (var i = 0; i < flightTimes.length; i++) {
			var newOption = document.createElement("option");
			newOption.text = flightTimes[i];
			startTimeBox.add(newOption);
		}
		displayFlightTimes();
	};

	$(function() {
		$("#beginDate").datepicker(
				{
					dateFormat : "yy-mm-d",
					minDate : 0,
					maxDate : 365,
					showOn : "button",
					onSelect : function(dateText, inst) {
						$("#endDate").datepicker("option", "minDate",
								$("#beginDate").datepicker("getDate"));
					}
				});
		$("#endDate").datepicker({
			dateFormat : "yy-mm-dd",
			minDate : 0,
			maxDate : 365,
			showOn : "button"
		});
	});

	function displayDestinations() {
		// Remove all current elements in the destinationBox.
		var destinationBox = document.getElementById("destination");
		for (var i = destinationBox.options.length - 1; i >= 0; i--) {
			destinationBox.remove(i);
		}

		// Copy all elements and make sure nothing is selected.
		var originBox = document.getElementById("origin");
		var originBoxOptions = originBox.options;
		for (var i = 0; i < originBoxOptions.length; i++) {
			if (i != originBox.selectedIndex) {
				var currentText = originBoxOptions[i].text;
				var newOption = document.createElement("option");
				newOption.text = currentText;
				destinationBox.add(newOption);
			}
		}
		destinationBox.selectedIndex = -1;
	}

	function displayFlightTimes() {
		var endTimeBox = document.getElementById("endTime");

		// Remove all current elements.
		for (var i = endTimeBox.options.length - 1; i >= 0; i--) {
			endTimeBox.remove(i);
		}

		// Only display times that are after the currently selected start time.
		var startTimeIndex = document.getElementById("startTime").selectedIndex;
		for (var i = 0; i < flightTimes.length; i++) {
			if (i > startTimeIndex) {
				var newOption = document.createElement("option");
				newOption.text = flightTimes[i];
				endTimeBox.add(newOption);
			}
		}
		endTimeBox.selectedIndex = -1;
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
			<h2>Add Flight Route</h2>
		</div>
	</div>
	<form:form method="POST" modelAttribute="flightRoute">
		<form:input type="hidden" path="id" id="id" />
		<table>
			<tr class="spaceUnder">
				<td><label for="aircraft">Aircraft: </label></td>
				<td><form:select path="aircraft" items="${aircrafts}" /></td>
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
				<td><label for="origin">Origin: </label></td>
				<td><form:select path="origin" id="origin" items="${airports}"
						onchange="displayDestinations()" /></td>
			</tr>
			<tr class="spaceUnder">
				<td><label for="destination">Destination: </label></td>
				<td><form:select path="destination" id="destination"
						items="${airports}" /></td>
				<td><form:errors path="destination" cssClass="error" /></td>
			</tr>
			<tr class="spaceUnder">
				<td><label for="startTime">Start Time: </label></td>
				<td><form:select path="startTime" id="startTime"
						onchange="displayFlightTimes()" /></td>
			</tr>
			<tr class="spaceUnder">
				<td><label for="endTime">End Time: </label></td>
				<td><form:select path="endTime" id="endTime" /></td>
				<td><form:errors path="endTime" cssClass="error" /></td>
			</tr>
			<tr class="spaceUnder">
				<td><label for="frequency">Frequency: </label></td>
				<td><form:radiobuttons path="frequency" id="frequency"
						items="${frequencies}" /></td>
			</tr>
			<tr class="spaceUnder">
				<td><label for="beginDate">Beginning Date: </label><span
					class="datePicker"></span></td>
				<td><form:input type="text" path="beginDate" id="beginDate"
						placeholder="yyyy-mm-dd" readonly="true" /></td>
				<td><form:errors path="beginDate" cssClass="error" /></td>
			</tr>
			<tr class="spaceUnder">
				<td><label for="endDate">End Date: </label></td>
				<td><form:input type="text" path="endDate" id="endDate"
						placeholder="yyyy-mm-dd" readonly="true" /></td>
				<td><form:errors path="endDate" cssClass="error" /></td>
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
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
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