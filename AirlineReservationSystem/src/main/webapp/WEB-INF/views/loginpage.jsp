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
        
        <form:form method="POST" modelAttribute="user">
	    <form:input type="hidden" path="id" id="id" />
                <table>
		    <tr>
			<td><label for="emailAddress">User Name: </label></td>
                        <td><form:input path="emailAddress" id="emailAddress" /></td>
                        <td><form:errors path="emailAddress" cssClass="error" /></td>
		    </tr>
                    <tr>
                        <td><label for="password">Password: </label></td>
                        <td><form:input path="password" id="password" /></td>
                        <td><form:errors path="password" cssClass="error" /></td>
                    </tr>
                    <tr>
                        <td colspan="3"><input type="submit" value="Log In" /></td>
                    </tr>
                </table>
        </form:form>
        
        <a href="<c:url value='/register' />">Create New Account</a>
    </body>
</html>
