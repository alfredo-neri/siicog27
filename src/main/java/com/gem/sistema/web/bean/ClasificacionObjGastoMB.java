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
import com.gem.sistema.business.service.reportador.GeneraTxtFilesService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

@ManagedBean(name = "clasificacionObjGastoMB")
@ViewScoped
public class ClasificacionObjGastoMB extends ReportePeriodos {

	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_GENERIC";
	private static final String CLAVE_REPORT = "RPT_LDF_GENERIC_EGRESOS";
	private static final String OUTPUT_REPORT_NAME = "LDF-ClasificacionObjetoGasto_";

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

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	@ManagedProperty("#{executePlService}")
	protected ExecutePlService executePlService;

	@PostConstruct
	public void init() {
		jasperReporteName = "ClasificacionObjGasto";
		endFilename = jasperReporteName + ".pdf";
		changePeriodo();
	}

	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {

		Integer idSector = this.getUserDetails().getIdSector();
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport(OUTPUT_REPORT_NAME, getMesSelected(), conctb, reporte);

		parameters.put("dependenciaName", conctb.getNomDep());
		parameters.put("imagenPathRigth", conctb.getImagePathRigth());
		parameters.put("imagenPathLeft", conctb.getImagePathLeft());
		parameters.put("firstMonth", getNombreMesInicial().toUpperCase());
		parameters.put("lastMonth", getNombreMesSelected().toUpperCase());
		parameters.put("lastDay", getLastDayByAnoEmp(getMesSelected(), conctb.getAnoemp()));
		parameters.put("anio", conctb.getAnoemp());
		parameters.put("decimalFormat", "%,." + noDecimales + "f");
		parameters.put("pPesos", pesos);
		parameters.put("sql", this.generateSQL(this.getUserDetails().getIdSector()));
		parameters.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		return parameters;
	}

	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	private String generateSQL(Integer sector) {
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

		sql.append("SELECT GRUP,	CLVGAS,	NOMGAS, ").append(" DECODE(APROBADO,NULL,0,APROBADO)APROBADO,")
				.append(" 	DECODE(AMPL_REDU,NULL,0,AMPL_REDU)AMPL_REDU, ")
				.append(" 	DECODE(MODIFICADO,NULL,0,MODIFICADO)MODIFICADO, ")
				.append(" 	DECODE(DEVENGADO,NULL,0,DEVENGADO)DEVENGADO, ")
				.append(" 	DECODE(PAGADO,NULL,0,PAGADO)PAGADO, ")
				.append(" 	DECODE(SUBEJERCICIO,NULL,0,SUBEJERCICIO)SUBEJERCICIO,ROW_NUMBER() OVER(PARTITION BY SUBSTR(CLVGAS,1,1),GRUP) ROW_OF_TOTAL ")
				.append(" FROM( ")
				.append(" SELECT GRUP,	CLVGAS,	NOMGAS,	DECODE(SUBSTR (CLVGAS,1,1),'6',(AMPLIACION -REDUCCIONES),APROBADO) APROBADO,\r\n"
						+ "	                DECODE(SUBSTR (CLVGAS,1,1),'6',APROBADO,(AMPLIACION -REDUCCIONES)) AMPL_REDU,  ")
				.append(" 	(APROBADO + AMPLIACION -REDUCCIONES) MODIFICADO,DEVENGADO,	PAGADO,  ")
				.append(" 	(APROBADO + AMPLIACION -REDUCCIONES) - DEVENGADO SUBEJERCICIO  ")
				.append(" FROM ((SELECT 2 GRUP,NAT.CLVGAS,	NAT.NOMGAS,                                       ")
				.append(auto).append(ejpa).append(redu).append(ejxpa).append(ampli)
				.append(" 	FROM NATGAS NAT LEFT JOIN PASO PA  ON   ")
				.append(" 		NAT.CLVGAS = PA.PARTIDA AND NAT.IDSECTOR = PA.IDSECTOR   ")
				.append(" 		AND	PA.IDSECTOR = ").append(sector)
				.append(" 		AND	SUBSTR(PA.PROGRAMA,11) IN ('25010109','25020707') AND PA.PROGRAMA <> '04040101010125020707' ")
				.append(" 	WHERE	SUBSTR(NAT.CLVGAS, 3, 2) = '00'   ").append(" 	GROUP BY NAT.CLVGAS,NAT.NOMGAS  ")
				.append(" 	ORDER BY NAT.CLVGAS ASC)  ").append(" UNION ALL  ")
				.append(" (SELECT 1 GRUP, NAT.CLVGAS,	NAT.NOMGAS,  											")
				.append(auto).append(ejpa).append(redu).append(ejxpa).append(ampli)
				.append(" 	FROM NATGAS NAT 	LEFT JOIN PASO PA  ON 	  ")
				.append(" 		NAT.CLVGAS = PA.PARTIDA AND NAT.IDSECTOR = PA.IDSECTOR   ")
				.append(" 		AND	PA.IDSECTOR = ").append(sector)
//				.append(" AND SUBSTR(PA.PROGRAMA, 15, 1) IN ('1','2','5','6','7','8','9') ")
				.append(" AND SUBSTR(PA.PROGRAMA, 11) IN ('11020501', '15010114',")
				.append(" '14010201','14010101','15010114', '14010203', '11010102') ")
				.append(" OR PA.PROGRAMA = '04040101010125020707' ")
				.append(" 	WHERE	SUBSTR(NAT.CLVGAS, 3, 2) = '00'   ").append(" 	GROUP BY NAT.CLVGAS,NAT.NOMGAS  ")
				.append(" 	ORDER BY NAT.CLVGAS ASC)  ").append(" )  )ORDER BY GRUP ASC");

		if (pesos != 1) {
			sqlMiles.append(
					"SELECT T2.GRUP,T2.CLVGAS , T2.NOMGAS, 	(T2.APROBADO /1000) APROBADO,(T2.AMPL_REDU /1000) AMPL_REDU,				")
					.append("	(T2.MODIFICADO /1000) MODIFICADO,(T2.DEVENGADO/1000) DEVENGADO,  ")
					.append("	(T2.PAGADO/1000) PAGADO,(T2.SUBEJERCICIO /1000) SUBEJERCICIO               ")
					.append("FROM(                                                                                                      ");
			sql.insert(0, sqlMiles);
			sql.append(")T2");
		}

		System.out.println(sql.toString());
		return sql.toString();
	}

