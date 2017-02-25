package edu.uiowa.ars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uiowa.ars.SystemSupport;
import edu.uiowa.ars.dao.UserDao;
import edu.uiowa.ars.model.User;

@Service("userService")
@Transactional
public final class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	public User findById(final int id) {
		return dao.findById(id);
	}

	public void saveUser(final User user) {
		dao.saveUser(user);

		// If we correctly entered the user in the database, then send them
		// an email.
		SystemSupport.sendEmail(user.getEmailAddress(), "Test Subject", "Test Content", null, null);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate
	 * update explicitly. Just fetch the entity from db and update it with
	 * proper values within transaction. It will be updated in db once
	 * transaction ends.
	 */
	public void updateUser(final User user) {
		final User entity = dao.findById(user.getId());
		if (entity != null) {
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmailAddress(user.getEmailAddress());
		}
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public void deleteUserById(final String id) {
		dao.deleteUserById(id);
	}
}
