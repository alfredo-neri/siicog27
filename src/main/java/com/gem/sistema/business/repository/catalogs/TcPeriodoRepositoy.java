package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcPeriodo;

@Repository(value = "tcPeriodoRepositoy")
public interface TcPeriodoRepositoy
		extends PagingAndSortingRepository<TcPeriodo, Long>, CrudRepository<TcPeriodo, Long>, QuerydslPredicateExecutor<TcPeriodo> {
	
	List<TcPeriodo> findByTipoPeriodo(Integer tipoPeriodo);
	
	List<TcPeriodo> findByTipoPeriodoOrderByPeriodo(Integer tipoPeriodo);
	
	List<TcPeriodo> findByTipoPeriodoAndPeriodoLessThanEqualOrderByPeriodo(Integer tipoPeriodo, Integer periodo);
	
	TcPeriodo findByTipoPeriodoAndPeriodo(Integer tipoPeriodo, Integer periodo);

}
