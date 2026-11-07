package com.gem.sistema.business.service.indicadores;

import java.util.List;

import com.gem.sistema.business.domain.Catdaa;
import com.gem.sistema.business.domain.Catdgm;
import com.gem.sistema.business.domain.Cpd;
import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.domain.TcFicha;
import com.gem.sistema.business.domain.TcFichaVariable;
import com.gem.sistema.business.domain.TcMatriz;
import com.gem.sistema.business.domain.TcNivel;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TrMatrizNivel;
import com.gem.sistema.business.domain.Variables;

public interface MatrizFichasIndicadoresService {

	TcMatriz saveMatriz(TcMatriz matriz);

	Catdgm getDependenciaGeneralById(Long id);

	Catdaa getDependenciaAuxiliarById(Long id);

	Cpd getTemaDesarrolloById(Long id);

	Cpd getTemaByCvetema(String cvetema);

	Muninep getProyectoById(Long id);

	List<Catdgm> listadoDependenciasGenerales(String Programa, Integer sector);

	List<Catdaa> listadoDependenciasAuxiliares(String Programa, Integer sector);

	List<Muninep> listadoProyectos(String Programa, Integer sector);

	List<Cpd> listadoTemasDesarrollo(String cveTema);

	List<TcFicha> listadoFichas(Integer sector);

	List<TcPeriodo> getTrimestres();

	List<Variables> getListadoVariables();

	List<TcFicha> listadoFichas(String dependencia, String codigoIndicador);

	Boolean validSave(TcMatriz matriz);

	Boolean existPrograma(TcMatriz matriz);

	List<TcMatriz> getMatrices(Integer sector);

	List<TcMatriz> getMatrices(Integer sector, String searchDependencia, String searchPrograma);

	List<TcNivel> getNivelesByMatrizId(Long idMatriz);

	void saveMatricesNivel(List<TrMatrizNivel> niveles);

	void saveFicha(TcFicha ficha);

	void deleteFicha(Long idFicha);

	void saveFichaVariable(TcFichaVariable fichaVariable);

	void deleteFichaVariable(TcFichaVariable fichaVariable);
	
}
