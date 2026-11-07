package com.gem.sistema.business.repository.catalogs;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcRetencione;

@Repository
public interface TcRetencionesRepository extends PagingAndSortingRepository<TcRetencione, Long>, CrudRepository<TcRetencione, Long>,
		QuerydslPredicateExecutor<TcRetencione> {

}
