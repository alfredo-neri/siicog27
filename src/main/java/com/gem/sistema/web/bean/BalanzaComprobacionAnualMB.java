package com.gem.sistema.web.bean;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcReportesFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.TcReportesFirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;

import static com.gem.sistema.util.UtilReport.getNameFileReport;

// TODO: Auto-generated Javadoc
/**
 * The Class RF001017MB.
 * 
 * @author Alfredo
 * @Timeline <strong>Date:</strong> 02/09/2021 <strong>Changes:</strong> Se
 *           implementa Servicio de firmas dinamicas<br>
 * @Sector Municipal
 */
@ManagedBean
@ViewScoped
public class BalanzaComprobacionAnualMB extends BaseDirectReport {
	private static final String CLAVE_REPORT = "RPT_CP_BAL_COM_DET_ANUAL";
	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";

	private Integer noDecimales;
	private Integer pesos;

	/** The mes. */
	private Integer mes;

	/** The cuenta inicial. */
	private Integer cuentaInicial;

	/** The cuenta final. */
	private Integer cuentaFinal;

	/** The nivel. */
	private Integer nivel;

	/** The con saldo cero. */
	private String conSaldoCero;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	@ManagedProperty("#{tcReportesFirmasRepository}")
	private TcReportesFirmasRepository reporteFirmasRepository;

	/**
	 * Gets the cuenta inicial.
	 *
	 * @return the cuenta inicial
	 */
	public Integer getCuentaInicial() {
		return cuentaInicial;
	}

	/**
	 * Sets the cuenta inicial.
	 *
	 * @param cuentaInicial the new cuenta inicial
	 */
	public void setCuentaInicial(Integer cuentaInicial) {
		this.cuentaInicial = cuentaInicial;
	}

	/**
	 * Gets the cuenta final.
	 *
	 * @return the cuenta final
	 */
	public Integer getCuentaFinal() {
		return cuentaFinal;
	}

	/**
	 * Sets the cuenta final.
	 *
	 * @param cuentaFinal the new cuenta final
	 */
	public void setCuentaFinal(Integer cuentaFinal) {
		this.cuentaFinal = cuentaFinal;
	}

	/**
	 * Gets the nivel.
	 *
	 * @return the nivel
	 */
	public Integer getNivel() {
		return nivel;
	}

	/**
	 * Sets the nivel.
	 *
	 * @param nivel the new nivel
	 */
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	/**
	 * Gets the con saldo cero.
	 *
	 * @return the con saldo cero
	 */
	public String getConSaldoCero() {
		return conSaldoCero;
	}

	/**
	 * Sets the con saldo cero.
	 *
	 * @param conSaldoCero the new con saldo cero
	 */
	public void setConSaldoCero(String conSaldoCero) {
		this.conSaldoCero = conSaldoCero;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct BalanzaComprobacionAnualMB ");
		jasperReporteName = "balanzaDetalladaAnual";

		cuentaInicial = 1111;
		cuentaFinal = 9999;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() throws ReportValidationException {
		Map<String, Object> paramsReport = new java.util.HashMap<String, Object>();
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		TcReportesFirma reporte = reporteFirmasRepository.findByIdSectorAndClave(sector, CLAVE_REPORT);

		endFilename = getNameFileReport(conctb, reporte);

		paramsReport.put("year", conctb.getAnoemp());
		paramsReport.put("pesos", pesos);
		paramsReport.put("sector", sector);
		paramsReport.put("decimalFormat", "%,." + noDecimales + "f");
		paramsReport.put("imagenPathLeft", conctb.getImagePathRigth());
		paramsReport.put("imagenPathRigth", StringUtils.EMPTY);
		paramsReport.put("nombreMunicipio", conctb.getNomDep());
		paramsReport.put("sql", generateSQL());
		paramsReport.putAll(puestosFirmasService.getFirmasOfReport(sector, CLAVE_REPORT));

		return paramsReport;
	}

	public void downloadXls() {
		this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
		this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());

		RequestContext.getCurrentInstance().execute(DOWNLOAD_XLS);
	}

