package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

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

// TODO: Auto-generated Javadoc
/**
 * The Class EdoFlujoEfecMB.
 */
@ManagedBean(name = "edoFlujoEfecMB")
@ViewScoped
public class EdoFlujoEfecMB extends ReportePeriodos {

	private static final String OUTPUT_REPORT_NAME = "EFE";
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_GENERIC";
	private static final String CLAVE_REPORT = "RPT_CONAC_GENERIC";

	private StreamedContent fileTxt;

	@ManagedProperty("#{executePlService}")
	protected ExecutePlService executePlService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "EdoFlujoEfec";
		endFilename = jasperReporteName + ".pdf";
		this.acumulado = Boolean.TRUE;
		this.mesAnterior = true;
		changePeriodo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport("CONAC-EstadoFlujosEfectivo", getMesSelected(), conctb, reporte);
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		parameters.put("pLastDay", getLastDayByAnoEmp(getMesSelected(), conctb.getAnoemp()));
		parameters.put("pDescripM", getNombreMesSelected().toUpperCase());
		parameters.put("pDescripMInicial", getNombreMesInicial().toUpperCase());
		parameters.put("pAn", conctb.getAnoemp().toString());
		parameters.put("pIdsector", this.getUserDetails().getIdSector());
		parameters.put("pMesFinal", getMesSelected());
		parameters.put("pMesInicial", getMesInicial());
		parameters.put("dependenciaName", conctb.getNomDep());
		parameters.put("queryFlujosActividadesOperacion",
				this.generaQueryFlujosActividadesOperacion(getMesInicial(), getMesSelected()));
		parameters.put("queryFlujosActividadesInversion",
				this.generaQueryFlujosActividadesInversion(getMesInicial(), getMesSelected()));
		parameters.put("queryFlujosActividadesFinanciamiento",
				this.generaQueryFlujosActividadesFinanciamiento(getMesInicial(), getMesSelected()));
		parameters.put("queryEfectivoEquivalentes",
				this.generaQueryEfectivoEquivalentes(getMesInicial(), getMesSelected()));
		parameters.put("imagenPathRigth", conctb.getImagePathRigth());
		parameters.put("imagenPathLeft", conctb.getImagePathLeft());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		return parameters;
	}

	private String generaQueryFlujosActividadesOperacion(Integer mesInicial, Integer mesFinal) {

		StringBuilder query = new StringBuilder();
		StringBuilder cargosActual = new StringBuilder();
		StringBuilder abonosActual = new StringBuilder();
		StringBuilder cargosAnterior = new StringBuilder();
		StringBuilder abonosAnterior = new StringBuilder();
		Integer sector = this.getUserDetails().getIdSector();

		for (int i = mesInicial; i <= mesFinal; i++) {
			cargosActual.append(" CU.CARGOS_" + i + " +");
			abonosActual.append(" CU.ABONOS_" + i + " +");
			cargosAnterior.append(" EA.CARGOS_" + i + " +");
			abonosAnterior.append(" EA.ABONOS_" + i + " +");
		}

		query.append("SELECT 1 GRUPO, T1.CUENTA, MA.NOMMAY, T1.ACUMULADO_ACTUAL, T1.ACUMULADO_ANTERIOR ")
				.append(" FROM ( ").append("SELECT SUBSTR(CU.CUENTA,1,3)||'0' CUENTA, SUM(( ")
				.append(abonosActual.substring(0, abonosActual.length() - 1)).append(" ) - ( ")
				.append(cargosActual.substring(0, cargosActual.length() - 1))
				.append(" ) + CU.SALINI) ACUMULADO_ACTUAL, SUM(( ")
				.append(abonosAnterior.substring(0, abonosAnterior.length() - 1)).append(" ) - ( ")
				.append(cargosAnterior.substring(0, cargosAnterior.length() - 1))
				.append(" ) + EA.SDO_INICIAL) ACUMULADO_ANTERIOR ").append(" FROM CUENTA CU ")
				.append(" LEFT JOIN TW_EJERCICIO_ANTERIOR EA ON ")
				.append(" CU.CUENTA = EA.CUENTA AND CU.SCTA = EA.SCTA AND CU.SSCTA = EA.SSCTA AND ")
				.append(" CU.SSSCTA = EA.SSSCTA AND CU.SSSSCTA = EA.SSSSCTA ")
				.append(" WHERE SUBSTR(CU.CUENTA,1,4) = '4151' AND CU.SCTA = '' AND CU.IDSECTOR= ").append(sector)
				.append(" GROUP BY SUBSTR(CU.CUENTA,1,3)||'0' \r\n").append("UNION \r\n")
				.append("SELECT SUBSTR(CU.CUENTA,1,3)||'0', SUM(( ")
				.append(abonosActual.substring(0, abonosActual.length() - 1)).append(" ) - ( ")
				.append(cargosActual.substring(0, cargosActual.length() - 1))
				.append(" ) + CU.SALINI) ACUMULADO_ACTUAL, SUM(( ")
				.append(abonosAnterior.substring(0, abonosAnterior.length() - 1)).append(" ) - ( ")
				.append(cargosAnterior.substring(0, cargosAnterior.length() - 1))
				.append(" ) + EA.SDO_INICIAL) ACUMULADO_ANTERIOR ").append("FROM CUENTA CU ")
				.append(" LEFT JOIN TW_EJERCICIO_ANTERIOR EA ON ")
				.append(" CU.CUENTA = EA.CUENTA AND SUBSTR(CU.SCTA,6) = EA.SCTA AND SUBSTR(CU.SSCTA,6) = EA.SSCTA AND")
				.append(" CU.SSSCTA = EA.SSSCTA AND CU.SSSSCTA = EA.SSSSCTA ")
				.append(" WHERE SUBSTR(CU.CUENTA,1,2) = '43' AND CU.SSSCTA <> '' AND CU.IDSECTOR= ").append(sector)
				.append(" GROUP BY SUBSTR(CU.CUENTA,1,3)||'0' \r\n").append("UNION \r\n")
				.append("SELECT SUBSTR(CU.CUENTA,1,3)||'0', SUM(( ")
				.append(abonosActual.substring(0, abonosActual.length() - 1)).append(" ) - ( ")
				.append(cargosActual.substring(0, cargosActual.length() - 1))
				.append(" ) + CU.SALINI) ACUMULADO_ACTUAL, SUM(( ")
				.append(abonosAnterior.substring(0, abonosAnterior.length() - 1)).append(" ) - ( ")
				.append(cargosAnterior.substring(0, cargosAnterior.length() - 1))
				.append(" ) + EA.SDO_INICIAL) ACUMULADO_ANTERIOR ").append(" FROM CUENTA CU")
				.append(" LEFT JOIN TW_EJERCICIO_ANTERIOR EA ON ")
				.append(" CU.CUENTA = EA.CUENTA AND SUBSTR(CU.SCTA,6) = EA.SCTA AND SUBSTR(CU.SSCTA,6) = EA.SSCTA AND ")
				.append(" CU.SSSCTA = EA.SSSCTA AND CU.SSSSCTA = EA.SSSSCTA")
				.append(" WHERE SUBSTR(CU.CUENTA,1,2) = '42' AND CU.SSSCTA <> '' AND CU.IDSECTOR= ").append(sector)
				.append(" GROUP BY SUBSTR(CU.CUENTA,1,3)||'0' \r\n").append("UNION \r\n")
				.append("SELECT CU.CUENTA, SUM(( ").append(abonosActual.substring(0, abonosActual.length() - 1))
				.append(" ) - ( ").append(cargosActual.substring(0, cargosActual.length() - 1))
				.append(" ) + CU.SALINI) ACUMULADO_ACTUAL, SUM(( ")
				.append(abonosAnterior.substring(0, abonosAnterior.length() - 1)).append(" ) - ( ")
				.append(cargosAnterior.substring(0, cargosAnterior.length() - 1))
				.append(" ) + EA.SDO_INICIAL) ACUMULADO_ANTERIOR ").append(" FROM CUENTA CU ")
				.append(" LEFT JOIN TW_EJERCICIO_ANTERIOR EA ON ")
				.append(" CU.CUENTA = EA.CUENTA AND CU.SCTA = EA.SCTA AND ")
				.append(" CU.SSCTA = EA.SSCTA AND CU.SSSCTA = EA.SSSCTA AND CU.SSSSCTA = EA.SSSSCTA")
				.append(" WHERE (SUBSTR(CU.CUENTA,1,2) = '41' OR CU.CUENTA = '4210') AND SUBSTR(CU.CUENTA,4,1) = '0' AND")
				.append(" CU.CUENTA NOT IN ('4100','4150') AND CU.SCTA = '' AND CU.IDSECTOR= ").append(sector)
				.append(" GROUP BY CU.CUENTA ").append(" ) T1 ")
				.append(" INNER JOIN MAYCTA MA ON T1.CUENTA = MA.CUENTA \r\n").append("UNION \r\n")
				.append(" SELECT 2, CLVGAS, NOMBRE, NVL(ACUMULADO_ACTUAL,0) ACUMULADO_ACTUAL, NVL(ACUMULADO_ANTERIOR,0) ACUMULADO_ANTERIOR ")
				.append("FROM ( ").append("SELECT CLVGAS, NOMGAS NOMBRE, ACUMULADO_ACTUAL, SUM(( ")
				.append(cargosAnterior.substring(0, cargosAnterior.length() - 1)).append(" ) - ( ")
				.append(abonosAnterior.substring(0, abonosAnterior.length() - 1))
				.append(" ) +	EA.SDO_INICIAL) ACUMULADO_ANTERIOR ").append(" FROM ( ")
				.append("SELECT CLVGAS, NOMGAS, CU.CUENTA, SUM(( ")
				.append(cargosActual.substring(0, cargosActual.length() - 1)).append(" ) - ( ")
				.append(abonosActual.substring(0, abonosActual.length() - 1))
				.append(" ) + CU.SALINI) ACUMULADO_ACTUAL ").append(" FROM NATGAS N")
				.append(" INNER JOIN CUENTA CU ON ")
				.append(" N.CLVGAS = SUBSTR(CU.SSSCTA,1,1)||'000' AND CU.CUENTA ='5100' AND CU.IDSECTOR=N.IDSECTOR ")
				.append(" WHERE N.IDSECTOR=").append(sector).append(" AND SUBSTR(N.CLVGAS,2,3)='000' ")
				.append(" GROUP BY CLVGAS, NOMGAS, CU.CUENTA ) T1 ").append(" RIGHT JOIN TW_EJERCICIO_ANTERIOR EA ON ")
				.append(" T1.CUENTA = EA.CUENTA AND SUBSTR(T1.CLVGAS,1,1) = SUBSTR(EA.SSSCTA,1,1) ")
				.append(" WHERE CLVGAS <> '7000' ")
				.append(" GROUP BY CLVGAS, NOMGAS, ACUMULADO_ACTUAL \r\n" + " UNION \r\n")
				.append("SELECT  CLVGAS, NOMGAS, ACUMULADO_ACTUAL, SUM(( ")
				.append(cargosAnterior.substring(0, cargosAnterior.length() - 1)).append(" ) - ( ")
				.append(abonosAnterior.substring(0, abonosAnterior.length() - 1))
				.append(" ) + EA.SDO_INICIAL) ACUMULADO_ANTERIOR FROM ( ")
				.append(" SELECT NA.CLVGAS, NA.NOMGAS, SUM(( ")
				.append(cargosActual.substring(0, cargosActual.length() - 1)).append(" ) - ( ")
				.append(abonosActual.substring(0, abonosActual.length() - 1))
				.append(" ) + CU.SALINI) ACUMULADO_ACTUAL ").append(" FROM NATGAS NA ")
				.append(" LEFT JOIN CUENTA CU ON ")
				.append(" SUBSTR(NA.CLVGAS,1,2) = SUBSTR(CU.SSSCTA,1,2) AND NA.IDSECTOR =  CU.IDSECTOR AND SUBSTR(CU.CUENTA,1,1) = '5' ")
				.append(" WHERE	(SUBSTR(NA.CLVGAS,1,1) = '4' OR SUBSTR(NA.CLVGAS,1,2) IN ('81','83', '85') ) AND ")
				.append(" SUBSTR(NA.CLVGAS,3,2) = '00' AND NA.CLVGAS <> '4000' AND NA.IDSECTOR= ").append(sector)
				.append(" GROUP BY NA.CLVGAS, NA.NOMGAS ").append(" )T1 ")
				.append(" LEFT JOIN TW_EJERCICIO_ANTERIOR EA ON SUBSTR(T1.CLVGAS,1,2) = SUBSTR(EA.SSSCTA,1,2) AND SUBSTR(EA.CUENTA,1,1) = '5' ")
				.append(" GROUP BY CLVGAS, NOMGAS, ACUMULADO_ACTUAL	\r\n").append("UNION \r\n")
				.append(" SELECT '9999', 'Otras Aplicaciones de Operación', SUM(( ")
				.append(cargosActual.substring(0, cargosActual.length() - 1)).append(" ) - ( ")
				.append(abonosActual.substring(0, abonosActual.length() - 1))
				.append(" ) + CU.SALINI) ACUMULADO_ACTUAL, SUM(( ")
				.append(cargosAnterior.substring(0, cargosAnterior.length() - 1)).append(" ) - ( ")
				.append(abonosAnterior.substring(0, abonosAnterior.length() - 1))
				.append(" ) + EA.SDO_INICIAL) ACUMULADO_ANTERIOR ").append(" FROM CUENTA CU ")
				.append(" LEFT JOIN TW_EJERCICIO_ANTERIOR EA ON CU.CUENTA = EA.CUENTA AND EA.SCTA='' ")
				.append(" WHERE CU.CUENTA IN ('5500') AND CU.SCTA='' AND CU.IDSECTOR=").append(sector)
				.append(" GROUP BY '9999', 'Otras Aplicaciones de Operación' ").append(") T2 ")
				.append(" ORDER BY 1,2 ");
		System.out.println("queryFlujosActividadesOperacion -> " + query.toString());

		return query.toString();
	}

	private String generaQueryFlujosActividadesInversion(Integer mesInicial, Integer mesFinal) {

		StringBuilder query = new StringBuilder();
		StringBuilder cargosActual = new StringBuilder();
		StringBuilder abonosActual = new StringBuilder();
		StringBuilder cargosAnterior = new StringBuilder();
		StringBuilder abonosAnterior = new StringBuilder();
		Integer sector = this.getUserDetails().getIdSector();

		for (int i = mesInicial; i <= mesFinal; i++) {
			cargosActual.append(" C.CARGOS_" + i + " +");
			abonosActual.append(" C.ABONOS_" + i + " +");
			cargosAnterior.append(" A.CARGOS_" + i + " +");
			abonosAnterior.append(" A.ABONOS_" + i + " +");
		}

		query.append("SELECT GRUPO, ORDEN, CUENTA, NOMBRE, ABS(SUM(MONTOS)) ACTUAL FROM (\r\n" + "SELECT *\r\n"
				+ "FROM (VALUES (1, 1, '0000', 'Bienes Inmuebles, Infraestructura y Construcciones en Proceso', 0.00),\r\n"
				+ "             (1, 2, '0000', 'Bienes Muebles', 0.00),\r\n"
				+ "             (2, 4, '0000', 'Bienes Inmuebles, Infraestructura y Construcciones en Proceso', 0.00),\r\n"
				+ "             (2, 6, '0000', 'Otras Aplicaciones de Inversión', 0.00)) AS TEMP (GRUPO, ORDEN, CUENTA, NOMBRE, MONTOS)\r\n"
				+ "UNION\r\n" + "SELECT CASE\r\n" + "           WHEN CUENTA = '1000' THEN 1\r\n"
				+ "           WHEN CUENTA = '1240' THEN 2 END GRUPO,\r\n" + "       CASE\r\n"
				+ "           WHEN CUENTA = '1000' THEN 3\r\n" + "           WHEN CUENTA = '1240' THEN 5 END ORDEN,\r\n"
				+ "       CUENTA,\r\n" + "       CASE\r\n"
				+ "           WHEN CUENTA = '1000' THEN 'Otros Orígenes de Inversión'\r\n"
				+ "           WHEN CUENTA = '1240' THEN  NOMCTA END NOMCTA,\r\n"
				+ "       (ACTUAL - ANTERIOR)                 MONTO\r\n"
				+ "FROM (SELECT DECODE(CUENTA, '1120', '1000','1260','1000',CUENTA) CUENTA,\r\n"
				+ "             NOMCTA,\r\n"
				+ "             DECODE(CUENTA, '1260', ((SDO_INICIAL - CARGOS_ANT + ABONOS_ANT) * -1),\r\n"
				+ "                                    (SDO_INICIAL + CARGOS_ANT - ABONOS_ANT)) ANTERIOR,\r\n"
				+ "             DECODE(CUENTA, '1260', ((SALINI - CARGOS_ACT + ABONOS_ACT) * -1) ,\r\n"
				+ "                                    (SALINI + CARGOS_ACT - ABONOS_ACT))              ACTUAL\r\n"
				+ "      FROM (SELECT C.NOMCTA,\r\n" + "                   C.CUENTA,\r\n"
				+ "                   C.SALINI,\r\n" + "                   A.SDO_INICIAL,\r\n" + "                   ("
				+ abonosAnterior.substring(0, abonosAnterior.length() - 1) + ") ABONOS_ANT,\r\n"
				+ "                   (" + cargosAnterior.substring(0, cargosAnterior.length() - 1)
				+ ") CARGOS_ANT,\r\n" + "                   (" + abonosActual.substring(0, abonosActual.length() - 1)
				+ ") ABONOS_ACT,\r\n" + "                   (" + cargosActual.substring(0, cargosActual.length() - 1)
				+ ") CARGOS_ACT\r\n" + "            FROM CUENTA C\r\n"
				+ "                     LEFT JOIN TW_EJERCICIO_ANTERIOR A\r\n"
				+ "                               ON C.CUENTA = A.CUENTA AND C.SCTA = A.SCTA AND C.SSCTA = A.SSCTA AND\r\n"
				+ "                                  C.SSSCTA = A.SSSCTA AND C.SSSSCTA = A.SSSSCTA\r\n"
				+ "            WHERE C.CUENTA IN ('1120', '1240', '1260')\r\n" + "              AND C.SCTA = ''\r\n"
				+ "              AND C.IDSECTOR = " + sector + ") T1) T2\r\n" + "ORDER BY GRUPO, 2 )T3\r\n"
				+ "GROUP BY GRUPO, ORDEN, CUENTA, NOMBRE ");

		System.out.println("queryFlujosActividadesInversion -> " + query.toString());

		return query.toString();
	}

	private String generaQueryFlujosActividadesFinanciamiento(Integer mesInicial, Integer mesFinal) {
		StringBuilder query = new StringBuilder();
		StringBuilder cargosActual = new StringBuilder();
		StringBuilder abonosActual = new StringBuilder();
		StringBuilder cargosAnterior = new StringBuilder();
		StringBuilder abonosAnterior = new StringBuilder();
		Integer sector = this.getUserDetails().getIdSector();

		for (int i = mesInicial; i <= mesFinal; i++) {
			cargosActual.append(" CU.CARGOS_" + i + " +");
			abonosActual.append(" CU.ABONOS_" + i + " +");
			cargosAnterior.append(" EA.CARGOS_" + i + " +");
			abonosAnterior.append(" EA.ABONOS_" + i + " +");
		}

		query.append("SELECT *\r\n" + "FROM (VALUES (1, 1, 'Endeudamiento Neto', 0.00),\r\n"
				+ "             (1, 2, 'Interno', 0.00),\r\n" + "             (1, 3, 'Externo', 0.00),\r\n"
				+ "             (1, 4, 'Otros Orígenes de Financiamiento', 0.0),\r\n"
				+ "             (2, 5, 'Servicios de la Deuda', 0.00),\r\n"
				+ "             (2, 6, 'Interno', 0.00),\r\n"
				+ "             (2, 7, 'Externo', 0.00)) AS TEMP (GRUPO, ORDEN, NOMBRE, ACTUAL)\r\n" + "UNION\r\n"
				+ "SELECT 2,\r\n" + "       8,\r\n" + "       'Otras Aplicaciones de Financiamiento',\r\n"
				+ "       ABS(MONTO) ACTUAL\r\n" + "FROM (SELECT SUM(PERIODO_ACTUAL) - SUM(PERIODO_ANTERIOR) MONTO\r\n"
				+ "      FROM (SELECT CUENTA,\r\n"
				+ "                   (SDO_INICIAL - CARGOS_ANT + ABONOS_ANT)                       PERIODO_ANTERIOR,\r\n"
				+ "                   DECODE(CUENTA, '3211', 0, (SALINI - CARGOS_ACT + ABONOS_ACT)) PERIODO_ACTUAL\r\n"
				+ "            FROM (SELECT CU.CUENTA,\r\n" + "                         CU.SALINI,\r\n"
				+ "                         EA.SDO_INICIAL,\r\n" + "                   ("
				+ abonosAnterior.substring(0, abonosAnterior.length() - 1) + ") ABONOS_ANT,\r\n"
				+ "                   (" + cargosAnterior.substring(0, cargosAnterior.length() - 1)
				+ ") CARGOS_ANT,\r\n" + "                   (" + abonosActual.substring(0, abonosActual.length() - 1)
				+ ") ABONOS_ACT,\r\n" + "                   (" + cargosActual.substring(0, cargosActual.length() - 1)
				+ ") CARGOS_ACT\r\n" + "                  FROM CUENTA CU\r\n"
				+ "                           LEFT JOIN TW_EJERCICIO_ANTERIOR EA\r\n"
				+ "                                     ON CU.CUENTA = EA.CUENTA AND CU.SCTA = EA.SCTA AND CU.SSCTA = EA.SSCTA AND\r\n"
				+ "                                        CU.SSSCTA = EA.SSSCTA AND\r\n"
				+ "                                        CU.SSSSCTA = EA.SSSSCTA\r\n"
				+ "                  WHERE (CU.CUENTA IN ('3211', '3221', '3231') OR SUBSTR(CU.CUENTA, 1, 3) = '211')\r\n"
				+ "                    AND CU.SCTA = ''\r\n" + "                    AND CU.IDSECTOR = " + sector
				+ "                    AND SUBSTR(CU.CUENTA, 4, 1) <> '0') T1\r\n"
				+ "            WHERE (SALINI <> 0 OR CARGOS_ACT <> 0 OR ABONOS_ACT <> 0)\r\n"
				+ "               OR CUENTA = '3211') T2) T3\r\n" + "ORDER BY GRUPO, 2");

		System.out.println("queryFlujosActividadesFinanciamiento -> " + query.toString());

		return query.toString();
	}

	private String generaQueryEfectivoEquivalentes(Integer mesInicial, Integer mesFinal) {
		StringBuilder query = new StringBuilder();
		StringBuilder cargosActual = new StringBuilder();
		StringBuilder abonosActual = new StringBuilder();
		StringBuilder cargosAnterior = new StringBuilder();
		StringBuilder abonosAnterior = new StringBuilder();
		Integer sector = this.getUserDetails().getIdSector();

		for (int i = mesInicial; i <= mesFinal; i++) {
			cargosActual.append(" C.CARGOS_" + i + " +");
			abonosActual.append(" C.ABONOS_" + i + " +");
			cargosAnterior.append(" A.CARGOS_" + i + " +");
			abonosAnterior.append(" A.ABONOS_" + i + " +");
		}

		query.append("SELECT CUENTA, NOMCTA,   (SDO_INICIAL + CARGOS_ANT - ABONOS_ANT) PERIODO_ANTERIOR,\r\n"
				+ "            (SALINI + CARGOS_ACT - ABONOS_ACT) PERIODO_ACTUAL\r\n"
				+ "FROM (SELECT SUBSTR(C.CUENTA, 1, 2)                    GRUPO,\r\n" + "             C.STACTA,\r\n"
				+ "             C.NOMCTA,\r\n" + "             C.CUENTA,\r\n" + "             C.SALINI,\r\n"
				+ "             A.SDO_INICIAL,\r\n" + "             ("
				+ abonosAnterior.substring(0, abonosAnterior.length() - 1) + ") ABONOS_ANT,\r\n" + "             ("
				+ cargosAnterior.substring(0, cargosAnterior.length() - 1) + ") CARGOS_ANT,\r\n" + "             ("
				+ abonosActual.substring(0, abonosActual.length() - 1) + ") ABONOS_ACT,\r\n" + "             ("
				+ cargosActual.substring(0, cargosActual.length() - 1) + ") CARGOS_ACT\r\n" + "      FROM CUENTA C\r\n"
				+ "               LEFT JOIN TW_EJERCICIO_ANTERIOR A\r\n"
				+ "                         ON C.CUENTA = A.CUENTA AND C.SCTA = A.SCTA AND C.SSCTA = A.SSCTA AND C.SSSCTA = A.SSSCTA AND\r\n"
				+ "                            C.SSSSCTA = A.SSSSCTA\r\n" + "      WHERE C.CUENTA = '1110'\r\n"
				+ "        AND C.SCTA = ''\r\n" + "        AND C.IDSECTOR =" + sector + "      ) T1\r\n"
				+ "WHERE SALINI <> 0\r\n" + "   OR CARGOS_ACT <> 0\r\n" + "   OR ABONOS_ACT <> 0\r\n"
				+ "ORDER BY CUENTA");

		System.out.println("queryEfectivoEquivalentes -> " + query.toString());

		return query.toString();
	}

	private String generaQueryTXT() {
		StringBuilder query = new StringBuilder();
		String flujosOperacion = this.generaQueryFlujosActividadesOperacion(getMesInicial(), getMesSelected());

		query.append(
				"SELECT '\"'||TRIM(TX.NOMMAY)||'\"|\"'|| DECODE(TX.CUENTA, '0000','',TRIM(TO_CHAR(TX.ACUMULADO_ACTUAL,'99999999999999999990.99'))) ||'\"'\r\n"
						+ "	,1 FROM (\r\n")
				.append(flujosOperacion.substring(0, flujosOperacion.length() - 15))
				.append(" UNION\r\n"
						+ "SELECT * FROM ( VALUES 	(0, '0000', 'Flujos de Efectivo de las Actividades de Operación', 0.00, 0.00),\r\n"
						+ "                			(1, '0000', 'Origen', 0.00, 0.00),\r\n"
						+ "  		              	(2, '0000', 'Aplicación', 0.00, 0.00),\r\n"
						+ "                			(3, '0000', 'Flujos Netos por Actividades de Opreación', 0.00, 0.00)\r\n"
						+ ") AS TEMP (GRUPO, CUENTA, NOMBRE, ACTUAL, ANTERIOR)\r\n" + "ORDER BY 1,2 \r\n" + ")TX ")
				.append(" UNION ALL ")
				.append("SELECT '\"'||TRIM(TX.NOMBRE)||'\"|\"'|| DECODE(TX.ORDEN, 0,'',TRIM(TO_CHAR(TX.ACTUAL,'99999999999999999990.99'))) ||'\"'\r\n"
						+ "	,2 FROM (\r\n")
				.append(this.generaQueryFlujosActividadesInversion(getMesInicial(), getMesSelected()))
				.append("UNION \r\n"
						+ "SELECT * FROM ( VALUES 	(0, 0,'0000', 'Flujos de Efectivo de las Actividades de Inversión', 0.00),\r\n"
						+ "                			(1, 0,'0000', 'Origen', 0.00),\r\n"
						+ "  		              	(2, 0,'0000', 'Aplicación', 0.00),\r\n"
						+ "                			(3, 0,'0000', 'Flujos Netos por Actividades de Inversión', 0.00)\r\n"
						+ ") AS TEMP (GRUPO, ORDEN, CUENTA, NOMBRE, ACTUAL)\r\n" + "ORDER BY 1, 2\r\n" + ")TX ")
				.append(" UNION ALL ")
				.append("SELECT '\"'||TRIM(TX.NOMBRE)||'\"|\"'|| DECODE(TX.ORDEN, 0,'',TRIM(TO_CHAR(TX.ACTUAL,'99999999999999999990.99'))) ||'\"', 3 FROM (\r\n"
						+ "SELECT * FROM ( VALUES 	(0, 0, 'Flujos de Efectivo de las Actividades de Financiamiento', 0.00),\r\n"
						+ "                			(1, 0, 'Origen', 0.00),\r\n"
						+ "  		              	(2, 0, 'Aplicación', 0.00),\r\n"
						+ "                			(3, 0, 'Flujos Netos de Efectivo por Actividades de Financiamiento', 0.00)\r\n"
						+ ") AS TEMP (GRUPO, ORDEN,  NOMBRE, ACTUAL)\r\n" + "UNION\r\n")
				.append(this.generaQueryFlujosActividadesFinanciamiento(getMesInicial(), getMesSelected()))
				.append(" )TX\r\n").append(" ORDER BY 2 ");

		System.out.println("QueryTXT-> " + query.toString());
		return query.toString();
	}

	Map<String, Object> out;
	InputStream stream = null;

	public StreamedContent getFileTxt() {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		String filename = getNameFileReport(OUTPUT_REPORT_NAME, getMesSelected(), conctb, reporte);

		parameter.addValue("i_query", this.generaQueryTXT());
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

	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
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

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
