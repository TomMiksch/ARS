package edu.uiowa.ars.service;

import edu.uiowa.ars.model.FlightRoute;
import java.util.List;

public interface FlightRouteService extends EntityManagementService<FlightRoute> {

    public List<String> getAllSymbols();
}
