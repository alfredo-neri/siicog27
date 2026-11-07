package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm3011;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm3011Repository.
 */
@Repository(value="pm3011Repository")
public interface Pm3011Repository extends PagingAndSortingRepository<Pm3011, Long>, QueryDslPredicateExecutor<Pm3011>{
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm3011> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm3011> S save(S entity);
	
}
