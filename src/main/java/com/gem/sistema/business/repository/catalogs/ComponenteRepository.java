package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.Componente;

// TODO: Auto-generated Javadoc
/**
 * The Interface ComponenteRepository.
 */
@Repository
public interface ComponenteRepository
		extends PagingAndSortingRepository<Componente, Long>, QueryDslPredicateExecutor<Componente> {

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Componente> findAll();

	/**
	 * Find by clvdepg and cveprog and cvetemas.
	 *
	 * @param clvdepg the clvdepg
	 * @param cveprog the cveprog
	 * @param cvetemas the cvetemas
	 * @return the list
	 */
	List<Componente> findByClvdepgAndCveprogAndCvetemas(String clvdepg, String cveprog, String cvetemas);
}
