package com.gem.sistema.business.repository.catalogs;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gem.sistema.business.domain.TcTipoCheque;

public interface TipoChequeRepository extends PagingAndSortingRepository<TcTipoCheque, Long>, QueryDslPredicateExecutor<TcTipoCheque>{

}
