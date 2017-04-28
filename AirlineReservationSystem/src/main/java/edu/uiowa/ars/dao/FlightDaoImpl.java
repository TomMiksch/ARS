package edu.uiowa.ars.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import edu.uiowa.ars.model.Flight;
import java.util.ArrayList;
import org.hibernate.criterion.Restrictions;

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
            Criteria criteria = createEntityCriteria();
                List<Flight> allFlights = new ArrayList<>();
		// check the non-stop flights
                criteria.add(Restrictions.eq("origin", entity.getOrigin()));
                criteria.add(Restrictions.eq("destination", entity.getDestination()));
                criteria.add(Restrictions.eq("date", entity.getDate()));
                

                // check the flights with stops (two stops)
                // this is brute for loop to find all the two-stop flights
                Criteria criteriaIntermediate = createEntityCriteria();
                criteriaIntermediate.add(Restrictions.eq("origin", entity.getOrigin()));
                List<Flight> intermediateFlights = criteriaIntermediate.list();

                List<Flight> flightWithStops = new ArrayList<>();
                for (Flight flight : intermediateFlights) {
                    Criteria tempCriteria = createEntityCriteria();
                    tempCriteria.add(Restrictions.eq("origin", flight.getDestination()));
                    tempCriteria.add(Restrictions.eq("destination", entity.getDestination()));
                    if (tempCriteria.list() != null) {
                        if (!tempCriteria.list().isEmpty()) { 
                            flightWithStops.addAll(tempCriteria.list());
                            flightWithStops.add(flight);
                        }
                    }
                }
		allFlights.addAll(flightWithStops);
		allFlights.addAll(criteria.list());
                
                return (List<Flight>) allFlights;
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
        public void updateEntity(final Flight entity){
                int id = entity.getId();
                String flightClass = entity.getFlightClass();
                int seats = entity.getEconomyClassPrice();
                System.out.println("UPDATE flight SET flight_class = '" + flightClass +
                    "', seats = " + seats +  " WHERE id = " + id);
                getSession().createSQLQuery("UPDATE flight SET flight_class = " + flightClass +
                    ", seats = " + seats +  " WHERE id = " + id).executeUpdate();
        }
}
