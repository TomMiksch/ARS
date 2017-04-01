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

</head>

<body>
	<h2>Add Aircraft</h2>
	<form:form method="POST" modelAttribute="aircraft">
		<form:input type="hidden" path="id" id="id" />
		<table>
			<tr>
				<td><label for="symbol">Aircraft Symbol: </label></td>
				<td><form:input path="symbol" id="symbol" /></td>
				<td><form:errors path="symbol" cssClass="error" /></td>
			</tr>
			<tr>
				<td><label for="aircraftType">Aircraft Type: </label></td>
				<td><form:select path="aircraftType" items="${aircraftTypes}" /></td>
			</tr>
			<tr>
				<td><label for="firstClassSeats">Number of First Class
						Seats: </label></td>
				<td><form:input path="firstClassSeats" id="firstClassSeats" /></td>
				<td><form:errors path="firstClassSeats" cssClass="error" /></td>
			</tr>
			<tr>
				<td><label for="businessClassSeats">Number of Business
						Class Seats: </label></td>
				<td><form:input path="businessClassSeats"
						id="businessClassSeats" /></td>
				<td><form:errors path="businessClassSeats" cssClass="error" /></td>
			</tr>
			<tr>
				<td><label for="economyClassSeats">Number of Economy
						Class Seats: </label></td>
				<td><form:input path="economyClassSeats" id="economyClassSeats" /></td>
				<td><form:errors path="economyClassSeats" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
	<br />
</body>
</html>