package com.gem.sistema.web.bean;

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
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilReport.getNameFileReport;
import static com.roonin.utils.UtilDate.getLastDayByAnoEmp;

// TODO: Auto-generated Javadoc
/**
 * The Class ECPIMB.
 * 
 * @author Alfredo
 * @sector municipal
 */
@ManagedBean(name = "eCPIMB")
@ViewScoped
public class ECPIMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_EDO_COMP_PRE_INGR";
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_ECPI";

	/** The mes. */
	private Integer mes;
	
	private StreamedContent txt;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@ManagedProperty("#{executePlService}")
	private ExecutePlService executePlService;
	
	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct RF009115MB ");
		jasperReporteName = "ECPI";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	@Override
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport(mes, conctb, reporte);

		paramsReport.put("entidadName", conctb.getNomDep());
		paramsReport.put("entidadClave", conctb.getClave());
		paramsReport.put("imagePath1", conctb.getImagePathRigth());
		paramsReport.put("imagePath2", conctb.getImagePathLeft());
		paramsReport.put("lastDayOfMonth", getLastDayByAnoEmp(mes, conctb.getAnoemp()));
		paramsReport.put("mesName",
				tcMesRepository.findByMes(StringUtils.leftPad(mes.toString(), 2, "0")).getDescripcion());
		paramsReport.put("year", conctb.getAnoemp());
		paramsReport.put("sql", this.generateQuery(sector, mes));
		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return paramsReport;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	@Override
	public StreamedContent generaReporteSimple(int type) throws ReportValidationException {
		// TODO Auto-generated method stub
		return null;
	}

	public StreamedContent getTxt() {
		Map<String, Object> out;
		InputStream stream = null;
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		String query = this.generateQuery(this.getUserDetails().getIdSector(), mes);

		parameter.addValue("i_mes", mes);
		parameter.addValue("i_sector", this.getUserDetails().getIdSector());
		parameter.addValue("i_query", query);

		out = executePlService.callProcedure(PROCEDURE_NAME, parameter);

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(
						new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			txt = new DefaultStreamedContent(stream, "application/txt", out.get("O_NAME_FILE").toString());
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSG_ERROR").toString());
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", out.get("O_MSG_ERROR").toString());
		}

		return txt;
	}

	public void setTxt(StreamedContent txt) {
		this.txt = txt;
	}

	/**
	 * Generate query.
	 *
	 * @param idSector the id sector
	 * @param mes      the mes
	 * @return the string
	 */
	public String generateQuery(Integer idSector, Integer mes) {
		StringBuilder cargo10 = new StringBuilder();
		StringBuilder abono10 = new StringBuilder();
		StringBuilder cargo50 = new StringBuilder();
		StringBuilder abono50 = new StringBuilder();
		StringBuilder ingreso = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Integer i = 1;

		while (i <= mes) {
			cargo10.append(" DI.CARGOS_" + i + " + ");
			abono10.append(" DI.ABONOS_" + i + " + ");
			cargo50.append(" CI.CARGOS_" + i + " + ");
			abono50.append(" CI.ABONOS_" + i + " + ");
			ingreso.append(" II.AUTOI_" + i + " + ");
			i++;
		}
		query.append(
				"SELECT SCTA, CUENTA||'  '||SCTA||'  '||SSCTA||'  '||SSSCTA||'  '||SSSSCTA CUENTA, NOMCTA, ESTIMADA, MODIFICADO_MES, RECAUDADO_MES, ")
				.append("MODIFICADO_ACUMULADO, RECAUDADO_ACUMULADO, (MODIFICADO_ACUMULADO -RECAUDADO_ACUMULADO) VARABS, ")
				.append("CASE WHEN MODIFICADO_ACUMULADO = 0 THEN 0 ")
				.append("ELSE ABS(((RECAUDADO_ACUMULADO - MODIFICADO_ACUMULADO) / MODIFICADO_ACUMULADO) * 100) END PORCENTAJE ")
				.append("FROM (SELECT CI.CUENTA, SUBSTR(DI.SCTA,12) SCTA,SUBSTR(DI.SSCTA,18,3)SSCTA, SUBSTR(DI.SSSCTA,2,3) SSSCTA, DI.SSSSCTA, DI.NOMCTA, ")
				.append("(DI.SALINI + (" + cargo10.substring(0, cargo10.length() - 2) + ") - ("
						+ abono10.substring(0, abono10.length() - 2) + ")) ESTIMADA, ")
				.append("(II.AUTOI_" + mes + " + DI.CARGOS_" + mes + " - DI.ABONOS_" + mes + ") MODIFICADO_MES, ")
				.append("(CI.ABONOS_" + mes + " - CI.CARGOS_" + mes + ") RECAUDADO_MES, ")
				.append("((" + ingreso.substring(0, ingreso.length() - 2) + ") + ("
						+ cargo10.substring(0, cargo10.length() - 2) + ") - ("
						+ abono10.substring(0, abono10.length() - 2) + ")) MODIFICADO_ACUMULADO, ")
				.append("((" + abono50.substring(0, abono50.length() - 2) + ") - ("
						+ cargo50.substring(0, cargo50.length() - 2) + ")) RECAUDADO_ACUMULADO")
				.append(" FROM CUENTA AS DI ")
				.append("INNER JOIN CUENTA AS CI ON CI.CUENTA= '8150' AND CI.SCTA=DI.SCTA AND CI.SSCTA=DI.SSCTA ")
				.append("AND CI.SSSCTA=DI.SSSCTA AND CI.SSSSCTA=DI.SSSSCTA AND CI.IDSECTOR = DI.IDSECTOR ")
				.append("LEFT JOIN INGRESO AS II ON DI.CUENTA = II.CUENTA AND DI.SCTA = II.SCTA AND DI.SSCTA=II.SSCTA ")
				.append("AND DI.SSSCTA = II.SSSCTA AND DI.SSSSCTA=II.SSSSCTA AND DI.IDSECTOR=II.IDSECTOR ")
				.append("WHERE (DI.CUENTA='8110')  AND DI.IDSECTOR=" + idSector)
				.append(" ORDER BY DI.CUENTA,DI.SCTA, DI.SSCTA, DI.SSSCTA, DI.SSSSCTA ) T1");

		System.out.println(query.toString());
		return query.toString();
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());
			this.createFilePdfInline();
			;
		}
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

	/**
	 * Gets the tc mes repository.
	 *
	 * @return the tc mes repository
	 */
	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	/**
	 * Sets the tc mes repository.
	 *
	 * @param tcMesRepository the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
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
