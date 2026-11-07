package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Programamun;

// TODO: Auto-generated Javadoc
/**
 * The Interface ProgramamunRepository.
 */
@Repository("programamunRepository")
public interface ProgramamunRepository
		extends PagingAndSortingRepository<Programamun, String>, QueryDslPredicateExecutor<Programamun> {
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Programamun> findAll();

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Programamun> S save(S entity);

	/**
	 * Find first by cvedepg and cvedepa and programa and idsector.
	 *
	 * @param cvedepg the cvedepg
	 * @param cvedepa the cvedepa
	 * @param programa the programa
	 * @param idSector the id sector
	 * @return the programamun
	 */
	Programamun findFirstByCvedepgAndCvedepaAndProgramaAndIdsector(String cvedepg, String cvedepa, String programa,
			Integer idSector);
	
	/**
	 * Count programamun by idsector.
	 *
	 * @param sector the sector
	 * @return the integer
	 */
	@Query(value = "SELECT COUNT(1) FROM PROGRAMAMUN WHERE IDSECTOR=:sector", nativeQuery = true)
	Integer countProgramamunByIdsector(@Param("sector") Integer sector);
}
