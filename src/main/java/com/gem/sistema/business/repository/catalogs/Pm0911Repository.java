package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm0911;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm0911Repository.
 */
@Repository(value="pm0911Repository")
public interface Pm0911Repository
		extends PagingAndSortingRepository<Pm0911, Long>, QueryDslPredicateExecutor<Pm0911>{
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm0911> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm0911> S save(S entity);
	
	/**
	 * Find all by idsector order by semes asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm0911> findAllByIdsectorOrderBySemesAsc(Integer idSector);
}
