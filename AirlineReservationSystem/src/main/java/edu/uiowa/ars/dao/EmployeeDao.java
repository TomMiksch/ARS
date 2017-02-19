package edu.uiowa.ars.dao;

import java.util.List;

import edu.uiowa.ars.model.Employee;

public interface EmployeeDao {

	Employee findById(int id);

	void saveEmployee(Employee employee);

	List<Employee> findAllEmployees();

	void deleteEmployeeById(String id);
}
