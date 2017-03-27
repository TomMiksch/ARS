package edu.uiowa.ars.controller;

import edu.uiowa.ars.SystemSupport;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.sql.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.uiowa.ars.model.User;
import edu.uiowa.ars.service.UserService;

@Controller
@RequestMapping("/")
public final class AppController {

	@Autowired
	UserService service;

	@Autowired
	MessageSource messageSource;

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
		final boolean duplicateEmail = service.findAllUsers().stream()
				.anyMatch(currentUser -> currentUser.getEmailAddress().equalsIgnoreCase(user.getEmailAddress()));
		if (duplicateEmail) {
			result.rejectValue("emailAddress", DEFAULT_MESSAGE_CODE, "Email address already in use.");
			return "register";
		}
		// This user is a customer.
		user.setUserType("Customer");
		service.saveUser(user);
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
	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String loginGet(final ModelMap model) {
		return "login";
	}

	/*
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String listUsers(final ModelMap model) {

		final List<User> users = service.findAllUsers();
		model.addAttribute("users", users);
		return "allusers";
	}

	/*
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newUser(final ModelMap model) {
		final User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		final Map<String, String> userTypes = new LinkedHashMap<>();
		userTypes.put("Admin", "Admin");
		userTypes.put("Employee", "Employee");
		model.addAttribute("userTypes", userTypes);
		return "new";
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
		final boolean duplicateEmail = service.findAllUsers().stream()
				.anyMatch(currentUser -> currentUser.getEmailAddress().equalsIgnoreCase(user.getEmailAddress()));
		if (duplicateEmail) {
			result.rejectValue("emailAddress", DEFAULT_MESSAGE_CODE, "Email address already in use.");
			return "new";
		}

		service.saveUser(user);
		model.addAttribute("firstName", user.getFirstName());
		return "success";
	}

	/*
	 * This method will delete an user by it's unique ID value from the
	 * database.
	 */
	@RequestMapping(value = { "/delete-{id}-user" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable final String id) {
		service.deleteUserById(id);
		return "redirect:/list";
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
		final User storedUser = service.getStoredUser(user);
		if (storedUser != null) {
			final String userType = storedUser.getUserType();
			if ("Admin".equals(userType)) {
				return "new";
			} else if ("Customer".equals(userType)) {
				return "hellouser";
			}
		} else {
			System.err.println("Invalid login with username/password combination: \"" + user.getEmailAddress()
					+ "\" and \"" + user.getPassword() + "\"");
		}
		// Indicate to the user that they have an invalid username/password
		// combination.
		result.reject("loginPageForm", "Invalid Username and/or Password.");
		return "loginpage";
	}
}
