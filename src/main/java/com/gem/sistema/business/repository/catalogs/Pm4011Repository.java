package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm4011;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm4011Repository.
 */
@Repository(value = "pm4011Repository")
public interface Pm4011Repository extends PagingAndSortingRepository<Pm4011, Long>, QueryDslPredicateExecutor<Pm4011> {
	/*
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm4011> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm4011> S save(S entity);

	/**
	 * Find all by idsector.
	 *
	 * @param idSector
	 *            the id sector
	 * @param sort
	 *            the sort
	 * @return the list
	 */
	List<Pm4011> findAllByIdsector(Integer idSector, Sort sort);

	/**
	 * Find all by idsector.
	 *
	 * @param idSector
	 *            the id sector
	 * @return the list
	 */
	List<Pm4011> findAllByIdsector(Integer idSector);

	@Transactional(timeout = 10)
	@Modifying(clearAutomatically = true)
	@Query("update #{#entityName} u set u.obs = :obs, u.clvreq = :clvreq, correo =:correo, cumple=:cumple,evidencia =:evidencia, requer=:requer where u.id = :id")
	int update(@Param("obs") String obs, @Param("clvreq") Integer clvreq, @Param("correo") String correo,
			@Param("cumple") String cumple, @Param("evidencia") String evidencia, @Param("requer") String requer,
			@Param("id") Long id);

}
