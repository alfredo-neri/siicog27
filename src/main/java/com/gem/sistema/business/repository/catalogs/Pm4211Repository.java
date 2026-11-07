package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm4211;


// TODO: Auto-generated Javadoc
/**
 * The Interface Pm4211Repository.
 *
 * @author Alfredo
 */
@Repository(value = "pm4211Repository")
public interface Pm4211Repository extends PagingAndSortingRepository<Pm4211, Long>, QueryDslPredicateExecutor<Pm4211> {
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm4211> findAll();

	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm4211> S save(S entity);

	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @return the pm 4211
	 */
	Pm4211 findAllByIdsector(Integer idSector);
	
	/**
	 * Find all by idsector and mes.
	 *
	 * @param idSector the id sector
	 * @param mes the mes
	 * @return the pm 4211
	 */
	Pm4211 findAllByIdsectorAndMes(Integer idSector, Integer mes);
	
	/**
	 * Count rows.
	 *
	 * @param idSector the id sector
	 * @param mes the mes
	 * @return the integer
	 */
	@Query("select count(1) from Pm4211 where idsector=:idSector and mes=:mes")
	Integer countRows(@Param("idSector") Integer idSector, @Param("mes") Integer mes);
}
