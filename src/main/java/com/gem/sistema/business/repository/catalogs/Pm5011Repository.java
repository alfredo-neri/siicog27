package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pm5011;

// TODO: Auto-generated Javadoc
/**
 * The Interface Pm5011Repository.
 */
@Repository(value = "pm5011Repository")
public interface Pm5011Repository extends PagingAndSortingRepository<Pm5011, Long>, QueryDslPredicateExecutor<Pm5011> {

	/* (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pm5011> findAll();
	
	/**
	 * Save.
	 *
	 * @param <S> the generic type
	 * @param entity the entity
	 * @return the s
	 */
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pm5011> S save(S entity);
	
	/**
	 * Exist month.
	 *
	 * @param mensual the mensual
	 * @param idSector the id sector
	 * @return the integer
	 */
	@Query("select count(1) from Pm5011 where mensual = :mensual and idsector =:idSector")
	Integer existMonth(@Param("mensual")Integer mensual, @Param("idSector")Integer idSector);
	
	/**
	 * Gets the month min.
	 *
	 * @param mensual the mensual
	 * @param idSector the id sector
	 * @return the month min
	 */
	@Query("select min(mensual) from Pm5011 where mensual = :mensual and idsector = :idSector")
	Integer getMonthMin(@Param("mensual")Integer mensual, @Param("idSector")Integer idSector);
	
	/**
	 * Gets the sum tcc.
	 *
	 * @param idSector the id sector
	 * @return the sum tcc
	 */
	@Query("select sum(tcc) from Pm5011 where idsector = :idSector")
	Integer getSumTcc( @Param("idSector")Integer idSector);
	
	/**
	 * Gets the by id sector and mensual.
	 *
	 * @param idSector the id sector
	 * @param mensual the mensual
	 * @return the by id sector and mensual
	 */
	@Query("select p from Pm5011 p where idsector =:idSector and mensual =:mensual")
	Pm5011 getByIdSectorAndMensual(@Param("idSector")Integer idSector,@Param("mensual") Integer mensual);
	
	/**
	 * Find by id sector.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query("select p from Pm5011 p where idsector =:idSector")
	List<Pm5011> findByIdSector (@Param("idSector") Integer idSector);
}
