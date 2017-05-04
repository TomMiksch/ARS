<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Airline Reservation System</title>

<style>
body {
	padding-top: 65px;
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

div#map_container {
	height: 400px;
}

font {
	color: gray;
}

.overlay {
	background-color: rgba(100, 100, 100, 0.5);
	height: 450px;
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
<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCrAkKZHS_TwHiUgoZ7lPv7Rc3a5SZSpm4"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	var map;
	var CID = {
		lat : 41.975736,
		lng : -91.670641
	};
	var ORD = {
		lat : 41.974185,
		lng : -87.907171
	};
	var ALT = {
		lat : 34.467355,
		lng : -83.574585
	};
	var SFO = {
		lat : 37.621355,
		lng : -122.378730
	};
	var LCY = {
		lat : 51.504817,
		lng : 0.049550
	};

	function loadMap() {
		var cen = new google.maps.LatLng(38.483924, -101.754673);
		var myOptions = {
			zoom : 4,
			center : cen,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(document.getElementById("map_container"),
				myOptions);

		var flights = "${flightRoutes}";
		alert(flights);
	}

	function addFlightPath(orig, dest) {
		var flightPlanCoordinates = [ CID, SFO ];
		var flightPath = new google.maps.Polyline({
			path : flightPlanCoordinates,
			geodesic : true,
			strokeColor : '#FF0000',
			strokeOpacity : 1.0,
			strokeWeight : 2
		});

		flightPath.setMap(map);
	}

	function placeToCoords(location) {
		if (location === "CID") {
			return CID;
		} else if (location === "ORD") {
			return ORD;
		} else if (location === "ATL") {
			return ATL;
		} else if (location === "SFO") {
			return SFO;
		} else {
			return LCY;
		}
	}

	window.onload = function() {
		loadMap();
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
				<li class="active"><a
					href="<c:url value='/gohome' />?<c:out value = "${pageContext.request.queryString}" />">Home</a></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Actions <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value='/loginpage' />">Login</a></li>
						<li><a href="<c:url value='/register' />">Create New
								Account</a></li>
						<li><a
							href="<c:url value='/reset' />?<c:out value = "${pageContext.request.queryString}" />">Reset
								Password</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value='/loginpage' />"><span
						class="glyphicon glyphicon-log-out"></span> Login</a></li>
			</ul>
		</div>
	</nav>
	<h2>Available Flights</h2>
	<table class="table table-hover table-bordered">
		<thead>
			<tr>
				<th>Date</th>
				<th>First Class Price</th>
				<th>Business Class Price</th>
				<th>Economy Class Price</th>
				<th>Origin</th>
				<th>Destination</th>
				<th>Start Time</th>
				<th>End Time</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${flightRoutes}" var="flight">
				<tr>
					<th>${flight.date}</th>
					<th>${flight.firstClassPrice}</th>
					<th>${flight.businessClassPrice}</th>
					<th>${flight.economyClassPrice}</th>
					<th>${flight.origin}</th>
					<th>${flight.destination}</th>
					<th>${flight.startTime}</th>
					<th>${flight.endTime}</th>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8 overlay">
			<br />
			<div id="map_container"></div>
		</div>
		<div class="col-md-2"></div>
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