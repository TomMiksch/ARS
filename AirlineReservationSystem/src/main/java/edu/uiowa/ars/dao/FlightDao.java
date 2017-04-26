package edu.uiowa.ars.dao;

import edu.uiowa.ars.model.Flight;

public interface FlightDao extends EntityDao<Flight> {

	void deleteAllFlightsFromRoute(final String id);
}
