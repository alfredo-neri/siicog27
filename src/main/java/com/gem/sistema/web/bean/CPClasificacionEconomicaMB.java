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

/**
 * @author Alfredo Neri
 *
 */
@Component("cPClasificacionEconomicaMB")
@Scope("view")
public class CPClasificacionEconomicaMB extends ReportePeriodos {
	private static final String OUTPUT_REPORT_NAME = "EAEPECE";
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_GENERIC";
	private static final String CLAVE_REPORT = "RPT_CONAC_GENERIC_EGRESOS";

	final static private String VW1 = "VI_CLASIFICACION_ECONOMICA_T1";
	final static private String VW2 = "VI_CLASIFICACION_ECONOMICA_T2";
	final static private String VW3 = "VI_CLASIFICACION_ECONOMICA_T3";
	final static private String VW4 = "VI_CLASIFICACION_ECONOMICA_T4";

	private StreamedContent fileTxt;
	private String reporte;
	private Integer noDecimales;
	private Integer pesos;

	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

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
		noDecimales = 2;
		reporte = "G";
		pesos = 1;
		jasperReporteName = "CNC_CPClasificacionEconomica";
		endFilename = jasperReporteName + ".pdf";
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

		endFilename = getNameFileReport(OUTPUT_REPORT_NAME, getMesSelected(), conctb, reporte);

