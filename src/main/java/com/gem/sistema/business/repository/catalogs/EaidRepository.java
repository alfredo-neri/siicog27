package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.Eaid;

/**
 * @author mateo
 *
 */
@Repository(value = "eaidRepository")
public interface EaidRepository extends PagingAndSortingRepository<Eaid, Long>, QueryDslPredicateExecutor<Eaid> {
	/**
	 * @param trimestre
	 * @param idSesctor
	 * @return
	 */
	List<Eaid> findByTrimestreAndIdSectorOrderByTrimestreAsc(Integer trimestre, Integer idSesctor);

	/**
	 * @param idSector
	 * @param trimestre
	 * @param consecutivo
	 * @return
	 */
	Eaid findByIdSectorAndTrimestreAndConsecutivo(Integer idSector, Integer trimestre, Integer consecutivo);
	
	/**
	 * @param IdSector
	 * @return
	 */
	List<Eaid> findByIdSectorOrderByTrimestreAscConsecutivoAsc(Integer IdSector);

}
