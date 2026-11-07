package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcMes;
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
 * The Class ECPEMB.
 *
 * @author Alfredo
 * @sector municipal
 */
@Component("eCPEMB")
@Scope("view")
public class ECPEMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_EDO_COMP_PRE_EGRE";
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_ECPE";

	private Integer mes;

	private Integer saldo0;

	/** The conctb. */
	private Conctb conctb;

	private StreamedContent txt;

	/** The conctb repository. */
	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The tc mes repository. */
	@Value("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	@Value("#{executePlService}")
	private ExecutePlService executePlService;

	@Value("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@Value("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@Value("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		jasperReporteName = "ECPE";
		this.saldo0 = 0;
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
		TcMes mesName = tcMesRepository.findByMes(StringUtils.leftPad(mes.toString(), 2, "0"));
		conctb = conctbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport(mes, conctb, reporte);

		paramsReport.put("imagePath1", conctb.getImagePathRigth());
		paramsReport.put("imagePath2", conctb.getImagePathLeft());
		paramsReport.put("entidadName", conctb.getNomDep());
		paramsReport.put("entidadClave", conctb.getClave());
		paramsReport.put("lastDayOfMonth", getLastDayByAnoEmp(mes, conctb.getAnoemp()));
		paramsReport.put("mesName", mesName.getDescripcion());
		paramsReport.put("year", conctb.getAnoemp());
		paramsReport.put("sql", this.generateQueryECPE(mes, sector));
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
		String query = this.generateQueryECPE(mes, this.getUserDetails().getIdSector());

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
			txt = StreamedContents.of(stream, "application/txt", out.get("O_NAME_FILE").toString(), null);
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
	 * Generate query ECPE.
	 *
	 * @param mes    the mes
	 * @param sector the sector
	 * @return the string
	 */
	public String generateQueryECPE(Integer mes, Integer sector) {
		StringBuilder sQuery = new StringBuilder();
		StringBuilder toej = new StringBuilder();
		StringBuilder redu = new StringBuilder();
		StringBuilder ampl = new StringBuilder();
		StringBuilder auto = new StringBuilder();

		for (Integer i = 1; i <= mes; i++) {
			auto.append(" PA.AUTO1_" + i + " +");
			redu.append(" PA.REDU1_" + i + " +");
			ampl.append(" PA.AMPLI1_" + i + " +");
			toej.append(" PA.TOEJE1_" + i + " +");
		}

		sQuery.append("SELECT	T1.*, (T1.MODIFICADO_ACUMULADO - T1.EJERCIDO_ACUMULADO) ABSOLUTA,")
				.append("DECODE(SUBSTR(T1.PARTIDA,2,3),'000',1,0) SUMAR ").append("FROM( SELECT PA.PARTIDA,NA.NOMGAS,")
				.append("SUM(PA.AUTO1_1 + PA.AUTO1_2 + PA.AUTO1_3 + PA.AUTO1_4 + PA.AUTO1_5 + PA.AUTO1_6 + PA.AUTO1_7 + PA.AUTO1_8 + PA.AUTO1_9 + PA.AUTO1_10 + PA.AUTO1_11 + PA.AUTO1_12) AUTORIZADO,")
				.append("SUM(PA.AUTO1_" + mes + " + PA.AMPLI1_" + mes + " - PA.REDU1_" + mes + ")MODIFICADO_MES,")
				.append("SUM(PA.TOEJE1_" + mes + ") EJERCIDO_MES,")
				.append("SUM((" + auto.substring(0, auto.length() - 2) + ") + (" + ampl.substring(0, ampl.length() - 2)
						+ ") - (" + redu.substring(0, redu.length() - 2) + ")) MODIFICADO_ACUMULADO,")
				.append("SUM(" + toej.substring(0, toej.length() - 2) + ") EJERCIDO_ACUMULADO")
				.append(" FROM PASO PA, NATGAS NA")
				.append(" WHERE PA.IDSECTOR = NA.IDSECTOR AND PA.PARTIDA = NA.CLVGAS AND PA.IDSECTOR= " + sector)
				.append(" GROUP BY PA.PARTIDA,NA.NOMGAS ").append("ORDER BY PA.PARTIDA )T1");

		if (saldo0 == 0) {
			sQuery.append(" WHERE T1.AUTORIZADO <> 0 OR T1.MODIFICADO_MES <> 0 ")
					.append(" OR T1.EJERCIDO_MES <> 0 OR T1.MODIFICADO_ACUMULADO <> 0 ")
					.append(" OR T1.EJERCIDO_ACUMULADO <> 0");
		}

		System.out.println(sQuery.toString());
		return sQuery.toString();
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public Integer getMes() {
		return mes;
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
			this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());
			this.createFilePdfInline();
		}
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

	public Integer getSaldo0() {
		return saldo0;
	}

	public void setSaldo0(Integer saldo0) {
		this.saldo0 = saldo0;
	}

}
