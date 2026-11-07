package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Cpd;;

// TODO: Auto-generated Javadoc
/**
 * The Interface CpdRepository.
 *
 * @author 
 */
@Repository
public interface CpdRepository extends PagingAndSortingRepository<Cpd, Long>, CrudRepository<Cpd, Long>, QuerydslPredicateExecutor<Cpd> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Cpd> findAll();
	
	
	List<Cpd> findByOrderByCvetemasAsc();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Cpd> S save(S entity);

	/**
	 * Find by pe and nope and tema.
	 *
	 * @param pe the pe
	 * @param nope the nope
	 * @param tema the tema
	 * @return the list
	 */
	List<Cpd> findByPeAndNopeAndTemaAndSubTema(String pe, String nope, String tema, String subtema);

	/**
	 * Find by cvetemas.
	 *
	 * @param tema the tema
	 * @return the list
	 */
	List<Cpd> findByCvetemas(String tema);
	
	Integer countByCvetemasStartingWith(String cvetemas);
	
	@Query(value = "SELECT * FROM CPD WHERE SUBSTR(CVETEMAS,1,6) = :cvetema AND SUB_TEMA <> ''", nativeQuery = true)
	List<Cpd> getByTema(@Param("cvetema") String cvetema);

	@Query(value = "SELECT * FROM CPD WHERE CVETEMAS = :cvetema ", nativeQuery = true)
	Cpd findOneByCvetemas(@Param("cvetema") String cvetema);
}
