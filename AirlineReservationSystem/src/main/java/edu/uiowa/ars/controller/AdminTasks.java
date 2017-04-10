package edu.uiowa.ars.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.uiowa.ars.model.Aircraft;
import edu.uiowa.ars.model.Aircraft.AircraftTypes;
import edu.uiowa.ars.model.FlightRoute;
import edu.uiowa.ars.model.FlightRoute.Airports;
import edu.uiowa.ars.model.FlightRoute.Frequency;
import edu.uiowa.ars.model.User;
import edu.uiowa.ars.model.User.Genders;
import edu.uiowa.ars.model.User.UserTypes;
import edu.uiowa.ars.service.AircraftService;
import edu.uiowa.ars.service.FlightRouteService;
import edu.uiowa.ars.service.UserService;

@Controller
@RequestMapping("/admin")
public final class AdminTasks {

	@Autowired
	FlightRouteService flightRouteService;

	@Autowired
	AircraftService aircraftService;

	@Autowired
	UserService userService;

	private static final String DEFAULT_MESSAGE_CODE = "SOME_DEFAULT";

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homeGet(final ModelMap model) {
		return "admin/home";
	}

	@RequestMapping(value = { "/userList" }, method = RequestMethod.GET)
	public String userListGet(final ModelMap model) {
		final List<User> users = userService.findAllEntities();
		model.addAttribute("users", users);
		return "admin/userList";
	}

	@RequestMapping(value = { "/delete-{id}-user" }, method = RequestMethod.GET)
	public String deleteUserGet(@PathVariable final String id) {
		userService.deleteEntityById(id);
		return "redirect:/admin/userList";
	}

	@RequestMapping(value = { "/addUser" }, method = RequestMethod.GET)
	public String addUserGet(final ModelMap model) {
		final User user = new User();
		model.addAttribute("user", user);
		final List<String> userTypes = UserTypes.getAllIdentifiers();
		userTypes.remove(UserTypes.CUSOMTER.getIdentifier());
		model.addAttribute("userTypes", userTypes);
		model.addAttribute("genders", Genders.getAllIdentifiers());
		return "admin/addUser";
	}

	@RequestMapping(value = { "/addUser" }, method = RequestMethod.POST)
	public String addUserPost(@Valid final User user, final BindingResult result, final ModelMap model) {
		if (result.hasErrors()) {
			final List<String> userTypes = UserTypes.getAllIdentifiers();
			userTypes.remove(UserTypes.CUSOMTER.getIdentifier());
			model.addAttribute("userTypes", userTypes);
			model.addAttribute("genders", Genders.getAllIdentifiers());
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
			return "admin/addUser";
		}

		userService.saveEntity(user);
		model.addAttribute("firstName", user.getFirstName());
		return "redirect:/admin/userList";
	}

	@RequestMapping(value = { "/aircraftList" }, method = RequestMethod.GET)
	public String aircraftListGet(final ModelMap model) {
		final List<Aircraft> aircrafts = aircraftService.findAllEntities();
		model.addAttribute("aircrafts", aircrafts);
		return "admin/aircraftList";
	}

	@RequestMapping(value = { "/delete-{id}-aircraft" }, method = RequestMethod.GET)
	public String deleteAircraftGet(@PathVariable final String id) {
		aircraftService.deleteEntityById(id);
		return "redirect:/admin/aircraftList";
	}

	@RequestMapping(value = { "/addAircraft" }, method = RequestMethod.GET)
	public String addAircraftGet(final ModelMap model) {
		final Aircraft aircraft = new Aircraft();
		model.addAttribute("aircraft", aircraft);
		model.addAttribute("aircraftTypes", AircraftTypes.getAllIdentifiers());
		return "admin/addAircraft";
	}

	@RequestMapping(value = { "/addAircraft" }, method = RequestMethod.POST)
	public String addAircraftPost(@Valid final Aircraft aircraft, final BindingResult result, final ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("aircraftTypes", AircraftTypes.getAllIdentifiers());
			return "admin/addAircraft";
		}

		aircraftService.saveEntity(aircraft);
		return "redirect:/admin/aircraftList";
	}

	@RequestMapping(value = { "/flightRouteList" }, method = RequestMethod.GET)
	public String flightRouteListGet(final ModelMap model) {
		final List<FlightRoute> flightRoutes = flightRouteService.findAllEntities();
		model.addAttribute("flightRoutes", flightRoutes);
		return "admin/flightRouteList";
	}

	@RequestMapping(value = { "/delete-{id}-flightRoute" }, method = RequestMethod.GET)
	public String deleteFlightRouteGet(@PathVariable final String id) {
		flightRouteService.deleteEntityById(id);
		return "redirect:/admin/flightRouteList";
	}

	@RequestMapping(value = { "/addFlightRoute" }, method = RequestMethod.GET)
	public String addFlightRouteGet(final ModelMap model) {
		final FlightRoute flightRoute = new FlightRoute();
		// Set daily to be the default frequency.
		flightRoute.setFrequency(Frequency.DAILY.getIdentifier());
		model.addAttribute("flightRoute", flightRoute);
		model.addAttribute("aircrafts", aircraftService.getAllSymbols());
		model.addAttribute("airports", Airports.getAllIdentifiers());
		model.addAttribute("frequencies", Frequency.getAllIdentifiers());
		return "admin/addFlightRoute";
	}

	@RequestMapping(value = { "/addFlightRoute" }, method = RequestMethod.POST)
	public String addFlightRoutePost(@Valid final FlightRoute flightRoute, final BindingResult result,
			final ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("aircrafts", aircraftService.getAllSymbols());
			model.addAttribute("airports", Airports.getAllIdentifiers());
			model.addAttribute("frequencies", Frequency.getAllIdentifiers());
			return "admin/addFlightRoute";
		}

		// Make sure a destination was entered.
		final String destination = flightRoute.getDestination();
		if (destination == null || destination.isEmpty()) {
			result.rejectValue("destination", DEFAULT_MESSAGE_CODE, "Destination is required.");
			model.addAttribute("aircrafts", aircraftService.getAllSymbols());
			model.addAttribute("airports", Airports.getAllIdentifiers());
			model.addAttribute("frequencies", Frequency.getAllIdentifiers());
			return "admin/addFlightRoute";
		}

		// Make sure an end time was entered.
		final String endTime = flightRoute.getEndTime();
		if (endTime == null || endTime.isEmpty()) {
			result.rejectValue("endTime", DEFAULT_MESSAGE_CODE, "End time is required.");
			model.addAttribute("aircrafts", aircraftService.getAllSymbols());
			model.addAttribute("airports", Airports.getAllIdentifiers());
			model.addAttribute("frequencies", Frequency.getAllIdentifiers());
			return "admin/addFlightRoute";
		}

		// TODO how should duplicates be checked for?
		flightRouteService.saveEntity(flightRoute);
		return "redirect:/admin/flightRouteList";
	}
}
