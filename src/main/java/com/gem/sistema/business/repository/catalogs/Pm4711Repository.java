package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm4711;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm4711Repository.
 */
@Repository(value = "pm4711Repository")
public interface Pm4711Repository extends PagingAndSortingRepository<Pm4711, Long>, QueryDslPredicateExecutor<Pm4711> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm4711> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm4711> S save(S entity);

	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @return the pm 4711
	 */
	Pm4711 findAllByIdsector(Integer idSector);

	/**
	 * Count.
	 *
	 * @param idSector the id sector
	 * @param anual the anual
	 * @return the integer
	 */
	@Query("select count(1) from Pm4711 where idsector=:idSector and anual=:anual")
	Integer count(@Param("idSector") Integer idSector, @Param("anual") Integer anual);
}
