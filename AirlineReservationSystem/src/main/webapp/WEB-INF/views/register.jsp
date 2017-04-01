<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Form</title>

<style>
.error {
	color: #ff0000;
}
</style>

</head>

<body>

	<h2>Registration Form</h2>

	<form:form method="POST" modelAttribute="user">
		<form:input type="hidden" path="id" id="id" />
		<table>
			<tr>
				<td><label for="firstName">First Name: </label></td>
				<td><form:input path="firstName" id="firstName" /></td>
				<td><form:errors path="firstName" cssClass="error" /></td>
			</tr>
			<tr>
				<td><label for="lastName">Last Name: </label></td>
				<td><form:input path="lastName" id="lastName" /></td>
				<td><form:errors path="lastName" cssClass="error" /></td>
			</tr>
			<tr>
				<td><label for="emailAddress">Email Address </label></td>
				<td><form:input path="emailAddress" id="emailAddress" /></td>
				<td><form:errors path="emailAddress" cssClass="error" /></td>
			</tr>
                        
                        <tr>
				<td><label for="dobY">Birth Year: </label></td>
                                <td><select id="year" name="dobY">
                                    <script>
                                        var myDate = new Date();
                                        var year = myDate.getFullYear();
                                        for(var i = year - 18; i > year-100; i--){
                                            document.write('<option value="'+i+'">'+i+'</option>');
                                        }
                                    </script>
                                    </select></td>
			</tr>
                        
                        <tr>
				<td><label for="dobM">Birth Month: </label></td>
                                <td><select id="month" name="dobM">
                                    <script>
                                        for(var i = 1; i <= 12; i++){
                                            document.write('<option value="'+i+'">'+i+'</option>');
                                        }
                                    </script>
                                    </select></td>
			</tr>
                        
                        <tr>
				<td><label for="dobD">Birth Day: </label></td>
                                <td><select id="day" name="dobD">
                                    <script>
                                        for(var i = 1; i <= 31; i++){
                                            document.write('<option value="'+i+'">'+i+'</option>');
                                        }
                                    </script>
                                    </select></td>
			</tr>
                        
                        <tr>
				<td><label for="gender">Gender: </label></td>
                                <td><select name = "Gender">
                                        <option value="Male">Male</option>
                                        <option value="Female">Female</option>
                                    </select>
                                </td>
			</tr>
                        
			<tr>
				<td><label for="phoneNumber">Phone Number: </label></td>
				<td><form:input path="phoneNumber" id="phoneNumber" /></td>
				<td><form:errors path="phoneNumber" cssClass="error" /></td>
			</tr>
                        
			<tr>
				<td colspan="3"><input type="submit" value="Register" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>