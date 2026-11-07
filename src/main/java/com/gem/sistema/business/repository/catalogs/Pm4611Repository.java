package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm4611;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm4611Repository.
 */
@Repository(value = "pm4611Repository")
public interface Pm4611Repository extends PagingAndSortingRepository<Pm4611, Long>, QueryDslPredicateExecutor<Pm4611> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm4611> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm4611> S save(S entity);
	
	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @return the pm 4611
	 */
	Pm4611 findAllByIdsector(Integer idSector);
	
	/**
	 * Count.
	 *
	 * @param idSector the id sector
	 * @param anual the anual
	 * @return the integer
	 */
	@Query("select count(1) from Pm4611 where idsector=:idSector and anual=:anual")
	Integer count(@Param("idSector") Integer idSector, @Param("anual") Integer anual);
}
