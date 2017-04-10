package edu.uiowa.ars.dao;

import java.util.List;

public interface EntityDao<T> {

	void saveEntity(T entity);

	List<T> findAllEntities();
	
	void deleteEntityById(final String id);
        
        List<T> findSelectedEntities(T entity);
}
