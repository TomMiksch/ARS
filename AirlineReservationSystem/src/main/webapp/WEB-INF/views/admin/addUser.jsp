<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add User</title>

<style>
.error {
	color: #ff0000;
}
</style>

<script>
    function checkMonth(value){
        var today = new Date();
        var currentYear = today.getFullYear() - 18;
        var max = 0;
        if(value > 1000){
            max = 12;
            if (currentYear == value){
                max = today.getMonth() + 1;
            }

            var monthList = "<option disabled selected>Month</option>";
            for(var i = 1; i <= max; i++){
                monthList += "<option>" + i + "</option>";
            }
            document.getElementById("month").innerHTML = monthList;
            
        }
        
    }
    
    function checkDay(year, month){
        var max = 31;
        var leap = year % 4;
        var today = new Date();
        
        if(month == 2){
            if(leap == 0){
                max = 29;
            }
            else{
                max = 28;
            }
        }
        else if(month == 4 || month == 6 || month == 9 || month == 11){
            max = 30;
        }
        else{
            max = 31;
        }
            
        if (year == today.getFullYear() - 18 && month == today.getMonth() + 1){
            max = today.getDate();
        }
            
        var dayList = "<option value='' disabled selected>Day</option>";
        for(var i = 1; i <= max; i++){
            dayList += "<option>" + i + "</option>"
        }
        document.getElementById("day").innerHTML = dayList;
    }
</script>

</head>

<body>

	<h2>Add User</h2>

	<form:form method="POST" modelAttribute="user">
		<form:input type="hidden" path="id" id="id" />
		<table>
			<tr>
				<td><label for="userType">User Type: </label></td>
				<td><form:select path="userType" items="${userTypes}"/></td>
			</tr>
		
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
                                <td><select id="year" name="dobY" onchange="checkMonth(this.value)">
                                        <option disabled selected>Year</option>
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
                                <td><select id="month" name="dobM" onchange="checkDay(document.getElementById('year').value, this.value)">
                                    </select></td>
			</tr>
                        
                        <tr>
				<td><label for="dobD">Birth Day: </label></td>
                                <td><select id="day" name="dobD">
                                    </select></td>
			</tr>
                        
                        <tr>
				<td><label for="gender">Gender: </label></td>
				<td><form:select path="gender" items="${genders}"/></td>
			</tr>
                        
			<tr>
				<td><label for="phoneNumber">Phone Number: </label></td>
				<td><form:input path="phoneNumber" id="phoneNumber" /></td>
				<td><form:errors path="phoneNumber" cssClass="error" /></td>
			</tr>

			<tr>
				<td colspan="3"><c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update" />
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register" />
						</c:otherwise>
					</c:choose></td>
			</tr>
		</table>
	</form:form>
	<br />
	<br /> Go back to
	<a href="<c:url value='home' />">home</a>
</body>
</html>