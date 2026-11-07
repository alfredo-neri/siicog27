package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm4911;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm4911Repository.
 */
@Repository(value="pm4911Repository")
public interface Pm4911Repository
		extends PagingAndSortingRepository<Pm4911, Long>, QueryDslPredicateExecutor<Pm4911>, Pm4911RepositoryCustom {
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm4911> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm4911> S save(S entity);

	/**
	 * Find all by idsector order by mensual asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm4911> findAllByIdsectorOrderByMensualAsc(Integer idSector);
}
