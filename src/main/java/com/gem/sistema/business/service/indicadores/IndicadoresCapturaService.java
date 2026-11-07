package com.gem.sistema.business.service.indicadores;

import java.math.BigInteger;
import java.util.List;

import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.dto.IndCapturaDTO;
import com.gem.sistema.business.dto.IndMetaDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface IndicadoresCapturaService.
 *
 * @author Samtriani
 */
public interface IndicadoresCapturaService {
	
	/**
	 * Llenar cabecera.
	 *
	 * @return the list
	 */
	List<IndCapturaDTO> llenarCabecera();

	/**
	 * Find all by clvdep.
	 *
	 * @param clvdep the clvdep
	 * @param clvnep the clvnep
	 * @return the list
	 */
	List<IndMetaDTO> findAllByClvdep(String clvdep, String clvnep);

	/**
	 * Save cabecero.
	 *
	 * @param dto the dto
	 */
	void saveCabecero(IndCapturaDTO dto);

	/**
	 * Save metas.
	 *
	 * @param dtoMetaList the dto meta list
	 * @return the boolean
	 */
	Boolean saveMetas(IndMetaDTO dtoMetaList);

	/**
	 * Tiene metas.
	 *
	 * @param clvdep the clvdep
	 * @param clvnep the clvnep
	 * @return true, if successful
	 */
	boolean tieneMetas(String clvdep, String clvnep);

	/**
	 * Puede registrar meta.
	 *
	 * @param id the id
	 * @param clvdep the clvdep
	 * @param clvnep the clvnep
	 * @param clvmet the clvmet
	 * @return true, if successful
	 */
	boolean puedeRegistrarMeta(Long id, String clvdep, String clvnep, Integer clvmet);

	/**
	 * Tiene programa.
	 *
	 * @param cvldep the cvldep
	 * @param clvnep the clvnep
	 * @return true, if successful
	 */
	boolean tienePrograma(String cvldep, String clvnep);

	/**
	 * Puede registrar cabecero.
	 *
	 * @param id the id
	 * @param cvldep the cvldep
	 * @param clvnep the clvnep
	 * @return true, if successful
	 */
	boolean puedeRegistrarCabecero(BigInteger id, String cvldep, String clvnep);

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
	 * Lista dependencias.
	 *
	 * @param sectorid the sectorid
	 * @return the list
	 */
	List<Xcatpro> listaDependencias(int sectorid);

	/**
	 * Filtra programa.
	 *
	 * @param clvDep the clv dep
	 * @return the list
	 */
	List<Xcatpro> filtraPrograma(String clvDep);

	/**
	 * Desripcion dep.
	 *
	 * @param clvDep the clv dep
	 * @return the string
	 */
	String desripcionDep(String clvDep);

	/**
	 * Llena desc programa.
	 *
	 * @param clvProgram the clv program
	 * @return the string
	 */
	String llenaDescPrograma(String clvProgram);
	
	/**
	 * Update metas.
	 *
	 * @param indMetaDTO the ind meta DTO
	 * @return the boolean
	 */
	Boolean updateMetas(IndMetaDTO indMetaDTO);
}
