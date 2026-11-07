package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.Proposito;

// TODO: Auto-generated Javadoc
/**
 * The Interface PropositoRepository.
 */
@Repository(value = "propositoRepository")
public interface PropositoRepository
		extends PagingAndSortingRepository<Proposito, Long>, QueryDslPredicateExecutor<Proposito> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Proposito> findAll();

	/**
	 * Find by clvdepg and cveprog and cvetemas.
	 *
	 * @param clvdepg  the clvdepg
	 * @param cveprog  the cveprog
	 * @param cvetemas the cvetemas
	 * @return the list
	 */
	List<Proposito> findByClvdepgAndCveprogAndCvetemas(String clvdepg, String cveprog, String cvetemas);

}
