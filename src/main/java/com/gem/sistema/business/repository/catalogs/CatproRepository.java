package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Catpro;
import com.querydsl.core.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Interface CatproRepository.
 */
@Repository
public interface CatproRepository extends PagingAndSortingRepository<Catpro, Long>, CrudRepository<Catpro, Long>, QuerydslPredicateExecutor<Catpro>{
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Catpro> findAll();  
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Catpro> S save(S entity);
	
	/* (non-Javadoc)
	 * @see org.springframework.data.querydsl.QuerydslPredicateExecutor#findAll(com.querydsl.core.types.Predicate)
	 */
	List<Catpro> findAll(Predicate prdct);
    
    /**
     * Find by clvpro.
     *
     * @param clvpro the clvpro
     * @return the list
     */
    List<Catpro> findByClvpro(String clvpro);
    
    /**
     * Find by nompro.
     *
     * @param nompro the nompro
     * @return the list
     */
    List<Catpro> findByNompro(String nompro);
    
    /**
     * Find by clvpro and nompro.
     *
     * @param clvpro the clvpro
     * @param nompro the nompro
     * @return the list
     */
    List<Catpro> findByClvproAndNompro(String clvpro, String nompro);

}
