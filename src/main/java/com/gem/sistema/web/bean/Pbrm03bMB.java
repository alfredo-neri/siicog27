package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.Map;

import jakarta.annotation.PostConstruct;


import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.service.catalogos.DataYearSystemService;


// TODO: Auto-generated Javadoc
/**
 * The Class Pbrm03bMB.
 */
@Component("pbrm03bMB")
@Scope("view")
public class Pbrm03bMB extends BaseDirectReport {

	/** The proyecto. */
	private String proyecto;
	
	/** The data year system service. */
	@Value("#{dataYearSystemService}")
	private DataYearSystemService dataYearSystemService;
	

	/**
	 * Gets the data year system service.
	 *
	 * @return the data year system service
	 */
	public DataYearSystemService getDataYearSystemService() {
		return dataYearSystemService;
	}


	/**
	 * Sets the data year system service.
	 *
	 * @param dataYearSystemService the new data year system service
	 */
	public void setDataYearSystemService(DataYearSystemService dataYearSystemService) {
		this.dataYearSystemService = dataYearSystemService;
	}


	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct Pbrm03bMB ");
		reportId = 2;
		tcReporte = reportesRepository.findById(reportId).orElse(null);
		jasperReporteName = "CaratulaPresupuestoIngresos";
		endFilename = jasperReporteName+".pdf";
	}

	
	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {

		// *Proyecto String - X si selecciona PROYECTO
		// *Definitivo String - X Si selecciona DEFINITIVO
		// *Ente String - Nombre del Municipio
		// *No String - Cve del Municipio
		// *Image String - Ruta del archivo de logo municipal
		// *Image2 String - Ruta del logo del organismo
		// *AnioFiscal String - Anio corriente
		// *LastAnio String - Anio corriente - 1
		// *ID_REF Integer -

		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		paramsReport.put("Proyecto", "PROYECTO".equals(this.proyecto) ? "X" : StringUtils.EMPTY);
		paramsReport.put("Definitivo", "DEFINITIVO".equals(this.proyecto) ? "X" : StringUtils.EMPTY);
		paramsReport.put("Ente", this.getUserDetails().getMunicipio());
		paramsReport.put("No", String.valueOf(this.getUserDetails().getIdMunicipio()));
		int year = dataYearSystemService.getYear(this.getUserDetails().getIdSector());
		paramsReport.put("AnioFiscal", String.valueOf(year)); 
		paramsReport.put("LastAnio", String.valueOf(year-1)); 
		paramsReport.put("ID_REF", new Integer(0)); //FALTA	
		paramsReport.put("Image", this.getUserDetails().getPathImgCab1()); 
		paramsReport.put("Image2", this.getUserDetails().getPathImgCab2()); 
		return paramsReport;
	}	



	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		//return new  DefaultStreamedContent();
		
		Map<String, Object> paramsQuery = new java.util.HashMap<String, Object>();
		paramsQuery.put("ID_REF", new Integer(0)); //FALTA
		return reporteadorDirectoImpl.getFileReport(tcReporte,paramsQuery, jasperReporteName,type);
		
	}
		
	
	/**
	 * Gets the proyecto.
	 *
	 * @return the proyecto
	 */
	public String getProyecto() {
		return proyecto;
	}

	/**
	 * Sets the proyecto.
	 *
	 * @param proyecto            the proyecto to set
	 */
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
	
}
