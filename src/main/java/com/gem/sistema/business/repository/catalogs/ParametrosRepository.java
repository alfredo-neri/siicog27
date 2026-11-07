package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.TcParametro;
import com.gem.sistema.business.domain.TcValores;

// TODO: Auto-generated Javadoc
/**
 * The Interface ParametrosRepository.
 */
@Repository("parametrosRepository")
public interface ParametrosRepository
		extends PagingAndSortingRepository<TcParametro, String>, QueryDslPredicateExecutor<TcParametro> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TcParametro> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcParametro> S save(S entity);

	/**
	 * Gets the valor by cv.
	 *
	 * @param cveParametro the cve parametro
	 * @return the valor by cv
	 */
	@Query("select valor from TcParametro where cveParametro = :cveParametro")
	String getValorByCv(@Param("cveParametro") String cveParametro);

	
}
