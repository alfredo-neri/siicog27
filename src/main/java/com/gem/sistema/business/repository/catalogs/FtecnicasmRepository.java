package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Ftecnicasm;


// TODO: Auto-generated Javadoc
/**
 * The Interface FtecnicasmRepository.
 */
@Repository
public interface FtecnicasmRepository  extends PagingAndSortingRepository<Ftecnicasm, Long>, QueryDslPredicateExecutor<Ftecnicasm> {
    
    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#findAll()
     */
    List<Ftecnicasm> findAll();
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Ftecnicasm> S save(S entity);
	
	/**
	 * Find by cvetemas and clvdep and clvfun and cveind.
	 *
	 * @param cvetemas the cvetemas
	 * @param clvdep the clvdep
	 * @param clvfun the clvfun
	 * @param cveind the cveind
	 * @return the list
	 */
	List<Ftecnicasm> findByCvetemasAndClvdepAndClvfunAndCveind(String cvetemas, String clvdep, String clvfun, String cveind);
	
	/**
	 * Find by idsector.
	 *
	 * @param idsector the idsector
	 * @return the list
	 */
	List<Ftecnicasm> findByIdsectorOrderByCvetemasAscClvdepAscClvfunAscCveindAsc(Integer idsector);
	
	List<Ftecnicasm> findByIdsectorOrderByClvdepAscClvfunAsc(Integer idsector);
	
	Integer countByIdsectorAndCvetemas(Integer idsector, String cvetemas);
}
