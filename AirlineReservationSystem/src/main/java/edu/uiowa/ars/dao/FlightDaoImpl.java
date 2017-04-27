package edu.uiowa.ars.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import edu.uiowa.ars.model.Flight;

@Repository("flightDao")
public final class FlightDaoImpl extends AbstractDao<Integer, Flight> implements FlightDao {

	@Override
	public void saveEntity(final Flight flight) {
		persist(flight);
	}

	@SuppressWarnings("unchecked")
	public List<Flight> findAllEntities() {
		final Criteria criteria = createEntityCriteria();
		return (List<Flight>) criteria.list();
	}

	public List<Flight> findSelectedEntities(final Flight entity) {
		return Collections.emptyList();
	}

	@Override
	public void deleteEntityById(final String id) {
		getSession().createSQLQuery("DELETE FROM flight WHERE id = " + id).executeUpdate();
	}

	@Override
	public void deleteAllFlightsFromRoute(final String id) {
		getSession().createSQLQuery("DELETE FROM flight WHERE flight_route_id = " + id).executeUpdate();
	}
}
