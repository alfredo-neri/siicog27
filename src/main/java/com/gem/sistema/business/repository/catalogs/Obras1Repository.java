package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Obras1;;

// TODO: Auto-generated Javadoc
/**
 * The Interface Obras1Repository.
 */
@Repository
public interface Obras1Repository extends PagingAndSortingRepository<Obras1, Long>, QueryDslPredicateExecutor<Obras1>{
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	@ReadOnlyProperty
	List<Obras1> findAll();
	
	/**
	 * Find all by order by clvcto asc.
	 *
	 * @return the list
	 */
	@ReadOnlyProperty
	List<Obras1> findAllByOrderByClvctoAsc();
	
	/**
	 * Find all by order by clvprv asc.
	 *
	 * @return the list
	 */
	@ReadOnlyProperty
	List<Obras1> findAllByOrderByClvprvAsc();
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Obras1> S save(S entity);
	
	/**
	 * Find by clvcto.
	 *
	 * @param clvcto the clvcto
	 * @return the list
	 */
	@ReadOnlyProperty
	List<Obras1> findByClvcto(String clvcto);
}
