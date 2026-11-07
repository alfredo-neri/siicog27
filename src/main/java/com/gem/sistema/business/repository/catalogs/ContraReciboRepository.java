package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.ContraRecibo;

/**
 * The Interface ContraReciboRepository.
 */
@Repository(value = "contraReciboRepository")
public interface ContraReciboRepository
		extends PagingAndSortingRepository<ContraRecibo, Long>, CrudRepository<ContraRecibo, Long>, QuerydslPredicateExecutor<ContraRecibo> {

	List<ContraRecibo> findAll();

	List<ContraRecibo> findAllByOrderByFolio();

	ContraRecibo findOneByFolio(Integer folio);

	long count();
}
