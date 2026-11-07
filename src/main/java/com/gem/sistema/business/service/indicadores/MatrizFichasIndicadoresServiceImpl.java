package com.gem.sistema.business.service.indicadores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.Catdaa;
import com.gem.sistema.business.domain.Catdgm;
import com.gem.sistema.business.domain.Cpd;
import com.gem.sistema.business.domain.Mir;
import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.domain.TcFicha;
import com.gem.sistema.business.domain.TcFichaTrimestre;
import com.gem.sistema.business.domain.TcFichaVariable;
import com.gem.sistema.business.domain.TcMatriz;
import com.gem.sistema.business.domain.TcNivel;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TcVariablesTrimestre;
import com.gem.sistema.business.domain.TrMatrizNivel;
import com.gem.sistema.business.domain.Variables;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.repository.catalogos.VariablesRepository;
import com.gem.sistema.business.repository.catalogs.CatdaaRepository;
import com.gem.sistema.business.repository.catalogs.CatdgmRepository;
import com.gem.sistema.business.repository.catalogs.CpdRepository;
import com.gem.sistema.business.repository.catalogs.MirRepository;
import com.gem.sistema.business.repository.catalogs.MuniNepRepository;
import com.gem.sistema.business.repository.catalogs.TcFichaRepository;
import com.gem.sistema.business.repository.catalogs.TcFichaVariableRepository;
import com.gem.sistema.business.repository.catalogs.TcMatrizRepository;
import com.gem.sistema.business.repository.catalogs.TcNivelRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TrMatrizNivelRepository;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;

/**
 * @author Alfredo
 * @Timeline <strong>Date: </strong>01/12/2021 <strong>Changes: </strong>Se
 *           ajusta componentes a nueva reglas de negocio.
 * 
 * @sector Municipal
 */
@Service(value = "matrizFichasIndicadoresService")
public class MatrizFichasIndicadoresServiceImpl implements MatrizFichasIndicadoresService {

	@Autowired
	private TcMatrizRepository matrizRepository;

	@Autowired
	private TcFichaRepository fichaRepository;

	@Autowired
	private TrMatrizNivelRepository matrizNivelRepository;

	@Autowired
	private TcNivelRepository nivelRepository;

	@Autowired
	private MuniNepRepository muniNepRepository;

	@Autowired
	private XcatproRepository xcatproRepository;

	@Autowired
	private CatdaaRepository catdaaRepository;

	@Autowired
	private CatdgmRepository catdgmRepository;

	@Autowired
	private CpdRepository cpdRepository;

	@Autowired
	private TcPeriodoRepositoy periodoRepositoy;

	@Autowired
	private MirRepository mirRepository;

	@Autowired
	private TcFichaVariableRepository fichaVariableRepository;

	@Autowired
	private VariablesRepository variablesRepository;

	@Override
	public TcMatriz saveMatriz(TcMatriz matriz) {

		return matrizRepository.save(matriz);
	}

	@Override
	public Catdgm getDependenciaGeneralById(Long id) {
		return catdgmRepository.findById(id).orElse(null);
	}

	@Override
	public Catdaa getDependenciaAuxiliarById(Long id) {
		return catdaaRepository.findById(id).orElse(null);
	}

	@Override
	public Cpd getTemaDesarrolloById(Long id) {
		return cpdRepository.findById(id).orElse(null);
	}

	@Override
	public Cpd getTemaByCvetema(String cvetema) {
		return cpdRepository.findOneByCvetemas(cvetema);
	}

	@Override
	public Muninep getProyectoById(Long id) {
		return muniNepRepository.findById(id).orElse(null);
	}

	@Override
	public List<Catdgm> listadoDependenciasGenerales(String Programa, Integer sector) {
		return catdgmRepository.findDependenciasByProgramaAndSector(Programa, sector);
	}

	@Override
	public List<Catdaa> listadoDependenciasAuxiliares(String Programa, Integer sector) {
		return catdaaRepository.findDependenciasByProgramaAndSector(Programa, sector);
	}

