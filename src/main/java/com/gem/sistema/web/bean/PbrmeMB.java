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
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class PbrmeMB.
 */
@ManagedBean
@ViewScoped
public class PbrmeMB extends BaseDirectReport {

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct PbRMe ");
		jasperReporteName = "PbRM01e";
		endFilename = jasperReporteName + ".pdf";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		TrPuestoFirma firma = new TrPuestoFirma();
		paramsReport.put("pIdSector", this.getUserDetails().getIdSector());
		paramsReport.put("pImagenPath", this.getUserDetails().getPathImgCab1());
		paramsReport.put("pAnio", conctb.getAnoemp());
		
		switch (conctb.getClave().substring(0, 1)) {
	      case "0": 
	        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
	            ConstantsClaveEnnum.CLAVE_F04.getValue());
	        paramsReport.put("firmaP1", firma.getPuesto().getPuesto());
	        paramsReport.put("firmaN1", firma.getNombre());
	        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
	            ConstantsClaveEnnum.CLAVE_F05.getValue());
	        paramsReport.put("firmaP2", firma.getPuesto().getPuesto());
	        paramsReport.put("firmaN2", firma.getNombre());
	        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
	            ConstantsClaveEnnum.CLAVE_F40.getValue());
	        paramsReport.put("firmaP3", firma.getPuesto().getPuesto());
	        paramsReport.put("firmaN3", firma.getNombre());
	        break;
	      case "2": // ODAS 
	    	  firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
	  	            ConstantsClaveEnnum.CLAVE_F04.getValue());
	  	        paramsReport.put("firmaP1", firma.getPuesto().getPuesto());
	  	        paramsReport.put("firmaN1", firma.getNombre());
	  	        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
	  	            ConstantsClaveEnnum.CLAVE_F05.getValue());
	  	        paramsReport.put("firmaP2", firma.getPuesto().getPuesto());
	  	        paramsReport.put("firmaN2", firma.getNombre());
	  	        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
	  	            ConstantsClaveEnnum.CLAVE_F40.getValue());
	  	        paramsReport.put("firmaP3", firma.getPuesto().getPuesto());
	  	        paramsReport.put("firmaN3", firma.getNombre());
	        break;
	      case "3": 
	    	  firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
		  	            ConstantsClaveEnnum.CLAVE_F04.getValue());
		  	        paramsReport.put("firmaP1", firma.getPuesto().getPuesto());
		  	        paramsReport.put("firmaN1", firma.getNombre());
		  	        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
		  	            ConstantsClaveEnnum.CLAVE_F05.getValue());
		  	        paramsReport.put("firmaP2", firma.getPuesto().getPuesto());
		  	        paramsReport.put("firmaN2", firma.getNombre());
		  	        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
		  	            ConstantsClaveEnnum.CLAVE_F40.getValue());
		  	        paramsReport.put("firmaP3", firma.getPuesto().getPuesto());
		  	        paramsReport.put("firmaN3", firma.getNombre());
	        break;
	      case "4": 
	    	  firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
		  	            ConstantsClaveEnnum.CLAVE_F04.getValue());
		  	        paramsReport.put("firmaP1", firma.getPuesto().getPuesto());
		  	        paramsReport.put("firmaN1", firma.getNombre());
		  	        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
		  	            ConstantsClaveEnnum.CLAVE_F05.getValue());
		  	        paramsReport.put("firmaP2", firma.getPuesto().getPuesto());
		  	        paramsReport.put("firmaN2", firma.getNombre());
		  	        firma = puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L,
		  	            ConstantsClaveEnnum.CLAVE_F40.getValue());
		  	        paramsReport.put("firmaP3", firma.getPuesto().getPuesto());
		  	        paramsReport.put("firmaN3", firma.getNombre());
	        break;
	      }
		
		return paramsReport;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
		/*
		 * Map<String, Object> paramsQuery = new java.util.HashMap<String, Object>();
		 * paramsQuery.put("ID_REF", new Integer(0)); //FALTA return
		 * reporteadorDirectoImpl.getFileReport(tcReporte,paramsQuery,
		 * reporteName,type);
		 */
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
