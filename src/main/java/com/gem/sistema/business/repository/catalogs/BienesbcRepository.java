package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Bienesbc;

// TODO: Auto-generated Javadoc
/**
 * The Interface BienesbcRepository.
 */
@Repository(value="bienesbcRepository")
public interface BienesbcRepository
		extends PagingAndSortingRepository<Bienesbc, Long>, QueryDslPredicateExecutor<Bienesbc>{
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Bienesbc> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Bienesbc> S save(S entity);
	
	/**
	 * Find all by idsector order by mes asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Bienesbc> findAllByIdsectorOrderByMesAsc(Integer idSector);
	
	/**
	 * Find by idsector and mes and consec.
	 *
	 * @param idSector the id sector
	 * @param mes the mes
	 * @param consec the consec
	 * @return the bienesbc
	 */
	Bienesbc findByIdsectorAndMesAndConsec(Integer idSector, Integer mes, Integer consec);
	
	/**
	 * Find by idsector and mes.
	 *
	 * @param idSector the id sector
	 * @param mes the mes
	 * @return the list
	 */
	List<Bienesbc> findByIdsectorAndMes(Integer idSector, Integer mes);
}
