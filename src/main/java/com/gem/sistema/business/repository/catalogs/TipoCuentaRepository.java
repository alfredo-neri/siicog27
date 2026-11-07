package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.TipoCuenta;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Interface TipoCuentaRepository.
 */
@Repository
public interface TipoCuentaRepository extends PagingAndSortingRepository<TipoCuenta, Long>, QueryDslPredicateExecutor<TipoCuenta> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TipoCuenta> findAll();
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.querydsl.QueryDslPredicateExecutor#findAll()
	 */        
	List<TipoCuenta> findAll(Predicate prdct);

	/**
	 * Find by id tipo cuenta.
	 *
	 * @param idTipoCuenta the id tipo cuenta
	 * @return the list
	 */
	List<TipoCuenta> findByIdTipoCuenta(Integer idTipoCuenta);

	/**
	 * Find by S tipo.
	 *
	 * @param sTipo the s tipo
	 * @return the list
	 */
	List<TipoCuenta> findBySTipo(Integer sTipo);

	/**
	 * Find by nombre.
	 *
	 * @param nombre the nombre
	 * @return the list
	 */
	List<TipoCuenta> findByNombre(String nombre);

	/**
	 * Find by id tipo cuenta and S tipo.
	 *
	 * @param idTipoCuenta the id tipo cuenta
	 * @param sTipo the s tipo
	 * @return the list
	 */
	List<TipoCuenta> findByIdTipoCuentaAndSTipo(Integer idTipoCuenta, Integer sTipo);

	/**
	 * Find by id tipo cuenta and nombre.
	 *
	 * @param idTipoCuenta the id tipo cuenta
	 * @param nombre the nombre
	 * @return the list
	 */
	List<TipoCuenta> findByIdTipoCuentaAndNombre(Integer idTipoCuenta, String nombre);

	/**
	 * Find by nombre and S tipo.
	 *
	 * @param nombre the nombre
	 * @param sTipo the s tipo
	 * @return the list
	 */
	List<TipoCuenta> findByNombreAndSTipo(String nombre, Integer sTipo);                

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TipoCuenta> S save(S entity);	
}
