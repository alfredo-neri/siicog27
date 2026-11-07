package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.gem.sistema.business.domain.TcCuentasAdicionales;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcCuentasAdicionalesRepository.
 */
@Repository(value = "tcCuentasAdicionalesRepository")
public interface TcCuentasAdicionalesRepository extends PagingAndSortingRepository<TcCuentasAdicionales, Long>, QueryDslPredicateExecutor<TcCuentasAdicionales>{
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TcCuentasAdicionales> findAll();

	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<TcCuentasAdicionales> findAllByIdsector(Integer idSector);
	
	/**
	 * Find by cuenta.
	 *
	 * @param cuenta the cuenta
	 * @param cuentaAdicional the cuenta adicional
	 * @param idSector the id sector
	 * @return the list
	 */
	List<TcCuentasAdicionales> findByCuenta(String cuenta, String cuentaAdicional, Integer idSector);

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcCuentasAdicionales> S save(S entity);

}
