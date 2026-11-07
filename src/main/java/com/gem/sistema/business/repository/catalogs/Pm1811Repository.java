package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm1811;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm1811Repository.
 */
@Repository(value="pm1811Repository")
public interface Pm1811Repository
		extends PagingAndSortingRepository<Pm1811, Long>, QueryDslPredicateExecutor<Pm1811>{
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm1811> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm1811> S save(S entity);
	
	/**
	 * Find all by idsector order by semes asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm1811> findAllByIdsectorOrderBySemesAsc(Integer idSector);
}
