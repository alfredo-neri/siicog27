package com.gem.sistema.business.repository.catalogs;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.gem.sistema.business.domain.TcEstatusCheque;

public interface EstatusChequeRepository
		extends PagingAndSortingRepository<TcEstatusCheque, Long>, CrudRepository<TcEstatusCheque, Long>, QuerydslPredicateExecutor<TcEstatusCheque> {

}
