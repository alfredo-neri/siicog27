package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.gem.sistema.business.domain.Pm5211;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm5211Repository.
 */
@Repository(value = "pm5211Repository")
public interface Pm5211Repository extends PagingAndSortingRepository<Pm5211, Long>, QueryDslPredicateExecutor<Pm5211> {
	
	/* (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm5211> findAll();
	
	/**
	 * Save.
	 *
	 * @param <S> the generic type
	 * @param entity the entity
	 * @return the s
	 */
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm5211> S save(S entity);
	
	
	/**
	 * Gets the min month.
	 *
	 * @param idSector the id sector
	 * @return the min month
	 */
	@Query("select min(mensual) from Pm5211 where idsector = :idSector ")
	Integer getMinMonth( @Param("idSector")Integer idSector);
	
	/**
	 * Gets the mensual.
	 *
	 * @param idSector the id sector
	 * @param mensual the mensual
	 * @return the mensual
	 */
	@Query("select p from Pm5211 p where idsector = :idSector and mensual =:mensual")
	Pm5211 getMensual(@Param("idSector")Integer idSector, @Param("mensual")Integer mensual);
	
	/**
	 * Find by id sector oder by mensual.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query("select p from Pm5211 p where idsector =:idSector order by mensual asc")
	List<Pm5211> findByIdSectorOderByMensual(@Param("idSector")Integer idSector);
}
