package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.ContraRecibo;
import com.gem.sistema.business.domain.TcFacturaContraRecibo;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcFacturaContraReciboRepository.
 */
@Repository(value = "tcFacturaContraReciboRepository")
public interface TcFacturasContraReciboRepository extends PagingAndSortingRepository<TcFacturaContraRecibo, Long>,
		QueryDslPredicateExecutor<TcFacturaContraRecibo> {

	List<TcFacturaContraRecibo> findAllByContraRecibo(ContraRecibo contraRecibo);
}
