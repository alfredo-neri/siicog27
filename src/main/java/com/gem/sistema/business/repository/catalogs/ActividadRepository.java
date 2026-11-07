package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.Actividad;

// TODO: Auto-generated Javadoc
/**
 * The Interface ActividadRepository.
 */
@Repository(value = "actividadRepository")
public interface ActividadRepository
		extends PagingAndSortingRepository<Actividad, Long>, QueryDslPredicateExecutor<Actividad> {

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Actividad> findAll();

	/**
	 * Find by clvdepg and cveprog and cvetemas and cvecom.
	 *
	 * @param clvdepg the clvdepg
	 * @param cveprog the cveprog
	 * @param cvetemas the cvetemas
	 * @param cvecom the cvecom
	 * @return the list
	 */
	List<Actividad> findByClvdepgAndCveprogAndCvetemasAndCvecom(String clvdepg, String cveprog, String cvetemas, Integer cvecom);


}
