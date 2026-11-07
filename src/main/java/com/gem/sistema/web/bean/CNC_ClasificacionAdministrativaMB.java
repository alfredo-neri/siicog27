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
import com.gem.sistema.business.service.reportador.GeneraTxtFilesService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "CNC_ClasificacionAdministrativaMB")
@ViewScoped
public class CNC_ClasificacionAdministrativaMB extends ReportePeriodos {
	private static final String OUTPUT_REPORT_NAME = "EAEPECA";
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_GENERIC";
	private static final String CLAVE_REPORT = "RPT_CONAC_GENERIC_EGRESOS";

	final static private String VW1 = "VI_CLASIFICACION_ECONOMICA_T1";
	final static private String VW2 = "VI_CLASIFICACION_ECONOMICA_T2";
	final static private String VW3 = "VI_CLASIFICACION_ECONOMICA_T3";
	final static private String VW4 = "VI_CLASIFICACION_ECONOMICA_T4";

	private StreamedContent fileTxt;
	private Integer noDecimales = 2;
	private Integer pesos = 1;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{generaTxtFilesService}")
	private GeneraTxtFilesService txtFilesService;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@ManagedProperty("#{executePlService}")
	protected ExecutePlService executePlService;

	@PostConstruct
	public void init() {
		jasperReporteName = "CNC_ClasificacionAdministrativa";
		endFilename = jasperReporteName + ".pdf";
		changePeriodo();
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);
		String query = this.generaQuery();

		endFilename = getNameFileReport(OUTPUT_REPORT_NAME, getMesSelected(), conctb, reporte);

		parameters.put("dependenciaName", conctb.getNomDep());
		parameters.put("imagePathRigth", conctb.getImagePathRigth());
		parameters.put("imagePathLeft", conctb.getImagePathLeft());
		parameters.put("decimalFormat", "%,." + noDecimales + "f");
		parameters.put("lastMonth", getNombreMesSelected().toUpperCase());
		parameters.put("lastDay", getLastDayByAnoEmp(getMesSelected(), conctb.getAnoemp()));
		parameters.put("pesos", pesos);
		parameters.put("year", conctb.getAnoemp());
		parameters.put("sql", query);
		parameters.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		System.out.println(query.toString());
		return parameters;
	}

	private String generaQuery() {
		String pSql = StringUtils.EMPTY;
		if (periodo.getTipoPeriodo() == 3) {
			pSql = "SELECT  SUM(APROBADO) APROBADO,\r\n" + " SUM(AMPLIACION_REDU) AMPL_REDU,\r\n"
					+ " SUM(MODIFICADO)MODIFICADO,\r\n" + " SUM(DEVENGADO) DEVENGADO,\r\n" + " SUM(PAGADO) PAGADO,\r\n"
					+ " SUM(SUBEJERCIDO) SUBEJERCICIO\r\n" + " FROM ";
			switch (periodo.getPeriodo()) {
			case 1:
				pSql = pSql + VW1;
				break;
			case 2:
				pSql = pSql + VW2;
				break;
			case 3:
				pSql = pSql + VW3;
				break;
			case 4:
				pSql = pSql + VW4;
				break;
			}
		} else {
			pSql = this.generateSql(this.getUserDetails().getIdSector());
		}

		return pSql;
	}

	private String generaQueryTxt() {
		StringBuilder query = new StringBuilder();

		query.append("SELECT '\"'||DE.CLVDEP||'\"|\"'||\r\n" + "			DE.NOMDEP||'\"|\"'||\r\n"
				+ "			TRIM(TO_CHAR(T1.APROBADO, '999999999999999990.99'))||'\"|\"'||\r\n"
				+ "			TRIM(TO_CHAR(T1.AMPL_REDU, '999999999999999990.99'))||'\"|\"'||\r\n"
				+ "			TRIM(TO_CHAR(T1.MODIFICADO, '999999999999999990.99'))||'\"|\"'||\r\n"
				+ "			TRIM(TO_CHAR(T1.DEVENGADO, '999999999999999990.99'))||'\"|\"'||\r\n"
				+ "			TRIM(TO_CHAR(T1.PAGADO, '999999999999999990.99'))||'\"|\"'||\r\n"
				+ "			TRIM(TO_CHAR(T1.SUBEJERCICIO, '999999999999999990.99'))||'\"'\r\n"
				+ "			 FROM (\r\n").append(this.generaQuery())
				.append("	)T1, CATDEP DE WHERE DE.CLVDEP = '200C01000000000'");

		return query.toString();
	}

	private String generateSql(Integer idSector) {
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlMiles = new StringBuilder();
		String auto = "SUM(";
		String ejpa = "SUM(";
		String redu = "SUM(";
		String ejxpa = "SUM(";
		String ampli = "SUM(";

		for (int y = 1; y <= getMesSelected(); y++) {
			ejpa = ejpa + " PA.EJPA1_" + y + " +";
			redu = redu + " PA.REDU1_" + y + " +";
			ejxpa = ejxpa + " PA.TOEJE1_" + y + " +";
			ampli = ampli + " PA.AMPLI1_" + y + " +";
		}

		for (int y = 1; y <= 12; y++) {
			auto = auto + " PA.AUTO1_" + y + " +";
		}

		auto = auto.substring(0, auto.length() - 2) + " ) APROBADO, ";
		ejpa = ejpa.substring(0, ejpa.length() - 2) + " ) PAGADO, ";
		redu = redu.substring(0, redu.length() - 2) + " ) REDUCCIONES, ";
		ejxpa = ejxpa.substring(0, ejxpa.length() - 2) + " ) DEVENGADO, ";
		ampli = ampli.substring(0, ampli.length() - 2) + " ) AMPLIACION ";

		sql.append(" SELECT 	APROBADO,  	(AMPLIACION -REDUCCIONES) AMPL_REDU,   ")
				.append(" 	(APROBADO + (AMPLIACION -REDUCCIONES)) MODIFICADO,  ").append(" 	DEVENGADO,	PAGADO,   ")
				.append(" 	(APROBADO + AMPLIACION -REDUCCIONES) - DEVENGADO SUBEJERCICIO ").append(" FROM (SELECT ")
				.append(auto).append(ejpa).append(redu).append(ejxpa).append(ampli).append(" FROM CATDEP D  ")
				.append(" 	    LEFT JOIN PASO PA  ").append("       ON PA.CLAVE = D.CLVDEP ")
				.append(" 		AND  	D.IDSECTOR = PA.IDSECTOR  ").append(" 		AND   	PA.IDSECTOR = 2 ")
				.append(" WHERE SUBSTR(PA.PARTIDA,2,3) = '000') T1    ");

		if (pesos != 1) {
			sqlMiles.append("SELECT 	(T2.APROBADO /1000) APROBADO,(T2.AMPL_REDU /1000) AMPL_REDU,				")
					.append("	(T2.MODIFICADO /1000) MODIFICADO,(T2.DEVENGADO/1000) DEVENGADO,  ")
					.append("	(T2.PAGADO/1000) PAGADO,(T2.SUBEJERCICIO /1000) SUBEJERCICIO               ")
					.append("FROM(                                                                                                      ");
			sql.insert(0, sqlMiles);
			sql.append(")T2");
		}
		System.out.println(sql.toString());
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

	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
	}

	public GeneraTxtFilesService getTxtFilesService() {
		return txtFilesService;
	}

	public void setTxtFilesService(GeneraTxtFilesService txtFilesService) {
		this.txtFilesService = txtFilesService;
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
