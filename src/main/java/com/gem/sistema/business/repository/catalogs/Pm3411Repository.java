package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm3411;
// TODO: Auto-generated Javadoc

/**
 * The Interface Pm3411Repository.
 */
@Repository(value = "pm3411Repository")
public interface Pm3411Repository extends PagingAndSortingRepository<Pm3411, Long>, QueryDslPredicateExecutor<Pm3411> {
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm3411> findAll();
	
	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm3411> findAllByIdsector(Integer idSector);

	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @param sort the sort
	 * @return the list
	 */
	List<Pm3411> findAllByIdsector(Integer idSector, Sort sort);

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm3411> S save(S entity);

	/**
	 * Find all by idsector order by semestral asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm3411> findAllByIdsectorOrderBySemestralAsc(Integer idSector);

	/**
	 * Count by semestre.
	 *
	 * @param idSector the id sector
	 * @param semestre the semestre
	 * @return the integer
	 */
	@Query("select count(1) from Pm3411 where idsector =:idSector and semestral =:semestre")
	Integer countBySemestre(@Param("idSector") Integer idSector, @Param("semestre") Integer semestre);

	/**
	 * Find all bysemestral and idsector.
	 *
	 * @param semestre the semestre
	 * @param idSector the id sector
	 * @return the pm 3411
	 */
	Pm3411 findAllBysemestralAndIdsector(Integer semestre, Integer idSector);

	/**
	 * Count.
	 *
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select count(1) from Pm3411 where idsector =:idSector")
	Integer count(@Param("idSector") Integer idSector);
	
	/**
	 * Sum acum.
	 *
	 * @param idSector the id sector
	 * @param semestre the semestre
	 * @return the integer
	 */
	@Query("select sum(spcam) from Pm3411 where idsector =:idSector and semestral <:semestre")
	Integer sumAcum(@Param("idSector") Integer idSector, @Param("semestre") Integer semestre);
	
	/**
	 * Sum acum by sector.
	 *
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select sum(spcam) from Pm3411 where idsector =:idSector")
	Integer sumAcumBySector(@Param("idSector") Integer idSector);
}
