package edu.uiowa.ars.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import edu.uiowa.ars.model.Booking;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

@Repository("bookingDao")
public final class BookingDaoImpl extends AbstractDao<Integer, Booking> implements BookingDao {

	@Override
	public void saveEntity(final Booking user) {
		persist(user);
	}

	@SuppressWarnings("unchecked")
	public List<Booking> findAllEntities() {
		final Criteria criteria = createEntityCriteria();
		return (List<Booking>) criteria.list();
	}

        @SuppressWarnings("unchecked")
	public List<Booking> findSelectedEntities(final Booking entity) {
		Criteria criteria = createEntityCriteria();
		return (List<Booking>) criteria.list();
	}
        
	@Override
	public void deleteEntityById(final String id) {
		getSession().createSQLQuery("DELETE FROM flight_route WHERE id = " + id).executeUpdate();
	}
}
