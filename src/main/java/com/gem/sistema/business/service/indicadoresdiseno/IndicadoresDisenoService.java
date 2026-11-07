package com.gem.sistema.business.service.indicadoresdiseno;

import java.util.List;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Cpd;
import com.gem.sistema.business.domain.Mir;
import com.gem.sistema.business.domain.Variables;
import com.gem.sistema.business.dto.FtecnicaddDTO;
import com.gem.sistema.business.dto.FtecnicadmDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface IndicadoresDisenoService.
 */
public interface IndicadoresDisenoService {
	
	/**
	 * Llena lista encabezado.
	 *
	 * @return the list
	 */
	List<FtecnicadmDTO> llenaListaEncabezado(Integer idSector);
	
	/**
	 * Llena lista detalle.
	 *
	 * @param ftecnicadmDTO the ftecnicadm DTO
	 * @return the list
	 */
	List<FtecnicaddDTO> llenaListaDetalle(FtecnicadmDTO ftecnicadmDTO);
	
	/**
	 * Llena combo temas.
	 *
	 * @return the list
	 */
	List<Cpd> llenaComboTemas();
	
	/**
	 * Llena combo dependencias.
	 *
	 * @param sectorId the sector id
	 * @return the list
	 */
	List<String> llenaComboDependencias(Integer sectorId);
	
	/**
	 * Llena combo programas.
	 *
	 * @param sectorId the sector id
	 * @return the list
	 */
	List<String> llenaComboProgramas(Integer sectorId);
	
	/**
	 * Llena combo programas filtrado X dep.
	 *
	 * @param clvDep the clv dep
	 * @param sectorId the sector id
	 * @return the list
	 */
	List<String> llenaComboProgramasFiltradoXDep(String clvDep, Integer sectorId);
	
	/**
	 * Llena combo codigos indicador.
	 *
	 * @return the list
	 */
	List<Mir> llenaComboCodigosIndicador();
	
	/**
	 * Tiene detalle.
	 *
	 * @param ftecnicadmDTO the ftecnicadm DTO
	 * @return true, if successful
	 */
	boolean tieneDetalle(FtecnicadmDTO ftecnicadmDTO);	
	
	/**
	 * Salvar diseno.
	 *
	 * @param ftecnicadmDTO the ftecnicadm DTO
	 * @return 
	 */
	boolean salvarDiseno(FtecnicadmDTO ftecnicadmDTO);
	
	/**
	 * Salvar diseno modificado.
	 *
	 * @param ftecnicadmDTO the ftecnicadm DTO
	 */
	void salvarDisenoModificado(FtecnicadmDTO ftecnicadmDTO);
	
	/**
	 * Salvar detalle.
	 *
	 * @param ftecnicaddDTO the ftecnicadd DTO
	 */
	void salvarDetalle(FtecnicaddDTO ftecnicaddDTO);
	
	/**
	 * Salvar detalle modificado.
	 *
	 * @param ftecnicaddDTO the ftecnicadd DTO
	 */
	void salvarDetalleModificado(FtecnicaddDTO ftecnicaddDTO);
	
	/**
	 * Delete diseno.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	boolean deleteDiseno(long id);
	
	/**
	 * Delete detalle.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	boolean deleteDetalle(long id);
	
	/**
	 * Llena combo variables.
	 *
	 * @return the list
	 */
	List<Variables> llenaComboVariables();
	
	/**
	 * Busca desc dependencia.
	 *
	 * @param clvDep the clv dep
	 * @param sectorId the sector id
	 * @return the list
	 */
	List<Catdep> buscaDescDependencia(String clvDep, Integer sectorId);
	
	
	/**
	 * Llena combo variables.
	 *
	 * @return the list
	 */
	List<Cpd> llenaComboVariablesLength();
	
	
	
}
