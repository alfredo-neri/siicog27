package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Muninep;

// TODO: Auto-generated Javadoc
/**
 * The Interface MuniNepRepository.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@Repository("muniNepRepository")
public interface MuniNepRepository
		extends PagingAndSortingRepository<Muninep, Long>, CrudRepository<Muninep, Long>, QuerydslPredicateExecutor<Muninep> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	@ReadOnlyProperty
	List<Muninep> findAll();

	/**
	 * Find all by idsector order by campo 7.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	@ReadOnlyProperty
	List<Muninep> findAllByIdsectorOrderByCampo7(Integer idSector);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Muninep> S save(S entity);

	/**
	 * Find by campo 8.
	 *
	 * @param campo8 the campo 8
	 * @return the list
	 */
	@ReadOnlyProperty
	List<Muninep> findByCampo8(String campo8);

	/**
	 * Find by campo 7.
	 *
	 * @param campo7 the campo 7
	 * @return the list
	 */
	List<Muninep> findByCampo7(String campo7);

	/**
	 * Find by campo 7 and idsector.
	 *
	 * @param campo7   the campo 7
	 * @param idsector the idsector
	 * @return the list
	 */
	List<Muninep> findByCampo7AndIdsector(String campo7, Integer idsector);

	/**
	 * Find first by campo 7 and idsector.
	 *
	 * @param campo7   the campo 7
	 * @param idsector the idsector
	 * @return the muninep
	 */
	Muninep findFirstByCampo7AndIdsector(String campo7, Integer idsector);

	/**
	 * Find by campo 0 and campo 1 and campo 2 and campo 3.
	 *
	 * @param campo0 the campo 0
	 * @param campo1 the campo 1
	 * @param campo2 the campo 2
	 * @param campo3 the campo 3
	 * @return the list
	 */
	@ReadOnlyProperty
	List<Muninep> findByCampo0AndCampo1AndCampo2AndCampo3(String campo0, String campo1, String campo2, String campo3);

	/**
	 * Find by campo 0 and campo 1 and campo 2 and campo 3 and campo 4.
	 *
	 * @param campo0 the campo 0
	 * @param campo1 the campo 1
	 * @param campo2 the campo 2
	 * @param campo3 the campo 3
	 * @param campo4 the campo 4
	 * @return the list
	 */
	@ReadOnlyProperty
	List<Muninep> findByCampo0AndCampo1AndCampo2AndCampo3AndCampo4(String campo0, String campo1, String campo2,
			String campo3, String campo4);

	@ReadOnlyProperty
	List<Muninep> findByCampo0AndCampo1AndCampo2AndCampo3AndCampo4AndIdsector(String campo0, String campo1,
																					   String campo2, String campo3, String campo4, Integer idsector);

	/**
	 * Find by campo 0 and campo 1 and campo 2 and campo 3 and campo 4 and campo 5.
	 *
	 * @param campo0 the campo 0
	 * @param campo1 the campo 1
	 * @param campo2 the campo 2
	 * @param campo3 the campo 3
	 * @param campo4 the campo 4
	 * @param campo5 the campo 5
	 * @return the list
	 */
	@ReadOnlyProperty
	List<Muninep> findByCampo0AndCampo1AndCampo2AndCampo3AndCampo4AndCampo5AndIdsector(String campo0, String campo1,
			String campo2, String campo3, String campo4, String campo5, Integer idsector);

	/**
	 * Find by idsector and campo 1.
	 *
	 * @param idsector the idsector
	 * @param campo1   the campo 1
	 * @return the list
	 */
	List<Muninep> findByIdsectorAndCampo1(Integer idsector, String campo1);

	/**
	 * Gets the by idsector.
	 *
	 * @param idsector the idsector
	 * @return the by idsector
	 */
	@Query("select m from Muninep  m where campo4=''  and idsector=:idsector and length(campo7)=6")
	List<Muninep> getByIdsector(@Param("idsector") Integer idsector);

	@Query("select m from Muninep m where length(campo7) = 4 and idsector=:idsector order by campo7 asc")
	List<Muninep> getListFuncion(@Param("idsector") Integer idsector);

	@Query("select m from Muninep m where length(campo7) = 6 and idsector=:idsector order by campo7 asc")
	List<Muninep> getListSubFuncion(@Param("idsector") Integer idsector);

	@Query("select m from Muninep m where length(campo7) = 8 and idsector=:idsector order by campo7 asc")
	List<Muninep> getListPrograma(@Param("idsector") Integer idsector);

	@Query("select m from Muninep m where length(campo7) = 10 and idsector=:idsector order by campo7 asc")
	List<Muninep> getListSubPrograma(@Param("idsector") Integer idsector);

	@Query("select m from Muninep m where length(campo7) = 12 and idsector=:idsector order by campo7 asc")
	List<Muninep> getListSubProyecto(@Param("idsector") Integer idsector);

	@Query(value = "SELECT M.*  FROM MUNINEP M INNER JOIN XCATPRO X ON M.IDSECTOR = X.SECTORID AND M.CAMPO7 = X.CLVFUN "
			+ "WHERE M.IDSECTOR = :sector AND SUBSTR (X.CLVFUN,1 ,8) = :clvfun AND SUBSTR (X.CLVDEP, 1 ,3) = :clvdep ", nativeQuery = true)
	List<Muninep> getMuninepsforClvdepAndClvfunAndSector(@Param("clvdep") String clvdep, @Param("clvfun") String clvfun,
			@Param("sector") Integer sector);

	@Query(value = "SELECT M.* FROM MUNINEP M INNER JOIN XCATPRO X ON M.CAMPO7 = X.CLVFUN AND M.IDSECTOR = X.SECTORID \n"
			+ "	WHERE SUBSTR(X.CLVFUN,1,8) = :programa AND M.IDSECTOR = :sector ", nativeQuery = true)
	List<Muninep> findMuninepByProgramaAndSector(@Param("programa") String programa, @Param("sector") Integer sector);
}
