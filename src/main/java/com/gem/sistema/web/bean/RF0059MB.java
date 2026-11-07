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

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcPeriodo;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcPeriodoRepositoy;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;

// TODO: Auto-generated Javadoc
/**
 * The Class RF0059MB.
 *
 * @author Alfredo
 * @Timeline <strong>Date:</strong> 01/12/2021 <strong>Changes:</strong> Se
 *           ajusta reporte
 * @Sector Municipal, Central
 */
@ManagedBean
@ViewScoped
public class RF0059MB extends BaseDirectReport {

	private static final String CLAVE_REPORT = "RPT_AVANCE_PRESUPUESTAL_EGRESOS";
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_GENERIC";
	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";
	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";

	private Integer mes;
	private Integer saldo0;
	private Integer capitulo;

	private StreamedContent fileTxt;
	
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

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
		LOGGER.info(":: En postconsruct RF005.9 ");

		saldo0 = 0;
		capitulo = 0;
		jasperReporteName = "RF005.9";
		endFilename = "Avance Presupuestal Egresos Global.pdf";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Integer idSector = this.getUserDetails().getIdSector();
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcPeriodo periodo = periodoRepositoy.findByTipoPeriodoAndPeriodo(1, mes);

		paramsReport.put("mes", mes);
		paramsReport.put("sector", idSector);
		paramsReport.put("year", conctb.getAnoemp());
		paramsReport.put("rfcEntidad", conctb.getRfc());
		paramsReport.put("claveEntidad", conctb.getClave());
		paramsReport.put("nombreEntidad", conctb.getNomDep());
		paramsReport.put("imagenPathLeft", conctb.getImagePathLeft());
		paramsReport.put("imagenPathRigth", conctb.getImagePathRigth());
		paramsReport.put("mesName", periodo.getDescripcion().toUpperCase());
		paramsReport.put("lastDay", getLastDayByAnoEmp(mes, conctb.getAnoemp()));
		paramsReport.put("query", this.generateQuery());

		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);

		endFilename = getNameFileReport(mes, conctb, reporte);
		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));

		return paramsReport;
	}

	private String generateQuery() {
		StringBuilder sql = new StringBuilder();
		StringBuilder ampliacion = new StringBuilder();
		StringBuilder reduccion = new StringBuilder();
		StringBuilder comprometido = new StringBuilder();
		StringBuilder devengado = new StringBuilder();
		StringBuilder pagado = new StringBuilder();
		StringBuilder ejercido = new StringBuilder();

		for (int i = 1; i <= mes; i++) {
			pagado.append(" P.EJPA1_" + i + " +");
			ejercido.append(" P.TOEJE1_" + i + " +");
			reduccion.append(" P.REDU1_" + i + " +");
			devengado.append(" P.EJXPA1_" + i + " +");
			ampliacion.append(" P.AMPLI1_" + i + " +");
			comprometido.append(" P.COMPRO1_" + i + " +");
		}

		String where = "";

		if (capitulo > 0) {
			where = " AND SUBSTR(P.PARTIDA,1,1) = '" + capitulo + "' ";
		}

		sql.append("SELECT 	T1.PARTIDA, T1.NOMGAS,\r\n" + "		T1.APROBADO, T1.AMPLIACION, T1.REDUCCION,\r\n"
				+ "		(T1.APROBADO + T1.AMPLIACION - T1.REDUCCION) MODIFICADO,\r\n"
				+ "		T1.COMPROMETIDO, T1.DEVENGADO, T1.PAGADO,\r\n"
				+ "		(T1.COMPROMETIDO + T1.DEVENGADO + T1.PAGADO) EJERCIDO,\r\n"
				+ "		((T1.APROBADO + T1.AMPLIACION - T1.REDUCCION) - (T1.COMPROMETIDO + T1.DEVENGADO + T1.PAGADO)) POR_EJERCER\r\n"
				+ "FROM (SELECT P.PARTIDA, N.NOMGAS,\r\n"
				+ "	SUM(P.AUTO1_1 +P.AUTO1_2 +P.AUTO1_3 +P.AUTO1_4 +P.AUTO1_5 +P.AUTO1_6 +P.AUTO1_7 +")
				.append("P.AUTO1_8 +P.AUTO1_9 +P.AUTO1_10 +P.AUTO1_11 +P.AUTO1_12) APROBADO,\r\n").append(" SUM(")
				.append(ampliacion.substring(0, ampliacion.length() - 2)).append(") AMPLIACION,\r\n").append(" SUM(")
				.append(reduccion.substring(0, reduccion.length() - 2)).append(") REDUCCION,\r\n").append(" SUM(")
				.append(pagado.substring(0, pagado.length() - 2)).append(") PAGADO,\r\n").append(" SUM(")
				.append(devengado.substring(0, devengado.length() - 2)).append(") DEVENGADO,\r\n").append(" SUM(")
				.append(ejercido.substring(0, ejercido.length() - 2)).append(") EJERCIDO,\r\n").append(" SUM(")
				.append(comprometido.substring(0, comprometido.length() - 2)).append(") COMPROMETIDO\r\n")
				.append("		FROM PASO P\r\n"
						+ "			INNER JOIN NATGAS N ON P.PARTIDA=N.CLVGAS AND P.IDSECTOR=N.IDSECTOR AND N.IDSECTOR=P.IDSECTOR\r\n"
						+ "		WHERE P.IDSECTOR = ")
				.append(this.getUserDetails().getIdSector()).append(where)
				.append("	GROUP BY N.NOMGAS,P.PARTIDA ORDER BY P.PARTIDA )T1 ");

		if (this.saldo0 == 0) {
			sql.append("WHERE	T1.APROBADO <> 0 OR T1.AMPLIACION <> 0 OR T1.REDUCCION <> 0 OR\r\n"
					+ "		(T1.APROBADO + T1.AMPLIACION - T1.REDUCCION) <> 0 OR \r\n"
					+ "		T1.COMPROMETIDO <> 0 OR T1.DEVENGADO <> 0 OR  T1.PAGADO <> 0 OR \r\n"
					+ "		(T1.COMPROMETIDO + T1.DEVENGADO + T1.PAGADO) <> 0 ");
		}

		System.out.println(sql.toString());
		return sql.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
		/*
		 * Map<String, Object> paramsQuery = new java.util.HashMap<String, Object>();
		 * paramsQuery.put("ID_REF", new Integer(0)); //FALTA return
		 * reporteadorDirectoImpl.getFileReport(tcReporte,paramsQuery,
		 * reporteName,type);
		 */
	}

	public void downloadTxt() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());
			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);
		}
	}

	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());
			RequestContext.getCurrentInstance().execute(DOWNLOAD_XLS);
		}
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());
			this.createFilePdfInline();
		}
	}
	
	private String generaQueryTXT() {
		
		StringBuilder query =  new StringBuilder();
		
		query.append("SELECT '\"'|| T2.PARTIDA|| '\"|\"' ||\r\n"
				+ "       TRIM(T2.NOMGAS)|| '\"|\"' ||\r\n"
				+ "       TRIM(TO_CHAR(T2.APROBADO, '999999999999999990.99'))|| '\"|\"' ||\r\n"
				+ "       TRIM(TO_CHAR(T2.AMPLIACION, '999999999999999990.99'))|| '\"|\"' ||\r\n"
				+ "       TRIM(TO_CHAR(T2.REDUCCION, '999999999999999990.99'))|| '\"|\"' ||\r\n"
				+ "       TRIM(TO_CHAR(T2.MODIFICADO, '999999999999999990.99'))|| '\"|\"' ||\r\n"
				+ "       TRIM(TO_CHAR(T2.COMPROMETIDO, '999999999999999990.99'))|| '\"|\"' ||\r\n"
				+ "       TRIM(TO_CHAR(T2.DEVENGADO, '999999999999999990.99'))|| '\"|\"' ||\r\n"
				+ "       TRIM(TO_CHAR(T2.PAGADO, '999999999999999990.99'))|| '\"|\"' ||\r\n"
				+ "       TRIM(TO_CHAR(T2.EJERCIDO, '999999999999999990.99'))|| '\"|\"' ||\r\n"
				+ "       TRIM(TO_CHAR(T2.POR_EJERCER, '999999999999999990.99'))|| '\"'\r\n"
				+ "FROM (").append(this.generateQuery()).append(" )T2");
		 
		System.out.println("queryTXT-> " + query.toString());
		return query.toString();
	}

	Map<String, Object> out;
	InputStream stream = null;
	public StreamedContent getFileTxt() {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		String filename = getNameFileReport(mes, conctb, reporte);
		
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

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
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

	public Integer getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(Integer capitulo) {
		this.capitulo = capitulo;
	}

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

	public Integer getSaldo0() {
		return saldo0;
	}

	public void setSaldo0(Integer saldo0) {
		this.saldo0 = saldo0;
	}

}
