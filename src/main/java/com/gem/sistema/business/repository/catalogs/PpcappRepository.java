package com.gem.sistema.business.repository.catalogs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gem.sistema.business.domain.PpCapp;

// TODO: Auto-generated Javadoc
/**
 * The Interface PpcappRepository.
 *
 * @author
 */
@Repository
public interface PpcappRepository extends PagingAndSortingRepository<PpCapp, Long>, QueryDslPredicateExecutor<PpCapp> {

	/**
	 * Find metas.
	 *
	 * @param idSector the id sector
	 * @return the list
	 */
	@Query(value = "SELECT ppcapp.id,ppcapp.clvdep,xcatpro.nompro,ppcapp.clvnep,muninep.campo6,ppcapp.clvfuen,fuentef.nombref,ppcapp.obj_proy "
			+ "FROM PP_CAPP ppcapp "
			+ "JOIN XCATPRO xcatpro on xcatpro.clvdep = ppcapp.clvdep and xcatpro.clvfun = ppcapp.clvnep and xcatpro.clvfin = ppcapp.clvfuen "
			+ "JOIN MUNINEP muninep on muninep.campo7 = ppcapp.clvnep and muninep.idsector = :idSector "
			+ "JOIN FUENTEF fuentef on fuentef.liga = ppcapp.clvfuen "
			+ "where ppcapp.idsector= xcatpro.sectorid  and xcatpro.sectorid =muninep.idsector	and muninep.idsector=fuentef.idsector "
			+ "ORDER BY ppcapp.clvdep, ppcapp.clvNep", nativeQuery = true)
	List<Object> findMetas(@Param("idSector") Integer idSector);

	@Query(value = "SELECT ppcapp.id,ppcapp.clvdep,xcatpro.nompro,ppcapp.clvnep,muninep.campo6,ppcapp.clvfuen,fuentef.nombref,ppcapp.obj_proy "
			+ "FROM PP_CAPP ppcapp "
			+ "JOIN XCATPRO xcatpro on xcatpro.clvdep = ppcapp.clvdep and xcatpro.clvfun = ppcapp.clvnep and xcatpro.clvfin = ppcapp.clvfuen "
			+ "JOIN MUNINEP muninep on muninep.campo7 = ppcapp.clvnep and muninep.idsector = :idSector "
			+ "JOIN FUENTEF fuentef on fuentef.liga = ppcapp.clvfuen "
			+ "where ppcapp.idsector= xcatpro.sectorid and xcatpro.sectorid =muninep.idsector and muninep.idsector=fuentef.idsector and "
			+ " 	 xcatpro.CLVDEP like :dependencia% " + "ORDER BY ppcapp.clvdep, ppcapp.clvNep", nativeQuery = true)
	List<Object> findMetasByDependencia(@Param("idSector") Integer idSector, @Param("dependencia") String dependencia);

	@Query(value = "SELECT ppcapp.id,ppcapp.clvdep,xcatpro.nompro,ppcapp.clvnep,muninep.campo6,ppcapp.clvfuen,fuentef.nombref,ppcapp.obj_proy "
			+ "FROM PP_CAPP ppcapp "
			+ "JOIN XCATPRO xcatpro on xcatpro.clvdep = ppcapp.clvdep and xcatpro.clvfun = ppcapp.clvnep and xcatpro.clvfin = ppcapp.clvfuen "
			+ "JOIN MUNINEP muninep on muninep.campo7 = ppcapp.clvnep and muninep.idsector = :idSector "
			+ "JOIN FUENTEF fuentef on fuentef.liga = ppcapp.clvfuen "
			+ "where ppcapp.idsector= xcatpro.sectorid and xcatpro.sectorid =muninep.idsector and muninep.idsector=fuentef.idsector and "
			+ " 	 xcatpro.CLVFUN like :programa% " + "ORDER BY ppcapp.clvdep, ppcapp.clvNep", nativeQuery = true)
	List<Object> findMetasByPrograma(@Param("idSector") Integer idSector, @Param("programa") String programa);

