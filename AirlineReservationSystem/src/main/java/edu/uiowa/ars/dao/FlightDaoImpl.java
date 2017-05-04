package edu.uiowa.ars.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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

	@SuppressWarnings("unchecked")
	public List<Flight> findSelectedEntities(final Flight entity) {
		final Criteria criteria = createEntityCriteria();
		final Set<Flight> allFlights = new HashSet<>();
		// check the non-stop flights
		// there has to be origin and destinations
		// depart time is optional

		if (!entity.getDate().isEmpty()) {
			criteria.add(Restrictions.eq("date", entity.getDate()));
		}
		if (entity.getOrigin() != null) {
			criteria.add(Restrictions.eq("origin", entity.getOrigin()));
		}
		if (entity.getDestination() != null) {
			criteria.add(Restrictions.eq("destination", entity.getDestination()));
		}
		// check the flights with stops (two stops)
		// this is brute for loop to find all the two-stop flights
		final Criteria criteriaIntermediate = createEntityCriteria();
		criteriaIntermediate.add(Restrictions.eq("origin", entity.getOrigin()));
		final List<Flight> intermediateFlights = criteriaIntermediate.list();

		final boolean dateSet = (entity.getDate() != null) && (!entity.getDate().isEmpty());
		final Set<Flight> flightWithStops = new HashSet<>();
		for (Flight flight : intermediateFlights) {
			final Criteria tempCriteria = createEntityCriteria();
			tempCriteria.add(Restrictions.eq("origin", flight.getDestination()));
			tempCriteria.add(Restrictions.eq("destination", entity.getDestination()));
			if (dateSet) {
				tempCriteria.add(Restrictions.eq("date", entity.getDate()));
			}
			if (tempCriteria.list() != null) {
				if (!tempCriteria.list().isEmpty()) {

					flightWithStops.addAll(tempCriteria.list());
					if (dateSet) {
						if (entity.getDate().equals(flight.getDate())) {
							flightWithStops.add(flight);
						}
					} else {
						flightWithStops.add(flight);
					}
				}
			}
		}
		allFlights.addAll(flightWithStops);
		allFlights.addAll(criteria.list());

		return new ArrayList<>(allFlights);
	}

	@Override
	public void deleteEntityById(final String id) {
		getSession().createSQLQuery("DELETE FROM flight WHERE id = " + id).executeUpdate();
	}

	@Override
	public void deleteAllFlightsFromRoute(final String id) {
		getSession().createSQLQuery("DELETE FROM flight WHERE flight_route_id = " + id).executeUpdate();
	}

	@Override
	public void updateEntity(final Flight entity) {
		int id = entity.getId();
		String flightClass = entity.getFlightClass();
		int seats = entity.getEconomyClassPrice();
		getSession()
				.createSQLQuery(
						"UPDATE flight SET flight_class = " + flightClass + ", seats = " + seats + " WHERE id = " + id)
				.executeUpdate();
	}
}
