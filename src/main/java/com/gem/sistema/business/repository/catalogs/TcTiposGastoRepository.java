package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.TcTipoGasto;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcTipoGastoRepository.
 */
@Repository(value = "tcTiposGastoRepository")
public interface TcTiposGastoRepository
		extends PagingAndSortingRepository<TcTipoGasto, Long>, QueryDslPredicateExecutor<TcTipoGasto> {

	List<TcTipoGasto> findAll();

}
