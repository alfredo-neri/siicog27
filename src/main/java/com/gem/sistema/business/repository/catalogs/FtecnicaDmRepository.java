package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Ftecnicadm;

// TODO: Auto-generated Javadoc
/**
 * repositorio FtecnicaDmRepository.
 *
 * @version 1
 */
@Repository("ftecnicaDmRepository")
public interface FtecnicaDmRepository extends PagingAndSortingRepository<Ftecnicadm, Long>, QueryDslPredicateExecutor<Ftecnicadm>{
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	@ReadOnlyProperty
	List<Ftecnicadm> findAll();
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Ftecnicadm> S save(S entity);
	
	@Query("select ft from Ftecnicadm ft where idsector=:idSector order by clvdep asc")
	List<Ftecnicadm> findAllByIdSector(@Param("idSector") Integer idSector);
	
	Integer countByIdSectorAndCvetemas(Integer idSector, String cvetemas);
		
	//List<Ftecnicadm> findByCvetemasAndClvdepAndClvfunAndClvfinAndCveind();
}
