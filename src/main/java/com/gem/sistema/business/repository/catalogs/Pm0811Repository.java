package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm0811;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm0811Repository.
 */
@Repository(value = "pm0811Repository")
public interface Pm0811Repository extends PagingAndSortingRepository<Pm0811, Long>, QueryDslPredicateExecutor<Pm0811> {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm0811> findAll();

	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @param sort the sort
	 * @return the list
	 */
	List<Pm0811> findAllByIdsector(Integer idSector, Sort sort);

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm0811> S save(S entity);

	/**
	 * Find all by idsector order by semestral asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm0811> findAllByIdsectorOrderBySemestralAsc(Integer idSector);

	/**
	 * Count by semestre.
	 *
	 * @param idSector the id sector
	 * @param semestre the semestre
	 * @return the integer
	 */
	@Query("select count(1) from Pm0811 where idsector =:idSector and semestral =:semestre")
	Integer countBySemestre(@Param("idSector") Integer idSector, @Param("semestre") Integer semestre);

	/**
	 * Find all bysemestral and idsector.
	 *
	 * @param trimestre the trimestre
	 * @param idSector the id sector
	 * @return the pm 0811
	 */
	Pm0811 findAllBysemestralAndIdsector(Integer trimestre, Integer idSector);

	/**
	 * Count.
	 *
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select count(1) from Pm0811 where idsector =:idSector")
	Integer count(@Param("idSector") Integer idSector);

}
