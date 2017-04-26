package edu.uiowa.ars.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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

	@SuppressWarnings("unchecked")
	public List<FlightRoute> findSelectedEntities(final FlightRoute entity) {
		Criteria criteria = createEntityCriteria();
		// check the non-stop flights
		criteria.add(Restrictions.eq("origin", entity.getOrigin()));
		criteria.add(Restrictions.eq("destination", entity.getDestination()));
		// check the flights with stops (two stops)
		// this is brute for loop to find all the two-stop flights
		Criteria criteriaIntermediate = createEntityCriteria();
		criteriaIntermediate.add(Restrictions.eq("origin", entity.getOrigin()));
		List<FlightRoute> intermediateFlights = criteriaIntermediate.list();

		List<FlightRoute> flightWithStops = new ArrayList<>();
		for (FlightRoute flight : intermediateFlights) {
			Criteria tempCriteria = createEntityCriteria();
			tempCriteria.add(Restrictions.eq("origin", flight.getDestination()));
			tempCriteria.add(Restrictions.eq("destination", entity.getDestination()));
			flightWithStops.addAll(tempCriteria.list());
		}
		List<FlightRoute> allFlights = new ArrayList<>();
		allFlights.addAll(flightWithStops);
		allFlights.addAll(criteria.list());

		return (List<FlightRoute>) allFlights;
	}

	@Override
	public void deleteEntityById(final String id) {
		getSession().createSQLQuery("DELETE FROM flight_route WHERE id = " + id).executeUpdate();
	}
}
