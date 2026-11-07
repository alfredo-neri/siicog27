package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Catflu;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Interface CatfluRepository.
 */
@Repository(value  ="catfluRepository")
public interface CatfluRepository extends PagingAndSortingRepository<Catflu, Long>, QueryDslPredicateExecutor<Catflu>{

	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Catflu> findAll();
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Catflu> S save(S entity);	
	
	/* (non-Javadoc)
	 * @see org.springframework.data.querydsl.QueryDslPredicateExecutor#findAll(com.mysema.query.types.Predicate)
	 */
	List<Catflu> findAll(Predicate prdct);
	
	/**
	 * Find by clvflu.
	 *
	 * @param clvflu the clvflu
	 * @return the list
	 */
	List<Catflu> findByClvflu(Double clvflu);
	
	/**
	 * Find by clvflu and idsector.
	 *
	 * @param clvflu the clvflu
	 * @param idsector the idsector
	 * @return the list
	 */
	List<Catflu> findByClvfluAndIdsector(Double clvflu, Integer idsector);
	
	/**
	 * Find by gruflu.
	 *
	 * @param gruflu the gruflu
	 * @return the list
	 */
	List<Catflu> findByGruflu(String gruflu);
	
	/**
	 * Find by nomflu.
	 *
	 * @param nomflu the nomflu
	 * @return the list
	 */
	List<Catflu> findByNomflu(String nomflu);
	
	/**
	 * Find by nomflu and gruflu.
	 *
	 * @param nomflu the nomflu
	 * @param gruflu the gruflu
	 * @return the list
	 */
	List<Catflu> findByNomfluAndGruflu(String nomflu, String gruflu);
	
	/**
	 * Find all by order by clvflu.
	 *
	 * @return the list
	 */
	List<Catflu> findAllByOrderByClvflu();
	
	/**
	 * Find by idsector.
	 *
	 * @param idsector the idsector
	 * @return the list
	 */
	List<Catflu> findByIdsector(Integer idsector);
	
	/**
	 * Gets the by clvflu and nomflu.
	 *
	 * @param clvflustr the clvflustr
	 * @param nomflu the nomflu
	 * @param idSector the id sector
	 * @return the by clvflu and nomflu
	 */
	@Query(value="SELECT FLU.* FROM CATFLU FLU WHERE FLU.CLVFLU LIKE :clvflustr AND LOWER(FLU.NOMFLU) LIKE LOWER(:nomflu) AND FLU.IDSECTOR = :idSector ORDER BY FLU.CLVFLU", nativeQuery=true)
	List<Catflu> getByClvfluAndNomflu(@Param("clvflustr") String clvflustr,@Param("nomflu") String nomflu,  @Param("idSector") Integer idSector);
	
	/**
	 * Find by idsector and sguflu.
	 *
	 * @param idsector the idsector
	 * @param sguflu the sguflu
	 * @return the list
	 */
	List<Catflu> findByIdsectorAndSguflu(Integer idsector, String sguflu);
}
