package com.gem.sistema.business.repository.catalogs;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gem.sistema.business.domain.TwMetasIngreso;

public interface TwMetasIngresoRepository
		extends PagingAndSortingRepository<TwMetasIngreso, Long>, QueryDslPredicateExecutor<TwMetasIngreso> {
	
	

}
