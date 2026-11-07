package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm5511;



// TODO: Auto-generated Javadoc
/**
 * The Interface Pm5511Repository.
 */
@Repository(value = "pm5511Repository")
public interface Pm5511Repository extends PagingAndSortingRepository<Pm5511, Long>, QueryDslPredicateExecutor<Pm5511> {
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm5511> findAll();
	
	/**
	 * Find all byidsector.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Pm5511> findAllByidsector(Integer idSector);
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm5511> S save(S entity);
	
	/**
	 * Find by month.
	 *
	 * @param idSector the id sector
	 * @param mensual the mensual
	 * @return the integer
	 */
	@Query("select count(1) from Pm5511 where idsector =:idSector and mensual =:mensual ")
	Integer findByMonth(@Param("idSector")Integer idSector, @Param("mensual")Integer mensual);
	
	/**
	 * Gets the by id sector and mensual.
	 *
	 * @param idSector the id sector
	 * @param mensual the mensual
	 * @return the by id sector and mensual
	 */
	@Query("select p from Pm5511 p where idsector = :idSector and mensual = :mensual")
	Pm5511 getByIdSectorAndMensual(@Param("idSector")Integer idSector, @Param("mensual")Integer mensual);

}
