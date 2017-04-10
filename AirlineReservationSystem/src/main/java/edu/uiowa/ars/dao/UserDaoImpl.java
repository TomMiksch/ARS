package edu.uiowa.ars.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import edu.uiowa.ars.model.User;

@Repository("userDao")
public final class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public User findById(final int id) {
		return getByKey(id);
	}

	public void saveEntity(final User user) {
		persist(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllEntities() {
		final Criteria criteria = createEntityCriteria();
		return (List<User>) criteria.list();
	}

	@Override
	public void deleteEntityById(final String id) {
		getSession().createSQLQuery("DELETE FROM user WHERE id = " + id).executeUpdate();
	}
        
        @SuppressWarnings("unchecked")
	public List<User> findSelectedEntities(final User entity) {
		final Criteria criteria = createEntityCriteria();
		return (List<User>) criteria.list();
	}
}
