package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcTiposPago;

// TODO: Auto-generated Javadoc
@Repository(value = "tcTiposPagoRepository")
public interface TcTiposPagoRepository
		extends PagingAndSortingRepository<TcTiposPago, Long>, QueryDslPredicateExecutor<TcTiposPago> {
	List<TcTiposPago> findAll();

}
