package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilReport.getNameFileReport;

import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.tabview.Tab;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

/**
 * @author Alfredo
 * @Timeline <strong>Date:</strong> 26/08/2021 <strong>Changes:</strong> Se
 *           crean componentes para la generacion de los reportes
 * @Sector Municipal
 */
@Scope("view")
@Component("proyectoPresupuestarioReportesMB")
public class ProyectoPresupuestarioReportesMB extends BaseDirectReport {
	private static final String WHERE_SALDO_CERO = " WHERE T1.ENERO <> 0 OR T1.FEBRERO <> 0 OR T1.MARZO <> 0 OR T1.ABRIL <> 0 OR T1.MAYO <> 0 OR T1.JUNIO <> 0 OR T1.JULIO <> 0 OR T1.AGOSTO <> 0 OR T1.SEPTIEMBRE <> 0 OR T1.OCTUBRE <> 0 	OR T1.NOVIEMBRE <> 0 OR T1.DICIEMBRE <> 0 ";
	private String saldoCero;
	private String claveReport;
	private Boolean showSaldoCero;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@PostConstruct
	public void init() {
		jasperReporteName = "PP_PbRM-01a";
		claveReport = "RPT_PROYECTO_PROGRAMA_ANUAL_DIMENSION_ADMINISTRATIVA_GASTO";
		showSaldoCero = Boolean.FALSE;
	}

	public void onTabChange(TabChangeEvent event) {

		this.uuid = null;
		this.endFilename = null;
		Tab tab = event.getTab();
		tab.clearInitialState();
		tab.setTitleStyle("background: #8eb987;");

		switch (event.getTab().getId()) {
		case "pbrm01a":
			this.showSaldoCero = Boolean.FALSE;
			this.jasperReporteName = "PP_PbRM-01a";
			this.claveReport = "RPT_PROYECTO_PROGRAMA_ANUAL_DIMENSION_ADMINISTRATIVA_GASTO";
			break;

		case "pbrm04a":
			this.showSaldoCero = Boolean.FALSE;
			this.jasperReporteName = "PP_PbRM04a";
			this.claveReport = "RPT_PROYECTO_PRESUPUESTO_EGRESOS_DETALLADO";
			break;

		case "pbrm04b":
			this.showSaldoCero = Boolean.FALSE;
			this.jasperReporteName = "PP_PbRM04b";
			this.claveReport = "RPT_PROYECTO_PRESUPUESTO_EGRESOS_OBJ_DEP_GENERAL";
			break;

		case "pbrm04c":
			this.showSaldoCero = Boolean.FALSE;
			this.jasperReporteName = "PP_PbRM04c";
			this.claveReport = "RPT_PROYECTO_PRESUPUESTO_EGRESOS_GLOBAL_CALENDARIZADO";
			break;

		case "pbrm04d":
			this.showSaldoCero = Boolean.FALSE;
			this.jasperReporteName = "PP_PbRM04d";
			this.claveReport = "RPT_PROYECTO_CARATULA_PRESUPUESTO_EGRESOS";
			break;

		case "pbrm06":
			this.showSaldoCero = Boolean.TRUE;
			this.jasperReporteName = "PP_PbRM06";
			this.claveReport = "RPT_PROYECTO_PROGRAMA_ANUAL_ADQUISICIONES";
			break;

		case "pbrm07b":
			this.showSaldoCero = Boolean.FALSE;
			this.jasperReporteName = "PP_PbRM07";
			this.claveReport = "RPT_PROYECTO_PROGRAMA_ANUAL_OBRA";
			break;
		}

	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, claveReport);

		endFilename = getNameFileReport(conctb, reporte);

		if (showSaldoCero) {
			paramsReport.put("saldoCero", saldoCero.equals("S") ? StringUtils.EMPTY : WHERE_SALDO_CERO);
		}

		paramsReport.put("sector", idSector);
		paramsReport.put("year", conctb.getAnoemp() + 1);
		paramsReport.put("claveMunicipio", conctb.getClave());
		paramsReport.put("imagenPathLeft", conctb.getImagePathLeft());
		paramsReport.put("imagenPathRigth", conctb.getImagePathRigth());
		paramsReport.put("nombreMunicipio", conctb.getNomDep());
		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(idSector, claveReport));

		return paramsReport;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSaldoCero() {
		return saldoCero;
	}

	public void setSaldoCero(String saldoCero) {
		this.saldoCero = saldoCero;
	}

	public String getClaveReport() {
		return claveReport;
	}

	public void setClaveReport(String claveReport) {
		this.claveReport = claveReport;
	}

	public Boolean getShowSaldoCero() {
		return showSaldoCero;
	}

	public void setShowSaldoCero(Boolean showSaldoCero) {
		this.showSaldoCero = showSaldoCero;
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

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
