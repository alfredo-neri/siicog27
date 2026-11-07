package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Ftecnicadd;

// TODO: Auto-generated Javadoc
/**
 * The Interface FtecnicaDdRepository.
 *
 * @author Sam repositorio FtecnicaDdRepository
 */
@Repository("ftecnicaDdRepository")
public interface FtecnicaDdRepository
		extends PagingAndSortingRepository<Ftecnicadd, Long>, QueryDslPredicateExecutor<Ftecnicadd> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	@ReadOnlyProperty
	List<Ftecnicadd> findAll();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Ftecnicadd> S save(S entity);

	/**
	 * Find by cvetemas and clvdep and clvfun and clvfin and cveind.
	 *
	 * @param Cvetemas the cvetemas
	 * @param Clvdep the clvdep
	 * @param clvfun the clvfun
	 * @param clvfin the clvfin
	 * @param cveind the cveind
	 * @return the list
	 */

	List<Ftecnicadd> findByCvetemasAndClvdepAndClvfunAndClvfinAndCveindAndIdsector(String Cvetemas, String Clvdep, String clvfun,
			String clvfin, String cveind, Integer idsector);

	/**
	 * Find by F tecnia.
	 *
	 * @param clvDep the clv dep
	 * @param clvFun the clv fun
	 * @param cveInd the cve ind
	 * @param codigo the codigo
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query("select f from Ftecnicadd f where clvdep = :clvDep and clvfun =:clvFun and cveind =:cveInd and codigo =:codigo and idsector =:idSector ")
	List<Ftecnicadd> findByFTecnia(@Param("clvDep") String clvDep, @Param("clvFun") String clvFun,
			@Param("cveInd") String cveInd, @Param("codigo") Integer codigo, @Param("idSector") Integer idSector);

	Integer countByNumvar(Integer numvar);
}
