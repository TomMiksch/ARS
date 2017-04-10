package edu.uiowa.ars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uiowa.ars.dao.FlightRouteDao;
import edu.uiowa.ars.model.FlightRoute;

@Service("flightRouteService")
@Transactional
public final class FlightRouteServiceImpl implements FlightRouteService {

	@Autowired
	private FlightRouteDao dao;

	@Override
	public void saveEntity(final FlightRoute aircraft) {
		dao.saveEntity(aircraft);
	}

	@Override
	public void deleteEntityById(final String id) {
		dao.deleteEntityById(id);
	}

	@Override
	public void updateEntity(final FlightRoute aircraft) {
	}

	public List<FlightRoute> findAllEntities() {
		return dao.findAllEntities();
	}

	@Override
	public boolean isEquivalentInDb(final FlightRoute entered, final FlightRoute db) {
		// TODO
		return false;
	}

	@Override
	public FlightRoute getStoredEntity(FlightRoute enteredEntity) {
		// TODO
		return null;
	}
        
        public List<FlightRoute> findSelectedEntities(final FlightRoute entity) {
		return dao.findSelectedEntities(entity);
	}

}
