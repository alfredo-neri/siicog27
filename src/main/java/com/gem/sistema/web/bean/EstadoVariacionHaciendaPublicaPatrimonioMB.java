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
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;

import org.primefaces.PrimeFaces;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.dto.EdoSF3211DTO;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.EdoSF3211Service;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadoVariacionHaciendaPublicaPatrimonioMB.
 */
@Component("estadoVariacionHaciendaPublicaPatrimonioMB")
@Scope("view")
public class EstadoVariacionHaciendaPublicaPatrimonioMB extends ReportePeriodos {

	private static final String OUTPUT_REPORT_NAME = "CONAC-EstadoVariacionHaciendaPublica";
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_EDO_VARIACION_HACIENDA_PUBLICA_PATRIMONIO";
	private static final String CLAVE_REPORT = "RPT_CONAC_GENERIC";
	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";

	private StreamedContent fileTxt;
	
	/** The conctb. */
	private Conctb conctb;

	/** The conctb repository. */
	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@Value("#{edoSF3211Service}")
	private EdoSF3211Service edoSF3211Service;

	@Value("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;
	
	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;
	
	@Value("#{executePlService}")
	protected ExecutePlService executePlService;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct EstadoVariacionHaciendaPublicaPatrimonioMB ");
		// reportId = 2;
		jasperReporteName = "HaciendaPublicaPatrimonio";
		endFilename = jasperReporteName + ".pdf";
		this.acumulado = true;
		this.mesAnterior = true;
		changePeriodo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);
		EdoSF3211DTO edoSF3211DTO = edoSF3211Service.executeQuery(sector, getMesInicial(), getMesSelected());
		
