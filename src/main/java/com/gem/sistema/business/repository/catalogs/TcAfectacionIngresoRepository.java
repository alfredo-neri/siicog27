package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcAfectacionIngreso;

@Repository(value = "tcAfectacionIngresoRepository")
public interface TcAfectacionIngresoRepository
		extends PagingAndSortingRepository<TcAfectacionIngreso, Long>, QueryDslPredicateExecutor<TcAfectacionIngreso> {
	List<TcAfectacionIngreso> findAll();

	TcAfectacionIngreso getByMes(Integer mes);
}
