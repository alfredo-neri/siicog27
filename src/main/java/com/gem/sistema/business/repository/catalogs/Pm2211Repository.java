package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm2211;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm2211Repository.
 */
@Repository(value="pm2211Repository")
public interface Pm2211Repository
		extends PagingAndSortingRepository<Pm2211, Long>, QueryDslPredicateExecutor<Pm2211>{
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm2211> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm2211> S save(S entity);
	
	/**
	 * Find all by idsector order by trimestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm2211> findAllByIdsectorOrderByTrimestreAsc(Integer idSector);
}
