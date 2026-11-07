package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.google.common.base.Strings;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadoCuentasMesGraficoMB.
 */
@ManagedBean
@ViewScoped
public class EstadoCuentasMesGraficoMB extends BaseDirectReport {
	private static final String DOWNLOAD_XLS = " jQuery('#form1\\\\:downXls').click();";

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The cuenta repository. */
	@ManagedProperty("#{cuentaRepository}")
	private CuentaRepository cuentaRepository;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	/** The mes. */
	private Integer mes;

	/** The cuenta. */
	private Cuenta cuenta;

	/** The order by. */
	private String orderBy;

	/** The selected cuenta. */
	private Cuenta selectedCuenta;

	/** The cuenta mayor. */
	private String cuentaMayor;

	/** The nombre cuenta mayor. */
	private String nombreCuentaMayor;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		LOGGER.info(":: En postconsruct EstadoCuentasMesGraficoMB ");
		jasperReporteName = "EstadoCuentasMesGrafico";
		endFilename = jasperReporteName + ".pdf";

		cuenta = new Cuenta();
		orderBy = "FECPOL";

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Firmas firmas = firmasRepository.findAllByIdsector(this.getUserDetails().getIdSector());
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		parameters.put("P_SECTOR", getUserDetails().getIdSector());
		parameters.put("P_MES", mes);
		parameters.put("P_IMG", getUserDetails().getPathImgCab1());

		parameters.put("P_CAMPO1", firmas.getCampo1());
		parameters.put("P_CAMPO2", firmas.getCampo2());
		parameters.put("P_CAMPO3", firmas.getCampo3().toString());

		parameters.put("P_CUENTA", cuenta.getCuenta());
		parameters.put("P_SCTA", cuenta.getScuenta());
		parameters.put("P_SSCTA", cuenta.getSscuenta());
		parameters.put("P_SSSCTA", cuenta.getSsscuenta());
		parameters.put("P_SSSSCTA", cuenta.getSssscuenta());

		parameters.put("P_L3", firmas.getL27());
		parameters.put("P_N3", firmas.getN27());
		parameters.put("P_L4", firmas.getL4());
		parameters.put("P_N4", firmas.getN4());
		parameters.put("P_L5", firmas.getL5());
		parameters.put("P_N5", firmas.getN5());

