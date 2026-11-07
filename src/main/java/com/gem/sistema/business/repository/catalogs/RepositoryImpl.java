package com.gem.sistema.business.repository.catalogs;


import static com.gem.sistema.util.UtilJPA.executeDinamicQueryJPA;
import static com.gem.sistema.util.UtilJPA.executeDinamicQueryOrderByJPA;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

// TODO: Auto-generated Javadoc
/**
 * The Class RepositoryImpl.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@Repository
public class RepositoryImpl implements RepositoryCustom {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryImpl.class);
		
	/** The em. */
	@Autowired
	private EntityManager em;
	
	/*
	 * (non-Javadoc)
	 * @see com.gem.sistema.business.repository.catalogs.TipoCuentaRepositoryCustom#finByFilters(
	 * com.gem.sistema.business.domain.TipoCuenta)
	 */	
	@Override
	public List<?> findByFilters(final Object entity) {
		LOGGER.info(":: Buscar por filtros {}" , entity);		
		return executeDinamicQueryJPA(em, entity, null);
	}
	
	/*
	 *(non-Javadoc)
	 * @see com.gem.sistema.business.repository.catalogs.TipoCuentaRepositoryCustom#finByFilters(
	 * com.gem.sistema.business.domain.TipoCuenta)
	 */
	@Override
	public List<?> findByFilters(final Object entity, final String operator) {
		LOGGER.info(":: Buscar por filtros {}" , entity);		
		return executeDinamicQueryJPA(em, entity, operator);
	}
	

	/*
	 * (non-Javadoc)
	 * @see com.gem.sistema.business.repository.catalogs.RepositoryCustom#findByFiltersOrderBy(java.lang.Object, java.lang.String)
	 */
	@Override
	public List<?> findByFiltersOrderBy(final Object entity, final String fieldName) {		
		return executeDinamicQueryOrderByJPA(em, entity, fieldName, null);
	}		
	
	/*
	 * (non-Javadoc) 
	 * see com.gem.sistema.business.repository.catalogs.RepositoryCustom#findByFiltersOrderBy(java.lang.Object, java.lang.String)
	 */
	@Override
	public List<?> findByFiltersOrderBy(final Object entity,  final String fieldName, final String operator) {		
		return executeDinamicQueryOrderByJPA(em, entity, fieldName, operator);
	}		
	
	/*
	 * (non-Javadoc)
	 * @see com.gem.sistema.business.repository.catalogs.RepositoryCustom#obtainOne(java.lang.String)
	 */
	public Object obtainOne(final String sql) {
		final Query query = em.createQuery(sql);		 
		return query.getSingleResult();
	}

}
