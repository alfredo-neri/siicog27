package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm1011;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm1011Repository.
 */
@Repository(value = "pm1011Repository")
public interface Pm1011Repository extends PagingAndSortingRepository<Pm1011, Long>, QueryDslPredicateExecutor<Pm1011> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm1011> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm1011> S save(S entity);

	/**
	 * Find all by idsector.
	 *
	 * @param idSector
	 *            the id sector
	 * @return the pm 1011
	 */
	Pm1011 findAllByIdsector(Integer idSector);

	/**
	 * Count.
	 *
	 * @param idSector
	 *            the id sector
	 * @param anual
	 *            the anual
	 * @return the integer
	 */
	@Query("select count(1) from Pm1011 where idsector=:idSector and anual=:anual")
	Integer count(@Param("idSector") Integer idSector, @Param("anual") Integer anual);

	@Transactional(timeout = 10)
	@Modifying(clearAutomatically = true)
	@Query("update #{#entityName} u set u.ipm = :ipm, tdr =:tdr, obsipm= :obsipm, obstdr =:obstdr where u.id = :id")
	int updatePm1011(@Param("ipm") Integer ipm, @Param("tdr") Integer tdr, @Param("obsipm") String obsipm,
			@Param("obstdr") String obstdr, @Param("id") Long id);
}
