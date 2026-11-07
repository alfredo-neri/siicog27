package com.gem.sistema.business.repository.catalogs;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface RepositoryCustom.
 *
 * @author Juan Carlos Pedraza Alcala
 */
public interface RepositoryCustom {

	
	/**
	 * Find by filters.
	 *
	 * @param entity the entity
	 * @return the list
	 */
	List<?> findByFilters(Object entity);
	
	
	/**
	 * Find by filters.
	 *
	 * @param entity the entity
	 * @param operator the operator
	 * @return the list
	 */
	List<?> findByFilters(Object entity, String operator);
	
	/**
	 * Find by filters order by.
	 *
	 * @param entity the entity
	 * @param fieldName the field name
	 * @return the list
	 */
	List<?> findByFiltersOrderBy(Object entity, String fieldName);
	
	/**
	 * Find by filters order by.
	 *
	 * @param entity the entity
	 * @param fieldName the field name
	 * @param operator the operator
	 * @return the list
	 */
//	List<?> findByFiltersOrderBy(Object entity, String ... fieldNames);
	
	/**
	 * 
	 * @param entity
	 * @param fieldName
	 * @param operator
	 * @return
	 */
	List<?> findByFiltersOrderBy(Object entity, String fieldName, String operator);
	
	/**
	 * Obtain one.
	 *
	 * @param sql the sql
	 * @return the object
	 */
	Object obtainOne(String sql);
	
	
}
