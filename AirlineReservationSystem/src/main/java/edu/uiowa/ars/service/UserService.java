package edu.uiowa.ars.service;

import java.util.List;

import edu.uiowa.ars.model.User;

public interface UserService {

	User findById(int id);

	void saveUser(User user);

	void updateUser(User user);
        
        void checkUser(User user);

	List<User> findAllUsers();

	void deleteUserById(String id);
}
