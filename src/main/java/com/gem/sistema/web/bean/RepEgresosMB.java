package com.gem.sistema.web.bean;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

// TODO: Auto-generated Javadoc
/**
 * The Class RepEgresosMB.
 */
@ManagedBean
@ViewScoped
public class RepEgresosMB extends BaseDirectReport {

	/** The tipo rep. */
	private String tipoRep;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct CaratulaPresupuestoEgresosMB ");
		jasperReporteName = "R_egresos";
		endFilename = jasperReporteName + ".pdf";
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		if ("pro".equals(getTipoRep())) {
			paramsReport.put("Proyecto", "X");
			paramsReport.put("Definitivo", "");
		} else {
			paramsReport.put("Proyecto", "");
			paramsReport.put("Definitivo", "X");
		}
		paramsReport.put("USUARIO", getUserDetails().getUsername());
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
	 * Gets the tipo rep.
	 *
	 * @return the tipo rep
	 */
	public String getTipoRep() {
		return tipoRep;
	}

	/**
	 * Sets the tipo rep.
	 *
	 * @param tipoRep the new tipo rep
	 */
	public void setTipoRep(String tipoRep) {
		this.tipoRep = tipoRep;
	}

}
