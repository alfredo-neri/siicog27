package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm0611;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm0611Repository.
 */
@Repository(value="pm0611Repository")
public interface Pm0611Repository
		extends PagingAndSortingRepository<Pm0611, Long>, QueryDslPredicateExecutor<Pm0611>{
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm0611> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm0611> S save(S entity);
	
	/**
	 * Find all by idsector order by semestral asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm0611> findAllByIdsectorOrderBySemestralAsc(Integer idSector);
}
