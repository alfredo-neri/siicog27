package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.Finalidad;

// TODO: Auto-generated Javadoc
/**
 * The Interface FinalidadRepository.
 */
@Repository(value = "finalidadRepository")
public interface FinalidadRepository extends PagingAndSortingRepository<Finalidad, Long>, QueryDslPredicateExecutor<Finalidad> {

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Finalidad> findAll();
	
	/**
	 * Find by clvdepg and cveprog and cvetemas.
	 *
	 * @param clvdepg the clvdepg
	 * @param cveprog the cveprog
	 * @param cvetemas the cvetemas
	 * @return the list
	 */
	List<Finalidad> findByClvdepgAndCveprogAndCvetemas(String clvdepg, String cveprog, String cvetemas);

}