		parameters.put("P_ORDER_BY", orderBy);
		parameters.put("P_ORDER_BY_EXTENSO",
				orderBy.equals("FECPOL") ? "FECHA" : (orderBy.equals("REFPOL") ? "REFERENCIA" : "MES"));
		return parameters;
	}

	public void downloadXls() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			/*
			 * if (!validateCuenta()) { FacesMessage message = new
			 * FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
			 * "La cuenta no existe"); FacesContext.getCurrentInstance().addMessage(null,
			 * message); //RequestContext.getCurrentInstance().execute(
			 * "PF('blockUIWidget').unblock();"); } else {
			 */
			RequestContext.getCurrentInstance().execute(DOWNLOAD_XLS);
			// }
		}
	}

	public void viewPdf() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.createFilePdfInlineValidate();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.gem.sistema.web.bean.BaseDirectReport#generaReporteSimple(int)
	 */
	public StreamedContent generaReporteSimple(int type) {
		return null;
	}

	/**
	 * Left pad.
	 */
	public void leftPad() {
		String value = cuenta.getScuenta();
		if (StringUtils.isNotEmpty(value)) {
			cuenta.setScuenta(StringUtils.leftPad(value, 10, StringUtils.EMPTY + ZERO));
		}

		value = cuenta.getSscuenta();
		if (StringUtils.isNotEmpty(value)) {
			cuenta.setSscuenta(StringUtils.leftPad(value, 15, StringUtils.EMPTY + ZERO));
		}

		value = cuenta.getSsscuenta();
		if (StringUtils.isNotEmpty(value)) {
			cuenta.setSsscuenta(StringUtils.leftPad(value, 4, StringUtils.EMPTY + ZERO));
		}

		value = cuenta.getSssscuenta();
		if (StringUtils.isNotEmpty(value)) {
			cuenta.setSssscuenta(StringUtils.leftPad(value, 3, StringUtils.EMPTY + ZERO));
		}
	}

	/**
	 * Todos vacios.
	 *
	 * @return true, if successful
	 */
	private boolean todosVacios() {
		return Strings.isNullOrEmpty(cuenta.getCuenta()) && Strings.isNullOrEmpty(cuenta.getScuenta())
				&& Strings.isNullOrEmpty(cuenta.getSscuenta()) && Strings.isNullOrEmpty(cuenta.getSscuenta())
				&& Strings.isNullOrEmpty(cuenta.getSscuenta());
	}

	/**
	 * Validate cuenta.
	 *
	 * @return true, if successful
	 */
	private boolean validateCuenta() {
		List<Cuenta> lista = todosVacios() ? new ArrayList<Cuenta>()
				: cuentaRepository.findAllByCuentaAndNomctaAndIdsector(cuenta.getCuenta(), cuenta.getScuenta(),
						cuenta.getSscuenta(), cuenta.getSsscuenta(), cuenta.getSssscuenta(),
						((Integer) getUserDetails().getIdSector()).longValue());
		return !lista.isEmpty();
	}

	/**
	 * Creates the file pdf inline validate.
	 */
	public void createFilePdfInlineValidate() {
		/*
		 * if (!validateCuenta()) { FacesMessage message = new
		 * FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
		 * "La cuenta no existe"); FacesContext.getCurrentInstance().addMessage(null,
		 * message);
		 * RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();")
		 * ; } else {
		 */if (this.orderBy.equals("FECPOL, MESPOL") && this.getUserDetails().getIdSector() == 2)
			jasperReporteName = "EstadoCuentasMesGraficoPorMes";
		else
			jasperReporteName = "EstadoCuentasMesGrafico";

		createFilePdfInline();
		RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
		RequestContext.getCurrentInstance()
				.execute("$(PrimeFaces.escapeClientId('form1:panelReporte')).css('height', '45em');");
		// }
	}

	/**
	 * Gets the download file xls.
	 *
	 * @return the download file xls
	 */
	public StreamedContent getDownloadFileXls() {
		if (this.orderBy.equals("FECPOL, MESPOL") && this.getUserDetails().getIdSector() == 2)
			jasperReporteName = "EstadoCuentasMesGraficoPorMes";
		else
			jasperReporteName = "EstadoCuentasMesGrafico";

		return getFileXls();
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta the new cuenta
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
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
	 * Gets the selected cuenta.
	 *
	 * @return the selected cuenta
	 */
	public Cuenta getSelectedCuenta() {
		return selectedCuenta;
	}

	/**
	 * Sets the selected cuenta.
	 *
	 * @param selectedCuenta the new selected cuenta
	 */
	public void setSelectedCuenta(Cuenta selectedCuenta) {
		this.selectedCuenta = selectedCuenta;
	}

	/**
	 * Gets the cuenta mayor.
	 *
	 * @return the cuenta mayor
	 */
	public String getCuentaMayor() {
		return cuentaMayor;
	}

	/**
	 * Sets the cuenta mayor.
	 *
	 * @param cuentaMayor the new cuenta mayor
	 */
	public void setCuentaMayor(String cuentaMayor) {
		this.cuentaMayor = cuentaMayor;
	}

	/**
	 * Gets the nombre cuenta mayor.
	 *
	 * @return the nombre cuenta mayor
	 */
	public String getNombreCuentaMayor() {
		return nombreCuentaMayor;
	}

	/**
	 * Sets the nombre cuenta mayor.
	 *
	 * @param nombreCuentaMayor the new nombre cuenta mayor
	 */
	public void setNombreCuentaMayor(String nombreCuentaMayor) {
		this.nombreCuentaMayor = nombreCuentaMayor;
	}

	/**
	 * Gets the order by.
	 *
	 * @return the order by
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * Sets the order by.
	 *
	 * @param orderBy the new order by
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * Gets the firmas repository.
	 *
	 * @return the firmas repository
	 */
	public FirmasRepository getFirmasRepository() {
		return firmasRepository;
	}

	/**
	 * Sets the firmas repository.
	 *
	 * @param firmasRepository the new firmas repository
	 */
	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	/**
	 * Gets the cuenta repository.
	 *
	 * @return the cuenta repository
	 */
	public CuentaRepository getCuentaRepository() {
		return cuentaRepository;
	}

	/**
	 * Sets the cuenta repository.
	 *
	 * @param cuentaRepository the new cuenta repository
	 */
	public void setCuentaRepository(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

}
