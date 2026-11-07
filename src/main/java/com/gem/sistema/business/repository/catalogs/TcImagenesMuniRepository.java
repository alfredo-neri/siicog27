package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.TcImagenesMuni;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcImagenesMuniRepository.
 */
@Repository("tcImagenesMuniRepository")
public interface TcImagenesMuniRepository
		extends PagingAndSortingRepository<TcImagenesMuni, Long>, QueryDslPredicateExecutor<TcImagenesMuni> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TcImagenesMuni> findAll();

	/**
	 * Find by clvmun.
	 *
	 * @param clvmun the clvmun
	 * @return the tc imagenes muni
	 */
	TcImagenesMuni findByClvmun(int clvmun);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcImagenesMuni> S save(S entity);

}
