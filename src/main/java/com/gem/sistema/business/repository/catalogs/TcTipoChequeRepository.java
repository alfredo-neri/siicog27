package com.gem.sistema.business.repository.catalogs;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gem.sistema.business.domain.TcTipoCheque;

public interface TcTipoChequeRepository
		extends PagingAndSortingRepository<TcTipoCheque, Long>, CrudRepository<TcTipoCheque, Long>, QuerydslPredicateExecutor<TcTipoCheque> {

}
