package edu.uiowa.ars.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        dao.updateEntity(aircraft);
    }

    public List<FlightRoute> findAllEntities() {
        return dao.findAllEntities();
    }

    @Override
    public boolean isEquivalentInDb(final FlightRoute entered, final FlightRoute db) {
        final String enteredSymbol = entered.getSymbol();

        if ((enteredSymbol != null)) {
            return enteredSymbol.equals(db.getSymbol());
        }
        return false;
    }

    @Override
    public FlightRoute getStoredEntity(FlightRoute enteredEntity) {
        final List<FlightRoute> allEntries = findAllEntities();
        if ((allEntries == null) || (allEntries.isEmpty())) {
            return null;
        } else {
            final Optional<FlightRoute> result = allEntries.stream()
                    .filter(entry -> isEquivalentInDb(enteredEntity, entry)).findAny();
            if (result.isPresent()) {
                return result.get();
            } else {
                return null;
            }
        }
    }

    public List<FlightRoute> findSelectedEntities(final FlightRoute entity) {
        return dao.findSelectedEntities(entity);
    }

    @Override
    public List<String> getAllSymbols() {
        return findAllEntities().stream().map(FlightRoute::getSymbol).collect(Collectors.toList());
    }

}
