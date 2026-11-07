package com.gem.sistema.web.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.service.reportador.ReportValidationException;

/**
 * The Class ArchivoAnualFichasMB.
 * 
 * @author Alfredo
 * @Timeline <strong>Date:</strong> 27/02/2022 <strong>Changes:</strong> Se crea
 *           reporte
 * @Sector Municipal
 */
@Component("archivoAnualFichasMB")
@Scope("view")
public class ArchivoAnualFichasMB extends BaseDirectReport {

	@PostConstruct
	public void init() {
		jasperReporteName = "fichaTecnicaDisenoIndicadoresAnual";
		endFilename = jasperReporteName + ".pdf";

	}
	
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

}
