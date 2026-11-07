package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Catdaa;

// TODO: Auto-generated Javadoc
/**
 * The Interface CatdaaRepository.
 */
@Repository(value = "catdaaRepository")
public interface CatdaaRepository extends PagingAndSortingRepository<Catdaa, Long>, QueryDslPredicateExecutor<Catdaa> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	@ReadOnlyProperty
	List<Catdaa> findAll();

	/**
	 * Método para ordenar por clave.
	 *
	 * @return the list
	 */
	List<Catdaa> findAllByOrderByClave();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Catdaa> S save(S entity);

	/**
	 * Find by clave.
	 *
	 * @param clave the clave
	 * @return the list
	 */
	List<Catdaa> findByClave(String clave);

	/**
	 * Gets the cvl pro.
	 *
	 * @return the cvl pro
	 */
	@Query("select distinct substr(x.clvpro, 4, 3) from Xcatpro x, Catdaa c where substr(x.clvpro, 4, 3) = c.clave and x.sectorid = 1 ")
	List<String> getCvlPro();

	/**
	 * Gets the by clave.
	 *
	 * @param clave the clave
	 * @return the by clave
	 */
	@Query("select c from Catdaa c where exists (select 1 from Xcatpro x where x.clvfin = c.clave and substr(x.clvpro, 4, 3) = :clave and x.sectorid = 1) ")
	List<Catdaa> getByClave(@Param("clave") String clave);

	@Query("select c from Catdaa c, Catdep d where c.clave = substring(d.clvdep,4,3) and substring(d.clvdep,1,3) = :clave and d.idsector = :sector")
	List<Catdaa> getByCatdgm(@Param("clave") String clave, @Param("sector") Integer sector);

	@Query(value = "SELECT DISTINCT A.* FROM CATDAA A INNER JOIN XCATPRO X ON A.CLAVE = SUBSTR(X.CLVDEP,4,3) WHERE  SUBSTR(X.CLVFUN,1,8) = :programa AND X.SECTORID = :sector ", nativeQuery = true)
	List<Catdaa> findDependenciasByProgramaAndSector(@Param("programa") String programa,
			@Param("sector") Integer sector);
}
