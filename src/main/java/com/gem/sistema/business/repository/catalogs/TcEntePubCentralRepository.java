package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.TcEntePubCentral;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcEntePubCentralRepository.
 */
public interface TcEntePubCentralRepository extends PagingAndSortingRepository<TcEntePubCentral, Long>, QueryDslPredicateExecutor<TcEntePubCentral> {

	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TcEntePubCentral> findAll();
	
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcEntePubCentral> S save(S entity);

}
