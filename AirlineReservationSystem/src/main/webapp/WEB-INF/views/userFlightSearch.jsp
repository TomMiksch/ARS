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

<script>
    var help1 = localStorage.getItem("email");
    var help2 = localStorage.getItem("seatClass");
    var help3 = localStorage.getItem("seats");
    document.cookie = "email=" + help1;
    document.cookie = "seatClass=" + help2;
    document.cookie = "seats=" + help3;
    
    function getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for(var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }
</script>

</head>

<body>
	<h2>Flights</h2>
        <form:input type="hidden" id="userEmail"/>
        <form:input type="hidden" id="seatClass"/>
        <form:input type="hidden" id="seats"/>
	<table>
		<tr>
			<td>Aircraft</td>
			<td>First Class Price</td>
			<td>Business Class Price</td>
			<td>Economy Class Price</td>
			<td>Origin</td>
			<td>Destination</td>
			<td>Start Time</td>
			<td>End Time</td>
			<td>Frequency</td>
		</tr>
		<c:forEach items="${flightRoutes}" var="flightRoute">
			<tr>
				<td>${flightRoute.aircraft}</td>
				<td>${flightRoute.firstClassPrice}</td>
				<td>${flightRoute.businessClassPrice}</td>
				<td>${flightRoute.economyClassPrice}</td>
				<td>${flightRoute.origin}</td>
				<td>${flightRoute.destination}</td>
				<td>${flightRoute.startTime}</td>
				<td>${flightRoute.endTime}</td>
				<td>${flightRoute.frequency}</td>
				<td><a id="booknow"
					href="<c:url value='book-${flightRoute.id}-booking' />">Book Flight</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />Go back to
	<a href="<c:url value='hellouser' />">home</a>
        
</form:form>
</body>
</html>