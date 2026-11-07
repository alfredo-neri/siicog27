package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm3111;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm3111Repository.
 */
@Repository(value = "pm3111Repository")
public interface Pm3111Repository extends PagingAndSortingRepository<Pm3111, Long>, QueryDslPredicateExecutor<Pm3111> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm3111> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm3111> S save(S entity);
	
	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @return the pm 3111
	 */
	Pm3111 findAllByIdsector(Integer idSector);
	
	/**
	 * Count.
	 *
	 * @param idSector the id sector
	 * @param anual the anual
	 * @return the integer
	 */
	@Query("select count(1) from Pm3111 where idsector=:idSector and anual=:anual")
	Integer count(@Param("idSector") Integer idSector, @Param("anual") Integer anual);
}
