package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@Component("estadoDeudaOtrosPasivosMB")
@Scope("view")
public class EstadoDeudaOtrosPasivosMB extends ReportePeriodos {

	private static final String OUTPUT_REPORT_NAME = "LDF-EADOP";
	private static final String CLAVE_REPORT = "RPT_LDF_GENERIC";

	/**
	 * Define el tipo de reporte que se contruira <br/>
	 * <li>0 para el reporte acumulado</li>
	 * <li>1 para el reporte con mes anterior sin acumulacion</li>
	 */
	private Integer type = 0;
	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@PostConstruct
	public void init() {
		jasperReporteName = "estadoDeudaOtrosPasivos";
		endFilename = jasperReporteName + ".pdf";
		this.mesAnterior = true;
		changePeriodo();
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {

		Integer sector = this.getUserDetails().getIdSector();
		Map<String, Object> parameters = new HashMap<String, Object>();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport(OUTPUT_REPORT_NAME, getMesSelected(), conctb, reporte);

		parameters.put("pImagenLeft", conctb.getImagePathLeft());
		parameters.put("pImagenRigth", conctb.getImagePathRigth());
		parameters.put("pNomDep", conctb.getNomDep());
		parameters.put("pDia", getLastDayByAnoEmp(getMesSelected(), conctb.getAnoemp()));
		parameters.put("mesFinal", getNombreMesSelected().toUpperCase());
		parameters.put("mesInicial", getNombreMesInicial().toUpperCase());
		parameters.put("pAnio", conctb.getAnoemp());
		parameters.put("p_mes", getMesSelected());
		parameters.put("query", getQuery());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return parameters;
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

	public String getQuery() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM (VALUES \r\n" + "				(1, 'DEUDA PÚBLICA', '', '', 0.00, 0.00),\r\n"
				+ "             (2, 'Corto Plazo', '', '', 0.00, 0.00),\r\n"
				+ "             (3, 'Deuda Interna', '', '', 0.00, 0.00),\r\n"
				+ "             (4, 'Instituciones de Crédito', '', '', 0.00, 0.00),\r\n"
				+ "             (5, 'Títulos y Valores', '', '', 0.00, 0.00),\r\n"
				+ "             (6, 'Arrendamientos Financieros', '', '', 0, 0),\r\n"
				+ "             (7, 'Deuda Externa', '', '', 0, 0),\r\n"
				+ "             (8, 'Organismos Financieros Internacionales', '', '', 0, 0),\r\n"
				+ "             (9, 'Deuda Bilateral', '', '', 0, 0),\r\n"
				+ "             (10, 'Títulos y Valores', '', '', 0, 0),\r\n"
				+ "             (11, 'Arrendamientos Financieros', '', '', 0, 0),\r\n"
				+ "             (12, 'Subtotal de Deuda Pública a Corto Plazo', '', '', 0, 0),\r\n"
				+ "             (13, 'Largo Plazo', '', '', 0.00, 0.00),\r\n"
				+ "             (14, 'Deuda Interna', '', '', 0.00, 0.00),\r\n"
				+ "             (15, 'Instituciones de Crédito', '', '', 0.00, 0.00),\r\n"
				+ "             (16, 'Títulos y Valores', '', '', 0.00, 0.00),\r\n"
				+ "             (17, 'Arrendamientos Financieros', '', '', 0, 0),\r\n"
				+ "             (18, 'Deuda Externa', '', '', 0, 0),\r\n"
				+ "             (19, 'Organismos Financieros Internacionales', '', '', 0, 0),\r\n"
				+ "             (20, 'Deuda Bilateral', '', '', 0, 0),\r\n"
				+ "             (21, 'Títulos y Valores', '', '', 0, 0),\r\n"
				+ "             (22, 'Arrendamientos Financieros', '', '', 0, 0),\r\n"
				+ "             (23, 'Subtotal de Deuda Pública a Largo Plazo', '', '', 0,0)\r\n"
				+ "              ) AS TEMP (GRUPO, TITULO, MONEDA, INSTITUCION, SDO_INICIAL, SDO_FINAL)\r\n"
				+ "UNION SELECT 24, 'Total de Otros Pasivos', '', '', ");

		query.append(" (SDO_INICIAL + ABONOS_ANT - CARGOS_ANT) SDO_INICAL_PERIODO,\r\n")
				.append(" (SALINI + ABONOS_ACT - CARGOS_ACT) SDO_FINAL_PERIODO\r\n")
				.append("FROM (SELECT SUBSTR(C.CUENTA, 1, 2) GRUPO,");

		Boolean activateEjercioAnteriorTable = Boolean.TRUE;

//		if (periodo.getTipoPeriodo() == 3 || periodo.getTipoPeriodo() == 1) {
//			if (periodo.getPeriodo() == 1) {
//				activateEjercioAnteriorTable = Boolean.TRUE;
//			}
//		}

		if (activateEjercioAnteriorTable) {
			query.append("SUM(C.SALINI) SALINI, SUM(A.SDO_INICIAL) SDO_INICIAL,");
		} else {
			query.append("SUM(C.SALINI) SALINI, SUM(C.SALINI) SDO_INICIAL,");
		}

		String abonoAnterior = StringUtils.EMPTY;
		String cargoAnterior = StringUtils.EMPTY;
		String abonoActual = StringUtils.EMPTY;
		String cargoActual = StringUtils.EMPTY;

		if (activateEjercioAnteriorTable) {

			for (int i = 1; i <= 12; i++) {
				cargoAnterior += "A.CARGOS_" + i + " +";
				abonoAnterior += "A.ABONOS_" + i + " +";
			}
		} else {
			for (int i = 1; i <= getMesSelected() - periodo.getTipoPeriodo(); i++) {
				cargoAnterior += "C.CARGOS_" + i + " +";
				abonoAnterior += "C.ABONOS_" + i + " +";
			}
		}

		for (int i = 1; i <= getMesSelected(); i++) {
			cargoActual += "C.CARGOS_" + i + " +";
			abonoActual += "C.ABONOS_" + i + " +";
		}

		if (!abonoAnterior.equals(StringUtils.EMPTY)) {
			abonoAnterior = abonoAnterior.substring(0, abonoAnterior.length() - 2);
			cargoAnterior = cargoAnterior.substring(0, cargoAnterior.length() - 2);
		} else {
			cargoAnterior = "0";
			abonoAnterior = "0";
		}
		abonoActual = abonoActual.substring(0, abonoActual.length() - 2);
		cargoActual = cargoActual.substring(0, cargoActual.length() - 2);

		query.append("SUM(").append(cargoAnterior).append(") CARGOS_ANT, ");
		query.append("SUM(").append(abonoAnterior).append(") ABONOS_ANT, ");
		query.append("SUM(").append(cargoActual).append(") CARGOS_ACT, ");
		query.append("SUM(").append(abonoActual).append(") ABONOS_ACT  ");

		query.append(" FROM CUENTA C ");

		if (activateEjercioAnteriorTable) {
			query.append("LEFT JOIN TW_EJERCICIO_ANTERIOR A ON 	C.CUENTA = A.CUENTA AND "
					+ " 	C.SCTA = A.SCTA AND C.SSCTA = A.SSCTA AND C.SSSCTA = A.SSSCTA AND C.SSSSCTA = A.SSSSCTA ");
		}

		query.append("WHERE C.CUENTA >= '2000' AND C.CUENTA <= '2999' AND C.SCTA = '' AND C.IDSECTOR = "
				+ this.getUserDetails().getIdSector() + "     AND SUBSTR(C.CUENTA, 4, 1) <> '0'\r\n"
				+ " GROUP BY SUBSTR(C.CUENTA, 1, 2)) T1\r\n"
				+ " WHERE (SALINI <> 0 OR CARGOS_ANT <> 0 OR ABONOS_ANT <> 0 OR CARGOS_ACT <> 0 OR ABONOS_ACT <> 0) "
				+ " ORDER BY GRUPO ");

		System.out.println("sql: " + query.toString());
		return query.toString();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
