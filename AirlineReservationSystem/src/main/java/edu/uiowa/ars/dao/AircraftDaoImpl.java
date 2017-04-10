package edu.uiowa.ars.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import edu.uiowa.ars.model.Aircraft;

@Repository("aircraftDao")
public final class AircraftDaoImpl extends AbstractDao<Integer, Aircraft> implements AircraftDao {

	public void saveEntity(final Aircraft user) {
		persist(user);
	}

	@SuppressWarnings("unchecked")
	public List<Aircraft> findAllEntities() {
		final Criteria criteria = createEntityCriteria();
		return (List<Aircraft>) criteria.list();
	}

	@Override
	public void deleteEntityById(final String id) {
		getSession().createSQLQuery("DELETE FROM aircraft WHERE id = " + id).executeUpdate();
	}
        
        @SuppressWarnings("unchecked")
	public List<Aircraft> findSelectedEntities(final Aircraft entity) {
		final Criteria criteria = createEntityCriteria();
		return (List<Aircraft>) criteria.list();
	}

}
