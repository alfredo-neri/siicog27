/**
 * 
 */
package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.TcRole;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcRoleRepository.
 *
 * @author gauss
 */
@Repository
public interface TcRoleRepository extends PagingAndSortingRepository<TcRole, Long>, QueryDslPredicateExecutor<TcRole> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TcRole> findAll();
	
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcRole> S save(S entity);
	
	
	
}
