package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.FolioContrarecibo;

/**
 * The Interface FoliosContrareciboRepository.
 */
@Repository(value = "foliosContrareciboRepository")
public interface FoliosContrareciboRepository
		extends PagingAndSortingRepository<FolioContrarecibo, Long>, QueryDslPredicateExecutor<FolioContrarecibo> {

	List<FolioContrarecibo> findAll();

	FolioContrarecibo findTopByAnio(Integer anio);
}
