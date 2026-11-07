package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Poliza;
import com.querydsl.core.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Interface PolizaRepository.
 */
@Repository
public interface PolizaRepository extends PagingAndSortingRepository<Poliza, Long>, CrudRepository<Poliza, Long>, QuerydslPredicateExecutor<Poliza> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Poliza> findAll();
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.querydsl.QuerydslPredicateExecutor#findAll()
	 */        
	List<Poliza> findAll(Predicate prdct);


	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Poliza> S save(S entity);	
}
