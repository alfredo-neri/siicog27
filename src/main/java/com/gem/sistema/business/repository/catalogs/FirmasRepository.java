package com.gem.sistema.business.repository.catalogs;

import java.util.List;


import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Firmas;


// TODO: Auto-generated Javadoc
/**
 * The Interface FirmasRepository.
 *
 * @author 
 */
@Repository(value = "firmasRepository")
public interface FirmasRepository
		extends PagingAndSortingRepository<Firmas, Long>, QueryDslPredicateExecutor<Firmas> {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Firmas> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Firmas> S save(S entity);

	/**
	 * Find all by idsector and id ref.
	 *
	 * @param idsector the idsector
	 * @param idRef the id ref
	 * @return the list
	 */
	List<Firmas> findAllByIdsectorAndIdRef(Integer idsector, long idRef);

	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @return the firmas
	 */
	Firmas findAllByIdsector(Integer idSector);

	/**
	 * Find first by idsector.
	 *
	 * @param idSector the id sector
	 * @return the firmas
	 */
	Firmas findFirstByIdsector(Integer idSector);
}
