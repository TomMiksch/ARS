package edu.uiowa.ars.service;

import edu.uiowa.ars.SystemSupport;
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
	public void saveEntity(final Booking booking) {
		dao.saveEntity(booking);
	}

	@Override
	public void deleteEntityById(final String id) {
		dao.deleteEntityById(id);
	}
        
        @Override
	public void confirmEntityByEmail(final String email) {
		SystemSupport.sendEmail(email, "Test Subject",
				"Hello,<br>"
					+ "Your flight has been confirmed.<br>"
					+ "<br><br>Sincerely,<br>Iowa Air",
				null, null);
	}

	@Override
	public void updateEntity(final Booking booking) {
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
