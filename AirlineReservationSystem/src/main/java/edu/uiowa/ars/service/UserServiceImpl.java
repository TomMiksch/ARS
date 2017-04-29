package edu.uiowa.ars.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
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
	public void saveEntity(final User user) {
		byte[] tempPass = new byte[16];
		final Random random = new Random();
		random.nextBytes(tempPass);
		String tempPassword = new String(Base64.getEncoder().encode(tempPass));
		// Encryption with md5
		user.setPassword(SystemSupport.md5(tempPassword));
		user.setPasswordHolder(SystemSupport.md5(tempPassword));
		dao.saveEntity(user);

		// If we correctly entered the user in the database, then send them
		// an email.
		SystemSupport.sendEmail(user.getEmailAddress(), "Test Subject",
				"Hello " + user.getFirstName() + ",<br>"
						+ "Thank you for registering for an Iowa Air online account! We are pleased to have your business. "
						+ "Please login to your Iowa Air account using the credentials listed below. You may update your password after login.<br><br>"
						+ "Username: " + user.getEmailAddress() + "<br>" + "Password: " + tempPassword
						+ "<br><br>Sincerely,<br>Iowa Air",
				null, null);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate
	 * update explicitly. Just fetch the entity from db and update it with
	 * proper values within transaction. It will be updated in db once
	 * transaction ends.
	 */
	@Override
	public void updateEntity(final User user) {
		final User entity = dao.findById(user.getId());
		if (entity != null) {
			entity.setUserType(user.getUserType());
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmailAddress(user.getEmailAddress());
			entity.setPassword(user.getPassword());
		}
	}

	@Override
	public List<User> findAllEntities() {
		return dao.findAllEntities();
	}

	@Override
	public void deleteEntityById(final String id) {
		dao.deleteEntityById(id);
	}

	@Override
	public boolean isEquivalentInDb(final User entered, final User db) {
		final String enteredUsername = entered.getEmailAddress();
		final String enteredPassword = entered.getPassword();

		if ((enteredUsername != null) && (enteredPassword != null)) {
			return enteredUsername.equals(db.getEmailAddress())
					&& db.getPassword().equals(SystemSupport.md5(enteredPassword));
		}
		return false;
	}

	@Override
	public User getStoredEntity(User enteredEntity) {
		final List<User> allEntries = findAllEntities();
		if ((allEntries == null) || (allEntries.isEmpty())) {
			return null;
		} else {
			final Optional<User> result = allEntries.stream().filter(entry -> isEquivalentInDb(enteredEntity, entry))
					.findAny();
			if (result.isPresent()) {
				return result.get();
			} else {
				return null;
			}
		}
	}

	public List<User> findSelectedEntities(final User entity) {
		return dao.findSelectedEntities(entity);
	}

	@Override
	public boolean isValidId(final String userId) {
		return (getUserById(userId) != null);
	}

	@Override
	public User getUserById(final String userId) {
		if ((userId == null) || userId.isEmpty()) {
			System.err.println("User id is not set. Requesting login.");
			return null;
		} else {
			final int usrId;
			try {
				usrId = Integer.parseInt(userId);
			} catch (final Exception e) {
				return null;
			}
			return findAllEntities().stream().filter(user -> user.getId() == usrId).findAny().orElse(null);
		}
	}
}
