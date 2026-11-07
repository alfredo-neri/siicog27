package com.gem.sistema.web.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.Map;
import jakarta.annotation.PostConstruct;
import org.primefaces.model.StreamedContent;

// TODO: Auto-generated Javadoc
/**
 * The Class RF001027MB.
 */
@Component("rF001027MB")
@Scope("view")
public class RF001027MB extends BaseDirectReport {
	
	/** The mes. */
	private Integer mes;

	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct Rf001027MB ");		
		jasperReporteName = "RF009.1.4";
		endFilename = jasperReporteName+".pdf";
	}

	
	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		int year = getCurrentYear();
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		paramsReport.put("AN",  Integer.valueOf(year)); 
		paramsReport.put("USUARIO", getUserDetails().getUsername()); 
		paramsReport.put("MES", mes); 		
		paramsReport.put("SECTOR", getUserDetails().getIdSector()); 		
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

	

	
	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}


	

}
