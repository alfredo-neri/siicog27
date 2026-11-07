/**
 * 
 */
package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.TcTipoUsuario;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcTipoUsuarioRepository.
 *
 * @author gauss
 */
public interface TcTipoUsuarioRepository extends PagingAndSortingRepository<TcTipoUsuario, Long>, QueryDslPredicateExecutor<TcTipoUsuario> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TcTipoUsuario> findAll();
	
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcTipoUsuario> S save(S entity);
}
