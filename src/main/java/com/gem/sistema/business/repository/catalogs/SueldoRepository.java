package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Tsueldo;

// TODO: Auto-generated Javadoc
/**
 * The Interface SueldoRepository.
 */
@Repository
public interface SueldoRepository
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
	 * Find native all order by cvepuesto nompuesto.
	 *
	 * @return the list
	 */
	@Query(value = "select t.* from tsueldos t order by CVEPUESTO , NOMPUESTO ASC", nativeQuery = true)
	List<Tsueldo> findNativeAllOrderByCvepuestoNompuesto();

	/**
	 * Find native order by cvepuesto nompuesto.
	 *
	 * @param cvepuesto the cvepuesto
	 * @param nompuesto the nompuesto
	 * @return the list
	 */
	@Query(value = "select t.* from tsueldos t where t.CVEPUESTO like  :cvepuesto  and "
			+ "lower(t.NOMPUESTO) like lower( :nompuesto  ) "
			+ "order by CVEPUESTO, NOMPUESTO ASC", nativeQuery = true)
	List<Tsueldo> findNativeOrderByCvepuestoNompuesto(@Param("cvepuesto") String cvepuesto,
			@Param("nompuesto") String nompuesto);
}
