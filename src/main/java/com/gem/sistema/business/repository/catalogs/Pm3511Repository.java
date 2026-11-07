package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm3511;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm3511Repository.
 */
@Repository("pm3511Repository")
public interface Pm3511Repository extends PagingAndSortingRepository<Pm3511, Long>, QueryDslPredicateExecutor<Pm3511> {
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm3511> findAll();
	
	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm3511> findAllByIdsector(Integer idSector);

	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @param sort the sort
	 * @return the list
	 */
	List<Pm3511> findAllByIdsector(Integer idSector, Sort sort);

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm3511> S save(S entity);

	/**
	 * Find all by idsector order by semes asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm3511> findAllByIdsectorOrderBySemesAsc(Integer idSector);

	/**
	 * Count by semestre.
	 *
	 * @param idSector the id sector
	 * @param semestre the semestre
	 * @return the integer
	 */
	@Query("select count(1) from Pm3511 where idsector =:idSector and semes =:semestre")
	Integer countBySemestre(@Param("idSector") Integer idSector, @Param("semestre") Integer semestre);

	/**
	 * Find all bysemes and idsector.
	 *
	 * @param semestre the semestre
	 * @param idSector the id sector
	 * @return the pm 3511
	 */
	Pm3511 findAllBysemesAndIdsector(Integer semestre, Integer idSector);

	/**
	 * Count.
	 *
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select count(1) from Pm3511 where idsector =:idSector")
	Integer count(@Param("idSector") Integer idSector);
}
