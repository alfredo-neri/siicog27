package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Bienesinm;

// TODO: Auto-generated Javadoc
/**
 * The Interface BienesinmRepository.
 */
@Repository(value="bienesinmRepository")
public interface BienesinmRepository
		extends PagingAndSortingRepository<Bienesinm, Long>, QueryDslPredicateExecutor<Bienesinm>{
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Bienesinm> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Bienesinm> S save(S entity);
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(java.lang.Iterable)
	 */
	<S extends Bienesinm> Iterable<S> save(Iterable<S> entities);
	
	/**
	 * Find all by idsector order by mes asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Bienesinm> findAllByIdsectorOrderByMesAsc(Integer idSector);
	
	/**
	 * Find by idsector and mes and consec.
	 *
	 * @param idSector the id sector
	 * @param mes the mes
	 * @param consec the consec
	 * @return the bienesinm
	 */
	Bienesinm findByIdsectorAndMesAndConsec(Integer idSector, Integer mes, Integer consec);
	
	/**
	 * Find by idsector and mes.
	 *
	 * @param idSector the id sector
	 * @param mes the mes
	 * @return the list
	 */
	List<Bienesinm> findByIdsectorAndMes(Integer idSector, Integer mes); 
}
