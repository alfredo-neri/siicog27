package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm1411;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm1411Repository.
 */
@Repository(value="pm1411Repository")
public interface Pm1411Repository
		extends PagingAndSortingRepository<Pm1411, Long>, QueryDslPredicateExecutor<Pm1411>{
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm1411> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm1411> S save(S entity);
	
	/**
	 * Find all by idsector order by mensual asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm1411> findAllByIdsectorOrderByMensualAsc(Integer idSector);
}
