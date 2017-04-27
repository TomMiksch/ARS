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
</style>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	
</script>

</head>

<body>
	<h2>Edit Flight Route</h2>
	<form:form method="POST" modelAttribute="flightRoute">
		<form:input type="hidden" path="id" id="id" />
		<table>
			<tr>
				<td><label for="symbol">Flight Route: </label></td>
				<td><form:select path="symbol" items="${flightID}" /></td>
			</tr>
			<tr>
				<td><label for="firstClassPrice">First Class Price: </label></td>
				<td><form:input path="firstClassPrice" id="firstClassPrice" /></td>
				<td><form:errors path="firstClassPrice" cssClass="error" /></td>
			</tr>
			<tr>
				<td><label for="businessClassPrice">Business Class
						Price: </label></td>
				<td><form:input path="businessClassPrice"
						id="businessClassPrice" /></td>
				<td><form:errors path="businessClassPrice" cssClass="error" /></td>
			</tr>
			<tr>
				<td><label for="economyClassPrice">Economy Class Price:
				</label></td>
				<td><form:input path="economyClassPrice" id="economyClassPrice" /></td>
				<td><form:errors path="economyClassPrice" cssClass="error" /></td>
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