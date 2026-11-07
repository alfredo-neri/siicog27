package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm0311;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm0311Repository.
 */
@Repository(value="pm0311Repository")
public interface Pm0311Repository
		extends PagingAndSortingRepository<Pm0311, Long>, QueryDslPredicateExecutor<Pm0311>{
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm0311> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm0311> S save(S entity);
	
	/**
	 * Find all by idsector order by trimestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm0311> findAllByIdsectorOrderByTrimestreAsc(Integer idSector);
}