	@Override
	public List<Muninep> listadoProyectos(String Programa, Integer sector) {

		List<Muninep> proyectos = muniNepRepository.findMuninepByProgramaAndSector(Programa, sector);

		return proyectos.stream().distinct().collect(Collectors.toList());
	}

	@Override
	public List<Cpd> listadoTemasDesarrollo(String cveTema) {
		return cpdRepository.getByTema(cveTema);
	}

	@Override
	public List<TcFicha> listadoFichas(Integer sector) {

		List<TcFicha> fichas = fichaRepository.findAllByMatrizNivelMatrizSectorOrderByMatrizNivelMatrizDependenciaClaveAscMatrizNivelMatrizDependenciaAuxiliarClaveAscMatrizNivelMatrizProgramaAscMatrizNivelNivelTipoNivelIdSubNivelAsc(sector);

		return this.loadMirToFicha(fichas);
	}

	private List<TcFicha> loadMirToFicha(List<TcFicha> fichas) {

		for (TcFicha tcFicha : fichas) {

			List<Mir> mir = mirRepository.findByCodigo(tcFicha.getMatrizNivel().getNivel().getCodigoIndicador());

			if (CollectionUtils.isNotEmpty(mir))
				tcFicha.getMatrizNivel().getNivel().setIndicador(mir.get(0));
		}

		return fichas;
	}

	@Override
	public Boolean validSave(TcMatriz matriz) {

		List<TcMatriz> matrizs = matrizRepository
				.findAllByDependenciaClaveAndDependenciaAuxiliarClaveAndProyecto_Campo7AndTemaCvetemas(
						matriz.getDependencia().getClave(), matriz.getDependenciaAuxiliar().getClave(),
						matriz.getProyecto().getCampo7(), matriz.getTema().getCvetemas());
		return CollectionUtils.isEmpty(matrizs);
	}

	@Override
	public Boolean existPrograma(TcMatriz matriz) {

		List<Xcatpro> xcatpros = xcatproRepository.findByDepGen_DepAux_Programa(matriz.getSector(),
				matriz.getDependencia().getClave(), matriz.getDependenciaAuxiliar().getClave(),
				matriz.getProyecto().getCampo7());

		return CollectionUtils.isNotEmpty(xcatpros);
	}

	@Override
	public List<TcPeriodo> getTrimestres() {
		return periodoRepositoy.findByTipoPeriodo(3);
	}

	@Override
	public List<Variables> getListadoVariables() {
		return variablesRepository.findByOrderByNumvarAsc();
	}

	@Override
	public List<TcFicha> listadoFichas(String dependencia, String codigoIndicador) {

		List<TcFicha> fichas = new ArrayList<TcFicha>();

		if (StringUtils.isNotEmpty(dependencia) && StringUtils.isNotEmpty(codigoIndicador)) {
			fichas = fichaRepository
					.findAllByMatrizNivelMatrizDependenciaClaveStartingWithAndMatrizNivelNivelCodigoIndicadorStartingWith(
							dependencia, codigoIndicador);
		} else if (StringUtils.isNotEmpty(dependencia)) {
			fichas = fichaRepository.findAllByMatrizNivelMatrizDependenciaClaveStartingWith(dependencia);
		} else if (StringUtils.isNotEmpty(codigoIndicador)) {
			fichas = fichaRepository.findAllByMatrizNivelNivelCodigoIndicadorStartingWith(codigoIndicador);
		}

		return this.loadMirToFicha(fichas);
	}

	@Override
	public List<TcMatriz> getMatrices(Integer sector) {
		List<TcMatriz> listMatrizs = matrizRepository.findAllBySectorOrderByProgramaAsc(sector);
		return poblateMatriz(sector, listMatrizs);
	}

