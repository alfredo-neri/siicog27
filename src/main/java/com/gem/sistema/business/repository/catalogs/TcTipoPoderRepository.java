package com.gem.sistema.business.repository.catalogs;

import java.util.List;


import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.TcTipoPoder;


// TODO: Auto-generated Javadoc
/**
 * The Interface TcTipoPoderRepository.
 */
@Repository(value = "tcTipoPoderRepository")
public interface TcTipoPoderRepository extends PagingAndSortingRepository<TcTipoPoder, Long>, QueryDslPredicateExecutor<TcTipoPoder> {
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TcTipoPoder> findAll();
	
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcTipoPoder> S save(S entity);
	
}
