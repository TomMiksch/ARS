package edu.uiowa.ars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uiowa.ars.dao.BookingDao;
import edu.uiowa.ars.model.Booking;

@Service("bookingService")
@Transactional
public final class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDao dao;

	@Override
	public void saveEntity(final Booking aircraft) {
		dao.saveEntity(aircraft);
	}

	@Override
	public void deleteEntityById(final String id) {
		dao.deleteEntityById(id);
	}

	@Override
	public void updateEntity(final Booking aircraft) {
	}

	public List<Booking> findAllEntities() {
		return dao.findAllEntities();
	}

	@Override
	public boolean isEquivalentInDb(final Booking entered, final Booking db) {
		// TODO
		return false;
	}

	@Override
	public Booking getStoredEntity(Booking enteredEntity) {
		// TODO
		return null;
	}
        
        public List<Booking> findSelectedEntities(final Booking entity) {
		return dao.findSelectedEntities(entity);
	}

}
