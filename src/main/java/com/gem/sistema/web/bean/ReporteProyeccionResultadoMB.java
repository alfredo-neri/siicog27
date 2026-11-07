package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilReport.getNameFileReport;

import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

// TODO: Auto-generated Javadoc
/**
 * The Class EdoActividadesMB.
 * 
 * @author Alfredo
 * @Timeline <strong>Date:</strong> 17/08/2021 <strong>Changes:</strong> Se
 *           implementa Servicio de firmas dinamicas
 * @Sector Municipal
 */
@Component("reporteProyeccionResultadoMB")
@Scope("view")
public class ReporteProyeccionResultadoMB extends BaseDirectReport {

	private Integer reporte;
	private String titleReport;
	private String claveReport;
	private String tipo;
	private String tipcta;
	private String claveFirmasReport;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@PostConstruct
	public void init() {

		jasperReporteName = "proyeccionesResultados";
		endFilename = jasperReporteName + ".pdf";

		reporte = 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		switch (reporte) {
		case 1:
			this.tipo = "P";
			this.tipcta = "I";
			this.claveReport = "Formato 7a";
			this.titleReport = "Proyección de Ingresos - LDF";
			this.claveFirmasReport = "RPT_PROYECCIONES_INGRESOS_LDF";
			break;

		case 2:
			this.tipo = "P";
			this.tipcta = "E";
			this.claveReport = "Formato 7b";
			this.titleReport = "Proyección de Egresos - LDF";
			this.claveFirmasReport = "RPT_PROYECCIONES_EGRESOS_LDF";
			break;
		case 3:
			this.tipo = "R";
			this.tipcta = "I";
			this.claveReport = "Formato 7c";
			this.titleReport = "Resultado de Ingresos - LDF";
			this.claveFirmasReport = "RPT_RESULTADOS_INGRESOS_LDF";
			break;
		case 4:
			this.tipo = "R";
			this.tipcta = "E";
			this.claveReport = "Formato 7d";
			this.titleReport = "Resultado de Egresos - LDF";
			this.claveFirmasReport = "RPT_RESULTADOS_EGRESOS_LDF";
			break;
		}

		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, claveFirmasReport);

		endFilename = getNameFileReport(conctb, reporte);

		parameters.put("tipo", tipo);
		parameters.put("sector", sector);
		parameters.put("tipcta", tipcta);
		parameters.put("titleReport", titleReport);
		parameters.put("claveReport", claveReport);
		parameters.put("year", conctb.getAnoemp());
		parameters.put("dependenciaName", conctb.getNomDep());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, claveFirmasReport));

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

	public Integer getReporte() {
		return reporte;
	}

	public void setReporte(Integer reporte) {
		this.reporte = reporte;
	}

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	public String getTitleReport() {
		return titleReport;
	}

	public void setTitleReport(String titleReport) {
		this.titleReport = titleReport;
	}

	public String getClaveReport() {
		return claveReport;
	}

	public void setClaveReport(String claveReport) {
		this.claveReport = claveReport;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipcta() {
		return tipcta;
	}

	public void setTipcta(String tipcta) {
		this.tipcta = tipcta;
	}

	public String getClaveFirmasReport() {
		return claveFirmasReport;
	}

	public void setClaveFirmasReport(String claveFirmasReport) {
		this.claveFirmasReport = claveFirmasReport;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

}
