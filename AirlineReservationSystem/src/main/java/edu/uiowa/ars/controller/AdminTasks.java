package edu.uiowa.ars.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.uiowa.ars.model.Aircraft;
import edu.uiowa.ars.model.Aircraft.AircraftTypes;
import edu.uiowa.ars.model.Booking;
import edu.uiowa.ars.model.Flight;
import edu.uiowa.ars.model.FlightRoute;
import edu.uiowa.ars.model.FlightRoute.Airports;
import edu.uiowa.ars.model.FlightRoute.Frequency;
import edu.uiowa.ars.model.User;
import edu.uiowa.ars.model.User.Genders;
import edu.uiowa.ars.model.User.UserTypes;
import edu.uiowa.ars.service.AircraftService;
import edu.uiowa.ars.service.BookingService;
import edu.uiowa.ars.service.FlightRouteService;
import edu.uiowa.ars.service.FlightService;
import edu.uiowa.ars.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public final class AdminTasks {

	@Autowired
	FlightRouteService flightRouteService;

	@Autowired
	AircraftService aircraftService;

	@Autowired
	UserService userService;

	@Autowired
	BookingService bookingService;

	@Autowired
	FlightService flightService;

	private static final String DEFAULT_MESSAGE_CODE = "SOME_DEFAULT";

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homeGet(final ModelMap model, @RequestParam(value = "userId", required = false) final String userId) {
		if (!userService.isValidAdmin(userId)) {
			return "redirect:/loginpage";
		}
		model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
		return "admin/home";
	}

	@RequestMapping(value = { "/userList" }, method = RequestMethod.GET)
	public String userListGet(final ModelMap model,
			@RequestParam(value = "userId", required = false) final String userId) {
		if (!userService.isValidAdmin(userId)) {
			return "redirect:/loginpage";
		}
		final List<User> users = userService.findAllEntities();
		model.addAttribute("users", users);
		model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
		return "admin/userList";
	}

	@RequestMapping(value = { "/delete-{id}-user" }, method = RequestMethod.GET)
	public String deleteUserGet(final ModelMap model, @PathVariable final String id,
			@RequestParam(value = "userId", required = false) final String userId) {
		if (!userService.isValidAdmin(userId)) {
			return "redirect:/loginpage";
		} else if (userId.equals(id)) {
			System.err.println("Cannot delete your own account this way!");
			model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
			return "redirect:/admin/home?userId=" + userId;
		}
		userService.deleteEntityById(id);
		model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
		return "redirect:/admin/userList?userId=" + userId;
	}

	@RequestMapping(value = { "/addUser" }, method = RequestMethod.GET)
	public String addUserGet(final ModelMap model,
			@RequestParam(value = "userId", required = false) final String userId) {
		if (!userService.isValidAdmin(userId)) {
			return "redirect:/loginpage";
		}
		final User user = new User();
		model.addAttribute("user", user);
		final List<String> userTypes = UserTypes.getAllIdentifiers();
		userTypes.remove(UserTypes.CUSOMTER.getIdentifier());
		model.addAttribute("userTypes", userTypes);
		model.addAttribute("genders", Genders.getAllIdentifiers());
		model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
		return "admin/addUser";
	}

	@RequestMapping(value = { "/addUser" }, method = RequestMethod.POST)
	public String addUserPost(@Valid final User user, final BindingResult result, final ModelMap model,
			@RequestParam(value = "userId", required = false) final String userId) {
		if (!userService.isValidAdmin(userId)) {
			return "redirect:/loginpage";
		} else if (result.hasErrors()) {
			final List<String> userTypes = UserTypes.getAllIdentifiers();
			userTypes.remove(UserTypes.CUSOMTER.getIdentifier());
			model.addAttribute("userTypes", userTypes);
			model.addAttribute("genders", Genders.getAllIdentifiers());
			model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
			return "admin/addUser";
		}

		// Check to see if any other users have the same email address.
		final boolean duplicateEmail = userService.findAllEntities().stream()
				.anyMatch(currentUser -> userService.isEquivalentInDb(user, currentUser));
		if (duplicateEmail) {
			result.rejectValue("emailAddress", DEFAULT_MESSAGE_CODE, "Email address already in use.");
			final List<String> userTypes = UserTypes.getAllIdentifiers();
			userTypes.remove(UserTypes.CUSOMTER.getIdentifier());
			model.addAttribute("userTypes", userTypes);
			model.addAttribute("genders", Genders.getAllIdentifiers());
			model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
			return "admin/addUser";
		}

		userService.saveEntity(user);
		model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
		return "redirect:/admin/userList?userId=" + userId;
	}

	@RequestMapping(value = { "/aircraftList" }, method = RequestMethod.GET)
	public String aircraftListGet(final ModelMap model,
			@RequestParam(value = "userId", required = false) final String userId) {
		if (!userService.isValidAdmin(userId)) {
			return "redirect:/loginpage";
		}
		final List<Aircraft> aircrafts = aircraftService.findAllEntities();
		model.addAttribute("aircrafts", aircrafts);
		model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
		return "admin/aircraftList";
	}

	@RequestMapping(value = { "/addAircraft" }, method = RequestMethod.GET)
	public String addAircraftGet(final ModelMap model,
			@RequestParam(value = "userId", required = false) final String userId) {
		if (!userService.isValidAdmin(userId)) {
			return "redirect:/loginpage";
		}
		final Aircraft aircraft = new Aircraft();
		model.addAttribute("aircraft", aircraft);
		model.addAttribute("aircraftTypes", AircraftTypes.getAllIdentifiers());
		model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
		return "admin/addAircraft";
	}

	@RequestMapping(value = { "/addAircraft" }, method = RequestMethod.POST)
	public String addAircraftPost(@Valid final Aircraft aircraft, final BindingResult result, final ModelMap model,
			@RequestParam(value = "userId", required = false) final String userId) {
		if (!userService.isValidAdmin(userId)) {
			return "redirect:/loginpage";
		} else if (result.hasErrors()) {
			model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
			model.addAttribute("aircraftTypes", AircraftTypes.getAllIdentifiers());
			return "admin/addAircraft";
		}

		aircraftService.saveEntity(aircraft);
		model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
		return "redirect:/admin/aircraftList?userId=" + userId;
	}

	@RequestMapping(value = { "/flightList" }, method = RequestMethod.GET)
	public String flightListGet(final ModelMap model,
			@RequestParam(value = "userId", required = false) final String userId) {
		if (!userService.isValidAdmin(userId)) {
			return "redirect:/loginpage";
		}
		final List<FlightDataHolder> flights = flightService.findAllEntities().stream()
				.map(flight -> new FlightDataHolder(flight)).collect(Collectors.toList());
		model.addAttribute("flights", flights);
		model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
		return "admin/flightList";
	}

	@RequestMapping(value = { "/flightRouteList" }, method = RequestMethod.GET)
	public String flightRouteListGet(final ModelMap model,
			@RequestParam(value = "userId", required = false) final String userId) {
		if (!userService.isValidAdmin(userId)) {
			return "redirect:/loginpage";
		}
		final List<FlightRoute> flightRoutes = flightRouteService.findAllEntities();
		model.addAttribute("flightRoutes", flightRoutes);
		model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
		return "admin/flightRouteList";
	}

	@RequestMapping(value = { "/addFlightRoute" }, method = RequestMethod.GET)
	public String addFlightRouteGet(final ModelMap model,
			@RequestParam(value = "userId", required = false) final String userId) {
		if (!userService.isValidAdmin(userId)) {
			return "redirect:/loginpage";
		}
		final FlightRoute flightRoute = new FlightRoute();
		// Set daily to be the default frequency.
		flightRoute.setFrequency(Frequency.DAILY.getIdentifier());
		model.addAttribute("flightRoute", flightRoute);
		model.addAttribute("aircrafts", aircraftService.getAllSymbols());
		model.addAttribute("airports", Airports.getAllIdentifiers());
		model.addAttribute("frequencies", Frequency.getAllIdentifiers());
		model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
		return "admin/addFlightRoute";
	}

	@RequestMapping(value = { "/addFlightRoute" }, method = RequestMethod.POST)
	public String addFlightRoutePost(@Valid final FlightRoute flightRoute, final BindingResult result,
			final ModelMap model, @RequestParam(value = "userId", required = false) final String userId) {
		if (!userService.isValidAdmin(userId)) {
			return "redirect:/loginpage";
		} else if (result.hasErrors()) {
			model.addAttribute("aircrafts", aircraftService.getAllSymbols());
			model.addAttribute("airports", Airports.getAllIdentifiers());
			model.addAttribute("frequencies", Frequency.getAllIdentifiers());
			model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
			return "admin/addFlightRoute";
		}

		// Make sure a destination was entered.
		final String destination = flightRoute.getDestination();
		if (destination == null || destination.isEmpty()) {
			result.rejectValue("destination", DEFAULT_MESSAGE_CODE, "Destination is required.");
			model.addAttribute("aircrafts", aircraftService.getAllSymbols());
			model.addAttribute("airports", Airports.getAllIdentifiers());
			model.addAttribute("frequencies", Frequency.getAllIdentifiers());
			model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
			return "admin/addFlightRoute";
		}

		// Make sure an end time was entered.
		final String endTime = flightRoute.getEndTime();
		if (endTime == null || endTime.isEmpty()) {
			result.rejectValue("endTime", DEFAULT_MESSAGE_CODE, "End time is required.");
			model.addAttribute("aircrafts", aircraftService.getAllSymbols());
			model.addAttribute("airports", Airports.getAllIdentifiers());
			model.addAttribute("frequencies", Frequency.getAllIdentifiers());
			model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
			return "admin/addFlightRoute";
		}

		// Make sure an beginning date was entered.
		final String beginDate = flightRoute.getBeginDate();
		if (beginDate == null || beginDate.isEmpty()) {
			result.rejectValue("beginDate", DEFAULT_MESSAGE_CODE, "Begin date is required.");
			model.addAttribute("aircrafts", aircraftService.getAllSymbols());
			model.addAttribute("airports", Airports.getAllIdentifiers());
			model.addAttribute("frequencies", Frequency.getAllIdentifiers());
			model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
			return "admin/addFlightRoute";
		}

		// Make sure an ending time was entered.
		final String endDate = flightRoute.getEndDate();
		if (endDate == null || endDate.isEmpty()) {
			result.rejectValue("endDate", DEFAULT_MESSAGE_CODE, "End date is required.");
			model.addAttribute("aircrafts", aircraftService.getAllSymbols());
			model.addAttribute("airports", Airports.getAllIdentifiers());
			model.addAttribute("frequencies", Frequency.getAllIdentifiers());
			model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
			return "admin/addFlightRoute";
		}

		// Save the flight route.
		flightRouteService.saveEntity(flightRoute);

		// Create a set of flights based off of this route.
		LocalDate beginLocalDate = new LocalDate(beginDate);
		final LocalDate endLocalDate = new LocalDate(endDate);

		final Frequency freq = Frequency.valueOf(flightRoute.getFrequency().toUpperCase());
		while (!beginLocalDate.isAfter(endLocalDate)) {
			// Save the current date as a new flight.
			final Flight flight = new Flight();
			flight.setFlightRouteId(flightRoute.getId());
			flight.setDate(beginLocalDate.toString("yyyy-MM-dd"));
			// String storedAircraft = flightRoute.getAircraft();
			// final Aircraft aircraft =
			// aircraftService.getStoredEntity(storedAircraft);
			flight.setOrigin(flightRoute.getOrigin());
			flight.setDestination(flightRoute.getDestination());
			flight.setFirstClassPrice(flightRoute.getFirstClassPrice());
			flight.setBusinessClassPrice(flightRoute.getBusinessClassPrice());
			flight.setEconomyClassPrice(flightRoute.getEconomyClassPrice());
			flight.setStartTime(flightRoute.getStartTime());
			flight.setEndTime(flightRoute.getEndTime());
			flight.setSeats(0);
			flight.setFlightClass("blank");
			flightService.saveEntity(flight);
			beginLocalDate = freq.apply(beginLocalDate);
		}

		model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
		return "redirect:/admin/flightRouteList?userId=" + userId;
	}

	@RequestMapping(value = { "/bookingList" }, method = RequestMethod.GET)
	public String bookingListGet(final ModelMap model,
			@RequestParam(value = "userId", required = false) final String userId) {
		if (!userService.isValidAdmin(userId)) {
			return "redirect:/loginpage";
		}
		final List<Booking> booking = bookingService.findAllEntities();
		model.addAttribute("booking", booking);
		model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
		return "admin/bookingList";
	}

	@RequestMapping(value = { "/delete-{id}-booking" }, method = RequestMethod.GET)
	public String deleteBookingGet(final ModelMap model, @PathVariable final String id,
			@RequestParam(value = "userId", required = false) final String userId) {
		if (!userService.isValidAdmin(userId)) {
			return "redirect:/loginpage";
		}
		bookingService.deleteEntityById(id);
		model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
		return "redirect:/admin/bookingList?userId=" + userId;
	}
        
        @RequestMapping(value = { "/delete-{id}-flightRoute" }, method = RequestMethod.GET)
	public String deleteFlightGet(final ModelMap model, @PathVariable final String id,
			@RequestParam(value = "userId", required = false) final String userId) {
		if (!userService.isValidAdmin(userId)) {
			return "redirect:/loginpage";
		}
		flightRouteService.deleteEntityById(id);
		model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
		return "redirect:/admin/flightRouteList?userId=" + userId;
	}
        
        @RequestMapping(value = { "/delete-{id}-aircraft" }, method = RequestMethod.GET)
	public String deleteAircraftGet(final ModelMap model, @PathVariable final String id,
			@RequestParam(value = "userId", required = false) final String userId) {
		if (!userService.isValidAdmin(userId)) {
			return "redirect:/loginpage";
		}
		aircraftService.deleteEntityById(id);
		model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
		return "redirect:/admin/aircraftList?userId=" + userId;
	}

	@RequestMapping(value = "/confirm-{user_email:.+}/{id}", method = RequestMethod.GET)
	public String confirmBookingGet(final ModelMap model, @PathVariable("user_email") final String email,
			@PathVariable("id") final String id,
			@RequestParam(value = "userId", required = false) final String userId) {
		if (!userService.isValidAdmin(userId)) {
			return "redirect:/loginpage";
		}
		bookingService.confirmEntityByEmail(email);
		bookingService.deleteEntityById(id);
		model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
		return "redirect:/admin/bookingList?userId=" + userId;
	}

	@RequestMapping(value = { "/editFlights" }, method = RequestMethod.GET)
	public String editFlights(final ModelMap model,
			@RequestParam(value = "userId", required = false) final String userId) {
		if (!userService.isValidAdmin(userId)) {
			return "redirect:/loginpage";
		}
		final FlightRoute flightRoute = new FlightRoute();
		// Set daily to be the default frequency.
		model.addAttribute("flightRoute", flightRoute);
		model.addAttribute("flightID", flightRouteService.getAllSymbols());
		model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
		return "admin/editFlights";
	}

	@RequestMapping(value = { "/editFlights" }, method = RequestMethod.POST)
	public String editFlightsPost(@Valid final FlightRoute flightRoute, final BindingResult result,
			final ModelMap model, @RequestParam(value = "userId", required = false) final String userId) {
		if (!userService.isValidAdmin(userId)) {
			return "redirect:/loginpage";
		} else if (result.hasErrors()) {
			model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
			return "editFlights";
		}

		final FlightRoute storedFlight = flightRouteService.getStoredEntity(flightRoute);
		if (storedFlight != null) {
			storedFlight.setFirstClassPrice(flightRoute.getFirstClassPrice());
			storedFlight.setBusinessClassPrice(flightRoute.getBusinessClassPrice());
			storedFlight.setEconomyClassPrice(flightRoute.getEconomyClassPrice());
		} else {
			System.err.println("Route Not Found");
		}

		flightRouteService.updateEntity(storedFlight);
		model.addAttribute("firstName", userService.getUserById(userId).getFirstName());
		return "redirect:/admin/flightRouteList?userId=" + userId;
	}

	public final class FlightDataHolder {

		private String aircraft;
		private String symbol;
		private String date;
		private int firstClassPrice;
		private int businessClassPrice;
		private int economyClassPrice;
		private String origin;
		private String destination;
		private String startTime;
		private String endTime;

		public FlightDataHolder(final Flight flight) {
			this.date = flight.getDate();
			final FlightRoute route = flightRouteService.findAllEntities().stream()
					.filter(flightRoute -> flightRoute.getId() == flight.getFlightRouteId()).findAny().get();
			this.aircraft = route.getAircraft();
			this.symbol = route.getSymbol();
			this.firstClassPrice = route.getFirstClassPrice();
			this.businessClassPrice = route.getBusinessClassPrice();
			this.economyClassPrice = route.getEconomyClassPrice();
			this.origin = route.getOrigin();
			this.destination = route.getDestination();
			this.startTime = route.getStartTime();
			this.endTime = route.getEndTime();
		}

		public int getFirstClassPrice() {
			return firstClassPrice;
		}

		public int getBusinessClassPrice() {
			return businessClassPrice;
		}

		public int getEconomyClassPrice() {
			return economyClassPrice;
		}

		public String getOrigin() {
			return origin;
		}

		public String getDestination() {
			return destination;
		}

		public String getStartTime() {
			return startTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public String getAircraft() {
			return aircraft;
		}

		public String getDate() {
			return date;
		}

		public String getSymbol() {
			return symbol;
		}

	}
}
