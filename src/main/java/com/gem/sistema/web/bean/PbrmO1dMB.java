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
 * The Class PbrmO1dMB.
 */
@ManagedBean
@ViewScoped
public class PbrmO1dMB extends BaseDirectReport {
	
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct PbrmO1dMB ");
		//reportId = 2;
		//tcReporte = reportesRepository.findOne(reportId);
		jasperReporteName = "PbRM_01d";
		endFilename = jasperReporteName + ".pdf";
	}
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		TrPuestoFirma firma = new TrPuestoFirma();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		
		paramsReport.put("anio",conctb.getAnoemp());
		paramsReport.put("imagen", conctb.getImagePathLeft());
		
		switch (conctb.getClave().substring(0, 1)) {
		case "0":
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
					ConstantsClaveEnnum.CLAVE_F04.getValue());
			paramsReport.put("firmaCargo1", firma.getPuesto().getPuesto());
			paramsReport.put("firmaNombre1", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
					ConstantsClaveEnnum.CLAVE_F40.getValue());
			paramsReport.put("firmaCargo2", firma.getPuesto().getPuesto());
			paramsReport.put("firmaNombre2", firma.getNombre());
			
			break;
		case "2":
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
					ConstantsClaveEnnum.CLAVE_F04.getValue());
			paramsReport.put("firmaCargo1", firma.getPuesto().getPuesto());
			paramsReport.put("firmaNombre1", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
					ConstantsClaveEnnum.CLAVE_F40.getValue());
			paramsReport.put("firmaCargo2", firma.getPuesto().getPuesto());
			paramsReport.put("firmaNombre2", firma.getNombre());
			break;
		case "3":
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
					ConstantsClaveEnnum.CLAVE_F04.getValue());
			paramsReport.put("firmaCargo1", firma.getPuesto().getPuesto());
			paramsReport.put("firmaNombre1", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
					ConstantsClaveEnnum.CLAVE_F40.getValue());
			paramsReport.put("firmaCargo2", firma.getPuesto().getPuesto());
			paramsReport.put("firmaNombre2", firma.getNombre());
			break;
		case "4":
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
					ConstantsClaveEnnum.CLAVE_F04.getValue());
			paramsReport.put("firmaCargo1", firma.getPuesto().getPuesto());
			paramsReport.put("firmaNombre1", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(idSector, 0L, 
					ConstantsClaveEnnum.CLAVE_F40.getValue());
			paramsReport.put("firmaCargo2", firma.getPuesto().getPuesto());
			paramsReport.put("firmaNombre2", firma.getNombre());
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
		Map<String, Object> paramsQuery = new java.util.HashMap<String, Object>();
		paramsQuery.put("ID_REF", new Integer(0)); //FALTA
		return reporteadorDirectoImpl.getFileReport(tcReporte,paramsQuery, reporteName,type);
		*/
	}

	
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

}
