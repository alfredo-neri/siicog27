package com.gem.sistema.business.service.indicadores;

import java.util.List;

import com.gem.sistema.business.domain.Actividad;
import com.gem.sistema.business.domain.Componente;
import com.gem.sistema.business.domain.Cpd;
import com.gem.sistema.business.domain.Finalidad;
import com.gem.sistema.business.domain.Mir;
import com.gem.sistema.business.domain.Proposito;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.dto.MatrizIndicadoresDTO;

// TODO: Auto-generated Javadoc
/**
 * The Interface MatrizIndicadoresService.
 */
public interface MatrizIndicadoresService {

	/**
	 * Save cabecero.
	 *
	 * @param dto the dto
	 */
	void saveCabecero(MatrizIndicadoresDTO dto);

	/**
	 * Save indicador.
	 *
	 * @param entity the entity
	 * @param tipo   the tipo
	 */
	void saveIndicador(Object entity, Integer tipo);

	/**
	 * Exists matriz indicadores.
	 *
	 * @param clvdepg  the clvdepg
	 * @param cveprog  the cveprog
	 * @param cvetemas the cvetemas
	 * @return true, if successful
	 */
	boolean existsMatrizIndicadores(String clvdepg, String cveprog, String cvetemas);

	/**
	 * Exists finalidad.
	 *
	 * @param clvdepg  the clvdepg
	 * @param cveprog  the cveprog
	 * @param cvetemas the cvetemas
	 * @return true, if successful
	 */
	boolean existsFinalidad(String clvdepg, String cveprog, String cvetemas);

	/**
	 * Exists proposito.
	 *
	 * @param clvdepg  the clvdepg
	 * @param cveprog  the cveprog
	 * @param cvetemas the cvetemas
	 * @return true, if successful
	 */
	boolean existsProposito(String clvdepg, String cveprog, String cvetemas);

	/**
	 * Exists componente.
	 *
	 * @param clvdepg  the clvdepg
	 * @param cveprog  the cveprog
	 * @param cvetemas the cvetemas
	 * @return true, if successful
	 */
	boolean existsComponente(String clvdepg, String cveprog, String cvetemas);

	/**
	 * Delete cabecero.
	 *
	 * @param id the id
	 */
	void deleteCabecero(Long id);

	/**
	 * Delete indicador.
	 *
	 * @param id   the id
	 * @param tipo the tipo
	 */
	void deleteIndicador(Long id, Integer tipo);

	/**
	 * Lista dependencias por sector.
	 *
	 * @param sector the sector
	 * @return the list
	 */
	List<String> listaDependenciasPorSector(Integer sector);

	/**
	 * Filtra programa.
	 *
	 * @param clvDep the clv dep
	 * @return the list
	 */
	List<Xcatpro> filtraPrograma(String clvDep);

	/**
	 * Find all temas matriz ind.
	 *
	 * @return the list
	 */
	List<Cpd> findAllTemasMatrizInd();

	/**
	 * Gets the descripcion dep.
	 *
	 * @param clvDep the clv dep
	 * @return the descripcion dep
	 */
	String getDescripcionDep(String clvDep);

	/**
	 * Gets the descripcion prog.
	 *
	 * @param clvProgram the clv program
	 * @return the descripcion prog
	 */
	String getDescripcionProg(String clvProgram);

	/**
	 * Gets the descripcion tema.
	 *
	 * @param clvtema the clvtema
	 * @return the descripcion tema
	 */
	String getDescripcionTema(String clvtema);

	/**
	 * Gets the descripcion indicador.
	 *
	 * @param codigo the codigo
	 * @return the descripcion indicador
	 */
	String getDescripcionIndicador(String codigo);

	/**
	 * Lista matriz indicadores.
	 *
	 * @return the list
	 */
	List<MatrizIndicadoresDTO> listaMatrizIndicadores();

	/**
	 * Find programas matriz ind.
	 *
	 * @param clvdep the clvdep
	 * @return the list
	 */
	List<String> findProgramasMatrizInd(String clvdep);

	/**
	 * Llenar lista finalidad.
	 *
	 * @param clvdepg  the clvdepg
	 * @param cveprog  the cveprog
	 * @param cvetemas the cvetemas
	 * @return the list
	 */
	List<Finalidad> llenarListaFinalidad(String clvdepg, String cveprog, String cvetemas);

	/**
	 * Llenar lista proposito.
	 *
	 * @param clvdepg  the clvdepg
	 * @param cveprog  the cveprog
	 * @param cvetemas the cvetemas
	 * @return the list
	 */
	List<Proposito> llenarListaProposito(String clvdepg, String cveprog, String cvetemas);

	/**
	 * Llenar lista componente.
	 *
	 * @param clvdepg  the clvdepg
	 * @param cveprog  the cveprog
	 * @param cvetemas the cvetemas
	 * @return the list
	 */
	List<Componente> llenarListaComponente(String clvdepg, String cveprog, String cvetemas);

	/**
	 * Llenar lista actividad.
	 *
	 * @param comp the comp
	 * @return the list
	 */
	List<Actividad> llenarListaActividad(Componente comp);

	/**
	 * Llenar lista codigo indicador.
	 *
	 * @param nivel the nivel
	 * @return the list
	 */
	List<Mir> llenarListaCodigoIndicador(String nivel);

	/**
	 * Find finalidad by id.
	 *
	 * @param id the id
	 * @return the finalidad
	 */
	Finalidad findFinalidadById(long id);

	/**
	 * Find componente by id.
	 *
	 * @param id the id
	 * @return the componente
	 */
	Componente findComponenteById(long id);

	/**
	 * Find proposito by id.
	 *
	 * @param id the id
	 * @return the proposito
	 */
	Proposito findPropositoById(long id);

	/**
	 * Find actividad by id.
	 *
	 * @param id the id
	 * @return the actividad
	 */
	Actividad findActividadById(long id);

	/**
	 * Reset captura.
	 *
	 * @param matrizDTO the matriz DTO
	 * @return the matriz indicadores DTO
	 */
	MatrizIndicadoresDTO resetCaptura(MatrizIndicadoresDTO matrizDTO);

	/**
	 * Llenar lista codigo indicador.
	 *
	 * @param nivel       the nivel
	 * @param cvePrograma the cve programa
	 * @return the list
	 */
	List<Mir> llenarListaCodigoIndicador(String nivel, String cvePrograma);

	/**
	 * Fin by prog and temas and final.
	 *
	 * @param cveDpg  the cve dpg
	 * @param progrma the progrma
	 * @param tema    the tema
	 * @return the list
	 */
	List<Finalidad> finByProgAndTemasAndFinal(String cveDpg, String progrma, String tema);

	/**
	 * Find proposito.
	 *
	 * @param cveDpg  the cve dpg
	 * @param progrma the progrma
	 * @param tema    the tema
	 * @return the list
	 */
	List<Proposito> findProposito(String cveDpg, String progrma, String tema);

	/**
	 * Find componente.
	 *
	 * @param cveDpg  the cve dpg
	 * @param progrma the progrma
	 * @param tema    the tema
	 * @return the list
	 */
	List<Componente> findComponente(String cveDpg, String progrma, String tema);

	/**
	 * Find actividad.
	 *
	 * @param cveDpg  the cve dpg
	 * @param progrma the progrma
	 * @param tema    the tema
	 * @return the list
	 */
	List<Actividad> findActividad(String cveDpg, String progrma, String tema);

}
