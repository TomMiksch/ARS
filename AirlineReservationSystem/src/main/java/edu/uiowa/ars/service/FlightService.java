package edu.uiowa.ars.service;

import java.util.List;

import edu.uiowa.ars.model.Flight;

public interface FlightService extends EntityManagementService<Flight> {

	void deleteAllFlightsFromRoute(final String id);
        
        void updateEntity(Flight entity);

		List<Flight> searchFlights(Flight flight, String parameter);
}
