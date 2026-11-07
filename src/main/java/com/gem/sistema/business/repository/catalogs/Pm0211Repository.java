package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm0211;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm0211Repository.
 */
@Repository(value="pm0211Repository")
public interface Pm0211Repository
		extends PagingAndSortingRepository<Pm0211, Long>, QueryDslPredicateExecutor<Pm0211>{
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm0211> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm0211> S save(S entity);
	
	/**
	 * Find all by idsector order by mensual asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm0211> findAllByIdsectorOrderByMensualAsc(Integer idSector);
}
