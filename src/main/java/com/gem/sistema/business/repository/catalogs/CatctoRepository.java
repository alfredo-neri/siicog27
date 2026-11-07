package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Catcto;;

// TODO: Auto-generated Javadoc
/**
 * The Interface CatctoRepository.
 */
@Repository
public interface CatctoRepository extends PagingAndSortingRepository<Catcto, Long>, QueryDslPredicateExecutor<Catcto>{
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Catcto> findAll();
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Catcto> S save(S entity);	
}
