<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset Password</title>

<style>
.error {
	color: #ff0000;
}
</style>

</head>

<body>

	<h2>Reset Form</h2>
        
        <form:form method="POST" modelAttribute="user">
		<form:input type="hidden" path="id" id="id" />
		<form:errors path="*" cssClass="error" />
		<table>
			<tr>
				<td><label for="emailAddress">Email: </label></td>
				<td><form:input path="emailAddress" id="emailAddress" /></td>
				<td></td>
			</tr>
			<tr>
				<td><label for="password">Old Password: </label></td>
				<td><form:password path="password" id="password" /></td>
			</tr>
                        <!-- User phoneNumber as temporary holder -->
                        <tr>
                                <td><label for=phoneNumber">New Password: </label> </td>
                                <td><form:input type = "number" path="phoneNumber" id="phoneNumber" /></td>
                        </tr>
			<tr>
				<td colspan="3"><input type="submit" value="Log In" /></td>
			</tr>
		</table>
	</form:form>

	<br />
	<br /> Go back to
	<a href="<c:url value='/list' />">List of All Users</a>
            
</body>
</html>