	public void viewPdf() {
		this.validatePolicyService.isAfectMonth(mes, null, this.getUserDetails().getIdSector());
		this.validatePolicyService.existPolices(mes, null, this.getUserDetails().getIdSector());

		this.createFilePdfInline();
	}

	private String generateSQL() {
		StringBuilder localStringBuilder1 = new StringBuilder();
		StringBuilder cargos = new StringBuilder();
		StringBuilder abonos = new StringBuilder();
		String niveles = "";
		String saldoCero = "";

		if (mes == 1) {
			cargos.append("0 + ");
			abonos.append("0 + ");
		} else {
			for (int i = 1; i < mes.intValue(); i++) {
				cargos.append("CARGOS_" + i + " + ");
				abonos.append("ABONOS_" + i + " + ");
			}
		}

		if (conSaldoCero.equals("N")) {
			saldoCero = " WHERE ( T1.SALDO_INICIAL<>0 OR T1.CARGOS <> 0 OR T1.ABONOS <> 0 ) ";
		}

		switch (nivel.intValue()) {
		case 1:
			niveles = " AND SCTA = '' AND SSCTA = '' AND SSSCTA = '' AND SSSSCTA = '' ";
			break;
		case 2:
			niveles = " AND SSCTA = '' AND SSSCTA = '' AND SSSSCTA = '' ";
			break;
		case 3:
			niveles = " AND SSSCTA = '' AND SSSSCTA = '' ";
			break;
		case 4:
			niveles = " AND SSSSCTA = '' ";
		}

		localStringBuilder1.append("SELECT\tT1.*, DECODE(UPPER(T1.STACTA), 'D',")
				.append(" SALDO_INICIAL + CARGOS - ABONOS, SALDO_INICIAL + ABONOS - CARGOS) SALDO_FINAL")
				.append(" FROM ( SELECT CUENTA||SCTA||SSCTA||SSSCTA||SSSSCTA GRUPO, CUENTA, SCTA, SSCTA,")
				.append(" SSSCTA,SSSSCTA, NOMCTA, STACTA, DECODE(UPPER(STACTA), 'D', SALINI + (")
				.append(cargos.substring(0, cargos.length() - 3)).append(") - (")
				.append(abonos.substring(0, abonos.length() - 3)).append("),SALINI + (")
				.append(abonos.substring(0, abonos.length() - 3)).append(") - (")
				.append(cargos.substring(0, cargos.length() - 3)).append(")) SALDO_INICIAL, CARGOS_").append(mes)
				.append(" CARGOS, ABONOS_").append(mes).append(" ABONOS")
				.append(" FROM CUENTA WHERE NOTCTA = 0 AND IDSECTOR= ").append(getUserDetails().getIdSector())
				.append(" AND CUENTA BETWEEN '").append(cuentaInicial).append("' AND '").append(cuentaFinal).append("'")
				.append(niveles).append(" AND CUENTA <> '8200' AND CUENTA <> '8210' AND CUENTA <> '8220'")
				.append("  AND CUENTA <> '8230' AND CUENTA <> '8240' AND CUENTA <> '8250'")
				.append("  AND CUENTA <> '8260' AND CUENTA <> '8270' AND CUENTA <> '8280'")
				.append("  AND CUENTA <> '8290' )").append(" T1 ").append(saldoCero)
				.append(" ORDER BY T1.CUENTA, T1.SCTA, T1.SSCTA, T1.SSSCTA, T1.SSSSCTA");

		if (pesos != 1) {
			localStringBuilder1.insert(0,
					"SELECT GRUPO, CUENTA, SCTA, SSCTA, SSSCTA, SSSSCTA, NOMCTA, STACTA, "
							+ "(SALDO_INICIAL / 1000) SALDO_INICIAL, (CARGOS / 1000) CARGOS, "
							+ " (ABONOS / 1000) ABONOS, (SALDO_FINAL / 1000) SALDO_FINAL FROM (");
			localStringBuilder1.append(" ) T");
		}

		System.out.println(":::::::::" + localStringBuilder1.toString());
		return localStringBuilder1.toString();
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

}
