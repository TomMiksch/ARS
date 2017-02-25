package edu.uiowa.ars.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

	/*
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
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
		final Map<String,String> userTypes = new LinkedHashMap<>();
		userTypes.put("Admin", "Admin");
		userTypes.put("Employee", "Employee");
		userTypes.put("Customer", "Customer");
		model.addAttribute("userTypes", userTypes);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveUser(@Valid final User user, final BindingResult result, final ModelMap model) {

		// TODO should also check here if entered email is valid.
		if (result.hasErrors()) {
			return "registration";
		}
		
		service.saveUser(user);

		model.addAttribute("success", "User " + user.getFullName() + " registered successfully!");
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
}
