/**
 * 
 */
package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Integradoe;

// TODO: Auto-generated Javadoc
/**
 * The Interface IntegradoeRepository.
 *
 * @author gauss
 */
@Repository
public interface IntegradoeRepository extends PagingAndSortingRepository<Integradoe, Long>, QueryDslPredicateExecutor<Integradoe> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Integradoe> findAll();
		
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Integradoe> S save(S entity);
	
	/**
	 * Find by partida.
	 *
	 * @param <S> the generic type
	 * @param partida the partida
	 * @return the s
	 */
	@Transactional(timeout = 10)
	<S extends Integradoe> S findByPartida( String partida);
	

}
