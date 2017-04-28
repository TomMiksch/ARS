package edu.uiowa.ars.service;

import edu.uiowa.ars.model.Flight;

public interface FlightService extends EntityManagementService<Flight> {

	void deleteAllFlightsFromRoute(final String id);
        
        void updateEntity(Flight entity);
}
