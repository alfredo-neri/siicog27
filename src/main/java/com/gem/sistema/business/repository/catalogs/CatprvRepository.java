package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Catprv;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Interface CatprvRepository.
 */
@Repository
public interface CatprvRepository extends PagingAndSortingRepository<Catprv, Long>, QueryDslPredicateExecutor<Catprv> {
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Catprv> findAll();
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Catprv> S save(S entity);
	
	/* (non-Javadoc)
	 * @see org.springframework.data.querydsl.QueryDslPredicateExecutor#findAll(com.mysema.query.types.Predicate)
	 */
	List<Catprv> findAll(Predicate prdct);
	
	/**
	 * Find all by order by clvprv desc.
	 *
	 * @return the list
	 */
	List<Catprv> findAllByOrderByClvprvDesc();
    
    /**
     * Find by clvprv.
     *
     * @param clvprv the clvprv
     * @return the list
     */
    List<Catprv> findByClvprv(Integer clvprv);
    
    /**
     * Find by clvprv and idsector.
     *
     * @param clvprv the clvprv
     * @param idsector the idsector
     * @return the list
     */
    List<Catprv> findByClvprvAndIdsector(Integer clvprv, Integer idsector);
    
    /**
     * Find by nomprv.
     *
     * @param nomprv the nomprv
     * @return the list
     */
    List<Catprv> findByNomprv(String nomprv);
    
    /**
     * Find by tipprv.
     *
     * @param tipprv the tipprv
     * @return the list
     */
    List<Catprv> findByTipprv(String tipprv);
    
    /**
     * Find by clvprv and nomprv.
     *
     * @param clvprv the clvprv
     * @param nomprv the nomprv
     * @return the list
     */
    List<Catprv> findByClvprvAndNomprv(Integer clvprv, String nomprv);
    
    /**
     * Find by clvprv and tipprv.
     *
     * @param clvprv the clvprv
     * @param tipprv the tipprv
     * @return the list
     */
    List<Catprv> findByClvprvAndTipprv(Integer clvprv, String tipprv);
    
    /**
     * Find by nomprv and tipprv.
     *
     * @param nomprv the nomprv
     * @param tipprv the tipprv
     * @return the list
     */
    List<Catprv> findByNomprvAndTipprv(String nomprv, String tipprv);
    
    /**
     * Find by clvprv and nomprv and tipprv.
     *
     * @param clvprv the clvprv
     * @param nomprv the nomprv
     * @param tipprv the tipprv
     * @return the list
     */
    List<Catprv> findByClvprvAndNomprvAndTipprv(Integer clvprv, String nomprv, String tipprv);
    
    /**
     * Find all by order by clvprv.
     *
     * @return the list
     */
    List<Catprv> findAllByOrderByClvprv();
    
    /**
     * Find by idsector order by clvprv.
     *
     * @param idsector the idsector
     * @return the list
     */
    List<Catprv> findByIdsectorOrderByClvprv(Integer idsector);
    
}
