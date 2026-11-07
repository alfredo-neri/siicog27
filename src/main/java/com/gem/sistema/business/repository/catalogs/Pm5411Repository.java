package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm5411;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm5411Repository.
 */
@Repository(value="pm5411Repository")
public interface Pm5411Repository
		extends PagingAndSortingRepository<Pm5411, Long>, QueryDslPredicateExecutor<Pm5411>{
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm5411> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm5411> S save(S entity);

	/**
	 * Find all by idsector order by mensual asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm5411> findAllByIdsectorOrderByMensualAsc(Integer idSector);
	
}
