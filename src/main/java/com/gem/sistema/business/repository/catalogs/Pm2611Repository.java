package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm2611;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm2611Repository.
 */
@Repository(value = "pm2611Repository")
public interface Pm2611Repository extends PagingAndSortingRepository<Pm2611, Long>, QueryDslPredicateExecutor<Pm2611> {
	
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm2611> findAll();
	
	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @param sort the sort
	 * @return the list
	 */
	List<Pm2611> findAllByIdsector(Integer idSector, Sort sort);

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm2611> S save(S entity);

	/**
	 * Find all by idsector order by semes asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm2611> findAllByIdsectorOrderBySemesAsc(Integer idSector);
	
	/**
	 * Count by trimestre.
	 *
	 * @param idSector the id sector
	 * @param semestre the semestre
	 * @return the integer
	 */
	@Query("select count(1) from Pm2611 where idsector =:idSector and semes =:semestre")
	Integer countByTrimestre(@Param("idSector")Integer idSector, @Param("semestre") Integer semestre);
	
	
	/**
	 * Find all bysemes and idsector.
	 *
	 * @param trimestre the trimestre
	 * @param idSector the id sector
	 * @return the pm 2611
	 */
	Pm2611 findAllBysemesAndIdsector(Integer trimestre, Integer idSector);
	
	/**
	 * Count.
	 *
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select count(1) from Pm2611 where idsector =:idSector")
	Integer count(@Param("idSector")Integer idSector);
	
	/**
	 * Sum.
	 *
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select sum(voltot) from Pm2611 where idsector =:idSector")
	Integer sum(@Param("idSector")Integer idSector);
	
	

}
