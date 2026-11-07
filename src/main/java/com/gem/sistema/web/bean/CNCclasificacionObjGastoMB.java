
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

import com.gem.sistema.business.bs.ConacService;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.reportador.GeneraTxtFilesService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "CNCclasificacionObjGastoMB")
@ViewScoped
public class CNCclasificacionObjGastoMB extends ReportePeriodos {

	private static final String CLAVE_REPORT = "RPT_CONAC_GENERIC_EGRESOS";
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_GENERIC";
	private static final String OUTPUT_REPORT_NAME = "EAEPECOG";

	final static private String VW1 = "VI_OBJETO_GASTO_CONAC_T1";
	final static private String VW2 = "VI_OBJETO_GASTO_CONAC_T2";
	final static private String VW3 = "VI_OBJETO_GASTO_CONAC_T3";
	final static private String VW4 = "VI_OBJETO_GASTO_CONAC_T4";

	private String pathName;
	private Integer noDecimales = 2;
	private Integer pesos = 1;

	private StreamedContent fileTxt;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{generaTxtFilesService}")
	private GeneraTxtFilesService txtFilesService;

	@ManagedProperty("#{conacService}")
	private ConacService conacService;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@ManagedProperty("#{executePlService}")
	protected ExecutePlService executePlService;

	@PostConstruct
	public void init() {
		jasperReporteName = "CNC_ClasificacionObjGasto";
		endFilename = jasperReporteName + ".pdf";
		changePeriodo();
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport(OUTPUT_REPORT_NAME, getMesSelected(), conctb, reporte);

		parameters.put("municipio", conctb.getNomDep());
		parameters.put("imagen", conctb.getImagePathLeft());
		parameters.put("imagen2", conctb.getImagePathRigth());
		parameters.put("firstMonth", getNombreMesInicial().toUpperCase());
		parameters.put("lastMonth", getNombreMesSelected().toUpperCase());
		parameters.put("lastDay", getLastDayByAnoEmp(getMesSelected(), conctb.getAnoemp()));
		parameters.put("anio", conctb.getAnoemp());
		parameters.put("decimalFormat", "%,." + noDecimales + "f");
		parameters.put("pPesos", pesos);
		parameters.put("sql", this.generaQuery());
		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		return parameters;
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

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	private String generaQueryTxt() {

		StringBuilder query = new StringBuilder();

		query.append("SELECT '\"'||CLVGAS||'\"|\"'||\r\n").append("		TRIM(NOMGAS)||'\"|\"'||\r\n")
				.append("		TRIM(TO_CHAR(APROBADO, '999999999999999990.99'))||'\"|\"'||\r\n")
				.append("		TRIM(TO_CHAR(AMPL_REDU, '999999999999999990.99'))||'\"|\"'||\r\n")
				.append("		TRIM(TO_CHAR(MODIFICADO, '999999999999999990.99'))||'\"|\"'||\r\n")
				.append("		TRIM(TO_CHAR(DEVENGADO, '999999999999999990.99')) ||'\"|\"'||\r\n")
				.append("		TRIM(TO_CHAR(PAGADO, '999999999999999990.99'))||'\"|\"'||\r\n")
				.append("		TRIM(TO_CHAR(SUBEJERCICIO, '999999999999999990.99'))||'\"' \r\n").append("	FROM (\r\n")
				.append(this.generaQuery()).append("	)T1");

		return query.toString();
	}

	private String generaQuery() {
		String sql = StringUtils.EMPTY;

		if (TRIMESTRE.equals(tipoPeriodo)) {
			sql = "SELECT * FROM ";
			switch (periodo.getPeriodo()) {
			case 1:
				sql = sql + VW1;
				break;
			case 2:
				sql = sql + VW2;
				break;
			case 3:
				sql = sql + VW3;
				break;
			case 4:
				sql = sql + VW4;
				break;
			}
		} else {
			
			Integer toMonth =this.periodo.getPeriodo() * this.periodo.getTipoPeriodo();
			sql = generateSQL(this.getUserDetails().getIdSector(), toMonth);

		}

		sql = sql + " ORDER BY CLVGAS ASC";
		System.out.println(" query->" + sql.toString());

		return sql;
	}

	private String generateSQL(Integer sector, Integer toMonth) {
		StringBuilder sql = new StringBuilder();
		StringBuilder sqlMiles = new StringBuilder();

		String auto = "SUM(";
		String ejpa = "SUM(";
		String redu = "SUM(";
		String ejxpa = "SUM(";
		String ampli = "SUM(";

		for (int y = 1; y <= toMonth; y++) {

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

		sql.append(
				" SELECT CLVGAS,NOMGAS,  DECODE(APROBADO,NULL,0,APROBADO)APROBADO, DECODE(AMPL_REDU,NULL,0,AMPL_REDU)AMPL_REDU,"
						+ " DECODE(MODIFICADO,NULL,0,MODIFICADO)MODIFICADO, DECODE(DEVENGADO,NULL,0,DEVENGADO)DEVENGADO,  "
						+ " DECODE(PAGADO,NULL,0,PAGADO)PAGADO, DECODE(SUBEJERCICIO,NULL,0,SUBEJERCICIO)SUBEJERCICIO "
						+ "  FROM(  SELECT CLVGAS,NOMGAS, DECODE(SUBSTR (CLVGAS,1,1),'6',(AMPLIACION -REDUCCIONES),"
						+ "  DECODE (CLVGAS,'4000',(APROBADO),"
						+ "  DECODE (CLVGAS,'4400',(APROBADO),APROBADO)))APROBADO, DECODE(SUBSTR (CLVGAS,1,1),'6',APROBADO,DECODE (CLVGAS,'4000',((AMPLIACION -REDUCCIONES)),DECODE (CLVGAS,'4400',((AMPLIACION -REDUCCIONES)),(AMPLIACION -REDUCCIONES)))) AMPL_REDU,   "
						+ " (APROBADO + AMPLIACION -REDUCCIONES) MODIFICADO,DEVENGADO,PAGADO,   (APROBADO + AMPLIACION -REDUCCIONES) - DEVENGADO SUBEJERCICIO"
						+ " FROM (SELECT NAT.CLVGAS,NAT.NOMGAS, " + auto + ejpa + redu + ejxpa + ampli
						+ " FROM NATGAS NAT LEFT JOIN PASO PA  ON    "
						+ " NAT.CLVGAS = PA.PARTIDA AND NAT.IDSECTOR = PA.IDSECTOR " + " AND PA.IDSECTOR = 2 "
						+ " WHERE SUBSTR(NAT.CLVGAS, 3, 2) = '00'  GROUP BY NAT.CLVGAS,NAT.NOMGAS   ORDER BY NAT.CLVGAS ASC ) ) ");

		if (pesos != 1) {
			sqlMiles.append(
					"SELECT T2.CLVGAS , T2.NOMGAS, 	(T2.APROBADO /1000) APROBADO,(T2.AMPL_REDU /1000) AMPL_REDU,				")
					.append("	(T2.MODIFICADO /1000) MODIFICADO,(T2.DEVENGADO/1000) DEVENGADO,  ")
					.append("	(T2.PAGADO/1000) PAGADO,(T2.SUBEJERCICIO /1000) SUBEJERCICIO               ")
					.append("FROM(                                                                                                      ");
			sql.insert(0, sqlMiles);
			sql.append(")T2");
		}
		System.out.println(sql.toString());
		return sql.toString();
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}

	public GeneraTxtFilesService getTxtFilesService() {
		return txtFilesService;
	}

	public void setTxtFilesService(GeneraTxtFilesService txtFilesService) {
		this.txtFilesService = txtFilesService;
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

	public ConacService getConacService() {
		return conacService;
	}

	public void setConacService(ConacService conacService) {
		this.conacService = conacService;
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
