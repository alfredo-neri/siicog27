package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Copome;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Interface CopomeRepository.
 *
 * @author Demetrio Cruz
 */
@Repository("copomeRepository")
public interface CopomeRepository extends PagingAndSortingRepository<Copome, Long>, QueryDslPredicateExecutor<Copome> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Copome> findAll();

  /**
   * Find by tpcpme.
   *
   * @param tpcpme the tpcpme
   * @return the list
   */
  List<Copome> findByTpcpme(String tpcpme);

  /**
   * Find first by tpcpme and idsector order by id asc.
   *
   * @param tpcpme the tpcpme
   * @param idSector the id sector
   * @return the copome
   */
  Copome findFirstByTpcpmeAndIdsectorOrderByIdAsc(String tpcpme, Integer idSector);

	/**
	 * Find first by tpcpme and mecpme and anopme and idsector order by id asc.
	 *
	 * @param tpcpme the tpcpme
	 * @param mecpme the mecpme
	 * @param anopme the anopme
	 * @param idSector the id sector
	 * @return the copome
	 */
	Copome findFirstByTpcpmeAndMecpmeAndAnopmeAndIdsectorOrderByIdAsc(String tpcpme, Integer mecpme, Integer anopme, Integer idSector);

  /* (non-Javadoc)
   * @see org.springframework.data.repository.CrudRepository#save(S)
   */
  @Transactional(timeout = 10)
  <S extends Copome> S save(S entity);

}
