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
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>Login</title>
    </head>
    <body>
        <h1>Log in</h1>
        
        <form action = "LoginPage.php" method="post">
            <input type="text" placeholder="Username" name="username"><br><br>
            <input type="password" placeholder="Password" name="password"><br><br>
            <input type="submit" name="Login" value="Login"/><br><br>
        </form>
        
        <a href="<c:url value='/list' />">List of All Users</a>
    </body>
</html>
