package com.gem.sistema.business.service.metas;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Fuentef;
import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.domain.PpCapp;
import com.gem.sistema.business.domain.PpMett;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.dto.MetasFisicasDTO;
import com.gem.sistema.business.dto.MetasFisicasDetalleDTO;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.FuentefRepository;
import com.gem.sistema.business.repository.catalogs.MuniNepRepository;
import com.gem.sistema.business.repository.catalogs.PpMettRepository;
import com.gem.sistema.business.repository.catalogs.PpcappRepository;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class MetasFisicasServiceImpl.
 */
@Service(value = "metasFisicasService")
public class MetasFisicasServiceImpl implements MetasFisicasService {

	/** The ppcapp repository. */
	@Autowired
	PpcappRepository ppcappRepository;

	/** The xcatpro repository. */
	@Autowired
	XcatproRepository xcatproRepository;

	/** The pp mett repository. */
	@Autowired
	PpMettRepository ppMettRepository;

	/** The catdep repository. */
	@Autowired
	CatdepRepository catdepRepository;

	/** The muni nep repository. */
	@Autowired
	MuniNepRepository muniNepRepository;

	/** The fuentef repository. */
	@Autowired
	FuentefRepository fuentefRepository;

//	private static final Logger LOGGER = LoggerFactory.getLogger(MetasFisicasServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.metas.MetasFisicasService#listaDependencias(
	 * java.lang.Integer)
	 */
	public List<Xcatpro> listaDependencias(Integer sector) {
		return xcatproRepository.findBySectorid(sector);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.metas.MetasFisicasService#filtraPrograma(
	 * java.lang.String)
	 */
	public List<Xcatpro> filtraPrograma(String clvDep) {
		return xcatproRepository.findByClvdep(clvDep);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.metas.MetasFisicasService#filtraFuente(java.
	 * lang.String, java.lang.String)
	 */
	public List<Xcatpro> filtraFuente(String clvDep, String clvFun) {
		return xcatproRepository.findByClvdepAndClvfun(clvDep, clvFun);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.metas.MetasFisicasService#desripcionDep(java
	 * .lang.String)
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
	 * @see
	 * com.gem.sistema.business.service.metas.MetasFisicasService#llenaDescPrograma(
	 * java.lang.String)
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
	 * @see
	 * com.gem.sistema.business.service.metas.MetasFisicasService#llenaDescFuente(
	 * java.lang.String, java.lang.Integer)
	 */
	public String llenaDescFuente(String clvFuen, Integer idSector) {
		List<Fuentef> listafuen = fuentefRepository.findByLigaAndIdsector(clvFuen, idSector);
		if (listafuen.isEmpty()) {
			return StringUtils.EMPTY;
		} else {
			return listafuen.get(0).getNombref();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.metas.MetasFisicasService#llenaDescNompro(
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	public String llenaDescNompro(String clvDep, String clvFun, String clvfin) {
		List<Xcatpro> listaXcat = xcatproRepository.findByClvdepAndClvfunAndClvfin(clvDep, clvFun, clvfin);
		if (listaXcat.isEmpty()) {
			return StringUtils.EMPTY;
		} else {
			return listaXcat.get(0).getNompro();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.metas.MetasFisicasService#
	 * llenaDescNomproNewReq(java.lang.String)
	 */
	public String llenaDescNomproNewReq(String clvDep) {
		List<Catdep> listaCatDep = catdepRepository.findByClvdep(clvDep);
		if (listaCatDep.isEmpty()) {
			return StringUtils.EMPTY;
		} else {
			return listaCatDep.get(0).getNomdep();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.metas.MetasFisicasService#getEncabezados(
	 * java.lang.Integer)
	 */
	@Override
	public List<MetasFisicasDTO> getEncabezados(Integer idSector, String dependencia, String programa) {

		List<MetasFisicasDTO> metas = new ArrayList<MetasFisicasDTO>();
		List<Object> metasObj = null;

		if (StringUtils.isNotEmpty(programa) && StringUtils.isNotEmpty(dependencia)) {
			metasObj = ppcappRepository.findMetas(idSector, dependencia, programa);
		} else if (StringUtils.isEmpty(programa) && StringUtils.isNotEmpty(dependencia)) {
			metasObj = ppcappRepository.findMetasByDependencia(idSector, dependencia);
		} else if (StringUtils.isNotEmpty(programa) && StringUtils.isEmpty(dependencia)) {
			metasObj = ppcappRepository.findMetasByPrograma(idSector, programa);
		} else {
			metasObj = ppcappRepository.findMetas(idSector);
		}

		for (Object o : metasObj) {
			Object obj[] = (Object[]) o;

			MetasFisicasDTO dto = new MetasFisicasDTO();

			dto.setId((BigInteger) obj[0]);
			dto.setClvDep((String) obj[1]);
			// dto.setNompro((String) obj[2]);
			dto.setNompro(llenaDescNomproNewReq(dto.getClvDep()));
			dto.setClvnep((String) obj[3]);
//			dto.setDesProyecto((String) obj[4]);
			dto.setDesProyecto(llenaDescPrograma(dto.getClvnep()));
			dto.setClvfuen((String) obj[5]);
			dto.setDesfuen((String) obj[6]);
			dto.setObjetivos((String) obj[7]);

			// llenando detalle
			List<MetasFisicasDetalleDTO> detalle = new ArrayList<MetasFisicasDetalleDTO>();
			List<Object> metasDetalleObj = ppcappRepository.findDetalleMetas(dto.getClvDep(), dto.getClvnep(),
					dto.getClvfuen());
			for (Object o2 : metasDetalleObj) {
				Object obj2[] = (Object[]) o2;
				MetasFisicasDetalleDTO det = new MetasFisicasDetalleDTO();
				det.setId((BigInteger) obj2[0]);
				det.setClvmet((Integer) obj2[1]);
				det.setUniMed((String) obj2[2]);
				det.setProgante((BigDecimal) obj2[3]);
				det.setCanMetia((BigDecimal) obj2[4]);
				det.setCanMeti((BigDecimal) obj2[5]);
				det.setNomInd((String) obj2[6]);
				det.setCanMetic1((BigDecimal) obj2[7]);
				det.setCanMetic2((BigDecimal) obj2[8]);
				det.setCanMetic3((BigDecimal) obj2[9]);
				det.setCanMetic4((BigDecimal) obj2[10]);
				det.setAmpli1((BigDecimal) obj2[11]);
				det.setRedu1((BigDecimal) obj2[12]);
				det.setCantAvan1((BigDecimal) obj2[13]);
				det.setVarfin1((String) obj2[14]);
				det.setAmpli2((BigDecimal) obj2[15]);
				det.setRedu2((BigDecimal) obj2[16]);
				det.setCantAvan2((BigDecimal) obj2[17]);
				det.setVarfin2((String) obj2[18]);
				det.setAmpli3((BigDecimal) obj2[19]);
				det.setRedu3((BigDecimal) obj2[20]);
				det.setCantAvan3((BigDecimal) obj2[21]);
				det.setVarfin3((String) obj2[22]);
				det.setAmpli4((BigDecimal) obj2[23]);
				det.setRedu4((BigDecimal) obj2[24]);
				det.setCantAvan4((BigDecimal) obj2[25]);
				det.setVarfin4((String) obj2[26]);
				detalle.add(det);
			}
			dto.setDetalle(detalle);

			metas.add(dto);
		}

		return metas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.metas.MetasFisicasService#
	 * findByClvdepClvNepClvFuen(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public MetasFisicasDTO findByClvdepClvNepClvFuen(String clvdep, String clvnep, String clvfuen) {

		List<Object> metasObj = ppcappRepository.findByClvdepClvnepClvfuen(clvdep, clvnep, clvfuen);

		if (metasObj == null || metasObj.size() < 1) {
			return null;
		} else {
			Object obj[] = (Object[]) metasObj.get(0);

			MetasFisicasDTO dto = new MetasFisicasDTO();

			dto.setId((BigInteger) obj[0]);
			dto.setClvDep((String) obj[1]);
			dto.setNompro((String) obj[2]);
			dto.setClvnep((String) obj[3]);
			dto.setDesProyecto((String) obj[4]);
			dto.setClvfuen((String) obj[5]);
			dto.setDesfuen((String) obj[6]);
			dto.setObjetivos((String) obj[7]);

			// llenando detalle
			List<MetasFisicasDetalleDTO> detalle = new ArrayList<MetasFisicasDetalleDTO>();
			List<Object> metasDetalleObj = ppcappRepository.findDetalleMetas(dto.getClvDep(), dto.getClvnep(),
					dto.getClvfuen());
			for (Object o2 : metasDetalleObj) {
				Object obj2[] = (Object[]) o2;
				MetasFisicasDetalleDTO det = new MetasFisicasDetalleDTO();
				det.setId((BigInteger) obj2[0]);
				det.setClvmet((Integer) obj2[1]);
				det.setUniMed((String) obj2[2]);
				det.setProgante((BigDecimal) obj2[3]);
				det.setCanMetia((BigDecimal) obj2[4]);
				det.setCanMeti((BigDecimal) obj2[5]);
				det.setNomInd((String) obj2[6]);
				det.setCanMetic1((BigDecimal) obj2[7]);
				det.setCanMetic2((BigDecimal) obj2[8]);
				det.setCanMetic3((BigDecimal) obj2[9]);
				det.setCanMetic4((BigDecimal) obj2[10]);
				det.setAmpli1((BigDecimal) obj2[11]);
				det.setRedu1((BigDecimal) obj2[12]);
				det.setCantAvan1((BigDecimal) obj2[13]);
				det.setVarfin1((String) obj2[14]);
				det.setAmpli2((BigDecimal) obj2[15]);
				det.setRedu2((BigDecimal) obj2[16]);
				det.setCantAvan2((BigDecimal) obj2[17]);
				det.setVarfin2((String) obj2[18]);
				det.setAmpli3((BigDecimal) obj2[19]);
				det.setRedu3((BigDecimal) obj2[20]);
				det.setCantAvan3((BigDecimal) obj2[21]);
				det.setVarfin3((String) obj2[22]);
				det.setAmpli4((BigDecimal) obj2[23]);
				det.setRedu4((BigDecimal) obj2[24]);
				det.setCantAvan4((BigDecimal) obj2[25]);
				det.setVarfin4((String) obj2[26]);
				detalle.add(det);
			}
			dto.setDetalle(detalle);

			return dto;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.business.service.metas.MetasFisicasService#
	 * findByClvdepClvNepClvFuenClvMet(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.Integer)
	 */
	@Override
	public MetasFisicasDetalleDTO findByClvdepClvNepClvFuenClvMet(String clvDep, String clvNep, String clvFuen,
			Integer clvMet) {
		List<Object> metasDetalleObj = ppcappRepository.findDetalleMetasByClvMet(clvDep, clvNep, clvFuen, clvMet);

		if (metasDetalleObj == null || metasDetalleObj.size() < 1) {
			return null;
		} else {
			Object obj2[] = (Object[]) metasDetalleObj.get(0);
			MetasFisicasDetalleDTO det = new MetasFisicasDetalleDTO();
			det.setId((BigInteger) obj2[0]);
			det.setClvmet((Integer) obj2[1]);
			det.setUniMed((String) obj2[2]);
			det.setProgante((BigDecimal) obj2[3]);
			det.setCanMetia((BigDecimal) obj2[4]);
			det.setCanMeti((BigDecimal) obj2[5]);
			det.setNomInd((String) obj2[6]);
			det.setCanMetic1((BigDecimal) obj2[7]);
			det.setCanMetic2((BigDecimal) obj2[8]);
			det.setCanMetic3((BigDecimal) obj2[9]);
			det.setCanMetic4((BigDecimal) obj2[10]);
			det.setAmpli1((BigDecimal) obj2[11]);
			det.setRedu1((BigDecimal) obj2[12]);
			det.setCantAvan1((BigDecimal) obj2[13]);
			det.setVarfin1((String) obj2[14]);
			det.setAmpli2((BigDecimal) obj2[15]);
			det.setRedu2((BigDecimal) obj2[16]);
			det.setCantAvan2((BigDecimal) obj2[17]);
			det.setVarfin2((String) obj2[18]);
			det.setAmpli3((BigDecimal) obj2[19]);
			det.setRedu3((BigDecimal) obj2[20]);
			det.setCantAvan3((BigDecimal) obj2[21]);
			det.setVarfin3((String) obj2[22]);
			det.setAmpli4((BigDecimal) obj2[23]);
			det.setRedu4((BigDecimal) obj2[24]);
			det.setCantAvan4((BigDecimal) obj2[25]);
			det.setVarfin4((String) obj2[26]);

			return det;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.metas.MetasFisicasService#saveMetas(com.gem.
	 * sistema.business.dto.MetasFisicasDetalleDTO)
	 */
	@Override
	public MetasFisicasDetalleDTO saveMetas(MetasFisicasDetalleDTO dtoMetaList) {
		PpMett metas = dtoToEntity(dtoMetaList, null);
		metas = ppMettRepository.save(metas);
		dtoMetaList.setId(BigInteger.valueOf(metas.getId()));
		return dtoMetaList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.metas.MetasFisicasService#deleteMeta(java.
	 * lang.Long)
	 */
	@Override
	public void deleteMeta(Long id) {
		ppMettRepository.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.metas.MetasFisicasService#saveCabecero(com.
	 * gem.sistema.business.dto.MetasFisicasDTO)
	 */
	@Override
	public MetasFisicasDTO saveCabecero(MetasFisicasDTO dto) {

		PpCapp p = new PpCapp();

		if (dto.getId() != null) {
			p.setId(dto.getId().longValue());
		}

		p.setClavedep(dto.getClaveDep());
		p.setAnioCap(dto.getAnioCap());
		p.setNumVer(dto.getNumVer());
		p.setClvdep(dto.getClvDep());
		p.setClvnep(dto.getClvnep());
		p.setClvfuen(dto.getClvfuen());
		p.setObjProy(dto.getObjetivos());
		p.setUserid(dto.getUserId());
		p.setIdsector(dto.getIdsector());
		p = ppcappRepository.save(p);
		dto.setId(BigInteger.valueOf(p.getId()));

		return dto;
	}

	/**
	 * Dto to entity.
	 *
	 * @param met    the met
	 * @param entity the entity
	 * @return the pp mett
	 */
	private PpMett dtoToEntity(MetasFisicasDetalleDTO met, PpMett entity) {
		PpMett meta = null;
		if (entity == null) {
			meta = new PpMett();
		} else {
			meta = entity;
		}
		if (met.getId() != null)
			meta.setId(met.getId().longValue());
		meta.setClvdep(met.getClvdep());
		meta.setClvnep(met.getClvnep());
		meta.setClvfuen(met.getClvfuen());
		meta.setClvmet(met.getClvmet());
		meta.setUniMed(met.getUniMed());
		meta.setProgante(met.getProgante());
		meta.setCanMetia(met.getCanMetia());
		meta.setCanMeti(met.getCanMeti());
		meta.setNomInd(met.getNomInd());
		meta.setCanMetic1(met.getCanMetic1());
		meta.setCanMetic2(met.getCanMetic2());
		meta.setCanMetic3(met.getCanMetic3());
		meta.setCanMetic4(met.getCanMetic4());
		meta.setAmpli1(met.getAmpli1());
		meta.setAmpli2(met.getAmpli2());
		meta.setAmpli3(met.getAmpli3());
		meta.setAmpli4(met.getAmpli4());
		meta.setRedu1(met.getRedu1());
		meta.setRedu2(met.getRedu2());
		meta.setRedu3(met.getRedu3());
		meta.setRedu4(met.getRedu4());
		meta.setCantAvan1(met.getCantAvan1());
		meta.setCantAvan2(met.getCantAvan2());
		meta.setCantAvan3(met.getCantAvan3());
		meta.setCantAvan4(met.getCantAvan4());
		meta.setVarfin1(met.getVarfin1());
		meta.setVarfin2(met.getVarfin2());
		meta.setVarfin3(met.getVarfin3());
		meta.setVarfin4(met.getVarfin4());
		meta.setUserid(met.getUserid());
		meta.setIdsector(met.getIdsector());

		return meta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.business.service.metas.MetasFisicasService#deleteCabecero(
	 * java.lang.Long)
	 */
	@Override
	public void deleteCabecero(Long id) {
		ppcappRepository.delete(id);
	}

	/**
	 * public XcatproRepository getXcatproRepository() { return xcatproRepository; }
	 * 
	 * public void setXcatproRepository(XcatproRepository xcatproRepository) {
	 * this.xcatproRepository = xcatproRepository; }
	 * 
	 * public PpMettRepository getPpMettRepository() { return ppMettRepository; }
	 * 
	 * public void setPpMettRepository(PpMettRepository ppMettRepository) {
	 * this.ppMettRepository = ppMettRepository; }
	 * 
	 * public PpCapturaRepository getPpCapturaRepository() { return
	 * ppCapturaRepository; }
	 * 
	 * public void setPpCapturaRepository(PpCapturaRepository ppCapturaRepository) {
	 * this.ppCapturaRepository = ppCapturaRepository; }
	 * 
	 * 
	 * 
	 * public CatdepRepository getCatdepRepository() { return catdepRepository; }
	 * 
	 * public void setCatdepRepository(CatdepRepository catdepRepository) {
	 * this.catdepRepository = catdepRepository; }
	 * 
	 * public MuniNepRepository getMuniNepRepository() { return muniNepRepository; }
	 * 
	 * public void setMuniNepRepository(MuniNepRepository muniNepRepository) {
	 * this.muniNepRepository = muniNepRepository; }
	 * 
	 * public PpcappRepository getPpcappRepository() { return ppcappRepository; }
	 * 
	 * public void setPpcappRepository(PpcappRepository ppcappRepository) {
	 * this.ppcappRepository = ppcappRepository; }
	 **/
}
