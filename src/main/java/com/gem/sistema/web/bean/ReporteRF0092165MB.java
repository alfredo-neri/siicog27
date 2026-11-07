package com.gem.sistema.web.bean;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;
import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.dto.TotalMesesDTO;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;
import com.gem.sistema.business.service.catalogos.GenerateTotaleService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.util.ConstantsClaveEnnum;

import static com.roonin.utils.UtilDate.getDateOfSystem;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteRF0092165MB.
 */
@ManagedBean(name = "reporteRF0092165MB")
@ViewScoped
public class ReporteRF0092165MB extends BaseDirectReport {
	
	/** The catdep. */
	private Catdep catdep;

	/** The x catpro. */
	private Xcatpro xCatpro;

	/** The clv dep. */
	private String clvDep;
	
	/** The clv pro. */
	private String clvPro;

	/** The conctb. */
	private Conctb conctb;

	/**
	 * Gets the clv dep.
	 *
	 * @return the clv dep
	 */
	public String getClvDep() {
		return clvDep;
	}

	/**
	 * Sets the clv dep.
	 *
	 * @param clvDep the new clv dep
	 */
	public void setClvDep(String clvDep) {
		this.clvDep = clvDep;
	}

	/**
	 * Gets the clv pro.
	 *
	 * @return the clv pro
	 */
	public String getClvPro() {
		return clvPro;
	}

	/**
	 * Sets the clv pro.
	 *
	 * @param clvPro the new clv pro
	 */
	public void setClvPro(String clvPro) {
		this.clvPro = clvPro;
	}


	/** The list catdep. */
	private List<Catdep> listCatdep;

	/** The list xcatpro. */
	private List<Xcatpro> listXcatpro;

	/** The catdep repository. */
	@ManagedProperty("#{catdepRepository}")
	private CatdepRepository catdepRepository;

	/** The xcatpro repository. */
	@ManagedProperty("#{xcatproRepository}")
	private XcatproRepository xcatproRepository;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The generate totale service. */
	@ManagedProperty("#{generateTotaleService}")
	private GenerateTotaleService generateTotaleService;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	/**
	 * Gets the catdep.
	 *
	 * @return the catdep
	 */
	public Catdep getCatdep() {
		return catdep;
	}

	/**
	 * Sets the catdep.
	 *
	 * @param catdep the new catdep
	 */
	public void setCatdep(Catdep catdep) {
		this.catdep = catdep;
	}

	/**
	 * Gets the x catpro.
	 *
	 * @return the x catpro
	 */
	public Xcatpro getxCatpro() {
		return xCatpro;
	}

	/**
	 * Sets the x catpro.
	 *
	 * @param xCatpro the new x catpro
	 */
	public void setxCatpro(Xcatpro xCatpro) {
		this.xCatpro = xCatpro;
	}

	/**
	 * Gets the list catdep.
	 *
	 * @return the list catdep
	 */
	public List<Catdep> getListCatdep() {
		return listCatdep;
	}

	/**
	 * Sets the list catdep.
	 *
	 * @param listCatdep the new list catdep
	 */
	public void setListCatdep(List<Catdep> listCatdep) {
		this.listCatdep = listCatdep;
	}

	/**
	 * Gets the list xcatpro.
	 *
	 * @return the list xcatpro
	 */
	public List<Xcatpro> getListXcatpro() {
		return listXcatpro;
	}

	/**
	 * Sets the list xcatpro.
	 *
	 * @param listXcatpro the new list xcatpro
	 */
	public void setListXcatpro(List<Xcatpro> listXcatpro) {
		this.listXcatpro = listXcatpro;
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
	 * Gets the generate totale service.
	 *
	 * @return the generate totale service
	 */
	public GenerateTotaleService getGenerateTotaleService() {
		return generateTotaleService;
	}

	/**
	 * Sets the generate totale service.
	 *
	 * @param generateTotaleService the new generate totale service
	 */
	public void setGenerateTotaleService(GenerateTotaleService generateTotaleService) {
		this.generateTotaleService = generateTotaleService;
	}

	/**
	 * Gets the conctb.
	 *
	 * @return the conctb
	 */
	public Conctb getConctb() {
		return conctb;
	}

	/**
	 * Sets the conctb.
	 *
	 * @param conctb the new conctb
	 */
	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
	}

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		this.jasperReporteName = "reporte_RF0092165";
		this.endFilename = jasperReporteName + ".pdf";
		listCatdep = catdepRepository.findAllByIdSectorCrossXcatpro(this.getUserDetails().getIdSector());

		if (!listCatdep.isEmpty()) {
			clvDep = this.listCatdep.get(0).getClvdep();
		}
		listXcatpro = xcatproRepository.getClave(this.listCatdep.get(0).getClvdep(),
				this.getUserDetails().getIdSector());
		if (!listXcatpro.isEmpty()) {
			clvPro = this.listXcatpro.get(0).getClvpro();
		}
	}

	/** The totales. */
	TotalMesesDTO totales;

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		totales = generateTotaleService.generateTotales(this.getUserDetails().getIdSector(),	
				clvDep.subSequence(0, 10).toString(), clvPro.substring(0, 15));
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = new TrPuestoFirma();
		
		parameters.put("dependencia", clvDep.subSequence(0, 10));
		parameters.put("programa", clvPro.substring(0, 15));
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F27.getValue());
		parameters.put("L1", firma.getPuesto().getPuesto());
		parameters.put("presidente", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F05.getValue());
		parameters.put("L2", firma.getPuesto().getPuesto());
		parameters.put("secretario", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, ConstantsClaveEnnum.CLAVE_F03.getValue());
		parameters.put("L3", firma.getPuesto().getPuesto());
		parameters.put("tesorero", firma.getNombre());
		parameters.put("pathImg", idSector==1? conctb.getImagePathRigth(): conctb.getImagePathLeft());
		parameters.put("idSector", idSector);
		parameters.put("anio", conctb.getAnoemp());
		parameters.put("descripcion", clvPro);
		parameters.put("municipio", conctb.getNomDep());
		parameters.put("dependenciaDescripcion", clvDep);
		parameters.put("enero", totales.getEnero());
		parameters.put("febrero", totales.getFebrero());
		parameters.put("marzo", totales.getMarzo());
		parameters.put("abril", totales.getAbril());
		parameters.put("mayo", totales.getMayo());
		parameters.put("junio", totales.getJunio());
		parameters.put("julio", totales.getJulio());
		parameters.put("agosto", totales.getAgosto());
		parameters.put("septiembre", totales.getSeptiembre());
		parameters.put("octubre", totales.getOctubre());
		parameters.put("noviembre", totales.getNoviembre());
		parameters.put("diciembre", totales.getDiciembre());
		parameters.put("tTotal", totales.getTotal());
		parameters.put("fecha", getDateOfSystem());
		return parameters;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * On change by clve dep.
	 */
	public void onChangeByClveDep() {
		listXcatpro = xcatproRepository.getClave(clvDep.substring(0, 10), this.getUserDetails().getIdSector());
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}
	

}
