package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Predial;

// TODO: Auto-generated Javadoc
/**
 * The Interface PredialRepository.
 */
@Repository(value="predialRepository")
public interface PredialRepository
		extends PagingAndSortingRepository<Predial, Long>, QueryDslPredicateExecutor<Predial>{
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Predial> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Predial> S save(S entity);
	
	/**
	 * Find all by idsector order by mes asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Predial> findAllByIdsectorOrderByMesAsc(Integer idSector);
	
	/**
	 * Find by idsector and mes.
	 *
	 * @param idSector the id sector
	 * @param mes the mes
	 * @return the predial
	 */
	Predial findByIdsectorAndMes(Integer idSector, String mes);
	
	/**
	 * Find by id sector order by mes.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query("select p from Predial p where idsector =:idSector order by integer(mes) asc")
	List<Predial> findByIdSectorOrderByMes (@Param("idSector") Integer idSector);
}
