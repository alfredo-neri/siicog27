/**
 * 
 */
package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.TcMenu;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcMenuRepository.
 *
 * @author gauss
 */
@Repository
public interface TcMenuRepository extends PagingAndSortingRepository<TcMenu, Long>, QueryDslPredicateExecutor<TcMenu> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TcMenu> findAll();
	
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcMenu> S save(S entity);
	
}
