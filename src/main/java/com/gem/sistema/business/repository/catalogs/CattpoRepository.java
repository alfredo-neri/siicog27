package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Cattpo;


// TODO: Auto-generated Javadoc
/**
 * The Interface CattpoRepository.
 */
@Repository("cattpoRepository")
public interface CattpoRepository extends PagingAndSortingRepository<Cattpo, Long>, QueryDslPredicateExecutor<Cattpo>{
	
    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#findAll()
     */
    List<Cattpo> findAll();
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Cattpo> S save(S entity);
	
	Cattpo findByTippol(String tippol);

}
