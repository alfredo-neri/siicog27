package com.gem.sistema.business.repository.catalogs;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Mir;;

// TODO: Auto-generated Javadoc
/**
 * The Interface MirRepository.
 *
 * @author 
 */
@Repository("mirRepository")
public interface MirRepository extends PagingAndSortingRepository<Mir, Long>, QueryDslPredicateExecutor<Mir>{
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Mir> findAll();
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Mir> S save(S entity);
	
	/**
	 * Find by codigo.
	 *
	 * @param codigo the codigo
	 * @return the list
	 */
	List<Mir> findByCodigo(String codigo);
	
	
	/*
	 * */
	
	/**
	 * Find by nivel.
	 *
	 * @param nivel the nivel
	 * @return the list
	 */
	List<Mir> findByNivel(String nivel);
	
	/**
	 * Checks if is validate cve id in F tecnica.
	 *
	 * @param codigo the codigo
	 * @return the integer
	 */
	@Query("select count(1) from Ftecnicasm ft where ft.cveind = :codigo")
	Integer isValidateCveIdInFTecnica(@Param("codigo") String codigo);
	
	/**
	 * Checks if is validate cve id in F componente.
	 *
	 * @param codigo the codigo
	 * @return the integer
	 */
	@Query("select count(1) from Componente co where co.cveind = :codigo")
	Integer isValidateCveIdInFComponente(@Param("codigo")  String codigo);
	
	/**
	 * Checks if is validate cve id in finalidad.
	 *
	 * @param codigo the codigo
	 * @return the integer
	 */
	@Query("select count(1) from Finalidad fi where fi.cveind = :codigo")
	Integer isValidateCveIdInFinalidad(@Param("codigo") String codigo);
	
	/**
	 * Checks if is validate cve id in proposito.
	 *
	 * @param codigo the codigo
	 * @return the integer
	 */
	@Query("select count(1) from Proposito pr where pr.cveind = :codigo")
	Integer isValidateCveIdInProposito(@Param("codigo") String codigo);
	
	/**
	 * Checks if is validate cve id in actividad.
	 *
	 * @param codigo the codigo
	 * @return the integer
	 */
	@Query("select count(1) from Actividad pr where pr.cveind = :codigo")
	Integer isValidateCveIdInActividad(@Param("codigo") String codigo);
	
}
