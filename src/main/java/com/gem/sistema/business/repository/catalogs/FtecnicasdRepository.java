package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Ftecnicasd;

// TODO: Auto-generated Javadoc
/**
 * The Interface FtecnicasdRepository.
 */
@Repository
public interface FtecnicasdRepository  extends PagingAndSortingRepository<Ftecnicasd, Long>, QueryDslPredicateExecutor<Ftecnicasd> {
    
    /* (non-Javadoc)
     * @see org.springframework.data.repository.CrudRepository#findAll()
     */
    List<Ftecnicasd> findAll();
	
	/* (non-Javadoc)
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Ftecnicasd> S save(S entity);
	
	/**
	 * Find by cvetemas and clvdep and clvfun and cveind.
	 *
	 * @param cvetemas the cvetemas
	 * @param clvdep the clvdep
	 * @param clvfun the clvfun
	 * @param cveind the cveind
	 * @return the list
	 */
	List<Ftecnicasd> findByCvetemasAndClvdepAndClvfunAndCveind(String cvetemas, String clvdep, String clvfun, String cveind);
	
	/**
	 * Find by cvetemas and clvdep and clvfun and cveind and codigo.
	 *
	 * @param cvetemas the cvetemas
	 * @param clvdep the clvdep
	 * @param clvfun the clvfun
	 * @param cveind the cveind
	 * @param codigo the codigo
	 * @return the list
	 */
	List<Ftecnicasd> findByCvetemasAndClvdepAndClvfunAndCveindAndCodigo(String cvetemas, String clvdep, String clvfun, String cveind, Integer codigo);
	
	Integer countByNumvar(Integer numvar);
}
