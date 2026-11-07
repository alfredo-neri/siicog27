package com.gem.sistema.business.repository.catalogs;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gem.sistema.business.domain.TcEstatusCheque;

public interface EstatusChequeRepository
		extends PagingAndSortingRepository<TcEstatusCheque, Long>, QueryDslPredicateExecutor<TcEstatusCheque> {

}