		parameters.put("dependenciaName", conctb.getNomDep());
		parameters.put("imagePathRigth", conctb.getImagePathRigth());
		parameters.put("imagePathLeft", conctb.getImagePathLeft());
		parameters.put("decimalFormat", "%,." + noDecimales + "f");
		parameters.put("tipoReporte", reporte);
		parameters.put("lastMonth", getNombreMesSelected().toUpperCase());
		parameters.put("lastDay", getLastDayByAnoEmp(getMesSelected(), conctb.getAnoemp()));
		parameters.put("query", this.generaQuery());
		parameters.put("pesos", pesos);
		parameters.put("year", conctb.getAnoemp());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return parameters;

	}

	private String generaQuery() {
		StringBuilder pSql = new StringBuilder();
		if (TRIMESTRE.equals(tipoPeriodo)) {

			pSql.append("SELECT TIPO,NOMGAS,APROBADO,AMPLIACION_REDU AMPLI_REDU,MODIFICADO,DEVENGADO,PAGADO,SUBEJERCIDO SUBEJERCICIO FROM ");
			Integer trimestre = periodo.getPeriodo();
			switch (trimestre) {
			case 1:
				pSql.append(VW1);
				break;
			case 2:
				pSql.append(VW2);
				break;
			case 3:
				pSql.append(VW3);
				break;
			case 4:
				pSql.append(VW4);
				break;
			}
		} else if (MES.equals(tipoPeriodo)) {
			Integer mes = periodo.getPeriodo();

			String aprobado = "";
			String ampliacion = "";
			String reduccion = "";
			String devengado = "";
			String pagado = "";
			for (int y = 1; y <= 12; y++) {

				aprobado = aprobado + " AUTO1_" + y + " +";
			}
			for (int y = 1; y <= mes; y++) {
				ampliacion = ampliacion + " AMPLI1_" + y + " +";
				reduccion = reduccion + " REDU1_" + y + " +";
				devengado = devengado + " TOEJE1_" + y + " +";
				pagado = pagado + " EJPA1_" + y + " +";

			}
			aprobado = aprobado.substring(0, aprobado.length() - 2);
			ampliacion = ampliacion.substring(0, ampliacion.length() - 2);
			reduccion = reduccion.substring(0, reduccion.length() - 2);
			devengado = devengado.substring(0, devengado.length() - 2);
			pagado = pagado.substring(0, pagado.length() - 2);

			pSql.append("SELECT TIPO, NOMBRE NOMGAS,\n" +
					"  SUM(APROBADO) APROBADO,\n" +
					"  SUM(AMPLIACION - REDUCCIONES) AMPLI_REDU,\n" +
					"  SUM(APROBADO + AMPLIACION - REDUCCIONES) MODIFICADO,\n" +
					"  SUM(DEVENGADO) DEVENGADO,\n" +
					"  SUM(PAGADO) PAGADO,\n" +
					"  SUM(APROBADO + AMPLIACION - REDUCCIONES - DEVENGADO) SUBEJERCICIO\n" +
					"  FROM(\n" +
					"SELECT  1 TIPO, 'Gasto Corriente' NOMBRE,\n" +
					"    SUM(").append(aprobado).append(") APROBADO,\n" +
					"    SUM(").append(ampliacion).append(") AMPLIACION,\n" +
					"    SUM(").append(reduccion).append(") REDUCCIONES,\n" +
					"    SUM(").append(devengado).append(") DEVENGADO,\n" +
					"    SUM(").append(pagado).append(") PAGADO\n" +
					"  FROM PASO\n" +
					"WHERE SUBSTR(PARTIDA,1,1) IN('1','2','3','4') AND SUBSTR(PARTIDA,4) <> '0'\n" +
					"UNION\n" +
					"SELECT  2, 'Gasto de Capital',\n" +
					"    SUM(").append(aprobado).append(") APROBADO,\n" +
					"    SUM(").append(ampliacion).append(") AMPLIACION,\n" +
					"    SUM(").append(reduccion).append(") REDUCCIONES,\n" +
					"    SUM(").append(devengado).append(") DEVENGADO,\n" +
					"    SUM(").append(pagado).append(") PAGADO\n" +
					"  FROM PASO\n" +
					"WHERE SUBSTR(PARTIDA,1,1) IN('5','6','7') AND SUBSTR(PARTIDA,4) <> '0'\n" +
					"UNION\n" +
					"SELECT  3, 'Amortización de la Deuda y Disminución de los Pasivos',\n" +
					"    SUM(").append(aprobado).append(") APROBADO,\n" +
					"    SUM(").append(ampliacion).append(") AMPLIACION,\n" +
					"    SUM(").append(reduccion).append(") REDUCCIONES,\n" +
					"    SUM(").append(devengado).append(") DEVENGADO,\n" +
					"    SUM(").append(pagado).append(") PAGADO\n" +
					"  FROM PASO\n" +
					"WHERE SUBSTR(PARTIDA,1,1) = '9' AND SUBSTR(PARTIDA,4) <> '0'\n" +
					"UNION\n" +
					"SELECT  4,  'Participaciones', 0, 0, 0, 0, 0\n" +
					"FROM\n" +
					"  SYSIBM.SYSDUMMY1\n" +
					"UNION\n" +
					"SELECT  5,  'Pensiones y Jubilaciones', 0, 0, 0, 0, 0 \n" +
					"FROM\n" +
					"  SYSIBM.SYSDUMMY1 \n" +
					")T1\n" +
					"GROUP BY TIPO, NOMBRE\n" +
					"ORDER BY TIPO ASC");
		}

		System.out.println(pSql.toString());

		return pSql.toString();
	}

	private String generaQueryTxt() {
		StringBuilder query = new StringBuilder();

		query.append("SELECT 	'\"'||T1.NOMGAS||'\"|\"'||\r\n"
				+ "		TRIM(TO_CHAR(T1.APROBADO, '999999999999999990.99'))||'\"|\"'||\r\n"
				+ "		TRIM(TO_CHAR(T1.AMPLI_REDU, '999999999999999990.99'))||'\"|\"'||\r\n"
				+ "		TRIM(TO_CHAR(T1.MODIFICADO, '999999999999999990.99'))||'\"|\"'||\r\n"
				+ "		TRIM(TO_CHAR(T1.DEVENGADO, '999999999999999990.99'))||'\"|\"'||\r\n"
				+ "		TRIM(TO_CHAR(T1.PAGADO, '999999999999999990.99'))||'\"|\"'||\r\n"
				+ "		TRIM(TO_CHAR(T1.SUBEJERCICIO, '999999999999999990.99'))||'\"'\r\n" + "	FROM (\r\n")
				.append(this.generaQuery()).append(") T1");

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

	public String getReporte() {
		return reporte;
	}

	public void setReporte(String reporte) {
		this.reporte = reporte;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
