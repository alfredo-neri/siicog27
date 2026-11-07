package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm1711;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm1711Repository.
 */
@Repository(value="pm1711Repository")
public interface Pm1711Repository
		extends PagingAndSortingRepository<Pm1711, Long>, QueryDslPredicateExecutor<Pm1711>{
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm1711> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm1711> S save(S entity);
	
	/**
	 * Find all by idsector order by trimestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm1711> findAllByIdsectorOrderByTrimestreAsc(Integer idSector);
}
