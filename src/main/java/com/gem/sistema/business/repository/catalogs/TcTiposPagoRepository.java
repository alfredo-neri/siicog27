package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcTiposPago;

// TODO: Auto-generated Javadoc
@Repository(value = "tcTiposPagoRepository")
public interface TcTiposPagoRepository
		extends PagingAndSortingRepository<TcTiposPago, Long>, CrudRepository<TcTiposPago, Long>, QuerydslPredicateExecutor<TcTiposPago> {
	List<TcTiposPago> findAll();

}
