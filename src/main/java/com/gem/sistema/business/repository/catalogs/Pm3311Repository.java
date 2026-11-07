package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm3311;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm3311Repository.
 */
@Repository(value = "pm3311Repository")
public interface Pm3311Repository extends PagingAndSortingRepository<Pm3311, Long>, QueryDslPredicateExecutor<Pm3311> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm3311> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm3311> S save(S entity);
	
	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @return the pm 3311
	 */
	Pm3311 findAllByIdsector(Integer idSector);
	
	/**
	 * Count.
	 *
	 * @param idSector the id sector
	 * @param anual the anual
	 * @return the integer
	 */
	@Query("select count(1) from Pm3311 where idsector=:idSector and anual=:anual")
	Integer count(@Param("idSector") Integer idSector, @Param("anual") Integer anual);
}
