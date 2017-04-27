package edu.uiowa.ars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uiowa.ars.dao.FlightRouteDao;
import edu.uiowa.ars.model.FlightRoute;
import java.util.stream.Collectors;

@Service("flightRouteService")
@Transactional
public final class FlightRouteServiceImpl implements FlightRouteService {

	@Autowired
	private FlightRouteDao dao;

	@Override
	public void saveEntity(final FlightRoute aircraft) {
                final List<FlightRoute> flightList = findAllEntities();
		if ((flightList == null) || (flightList.size() == 0)) {
			aircraft.setSymbol("001");
		} else {
			final String prevSymbol = flightList.get(flightList.size() - 1).getSymbol();
			try {
				final int newSymbolNum = Integer.parseInt(prevSymbol) + 1;
				aircraft.setSymbol(String.format("%03d", newSymbolNum));
			} catch (final Exception e) {
				System.err.println("Failed to parse flight symbol: " + prevSymbol);
				throw e;
			}
		}
            
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
        
        @Override
	public List<String> getAllSymbols() {
		return findAllEntities().stream().map(FlightRoute::getSymbol).collect(Collectors.toList());
	}

}
