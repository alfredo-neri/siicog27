package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Bienesm;

// TODO: Auto-generated Javadoc
/**
 * The Interface BienesmRepository.
 */
@Repository(value="bienesmRepository")
public interface BienesmRepository
		extends PagingAndSortingRepository<Bienesm, Long>, QueryDslPredicateExecutor<Bienesm>{
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Bienesm> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Bienesm> S save(S entity);
	
	/**
	 * Find all by idsector order by mes asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Bienesm> findAllByIdsectorOrderByMesAsc(Integer idSector);
	
	/**
	 * Find by idsector and mes and consec.
	 *
	 * @param idSector the id sector
	 * @param mes the mes
	 * @param consec the consec
	 * @return the bienesm
	 */
	Bienesm findByIdsectorAndMesAndConsec(Integer idSector, Integer mes, Integer consec);
	
	/**
	 * Find by idsector and mes.
	 *
	 * @param idSector the id sector
	 * @param mes the mes
	 * @return the list
	 */
	List<Bienesm> findByIdsectorAndMes(Integer idSector, Integer mes); 
}
