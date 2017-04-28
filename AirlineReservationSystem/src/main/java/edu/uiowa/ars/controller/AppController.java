package edu.uiowa.ars.controller;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.uiowa.ars.SystemSupport;
import edu.uiowa.ars.model.FlightRoute;
import edu.uiowa.ars.model.User;
import edu.uiowa.ars.model.Booking;
import edu.uiowa.ars.model.Flight;
import edu.uiowa.ars.service.FlightRouteService;
import edu.uiowa.ars.service.UserService;
import edu.uiowa.ars.service.BookingService;
import edu.uiowa.ars.service.FlightService;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/")
public final class AppController {

	@Autowired
	UserService service;

	@Autowired
	MessageSource messageSource;

	@Autowired
	FlightRouteService flightRouteService;

        @Autowired
        BookingService bookingService;
        
        @Autowired
        FlightService flightService;
        
	private static final String DEFAULT_MESSAGE_CODE = "SOME_DEFAULT";

	/**
	 * This method links the register.jsp file to the /register URL. This page
	 * is for customers to register for an account.
	 * 
	 * @param model
	 *            The map of data to be passed into the register.jsp.
	 * @return register.jsp
	 */
	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public String registerGet(final ModelMap model) {
		final User user = new User();
		model.addAttribute("user", user);
		return "register";
	}

	/**
	 * This method links the form submission on the /register page to further
	 * action that should be taken with the user entered data.
	 * 
	 * @param user
	 *            The object containing the resulting data from the form
	 *            submission.
	 * @param result
	 *            The result of the POST data request.
	 * @param model
	 *            The map of data to be passed into resulting page.
	 * @return success.jsp if successful. register.jsp otherwise.
	 */
	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String registerPost(@Valid final User user, final BindingResult result, final ModelMap model) {
		if (result.hasErrors()) {
			return "register";
		}

		// Check to see if any other users have the same email address.
		final boolean duplicateEmail = service.findAllEntities().stream()
				.anyMatch(currentUser -> currentUser.getEmailAddress().equalsIgnoreCase(user.getEmailAddress()));
		if (duplicateEmail) {
			result.rejectValue("emailAddress", DEFAULT_MESSAGE_CODE, "Email address already in use.");
			return "register";
		}
		// This user is a customer.
		user.setUserType("Customer");
		service.saveEntity(user);
		model.addAttribute("firstName", user.getFirstName());
		return "success";
	}

