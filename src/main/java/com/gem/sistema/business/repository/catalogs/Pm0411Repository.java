package com.gem.sistema.business.repository.catalogs;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm0411;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm0411Repository.
 */
@Repository(value = "pm0411Repository")
public interface Pm0411Repository extends PagingAndSortingRepository<Pm0411, Long>, QueryDslPredicateExecutor<Pm0411> {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm0411> findAll();
	
	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @param sort the sort
	 * @return the list
	 */
	List<Pm0411> findAllByIdsector(Integer idSector, Sort sort);

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm0411> S save(S entity);

	/**
	 * Find all by idsector order by trimestre asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm0411> findAllByIdsectorOrderByTrimestreAsc(Integer idSector);
	
	/**
	 * Count by trimestre.
	 *
	 * @param idSector the id sector
	 * @param trimestre the trimestre
	 * @return the integer
	 */
	@Query("select count(1) from Pm0411 where idsector =:idSector and trimestre =:trimestre")
	Integer countByTrimestre(@Param("idSector")Integer idSector, @Param("trimestre") Integer trimestre);
	
	
	/**
	 * Find all bytrimestre and idsector.
	 *
	 * @param trimestre the trimestre
	 * @param idSector the id sector
	 * @return the pm 0411
	 */
	Pm0411 findAllBytrimestreAndIdsector(Integer trimestre, Integer idSector);
	
	/**
	 * Count.
	 *
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select count(1) from Pm0411 where idsector =:idSector")
	Integer count(@Param("idSector")Integer idSector);
	
	/**
	 * Sum acum di.
	 *
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select sum(di) from Pm0411 where idsector =:idSector")
	Integer sumAcumDi(@Param("idSector")Integer idSector);
	
	
	/**
	 * Sum acum dc.
	 *
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select sum(dc) from Pm0411 where idsector =:idSector")
	Integer sumAcumDc(@Param("idSector")Integer idSector);
	

}