	@Override
	public List<TcMatriz> getMatrices(Integer sector, String searchDependencia, String searchPrograma) {

		List<TcMatriz> matrizs = null;

		if (!StringUtils.isEmpty(searchDependencia) && !StringUtils.isEmpty(searchPrograma)) {
			matrizs = matrizRepository
					.findAllBySectorAndDependenciaClaveStartingWithIgnoreCaseAndProgramaStartingWithOrderByDependenciaClaveAsc(
							sector, searchDependencia, searchPrograma);
		} else if (!StringUtils.isEmpty(searchDependencia) && StringUtils.isEmpty(searchPrograma)) {
			matrizs = matrizRepository
					.findAllBySectorAndDependenciaClaveStartingWithIgnoreCaseOrderByDependenciaClaveAsc(sector,
							searchDependencia);
		} else {
			matrizs = matrizRepository.findAllBySectorAndProgramaStartingWithOrderByDependenciaClaveAsc(sector,
					searchPrograma);
		}

		return poblateMatriz(sector, matrizs);
	}

	@Override
	public List<TcNivel> getNivelesByMatrizId(Long idMatriz) {
		return nivelRepository.findAllByNivelesMatrizId(idMatriz);
	}

	@Override
	public void saveMatricesNivel(List<TrMatrizNivel> niveles) {
		matrizNivelRepository.saveAll(niveles);
	}

	@Override
	public void saveFicha(TcFicha ficha) {

		if (CollectionUtils.isEmpty(ficha.getTrimestres()) || CollectionUtils.isEmpty(ficha.getVariables())) {

			List<TcFichaVariable> fichaVariables = new ArrayList<TcFichaVariable>();
			List<TcFichaTrimestre> fichaTrimestres = new ArrayList<TcFichaTrimestre>();
			List<TcPeriodo> periodos = periodoRepositoy.findByTipoPeriodo(3);
//			System.out.println("-----------------" + ficha.getMatrizNivel().getNivel().getCodigoIndicador());
			Mir mir = mirRepository.findByCodigo(ficha.getMatrizNivel().getNivel().getCodigoIndicador()).get(0);

			for (TcPeriodo tcPeriodo : periodos) {
				TcFichaTrimestre fichaTrimestre = new TcFichaTrimestre();

				fichaTrimestre.setPeriodo(tcPeriodo);
				fichaTrimestres.add(fichaTrimestre);
			}

			int index = 0;
			for (Variables variable : mir.getVariables()) {
				List<TcVariablesTrimestre> trimestres = new ArrayList<TcVariablesTrimestre>();

				for (TcPeriodo tcPeriodo : periodos) {
					TcVariablesTrimestre trimestre = new TcVariablesTrimestre();

					trimestre.setPeriodo(tcPeriodo);
					trimestres.add(trimestre);
				}

				TcFichaVariable fichaVariable = new TcFichaVariable();
				fichaVariable.setCodigo(++index);
				fichaVariable.setVariable(variable);
				fichaVariable.setTrimestres(trimestres);

				fichaVariables.add(fichaVariable);
			}

			ficha.setTrimestres(fichaTrimestres);
			ficha.setVariables(fichaVariables);
		}

		fichaRepository.save(ficha);
	}

	@Override
	public void deleteFicha(Long idFicha) {
		fichaRepository.deleteById(idFicha);
	}

