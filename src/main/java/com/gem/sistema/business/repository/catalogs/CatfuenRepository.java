package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Catfuen;
// TODO: Auto-generated Javadoc

/**
 * The Interface CatfuenRepository.
 */
@Repository(value ="catfuenRepository")
public interface CatfuenRepository
		extends PagingAndSortingRepository<Catfuen, Long>, CrudRepository<Catfuen, Long>, QuerydslPredicateExecutor<Catfuen> {
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Catfuen> findAll();

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Catfuen> S save(S entity);

}
