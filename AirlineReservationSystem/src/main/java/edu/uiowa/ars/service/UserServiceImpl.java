package edu.uiowa.ars.service;

import java.util.Base64;
import java.util.List;
import java.util.Random;

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
        @Override
	public void saveUser(final User user) {
		byte[] tempPass = new byte[16];
		final Random random = new Random();
		random.nextBytes(tempPass);
                String tempPassword = new String(Base64.getEncoder().encode(tempPass));
		// Encryption with md5 
		user.setPassword(SystemSupport.md5(tempPassword)); 
		dao.saveUser(user);

		// If we correctly entered the user in the database, then send them
		// an email.
		SystemSupport.sendEmail(user.getEmailAddress(), "Test Subject", "Hello " + user.getFirstName() + ",<br>"
				+ "Thank you for registering for an Iowa Air online account! We are pleased to have your business. "
				+ "Please login to your Iowa Air account using the credentials listed below. You may update your password after login.<br><br>"
				+ "Username: " + user.getEmailAddress() + "<br>"
				+ "Password: " + tempPassword + "<br><br>Sincerely,<br>Iowa Air", null, null);
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
			entity.setUserType(user.getUserType());
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmailAddress(user.getEmailAddress());
			entity.setPassword(user.getPassword());
		}
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public void deleteUserById(final String id) {
		dao.deleteUserById(id);
	}
}
