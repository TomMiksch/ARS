package edu.uiowa.ars.dao;

import java.util.List;

import edu.uiowa.ars.model.User;

public interface UserDao {

	User findById(int id);

       // void findUserByEmail(User user);

	void saveUser(User user);

	List<User> findAllUsers();

	void deleteUserById(String id);
}
