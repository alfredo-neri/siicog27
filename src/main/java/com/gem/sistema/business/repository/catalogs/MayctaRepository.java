package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Maycta;
import com.mysema.query.types.Predicate;;

// TODO: Auto-generated Javadoc
/**
 * The Interface MayctaRepository.
 *
 * @author 
 */
@Repository(value = "mayctaRepository")
public interface MayctaRepository extends PagingAndSortingRepository<Maycta, Long>, QueryDslPredicateExecutor<Maycta>{

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Maycta> findAll();

	/**
	 * Find all by order by cuenta.
	 *
	 * @param predicate the predicate
	 * @return the list
	 */
	List<Maycta> findAllByOrderByCuenta(Predicate predicate);


	/**
	 * Find by natcta or natcta order by cuenta.
	 *
	 * @param deudora the deudora
	 * @param acredora the acredora
	 * @return the list
	 */
	List<Maycta> findByNatctaOrNatctaOrderByCuenta(String deudora, String acredora);

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Maycta> S save(S entity);

	/**
	 * Find by cuenta.
	 *
	 * @param cuenta the cuenta
	 * @return the list
	 */
	List<Maycta> findByCuenta(String cuenta);
	
	/**
	 * Find first by cuenta.
	 *
	 * @param cuenta the cuenta
	 * @return the maycta
	 */
	Maycta findFirstByCuenta(String cuenta);

}
