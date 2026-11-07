package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Localidades;;

// TODO: Auto-generated Javadoc
/**
 * The Interface LocalidadesRepository.
 */
@Repository("localidadesRepository")
public interface LocalidadesRepository extends PagingAndSortingRepository<Localidades, Long>, QueryDslPredicateExecutor<Localidades>{
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	@ReadOnlyProperty
	List<Localidades> findAll();
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Localidades> S save(S entity);
	
	/**
	 * Find all by order by cve mun asc.
	 *
	 * @return the list
	 */
	@ReadOnlyProperty
	List<Localidades> findAllByOrderByCveMunAsc();
	
	/**
	 * Find all by order by cve mun asc cve loc asc.
	 *
	 * @return the list
	 */
	@ReadOnlyProperty
	List<Localidades> findAllByOrderByCveMunAscCveLocAsc();
	
	/**
	 * Number by cveloc.
	 *
	 * @param localidad the localidad
	 * @return the integer
	 */
	@Query(value = "SELECT COUNT(1) FROM LOCALIDADES WHERE CVELOC=:localidad", nativeQuery = true)
	Integer numberByCveloc(@Param("localidad") Integer localidad);
}
