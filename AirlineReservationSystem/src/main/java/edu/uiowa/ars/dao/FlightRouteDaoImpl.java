package edu.uiowa.ars.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import edu.uiowa.ars.model.FlightRoute;

@Repository("flightRouteDao")
public final class FlightRouteDaoImpl extends AbstractDao<Integer, FlightRoute> implements FlightRouteDao {

	@Override
	public void saveEntity(final FlightRoute user) {
		persist(user);
	}

	@SuppressWarnings("unchecked")
	public List<FlightRoute> findAllEntities() {
		final Criteria criteria = createEntityCriteria();
		return (List<FlightRoute>) criteria.list();
	}

	@Override
	public void deleteEntityById(final String id) {
		getSession().createSQLQuery("DELETE FROM flight_route WHERE id = " + id).executeUpdate();
	}
}