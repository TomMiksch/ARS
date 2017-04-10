package edu.uiowa.ars.service;

import java.util.List;

public interface EntityManagementService<T> {

	void saveEntity(T entity);

	void updateEntity(T entity);

	void deleteEntityById(String id);

	List<T> findAllEntities();

	T getStoredEntity(T enteredEntity);
        
        List<T> findSelectedEntities(T entity);

	boolean isEquivalentInDb(T entered, T db);
}
