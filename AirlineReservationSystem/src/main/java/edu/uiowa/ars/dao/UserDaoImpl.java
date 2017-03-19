package edu.uiowa.ars.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import edu.uiowa.ars.model.User;

@Repository("userDao")
public final class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public User findById(final int id) {
		return getByKey(id);
	}

	public void saveUser(final User user) {
		persist(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		final Criteria criteria = createEntityCriteria();
		return (List<User>) criteria.list();
	}

	public void deleteUserById(final String id) {
		getSession().createSQLQuery("delete from User where id = " + id).executeUpdate();
	}
        
        
}