	private String generaQueryTXT() {

		StringBuilder query = new StringBuilder();

		query.append("SELECT  '\"'|| T2.CLVGAS ||'\"|\"'||\r\n" + "              TRIM(T2.NOMGAS) ||'\"|\"'||\r\n"
				+ "              TRIM(TO_CHAR(T2.APROBADO, '999999999999999990.99')) ||'\"|\"'||\r\n"
				+ "              TRIM(TO_CHAR(T2.AMPL_REDU, '999999999999999990.99')) ||'\"|\"'||\r\n"
				+ "              TRIM(TO_CHAR(T2.MODIFICADO, '999999999999999990.99')) ||'\"|\"'||\r\n"
				+ "              TRIM(TO_CHAR(T2.DEVENGADO, '999999999999999990.99')) ||'\"|\"'||\r\n"
				+ "              TRIM(TO_CHAR(T2.PAGADO, '999999999999999990.99')) ||'\"|\"'||\r\n"
				+ "              TRIM(TO_CHAR(T2.SUBEJERCICIO, '999999999999999990.99')) ||'\"'\r\n" + "FROM(")
				.append(this.generateSQL(this.getUserDetails().getIdSector())).append(" ) T2");

		System.out.println("QUERY TXT->" + query.toString());
		return query.toString();
	}

	Map<String, Object> out;
	InputStream stream = null;

	public StreamedContent getFileTxt() {

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		String filename = getNameFileReport("EAEPECOG", getMesSelected(), conctb, reporte);

		parameter.addValue("i_filename", filename.substring(0, filename.length() - 4));
		parameter.addValue("i_query", this.generaQueryTXT());

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
