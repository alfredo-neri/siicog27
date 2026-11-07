package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "cNCEstadoDeudaOtrosPasivosMB")
@ViewScoped
public class CNCEstadoDeudaOtrosPasivosMB extends ReportePeriodos {
	private static final String OUTPUT_REPORT_NAME = "EADYOP";
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_GENERIC";
	private static final String CLAVE_REPORT = "RPT_CONAC_GENERIC";

	private StreamedContent fileTxt;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{executePlService}")
	protected ExecutePlService executePlService;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@PostConstruct
	public void init() {
		this.acumulado = Boolean.TRUE;
		jasperReporteName = "CNC_EstadoDeudaOtrosPasivos";
		endFilename = jasperReporteName + ".pdf";
		changePeriodo();
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {

		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport(OUTPUT_REPORT_NAME, getMesSelected(), conctb, reporte);

		parameters.put("pImagenLeft", conctb.getImagePathLeft());
		parameters.put("pImagenRigth", conctb.getImagePathRigth());
		parameters.put("dependenciaName", conctb.getNomDep());
		parameters.put("pDia", getLastDayByAnoEmp(getMesSelected(), conctb.getAnoemp()));
		parameters.put("mesFinal", getNombreMesSelected().toUpperCase());
		parameters.put("mesInicial", getNombreMesInicial().toUpperCase());
		parameters.put("pAnio", conctb.getAnoemp());
		parameters.put("p_mes", getMesSelected());
		parameters.put("query", getQuery());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return parameters;
	}

	public String getQuery() {
		StringBuilder query = new StringBuilder();

		String cargoActual = StringUtils.EMPTY;
		String abonoActual = StringUtils.EMPTY;
		String cargoAnterior = StringUtils.EMPTY;
		String abonoAnterior = StringUtils.EMPTY;

		for (int i = 1; i <= getMesSelected(); i++) {
			cargoActual += "C.CARGOS_" + i + " +";
			abonoActual += "C.ABONOS_" + i + " +";
			cargoAnterior += "EA.CARGOS_" + i + " +";
			abonoAnterior += "EA.ABONOS_" + i + " +";
		}

		abonoActual = abonoActual.substring(0, abonoActual.length() - 2);
		cargoActual = cargoActual.substring(0, cargoActual.length() - 2);
		abonoAnterior = abonoAnterior.substring(0, abonoAnterior.length() - 2);
		cargoAnterior = cargoAnterior.substring(0, cargoAnterior.length() - 2);

		query.append("SELECT * \r\n" + " FROM (VALUES (1, 'DEUDA PÚBLICA', '', '', 0.00, 0.00),\r\n"
				+ "                       (2, 'Corto Plazo', '', '', 0.00, 0.00),\r\n"
				+ "                       (3, 'Deuda Interna', '', '', 0.00, 0.00),\r\n"
				+ "                       (4, 'Instituciones de Crédito', '', '', 0.00, 0.00),\r\n"
				+ "                       (5, 'Títulos y Valores', '', '', 0.00, 0.00),\r\n"
				+ "                       (6, 'Arrendamientos Financieros', '', '', 0, 0),\r\n"
				+ "                       (7, 'Deuda Externa', '', '', 0, 0),\r\n"
				+ "                       (8, 'Organismos Financieros Internacionales', '', '', 0, 0),\r\n"
				+ "                       (9, 'Deuda Bilateral', '', '', 0, 0),\r\n"
				+ "                       (10, 'Títulos y Valores', '', '', 0, 0),\r\n"
				+ "                       (11, 'Arrendamientos Financieros', '', '', 0, 0),\r\n"
				+ "                       (12, 'Subtotal de Deuda Pública a Corto Plazo', '', '', 0, 0),\r\n"
				+ "                       (13, 'Largo Plazo', '', '', 0.00, 0.00),\r\n"
				+ "                       (14, 'Deuda Interna', '', '', 0.00, 0.00),\r\n"
				+ "                       (15, 'Instituciones de Crédito', '', '', 0.00, 0.00),\r\n"
				+ "                       (16, 'Títulos y Valores', '', '', 0.00, 0.00),\r\n"
				+ "                       (17, 'Arrendamientos Financieros', '', '', 0, 0),\r\n"
				+ "                       (18, 'Deuda Externa', '', '', 0, 0),\r\n"
				+ "                       (19, 'Organismos Financieros Internacionales', '', '', 0, 0),\r\n"
				+ "                       (20, 'Deuda Bilateral', '', '', 0, 0),\r\n"
				+ "                       (21, 'Títulos y Valores', '', '', 0, 0),\r\n"
				+ "                       (22, 'Arrendamientos Financieros', '', '', 0, 0),\r\n"
				+ "                       (23, 'Subtotal de Deuda Pública a Largo Plazo', '', '', 0,\r\n"
				+ "                        0)) AS TEMP (GRUPO, TITULO, MONEDA, INSTITUCION, SDO_INICIAL, SDO_FINAL)\r\n"
				+ "          UNION\r\n" + "          SELECT 24, 'Total de Otros Pasivos', '', '',")
				.append(" SUM(SDO_INICIAL - CARGOS_ANTERIOR + ABONOS_ANTERIOR) PERIODO_ANTERIOR, \n"
						+ " 		SUM(SALINI - CARGOS_ACTUAL + ABONOS_ACTUAL) PERIODO_ACTUAL\n" + "	FROM (\n"
						+ "		SELECT 	C.CUENTA, C.SALINI, EA.SDO_INICIAL, ")
				.append(abonoAnterior).append(" ABONOS_ANTERIOR, ").append(cargoAnterior).append(" CARGOS_ANTERIOR, ")
				.append(abonoActual).append(" ABONOS_ACTUAL, ").append(cargoActual).append(" CARGOS_ACTUAL ")
				.append("			FROM CUENTA C \n"
						+ "				LEFT JOIN TW_EJERCICIO_ANTERIOR EA ON 	C.CUENTA = EA.CUENTA AND \n"
						+ "														C.SCTA = EA.SCTA AND \n"
						+ "														C.SSCTA = EA.SSCTA AND \n"
						+ "														C.SSSCTA = EA.SSSCTA AND \n"
						+ "														C.SSSSCTA = EA.SSSSCTA \n"
						+ "			WHERE 	C.CUENTA >= '2000' AND C.CUENTA <= '2999' \n"
						+ "				AND C.SCTA ='' AND C.IDSECTOR = " + this.getUserDetails().getIdSector()
						+ "             AND SUBSTR(C.CUENTA,4,1) <>'0' \n" + "	) T1 ORDER BY GRUPO ");

		System.out.println("sql: " + query.toString());
		return query.toString();
	}

	private String generaQueryTxt() {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT '\"'||TITULO ||'\"|\"'|| MONEDA ||'\"|\"'|| INSTITUCION ||'\"|\"'||\r\n"
				+ "            TRIM(TO_CHAR(SDO_INICIAL, '999999999999999990.99')) ||'\"|\"'||\r\n"
				+ "            TRIM(TO_CHAR(SDO_FINAL, '999999999999999990.99')) ||'\"'\r\n"
				+ "    FROM (").append(this.getQuery())
				.append(" )T2");

		return sql.toString();
	}

	Map<String, Object> out;
	InputStream stream = null;

	public StreamedContent getFileTxt() {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		String filename = getNameFileReport(OUTPUT_REPORT_NAME, getMesSelected(), conctb, reporte);

		parameter.addValue("i_query", this.generaQueryTxt());
		parameter.addValue("i_filename", filename.substring(0, filename.length() - 4));

		out = executePlService.callProcedure(PROCEDURE_NAME, parameter);

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(
						new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			fileTxt = new DefaultStreamedContent(stream, "application/txt", out.get("O_NAME_FILE").toString());
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
		}

		return fileTxt;
	}

	public void setFileTxt(StreamedContent fileTxt) {
		this.fileTxt = fileTxt;
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

	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
	}
}
