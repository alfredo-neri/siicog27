package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Ambitos;;

// TODO: Auto-generated Javadoc
/**
 * The Interface AmbitosRepository.
 */
@Repository
public interface AmbitosRepository extends PagingAndSortingRepository<Ambitos, Long>, QueryDslPredicateExecutor<Ambitos>{
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Ambitos> findAll();
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Ambitos> S save(S entity);	
}
