package com.gem.sistema.business.service.indicadores;

import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import jakarta.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.domain.PpCaptura;
import com.gem.sistema.business.domain.PpMeta;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.dto.IndCapturaDTO;
import com.gem.sistema.business.dto.IndMetaDTO;
import com.gem.sistema.business.predicates.PpCapturaPredicates;
import com.gem.sistema.business.predicates.PpMetaPredicates;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.MuniNepRepository;
import com.gem.sistema.business.repository.catalogs.PpCapturaRepository;
import com.gem.sistema.business.repository.catalogs.PpMetaRepository;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;
import com.querydsl.core.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class IndicadoresCapturaServiceImpl.
 */
@Service("indicadoresCapturaService")
public class IndicadoresCapturaServiceImpl implements IndicadoresCapturaService {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.IndicadoresCapturaService#
	 * llenarCabecera()
	 */
	@Override
	public List<IndCapturaDTO> llenarCabecera() {
		List<Muninep> programas = null;
		List<Object> dependenciasObj = xcatproRepository.findDepEjecutoras();
		List<IndCapturaDTO> dependencias = new ArrayList<IndCapturaDTO>();
		for (Object o : dependenciasObj) {
			Object obj[] = (Object[]) o;
			IndCapturaDTO dto = new IndCapturaDTO((String) obj[0], (String) obj[1], (String) obj[3], (String) obj[2],
					(String) obj[4], (String) obj[5], (BigInteger) obj[6], (String) obj[7], (String) obj[8],
					(String) obj[9], (String) obj[10], (String) obj[11], (String) obj[12], (String) obj[13]);
			programas = muniNepRepository.findByCampo7(dto.getClvnep());
			if (!programas.isEmpty()) {
				dto.setDesProgramas(programas.get(ZERO).getCampo6());
				dto.setObjPrograma(programas.get(ZERO).getCampo8());
			} else {
				dto.setDesProgramas(StringUtils.EMPTY);
				dto.setObjPrograma(StringUtils.EMPTY);
			}

			dependencias.add(dto);
		}

		// for (IndCapturaDTO dep : dependencias) {
		// programas = xcatproRepository.findProgramas(dep.getClvDep());
		// dep.setProgramas(programas);
		// dep.setMetaList(findAllByClvdep(dep.getClvDep()));
		// }
		return dependencias;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.IndicadoresCapturaService#
	 * tieneMetas(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean tieneMetas(String clvdep, String clvnep) {
		List<PpMeta> metas = ppMetaRepository.findAllByClvdepAndClvnep(clvdep, clvnep);
		return metas != null && !metas.isEmpty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.IndicadoresCapturaService#
	 * puedeRegistrarMeta(java.lang.Long, java.lang.String, java.lang.String,
	 * java.lang.Integer)
	 */
	@Override
	public boolean puedeRegistrarMeta(Long id, String clvdep, String clvnep, Integer clvmet) {
		List<PpMeta> metas = ppMetaRepository.findAllByClvdepAndClvnepAndClvmet(clvdep, clvnep, clvmet);
		if (metas != null && !metas.isEmpty()) {
			if (id == null) {
				return false;
			}

			for (PpMeta meta : metas) {
				if (meta.getId() != id.longValue()) {
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.IndicadoresCapturaService#
	 * tienePrograma(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean tienePrograma(String cvldep, String clvnep) {
		Predicate p = PpCapturaPredicates.existProgram(cvldep, clvnep);
		return ppCapturaRepository.count(p) > ZERO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.IndicadoresCapturaService#
	 * puedeRegistrarCabecero(java.math.BigInteger, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean puedeRegistrarCabecero(BigInteger id, String cvldep, String clvnep) {
		if (id == null) {
			Predicate p = PpCapturaPredicates.existProgram(cvldep, clvnep);
			return ppCapturaRepository.count(p) > ZERO;
		}
		List<PpCaptura> pCapturas = this.ppCapturaRepository.findByClvdepAndClvnep(cvldep, clvnep);
		if (pCapturas != null && !pCapturas.isEmpty()) {
			for (PpCaptura captura : pCapturas) {
				if (captura.getId() != id.longValue()) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.IndicadoresCapturaService#
	 * findAllByClvdep(java.lang.String, java.lang.String)
	 */
	@Override
	public List<IndMetaDTO> findAllByClvdep(String clvdep, String clvnep) {
		List<PpMeta> metas = ppMetaRepository.findAllByClvdepAndClvnep(clvdep, clvnep);
		List<IndMetaDTO> listaMetas = new ArrayList<IndMetaDTO>();

		for (PpMeta met : metas) {
			IndMetaDTO meta = new IndMetaDTO();
			// meta.setAmpli1(met.getAmpli1());
			// meta.setAmpli2(met.getAmpli2());
			// meta.setAmpli3(met.getAmpli3());
			// meta.setAmpli4(met.getAmpli4());
			// meta.setAmpli5(met.getAmpli5());
			meta.setAnioCap(met.getAnioCap());
			meta.setCampo0(met.getCampo0());
			meta.setCampo1(met.getCampo1());
			meta.setCampo2(met.getCampo2());
			meta.setCampo3(met.getCampo3());
			meta.setCampo4(met.getCampo4());
			meta.setCampo5(met.getCampo5());
			meta.setCanMet(met.getCanMet());
			meta.setCanMeta(met.getCanMeta());
			meta.setCanMeti(met.getCanMeti());
			meta.setCanMetia(met.getCanMetia());
			meta.setCanMetic1(met.getCanMetic1());
			meta.setCanMetic10(met.getCanMetic10());
			meta.setCanMetic11(met.getCanMetic11());
			meta.setCanMetic12(met.getCanMetic12());
			meta.setCanMetic13(met.getCanMetic13());
			meta.setCanMetic2(met.getCanMetic2());
			meta.setCanMetic3(met.getCanMetic3());
			meta.setCanMetic4(met.getCanMetic4());
			meta.setCanMetic5(met.getCanMetic5());
			meta.setCanMetic6(met.getCanMetic6());
			meta.setCanMetic7(met.getCanMetic7());
			meta.setCanMetic8(met.getCanMetic8());
			meta.setCanMetic9(met.getCanMetic9());
			meta.setCantAvan1(met.getCantAvan1());
			meta.setCantAvan10(met.getCantAvan10());
			meta.setCantAvan11(met.getCantAvan11());
			meta.setCantAvan12(met.getCantAvan12());
			meta.setCantAvan13(met.getCantAvan13());
			meta.setCantAvan2(met.getCantAvan2());
			meta.setCantAvan3(met.getCantAvan3());
			meta.setCantAvan4(met.getCantAvan4());
			meta.setCantAvan5(met.getCantAvan5());
			meta.setCantAvan6(met.getCantAvan6());
			meta.setCantAvan7(met.getCantAvan7());
			meta.setCantAvan8(met.getCantAvan8());
			meta.setCantAvan9(met.getCantAvan9());
			meta.setClavedep(met.getClavedep());
			meta.setClvdep(met.getClvdep());
			meta.setClvfuen(met.getClvfuen());
			meta.setClvmet(met.getClvmet());
			meta.setClvnep(met.getClvnep());
			meta.setClvreg(met.getClvreg());
			meta.setCosto(met.getCosto());
			meta.setFormula(met.getFormula());
			meta.setId(met.getId());
			meta.setIdRef(met.getIdRef());
			meta.setIdsector(met.getIdsector());
			meta.setNomInd(met.getNomInd());
			meta.setNomMet(met.getNomMet());
			meta.setNumVer(met.getNumVer());
			// meta.setProgante(met.getProgante());
			// meta.setRedu1(met.getRedu1());
			// meta.setRedu2(met.getRedu2());
			// meta.setRedu3(met.getRedu3());
			// meta.setRedu4(met.getRedu4());
			// meta.setRedu5(met.getRedu5());
			meta.setUniMed(met.getUniMed());
			meta.setUserid(met.getUserid());
			meta.setVarfin1(met.getVarfin1());
			meta.setVarfin2(met.getVarfin2());
			meta.setVarfin3(met.getVarfin3());
			meta.setVarfin4(met.getVarfin4());
			meta.setVarfis1(met.getVarfis1());
			meta.setVarfis2(met.getVarfis2());
			meta.setVarfis3(met.getVarfis3());
			meta.setVarfis4(met.getVarfis4());
			meta.setDimensionc(met.getDimensionc());
			listaMetas.add(meta);
		}
		return listaMetas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.IndicadoresCapturaService#
	 * saveCabecero(com.gem.sistema.business.dto.IndCapturaDTO)
	 */
	@Override
	public void saveCabecero(IndCapturaDTO dto) {

		PpCaptura p = new PpCaptura();
		if (dto.getId() != null) {
			p.setId(dto.getId().longValue());
		}
		p.setCampo0(dto.getCampo0());
		p.setCampo1(dto.getCampo1());
		p.setCampo2(dto.getCampo2());
		p.setCampo3(dto.getCampo3());
		p.setCampo4(dto.getCampo4());
		p.setCampo5(dto.getCampo5());
		p.setClavedep(dto.getClaveDep());
		p.setAnioCap(dto.getAnioCap());
		p.setNumVer(dto.getNumVer());
		p.setClvdep(dto.getClvDep());
		p.setClvfuen(dto.getClvfuen());
		p.setObjProy(dto.getObjetivos());
		p.setEstProy(dto.getEstrategias());
		p.setDescProy(dto.getDesProblemasOportunidades());
		p.setClvnep(dto.getClvnep());
		p.setUserid(dto.getUserId());
		p.setSectorid(dto.getIdsector());
		p.setClvreg("0");// pregunrar gerard y alfred donde obtener estos
							// valores hc
		p.setNumVer(0);
		p.setDimensiond(new BigDecimal(0));
		p.setDimensionc(dto.getDimensionc());
		ppCapturaRepository.save(p);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.IndicadoresCapturaService#
	 * saveMetas(com.gem.sistema.business.dto.IndMetaDTO)
	 */
	@Override
	public Boolean saveMetas(IndMetaDTO dtoMetaList) {
		PpMeta metas = dtoToEntity(dtoMetaList);
		Boolean bandera = Boolean.FALSE;
		List<PpMeta> list = (List<PpMeta>) ppMetaRepository.findAll(PpMetaPredicates.existCode(metas.getClvmet(),
				metas.getClvdep(), metas.getClvnep(), metas.getIdsector()));
		if (CollectionUtils.isEmpty(list)) {
			ppMetaRepository.save(metas);
			return Boolean.TRUE;
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "El codigo ya se encuentra registrado",
					"El codigo ya se encuentra registrado");
			return Boolean.FALSE;
		}
	}

	/**
	 * Dto to entity.
	 *
	 * @param met the met
	 * @return the pp meta
	 */
	private PpMeta dtoToEntity(IndMetaDTO met) {
		PpMeta meta = new PpMeta();
		if (met.getId() != null) {
			meta.setId(met.getId().longValue());
		}
		// meta.setId(met.getId());
		// meta.setAmpli1(met.getAmpli1());
		// meta.setAmpli2(met.getAmpli2());
		// meta.setAmpli3(met.getAmpli3());
		// meta.setAmpli4(met.getAmpli4());
		// meta.setAmpli5(met.getAmpli5());
		meta.setAnioCap(met.getAnioCap());
		meta.setCampo0(met.getCampo0());
		meta.setCampo1(met.getCampo1());
		meta.setCampo2(met.getCampo2());
		meta.setCampo3(met.getCampo3());
		meta.setCampo4(met.getCampo4());
		meta.setCampo5(met.getCampo5());
		meta.setCanMet(met.getCanMet());
		meta.setCanMeta(met.getCanMeta());
		meta.setCanMeti(met.getCanMeti());
		meta.setCanMetia(met.getCanMetia());
		meta.setCanMetic1(met.getCanMetic1());
		meta.setCanMetic10(met.getCanMetic10());
		meta.setCanMetic11(met.getCanMetic11());
		meta.setCanMetic12(met.getCanMetic12());
		meta.setCanMetic13(met.getCanMetic13());
		meta.setCanMetic2(met.getCanMetic2());
		meta.setCanMetic3(met.getCanMetic3());
		meta.setCanMetic4(met.getCanMetic4());
		meta.setCanMetic5(met.getCanMetic5());
		meta.setCanMetic6(met.getCanMetic6());
		meta.setCanMetic7(met.getCanMetic7());
		meta.setCanMetic8(met.getCanMetic8());
		meta.setCanMetic9(met.getCanMetic9());
		meta.setCantAvan1(met.getCantAvan1());
		meta.setCantAvan10(met.getCantAvan10());
		meta.setCantAvan11(met.getCantAvan11());
		meta.setCantAvan12(met.getCantAvan12());
		meta.setCantAvan13(met.getCantAvan13());
		meta.setCantAvan2(met.getCantAvan2());
		meta.setCantAvan3(met.getCantAvan3());
		meta.setCantAvan4(met.getCantAvan4());
		meta.setCantAvan5(met.getCantAvan5());
		meta.setCantAvan6(met.getCantAvan6());
		meta.setCantAvan7(met.getCantAvan7());
		meta.setCantAvan8(met.getCantAvan8());
		meta.setCantAvan9(met.getCantAvan9());
		meta.setClavedep(met.getClavedep());
		meta.setClvdep(met.getClvdep());
		meta.setClvfuen(met.getClvfuen());
		meta.setClvmet(met.getClvmet());
		meta.setClvnep(met.getClvnep());
		meta.setClvreg(met.getClvreg());
		meta.setCosto(met.getCosto());
		meta.setFormula(met.getFormula());
		meta.setIdRef(met.getIdRef());
		meta.setIdsector(met.getIdsector());
		meta.setNomInd(met.getNomInd());
		meta.setNomMet(met.getNomMet());
		meta.setNumVer(met.getNumVer());
		// meta.setProgante(met.getProgante());
		// meta.setRedu1(met.getRedu1());
		// meta.setRedu2(met.getRedu2());
		// meta.setRedu3(met.getRedu3());
		// meta.setRedu4(met.getRedu4());
		// meta.setRedu5(met.getRedu5());
		meta.setUniMed(met.getUniMed());
		meta.setUserid(met.getUserid());
		meta.setVarfin1(met.getVarfin1());
		meta.setVarfin2(met.getVarfin2());
		meta.setVarfin3(met.getVarfin3());
		meta.setVarfin4(met.getVarfin4());
		meta.setVarfis1(met.getVarfis1());
		meta.setVarfis2(met.getVarfis2());
		meta.setVarfis3(met.getVarfis3());
		meta.setVarfis4(met.getVarfis4());
		meta.setDimensionc(met.getDimensionc());
		return meta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.IndicadoresCapturaService#
	 * listaDependencias(int)
	 */
	public List<Xcatpro> listaDependencias(int sectorid) {
		return xcatproRepository.findBySectoridOrderByClvdep(sectorid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.IndicadoresCapturaService#
	 * filtraPrograma(java.lang.String)
	 */
	public List<Xcatpro> filtraPrograma(String clvDep) {
		return xcatproRepository.findByClvdep(clvDep);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.IndicadoresCapturaService#
	 * desripcionDep(java.lang.String)
	 */
	public String desripcionDep(String clvDep) {
		List<Catdep> listaDesc = catdepRepository.findByClvdep(clvDep);
		if (listaDesc.isEmpty()) {
			return StringUtils.EMPTY;
		} else {
			return listaDesc.get(0).getNomdep();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.IndicadoresCapturaService#
	 * llenaDescPrograma(java.lang.String)
	 */
	public String llenaDescPrograma(String clvProgram) {
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
	 * @see com.gem.sistema.business.service.indicadores.IndicadoresCapturaService#
	 * deleteCabecero(java.lang.Long)
	 */
	@Override
	public void deleteCabecero(Long id) {
		ppCapturaRepository.deleteById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.IndicadoresCapturaService#
	 * deleteMeta(java.lang.Long)
	 */
	@Override
	public void deleteMeta(Long id) {
		System.out.println("deleteMeta: " + id);
		ppMetaRepository.deleteById(id);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.indicadores.IndicadoresCapturaService#
	 * updateMetas(com.gem.sistema.business.dto.IndMetaDTO)
	 */
	@Override
	public Boolean updateMetas(IndMetaDTO indMetaDTO) {
		PpMeta metas = dtoToEntity(indMetaDTO);
		ppMetaRepository.save(metas);
		return Boolean.TRUE;

	}

}
