package com.gem.sistema.web.bean;

import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.service.catalogos.ReporteRF001027Service;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.web.util.GetFileInlineServlet;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteRF001027SMB.
 */
@ManagedBean(name = "reporteRF001027SMB")
@ViewScoped
public class ReporteRF001027SMB extends  BaseDirectReport {
	
	/** The uuid. */
	private String uuid;
	
	/** The mes. */
	private String mes;
	
	/** The reporte RF 001027 service. */
	@ManagedProperty("#{reporteRF001027Service}")
	private ReporteRF001027Service reporteRF001027Service;
	
	/**
	 * Gets the reporte RF 001027 service.
	 *
	 * @return the reporte RF 001027 service
	 */
	public ReporteRF001027Service getReporteRF001027Service() {
		return reporteRF001027Service;
	}

	/**
	 * Sets the reporte RF 001027 service.
	 *
	 * @param reporteRF001027Service the new reporte RF 001027 service
	 */
	public void setReporteRF001027Service(ReporteRF001027Service reporteRF001027Service) {
		this.reporteRF001027Service = reporteRF001027Service;
	}
	
	

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct RF005.9 ");
		//reportId = 2;
		//tcReporte = reportesRepository.findOne(reportId);
		jasperReporteName = "reporte_RF001027";
		endFilename = "M-EdoCoEgrGbl.pdf";
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		return this.reporteRF001027Service.getParameters(Integer.valueOf(mes));
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		return null;
	}

}
