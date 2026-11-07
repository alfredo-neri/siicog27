package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm4311;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm4311Repository.
 */
@Repository(value="pm4311Repository")
public interface Pm4311Repository
		extends PagingAndSortingRepository<Pm4311, Long>, QueryDslPredicateExecutor<Pm4311>{
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm4311> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm4311> S save(S entity);

	/**
	 * Find all by idsector order by mensual asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm4311> findAllByIdsectorOrderByMensualAsc(Integer idSector);
}
