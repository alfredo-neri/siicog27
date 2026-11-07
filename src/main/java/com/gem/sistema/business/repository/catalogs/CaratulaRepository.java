package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Caratula;

// TODO: Auto-generated Javadoc
/**
 * The Interface CaratulaRepository.
 *
 * @author gauss.
 */
@Repository("caratulaRepository")
public interface CaratulaRepository extends PagingAndSortingRepository<Caratula, Long>, QueryDslPredicateExecutor<Caratula> {
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Caratula> findAll();
	
	
	/**
	 * Find all by order by cuenta.
	 *
	 * @return the list
	 */
	@Query(value = "SELECT * FROM INGRESOT WHERE SECTORID =:sector ORDER BY CUENTA, SCTA ASC", nativeQuery = true)
	List<Caratula> findAllByOrderByCuenta(@Param("sector") Integer sector);
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Caratula> S save(S entity);

	@Query(value = "SELECT * FROM INGRESOT WHERE CUENTA =:cuenta AND SUBSTR(SCTA,7) =:scta AND SECTORID =:sector ", nativeQuery = true)
	List<Caratula> findCuentaSctaSector(@Param("cuenta") String cuenta, @Param("scta") String scta, @Param("sector") Integer sector);
}
