package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm1611;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm1611Repository.
 */
@Repository(value="pm1611Repository")
public interface Pm1611Repository
		extends PagingAndSortingRepository<Pm1611, Long>, QueryDslPredicateExecutor<Pm1611>{
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm1611> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm1611> S save(S entity);
	
	/**
	 * Find all by idsector order by trimestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm1611> findAllByIdsectorOrderByTrimestreAsc(Integer idSector);
}
