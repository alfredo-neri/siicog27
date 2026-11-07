package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Fuentef;
import com.gem.sistema.business.dto.CatdaaDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface FuentefRepository.
 *
 * @author 
 */
@Repository(value = "fuentefRepository")
public interface FuentefRepository
		extends PagingAndSortingRepository<Fuentef, Long>, QueryDslPredicateExecutor<Fuentef> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	@ReadOnlyProperty
	List<Fuentef> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Fuentef> S save(S entity);

	/**
	 * Find by clvfte and idsector.
	 *
	 * @param clvfte the clvfte
	 * @param idSector the id sector
	 * @return the list
	 */
	@ReadOnlyProperty
	List<Fuentef> findByClvfteAndIdsector(String clvfte, Integer idSector);

	/**
	 * Find by liga and idsector.
	 *
	 * @param liga the liga
	 * @param idSector the id sector
	 * @return the list
	 */
	@ReadOnlyProperty
	List<Fuentef> findByLigaAndIdsector(String liga, Integer idSector);
	
	

	/**
	 * Gets the by id sector.
	 *
	 * @param idSector the id sector
	 * @return the by id sector
	 */
	@Query("select f from Fuentef f where idsector =:idSector and liga <> '' and length(liga) > 2 order by f.liga asc")

	List<Fuentef> getByIdSector(@Param("idSector") Integer idSector);
	
	
	@Query("select f from Fuentef f where idsector =:idSector and substr(clvfte,1,2) <> '00' order by f.clvfte asc")
	List<Fuentef> getFuentesFull(@Param("idSector") Integer idSector);

	/**
	 * Gets the fuente by clave.
	 *
	 * @param clave the clave
	 * @return the fuente by clave
	 */
	@Query(value = "select distinct  x.clvfin || ' ' || f.nombref from fuentef f inner join xcatpro x on x.clvfin = f.liga and x.sectorid = f.idsector where substr( x.clvpro,4,3) =:clave and x.sectorid=1", nativeQuery = true)
	List<String> getFuenteByClave(@Param("clave") String clave);

}
