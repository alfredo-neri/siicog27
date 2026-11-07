package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm5611;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm5611Repository.
 */
@Repository(value = "pm5611Repository")
public interface Pm5611Repository extends PagingAndSortingRepository<Pm5611, Long>, QueryDslPredicateExecutor<Pm5611> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm5611> findAll();
	
	/**
	 * Find all byidsector.
	 *
	 * @param idsector the idsector
	 * @param sort the sort
	 * @return the list
	 */
	List<Pm5611> findAllByidsector(Integer idsector, Sort sort);
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm5611> S save(S entity);
	
	/**
	 * Exist month.
	 *
	 * @param mensual the mensual
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select count(1) from Pm5611 p where mensual = :mensual and idsector =:idSector")
	Integer existMonth(@Param("mensual")Integer mensual, @Param("idSector")Integer idSector);
	
	/**
	 * Gets the by id sector and mensual.
	 *
	 * @param idSector the id sector
	 * @param mensual the mensual
	 * @return the by id sector and mensual
	 */
	@Query("select p from Pm5611 p where idsector =:idSector and mensual =:mensual")
	Pm5611 getByIdSectorAndMensual(@Param("idSector")Integer idSector,@Param("mensual") Integer mensual);
	 
	
}
