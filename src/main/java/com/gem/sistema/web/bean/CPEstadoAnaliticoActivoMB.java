package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilReport.getNameFileReport;

import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

/**
 * @author Alfredo
 * @Timeline <strong>Date:</strong> 03/09/2021 <strong>Changes:</strong> Se
 *           crean componentes del reporte<br>
 * @Sector Municipal
 */
@Component("cPEstadoAnaliticoActivoMB")
@Scope("view")
public class CPEstadoAnaliticoActivoMB extends BaseDirectReport {
	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";
	private static final String CLAVE_REPORT = "RPT_CP_EDO_ANALITICO_ACTIVO";

	private Integer noDecimales;
	private Integer pesos;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@PostConstruct
	public void init() {
		jasperReporteName = "estadoAnaliticoActivo";
		noDecimales = 2;
		pesos = 1;
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport(conctb, reporte);

		parameters.put("imagenPathRigth", StringUtils.EMPTY);
		parameters.put("imagenPathLeft", conctb.getImagePathLeft());
		parameters.put("nombreEntidad", conctb.getNomDep());
		parameters.put("decimalFormat", "%,." + noDecimales + "f");
		parameters.put("claveEntidad", conctb.getClave());
		parameters.put("pesos", pesos);
		parameters.put("year", conctb.getAnoemp());
		parameters.put("sql", this.generaQuery(sector));
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public void downloadXls() {
		this.validatePolicyService.isAfectMonth(12, null, this.getUserDetails().getIdSector());
		this.validatePolicyService.existPolices(12, null, this.getUserDetails().getIdSector());

		PrimeFaces.current().executeScript(DOWNLOAD_XLS);
	}

	public void viewPdf() {
		this.validatePolicyService.isAfectMonth(12, null, this.getUserDetails().getIdSector());
		this.validatePolicyService.existPolices(12, null, this.getUserDetails().getIdSector());

		this.createFilePdfInline();

	}

	private String generaQuery(Integer sector) {
		StringBuilder query = new StringBuilder();

		query.append("SELECT 	CUENTA,\r\n" + "		NOMCTA,\r\n" + "		SALINIANT,\r\n"
				+ "		(CARGOS_1 + CARGOS_2 + CARGOS_3 + \r\n" + "		 CARGOS_4 + CARGOS_5 + CARGOS_6 + \r\n"
				+ "		 CARGOS_7 + CARGOS_8 + CARGOS_9 + \r\n" + "		 CARGOS_10 + CARGOS_11 + CARGOS_12) CARGOS,\r\n"
				+ "		(ABONOS_1 + ABONOS_2 + ABONOS_3 + \r\n" + "		 ABONOS_4 + ABONOS_5 + ABONOS_6 + \r\n"
				+ "		 ABONOS_7 + ABONOS_8 + ABONOS_9 + \r\n" + "		 ABONOS_10 + ABONOS_11 + ABONOS_12) ABONOS\r\n"
				+ "\r\n" + "	FROM CUENTA \r\n" + "WHERE 	IDSECTOR = " + sector
				+ "	AND	SUBSTR(CUENTA,1,1) = '1' \r\n" + "	AND SUBSTR(CUENTA,4,1) = '0'");

		if (pesos != 1) {
			query.insert(0, "SELECT CUENTA, NOMCTA, (SALINIANT/1000) SALINIANT, "
					+ "(CARGOS/1000) CARGOS, (ABONOS/1000) ABONOS FROM( ");
			query.append(" )T1");
		}

		return query.toString();
	}

	public Integer getNoDecimales() {
		return noDecimales;
	}

	public void setNoDecimales(Integer noDecimales) {
		this.noDecimales = noDecimales;
	}

	public Integer getPesos() {
		return pesos;
	}

	public void setPesos(Integer pesos) {
		this.pesos = pesos;
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

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
