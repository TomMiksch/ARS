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
	background-image:
		url("https://www.scienceabc.com/wp-content/uploads/2015/10/airplane.jpg");
	background-size: cover;
	-webkit-filter: blur(0px);
}
</style>

<script>
	window.onload = function() {
		var email = localStorage.getItem("email");

		var firstName = "${firstName}";
		localStorage.setItem("firstName", firstName);

		document.getElementById("greet").innerHTML = "Hello, " + firstName;
		//document.getElementById("greet").innerHTML = localStorage.getItem("email");

		if (localStorage) {
			// Add an event listener for form submissions
			document
					.getElementById("reservationFlightSearchForm")
					.addEventListener(
							"submit",
							function() {
								// Get the value of the name field.
								var seats1 = document.getElementById("seats").value;
								var seatClass = document
										.getElementById("seatClass").value;

								// Save the name in localStorage.
								localStorage.setItem("seats", seats1);
								localStorage.setItem("seatClass", seatClass);
							});
		}
	};

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

	var today = new Date();
	document.getElementsByName("departDate")[0].setAttribute('min', today);
</script>

</head>

<!-- The begining of what Peter added for our login page -->

<article id="login">
	<h2 class="left gamma">Home</h2>

	<!--
    <form id="loginForm" method="post" action="<c:url value='/new' />">
	<input type="hidden" name="previousPage" value="/homePage.do?locale=" />
    <input type="hidden" name="activeTab" id="loginForm.activeTab"/>
    <label for="loginForm.loginId">
        Username
		<input type="text" name="loginId" maxlength="52" size="20" value="" id="loginForm.loginId" autocomplete="off" aria-required="true"/>
	</label>
    <br />	
	<label for="loginForm.lastName">
        Last name
		<input type="text" name="lastName" maxlength="35" size="20" value="" id="loginForm.lastName" autocomplete="off" aria-required="true"/>
	</label>
    <br />
    <label for="loginForm.password">
	    Password
		<input type="password" name="password" maxlength="16" size="20"	value="" id="loginForm.password" aria-required="true"/>
	</label>
	
	<div class="span12 pushLeft forgot-link-wrapper">
		<p><span class="hidden-accessible">Reset your password?</span></p>
	</div>
	<div class="remember-submit-wrapper">
		<div class="span6 pushLeft customComponent">
		    <input type="checkbox" id="loginForm.rememberMeCheckbox" name="rememberMe"  >
		    <label for="loginForm.rememberMeCheckbox">
		        <span class="control left"></span>
		        <span class="lbl left">Remember me</span>
		    </label>
		</div>
		<div class="span6 pushLeft">
			<input type="submit" name="_button_go" value="Log in" class="btn btn-fullWidth" />
		</div>
	</div>
	</form>
-->


</article>

<h3 id="greet"></h3>

