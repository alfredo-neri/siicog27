package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm1511;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm1511Repository.
 */
@Repository(value = "pm1511Repository")
public interface Pm1511Repository extends PagingAndSortingRepository<Pm1511, Long>, QueryDslPredicateExecutor<Pm1511> {
	
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm1511> findAll();

	/**
	 * Find all by idsector.
	 *
	 * @param idSector the id sector
	 * @param sort the sort
	 * @return the list
	 */
	List<Pm1511> findAllByIdsector(Integer idSector, Sort sort);

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm1511> S save(S entity);

	/**
	 * Find all by idsector order by semestral asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm1511> findAllByIdsectorOrderBySemestralAsc(Integer idSector);

	/**
	 * Count by semestre.
	 *
	 * @param idSector the id sector
	 * @param semestre the semestre
	 * @return the integer
	 */
	@Query("select count(1) from Pm1511 where idsector =:idSector and semestral =:semestre")
	Integer countBySemestre(@Param("idSector") Integer idSector, @Param("semestre") Integer semestre);

	/**
	 * Find all bysemestral and idsector.
	 *
	 * @param semestre the semestre
	 * @param idSector the id sector
	 * @return the pm 1511
	 */
	Pm1511 findAllBysemestralAndIdsector(Integer semestre, Integer idSector);

	/**
	 * Count.
	 *
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select count(1) from Pm1511 where idsector =:idSector")
	Integer count(@Param("idSector") Integer idSector);
	
	/**
	 * Sum acunccms.
	 *
	 * @param idSector the id sector
	 * @param semestre the semestre
	 * @return the integer
	 */
	@Query("select sum(nccms) from Pm1511 where idsector =:idSector and semestral <=:semestre")
	Integer sumAcunccms(@Param("idSector") Integer idSector, @Param("semestre") Integer semestre);
	
	
	/**
	 * Sum acunccm.
	 *
	 * @param idSector the id sector
	 * @param semestre the semestre
	 * @return the integer
	 */
	@Query("select sum(nccm) from Pm1511 where idsector =:idSector and semestral <=:semestre")
	Integer sumAcunccm(@Param("idSector") Integer idSector, @Param("semestre") Integer semestre);
	
	/**
	 * Sum acutcpd.
	 *
	 * @param idSector the id sector
	 * @param semestre the semestre
	 * @return the integer
	 */
	@Query("select sum(tcpd) from Pm1511 where idsector =:idSector and semestral <=:semestre")
	Integer sumAcutcpd(@Param("idSector") Integer idSector, @Param("semestre") Integer semestre);
	
	
	/**
	 * Sum accms.
	 *
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select sum(acunccms) from Pm1511 where idsector =:idSector")
	Integer sumAccms(@Param("idSector")Integer idSector);
	
	/**
	 * Sum accm.
	 *
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select sum(acunccm) from Pm1511 where idsector =:idSector")
	Integer sumAccm(@Param("idSector")Integer idSector);
	
	/**
	 * Sum atcpd.
	 *
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select sum(acutcpd) from Pm1511 where idsector =:idSector")
	Integer sumAtcpd(@Param("idSector")Integer idSector);
	
	/**
	 * Sum nccms.
	 *
	 * @param idSector the id sector
	 * @param semestre the semestre
	 * @return the integer
	 */
	@Query("select sum(nccms) from Pm1511 where idsector =:idSector and semestral <=:semestre")
	Integer sumNccms(@Param("idSector")Integer idSector, @Param("semestre") Integer semestre);
	
	/**
	 * Sum nccm.
	 *
	 * @param idSector the id sector
	 * @param semestre the semestre
	 * @return the integer
	 */
	@Query("select sum(nccm) from Pm1511 where idsector =:idSector and semestral <=:semestre")
	Integer sumNccm(@Param("idSector")Integer idSector, @Param("semestre") Integer semestre);
	
	
	/**
	 * Sum tcpd.
	 *
	 * @param idSector the id sector
	 * @param semestre the semestre
	 * @return the integer
	 */
	@Query("select sum(tcpd) from Pm1511 where idsector =:idSector and semestral <=:semestre")
	Integer sumTcpd(@Param("idSector")Integer idSector, @Param("semestre") Integer semestre);

}
