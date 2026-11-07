package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.StreamedContent;

import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.predicates.CuentaPredicates;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.catalogos.PolideService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;

// TODO: Auto-generated Javadoc
/**
 * The Class ConsultaMovimientosMB.
 */
@ManagedBean
@ViewScoped
public class ConsultaMovimientosMB extends BaseDirectReport {

	/** The firmas repository. */
	@ManagedProperty("#{firmasRepository}")
	private FirmasRepository firmasRepository;

	/** The polide service. */
	@ManagedProperty("#{polideService}")
	private PolideService polideService;

	@ManagedProperty("#{cuentaRepository}")
	private CuentaRepository cuentaRepository;

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	/** The polide. */
	private Polide polide;

	/** The selected. */
	private Polide selected;

	/** The lista. */
	private List<Polide> lista;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		LOGGER.info(":: En postconsruct ConsultaMovimientosMB ");
		// reportId = 2;
		jasperReporteName = "";
		endFilename = jasperReporteName + ".pdf";

		polide = new Polide();
		polide.setCuenta("1000");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		return null;
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
	 * Buscar.
	 */
	public void buscar() {
		if (validaCampos() == Boolean.TRUE) {
			if (this.validatePolicyService.isOpenMonth(polide.getMespol(), null,
					this.getUserDetails().getIdSector()) == Boolean.TRUE) {
				polide.setIdsector(getUserDetails().getIdSector());
				lista = polideService.consultaMovimientos(polide);
				RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
			}
			RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
		}
	}

	/**
	 * Left pad.
	 */
	public void leftPad() {
		String value = polide.getScta();
		if (StringUtils.isNotEmpty(value)) {
			if (StringUtils.substring(polide.getCuenta(), 0, 1).equals(String.valueOf("5"))
					|| StringUtils.substring(polide.getCuenta(), 0, 2).equals(String.valueOf("82"))) {
				polide.setScta(StringUtils.rightPad(value.toUpperCase(), 15, StringUtils.EMPTY + ZERO));
			} else {
				polide.setScta(StringUtils.leftPad(value.toUpperCase(), 15, StringUtils.EMPTY + ZERO));
			}

		}

		value = polide.getSscta();
		if (StringUtils.isNotEmpty(value)) {
			polide.setSscta(StringUtils.leftPad(value.toUpperCase(), 20, StringUtils.EMPTY + ZERO));
		}

		value = polide.getSsscta();
		if (StringUtils.isNotEmpty(value)) {
			polide.setSsscta(StringUtils.leftPad(value.toUpperCase(), 4, StringUtils.EMPTY + ZERO));
		}

		value = polide.getSssscta();
		if (StringUtils.isNotEmpty(value)) {
			polide.setSssscta(StringUtils.leftPad(value.toUpperCase(), 3, StringUtils.EMPTY + ZERO));
		}

	}

	/**
	 * On row select.
	 */
	public void onRowSelect() {
		if (Objects.nonNull(selected)) {
			this.polide.setCuenta(selected.getCuenta());
			this.polide.setScta(selected.getScta());
			this.polide.setSscta(selected.getSscta());
			this.polide.setSsscta(selected.getSsscta());
			this.polide.setSssscta(selected.getSssscta());
		}
	}

	/**
	 * Valida campos.
	 *
	 * @return true, if successful
	 */
	private boolean validaCampos() {
		// boolean isValid = StringUtils.isNotBlank(polide.getCuenta()) &&
		// StringUtils.isNotBlank(polide.getScta())
		// && StringUtils.isNotBlank(polide.getSscta())
		// && (StringUtils.isNotBlank(polide.getSsscta()) ||
		// StringUtils.isNotBlank(polide.getSssscta()));
		polide.setIdsector(this.getUserDetails().getIdSector());
		Boolean isValidate = Boolean.TRUE;
		if (CollectionUtils.isEmpty(
				(Collection<?>) this.getCuentaRepository().findAll(CuentaPredicates.isLastLavelAcount(polide)))) {
			isValidate = Boolean.FALSE;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"La cuenta no exite o no es de último nivel. Favor de validar.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
		}

		return isValidate;
	}

	// public void buscar() {
	// if (!validaCampos()) {
	// FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
	// StringUtils.EMPTY,
	// "La cuenta no existe");
	// FacesContext.getCurrentInstance().addMessage(null, message);
	// RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
	// } else {
	// createFilePdfInline();
	// RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
	// RequestContext.getCurrentInstance()
	// .execute("$(PrimeFaces.escapeClientId('form1:panelReporte')).css('height',
	// '57em');");
	// }
	// }

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
	 * @param firmasRepository
	 *            the new firmas repository
	 */
	public void setFirmasRepository(FirmasRepository firmasRepository) {
		this.firmasRepository = firmasRepository;
	}

	/**
	 * Gets the polide service.
	 *
	 * @return the polide service
	 */
	public PolideService getPolideService() {
		return polideService;
	}

	/**
	 * Sets the polide service.
	 *
	 * @param polideService
	 *            the new polide service
	 */
	public void setPolideService(PolideService polideService) {
		this.polideService = polideService;
	}

	/**
	 * Gets the polide.
	 *
	 * @return the polide
	 */
	public Polide getPolide() {
		return polide;
	}

	/**
	 * Sets the polide.
	 *
	 * @param polide
	 *            the new polide
	 */
	public void setPolide(Polide polide) {
		this.polide = polide;
	}

	/**
	 * Gets the selected.
	 *
	 * @return the selected
	 */
	public Polide getSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected
	 *            the new selected
	 */
	public void setSelected(Polide selected) {
		this.selected = selected;
	}

	/**
	 * Gets the lista.
	 *
	 * @return the lista
	 */
	public List<Polide> getLista() {
		return lista;
	}

	/**
	 * Sets the lista.
	 *
	 * @param lista
	 *            the new lista
	 */
	public void setLista(List<Polide> lista) {
		this.lista = lista;
	}

	public CuentaRepository getCuentaRepository() {
		return cuentaRepository;
	}

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
