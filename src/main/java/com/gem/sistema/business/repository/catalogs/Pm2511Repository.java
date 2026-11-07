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

import com.gem.sistema.business.domain.Pm2511;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm2511Repository.
 */
@Repository(value = "pm2511Repository")
public interface Pm2511Repository extends PagingAndSortingRepository<Pm2511, Long>, QueryDslPredicateExecutor<Pm2511> {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm2511> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm2511> S save(S entity);

	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @param sort the sort
	 * @return the list
	 */
	List<Pm2511> findAllByIdsector(Integer idSector, Sort sort);
	
	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm2511> findAllByIdsector(Integer idSector);

	/**
	 * Count by trimestre.
	 *
	 * @param idSector the id sector
	 * @param trimestre the trimestre
	 * @return the integer
	 */
	@Query("select count(1) from Pm2511 p where idsector = :idSector and trimestre = :trimestre")
	Integer countByTrimestre(@Param("idSector") Integer idSector, @Param("trimestre") Integer trimestre);
	
	/**
	 * Count.
	 *
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select count(1) from Pm2511 p where idsector = :idSector")
	Integer count(@Param("idSector") Integer idSector);
	
	
	/**
	 * Find all bytrimestre and idsector.
	 *
	 * @param trimestre the trimestre
	 * @param idSector the id sector
	 * @return the pm 2511
	 */
	Pm2511 findAllBytrimestreAndIdsector(Integer trimestre, Integer idSector);
	
	
	/**
	 * Sum acum by id sector.
	 *
	 * @param idSector the id sector
	 * @return the big decimal
	 */
	@Query("select sum(agua) from Pm2511 where idsector = :idSector")
	BigDecimal sumAcumByIdSector(@Param("idSector")Integer idSector);
}
