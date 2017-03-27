<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Airline Reservation System</title>

<style>
tr:first-child {
	font-weight: bold;
	background-color: #C6C9C4;
}
</style>

</head>


<body>
	<h2>List of Users</h2>
	<table>
		<tr>
			<td>User Type</td>
			<td>First Name</td>
			<td>Last Name</td>
			<td>Email Address</td>
                        <td>Date of Birth</td>
                        <td>Gender</td>
                        <td>Phone Number</td>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.userType}</td>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.emailAddress}</td>
                                <td>${user.dob}</td>
                                <td>${user.gender}</td>
                                <td>${user.phoneNumber}</td>
				<td><a href="<c:url value='/delete-${user.id}-user' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<a href="<c:url value='/new' />">Add New User</a>
</body>
</html>