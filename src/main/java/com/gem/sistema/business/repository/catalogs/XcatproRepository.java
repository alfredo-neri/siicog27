package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.dto.IndCapturaDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface XcatproRepository.
 *
 * @author
 */
@Repository(value = "xcatproRepository")
public interface XcatproRepository
		extends PagingAndSortingRepository<Xcatpro, Long>, QueryDslPredicateExecutor<Xcatpro> {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	List<Xcatpro> findAll();

	/**
	 * Find by sectorid.
	 *
	 * @param sectorid the sectorid
	 * @param sort     the sort
	 * @return the list
	 */
	List<Xcatpro> findBySectorid(Integer sectorid, Sort sort);

	/**
	 * Find distinct clvpro by sectorid.
	 *
	 * @param sectorid the sectorid
	 * @param sort     the sort
	 * @return the list
	 */
	List<Xcatpro> findDistinctClvproBySectorid(Integer sectorid, Sort sort);

	/**
	 * Find custom distinct.
	 *
	 * @param idSector the id sector
	 * @param clvdep   the clvdep
	 * @return the list
	 */
	@Query(value = "select ROW_NUMBER() OVER()  as id,  clvpro, cappro, clvdep, clvfin, clvfun,"
			+ " feccap, ID_REF, ini, nompro, sectorid, ultniv, USERID "
			+ "from(select distinct (x.clvfun||x.clvfin) as clvpro,  '' as cappro, '' as clvdep, '' as clvfin, "
			+ "'' as clvfun, null as feccap, 0 as ID_REF, '' as ini, x.NOMPRO as nompro, x.SECTORID as SECTORID, "
			+ "'' as ultniv, '' as USERID from Xcatpro x where x.CLVDEP=:clvdep AND x.SECTORID=:idSector  order by 1)", nativeQuery = true)
	List<Xcatpro> findCustomDistinct(@Param("idSector") Integer idSector, @Param("clvdep") String clvdep);

	/**
	 * Find by sectorid.
	 *
	 * @param sectorid the sectorid
	 * @return the list
	 */
	List<Xcatpro> findBySectorid(Integer sectorid);

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 */
	@Transactional(timeout = 10)
	<S extends Xcatpro> S save(S entity);

	/**
	 * Find by clvdep and clvfun.
	 *
	 * @param clvdep the clvdep
	 * @param clvFun the clv fun
	 * @return the list
	 */
	List<Xcatpro> findByClvdepAndClvfun(String clvdep, String clvFun);

	/**
	 * Find by sectorid and clvdep and clvfun and clvfin.
	 *
	 * @param sectorId the sector id
	 * @param clvdep   the clvdep
	 * @param clvFun   the clv fun
	 * @param clvFin   the clv fin
	 * @return the list
	 */
	List<Xcatpro> findBySectoridAndClvdepAndClvfunAndClvfin(Integer sectorId, String clvdep, String clvFun,
			String clvFin);

	/**
	 * Find first by sectorid and clvdep and clvfun and clvfin order by id desc.
	 *
	 * @param sectorId the sector id
	 * @param clvdep   the clvdep
	 * @param clvFun   the clv fun
	 * @param clvFin   the clv fin
	 * @return the xcatpro
	 */
	Xcatpro findFirstBySectoridAndClvdepAndClvfunAndClvfinOrderByIdDesc(Integer sectorId, String clvdep, String clvFun,
			String clvFin);

	/**
	 * Find by clvdep and clvfun and clvfin.
	 *
	 * @param clvdep the clvdep
	 * @param clvFun the clv fun
	 * @param clvFin the clv fin
	 * @return the list
	 */
	List<Xcatpro> findByClvdepAndClvfunAndClvfin(String clvdep, String clvFun, String clvFin);

	/**
	 * Find by clvpro.
	 *
	 * @param clvpro the clvpro
	 * @return the list
	 */
	List<Xcatpro> findByClvpro(String clvpro);

	/**
	 * Find by clvfun.
	 *
	 * @param clvFun the clv fun
	 * @return Lista con los Programas resultantes de la busqueda por CLVFUN
	 */
	List<Xcatpro> findByClvfun(String clvFun);

	/**
	 * Find by clvfin.
	 *
	 * @param clvFin the clv fin
	 * @return the list
	 */
	List<Xcatpro> findByClvfin(String clvFin);

	/**
	 * Find dep ejecutoras.
	 *
	 * @return the list
	 */
	@Query(value = "SELECT CLV.CLVDEP, CD.NOMDEP, CLV.OBJ_PROY, CLV.DESC_PROY, CLV.EST_PROY, CLV.CLVNEP, CLV.ID, CLV.DIMENSIONC, CLV.CAMPO0, CLV.CAMPO1, CLV.CAMPO2, CLV.CAMPO3, CLV.CAMPO4, CLV.CAMPO5  FROM CATDEP CD, "
			+ "(SELECT X.CLVDEP, P.OBJ_PROY, P.DESC_PROY, P.EST_PROY, P.CLVNEP, P.ID, P.DIMENSIONC, P.CAMPO0, P.CAMPO1, P.CAMPO2, P.CAMPO3, P.CAMPO4, P.CAMPO5 "
			+ "FROM XCATPRO X, PP_CAPTURA P WHERE X.CLVDEP=P.CLVDEP GROUP BY X.CLVDEP, P.OBJ_PROY, P.DESC_PROY, P.EST_PROY, P.CLVNEP, P.ID, P.DIMENSIONC, P.CAMPO0, P.CAMPO1, P.CAMPO2, P.CAMPO3, P.CAMPO4, P.CAMPO5) CLV "
			+ "WHERE CD.CLVDEP = CLV.CLVDEP ORDER BY CLV.CLVDEP ASC", nativeQuery = true)
	List<Object> findDepEjecutoras();

	/**
	 * Find programas.
	 *
	 * @param clvdep the clvdep
	 * @return the list
	 */
	@Query("SELECT NEW com.gem.sistema.business.dto.IndCapturaDTO(x.clvfun, m.campo7) "
			+ "FROM Xcatpro x, PpCaptura p, Muninep m WHERE x.clvdep=:clvdep "
			+ "AND x.clvfun=p.clvnep AND x.clvfun=m.campo8 ORDER BY x.clvfun DESC")
	List<IndCapturaDTO> findProgramas(@Param("clvdep") String clvdep);

	/**
	 * Find programas dialog.
	 *
	 * @param sector the sector
	 * @return the list
	 */
	@Query(value = "SELECT DISTINCT SUBSTR(CLVFUN,1,8), M.CAMPO6 FROM MUNINEP M, XCATPRO X "
			+ "WHERE M.CAMPO7 = SUBSTR(X.CLVFUN,1,8) AND X.SECTORID=M.IDSECTOR "
			+ "AND IDSECTOR=:sector", nativeQuery = true)
	List<Object> findProgramasDialog(@Param("sector") Integer sector);

	/**
	 * Find by clvdep.
	 *
	 * @param clvdep the clvdep
	 * @return the list
	 */
	List<Xcatpro> findByClvdep(String clvdep);

	/**
	 * Find programas matriz ind.
	 *
	 * @param clvdepg the clvdepg
	 * @return the list
	 */
	@Query("SELECT SUBSTRING(x.clvfun,1,8) from Xcatpro x where SUBSTRING(x.clvdep,1,3)=:clvdepg")
	List<String> findProgramasMatrizInd(@Param("clvdepg") String clvdepg);

	/**
	 * Find programas distinct sub string by sector.
	 *
	 * @param sector the sector
	 * @return the list
	 */
	@Query("SELECT SUBSTRING(x.clvdep,1,3) from Xcatpro x where x.sectorid=:sector group by SUBSTR(x.clvdep,1,3) order by 1")
	List<String> findProgramasDistinctSubStringBySector(@Param("sector") Integer sector);

	/**
	 * Find first by clvdep and progragram and ultniv and sector.
	 *
	 * @param clvdep   the clvdep
	 * @param program  the program
	 * @param ultniv   the ultniv
	 * @param sectorId the sector id
	 * @return the xcatpro
	 */
	@Query(value = "SELECT * FROM XCATPRO X "
			+ "WHERE CLVDEP = :clvdep and (CLVFUN||CLVFIN) =:program and ULTNIV =:ultniv and SECTORID=:sectorid ORDER BY ID ASC", nativeQuery = true)
	Xcatpro findFirstByClvdepAndProgragramAndUltnivAndSector(@Param("clvdep") String clvdep,
			@Param("program") String program, @Param("ultniv") String ultniv, @Param("sectorid") Integer sectorId);

	/**
	 * Existt by id sector and sscta.
	 *
	 * @param idSectorm the id sectorm
	 * @param sscta     the sscta
	 * @return the boolean
	 */
	@Query(value = "select CASE  WHEN count(*)> 0 THEN 'true' ELSE 'false' END from (select distinct (x.clvfun||x.clvfin) as clvpro "
			+ "from Xcatpro x where x.SECTORID=:idSector order by 1) where clvpro = :sscta", nativeQuery = true)
	Boolean existtByIdSectorAndSscta(@Param("idSector") Integer idSectorm, @Param("sscta") String sscta);

	/**
	 * Exist by clvdep and clvfunfin.
	 *
	 * @param idSector the id sector
	 * @param scta     the scta
	 * @param sscta    the sscta
	 * @return the boolean
	 */
	@Query(value = "SELECT CASE  WHEN COUNT(1)> 0 THEN 'true' ELSE 'false' END " + "FROM XCATPRO X "
			+ "WHERE X.CLVDEP=:scta AND (X.CLVFUN||X.CLVFIN) =:sscta AND X.SECTORID=:idSector "
			+ "ORDER BY 1", nativeQuery = true)
	Boolean existByClvdepAndClvfunfin(@Param("idSector") Integer idSector, @Param("scta") String scta,
			@Param("sscta") String sscta);

	/**
	 * Gets the clave.
	 *
	 * @param clvdep   the clvdep
	 * @param idsector the idsector
	 * @return the clave
	 */
	@Query("select x  from Xcatpro x where x.clvdep=:clvdep and sectorid=:idsector")
	List<Xcatpro> getClave(@Param("clvdep") String clvdep, @Param("idsector") Integer idsector);

	/**
	 * Gets the clave by id.
	 *
	 * @param idsector the idsector
	 * @return the clave by id
	 */
	@Query("select distinct x.clvdep from Xcatpro x where  sectorid=:idsector")
	List<String> getClaveById(@Param("idsector") Integer idsector);

	/**
	 * Gets the clvpro by id sector.
	 *
	 * @param idsector the idsector
	 * @return the clvpro by id sector
	 */
	@Query("select  x.clvdep||x.clvfun||x.clvfin ||'_'|| x.nompro from Xcatpro x where  x.sectorid=:idsector group by x.clvdep||x.clvfun||x.clvfin ||'_'|| x.nompro")
	List<String> getClvproByIdSector(@Param("idsector") Integer idsector);

	/**
	 * Gets the clvfunclvfin nompro by id sector.
	 *
	 * @param idsector the idsector
	 * @return the clvfunclvfin nompro by id sector
	 */
	@Query("select distinct x.clvfun|| x.clvfin||'_'||x.nompro from Xcatpro x where  x.sectorid=:idsector")
	List<String> getClvfunclvfinNomproByIdSector(@Param("idsector") Integer idsector);

	/**
	 * Gets the clvdep by idsector.
	 *
	 * @param idsector the idsector
	 * @return the clvdep by idsector
	 */
	@Query("select distinct substr(clvdep,1,3) from Xcatpro where sectorid=:idsector ")
	List<String> getClvdepByIdsector(@Param("idsector") Integer idsector);

	/**
	 * Gets the clvfunclvfin by id sector.
	 *
	 * @param idsector the idsector
	 * @return the clvfunclvfin by id sector
	 */
	@Query("select distinct x.clvfun|| x.clvfin from Xcatpro x where  x.sectorid=:idsector")
	List<String> getClvfunclvfinByIdSector(@Param("idsector") Integer idsector);

	/**
	 * Gets the nompro by id sector and programa.
	 *
	 * @param idsector the idsector
	 * @param programa the programa
	 * @return the nompro by id sector and programa
	 */
	@Query(value = "select nompro from Xcatpro where sectorid=:idsector and clvfun||clvfin =:programa limit 1", nativeQuery = true)
	String getNomproByIdSectorAndPrograma(@Param("idsector") Integer idsector, @Param("programa") String programa);

	/**
	 * Find by clvdep and sectorid order by clvdep.
	 *
	 * @param clvdep   the clvdep
	 * @param sectorId the sector id
	 * @return the list
	 */
	List<Xcatpro> findByClvdepAndSectoridOrderByClvdep(String clvdep, Integer sectorId);

	/**
	 * Find distinct clvdep by sectorid order by clvdep.
	 *
	 * @param idsector the idsector
	 * @return the list
	 */
	@Query("select distinct clvdep from Xcatpro where sectorid=:idsector order by clvdep asc")
	List<String> findDistinctClvdepBySectoridOrderByClvdep(@Param("idsector") Integer idsector);

	/**
	 * Gets the claves programa by id sector.
	 *
	 * @param idsector the idsector
	 * @return the claves programa by id sector
	 */
	@Query(value = "select CONCAT(SUBSTR(clvpro,1,6),'000'), CONCAT(SUBSTR(clvpro,7,9),'0000') from XCATPRO where sectorId=:idSector", nativeQuery = true)
	List<Object[]> getClavesProgramaByIdSector(@Param("idSector") Integer idsector);

	/**
	 * Llena lista programas distinct.
	 *
	 * @param clvdep   the clvdep
	 * @param idsector the idsector
	 * @return the list
	 */
	@Query("select distinct clvfun from Xcatpro where clvdep=:clvdep and sectorid=:idsector order by 1 asc")
	List<String> llenaListaProgramasDistinct(@Param("clvdep") String clvdep, @Param("idsector") Integer idsector);

	/**
	 * Llena lista programas complete.
	 *
	 * @param idsector the idsector
	 * @return the list
	 */
	@Query("select distinct clvfun from Xcatpro where sectorid=:idsector order by clvfun asc")
	List<String> llenaListaProgramasComplete(@Param("idsector") Integer idsector);

	/**
	 * Find by sector order by clvdep clvfun clvfin.
	 *
	 * @param idsector the idsector
	 * @return the list
	 */
	@Query("select x from Xcatpro x WHERE sectorid =:idsector order by clvdep, clvfun, clvfin asc")
	List<Xcatpro> findBySectorOrderByClvdepClvfunClvfin(@Param("idsector") Integer idsector);

	/**
	 * Find by clvdep and sectorid.
	 *
	 * @param clveDep  the clve dep
	 * @param idSector the id sector
	 * @return the list
	 */
	List<Xcatpro> findByClvdepAndSectorid(String clveDep, Integer idSector);

	@Query(value = "select nompro from Xcatpro where sectorid=:idsector and clvpro =:programa limit 1", nativeQuery = true)
	String getNomproByIdSectorAndClvpro(@Param("idsector") Integer idsector, @Param("programa") String programa);

	/**
	 * Find by sectorid.
	 *
	 * @param sectorid the sectorid order clvdep
	 * @return the list
	 */
	List<Xcatpro> findBySectoridOrderByClvdep(Integer sectorid);

	Integer countBySectoridAndClvfunAndClvfin(Integer sectorid, String clvfun, String clvfin);

	Integer countBySectoridAndClvfunStartingWith(Integer sectorid, String clvfun);

	Integer countBySectoridAndClvdepStartingWithAndClvfunStartingWith(Integer sectorid, String cvldep, String clvfun);

	@Query(value = "SELECT * FROM XCATPRO WHERE SECTORID =:sector AND SUBSTR(CLVDEP,1,3) =:depGeneral AND SUBSTR(CLVDEP,4,3) =:depAuxiliar AND CLVFUN =:programa ", nativeQuery = true)
	List<Xcatpro> findByDepGen_DepAux_Programa(@Param("sector") Integer sector, @Param("depGeneral") String depGeneral,
			@Param("depAuxiliar") String depAuxiliar, @Param("programa") String programa);
}
