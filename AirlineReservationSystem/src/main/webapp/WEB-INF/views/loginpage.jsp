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
	color: #ff0000;
}
</style>
<script>
    window.onload = function() {

        // Check for LocalStorage support.
        if (localStorage) {
          // Add an event listener for form submissions
          document.getElementById("info").addEventListener("submit", function() {
            // Get the value of the name field.
            var email = document.getElementById("emailAddress").value;

            // Save the name in localStorage.
            localStorage.setItem("email", email);
          });
        }

    };
</script>
</head>
<body>
	<h1>Log in</h1>

	<form:form method="POST" modelAttribute="user" id="info">
		<form:input type="hidden" path="id" id="id" />
		<form:errors path="*" cssClass="error" id="formErrors" />
		<table>
			<tr>
				<td><label for="emailAddress">Email: </label></td>
				<td><form:input path="emailAddress" id="emailAddress" /></td>
				<td></td>
			</tr>
			<tr>
				<td><label for="password">Password: </label></td>
				<td><form:password path="password" id="password" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Log In"
					id="submitButton" /></td>
			</tr>
		</table>
	</form:form>

	<a href="<c:url value='/register' />">Create New Account</a>
	<br />
	<a href="<c:url value='/reset' />">Reset Password</a>
</body>
</html>
