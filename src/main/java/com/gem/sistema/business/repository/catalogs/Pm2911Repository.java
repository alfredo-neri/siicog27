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

import com.gem.sistema.business.domain.Pm2911;


// TODO: Auto-generated Javadoc
/**
 * The Interface Pm2911Repository.
 */
@Repository(value = "pm2911Repository")
public interface Pm2911Repository extends PagingAndSortingRepository<Pm2911, Long>, QueryDslPredicateExecutor<Pm2911> {
	
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm2911> findAll();
	
	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @param sort the sort
	 * @return the list
	 */
	List<Pm2911> findAllByIdsector(Integer idSector, Sort sort);

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm2911> S save(S entity);

	/**
	 * Find all by idsector order by semes asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm2911> findAllByIdsectorOrderBySemesAsc(Integer idSector);
	
	/**
	 * Count by semestre.
	 *
	 * @param idSector the id sector
	 * @param semestre the semestre
	 * @return the integer
	 */
	@Query("select count(1) from Pm2911 where idsector =:idSector and semes =:semestre")
	Integer countBySemestre(@Param("idSector")Integer idSector, @Param("semestre") Integer semestre);
	
	
	/**
	 * Find all bysemes and idsector.
	 *
	 * @param trimestre the trimestre
	 * @param idSector the id sector
	 * @return the pm 2911
	 */
	Pm2911 findAllBysemesAndIdsector(Integer trimestre, Integer idSector);
	
	/**
	 * Count.
	 *
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select count(1) from Pm2911 where idsector =:idSector")
	Integer count(@Param("idSector")Integer idSector);
	
	/**
	 * Sum acu gast.
	 *
	 * @param idSector the id sector
	 * @return the big decimal
	 */
//	@Query("select sum(di) from Pm2911 where idsector =:idSector")
//	Integer sumAcumDi(@Param("idSector")Integer idSector);
	/**
	 * @param idSector
	 * @return
	 */
	@Query("select sum(gastot) from Pm2911 where idsector =:idSector")
	BigDecimal sumAcuGast(@Param("idSector")Integer idSector);
	
	/**
	 * Sum acu tot.
	 *
	 * @param idSector the id sector
	 * @return the big decimal
	 */
	@Query("select sum(totton) from Pm2911 where idsector =:idSector")
	BigDecimal sumAcuTot(@Param("idSector")Integer idSector);

}