	private List<TcMatriz> poblateMatriz(Integer sector, List<TcMatriz> listMatrizs) {

		for (TcMatriz tcMatriz : listMatrizs) {
			tcMatriz.setHeaderFull(Boolean.FALSE);

			Muninep programa = muniNepRepository.findFirstByCampo7AndIdsector(tcMatriz.getPrograma(), sector);
			if (null != programa) {
				tcMatriz.setProgramaDescripcion(programa.getCampo6());
				tcMatriz.setHeaderFull(Boolean.TRUE);
			}

			if (tcMatriz.getDependenciaAuxiliar() == null) {
				tcMatriz.setDependenciaAuxiliar(new Catdaa());
				tcMatriz.setHeaderFull(Boolean.FALSE);
			}

			if (tcMatriz.getDependencia() == null) {
				tcMatriz.setDependencia(new Catdgm());
				tcMatriz.setHeaderFull(Boolean.FALSE);
			}

			if (tcMatriz.getProyecto() == null) {
				tcMatriz.setProyecto(new Muninep());
				tcMatriz.setHeaderFull(Boolean.FALSE);
			}

			if (xcatproRepository.countBySectoridAndClvfunStartingWith(sector, tcMatriz.getPrograma()) > 0)
				tcMatriz.setExistXcatpro(Boolean.TRUE);

			if (tcMatriz.getDependencia().getId() == null || tcMatriz.getDependenciaAuxiliar().getId() == null
					|| tcMatriz.getProyecto().getId() == null || tcMatriz.getTema().getCvetemas().length() < 8)
				tcMatriz.setCanUpdateIndicador(Boolean.FALSE);

			List<TrMatrizNivel> componentes = new ArrayList<TrMatrizNivel>();

			Iterator<TrMatrizNivel> maIterator = tcMatriz.getNiveles().listIterator();
			while (maIterator.hasNext()) {

				TrMatrizNivel nivel = maIterator.next();
				if (nivel.getNivel().getTipoNivel().getClave().equals("A")) {
					maIterator.remove();
				}

			}

			for (TrMatrizNivel nivel : tcMatriz.getNiveles()) {
				switch (nivel.getNivel().getTipoNivel().getClave()) {
				case "F":
					tcMatriz.setFinalidad(nivel);
					break;

				case "P":
					tcMatriz.setProposito(nivel);
					break;

				case "C":

					List<TrMatrizNivel> actividades = new ArrayList<TrMatrizNivel>();
					actividades = matrizNivelRepository.findAllByMatrizIdAndNivelPadre(tcMatriz.getId(),
							nivel.getNivel().getId());

					nivel.getNivel().setActividades(actividades);

					componentes.add(nivel);

					break;

				default:
					break;
				}

			}

			tcMatriz.setComponentes(componentes);

		}

		return listMatrizs;
	}

//	public TcMatriz showMatriz(Long idMatriz) {
//		
//		TcMatriz  tcMatriz = matrizRepository.findOne(idMatriz);
//
//			tcMatriz.setHeaderFull(Boolean.FALSE);
//
//			Muninep programa = muniNepRepository.findFirstByCampo7AndIdsector(tcMatriz.getPrograma(), tcMatriz.getSector());
//			if (null != programa) {
//				tcMatriz.setProgramaDescripcion(programa.getCampo6());
//				tcMatriz.setHeaderFull(Boolean.TRUE);
//			}
//
//			if (tcMatriz.getDependenciaAuxiliar() == null) {
//				tcMatriz.setDependenciaAuxiliar(new Catdaa());
//				tcMatriz.setHeaderFull(Boolean.FALSE);
//			}
//
//			if (tcMatriz.getDependencia() == null) {
//				tcMatriz.setDependencia(new Catdgm());
//				tcMatriz.setHeaderFull(Boolean.FALSE);
//			}
//
//			if (tcMatriz.getProyecto() == null) {
//				tcMatriz.setProyecto(new Muninep());
//				tcMatriz.setHeaderFull(Boolean.FALSE);
//			}
//
//			if (xcatproRepository.countBySectoridAndClvfunStartingWith(tcMatriz.getSector(), tcMatriz.getPrograma()) > 0)
//				tcMatriz.setExistXcatpro(Boolean.TRUE);
//
//			if (tcMatriz.getDependencia().getId() == null || tcMatriz.getDependenciaAuxiliar().getId() == null
//					|| tcMatriz.getProyecto().getId() == null || tcMatriz.getTema().getCvetemas().length() < 8)
//				tcMatriz.setCanUpdateIndicador(Boolean.FALSE);
//
//			List<TcNivel> componentes = new ArrayList<TcNivel>();
//
//			Iterator<TrMatrizNivel> maIterator = tcMatriz.getNiveles().listIterator();
//			while (maIterator.hasNext()) {
//
//				TrMatrizNivel nivel = maIterator.next();
//				if (nivel.getNivel().getTipoNivel().getClave().equals("A")) {
//					maIterator.remove();
//				}
//
//			}
//
//			for (TrMatrizNivel nivel : tcMatriz.getNiveles()) {
//				switch (nivel.getNivel().getTipoNivel().getClave()) {
//				case "F":
//					tcMatriz.setFinalidad(nivel.getNivel());
//					tcMatriz.setFinalidad2(nivel);
//					break;
//
//				case "P":
//					tcMatriz.setProposito(nivel.getNivel());
//					tcMatriz.setProposito2(nivel);
//					break;
//
//				case "C":
//
//					List<TcNivel> actividades = new ArrayList<TcNivel>();
//
//					actividades = nivelRepository.findAllByNivelesMatrizIdAndTipoNivelClaveAndPadre(tcMatriz.getId(),
//							"A", nivel.getNivel().getId());
//					nivel.getNivel().setActividades(actividades);
//
//					componentes.add(nivel.getNivel());
//					break;
//
//				default:
//					break;
//				}
//
//			}
//
//			tcMatriz.setComponentes(componentes);
//
//
//		return tcMatriz;
//	}

