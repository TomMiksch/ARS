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

body {
	padding-top: 65px;
	background-image:
		url("https://www.scienceabc.com/wp-content/uploads/2015/10/airplane.jpg");
	background-size: cover;
	-webkit-filter: blur(0px);
}

h1 {
	text-align: center;
}

h2 {
	text-align: center;
}

h3 {
	text-align: center;
}

font {
	color: gray;
}

.overlay {
	background-color: rgba(100, 100, 100, 0.5);
	height: 500px;
}

label {
	display: block;
	width: 120px;
}

p.copyRight {
	color: gray;
	text-align: center;
}
</style>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	function checkMonth(value) {
		var today = new Date();
		var currentYear = today.getFullYear() - 18;
		var max = 0;
		if (value > 1000) {
			max = 12;
			if (currentYear == value) {
				max = today.getMonth() + 1;
			}

			var monthList = "<option disabled selected>Month</option>";
			for (var i = 1; i <= max; i++) {
				monthList += "<option>" + i + "</option>";
			}
			document.getElementById("month").innerHTML = monthList;

		}

	}

	function checkDay(year, month) {
		var max = 31;
		var leap = year % 4;
		var today = new Date();

		if (month == 2) {
			if (leap == 0) {
				max = 29;
			} else {
				max = 28;
			}
		} else if (month == 4 || month == 6 || month == 9 || month == 11) {
			max = 30;
		} else {
			max = 31;
		}

		if (year == today.getFullYear() - 18 && month == today.getMonth() + 1) {
			max = today.getDate();
		}

		var dayList = "<option value='' disabled selected>Day</option>";
		for (var i = 1; i <= max; i++) {
			dayList += "<option>" + i + "</option>"
		}
		document.getElementById("day").innerHTML = dayList;
	}
</script>
</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">Airline Reservation System</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="<c:url value='/home' />">Home</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Actions <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value='/loginpage' />">Login</a></li>
						<li><a href="<c:url value='/register' />">Create New
								Account</a></li>
						<li><a href="<c:url value='/reset' />">Reset Password</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value='/loginpage' />"><span
						class="glyphicon glyphicon-log-in"></span> Login</a></li>
			</ul>
		</div>
	</nav>
	<h2>Registration Form</h2>
	<div class="container">
		<div class="row">
			<div class="col-md-5 overlay">
				<br />
				<form:form method="POST" modelAttribute="user">
					<form:input type="hidden" path="id" id="id" />
					<!-- First Name. -->
					<label for="firstName">First Name: </label>
					<form:input path="firstName" id="firstName" />
					<form:errors path="firstName" cssClass="error" />
					<br />
					<br />
					<!-- Last Name. -->
					<label for="lastName">Last Name: </label>
					<form:input path="lastName" id="lastName" />
					<form:errors path="lastName" cssClass="error" />
					<br />
					<br />
					<!-- Email Address. -->
					<label for="emailAddress">Email Address </label>
					<form:input path="emailAddress" id="emailAddress" />
					<form:errors path="emailAddress" cssClass="error" />
					<br />
					<br />
					<!-- Birth Year. -->
					<label for="dobY">Birth Year: </label>
					<select id="year" name="dobY" onchange="checkMonth(this.value)">
						<option disabled selected>Year</option>
						<script type="text/javascript">
							var myDate = new Date();
							var year = myDate.getFullYear();
							for (var i = year - 18; i > year - 100; i--) {
								document.write('<option value="'+i+'">' + i
										+ '</option>');
							}
						</script>
					</select>
					<br />
					<br />
					<!-- Birth Month. -->
					<label for="dobM">Birth Month: </label>
					<select id="month" name="dobM"
						onchange="checkDay(document.getElementById('year').value, this.value)">
					</select>
					<br />
					<br />
					<!-- Birth Day. -->
					<label for="dobD">Birth Day: </label>
					<select id="day" name="dobD">
					</select>
					<br />
					<br />
					<!-- Gender -->
					<label for="gender">Gender: </label>
					<select name="Gender">
						<option value="Male">Male</option>
						<option value="Female">Female</option>
					</select>
					<br />
					<br />
					<!-- Phone Number. -->
					<label for="phoneNumber">Phone Number: </label>
					<form:input path="phoneNumber" id="phoneNumber" />
					<form:errors path="phoneNumber" cssClass="error" />
					<br />
					<br />
					<input class="btn btn-primary" type="submit" value="Register" />
				</form:form>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<footer class="navbar navbar-inverse navbar-fixed-bottom">
		<p class="copyRight">
			Mallory Tollefson, Thomas Miksch, Peter Li<br />&#9400; Iowa Air
			2017
		</p>
	</footer>
</body>
</html>