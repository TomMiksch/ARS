package edu.uiowa.ars.service;

import java.util.List;

import edu.uiowa.ars.model.Aircraft;

public interface AircraftService extends EntityManagementService<Aircraft> {
	
	public List<String> getAllSymbols();
}
