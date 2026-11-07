package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Tipcta;

// TODO: Auto-generated Javadoc
/**
 * The Interface TipctaRepository.
 */
@Repository
public interface TipctaRepository  extends PagingAndSortingRepository<Tipcta, Long>, QueryDslPredicateExecutor<Tipcta> {
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Tipcta> S save(S entity);
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Tipcta> findAll();
	
	/**
	 * Find by clvtip.
	 *
	 * @param clvtip the clvtip
	 * @return the list
	 */
	List<Tipcta> findByClvtip(String clvtip);
	

}
