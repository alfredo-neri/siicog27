package com.gem.sistema.business.repository.catalogs;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Catben;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Interface CatbenRepository.
 *
 * @author 
 */
@Repository
public interface CatbenRepository extends PagingAndSortingRepository<Catben, Long>, QueryDslPredicateExecutor<Catben> {
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Catben> findAll();   
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Catben> S save(S entity);
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.data.querydsl.QueryDslPredicateExecutor#findAll(com.mysema.query.types.Predicate)
	 */
	List<Catben> findAll(Predicate prdct);
    
	/**
	 * Find by clvben.
	 *
	 * @param clvben the clvben
	 * @return the list
	 */
    List<Catben> findByClvben(BigDecimal clvben);
    
    /**
     * Find by nomben.
     *
     * @param nomben the nomben
     * @return the list
     */
    List<Catben> findByNomben(String nomben);
    
    /**
     * Find by clvben and nomben.
     *
     * @param clvben the clvben
     * @param nomben the nomben
     * @return the list
     */
    List<Catben> findByClvbenAndNomben(BigDecimal clvben, String nomben);
    
    /**
     * Find all by order by clvben.
     *
     * @return the list
     */
    List<Catben> findAllByOrderByClvben();
        	
	
}
