package com.gem.sistema.business.service.indicadores;

import static com.gem.sistema.util.Constants.ZERO;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.Actividad;
import com.gem.sistema.business.domain.Catdgm;
import com.gem.sistema.business.domain.Componente;
import com.gem.sistema.business.domain.Cpd;
import com.gem.sistema.business.domain.Finalidad;
import com.gem.sistema.business.domain.Matind;
import com.gem.sistema.business.domain.Mir;
import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.domain.Proposito;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.dto.MatrizIndicadoresDTO;
import com.gem.sistema.business.predicates.ActividadPredicate;
import com.gem.sistema.business.predicates.ComponentePredicates;
import com.gem.sistema.business.predicates.FinalidadPredicates;
import com.gem.sistema.business.predicates.MatindPredicates;
import com.gem.sistema.business.predicates.MirPredicates;
import com.gem.sistema.business.predicates.PropositoPredicates;
import com.gem.sistema.business.repository.catalogos.VariablesRepository;
import com.gem.sistema.business.repository.catalogs.ActividadRepository;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.CatdgmRepository;
import com.gem.sistema.business.repository.catalogs.ComponenteRepository;
import com.gem.sistema.business.repository.catalogs.CpdRepository;
import com.gem.sistema.business.repository.catalogs.FinalidadRepository;
import com.gem.sistema.business.repository.catalogs.MatindRepository;
import com.gem.sistema.business.repository.catalogs.MirRepository;
import com.gem.sistema.business.repository.catalogs.MuniNepRepository;
import com.gem.sistema.business.repository.catalogs.PpCapturaRepository;
import com.gem.sistema.business.repository.catalogs.PpMetaRepository;
import com.gem.sistema.business.repository.catalogs.PropositoRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;
import com.querydsl.core.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class MatrizIndicadoresServiceImpl.
 */
@Service(value = "matrizIndicadoresService")
public class MatrizIndicadoresServiceImpl implements MatrizIndicadoresService {
	private static final Log LOG = LogFactory.getLog(MatrizIndicadoresServiceImpl.class);


	/** The xcatpro repository. */
	@Autowired
	XcatproRepository xcatproRepository;

	/** The pp meta repository. */
	@Autowired
	PpMetaRepository ppMetaRepository;

	/** The pp captura repository. */
	@Autowired
	PpCapturaRepository ppCapturaRepository;

	/** The catdep repository. */
	@Autowired
	CatdepRepository catdepRepository;

	/** The muni nep repository. */
	@Autowired
	MuniNepRepository muniNepRepository;

	/** The matind repository. */
	@Autowired
	MatindRepository matindRepository;

	/** The catdgm repository. */
	@Autowired
	CatdgmRepository catdgmRepository;

	/** The cpd repository. */
	@Autowired
	CpdRepository cpdRepository;

	/** The finalidad repository. */
	@Autowired
	FinalidadRepository finalidadRepository;

	/** The componente repository. */
	@Autowired
	ComponenteRepository componenteRepository;

	/** The proposito repository. */
	@Autowired
	PropositoRepository propositoRepository;

	/** The actividad repository. */
	@Autowired
	ActividadRepository actividadRepository;

	/** The mir repository. */
	@Autowired
	private MirRepository mirRepository;

	@Autowired
	private TcPeriodoRepositoy periodoRepositoy;

