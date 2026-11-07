package com.gem.sistema.web.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gem.sistema.business.domain.Catdaa;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.CatdaaRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.util.ConstantsClaveEnnum;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections4.CollectionUtils;

import org.primefaces.model.StreamedContent;

// TODO: Auto-generated Javadoc
/**
 * The Class RF0092163MB.
 */
@ManagedBean(name = "rF0092163MB")
@ViewScoped
public class RF0092163MB extends BaseDirectReport {

	/** The clave dep aux. */
	private String claveDepAux;

	/** The list catdaa. */
	private List<Catdaa> listCatdaa;

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The catdaa repository. */
	@ManagedProperty("#{catdaaRepository}")
	private CatdaaRepository catdaaRepository;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;
	
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	/**
	 * Gets the clave dep aux.
	 *
	 * @return the clave dep aux
	 */
	public String getClaveDepAux() {
		return claveDepAux;
	}

	/**
	 * Sets the clave dep aux.
	 *
	 * @param claveDepAux the new clave dep aux
	 */
	public void setClaveDepAux(String claveDepAux) {
		this.claveDepAux = claveDepAux;
	}

	/**
	 * Gets the list catdaa.
	 *
	 * @return the list catdaa
	 */
	public List<Catdaa> getListCatdaa() {
		return listCatdaa;
	}

	/**
	 * Sets the list catdaa.
	 *
	 * @param listCatdaa the new list catdaa
	 */
	public void setListCatdaa(List<Catdaa> listCatdaa) {
		this.listCatdaa = listCatdaa;
	}

	/**
	 * Gets the firmas repository.
	 *
	 * @return the firmas repository
	 */
	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	/**
	 * Sets the firmas repository.
	 *
	 * @param firmasRepository the new firmas repository
	 */
	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	/**
	 * Gets the catdaa repository.
	 *
	 * @return the catdaa repository
	 */
	public CatdaaRepository getCatdaaRepository() {
		return catdaaRepository;
	}

	/**
	 * Sets the catdaa repository.
	 *
	 * @param catdaaRepository the new catdaa repository
	 */
	public void setCatdaaRepository(CatdaaRepository catdaaRepository) {
		this.catdaaRepository = catdaaRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Integer idSector = this.getUserDetails().getIdSector();
		Map<String, Object> parameters = new HashMap<String, Object>();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = new TrPuestoFirma();
		
		
		parameters.put("anio", conctb.getAnoemp().toString());
		parameters.put("idSector", this.getUserDetails().getIdSector());
		parameters.put("dependencia", claveDepAux);
		parameters.put("municipio", this.getUserDetails().getMunicipio());
		parameters.put("imagePath", this.getUserDetails().getPathImgCab1());

		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
				ConstantsClaveEnnum.CLAVE_F27.getValue());
		parameters.put("firmaP1", firma.getPuesto().getPuesto());
		parameters.put("firmaN1", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
				ConstantsClaveEnnum.CLAVE_F05.getValue());
		parameters.put("firmaP2", firma.getPuesto().getPuesto());
		parameters.put("firmaN2", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
				ConstantsClaveEnnum.CLAVE_F03.getValue());
		parameters.put("firmaP3", firma.getPuesto().getPuesto());
		parameters.put("firmaN3", firma.getNombre());
		
		return parameters;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {

		return null;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		jasperReporteName = "RF009_2_1_6_3";
		endFilename = jasperReporteName + ".pdf";

		listCatdaa = catdaaRepository.findAllByOrderByClave();

		if (!CollectionUtils.isEmpty(listCatdaa)) {
			claveDepAux = listCatdaa.get(0).getClave();
		}

	}

}
