package com.gem.sistema.web.bean;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.service.catalogos.Pbrm08DetailService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class MPbrm08bMB.
 */
@ManagedBean
@ViewScoped
public class MPbrm08bMB extends BaseDirectReport {
	
	/** The p trim. */
	private String pTrim;
	

	/** The pbrm 08 detail service. */
	@ManagedProperty("#{pbrm08DetailService}")
	private Pbrm08DetailService pbrm08DetailService;
	
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	/**
	 * Gets the pbrm 08 detail service.
	 *
	 * @return the pbrm 08 detail service
	 */
	public Pbrm08DetailService getPbrm08DetailService() {
		return pbrm08DetailService;
	}

	/**
	 * Sets the pbrm 08 detail service.
	 *
	 * @param pbrm08DetailService the new pbrm 08 detail service
	 */
	public void setPbrm08DetailService(Pbrm08DetailService pbrm08DetailService) {
		this.pbrm08DetailService = pbrm08DetailService;
	}


	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct MPbrm08bMB ");
		// reportId = 2;
		// tcReporte = reportesRepository.findOne(reportId);
		jasperReporteName = "Pbrm08b";
		endFilename = jasperReporteName + ".pdf";
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TrPuestoFirma firma = new TrPuestoFirma();
		paramsReport.put("pSsql1",
				pbrm08DetailService.executeQueryHead(idSector, Integer.valueOf(pTrim)));
		paramsReport.put("pSsql2",
				pbrm08DetailService.executeQueryDetail(idSector, Integer.valueOf(pTrim)));
		paramsReport.put("pImg1", this.getUserDetails().getPathImgCab1());
		paramsReport.put("pImg2", this.getUserDetails().getPathImgCab2());
		paramsReport.put("pAn", conctb.getAnoemp());
		paramsReport.put("pIdsector", idSector);
		paramsReport.put("pTrim", Integer.valueOf(pTrim));
		
		switch (conctb.getClave().substring(0, 1)) {
		case "0":
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
					ConstantsClaveEnnum.CLAVE_F04.getValue());
			paramsReport.put("pL4", firma.getPuesto().getPuesto());
			paramsReport.put("pN4", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
					ConstantsClaveEnnum.CLAVE_F40.getValue());
			paramsReport.put("pL5", firma.getPuesto().getPuesto());
			paramsReport.put("pN5", firma.getNombre());
			
			break;
		case "2":
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
					ConstantsClaveEnnum.CLAVE_F04.getValue());
			paramsReport.put("pL4", firma.getPuesto().getPuesto());
			paramsReport.put("pN4", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
					ConstantsClaveEnnum.CLAVE_F40.getValue());
			paramsReport.put("pL5", firma.getPuesto().getPuesto());
			paramsReport.put("pN5", firma.getNombre());
			break;
		case "3":
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
					ConstantsClaveEnnum.CLAVE_F04.getValue());
			paramsReport.put("pL4", firma.getPuesto().getPuesto());
			paramsReport.put("pN4", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
					ConstantsClaveEnnum.CLAVE_F40.getValue());
			paramsReport.put("pL5", firma.getPuesto().getPuesto());
			paramsReport.put("pN5", firma.getNombre());
			break;
		case "4":
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
					ConstantsClaveEnnum.CLAVE_F04.getValue());
			paramsReport.put("pL4", firma.getPuesto().getPuesto());
			paramsReport.put("pN4", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
					ConstantsClaveEnnum.CLAVE_F40.getValue());
			paramsReport.put("pL5", firma.getPuesto().getPuesto());
			paramsReport.put("pN5", firma.getNombre());
			break;
		}
		
		return paramsReport;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
		/*
		 * Map<String, Object> paramsQuery = new java.util.HashMap<String,
		 * Object>(); paramsQuery.put("ID_REF", new Integer(0)); //FALTA return
		 * reporteadorDirectoImpl.getFileReport(tcReporte,paramsQuery,
		 * reporteName,type);
		 */
	}

	/**
	 * Gets the p trim.
	 *
	 * @return the p trim
	 */
	public String getpTrim() {
		return pTrim;
	}

	/**
	 * Sets the p trim.
	 *
	 * @param pTrim the new p trim
	 */
	public void setpTrim(String pTrim) {
		this.pTrim = pTrim;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}
	
	
}
