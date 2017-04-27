package edu.uiowa.ars.dao;

import edu.uiowa.ars.model.FlightRoute;

public interface FlightRouteDao extends EntityDao<FlightRoute> {
    
    void updateEntity(FlightRoute entity);
}
