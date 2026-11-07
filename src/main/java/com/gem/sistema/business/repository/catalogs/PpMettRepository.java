package com.gem.sistema.business.repository.catalogs;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.PpMeta;
import com.gem.sistema.business.domain.PpMett;;

// TODO: Auto-generated Javadoc
/**
 * The Interface PpMettRepository.
 */
@Repository
public interface PpMettRepository extends PagingAndSortingRepository<PpMett, Long>, QueryDslPredicateExecutor<PpMett> {

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends PpMett> S save(S entity);
}
