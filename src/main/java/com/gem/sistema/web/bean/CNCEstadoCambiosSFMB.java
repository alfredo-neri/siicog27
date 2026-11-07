package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;

import org.primefaces.PrimeFaces;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@Component("cNCEstadoCambiosSFMB")
@Scope("view")
public class CNCEstadoCambiosSFMB extends ReportePeriodos {
	private static final String CLAVE_REPORT = "RPT_CONAC_GENERIC";
	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		this.jasperReporteName = "CONAC_EstadoCambiosSitFin";
		this.endFilename = this.jasperReporteName + ".pdf";
		this.mesAnterior = true;
		this.acumulado = true;
		changePeriodo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport("CONAC-EstadoCambiosSitFin", getMesSelected(), conctb, reporte);

		parameters.put("year", conctb.getAnoemp());
		parameters.put("lastDay", getLastDayByAnoEmp(getMesSelected(), conctb.getAnoemp()));
		parameters.put("sqlActivo", this.generaQueryActivo());
		parameters.put("sqlPasivo", this.generaQueryPasivo());
		parameters.put("sqlHacienda", this.generaQueryHacienda());
		parameters.put("sql3211", this.generaQuery3211());
		parameters.put("imageLeft", conctb.getImagePathLeft());
		parameters.put("imageRigth", conctb.getImagePathRigth());
		parameters.put("mesFinalName", getNombreMesSelected().toUpperCase());
		parameters.put("mesInicialName", getNombreMesInicial().toUpperCase());
		parameters.put("dependenciaName", conctb.getNomDep());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));
		return parameters;
	}

	private String generaQueryActivo() {

		StringBuilder query = new StringBuilder();
		StringBuilder cargosAct = new StringBuilder();
		StringBuilder abonosAct = new StringBuilder();
		StringBuilder cargosAnt = new StringBuilder();
		StringBuilder abonosAnt = new StringBuilder();

		for (int row = 1; row <= getMesSelected(); row++) {

			cargosAct.append(" C.CARGOS_" + row + " +");
			abonosAct.append(" C.ABONOS_" + row + " +");
			cargosAnt.append(" A.CARGOS_" + row + " +");
			abonosAnt.append(" A.ABONOS_" + row + " +");
		}

		query.append("SELECT CUENTA, NOMCTA, \r\n"
				+ "       CASE WHEN (ANTERIOR - ACTUAL) > 0 THEN (ANTERIOR - ACTUAL) ELSE 0.0 END ORIGEN,\r\n"
				+ "       CASE WHEN (ANTERIOR - ACTUAL) < 0 THEN ABS(ANTERIOR - ACTUAL) ELSE 0.0 END APLICACION\r\n"
				+ "    FROM (SELECT CUENTA, NOMCTA,\r\n"
				+ "                 DECODE(CUENTA, '1260', ((SDO_INICIAL + ABONOS_ANT - CARGOS_ANT) * -1),\r\n"
				+ "                                        (SDO_INICIAL + CARGOS_ANT - ABONOS_ANT)) ANTERIOR,\r\n"
				+ "                 DECODE(CUENTA, '1260', ((SALINI + ABONOS_ACT - CARGOS_ACT) * -1),\r\n"
				+ "                                        (SALINI + CARGOS_ACT - ABONOS_ACT)) ACTUAL\r\n"
				+ "               FROM (SELECT SUBSTR(C.CUENTA, 1, 2) GRUPO, C.STACTA, C.NOMCTA, C.CUENTA, C.SALINI, A.SDO_INICIAL,\r\n")
				.append(" ( ").append(abonosAnt.substring(0, abonosAnt.length() - 1)).append(" ) ABONOS_ANT,\r\n")
				.append("	( ").append(cargosAnt.substring(0, cargosAnt.length() - 1)).append(" ) CARGOS_ANT,\r\n")
				.append("	( ").append(abonosAct.substring(0, abonosAct.length() - 1)).append(" ) ABONOS_ACT,\r\n")
				.append("	( ").append(cargosAct.substring(0, cargosAct.length() - 1)).append(" ) CARGOS_ACT\r\n")
				.append("                     FROM CUENTA C\r\n"
						+ "                              LEFT JOIN TW_EJERCICIO_ANTERIOR A\r\n"
						+ "                                        ON C.CUENTA = A.CUENTA AND C.SCTA = A.SCTA AND C.SSCTA = A.SSCTA AND\r\n"
						+ "                                           C.SSSCTA = A.SSSCTA AND C.SSSSCTA = A.SSSSCTA\r\n"
						+ "                     WHERE C.CUENTA >= '1000' AND C.CUENTA <= '1293' AND C.SCTA = ''\r\n"
						+ "                       AND C.IDSECTOR = ")
				.append(this.getUserDetails().getIdSector())
				.append("                       AND SUBSTR(C.CUENTA, 4, 1) = '0' AND SUBSTR(C.CUENTA, 3) <> '00'\r\n"
						+ "                     ) T1 ) T2 ORDER BY CUENTA");

		System.out.println("QueryActivo: " + query.toString());
		return query.toString();
	}

	private String generaQueryPasivo() {
		StringBuilder query = new StringBuilder();
		StringBuilder cargosAct = new StringBuilder();
		StringBuilder abonosAct = new StringBuilder();
		StringBuilder cargosAnt = new StringBuilder();
		StringBuilder abonosAnt = new StringBuilder();

		for (int row = 1; row <= getMesSelected(); row++) {

			cargosAct.append(" C.CARGOS_" + row + " +");
			abonosAct.append(" C.ABONOS_" + row + " +");
			cargosAnt.append(" A.CARGOS_" + row + " +");
			abonosAnt.append(" A.ABONOS_" + row + " +");
		}

		query.append("select CUENTA, NOMCTA, \r\n"
				+ "       CASE WHEN (ANTERIOR - ACTUAL) < 0 THEN ABS(ANTERIOR - ACTUAL) ELSE 0.0 END ORIGEN,\r\n"
				+ "       CASE WHEN (ANTERIOR - ACTUAL) > 0 THEN (ANTERIOR - ACTUAL) ELSE 0.0 END    APLICACION\r\n"
				+ "    FROM (SELECT CUENTA,\r\n" + "                 NOMCTA,\r\n"
				+ "                 SDO_INICIAL - CARGOS_ANT + ABONOS_ANT ANTERIOR,\r\n"
				+ "                 SALINI - CARGOS_ACT + ABONOS_ACT      ACTUAL\r\n"
				+ "               FROM (SELECT SUBSTR(C.CUENTA, 1, 2) GRUPO, C.STACTA, C.NOMCTA,\r\n"
				+ "                            C.CUENTA, C.SALINI, A.SDO_INICIAL,\r\n").append(" 	( ")
				.append(abonosAnt.substring(0, abonosAnt.length() - 1)).append(" ) ABONOS_ANT,\r\n").append("	( ")
				.append(cargosAnt.substring(0, cargosAnt.length() - 1)).append(" ) CARGOS_ANT,\r\n").append("	( ")
				.append(abonosAct.substring(0, abonosAct.length() - 1)).append(" ) ABONOS_ACT,\r\n").append("	( ")
				.append(cargosAct.substring(0, cargosAct.length() - 1)).append(" ) CARGOS_ACT\r\n")
				.append("                     FROM CUENTA C\r\n"
						+ "                              LEFT JOIN TW_EJERCICIO_ANTERIOR A\r\n"
						+ "                                        ON C.CUENTA = A.CUENTA AND C.SCTA = A.SCTA AND C.SSCTA = A.SSCTA AND\r\n"
						+ "                                           C.SSSCTA = A.SSSCTA AND C.SSSSCTA = A.SSSSCTA\r\n"
						+ "                     WHERE C.CUENTA >= '2000'\r\n"
						+ "                       AND C.CUENTA <= '2999'\r\n"
						+ "                       AND C.SCTA = '' AND C.IDSECTOR = ")
				.append(this.getUserDetails().getIdSector())
				.append("                       AND SUBSTR(C.CUENTA, 4, 1) = '0' AND SUBSTR(C.CUENTA, 3) <> '00'\r\n"
						+ "                     ) T1 ) T2 ORDER BY CUENTA");

		System.out.println("QueryPasivo: " + query.toString());
		return query.toString();
	}

	private String generaQueryHacienda() {

		StringBuilder query = new StringBuilder();
		StringBuilder cargosAct = new StringBuilder();
		StringBuilder abonosAct = new StringBuilder();
		StringBuilder cargosAnt = new StringBuilder();
		StringBuilder abonosAnt = new StringBuilder();

		for (int row = 1; row <= getMesSelected(); row++) {

			cargosAct.append(" C.CARGOS_" + row + " +");
			abonosAct.append(" C.ABONOS_" + row + " +");
			cargosAnt.append(" A.CARGOS_" + row + " +");
			abonosAnt.append(" A.ABONOS_" + row + " +");
		}
		
		query.append("SELECT CUENTA,\r\n"
				+ "       NOMCTA,\r\n"
				+ "       CASE\r\n"
				+ "           WHEN VARIACION < 0 THEN ABS(VARIACION)\r\n"
				+ "           ELSE 0.0 END ORIGEN,\r\n"
				+ "       CASE\r\n"
				+ "           WHEN VARIACION > 0 THEN VARIACION\r\n"
				+ "           ELSE 0.0 END APLICACION\r\n"
				+ "FROM (SELECT *, T2.PERIODO_ANTERIOR - T2.PERIODO_ACTUAL VARIACION\r\n"
				+ "      FROM (SELECT CUENTA,\r\n"
				+ "                   NOMCTA,\r\n"
				+ "                   (SDO_INICIAL - CARGOS_ANT + ABONOS_ANT)    PERIODO_ANTERIOR,\r\n"
				+ "                   DECODE(CUENTA, '3210', FN_GET_CUENTA_3211(6, 'a'),\r\n"
				+ "                          (SALINI - CARGOS_ACT + ABONOS_ACT)) PERIODO_ACTUAL\r\n"
				+ "            FROM (SELECT DECODE(SUBSTR(C.CUENTA, 1, 1), 2, SUBSTR(C.CUENTA, 1, 2), SUBSTR(C.CUENTA, 1, 1)) GRUPO,\r\n"
				+ "                         C.STACTA,\r\n"
				+ "                         C.NOMCTA,\r\n"
				+ "                         C.CUENTA,\r\n"
				+ "                         C.SALINI,\r\n"
				+ "                         A.SDO_INICIAL,").append(" 	SUM( ")
		.append(abonosAnt.substring(0, abonosAnt.length() - 1)).append(" ) ABONOS_ANT,\r\n").append("	SUM( ")
		.append(cargosAnt.substring(0, cargosAnt.length() - 1)).append(" ) CARGOS_ANT,\r\n").append("	SUM( ")
		.append(abonosAct.substring(0, abonosAct.length() - 1)).append(" ) ABONOS_ACT,\r\n").append("	SUM( ")
		.append(cargosAct.substring(0, cargosAct.length() - 1)).append(" ) CARGOS_ACT\r\n")
		.append( "                  FROM CUENTA C\r\n"
				+ "                           LEFT JOIN TW_EJERCICIO_ANTERIOR A\r\n"
				+ "                                     ON C.CUENTA = A.CUENTA AND C.SCTA = A.SCTA AND C.SSCTA = A.SSCTA AND\r\n"
				+ "                                        C.SSSCTA = A.SSSCTA AND\r\n"
				+ "                                        C.SSSSCTA = A.SSSSCTA\r\n"
				+ "                  WHERE C.CUENTA >= '3000'\r\n"
				+ "                    AND C.CUENTA <= '3321'\r\n"
				+ "                    AND C.SCTA = ''\r\n"
				+ "                    AND C.IDSECTOR = 2\r\n"
				+ "                    AND SUBSTR(C.CUENTA, 4, 1) = '0'\r\n"
				+ "                  GROUP BY C.STACTA, C.NOMCTA, C.CUENTA, C.SALINI, A.SDO_INICIAL, SUBSTR(C.CUENTA, 1, 2)) T1\r\n"
				+ "            WHERE CUENTA NOT IN ('3000', '3100', '3200')\r\n"
				+ "            ORDER BY CUENTA) T2) T3 ");

		System.out.println("QueryHacienda: " + query.toString());
		return query.toString();
	}

	private String generaQuery3211() {
		StringBuilder query = new StringBuilder();
		StringBuilder cargosAct = new StringBuilder();
		StringBuilder abonosAct = new StringBuilder();
		StringBuilder cargosAnt = new StringBuilder();
		StringBuilder abonosAnt = new StringBuilder();

		for (int row = 1; row <= getMesSelected(); row++) {

			cargosAct.append(" C.CARGOS_" + row + " +");
			abonosAct.append(" C.ABONOS_" + row + " +");
			cargosAnt.append(" EA.CARGOS_" + row + " +");
			abonosAnt.append(" EA.ABONOS_" + row + " +");
		}

		query.append("SELECT (SUM(T3.VAN4) - SUM(T3.VAN5)) TOTAL_ANT, (SUM(T3.VACT4) - SUM(T3.VACT5)) TOTAL_ACT\r\n"
				+ "FROM (SELECT CASE WHEN T2.GRUPO = '4' THEN SUM(T2.VMANT) ELSE 0.00 END VAN4,\r\n"
				+ "             CASE WHEN T2.GRUPO = '5' THEN SUM(T2.VMANT) ELSE 0.00 END VAN5,\r\n"
				+ "             CASE WHEN T2.GRUPO = '4' THEN SUM(T2.VMACT) ELSE 0.00 END VACT4,\r\n"
				+ "             CASE WHEN T2.GRUPO = '5' THEN SUM(T2.VMACT) ELSE 0.00 END VACT5\r\n"
				+ "      FROM (SELECT T1.GRUPO,\r\n" + "                   T1.SALINI,\r\n"
				+ "                   T1.SDO_INICIAL,\r\n" + "                   CASE\r\n"
				+ "                       WHEN T1.GRUPO = '4' THEN (T1.SDO_INICIAL + ABONOS_ANT - CARGOS_ANT)\r\n"
				+ "                       ELSE (T1.SDO_INICIAL - ABONOS_ANT + CARGOS_ANT) END VMANT,\r\n"
				+ "                   CASE\r\n"
				+ "                       WHEN T1.GRUPO = '4' THEN (T1.SALINI + ABONOS_ACT - CARGOS_ACT)\r\n"
				+ "                       ELSE (T1.SALINI - ABONOS_ACT + CARGOS_ACT) END VMACT\r\n"
				+ "            FROM (\r\n" + "                SELECT SUBSTR(C.CUENTA, 1, 1) GRUPO,\r\n"
				+ "                         C.CUENTA CUENTA,\r\n" + " C.SALINI SALINI,\r\n"
				+ "                         EA.SDO_INICIAL SDO_INICIAL,\r\n").append(" 	SUM( ")
				.append(abonosAnt.substring(0, abonosAnt.length() - 1)).append(" ) ABONOS_ANT,\r\n").append(" 	SUM( ")
				.append(cargosAnt.substring(0, cargosAnt.length() - 1)).append(" ) CARGOS_ANT,\r\n").append(" 	SUM( ")
				.append(cargosAct.substring(0, cargosAct.length() - 1)).append(" ) CARGOS_ACT,\r\n" + " 	SUM( ")
				.append(abonosAct.substring(0, abonosAct.length() - 1)).append(" ) ABONOS_ACT\r\n")
				.append("                  FROM CUENTA C\r\n"
						+ "                    LEFT JOIN TW_EJERCICIO_ANTERIOR EA ON C.CUENTA = EA.CUENTA   AND\r\n"
						+ "														C.SCTA = EA.SCTA       AND\r\n"
						+ "														C.SSCTA = EA.SSCTA     AND\r\n"
						+ "														C.SSSCTA = EA.SSSCTA   AND\r\n"
						+ "														C.SSSSCTA = EA.SSSSCTA\r\n"
						+ "                  WHERE C.IDSECTOR = 2\r\n"
						+ "                    AND (((SUBSTR(C.CUENTA, 1, 1) = 4 AND\r\n"
						+ "                           SUBSTR(C.CUENTA, 4, 1) <> '0')\r\n"
						+ "                      OR\r\n"
						+ "                          (SUBSTR(C.CUENTA, 1, 1) = 5 AND\r\n"
						+ "                           SUBSTR(C.CUENTA, 2, 1) > 0)\r\n"
						+ "                             ) AND\r\n" + "                         C.SCTA = '')\r\n"
						+ "                  GROUP BY C.CUENTA, C.SALINI, EA.SDO_INICIAL\r\n"
						+ "                  ORDER BY C.CUENTA) T1) T2\r\n" + "      GROUP BY T2.GRUPO) T3");

		System.out.println("Query3211: " + query.toString());
		return query.toString();
	}

	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(getMesSelected(), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			PrimeFaces.current().executeScript(DOWNLOAD_XLS);
		}
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(getMesSelected(), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.createFilePdfInline();
		}
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
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
