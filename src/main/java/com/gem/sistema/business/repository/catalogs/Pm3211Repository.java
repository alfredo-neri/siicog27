package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm3211;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm3211Repository.
 */
@Repository(value = "pm3211Repository")
public interface Pm3211Repository extends PagingAndSortingRepository<Pm3211, Long>, QueryDslPredicateExecutor<Pm3211> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm3211> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm3211> S save(S entity);

	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @return the pm 3211
	 */
	Pm3211 findAllByIdsector(Integer idSector);
	
	/**
	 * Find all by idsector and anual.
	 *
	 * @param idSector the id sector
	 * @param anio the anio
	 * @return the pm 3211
	 */
	Pm3211 findAllByIdsectorAndAnual(Integer idSector, Integer anio);

	/**
	 * Count.
	 *
	 * @param idSector the id sector
	 * @param anual the anual
	 * @return the integer
	 */
	@Query("select count(1) from Pm3211 where idsector=:idSector and anual=:anual")
	Integer count(@Param("idSector") Integer idSector, @Param("anual") Integer anual);
}
