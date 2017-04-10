<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Aircraft</title>

<style>
.error {
	color: #ff0000;
}
</style>

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
	<h2>Add Aircraft</h2>
	<form:form method="POST" modelAttribute="aircraft">
		<form:input type="hidden" path="id" id="id" />
		<table>
			<tr>
				<td><label for="aircraftType">Aircraft Type: </label></td>
				<td><form:select id="aircraftSelect" path="aircraftType"
						items="${aircraftTypes}" onchange="displaySeatNumbers()" /></td>
			</tr>
			<tr>
				<td><label for="firstClassSeats">Number of First Class
						Seats: </label></td>
				<td><form:input path="firstClassSeats" id="firstClassSeats"
						readonly="true" /></td>
			</tr>
			<tr>
				<td><label for="businessClassSeats">Number of Business
						Class Seats: </label></td>
				<td><form:input path="businessClassSeats"
						id="businessClassSeats" readonly="true" /></td>
			</tr>
			<tr>
				<td><label for="economyClassSeats">Number of Economy
						Class Seats: </label></td>
				<td><form:input path="economyClassSeats" id="economyClassSeats"
						readonly="true" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
	<br />
	<br /> Go back to
	<a href="<c:url value='home' />">home</a>
</body>
</html>