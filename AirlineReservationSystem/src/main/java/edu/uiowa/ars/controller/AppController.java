package edu.uiowa.ars.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.uiowa.ars.model.Employee;
import edu.uiowa.ars.service.EmployeeService;

@Controller
@RequestMapping("/")
public final class AppController {

	@Autowired
	EmployeeService service;

	@Autowired
	MessageSource messageSource;

	/*
	 * This method will list all existing employees.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listEmployees(final ModelMap model) {

		final List<Employee> employees = service.findAllEmployees();
		model.addAttribute("employees", employees);
		return "allemployees";
	}

	/*
	 * This method will provide the medium to add a new employee.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newEmployee(final ModelMap model) {
		final Employee employee = new Employee();
		model.addAttribute("employee", employee);
		model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveEmployee(@Valid final Employee employee, final BindingResult result, final ModelMap model) {

		// TODO should also check here if entered email is valid.
		if (result.hasErrors()) {
			return "registration";
		}

		service.saveEmployee(employee);

		model.addAttribute("success", "Employee " + employee.getFullName() + " registered successfully!");
		return "success";
	}

	/*
	 * This method will delete an employee by it's unique ID value from the
	 * database.
	 */
	@RequestMapping(value = { "/delete-{id}-employee" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable final String id) {
		service.deleteEmployeeById(id);
		return "redirect:/list";
	}
}
