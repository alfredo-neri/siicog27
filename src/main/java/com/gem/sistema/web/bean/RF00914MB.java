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
 * The Class RF00914MB.
 */
@ManagedBean
@ViewScoped
public class RF00914MB extends BaseDirectReport {

	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";
	private static final String CLAVE_REPORT = "RPT_BALANZA_COMPROBACION";
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_GENERIC";

	/** The mes. */
	private Integer mes;

	/** The saldo 0. */
	private Integer saldo0;
	
	private StreamedContent fileTxt;

	@ManagedProperty("#{tcPeriodoRepositoy}")
	private TcPeriodoRepositoy periodoRepositoy;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

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
		LOGGER.info(":: En postconsruct RF00914MB ");
		jasperReporteName = "RF009_1_4";
		endFilename = jasperReporteName + ".pdf";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		TcPeriodo periodo = periodoRepositoy.findByTipoPeriodoAndPeriodo(1, mes);
		Integer idSector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(idSector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(idSector, CLAVE_REPORT);
		
		endFilename = getNameFileReport(mes, conctb, reporte);

		paramsReport.put("mes", mes);
		paramsReport.put("year", conctb.getAnoemp());
		paramsReport.put("where", this.generateWhere(idSector, mes, saldo0));
		paramsReport.put("lastDay", getLastDayByAnoEmp(mes, conctb.getAnoemp()));
		paramsReport.put("mesName", periodo.getDescripcion());
		paramsReport.put("idSector", idSector);
		paramsReport.put("imageLeft", conctb.getImagePathLeft());
		paramsReport.put("imageRigth", conctb.getImagePathRigth());
		paramsReport.put("entidadName", conctb.getNomDep());
		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(idSector, CLAVE_REPORT));
		
		return paramsReport;
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
			;
		}
	}
	
	private String generaQueryTXT() {
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		StringBuilder query = new StringBuilder();
		
		if (mes == 1) {
			cargos.append("0 + ");
			abonos.append("0 + ");
		} else {
			for (int i = 1; i < mes; i++) {
				cargos.append("CARGOS_" + i + " + ");
				abonos.append("ABONOS_" + i + " + ");
			}
		}
		
		query.append("SELECT '\"' || T2.CUENTA || '\"|\"' ||\r\n"
				+ "       T2.NOMCTA || '\"|\"' ||\r\n"
				+ "       TRIM(TO_CHAR(DECODE(STACTA, 'D', SALDO_INICIAL, 0), '999999999999999990.99')) || '\"|\"' ||\r\n"
				+ "       TRIM(TO_CHAR(DECODE(STACTA, 'D', 0, SALDO_INICIAL), '999999999999999990.99')) || '\"|\"' ||\r\n"
				+ "       TRIM(TO_CHAR(T2.CARGOS, '999999999999999990.99')) || '\"|\"' ||\r\n"
				+ "       TRIM(TO_CHAR(T2.ABONOS, '999999999999999990.99')) || '\"|\"' ||\r\n"
				+ "       TRIM(TO_CHAR(DECODE(STACTA, 'D', SALDO_FINAL, 0), '999999999999999990.99')) || '\"|\"' ||\r\n"
				+ "       TRIM(TO_CHAR(DECODE(STACTA, 'D', 0, SALDO_FINAL), '999999999999999990.99'))\r\n"
				+ "FROM (SELECT CUENTA,\r\n"
				+ "             NOMCTA,\r\n"
				+ "             STACTA,\r\n"
				+ "             SALDO_INICIAL,\r\n"
				+ "             CARGOS,\r\n"
				+ "             ABONOS,\r\n"
				+ "             DECODE(T1.STACTA, 'D', SALDO_INICIAL + CARGOS - ABONOS,\r\n"
				+ "                    SALDO_INICIAL + ABONOS - CARGOS) SALDO_FINAL\r\n"
				+ "      FROM (SELECT CUENTA,\r\n"
				+ "                   NOMCTA,\r\n"
				+ "                   STACTA,\r\n"
				+ "                   DECODE(STACTA, 'D', SALINI + (").append(cargos.substring(0, cargos.length() - 2)).append(") - (").append(abonos.substring(0, abonos.length() -2)).append("),\r\n"
				+ "                          SALINI + (").append(abonos.substring(0, abonos.length() -2)).append(") - (").append(cargos.substring(0, cargos.length() - 2)).append(")) SALDO_INICIAL,\r\n"
				+ "                   (CARGOS_").append(mes).append(")                               CARGOS,\r\n"
				+ "                   (ABONOS_").append(mes).append(")                               ABONOS\r\n"
				+ "            FROM CUENTA\r\n"
				+ "            WHERE SCTA = ''\r\n"
				+ "              AND NOTCTA = 0\r\n"
				+ "              AND (SUBSTR(CUENTA, 4, 1) <> '0' OR SUBSTR(CUENTA, 1, 1) = '5' OR SUBSTR(CUENTA, 1, 2) = '81' OR\r\n"
				+ "                   CUENTA LIKE '7%0')\r\n"
				+ "              AND IDSECTOR = ").append(this.getUserDetails().getIdSector()).append(") T1 ");
		if (saldo0 == 0) {
			query.append(" WHERE (T1.SALDO_INICIAL <> 0 OR T1.CARGOS <> 0 OR T1.ABONOS <> 0) ");
		}
		
		query.append( " ORDER BY T1.CUENTA)T2");
		
		System.out.println("Query TXT-> " + query.toString());
		return query.toString();
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

	/**
	 * Generate where.
	 *
	 * @param idSector the id sector
	 * @param mes      the mes
	 * @param saldos   the saldos
	 * @return the string
	 */
	public String generateWhere(Integer idSector, Integer mes, Integer saldos) {
		String str = "";
		if (saldos == 0) {
			for (int i = 1; i <= mes; i++) {
				str = str + "ABONOS_" + i + " <> 0 OR CARGOS_" + i + " <> 0 OR ";
			}
			str = " AND (" + str + " SALINI <> 0)";
		}

		str = "IDSECTOR = " + idSector + str;

		return str;
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

	/**
	 * Gets the saldo 0.
	 *
	 * @return the saldo 0
	 */
	public Integer getSaldo0() {
		return saldo0;
	}

	/**
	 * Sets the saldo 0.
	 *
	 * @param saldo0 the new saldo 0
	 */
	public void setSaldo0(Integer saldo0) {
		this.saldo0 = saldo0;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	public TcPeriodoRepositoy getPeriodoRepositoy() {
		return periodoRepositoy;
	}

	public void setPeriodoRepositoy(TcPeriodoRepositoy periodoRepositoy) {
		this.periodoRepositoy = periodoRepositoy;
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

	public TcReportesFirmasRepository getReporteFirmasRepository() {
		return reporteFirmasRepository;
	}

	public void setReporteFirmasRepository(TcReportesFirmasRepository reporteFirmasRepository) {
		this.reporteFirmasRepository = reporteFirmasRepository;
	}

}