	@Override
	public void saveFichaVariable(TcFichaVariable fichaVariable) {
		fichaVariableRepository.save(fichaVariable);
	}

	@Override
	public void deleteFichaVariable(TcFichaVariable fichaVariable) {
		fichaVariableRepository.delete(fichaVariable);
	}

	public TcMatrizRepository getMatrizRepository() {
		return matrizRepository;
	}

	public void setMatrizRepository(TcMatrizRepository matrizRepository) {
		this.matrizRepository = matrizRepository;
	}

	public MuniNepRepository getMuniNepRepository() {
		return muniNepRepository;
	}

	public void setMuniNepRepository(MuniNepRepository muniNepRepository) {
		this.muniNepRepository = muniNepRepository;
	}

	public XcatproRepository getXcatproRepository() {
		return xcatproRepository;
	}

	public void setXcatproRepository(XcatproRepository xcatproRepository) {
		this.xcatproRepository = xcatproRepository;
	}

	public CatdaaRepository getCatdaaRepository() {
		return catdaaRepository;
	}

	public void setCatdaaRepository(CatdaaRepository catdaaRepository) {
		this.catdaaRepository = catdaaRepository;
	}

	public CatdgmRepository getCatdgmRepository() {
		return catdgmRepository;
	}

	public void setCatdgmRepository(CatdgmRepository catdgmRepository) {
		this.catdgmRepository = catdgmRepository;
	}

	public CpdRepository getCpdRepository() {
		return cpdRepository;
	}

	public void setCpdRepository(CpdRepository cpdRepository) {
		this.cpdRepository = cpdRepository;
	}

	public TrMatrizNivelRepository getMatrizNivelRepository() {
		return matrizNivelRepository;
	}

	public void setMatrizNivelRepository(TrMatrizNivelRepository matrizNivelRepository) {
		this.matrizNivelRepository = matrizNivelRepository;
	}

	public TcNivelRepository getNivelRepository() {
		return nivelRepository;
	}

	public void setNivelRepository(TcNivelRepository nivelRepository) {
		this.nivelRepository = nivelRepository;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public TcFichaRepository getFichaRepository() {
		return fichaRepository;
	}

	public void setFichaRepository(TcFichaRepository fichaRepository) {
		this.fichaRepository = fichaRepository;
	}

	public MirRepository getMirRepository() {
		return mirRepository;
	}

	public void setMirRepository(MirRepository mirRepository) {
		this.mirRepository = mirRepository;
	}

	public TcFichaVariableRepository getFichaVariableRepository() {
		return fichaVariableRepository;
	}

	public void setFichaVariableRepository(TcFichaVariableRepository fichaVariableRepository) {
		this.fichaVariableRepository = fichaVariableRepository;
	}

	public VariablesRepository getVariablesRepository() {
		return variablesRepository;
	}

	public void setVariablesRepository(VariablesRepository variablesRepository) {
		this.variablesRepository = variablesRepository;
	}

}
