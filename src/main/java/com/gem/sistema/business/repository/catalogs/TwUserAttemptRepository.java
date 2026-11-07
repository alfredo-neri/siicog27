/**
 * 
 */
package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.TwUserAttempt;

// TODO: Auto-generated Javadoc
/**
 * The Interface TwUserAttemptRepository.
 *
 * @author gauss
 */
@Repository
public interface TwUserAttemptRepository extends PagingAndSortingRepository<TwUserAttempt, Long>, QueryDslPredicateExecutor<TwUserAttempt> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TwUserAttempt> findAll();
	
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TwUserAttempt> S save(S entity);
	
	/**
	 * Find by usuario.
	 *
	 * @param <S> the generic type
	 * @param usuario the usuario
	 * @return the s
	 */
	<S extends TwUserAttempt> S findByUsuario( String usuario);
	
}
