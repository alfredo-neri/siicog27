package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm4111;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm4111Repository.
 */
@Repository(value = "pm4111Repository")
public interface Pm4111Repository extends PagingAndSortingRepository<Pm4111, Long>, QueryDslPredicateExecutor<Pm4111> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm4111> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm4111> S save(S entity);

	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @return the pm 4111
	 */
	Pm4111 findAllByIdsector(Integer idSector);

	/**
	 * Count.
	 *
	 * @param idSector the id sector
	 * @param anual the anual
	 * @return the integer
	 */
	@Query("select count(1) from Pm4111 where idsector=:idSector and anual=:anual")
	Integer count(@Param("idSector") Integer idSector, @Param("anual") Integer anual);
}
