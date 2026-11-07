package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Pasot;

// TODO: Auto-generated Javadoc
/**
 * The Interface PasotRepository.
 *
 * @author gauss.
 */
@Repository("pasotRepository")
public interface PasotRepository extends PagingAndSortingRepository<Pasot, Long>, QueryDslPredicateExecutor<Pasot> {
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Pasot> findAll();


	/**
	 * Find all by order by partida.
	 *
	 * @return the list
	 */
	List<Pasot> findAllByOrderByPartida();

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Pasot> S save(S entity);

	/**
	 * Find first by partida and idsector.
	 *
	 * @param partida the partida
	 * @param idsector the idsector
	 * @return the pasot
	 */
	Pasot findFirstByPartidaAndIdsector(String partida, Integer idsector);






}
