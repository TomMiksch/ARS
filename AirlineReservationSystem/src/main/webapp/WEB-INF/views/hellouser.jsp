<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Iowa Air</title>
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

div#map_container {
	height: 400px;
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
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCrAkKZHS_TwHiUgoZ7lPv7Rc3a5SZSpm4"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	var destinations = [ 'CID', 'ORD', 'ATL', 'SFO', 'LCY' ];

	function checkDest(value) {
		var destList = " <option disabled selected>Destination</option>";
		for (var i = 0; i <= destinations.length - 1; i++) {
			if (destinations[i] !== value) {
				destList += "<option value="+destinations[i]+">"
						+ destinations[i] + "</option>";
			}
		}
		document.getElementById("destination").innerHTML = destList;
	}

	$(function() {
		$("#dateInput").datepicker({
			dateFormat : "yy-mm-dd",
			minDate : 0,
			maxDate : 365,
			showOn : "button"
		});
	});

	function loadMap() {
		var cen = new google.maps.LatLng(38.483924, -101.754673);
		var latlng = new google.maps.LatLng(41.975736, -91.670641);
		var latlng2 = new google.maps.LatLng(41.974185, -87.907171);
		var latlng3 = new google.maps.LatLng(34.467355, -83.574585);
		var latlng4 = new google.maps.LatLng(37.621355, -122.378730);
		var latlng5 = new google.maps.LatLng(51.504817, 0.049550);
		var myOptions = {
			zoom : 4,
			center : cen,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		var map = new google.maps.Map(document.getElementById("map_container"),
				myOptions);

		var marker1 = new google.maps.Marker({
			position : latlng,
			map : map,
			title : "CID"
		});
		var marker2 = new google.maps.Marker({
			position : latlng2,
			map : map,
			title : "ORD"
		});
		var marker3 = new google.maps.Marker({
			position : latlng3,
			map : map,
			title : "ALT"
		});
		var marker4 = new google.maps.Marker({
			position : latlng4,
			map : map,
			title : "SFO"
		});
		var marker5 = new google.maps.Marker({
			position : latlng5,
			map : map,
			title : "LCY"
		});

	}
</script>
</head>

<body onload="loadMap()">
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">Airline Reservation System</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a
					href="<c:url value='/gohome' />?<c:out value = "${pageContext.request.queryString}" />">Home</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Actions <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value='/home' />">Logout</a></li>
						<li><a href="<c:url value='/register' />">Create New
								Account</a></li>
						<li><a
							href="<c:url value='/reset' />?<c:out value = "${pageContext.request.queryString}" />">Reset
								Password</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a>Hello, ${firstName}!</a></li>
				<li><a href="<c:url value='/loginpage' />"><span
						class="glyphicon glyphicon-log-out"></span> Logout</a></li>
			</ul>
		</div>
	</nav>
	<h1>Home</h1>
	<div class="container">
		<div class="row">
			<div class="col-md-5 overlay">
				<h2>Search Flights</h2>
				<br>
				<form name="reservationFlightSearchForm"
					id="reservationFlightSearchForm" method="POST"
					modelAttribute="flightroute" class="zeta">
					<input type="hidden" id="showMoreOptions" name="showMoreOptions"
						value="false" /> <input type="hidden" id="fromSearchPage"
						name="fromSearchPage" value="true" /> <label for="origin">
						From <span class="icon-required" aria-hidden="true"></span><span
						class="hidden-accessible"> (required)</span> <select name="origin"
						id="origin" onchange="checkDest(this.value)">
							<option disabled selected>Depart From</option>
							<option value="CID">CID</option>
							<option value="ORD">ORD</option>
							<option value="ATL">ATL</option>
							<option value="SFO">SFO</option>
							<option value="LCY">LCY</option>
					</select>
					</label> <br /> <br /> <label for="destination"> To <span
						class="icon-required" aria-hidden="true"></span><span
						class="hidden-accessible">(required)</span> <select
						name="destination" id="destination">
							<option disabled selected>Destination</option>
					</select>
					</label> <br /> <br /> <label for="seats"> Number of passengers <select
						name="seats" onchange="calcTotalNoOfPsgrsOnHomePage('')"
						id="seats">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
					</select>
					</label> <br /> <br /> <label for="date"> Depart<span
						class="datePicker"></span> <input id="dateInput" name="date"
						type="text" placeholder="yyyy-mm-dd" readonly />
					</label> <br /> <br /> <label for="flightClass" class="aa-display-none">
						Fare preference <select id="flightClass" name="flightClass">
							<option selected="selected" value="Economy">Economy</option>
							<option value="Business">Business</option>
							<option value="First">First</option>
					</select>
					</label> <br /> <br /> <input type="submit" value="Search"
						id="flightSearchForm.button.reSubmit" class="btn btn-primary">
				</form>
			</div>
			<div class="col-md-2"></div>
			<div class="col-md-5 overlay">
				<h2>Serviced Locations</h2>
				<div id="map_container"></div>
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
