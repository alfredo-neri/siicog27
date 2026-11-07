package com.gem.sistema.business.repository.catalogs;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gem.sistema.business.domain.TwMetasIngreso;

public interface TwMetasIngresoRepository
		extends PagingAndSortingRepository<TwMetasIngreso, Long>, CrudRepository<TwMetasIngreso, Long>, QuerydslPredicateExecutor<TwMetasIngreso> {
	
	

}
