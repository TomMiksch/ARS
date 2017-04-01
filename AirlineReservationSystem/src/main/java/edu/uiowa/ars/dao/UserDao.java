package edu.uiowa.ars.dao;

import edu.uiowa.ars.model.User;

public interface UserDao extends EntityDao<User> {

	User findById(int id);
}
