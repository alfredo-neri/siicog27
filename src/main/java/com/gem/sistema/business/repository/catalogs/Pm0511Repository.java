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

import com.gem.sistema.business.domain.Pm0511;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm0511Repository.
 *
 * @author Mateo
 */
@Repository(value = "pm0511Repository")
public interface Pm0511Repository extends PagingAndSortingRepository<Pm0511, Long>, QueryDslPredicateExecutor<Pm0511> {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm0511> findAll();

	/**
	 * Find all by idsector.
	 *
	 * @param idSector
	 *            the id sector
	 * @param sort
	 *            the sort
	 * @return the list
	 */
	@Query(value = "select p.* from Pm0511 p where idsector = :idSector order by trimestre, cast(conse as INTEGER) asc", nativeQuery = true)
	List<Pm0511> findByIdsector(@Param("idSector") Integer idSector);

	@Query(value = "select p.* from Pm0511 p where idsector = :idSector and trimestre = :trimestre order by trimestre, cast(conse as INTEGER) asc", nativeQuery = true)
	List<Pm0511> findByTrimestreAndIdsector(@Param("idSector") Integer idSector, @Param("trimestre") Integer trimestre);

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm0511> S save(S entity);

	/**
	 * Find all by idsector order by trimestre asc.
	 *
	 * @param idSector
	 *            the id sector
	 * @return the list
	 */
	List<Pm0511> findAllByIdsectorOrderByTrimestreAsc(Integer idSector);

	/**
	 * Count by trimestre.
	 *
	 * @param idSector
	 *            the id sector
	 * @param trimestre
	 *            the trimestre
	 * @param conse
	 *            the conse
	 * @return the integer
	 */
	@Query("select count(1) from Pm0511 where idsector =:idSector and trimestre =:trimestre and conse =:conse ")
	Integer countByTrimestre(@Param("idSector") Integer idSector, @Param("trimestre") Integer trimestre,
			@Param("conse") String conse);

	/**
	 * Find all bytrimestre and idsector.
	 *
	 * @param trimestre
	 *            the trimestre
	 * @param idSector
	 *            the id sector
	 * @return the pm 0511
	 */
	Pm0511 findAllBytrimestreAndIdsector(Integer trimestre, Integer idSector);

	/**
	 * Find byid sectordistinc.
	 *
	 * @param idSector
	 *            the id sector
	 * @return the list
	 */
	@Query("select distinct trimestre from Pm0511 where idsector =:idSector ")
	List<Integer> findByidSectordistinc(@Param("idSector") Integer idSector);

	@Transactional(timeout = 10)
	@Modifying(clearAutomatically = true)
	@Query("update #{#entityName} u set u.conse = :conse, u.trimestre = :trimestre, u.emergencia = :emergencia, u.tiempo = :tiempo, u.observaciones = :observaciones  where u.id = :id")
	int update(@Param("conse") String conse, @Param("trimestre") Integer trimestre,
			@Param("emergencia") String emergencia, @Param("tiempo") Integer tiempo,
			@Param("observaciones") String observaciones, @Param("id") Long id);

}
