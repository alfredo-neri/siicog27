/**
 * 
 */
package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.TcMenuItem;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcMenuItemRepository.
 *
 * @author gauss
 */
@Repository
public interface TcMenuItemRepository extends PagingAndSortingRepository<TcMenuItem, Long>, CrudRepository<TcMenuItem, Long>, QuerydslPredicateExecutor<TcMenuItem> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TcMenuItem> findAll();
	
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcMenuItem> S save(S entity);
	
}
