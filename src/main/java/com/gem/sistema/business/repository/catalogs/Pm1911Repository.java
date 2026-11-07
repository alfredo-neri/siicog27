package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm1911;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm1911Repository.
 */
@Repository(value="pm1911Repository")
public interface Pm1911Repository
		extends PagingAndSortingRepository<Pm1911, Long>, QueryDslPredicateExecutor<Pm1911>{
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm1911> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm1911> S save(S entity);
	
	/**
	 * Find all by idsector order by trimestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm1911> findAllByIdsectorOrderByTrimestreAsc(Integer idSector);
}
