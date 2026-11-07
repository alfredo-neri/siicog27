package com.gem.sistema.business.repository.catalogs;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcRetencione;

@Repository
public interface TcRetencionesRepository extends PagingAndSortingRepository<TcRetencione, Long>,
		QueryDslPredicateExecutor<TcRetencione> {

}
