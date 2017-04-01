package edu.uiowa.ars.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uiowa.ars.dao.AircraftDao;
import edu.uiowa.ars.model.Aircraft;

@Service("aircraftService")
@Transactional
public final class AircraftServiceImpl implements AircraftService {

	@Autowired
	private AircraftDao dao;

	@Override
	public void saveEntity(final Aircraft aircraft) {
		dao.saveEntity(aircraft);
	}

	@Override
	public void deleteEntityById(final String id) {
		dao.deleteEntityById(id);
	}

	@Override
	public void updateEntity(final Aircraft aircraft) {
	}

	public List<Aircraft> findAllEntities() {
		return dao.findAllEntities();
	}

	@Override
	public boolean isEquivalentInDb(final Aircraft entered, final Aircraft db) {
		return db.getSymbol().equals(entered.getSymbol());
	}

	@Override
	public Aircraft getStoredEntity(Aircraft enteredEntity) {
		final List<Aircraft> allEntries = findAllEntities();
		if ((allEntries == null) || (allEntries.isEmpty())) {
			return null;
		} else {
			final Optional<Aircraft> result = allEntries.stream()
					.filter(entry -> isEquivalentInDb(enteredEntity, entry)).findAny();
			if (result.isPresent()) {
				return result.get();
			} else {
				return null;
			}
		}
	}
}
