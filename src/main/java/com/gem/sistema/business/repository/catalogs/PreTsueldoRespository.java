package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.PreTsueldo;
import com.gem.sistema.business.domain.PreTsueldoPK;

// TODO: Auto-generated Javadoc
/**
 * The Interface PreTsueldoRespository.
 */
@Repository
public interface PreTsueldoRespository
		extends PagingAndSortingRepository<PreTsueldo, PreTsueldoPK>, QueryDslPredicateExecutor<PreTsueldo> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<PreTsueldo> findAll();


}
