package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.PpMeta;;

// TODO: Auto-generated Javadoc
/**
 * The Interface PpMetaRepository.
 */
@Repository
public interface PpMetaRepository extends PagingAndSortingRepository<PpMeta, Long>, QueryDslPredicateExecutor<PpMeta> {

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends PpMeta> S save(S entity);

	/**
	 * Find all by clvdep.
	 *
	 * @param clvdep the clvdep
	 * @return the list
	 */
	List<PpMeta> findAllByClvdep(String clvdep);
	
	/**
	 * Find all by clvdep and clvnep.
	 *
	 * @param clvdep the clvdep
	 * @param clvnep the clvnep
	 * @return the list
	 */
	List<PpMeta> findAllByClvdepAndClvnep(String clvdep, String clvnep);
	
	/**
	 * Find all by clvdep and clvnep and clvmet.
	 *
	 * @param clvdep the clvdep
	 * @param clvnep the clvnep
	 * @param clvmet the clvmet
	 * @return the list
	 */
	List<PpMeta> findAllByClvdepAndClvnepAndClvmet(String clvdep, String clvnep, Integer clvmet);
}