<a href="<c:url value='/home' />">Log Out</a>
<br />
<a href="<c:url value='/reset' />">Reset Password</a>
<br />
<div id="aa-booking-module"
	class="ui-tabs-panel ui-widget-content ui-corner-bottom">
	<form name="reservationFlightSearchForm"
		id="reservationFlightSearchForm" method="POST"
		modelAttribute="flightroute" class="zeta">
		<input type="hidden" id="showMoreOptions" name="showMoreOptions"
			value="false" /> <input type="hidden" id="fromSearchPage"
			name="fromSearchPage" value="true" />
		<div class="row-form">
			<%--<div class="span7">
            <ul role="radiogroup">
                <li class="customComponent" role="radio" aria-checked="true">
                    <input type="radio" name="tripType" value="roundTrip" id="flightSearchForm.tripType.roundTrip" checked />
                    <label for="flightSearchForm.tripType.roundTrip" class="pillbox">
                        <span class="hidden-accessible">Search flights round trip</span>
                    </label>
                </li>
                <li class="customComponent" role="radio" aria-checked="false">
                    <input type="radio" name="tripType" value="oneWay" id="flightSearchForm.tripType.oneWay"  >
                    <label for="flightSearchForm.tripType.oneWay" class="pillbox">
                        <span class="hidden-accessible">Search flights one way</span>
                    </label>
                </li>
                
            </ul>
        </div>--%>
		</div>
		<div class="row-form">
			<div class="span12">
				<small>( <span class="icon-required" aria-hidden="true"></span>
					Required <span class="hidden-accessible">dot indicates
						required</span>)
				</small>
			</div>
		</div>
		<div class="row-form">
			<div class="span4">
				<div class="position-relative margin-bottom">
					<label for="origin"> From <span class="icon-required"
						aria-hidden="true"></span><span class="hidden-accessible">
							(required)</span> <select name="origin" id="origin"
						onchange="checkDest(this.value)">
							<option disabled selected>Depart From</option>
							<option value="CID">CID</option>
							<option value="ORD">ORD</option>
							<option value="ATL">ATL</option>
							<option value="SFO">SFO</option>
							<option value="LCY">LCY</option>
					</select>
					</label>
					<%--<a data-for="reservationFlightSearchForm.originAirport" class="widget aaAirportLookup" href="javascript:void(0);">
                    <span class="icon icon-search" aria-hidden="true"></span> <span class="hidden-accessible">From airport look up</span>
                </a>--%>
				</div>
			</div>
			<div class="span4">
				<div class="position-relative margin-bottom">
					<label for="destination"> To <span class="icon-required"
						aria-hidden="true"></span><span class="hidden-accessible">(required)</span>
						<select name="destination" id="destination">
							<option disabled selected>Destination</option>
					</select>
					</label>
					<%--<a data-for="reservationFlightSearchForm.destinationAirport" class="widget aaAirportLookup" href="javascript:void(0);">
                    <span class="icon icon-search" aria-hidden="true"></span> <span class="hidden-accessible">To airport look up</span>
                </a>--%>
				</div>
			</div>
			<div class="span4">
				<div class="margin-bottom">
					<label for="seats"> Number of passengers <select
						name="seats" id="seats">
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
					</select>
					</label>


				</div>
			</div>
		</div>
		<div class="row-form aa-flightSearchForm-datesRow">
			<div class="span4">
				<div>
					<label for="date"> Depart <span class="icon-required"
						aria-hidden="true"></span><span class="hidden-accessible">,
							required.</span> <span class="datePicker"></span> <input id="date"
						name="date" type="date" />
					</label>
				</div>
			</div>
			<div class="span4">
				<%--<div>
                <label for="aa-returningFrom" >
                    Return <span class="icon-required" aria-hidden="true"></span><span class="hidden-accessible">, required.</span>
                    <span class="hidden-accessible">(date format mm/dd/yyyy)</span>
                    <input class="aaDatePicker" id="aa-returningFrom" name="returnDate" type="text" value="" placeholder="mm/dd/yyyy" >
                </label>
            </div>--%>
			</div>
			<div class="span4">
				<label for="flightClass" class="aa-display-none"> Fare
					preference <select id="flightClass" name="flightClass">
						<option selected="selected" value="Economy">Economy</option>
						<option value="Business">Business</option>
						<option value="First">First</option>
				</select>
				</label>
			</div>
			<div id="flightSearchFormSubmitButton" class="span4 ">
				<div>
					<input type="submit" value="Search"
						id="flightSearchForm.button.reSubmit" class="btn btn-fullWidth">
					<%--<button id="flightSearchForm.button.vacationSubmit" type="button" class="btn btn-fullWidth is-hidden">
                    Search<span aria-hidden="true" class="icon-newpage"></span>
                    <span class="hidden-accessible">, Opens another site in a new window that may not meet accessibility guidelines.</span>
                </button>--%>
				</div>
			</div>
		</div>
	</form>
</div>
<!-- The end of what Peter added -->

</html>
