<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Confirmation Page</title>
</head>
<body>
	<h2>Registration Successful</h2>
	Thank you, ${firstName} for registering an account with Iowa Air!
	<br />
	<br /> Log in with your new account
	<a href="<c:url value='/loginpage' />">here</a>

</body>

</html>