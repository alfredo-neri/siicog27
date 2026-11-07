package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.gem.sistema.business.domain.TcMes;

// TODO: Auto-generated Javadoc
/**
 * The Interface TcMesRepository.
 */
@Repository(value= "tcMesRepository")
public interface TcMesRepository extends PagingAndSortingRepository<TcMes, Long>, QueryDslPredicateExecutor<TcMes> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<TcMes> findAll();
	
	/**
	 * Find by id less than equal.
	 *
	 * @param id the id
	 * @return the list
	 */
	List<TcMes> findByIdLessThanEqual(Long id);
	
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends TcMes> S save(S entity);
	
	/**
	 * Gets the mes activo.
	 *
	 * @param mes the mes
	 * @return the mes activo
	 */
	@Query("select m from TcMes m where mes <= lpad(:mes, 2, '0')")
	List<TcMes> getMesActivo(@Param("mes")String mes);
	
	/**
	 * Find by mes.
	 *
	 * @param mes the mes
	 * @return the tc mes
	 */
	TcMes findByMes(String mes);
	
	
	
}
