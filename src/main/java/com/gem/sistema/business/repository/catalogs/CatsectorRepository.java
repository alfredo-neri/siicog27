package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;


import com.gem.sistema.business.domain.Catsector;

// TODO: Auto-generated Javadoc
/**
 * The Interface CatsectorRepository.
 */
public interface CatsectorRepository extends PagingAndSortingRepository<Catsector, Long>, CrudRepository<Catsector, Long>, QuerydslPredicateExecutor<Catsector> {
	
     /* (non-Javadoc)
      * @see org.springframework.data.repository.CrudRepository#findAll()
      */
     List<Catsector> findAll();
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Catsector> S save(S entity);

}