	/**
	 * This method links the register.jsp file to the /login URL. This page is
	 * for customers to register for an account.
	 * 
	 * @param model
	 *            The map of data to be passed into the login.jsp.
	 * @return login.jsp
	 */
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homeGet(final ModelMap model) {
		final FlightRoute flightRoute = new FlightRoute();
		model.addAttribute("flightRoute", flightRoute);
		return "home";
	}

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.POST)
	public String searchedFlights(@Valid final FlightRoute flightRoute, final BindingResult result,
			final ModelMap model) {
		if (result.hasErrors()) {
			return "/";
		}
		flightRouteService.saveEntity(flightRoute);
		return "flightSearchResult";
	}

	@RequestMapping(value = { "/hellouser" }, method = RequestMethod.GET)
	public String userHomeGet(final ModelMap model) {
            final Flight flight = new Flight();
            model.addAttribute("flight", flight);
            final User user = new User();
            model.addAttribute("user", user);
            return "hellouser";
	}
        
        @RequestMapping(value = { "/hellouser" }, method = RequestMethod.POST)
	public String userSearchedFlights(@Valid final FlightRoute flightRoute, final BindingResult result, final ModelMap model) {
            if (result.hasErrors()) {
		return "/hellouser";
	    }
            flightRouteService.saveEntity(flightRoute);
            return "userFlightSearch";
        }

	/*
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newUser(final ModelMap model) {
		final User user = new User();
		model.addAttribute("user", user);
		return "new";
	}

	@RequestMapping(value = { "/success" }, method = RequestMethod.GET)
	public String success(final ModelMap model) {
		final User user = new User();
		model.addAttribute("user", user);
		return "success";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveUser(@Valid final User user, final BindingResult result, final ModelMap model) {
		if (result.hasErrors()) {
			return "new";
		}

		// Check to see if any other users have the same email address.
		final boolean duplicateEmail = service.findAllEntities().stream()
				.anyMatch(currentUser -> currentUser.getEmailAddress().equalsIgnoreCase(user.getEmailAddress()));
		if (duplicateEmail) {
			result.rejectValue("emailAddress", DEFAULT_MESSAGE_CODE, "Email address already in use.");
			return "new";
		}

		service.saveEntity(user);
		model.addAttribute("firstName", user.getFirstName());
		return "success";
	}

	/*
	 * Method that displays the login page
	 */
	@RequestMapping(value = { "/loginpage" }, method = RequestMethod.GET)
	public String loginPage(final ModelMap model) {
		final User user = new User();
		model.addAttribute("user", user);
		return "loginpage";
	}

	@RequestMapping(value = { "/loginpage" }, method = RequestMethod.POST)
	public String checkUser(@Valid final User user, final BindingResult result, final ModelMap model) {

		if (result.hasErrors()) {
			return "loginpage";
		}

		// Determine if this is a valid user login or not.
		final User storedUser = service.getStoredEntity(user);
		if (storedUser != null) {
			final String userType = storedUser.getUserType();
			if ("Admin".equals(userType)) {
				return "redirect:/admin/home";
			} else if ("Customer".equals(userType)) {
                                model.addAttribute("firstName", storedUser.getFirstName());
				return "hellouser";
			}

			// Changed user.getPassword() to something else since
			// user.GetPassword will return a hashed password
		} else {
			System.err.println("Invalid login with username/password combination: \"" + user.getEmailAddress() + "\"");
		}
		// Indicate to the user that they have an invalid username/password
		// combination.
		result.reject("loginPageForm", "Invalid Username and/or Password.");
		return "loginpage";
	}

	/*
	 * Reset password User user.phoneNumber as temporary password holder as can
	 * be seen from reset.jsp file
	 */

	@RequestMapping(value = { "/reset" }, method = RequestMethod.GET)
	public String resetPasswd(final ModelMap model) {
		final User user = new User();
		model.addAttribute("user", user);
		return "reset";
	}

	@RequestMapping(value = { "/reset" }, method = RequestMethod.POST)
	public String resetPw(@Valid final User user, final BindingResult result, final ModelMap model) {

		if (result.hasErrors()) {
			return "home";
		}

		// Determine if this is a valid user login or not.
		User storedUser = service.getStoredEntity(user);
		if (storedUser != null) {

			// If update password successfully, then redirect to loginpage
			storedUser.setPassword(SystemSupport.md5(user.getPasswordHolder()));
			service.updateEntity(storedUser);
			return "loginpage";

			// Changed user.getPassword() to something else since
			// user.GetPassword will return a hashed password
		} else {
			System.err.println("Invalid login with username/password combination: \"" + user.getEmailAddress() + "\"");
		}
		// Indicate to the user that they have an invalid username/password
		// combination.
		result.reject("loginPageForm", "Invalid Username and/or Password.");
		return "home";
	}
        
        @RequestMapping(value = { "/flightSearchResult" }, method = RequestMethod.POST)
	public String searchFlights(@Valid final Flight flight, final BindingResult result, final ModelMap model) {
            if (flight.getDestination() == null){
                return "home";
            }
                final List<Flight> flights = flightService.findSelectedEntities(flight);
		model.addAttribute("flightRoutes", flights);
            return "flightSearchResult";
	}
        
        @RequestMapping(value = { "/userFlightSearch" }, method = RequestMethod.POST)
	public String userSearchFlights(@Valid final Booking booking, @Valid final Flight flight, final BindingResult result, final ModelMap model) {
            if (flight.getDestination() == null){
                return "hellouser";
            }
            final List<Flight> flights = flightService.findSelectedEntities(flight);
		model.addAttribute("flightRoutes", flights);
            return "userFlightSearch";
	}
        
        @RequestMapping(value = { "/book-{id}-booking" }, method = RequestMethod.GET)
	public String deleteBookingGet(@PathVariable final String id) {
                /*int i = 0;
                String[] things = {"No"};
                HttpServletRequest request = null;
                Cookie[] cookies = request.getCookies();
                System.out.println("FUCK " + cookies[1]);*/
                final Booking booking = new Booking();
                booking.setUserEmail("thomas-miksch@uiowa.edu");
                booking.setFlightNumber(Integer.parseInt(id));
                booking.setSeatClass("First");
                booking.setSeats(6);
		bookingService.saveEntity(booking);
		return "redirect:/hellouser";
	}
        
        public final class FlightDataHolder {

                private int id;
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
                        this.id = flight.getId();
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
                
                public int getId(){
                    return id;
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
                
                public String getSymbol(){
                        return symbol;
                }


	}

	/*
	 * @InitBinder public void initBinder(WebDataBinder binder) {
	 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 * sdf.setLenient(true); binder.registerCustomEditor(Date.class, new
	 * CustomDateEditor(sdf, true)); }
	 */
}