	@Query(value = "SELECT ppcapp.id,ppcapp.clvdep,xcatpro.nompro,ppcapp.clvnep,muninep.campo6,ppcapp.clvfuen,fuentef.nombref,ppcapp.obj_proy "
			+ "FROM PP_CAPP ppcapp "
			+ "JOIN XCATPRO xcatpro on xcatpro.clvdep = ppcapp.clvdep and xcatpro.clvfun = ppcapp.clvnep and xcatpro.clvfin = ppcapp.clvfuen "
			+ "JOIN MUNINEP muninep on muninep.campo7 = ppcapp.clvnep and muninep.idsector = :idSector "
			+ "JOIN FUENTEF fuentef on fuentef.liga = ppcapp.clvfuen "
			+ "where ppcapp.idsector= xcatpro.sectorid and xcatpro.sectorid =muninep.idsector and muninep.idsector=fuentef.idsector and "
			+ " 	 xcatpro.CLVDEP like :dependencia% and xcatpro.CLVFUN like :programa% "
			+ "ORDER BY ppcapp.clvdep, ppcapp.clvNep", nativeQuery = true)
	List<Object> findMetas(@Param("idSector") Integer idSector, @Param("dependencia") String dependencia,
			@Param("programa") String programa);

	/**
	 * Find by clvdep clvnep clvfuen.
	 *
	 * @param clvDep  the clv dep
	 * @param clvNep  the clv nep
	 * @param clvFuen the clv fuen
	 * @return the list
	 */
	@Query(value = "SELECT ppcapp.id,ppcapp.clvdep,xcatpro.nompro,ppcapp.clvnep,muninep.campo6,ppcapp.clvfuen,fuentef.nombref,ppcapp.obj_proy "
			+ "FROM PP_CAPP ppcapp "
			+ "JOIN XCATPRO xcatpro on xcatpro.clvdep = ppcapp.clvdep and xcatpro.clvfun = ppcapp.clvnep and xcatpro.clvfin = ppcapp.clvfuen "
			+ "JOIN MUNINEP muninep on muninep.campo7 = ppcapp.clvnep "
			+ "JOIN FUENTEF fuentef on fuentef.liga = ppcapp.clvfuen "
			+ "WHERE ppcapp.clvdep = :clvDep and ppcapp.clvnep = :clvNep and ppcapp.clvFuen = :clvFuen", nativeQuery = true)
	List<Object> findByClvdepClvnepClvfuen(@Param("clvDep") String clvDep, @Param("clvNep") String clvNep,
			@Param("clvFuen") String clvFuen);

	/**
	 * Find detalle metas.
	 *
	 * @param clvDep  the clv dep
	 * @param clvNep  the clv nep
	 * @param clvFuen the clv fuen
	 * @return the list
	 */
	@Query(value = "SELECT id,clvmet,uni_med,ProgAnte,can_metia,can_meti,nom_ind,"
			+ "	can_metic_1,can_metic_2,can_metic_3,can_metic_4," + "	ampli_1,redu_1,cant_avan_1,varfin_1,"
			+ "	ampli_2,redu_2,cant_avan_2,varfin_2," + "	ampli_3,redu_3,cant_avan_3,varfin_3,"
			+ "	ampli_4,redu_4,cant_avan_4,varfin_4 " + "FROM PP_METT ppmett "
			+ "WHERE ppmett.clvdep = :clvDep and ppmett.clvNep = :clvNep and ppmett.clvfuen = :clvFuen", nativeQuery = true)
	List<Object> findDetalleMetas(@Param("clvDep") String clvDep, @Param("clvNep") String clvNep,
			@Param("clvFuen") String clvFuen);

	/**
	 * Find detalle metas by clv met.
	 *
	 * @param clvDep  the clv dep
	 * @param clvNep  the clv nep
	 * @param clvFuen the clv fuen
	 * @param clvMet  the clv met
	 * @return the list
	 */
	@Query(value = "SELECT id,clvmet,uni_med,ProgAnte,can_metia,can_meti,nom_ind,"
			+ "	can_metic_1,can_metic_2,can_metic_3,can_metic_4," + "	ampli_1,redu_1,cant_avan_1,varfin_1,"
			+ "	ampli_2,redu_2,cant_avan_2,varfin_2," + "	ampli_3,redu_3,cant_avan_3,varfin_3,"
			+ "	ampli_4,redu_4,cant_avan_4,varfin_4 " + "FROM PP_METT ppmett "
			+ "WHERE ppmett.clvdep = :clvDep and ppmett.clvNep = :clvNep and ppmett.clvfuen = :clvFuen and ppmett.clvmet = :clvMet", nativeQuery = true)
	List<Object> findDetalleMetasByClvMet(@Param("clvDep") String clvDep, @Param("clvNep") String clvNep,
			@Param("clvFuen") String clvFuen, @Param("clvMet") Integer clvMet);

}
