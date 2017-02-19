package edu.uiowa.ars.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import edu.uiowa.ars.model.Employee;

@Repository("employeeDao")
public final class EmployeeDaoImpl extends AbstractDao<Integer, Employee> implements EmployeeDao {

	public Employee findById(final int id) {
		return getByKey(id);
	}

	public void saveEmployee(final Employee employee) {
		persist(employee);
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findAllEmployees() {
		final Criteria criteria = createEntityCriteria();
		return (List<Employee>) criteria.list();
	}

	public void deleteEmployeeById(final String id) {
		getSession().createSQLQuery("delete from Employee where id = " + id).executeUpdate();
	}
}
