package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;;

@Component("cNCEdoAnaliticoActivoMB")
@Scope("view")
public class CNCEdoAnaliticoActivoMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_CONAC_GENERIC_INGRESOS";
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_GENERIC";
	private static final String OUTPUT_REPORT_NAME = "EAA";
	
	private Integer mesSelected;
	
	private StreamedContent fileTxt;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{tcPeriodoRepositoy}")
	protected TcPeriodoRepositoy periodoRepositoy;
	
	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	/** Servicio para validacion de mes aprturado, polizas, afectacion */
	@Value("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;
	
	@Value("#{executePlService}")
	protected ExecutePlService executePlService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		String query = this.generateQuery(sector);
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcPeriodo mes = periodoRepositoy.findByTipoPeriodoAndPeriodo(1, mesSelected);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);
		
		endFilename = getNameFileReport(OUTPUT_REPORT_NAME, mesSelected, conctb, reporte);

		parameters.put("year", conctb.getAnoemp());
		parameters.put("query", query);
		parameters.put("mesName", mes.getDescripcion().toUpperCase());
		parameters.put("lastDay", getLastDayByAnoEmp(mesSelected, conctb.getAnoemp()));
		parameters.put("imagenPathLeft", conctb.getImagePathLeft());
		parameters.put("imagenPathRigth", conctb.getImagePathRigth());
		parameters.put("dependenciaName", conctb.getNomDep());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return parameters;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "CNC_EdoAnaliticoActivo";
		endFilename = jasperReporteName + ".pdf";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Generate query.
	 *
	 * @param idSector the id sector
	 * @return the string
	 */
	private String generateQuery(Integer idSector) {

		StringBuilder sql = new StringBuilder();
		StringBuilder cargosAnterior = new StringBuilder();
		StringBuilder abonosAnterior = new StringBuilder();
		StringBuilder cargosActual = new StringBuilder();
		StringBuilder abonosActual = new StringBuilder();
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();

		for (int row = 1; row <= mesSelected; row++) {
			cargos.append(" EA.CARGOS_" + row + " +");
			abonos.append(" EA.ABONOS_" + row + " +");
			cargosActual.append(" CU.CARGOS_" + row + " +");
			abonosActual.append(" CU.ABONOS_" + row + " +");
		}

		for (int row = mesSelected + 1; row <= 12; row++) {
			cargosAnterior.append(" EA.CARGOS_" + row + " +");
			abonosAnterior.append(" EA.ABONOS_" + row + " +");
		}

		sql.append("SELECT T2.CUENTA, T2.NOMCTA, T2.SALDO_INICIAL, T2.CARGOS, T2.ABONOS,\r\n"
				+ "       (T2.SALDO_INICIAL + T2.CARGOS - T2.ABONOS) SALDO_FINAL,\r\n"
				+ "       ((T2.SALDO_INICIAL + T2.CARGOS - T2.ABONOS) - T2.SALDO_INICIAL) VARIACION\r\n"
				+ "FROM (SELECT T1.CUENTA, T1.NOMCTA,\r\n"
				+ "             (T1.SDO_INICIAL + T1.CARGOS - T1.ABONOS) SALDO_INICIAL,\r\n"
				+ "             (T1.CARGOS_ANTERIOR + T1.CARGOS_ACTUAL)  CARGOS,\r\n"
				+ "             (T1.ABONOS_ANTERIOR + T1.ABONOS_ACTUAL)  ABONOS\r\n"
				+ "      FROM (SELECT CU.CUENTA, CU.NOMCTA,\r\n"
				+ "                   DECODE(CU.CUENTA, '1260', EA.SDO_INICIAL * -1, EA.SDO_INICIAL) SDO_INICIAL,\r\n"
				+ "       ( ").append(cargosActual.substring(0, cargosActual.length() - 1)).append(" ) CARGOS_ACTUAL,\r\n")
				.append(" ( ").append(abonosActual.substring(0, abonosActual.length() - 1)).append(" ) ABONOS_ACTUAL,\r\n")
				.append(" ( ").append(cargos.substring(0, cargos.length() - 1)).append(" ) CARGOS,\r\n")
				.append(" ( ").append(abonos.substring(0, abonos.length() - 1)).append(" ) ABONOS,\r\n")
				.append(" ( ").append(abonosAnterior.substring(0, abonosAnterior.length() - 1)).append(" ) ABONOS_ANTERIOR,\r\n")
				.append(" ( ").append(cargosAnterior.substring(0, cargosAnterior.length() - 1)).append(" ) CARGOS_ANTERIOR\r\n")
				.append("            FROM CUENTA CU\r\n").append(" INNER JOIN TW_EJERCICIO_ANTERIOR EA ON CU.CUENTA = EA.CUENTA AND\r\n"
						+ "                                                            CU.SCTA = EA.SCTA AND\r\n"
						+ "                                                            CU.SSCTA = EA.SSCTA AND\r\n"
						+ "                                                            CU.SSSCTA = EA.SSSCTA AND\r\n"
						+ "                                                            CU.SSSSCTA = EA.SSSSCTA\r\n"
						+ "            WHERE CU.CUENTA LIKE '1%0') T1 WHERE SUBSTR(CUENTA,3) <> '00' ) T2 ORDER BY CUENTA");

		System.out.println("SQL::: " + sql.toString());

		return sql.toString();
	}

	private String generateQueryTxt() {
		
		StringBuilder txtQuery = new StringBuilder();
		
		txtQuery.append("SELECT '\"' ||NOMCTA||'\"|\"' ||\r\n"
				+ "		TRIM(TO_CHAR(SALDO_INICIAL, '999999999999999990.99'))||'\"|\"' ||\r\n"
				+ "		TRIM(TO_CHAR(CARGOS, '999999999999999990.99'))||'\"|\"' ||\r\n"
				+ "		TRIM(TO_CHAR(ABONOS, '999999999999999990.99'))||'\"|\"' ||\r\n"
				+ "		TRIM(TO_CHAR(SALDO_FINAL, '999999999999999990.99'))||'\"|\"' ||\r\n"
				+ "		TRIM(TO_CHAR(VARIACION, '999999999999999990.99'))||'\"'\r\n"
				+ "FROM (\r\n").append(this.generateQuery(this.getUserDetails().getIdSector())).append(
				 " )T3");
		
		return txtQuery.toString();
		
	}
	/** Metodo de validacion al descargar el PDF */
	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(mesSelected, null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mesSelected, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mesSelected, null, this.getUserDetails().getIdSector());
			this.createFilePdfInline();
		}
	}

	Map<String, Object> out;
	InputStream stream = null;
	public StreamedContent getFileTxt() {
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		String filename = getNameFileReport(OUTPUT_REPORT_NAME, mesSelected, conctb, reporte);
		
		parameter.addValue("i_query", this.generateQueryTxt());
		parameter.addValue("i_filename", filename.substring(0, filename.length() - 4));

		out = executePlService.callProcedure(PROCEDURE_NAME, parameter);

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(
						new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			fileTxt = StreamedContents.of(stream, "application/txt", out.get("O_NAME_FILE").toString(), null);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
		}
		return fileTxt;
	}

	public void setFileTxt(StreamedContent fileTxt) {
		this.fileTxt = fileTxt;
	}

	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
	}

	public Integer getMesSelected() {
		return mesSelected;
	}

	public void setMesSelected(Integer mesSelected) {
		this.mesSelected = mesSelected;
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

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
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
