package com.gem.sistema.business.service.metas;

import java.util.List;

import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.dto.MetasFisicasDTO;
import com.gem.sistema.business.dto.MetasFisicasDetalleDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface MetasFisicasService.
 */
public interface MetasFisicasService {
	
	/**
	 * Lista dependencias.
	 *
	 * @param sector the sector
	 * @return the list
	 */
	List<Xcatpro> listaDependencias(Integer sector);
	
	/**
	 * Llena desc programa.
	 *
	 * @param clvProgram the clv program
	 * @return the string
	 */
	String llenaDescPrograma(String clvProgram);
	
	/**
	 * Llena desc fuente.
	 *
	 * @param clvFuen the clv fuen
	 * @param idSector the id sector
	 * @return the string
	 */
	String llenaDescFuente(String clvFuen, Integer idSector);
	
	/**
	 * Llena desc nompro.
	 *
	 * @param clvDep the clv dep
	 * @param clvFun the clv fun
	 * @param clvfin the clvfin
	 * @return the string
	 */
	String llenaDescNompro(String clvDep, String clvFun, String clvfin);
	
	/**
	 * Llena desc nompro new req.
	 *
	 * @param clvDep the clv dep
	 * @return the string
	 */
	String llenaDescNomproNewReq(String clvDep);
	
	/**
	 * Filtra programa.
	 *
	 * @param clvDep the clv dep
	 * @return the list
	 */
	List<Xcatpro> filtraPrograma(String clvDep);
	
	/**
	 * Filtra fuente.
	 *
	 * @param clvDep the clv dep
	 * @param clvFun the clv fun
	 * @return the list
	 */
	List<Xcatpro> filtraFuente(String clvDep, String clvFun);
	
	/**
	 * Gets the encabezados.
	 *
	 * @param idSector the id sector
	 * @return the encabezados
	 */
	List<MetasFisicasDTO> getEncabezados(Integer idSector, String dependencia, String programa);
	
	/**
	 * Find by clvdep clv nep clv fuen.
	 *
	 * @param clvdep the clvdep
	 * @param clvnep the clvnep
	 * @param clvfuen the clvfuen
	 * @return the metas fisicas DTO
	 */
	MetasFisicasDTO findByClvdepClvNepClvFuen(String clvdep, String clvnep, String clvfuen);
	
	/**
	 * Find by clvdep clv nep clv fuen clv met.
	 *
	 * @param clvdep the clvdep
	 * @param clvnep the clvnep
	 * @param clvfuen the clvfuen
	 * @param clvmet the clvmet
	 * @return the metas fisicas detalle DTO
	 */
	MetasFisicasDetalleDTO findByClvdepClvNepClvFuenClvMet(String clvdep, String clvnep, String clvfuen, Integer clvmet);
	
	/**
	 * Save metas.
	 *
	 * @param dtoMetaList the dto meta list
	 * @return the metas fisicas detalle DTO
	 */
	MetasFisicasDetalleDTO saveMetas(MetasFisicasDetalleDTO dtoMetaList);
	
	/**
	 * Save cabecero.
	 *
	 * @param dto the dto
	 * @return the metas fisicas DTO
	 */
	MetasFisicasDTO saveCabecero(MetasFisicasDTO dto);
	
	/**
	 * Delete cabecero.
	 *
	 * @param id the id
	 */
	void deleteCabecero(Long id);
	
	/**
	 * Delete meta.
	 *
	 * @param id the id
	 */
	void deleteMeta(Long id);
	
	/**
	 * Desripcion dep.
	 *
	 * @param clvDep the clv dep
	 * @return the string
	 */
	String desripcionDep(String clvDep);
	
}