		endFilename = getNameFileReport(OUTPUT_REPORT_NAME, getMesSelected(), conctb, reporte);
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		parameters.put("year", conctb.getAnoemp());
		parameters.put("pDesMes", getNombreMesSelected().toUpperCase());
		parameters.put("pLastDay", getLastDayByAnoEmp(getMesSelected(), conctb.getAnoemp()));
		parameters.put("3211Actual", edoSF3211DTO.getTotalAct());
		parameters.put("sqlPatrimonio", this.generaQueryPatrimonio(sector, conctb.getAnoemp()));
		parameters.put("pDesMesInicial", getNombreMesInicial().toUpperCase());
		parameters.put("dependenciaName", conctb.getNomDep());
		parameters.put("imagenPathLeft", conctb.getImagePathLeft());
		parameters.put("imagenPathRight", conctb.getImagePathRigth());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return parameters;
	}

	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(getMesSelected(), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(getMesSelected(), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(getMesSelected(), null, this.getUserDetails().getIdSector());

			PrimeFaces.current().executeScript(DOWNLOAD_XLS);
		}
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(getMesSelected(), null,
				this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(getMesSelected(), null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(getMesSelected(), null, this.getUserDetails().getIdSector());
			this.createFilePdfInline();
			;
		}
	}

	/**
	 * Genera query patrimonio.
	 *
	 * @param idsector the idsector
	 * @param mes      the mes
	 * @return the string
	 */
	public String generaQueryPatrimonio(Integer idsector, Integer year) {
		StringBuilder cargosAnt = new StringBuilder();
		StringBuilder abonosAnt = new StringBuilder();
		StringBuilder cargosAct = new StringBuilder();
		StringBuilder abonosAct = new StringBuilder();
		StringBuilder sSqlP = new StringBuilder();

		if (getMesSelected() == 1) {
			abonosAnt.append(" 0.00 ");
			cargosAnt.append(" 0.00 ");
			abonosAct.append(" 0.00 ");
			cargosAct.append(" 0.00 ");
		} else {

			for (int i = getMesInicial(); i <= getMesSelected(); i++) {
				cargosAnt.append(" A.CARGOS_" + i + "+");
				abonosAnt.append(" A.ABONOS_" + i + "+");
				cargosAct.append(" C.CARGOS_" + i + "+");
				abonosAct.append(" C.ABONOS_" + i + "+");
			}
		}

		sSqlP.append("SELECT ORDEN, GRUPO, CUENTA, NOMCTA, ")
				.append("		DECODE(CUENTA, '3110', SALDO, 0) COLUMN_1, ")
				.append("		CASE WHEN GRUPO = '32' AND CUENTA <> '3210' THEN ")
				.append("		SALDO ELSE 0	END COLUMN_2, ")
//				.append("		DECODE(CUENTA, '3210', SALDO, 0) COLUMN_3, ")
//				.append("		DECODE(CUENTA, '3210', FN_GET_CUENTA_3211("+ getMesSelected() + ", 'a') , 0) COLUMN_3, ")
				.append("		CASE WHEN ORDEN = " + year +" AND CUENTA = '3210' THEN\r\n"
						+ "            FN_GET_CUENTA_3211("+ getMesSelected() + ", 'a')\r\n"
						+ "           ELSE 0 END COLUMN_3, ")
				.append("		0 COLUMN_4 	FROM (" + "		SELECT 	").append(year)
				.append(" ORDEN, GRUPO, CUENTA, NOMCTA, CARGOS_ACTUAL + ABONOS_ACTUAL SALDO ").append("			FROM (")
				.append("	SELECT 	SUBSTR(C.CUENTA,1,2) GRUPO, C.CUENTA, C.NOMCTA, C.STACTA, C.SALINI,")
				.append(abonosAct.substring(0, abonosAct.length() - 1)).append(" ABONOS_ACTUAL, ")
				.append(cargosAct.substring(0, cargosAct.length() - 1))
				.append(" CARGOS_ACTUAL " + "						FROM CUENTA C\r\n"
						+ "					WHERE 	SUBSTR(C.CUENTA,1,1) = 3 AND  C.SCTA = ''\r\n"
						+ "				)T1 UNION SELECT 	")
				.append(year - 1).append(", GRUPO, CUENTA, NOMCTA, CASE ")
				.append(" WHEN CUENTA = '3220' THEN  FN_GET_SALDO_INICIAL_CUENTA('3220','','','','',1) ")
				.append(" WHEN CUENTA = '3210' THEN 0 ELSE ")
				.append(" SDO_INICIAL - CARGOS_ANTERIOR + ABONOS_ANTERIOR END SALDO\r\n")
				.append("			FROM (\r\n"
						+ "					SELECT 	SUBSTR(A.CUENTA,1,2) GRUPO, A.CUENTA, C.NOMCTA,\r\n"
						+ "					 A.SDO_INICIAL,\r\n")
				.append(abonosAnt.substring(0, abonosAnt.length() - 1)).append(" ABONOS_ANTERIOR, ")
				.append(cargosAnt.substring(0, cargosAnt.length() - 1))
				.append(" CARGOS_ANTERIOR " + "						FROM TW_EJERCICIO_ANTERIOR A \r\n"
						+ "							INNER JOIN CUENTA C ON 	C.CUENTA = A.CUENTA AND\r\n"
						+ "													C.SCTA = A.SCTA AND\r\n"
						+ "													C.SSCTA = A.SSCTA AND\r\n"
						+ "													C.SSSCTA = A.SSSCTA AND\r\n"
						+ "													C.SSSSCTA = A.SSSSCTA\r\n"
						+ "					WHERE 	SUBSTR(A.CUENTA,1,1) = 3 AND  A.SCTA = ''\r\n"
						+ "				)T1 ORDER BY ORDEN, CUENTA )T2 WHERE SUBSTR(CUENTA,4) ='0' AND SUBSTR(CUENTA,3) <> '00'");

		System.out.println(sSqlP.toString());
		return sSqlP.toString();
	}
	
	private String generaQueryTXT(Integer sector, Integer year) {
		StringBuilder query = new StringBuilder();
		
		String principalQuery = generaQueryPatrimonio(sector, year);
		
		query.append("SELECT * FROM (\r\n").append(principalQuery).append(""
				
				+ "UNION ALL\r\n"
				+ "SELECT ORDEN,\r\n"
				+ "       GRUPO,\r\n"
				+ "'',\r\n"
				+ "DECODE(GRUPO,'31','Hacienda Pública / Patrimonio Contribuido Neto de ',"
				+ "             '32','Hacienda Pública / Patrimonio Generado Neto de ',"
				+ "             '33','Exceso o Insuficiencia en la Actualización de la Hacienda Pública / Patrimonio Neto de '"
				+ "      )||ORDEN,\r\n"
				+ "       SUM(COLUMN_1),\r\n"
				+ "       SUM(COLUMN_2),\r\n"
				+ "       SUM(COLUMN_3),\r\n"
				+ "       SUM(COLUMN_4)\r\n"
				+ "FROM (").append(principalQuery).append(" ) T3\r\n"
				+ "GROUP BY ROLLUP (ORDEN, GRUPO)\r\n"
				+ ")T4\r\n"
				+ "WHERE GRUPO IS NOT NULL\r\n"
				+ "ORDER BY ORDEN, GRUPO, CUENTA ");
		
		System.out.println("Query TXT ->" + query.toString());
		return query.toString();
	}

	/**
	 * Genera query 3211.
	 *
	 * @param idsector the idsector
	 * @param mes      the mes
	 * @return the string
	 */
	public String generaQuery3211(Integer idsector) {
		StringBuilder cargonant = new StringBuilder();
		StringBuilder abonosant = new StringBuilder();
		StringBuilder cargosact = new StringBuilder();
		StringBuilder abonosact = new StringBuilder();
		StringBuilder sSql = new StringBuilder();

		if (getMesSelected() == 1) {
			cargonant.append(" 0.00  ");
			abonosant.append(" 0.00  ");
		} else {

			for (int i = getMesInicial(); i < getMesSelected(); i++) {
				cargonant.append(" C.CARGOS_" + i + "+");
				abonosant.append(" C.ABONOS_" + i + "+");
			}
		}

		for (int i = getMesInicial(); i <= getMesSelected(); i++) {
			cargosact.append(" C.CARGOS_" + i + "+");
			abonosact.append(" C.ABONOS_" + i + "+");
		}

		sSql.append("SELECT (SUM(T3.VAN4)-SUM(T3.VAN5)) TOTAL_ANT,").append(" (SUM(T3.VACT4)-SUM(T3.VACT5)) TOTAL_ACT")
				.append(" FROM (SELECT ").append(" CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMANT) ELSE 0.00 END VAN4,")
				.append(" CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMANT) ELSE 0.00 END VAN5,")
				.append(" CASE WHEN T2.GRUPO='4' THEN SUM(T2.VMACT) ELSE 0.00 END VACT4,")
				.append(" CASE WHEN T2.GRUPO='5' THEN SUM(T2.VMACT)ELSE 0.00 END VACT5")
				.append(" FROM(SELECT  T1.GRUPO, ").append(" T1.SALINI,")
				.append(" CASE WHEN T1.GRUPO  ='4' THEN (T1.SALINI+ABONOS_ANT-CARGOS_ANT)")
				.append(" ELSE (T1.SALINI-ABONOS_ANT+CARGOS_ANT)").append(" END VMANT,")
				.append(" CASE WHEN T1.GRUPO  ='4' THEN (T1.SALINI+ABONOS_ACT-CARGOS_ACT)")
				.append(" ELSE (T1.SALINI-ABONOS_ACT+CARGOS_ACT)").append(" END VMACT")
				.append(" FROM (SELECT SUBSTR(C.CUENTA,1,1) GRUPO, ").append(" C.CUENTA CUENTA ,")
				.append(" C.SALINI SALINI,").append(" SUM(").append(cargonant.substring(1, cargonant.length() - 1))
				.append(") CARGOS_ANT,").append(" SUM(").append(abonosant.substring(1, abonosant.length() - 1))
				.append(") ABONOS_ANT,").append(" SUM(").append(cargosact.substring(1, cargosact.length() - 1))
				.append(") CARGOS_ACT,").append(" SUM(").append(abonosact.substring(1, abonosact.length() - 1))
				.append(") ABONOS_ACT").append(" FROM CUENTA C").append(" WHERE C.IDSECTOR=" + idsector)
				.append(" AND (((C.CUENTA >='4100' AND C.CUENTA<='4399' AND SUBSTR(C.CUENTA,4,1)<>'0')")
				.append(" OR (C.CUENTA IN ('5100','5200','5300','5400','5600','5700')))").append(" AND C.SCTA ='')")
				.append(" OR (C.CUENTA ='5500' AND C.SCTA<>'' AND C.SSCTA ='' AND C.NOTCTA=0)")
				.append(" GROUP BY C.CUENTA,").append(" C.SALINI").append(" ORDER BY C.CUENTA)T1)T2")
				.append(" GROUP BY T2.GRUPO )T3");
		System.out.println(sSql.toString());
		return sSql.toString();

	}

	Map<String, Object> out;
	InputStream stream = null;
	public StreamedContent getFileTxt() {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);
		EdoSF3211DTO edoSF3211DTO = edoSF3211Service.executeQuery(sector, getMesInicial(), getMesSelected());
		
		String filename = getNameFileReport("EVHP", getMesSelected(), conctb, reporte);
		
		parameter.addValue("i_sector", sector);
		parameter.addValue("i_filename", filename.substring(0, filename.length() - 4));
		parameter.addValue("i_3211_actual", edoSF3211DTO.getTotalAct());
		parameter.addValue("i_query_hacienda", this.generaQueryTXT(sector, conctb.getAnoemp()));

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

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	/**
	 * Gets the conctb.
	 *
	 * @return the conctb
	 */
	public Conctb getConctb() {
		return conctb;
	}

	/**
	 * Sets the conctb.
	 *
	 * @param conctb the new conctb
	 */
	public void setConctb(Conctb conctb) {
		this.conctb = conctb;
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

	public EdoSF3211Service getEdoSF3211Service() {
		return edoSF3211Service;
	}

	public void setEdoSF3211Service(EdoSF3211Service edoSF3211Service) {
		this.edoSF3211Service = edoSF3211Service;
	}

}
