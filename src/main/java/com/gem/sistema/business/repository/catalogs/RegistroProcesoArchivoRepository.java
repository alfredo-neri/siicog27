package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;


import com.gem.sistema.business.domain.TcRegistraProcesoArchivo;

// TODO: Auto-generated Javadoc
/**
 * The Interface RegistroProcesoArchivoRepository.
 */
public interface RegistroProcesoArchivoRepository extends PagingAndSortingRepository<TcRegistraProcesoArchivo, Long>, QueryDslPredicateExecutor<TcRegistraProcesoArchivo>{
     
     /* (non-Javadoc)
      * @see org.springframework.data.repository.CrudRepository#findAll()
      */
     List<TcRegistraProcesoArchivo> findAll();
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcRegistraProcesoArchivo> S save(S entity);	

}