	@Autowired
	private VariablesRepository variablesRepository;


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * listaMatrizIndicadores()
	 */
	@Override
	public List<MatrizIndicadoresDTO> listaMatrizIndicadores() {
		List<Matind> mat = matindRepository.findAllByOrderByClvdepgAscCveprogAscCvetemasAsc();
		List<MatrizIndicadoresDTO> matDto = new ArrayList<MatrizIndicadoresDTO>();
		for (Matind m : mat) {
			matDto.add(entityToDto(m));
		}
		return matDto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * saveIndicador(java.lang.Object, java.lang.Integer)
	 */
	public void saveIndicador(Object entity, Integer tipo) {
		switch (tipo) {
		case 1:
			Finalidad f = (Finalidad) entity;
			LOG.debug("FINALIDAD:::" + f);
			finalidadRepository.save(f);
			break;
		case 2:
			Proposito p = (Proposito) entity;
			propositoRepository.save(p);
			break;
		case 3:
			Componente c = (Componente) entity;
			componenteRepository.save(c);
			break;
		case 4:
			Actividad a = (Actividad) entity;
			actividadRepository.save(a);
			break;

		default:
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * llenarListaCodigoIndicador(java.lang.String)
	 */
	public List<Mir> llenarListaCodigoIndicador(String nivel) {
		return mirRepository.findByNivel(nivel);
	}

	/**
	 * Entity to dto.
	 *
	 * @param m the m
	 * @return the matriz indicadores DTO
	 */
	private MatrizIndicadoresDTO entityToDto(Matind m) {
		MatrizIndicadoresDTO dto = new MatrizIndicadoresDTO();
		dto.setId(m.getId());
		dto.setClvdepg(m.getClvdepg());
		dto.setComponente(m.getComponente());
		dto.setCvefin(m.getCvefin());
		dto.setCveprog(m.getCveprog());
		dto.setCvetemas(m.getCvetemas());
		dto.setFeccap(m.getFeccap());
		dto.setFinalidad(m.getFinalidad());
		dto.setIdRef(m.getIdRef());
		dto.setIdsector(m.getIdsector());
		dto.setObjetivo(m.getObjetivo());
		dto.setProposito(m.getProposito());
		dto.setUserid(m.getUserid());
		dto.setUsuario(m.getUsuario());

		return dto;
	}

	/**
	 * Dto to entity.
	 *
	 * @param m the m
	 * @return the matind
	 */
	private Matind dtoToEntity(MatrizIndicadoresDTO m) {
		Matind entity = new Matind();
		entity.setId(m.getId());
		entity.setClvdepg(m.getClvdepg());
		entity.setComponente(m.getComponente());
		entity.setCvefin(m.getCvefin());
		entity.setCveprog(m.getCveprog());
		entity.setCvetemas(m.getCvetemas());
		entity.setFeccap(m.getFeccap());
		entity.setFinalidad(m.getFinalidad());
		entity.setIdRef(m.getIdRef());
		entity.setIdsector(m.getIdsector());
		entity.setObjetivo(m.getObjetivo());
		entity.setProposito(m.getProposito());
		entity.setUserid(m.getUserid());
		entity.setUsuario(m.getUsuario());

		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * findProgramasMatrizInd(java.lang.String)
	 */
	@Override
	public List<String> findProgramasMatrizInd(String clvdep) {
		return xcatproRepository.findProgramasMatrizInd(clvdep);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * getDescripcionProg(java.lang.String)
	 */
	@Override
	public String getDescripcionProg(String clvProgram) {
		List<Muninep> listamuni = muniNepRepository.findByCampo7(clvProgram);
		if (listamuni.isEmpty()) {
			return StringUtils.EMPTY;
		} else {
			return listamuni.get(0).getCampo6();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * listaDependenciasPorSector(java.lang.Integer)
	 */
	public List<String> listaDependenciasPorSector(Integer sector) {
		return xcatproRepository.findProgramasDistinctSubStringBySector(sector);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * filtraPrograma(java.lang.String)
	 */
	public List<Xcatpro> filtraPrograma(String clvDep) {
		return xcatproRepository.findByClvdep(clvDep);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * getDescripcionDep(java.lang.String)
	 */
	public String getDescripcionDep(String clave) {
		List<Catdgm> listaDesc = catdgmRepository.findByClave(clave);
		if (listaDesc.isEmpty()) {
			return StringUtils.EMPTY;
		} else {
			return listaDesc.get(0).getNombre();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * deleteCabecero(java.lang.Long)
	 */
	@Override
	public void deleteCabecero(Long id) {
		matindRepository.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * deleteIndicador(java.lang.Long, java.lang.Integer)
	 */
	@Override
	public void deleteIndicador(Long id, Integer tipo) {
		switch (tipo) {
		case 1:
			finalidadRepository.deleteById(id);
			break;
		case 2:
			propositoRepository.deleteById(id);
			break;
		case 3:
			componenteRepository.deleteById(id);
			break;
		case 4:
			actividadRepository.deleteById(id);
			break;
		default:
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * existsMatrizIndicadores(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean existsMatrizIndicadores(String clvdepg, String cveprog, String cvetemas) {
		Predicate p = MatindPredicates.existsMatrizIndicadores(clvdepg, cveprog, cvetemas);
		return matindRepository.count(p) > ZERO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * existsFinalidad(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean existsFinalidad(String clvdepg, String cveprog, String cvetemas) {
		Predicate p = FinalidadPredicates.existsFinalidad(clvdepg, cveprog, cvetemas);
		return finalidadRepository.count(p) > ZERO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * existsProposito(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean existsProposito(String clvdepg, String cveprog, String cvetemas) {
		Predicate p = PropositoPredicates.existsProposito(clvdepg, cveprog, cvetemas);
		return propositoRepository.count(p) > ZERO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * existsComponente(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean existsComponente(String clvdepg, String cveprog, String cvetemas) {
		Predicate p = ComponentePredicates.existsComponente(clvdepg, cveprog, cvetemas);
		return componenteRepository.count(p) > ZERO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * saveCabecero(com.gem.sistema.business.dto.MatrizIndicadoresDTO)
	 */
	@Override
	public void saveCabecero(MatrizIndicadoresDTO dto) {
		matindRepository.save(dtoToEntity(dto));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * getDescripcionTema(java.lang.String)
	 */
	@Override
	public String getDescripcionTema(String clvtema) {
		List<Cpd> listaTema = cpdRepository.findByCvetemas(clvtema);
		if (listaTema.isEmpty()) {
			return StringUtils.EMPTY;
		} else {
			return listaTema.get(0).getDescripcion();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * getDescripcionIndicador(java.lang.String)
	 */
	@Override
	public String getDescripcionIndicador(String codigo) {
		List<Mir> listaCod = mirRepository.findByCodigo(codigo);
		if (listaCod.isEmpty()) {
			return StringUtils.EMPTY;
		} else {
			return listaCod.get(0).getNomind();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * findAllTemasMatrizInd()
	 */
	@Override
	public List<Cpd> findAllTemasMatrizInd() {
		return cpdRepository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * llenarListaFinalidad(java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<Finalidad> llenarListaFinalidad(String clvdepg, String cveprog, String cvetemas) {
		return finalidadRepository.findByClvdepgAndCveprogAndCvetemas(clvdepg, cveprog, cvetemas);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * llenarListaProposito(java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<Proposito> llenarListaProposito(String clvdepg, String cveprog, String cvetemas) {
		return propositoRepository.findByClvdepgAndCveprogAndCvetemas(clvdepg, cveprog, cvetemas);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * llenarListaComponente(java.lang.String, java.lang.String, java.lang.String)
	 */
	public List<Componente> llenarListaComponente(String clvdepg, String cveprog, String cvetemas) {
		return componenteRepository.findByClvdepgAndCveprogAndCvetemas(clvdepg, cveprog, cvetemas);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * llenarListaActividad(com.gem.sistema.business.domain.Componente)
	 */
	public List<Actividad> llenarListaActividad(Componente comp) {
		return actividadRepository.findByClvdepgAndCveprogAndCvetemasAndCvecom(comp.getClvdepg(), comp.getCveprog(),
				comp.getCvetemas(), comp.getCvecom());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * findComponenteById(long)
	 */
	@Override
	public Componente findComponenteById(long id) {
		return componenteRepository.findById(id).orElse(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * findPropositoById(long)
	 */
	@Override
	public Proposito findPropositoById(long id) {
		return propositoRepository.findById(id).orElse(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * findFinalidadById(long)
	 */
	@Override
	public Finalidad findFinalidadById(long id) {
		return finalidadRepository.findById(id).orElse(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * findActividadById(long)
	 */
	@Override
	public Actividad findActividadById(long id) {
		return actividadRepository.findById(id).orElse(null);
	}

	/**
	 * Gets the xcatpro repository.
	 *
	 * @return the xcatpro repository
	 */
	public XcatproRepository getXcatproRepository() {
		return xcatproRepository;
	}

	/**
	 * Sets the xcatpro repository.
	 *
	 * @param xcatproRepository the new xcatpro repository
	 */
	public void setXcatproRepository(XcatproRepository xcatproRepository) {
		this.xcatproRepository = xcatproRepository;
	}

	/**
	 * Gets the pp meta repository.
	 *
	 * @return the pp meta repository
	 */
	public PpMetaRepository getPpMetaRepository() {
		return ppMetaRepository;
	}

	/**
	 * Sets the pp meta repository.
	 *
	 * @param ppMetaRepository the new pp meta repository
	 */
	public void setPpMetaRepository(PpMetaRepository ppMetaRepository) {
		this.ppMetaRepository = ppMetaRepository;
	}

	/**
	 * Gets the pp captura repository.
	 *
	 * @return the pp captura repository
	 */
	public PpCapturaRepository getPpCapturaRepository() {
		return ppCapturaRepository;
	}

	/**
	 * Sets the pp captura repository.
	 *
	 * @param ppCapturaRepository the new pp captura repository
	 */
	public void setPpCapturaRepository(PpCapturaRepository ppCapturaRepository) {
		this.ppCapturaRepository = ppCapturaRepository;
	}

	/**
	 * Gets the catdep repository.
	 *
	 * @return the catdep repository
	 */
	public CatdepRepository getCatdepRepository() {
		return catdepRepository;
	}

	/**
	 * Sets the catdep repository.
	 *
	 * @param catdepRepository the new catdep repository
	 */
	public void setCatdepRepository(CatdepRepository catdepRepository) {
		this.catdepRepository = catdepRepository;
	}

	/**
	 * Gets the muni nep repository.
	 *
	 * @return the muni nep repository
	 */
	public MuniNepRepository getMuniNepRepository() {
		return muniNepRepository;
	}

	/**
	 * Sets the muni nep repository.
	 *
	 * @param muniNepRepository the new muni nep repository
	 */
	public void setMuniNepRepository(MuniNepRepository muniNepRepository) {
		this.muniNepRepository = muniNepRepository;
	}

	/**
	 * Gets the matind repository.
	 *
	 * @return the matind repository
	 */
	public MatindRepository getMatindRepository() {
		return matindRepository;
	}

	/**
	 * Sets the matind repository.
	 *
	 * @param matindRepository the new matind repository
	 */
	public void setMatindRepository(MatindRepository matindRepository) {
		this.matindRepository = matindRepository;
	}

	/**
	 * Gets the catdgm repository.
	 *
	 * @return the catdgm repository
	 */
	public CatdgmRepository getCatdgmRepository() {
		return catdgmRepository;
	}

	/**
	 * Sets the catdgm repository.
	 *
	 * @param catdgmRepository the new catdgm repository
	 */
	public void setCatdgmRepository(CatdgmRepository catdgmRepository) {
		this.catdgmRepository = catdgmRepository;
	}

	/**
	 * Gets the cpd repository.
	 *
	 * @return the cpd repository
	 */
	public CpdRepository getCpdRepository() {
		return cpdRepository;
	}

	/**
	 * Sets the cpd repository.
	 *
	 * @param cpdRepository the new cpd repository
	 */
	public void setCpdRepository(CpdRepository cpdRepository) {
		this.cpdRepository = cpdRepository;
	}

	/**
	 * Gets the finalidad repository.
	 *
	 * @return the finalidad repository
	 */
	public FinalidadRepository getFinalidadRepository() {
		return finalidadRepository;
	}

	/**
	 * Sets the finalidad repository.
	 *
	 * @param finalidadRepository the new finalidad repository
	 */
	public void setFinalidadRepository(FinalidadRepository finalidadRepository) {
		this.finalidadRepository = finalidadRepository;
	}

	/**
	 * Gets the componente repository.
	 *
	 * @return the componente repository
	 */
	public ComponenteRepository getComponenteRepository() {
		return componenteRepository;
	}

	/**
	 * Sets the componente repository.
	 *
	 * @param componenteRepository the new componente repository
	 */
	public void setComponenteRepository(ComponenteRepository componenteRepository) {
		this.componenteRepository = componenteRepository;
	}

	/**
	 * Gets the proposito repository.
	 *
	 * @return the proposito repository
	 */
	public PropositoRepository getPropositoRepository() {
		return propositoRepository;
	}

	/**
	 * Sets the proposito repository.
	 *
	 * @param propositoRepository the new proposito repository
	 */
	public void setPropositoRepository(PropositoRepository propositoRepository) {
		this.propositoRepository = propositoRepository;
	}

	/**
	 * Gets the actividad repository.
	 *
	 * @return the actividad repository
	 */
	public ActividadRepository getActividadRepository() {
		return actividadRepository;
	}

	/**
	 * Sets the actividad repository.
	 *
	 * @param actividadRepository the new actividad repository
	 */
	public void setActividadRepository(ActividadRepository actividadRepository) {
		this.actividadRepository = actividadRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * resetCaptura(com.gem.sistema.business.dto.MatrizIndicadoresDTO)
	 */
	@Override
	public MatrizIndicadoresDTO resetCaptura(MatrizIndicadoresDTO matrizDTO) {
		return this.entityToDto(this.matindRepository.findOne(MatindPredicates
				.existsMatrizIndicadores(matrizDTO.getClvdepg(), matrizDTO.getCveprog(), matrizDTO.getCvetemas())).orElse(null));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * llenarListaCodigoIndicador(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Mir> llenarListaCodigoIndicador(String nivel, String cvePrograma) {
		return (List<Mir>) this.mirRepository.findAll(MirPredicates.findByNivelAndCveProg(nivel, cvePrograma));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * finByProgAndTemasAndFinal(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public List<Finalidad> finByProgAndTemasAndFinal(String cveDpg, String progrma, String tema) {
		// TODO Auto-generated method stub
		return (List<Finalidad>) finalidadRepository
				.findAll(FinalidadPredicates.findByProgAndTemaAndFinal(cveDpg, progrma, tema));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * findProposito(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Proposito> findProposito(String cveDpg, String progrma, String tema) {
		// TODO Auto-generated method stub
		return (List<Proposito>) propositoRepository.findAll(PropositoPredicates.finProsito(cveDpg, progrma, tema));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * findComponente(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Componente> findComponente(String cveDpg, String progrma, String tema) {
		// TODO Auto-generated method stub
		return (List<Componente>) componenteRepository
				.findAll(ComponentePredicates.existsComponente(cveDpg, progrma, tema));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.MatrizIndicadoresService#
	 * findActividad(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Actividad> findActividad(String cveDpg, String progrma, String tema) {
		// TODO Auto-generated method stub
		return (List<Actividad>) this.actividadRepository
				.findAll(ActividadPredicate.findActividad(cveDpg, progrma, tema));
	}

	public MirRepository getMirRepository() {
		return mirRepository;
	}

	public void setMirRepository(MirRepository mirRepository) {
		this.mirRepository = mirRepository;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public VariablesRepository getVariablesRepository() {
		return variablesRepository;
	}

	public void setVariablesRepository(VariablesRepository variablesRepository) {
		this.variablesRepository = variablesRepository;
	}

}
