package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Agua;

// TODO: Auto-generated Javadoc
/**
 * The Interface AguaRepository.
 */
@Repository(value="aguaRepository")
public interface AguaRepository
		extends PagingAndSortingRepository<Agua, Long>, QueryDslPredicateExecutor<Agua>{
	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Agua> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Agua> S save(S entity);
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(java.lang.Iterable)
	 */
	<S extends Agua> Iterable<S> save(Iterable<S> entities);
	
	/**
	 * Find by idsector order by mes asc.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query(value = "select a.* from Agua a where idsector = :idSector order by cast(mes as int) asc", nativeQuery = true)
	List<Agua> findByIdsectorOrderByMesAsc(@Param("idSector")Integer idSector);
	
	/**
	 * Find by idsector and mes.
	 *
	 * @param idSector the id sector
	 * @param mes the mes
	 * @return the agua
	 */
	Agua findByIdsectorAndMes(Integer idSector, String mes);
} 
