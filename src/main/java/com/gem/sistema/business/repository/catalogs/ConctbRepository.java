package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Conctb;

// TODO: Auto-generated Javadoc
/**
 * The Interface ConctbRepository.
 */
@Repository("conctbRepository")
public interface ConctbRepository extends PagingAndSortingRepository<Conctb, Long>, QueryDslPredicateExecutor<Conctb> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Conctb> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Conctb> S save(S entity);

	/**
	 * Find first by order by id asc.
	 *
	 * @return the conctb
	 */
	Conctb findFirstByOrderByIdAsc();

	/**
	 * Find first by idsector order by id asc.
	 *
	 * @param idSector
	 *            the id sector
	 * @return the conctb
	 */
	Conctb findFirstByIdsectorOrderByIdAsc(Integer idSector);

	/**
	 * Find first by idsector order by id desc.
	 *
	 * @param idSector
	 *            the id sector
	 * @return the conctb
	 */
	Conctb findFirstByIdsectorOrderByIdDesc(Integer idSector);

	/**
	 * Find by idsector.
	 *
	 * @param idSector
	 *            the id sector
	 * @return the conctb
	 */
	Conctb findByIdsector(Integer idSector);

	/**
	 * Find all by idsector and id ref.
	 *
	 * @param idSector
	 *            the id sector
	 * @param idRef
	 *            the id ref
	 * @return the list
	 */
	List<Conctb> findAllByIdsectorAndIdRef(int idSector, long idRef);

	/**
	 * Gets the mes.
	 *
	 * @param idSector
	 *            the id sector
	 * @return the mes
	 */
	@Query("select co.mesemp from Conctb co where idsector = :idsector")
	List<Integer> getMes(@Param("idsector") Integer idSector);

	/**
	 * Find by clvemp and idsector.
	 *
	 * @param clvemp
	 *            the clvemp
	 * @param idsector
	 *            the idsector
	 * @return the conctb
	 */
	Conctb findByClvempAndIdsector(String clvemp, Integer idsector);

	/**
	 * @param clave
	 * @param idsector
	 * @return
	 */
	@Transactional(timeout = 10)
	@Modifying(clearAutomatically = true)
	@Query("update #{#entityName} cb set cb.clave = :clave where cb.idsector = :idsector")
	int updateClave(@Param("clave") String clave, @Param("idsector") Integer idsector);
	
	/**
	 * @param idSector
	 * @return
	 */
	Conctb findByIdsectorAndIdRef(Integer idSector, long idRef);

}
