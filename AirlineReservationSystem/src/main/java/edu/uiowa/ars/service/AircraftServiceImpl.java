package edu.uiowa.ars.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
		// The aircraft symbol is +1 the previous symbol with the format 001,
		// 002, etc.
		final List<Aircraft> aircraftList = findAllEntities();
		if ((aircraftList == null) || (aircraftList.size() == 0)) {
			aircraft.setSymbol("001");
		} else {
			final String prevSymbol = aircraftList.get(aircraftList.size() - 1).getSymbol();
			try {
				final int newSymbolNum = Integer.parseInt(prevSymbol) + 1;
				aircraft.setSymbol(String.format("%03d", newSymbolNum));
			} catch (final Exception e) {
				System.err.println("Failed to parse aircraft symbol: " + prevSymbol);
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

	@Override
	public List<String> getAllSymbols() {
		return findAllEntities().stream().map(Aircraft::getSymbol).collect(Collectors.toList());
	}
}
