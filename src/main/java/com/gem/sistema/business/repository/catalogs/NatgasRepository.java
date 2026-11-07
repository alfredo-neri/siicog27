package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Natgas;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Interface NatgasRepository.
 */
@Repository(value = "natgasRepository")
public interface NatgasRepository
		extends PagingAndSortingRepository<Natgas, Long>, QueryDslPredicateExecutor<Natgas>, NatgasRepositoryCustom {

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Natgas> findAll();

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Natgas> S save(S entity);

	/* (non-Javadoc)
	 * @see org.springframework.data.querydsl.QueryDslPredicateExecutor#findAll(com.mysema.query.types.Predicate)
	 */
	List<Natgas> findAll(Predicate prdct);

	/**
	 * Find by clvgas.
	 *
	 * @param clvgas the clvgas
	 * @return the list
	 */
	List<Natgas> findByClvgas(String clvgas);

	/**
	 * Find by clvgas not like.
	 *
	 * @param clvgas the clvgas
	 * @return the list
	 */
	List<Natgas> findByClvgasNotLike(String clvgas);

	/**
	 * Find by clvgas not like and idsector.
	 *
	 * @param clvgas the clvgas
	 * @param idsector the idsector
	 * @return the list
	 */
	List<Natgas> findByClvgasNotLikeAndIdsector(String clvgas, Integer idsector);

	/**
	 * Find by clvgas and idsector.
	 *
	 * @param clvgas the clvgas
	 * @param idsector the idsector
	 * @return the list
	 */
	List<Natgas> findByClvgasAndIdsector(String clvgas, Integer idsector);

	/**
	 * Find by idsector.
	 *
	 * @param idsector the idsector
	 * @return the list
	 */
	List<Natgas> findByIdsector(Integer idsector);

	/**
	 * Find by nomgas.
	 *
	 * @param nomgas the nomgas
	 * @return the list
	 */
	List<Natgas> findByNomgas(String nomgas);

	/**
	 * Find by clvgas and nomgas.
	 *
	 * @param clvgas the clvgas
	 * @param nomgas the nomgas
	 * @return the list
	 */
	List<Natgas> findByClvgasAndNomgas(String clvgas, String nomgas);

	/**
	 * Find first by clvgas and indcap and idsector.
	 *
	 * @param clvgas the clvgas
	 * @param indcap the indcap
	 * @param idSector the id sector
	 * @return the natgas
	 */
	Natgas findFirstByClvgasAndIndcapAndIdsector(String clvgas, String indcap, Integer idSector);

	/**
	 * Find first by clvgas and idsector.
	 *
	 * @param clvgas the clvgas
	 * @param idSector the id sector
	 * @return the natgas
	 */
	Natgas findFirstByClvgasAndIdsector(String clvgas, Integer idSector);

	/**
	 * Find all by idsector order by clvgas.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	@ReadOnlyProperty
	List<Natgas> findAllByIdsectorOrderByClvgas(Integer idSector);

	/**
	 * Find all which not contains ceros.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query("Select n from Natgas n where substring(n.clvgas,3,2) <> '00' and idsector = :idSector order by clvgas asc")
	List<Natgas> findAllWhichNotContainsCeros(@Param("idSector") Integer idSector);

	/**
	 * Find all which not ends with ceros.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query("Select n from Natgas n where clvgas not like '%0' and idsector = :idSector order by clvgas asc")
	List<Natgas> findAllWhichNotEndsWithCeros(@Param("idSector") Integer idSector);

}
