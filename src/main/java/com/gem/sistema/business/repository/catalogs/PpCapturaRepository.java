package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.PpCaptura;;

// TODO: Auto-generated Javadoc
/**
 * The Interface PpCapturaRepository.
 */
@Repository
public interface PpCapturaRepository extends PagingAndSortingRepository<PpCaptura, Long>, QueryDslPredicateExecutor<PpCaptura> {
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends PpCaptura> S save(S entity);
	
	/**
	 * Find by clvdep and clvnep.
	 *
	 * @param clvdep the clvdep
	 * @param clvnep the clvnep
	 * @return the list
	 */
	@ReadOnlyProperty
	List<PpCaptura> findByClvdepAndClvnep(String clvdep, String clvnep);
}
