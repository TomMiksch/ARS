package edu.uiowa.ars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uiowa.ars.SystemSupport;
import edu.uiowa.ars.dao.EmployeeDao;
import edu.uiowa.ars.model.Employee;

@Service("employeeService")
@Transactional
public final class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao dao;

	public Employee findById(final int id) {
		return dao.findById(id);
	}

	public void saveEmployee(final Employee employee) {
		dao.saveEmployee(employee);

		// If we correctly entered the employee in the database, then send them
		// an email.
		SystemSupport.sendEmail(employee.getEmailAddress(), "Test Subject", "Test Content", null, null);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate
	 * update explicitly. Just fetch the entity from db and update it with
	 * proper values within transaction. It will be updated in db once
	 * transaction ends.
	 */
	public void updateEmployee(final Employee employee) {
		final Employee entity = dao.findById(employee.getId());
		if (entity != null) {
			entity.setFirstName(employee.getFirstName());
			entity.setLastName(employee.getLastName());
			entity.setEmailAddress(employee.getEmailAddress());
		}
	}

	public List<Employee> findAllEmployees() {
		return dao.findAllEmployees();
	}

	public void deleteEmployeeById(final String id) {
		dao.deleteEmployeeById(id);
	}
}
