package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Indicadores;
import com.mysema.query.types.Predicate;;

// TODO: Auto-generated Javadoc
/**
 * The Interface IndicadoresRepository.
 *
 * @author 
 */
@Repository
public interface IndicadoresRepository extends PagingAndSortingRepository<Indicadores, Long>, QueryDslPredicateExecutor<Indicadores>{
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Indicadores> findAll();
	
	/**
	 * Find all by order by cveind asc.
	 *
	 * @param predicate the predicate
	 * @return the list
	 */
	List<Indicadores> findAllByOrderByCveindAsc(Predicate predicate);
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Indicadores> S save(S entity);
	
	/**
	 * Find by cveind and idsector.
	 *
	 * @param cveind the cveind
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Indicadores> findByCveindAndIdsector(Integer cveind, Integer idSector);
}
