package edu.uiowa.ars.service;

import java.util.List;

import edu.uiowa.ars.model.Employee;

public interface EmployeeService {

	Employee findById(int id);

	void saveEmployee(Employee employee);

	void updateEmployee(Employee employee);

	List<Employee> findAllEmployees();

	void deleteEmployeeById(String id);
}
