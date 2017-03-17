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

		// This user is a customer.
		user.setUserType("Customer");
		service.saveUser(user);

		model.addAttribute("success", "User " + user.getFullName() + " registered successfully!");
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
		final User user = new User();
		model.addAttribute("user", user);
		return "login";
	}

	/**
	 * This method links the login home page which has many place holders so
	 * far. The template is AA.com This page pass the data to the data base and
	 * checks if there is a match
	 * 
	 * @param user
	 * @param result
	 * @param model
	 * @return success.jsp if successful. register.jsp otherwise.
	 */

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.POST)
	public String loginPost(@Valid final User user, final BindingResult result, final ModelMap model) {

		if (result.hasErrors()) {
			return "register";
		}

		// This user is a customer.
		user.setUserType("Customer");
		service.saveUser(user);

		model.addAttribute("success", "User " + user.getFullName() + " registered successfully!");
		return "success";
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
        /*
            Method that displays the login page
        */
        @RequestMapping(value = { "/loginpage" }, method = RequestMethod.GET)
        public String loginPage(final ModelMap model){
            
            return "loginpage";
        }
        
        @RequestMapping(value = { "/loginpage" }, method = RequestMethod.POST)
	public String checkUser(@Valid final User user, final BindingResult result, final ModelMap model) {

		if (result.hasErrors()) {
			return "loginpage";
		}
                
                service.checkUser(user);
                
                if ("Admin".equals(user.getUserType())){
                    return "registration";
                }
                else if("Customer".equals(user.getUserType())){
                    return "hellouser";
                }
                
                return "loginpage";
	}
}
