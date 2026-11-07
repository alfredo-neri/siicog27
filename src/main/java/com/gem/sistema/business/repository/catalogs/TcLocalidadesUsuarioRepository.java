package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.TcLocalidadesUsuario;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcLocalidadesUsuarioRepository.
 */
public interface TcLocalidadesUsuarioRepository
		extends PagingAndSortingRepository<TcLocalidadesUsuario, Long>, CrudRepository<TcLocalidadesUsuario, Long>, QuerydslPredicateExecutor<TcLocalidadesUsuario> {

	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TcLocalidadesUsuario> findAll();
	
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcLocalidadesUsuario> S save(S entity);
}
