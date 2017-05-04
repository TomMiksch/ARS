package edu.uiowa.ars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import edu.uiowa.ars.dao.FlightDao;
import edu.uiowa.ars.model.Flight;

@Service("flightService")
@Transactional
public final class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightDao dao;

    @Override
    public void saveEntity(final Flight flight) {
        dao.saveEntity(flight);
    }

    @Override
    public void deleteEntityById(final String id) {
        dao.deleteEntityById(id);
    }

    @Override
    public void updateEntity(final Flight flight) {
        dao.updateEntity(flight);
    }

    public List<Flight> findAllEntities() {
        return dao.findAllEntities();
    }

    @Override
    public boolean isEquivalentInDb(final Flight entered, final Flight db) {
        return false;
    }

    @Override
    public Flight getStoredEntity(final Flight enteredEntity) {
        return null;
    }

    public List<Flight> findSelectedEntities(final Flight entity) {
        return dao.findSelectedEntities(entity);
    }

    @Override
    public void deleteAllFlightsFromRoute(final String id) {
        dao.deleteAllFlightsFromRoute(id);
    }

    @Override
    public List<Flight> searchFlights(final Flight flight, final String returnDate) {
        // Start by finding one way flights.
        final List<Flight> there = findSelectedEntities(flight);

        if (!Strings.isNullOrEmpty(returnDate)) {
            // Modify the existing flight to find the route back.
            flight.setDate(returnDate);
            final String temp = flight.getDestination();
            flight.setDestination(flight.getOrigin());
            flight.setOrigin(temp);
            final List<Flight> back = findSelectedEntities(flight);

            if (back.size() > 0) {
                back.forEach(entry -> {
                    if (!there.contains(entry)) {
                        there.add(entry);
                    }
                });
            } else {
                there.clear();
            }

        }
        return there;
    }

}
