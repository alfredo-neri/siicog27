
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
@ManagedBean(name = "CNCfinalidadFuncionMB")
@ViewScoped
public class CNCfinalidadFuncionMB extends ReportePeriodos {
	private static final String OUTPUT_REPORT_NAME = "EAEPECF";
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_GENERIC";
	private static final String CLAVE_REPORT = "RPT_CONAC_GENERIC_EGRESOS";

	private StreamedContent fileTxt;
	private Integer noDecimales;
	private Integer pesos;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@ManagedProperty("#{executePlService}")
	protected ExecutePlService executePlService;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		noDecimales = 2;
		pesos = 1;
		jasperReporteName = "CNC_ClasificacionFinalidadFuncion";
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
		parameters.put("lastMonth", getNombreMesSelected().toUpperCase());
		parameters.put("lastDay", getLastDayByAnoEmp(getMesSelected(), conctb.getAnoemp()));
		parameters.put("year", conctb.getAnoemp());
		parameters.put("decimalFormat", "%,." + noDecimales + "f");
		parameters.put("pesos", pesos);
		parameters.put("sql", this.generaQuery(sector));
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return parameters;
	}

	public String generaQuery(Integer idsector) {

		StringBuilder sSql1 = new StringBuilder();
		StringBuilder sqlMiles = new StringBuilder();
		String aprobado = "SUM(";
		String ampliacion = "SUM(";
		String reduccion = "SUM(";
		String devengado = "SUM(";
		String pagado = "SUM(";

		for (int y = 1; y <= getMesSelected(); y++) {
			ampliacion = ampliacion + " PA.AMPLI1_" + y + " +";
			reduccion = reduccion + " PA.REDU1_" + y + " +";
			devengado = devengado + " PA.TOEJE1_" + y + " +";
			pagado = pagado + " PA.EJPA1_" + y + " +";
		}

		for (int y = 1; y <= 12; y++) {
			aprobado = aprobado + " PA.AUTO1_" + y + " +";
		}

		aprobado = aprobado.substring(0, aprobado.length() - 2) + " ) APROBADO, ";
		ampliacion = ampliacion.substring(0, ampliacion.length() - 2) + " ) AMPLIACIONES, ";
		reduccion = reduccion.substring(0, reduccion.length() - 2) + " ) REDUCCIONES, ";
		devengado = devengado.substring(0, devengado.length() - 2) + " ) DEVENGADO, ";
		pagado = pagado.substring(0, pagado.length() - 2) + " ) PAGADO ";

		sSql1.append("	SELECT GRUP,CAMPO7,	CAMPO6,				")
				.append("		DECODE(APROBADO, NULL, 0,APROBADO)APROBADO, 				")
				.append("		DECODE(APMLIREDU, NULL, 0,	APMLIREDU)APMLIREDU,				")
				.append("		DECODE(MODIFICADO, NULL, 0, MODIFICADO)MODIFICADO,				")
				.append("		DECODE(DEVENGADO, NULL, 0,DEVENGADO)DEVENGADO, 				")
				.append("		DECODE(PAGADO, NULL, 0,PAGADO)PAGADO,  				")
				.append("		DECODE(SUBEJERCICIO, NULL, 0, SUBEJERCICIO)SUBEJERCICIO				")
				.append("	FROM(SELECT GRUP,	T1.CAMPO7,	CAMPO6 ,APROBADO,				")
				.append("			(T1.AMPLIACIONES - T1.REDUCCIONES) APMLIREDU,				")
				.append("			(T1.APROBADO + T1.AMPLIACIONES -T1.REDUCCIONES) MODIFICADO,				")
				.append("			T1.DEVENGADO,	T1.PAGADO,				")
				.append("			((T1.APROBADO + T1.AMPLIACIONES -T1.REDUCCIONES) - T1.DEVENGADO) SUBEJERCICIO				")
				.append("		FROM ((	SELECT * FROM(				")
				.append("				(SELECT NA.CAMPO6,1 GRUP,NA.CAMPO7,				").append(aprobado)
				.append(ampliacion).append(reduccion).append(devengado).append(pagado)
				.append("						FROM PASO PA RIGHT JOIN MUNINEP NA		ON 				")
				.append("							NA.CAMPO7 = SUBSTR(PA.PROGRAMA,	1,	4) AND				")
				.append("							SUBSTR(PA.PARTIDA,	4) <> '0' AND				")
				.append("							NA.IDSECTOR = PA.IDSECTOR AND				")
				.append("							PA.IDSECTOR = 2 				")
				.append("						WHERE NA.CAMPO1<>'' AND NA.CAMPO2=''				")
				.append("						GROUP BY NA.CAMPO0,	NA.CAMPO7,	NA.CAMPO6)				")
				.append("					UNION ALL(SELECT M.CAMPO6 ,T1.*  FROM (				")
				.append("				SELECT 1 GRUP,	NA.CAMPO0,				").append(aprobado).append(ampliacion)
				.append(reduccion).append(devengado).append(pagado)
				.append("				FROM PASO PA RIGHT JOIN MUNINEP NA		ON 				")
				.append("					NA.CAMPO7 = SUBSTR(PA.PROGRAMA,	1,	4) AND				")
				.append("					SUBSTR(PA.PARTIDA,	4) <> '0' AND				")
				.append("					NA.IDSECTOR = PA.IDSECTOR AND				")
				.append("					PA.IDSECTOR = 2 			")
				.append("				WHERE NA.CAMPO1<>'' AND NA.CAMPO2=''				")
				.append("				GROUP BY NA.CAMPO0) T1, MUNINEP M				")
				.append("			WHERE M.CAMPO0=T1.CAMPO0 AND M.IDSECTOR=2	AND M.CAMPO1='' )				")
				.append("			) ORDER BY SUBSTR(CAMPO7,1,2)	)			")
				.append("	) T1) ORDER BY 1,2				");

		if (pesos != 1) {
			sqlMiles.append(
					"SELECT T2.GRUP,T2.CAMPO7 , T2.CAMPO6, 	(T2.APROBADO /1000) APROBADO,(T2.APMLIREDU /1000) APMLIREDU,				")
					.append("	(T2.MODIFICADO /1000) MODIFICADO,(T2.DEVENGADO/1000) DEVENGADO,  ")
					.append("	(T2.PAGADO/1000) PAGADO,(T2.SUBEJERCICIO /1000) SUBEJERCICIO               ")
					.append("FROM(                                                                                                      ");
			sSql1.insert(0, sqlMiles);
			sSql1.append(")T2");
		}

		System.out.println(sSql1.toString());
		return sSql1.toString();

	}

	private String generaQueryTxt() {
		StringBuilder query = new StringBuilder();

		query.append("SELECT 	'\"'||SUBSTR(TX.CAMPO7,1,2)||'\"|\"'||\r\n"
				+ "		TRIM(SUBSTR(TX.CAMPO7,3,2))||'\"|\"'||\r\n" + "		TX.CAMPO6||'\"|\"'||\r\n"
				+ "		TRIM(TO_CHAR(TX.APROBADO, '999999999999999990.99'))||'\"|\"'||\r\n"
				+ "		TRIM(TO_CHAR(TX.APMLIREDU, '999999999999999990.99'))||'\"|\"'||\r\n"
				+ "		TRIM(TO_CHAR(TX.MODIFICADO, '999999999999999990.99'))||'\"|\"'||\r\n"
				+ "		TRIM(TO_CHAR(TX.DEVENGADO, '999999999999999990.99'))||'\"|\"'||\r\n"
				+ "		TRIM(TO_CHAR(TX.PAGADO, '999999999999999990.99'))||'\"|\"'||\r\n"
				+ "		TRIM(TO_CHAR(TX.SUBEJERCICIO, '999999999999999990.99'))||'\"'\r\n" + "	 FROM (\r\n")
				.append(this.generaQuery(this.getUserDetails().getIdSector())).append(") TX");

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

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
