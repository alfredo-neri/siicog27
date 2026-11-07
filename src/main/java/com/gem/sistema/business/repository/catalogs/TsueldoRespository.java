package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Tsueldo;

// TODO: Auto-generated Javadoc
/**
 * The Interface TsueldoRespository.
 */
public interface TsueldoRespository
		extends PagingAndSortingRepository<Tsueldo, Long>, QueryDslPredicateExecutor<Tsueldo> {

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Tsueldo> findAll();

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Tsueldo> S save(S entity);

	/**
	 * Truncate.
	 */
	@Transactional(timeout = 60)
	@Modifying
	@Query(value = " DELETE FROM GEMUSER.TSUELDOS ", nativeQuery = true)
	void truncate();

}
