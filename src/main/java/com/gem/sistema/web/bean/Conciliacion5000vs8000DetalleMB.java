package com.gem.sistema.web.bean;

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

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.util.ConstantsClaveEnnum;

// TODO: Auto-generated Javadoc
/**
 * The Class Conciliacion5000vs8000DetalleMB.
 */
@ManagedBean
@ViewScoped
public class Conciliacion5000vs8000DetalleMB extends BaseDirectReport {

	

	@ManagedProperty("#{validatePolicyService}")
	private ValidatePolicyService validatePolicyService;

	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;

	/** The mes inicial. */
	private Integer mesInicial;

	/** The mes final. */
	private Integer mesFinal;

	/** The partida. */
	private Integer partida;

	/** The cuentas con saldo cero. */
	private boolean cuentasConSaldoCero;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		LOGGER.info(":: En postconsruct Conciliacion5000vs8000DetalleMB ");
		jasperReporteName = "Conciliacion5000vs8000Detalle";
		endFilename = jasperReporteName + ".pdf";
		cuentasConSaldoCero = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		Conctb conctb = conctbRepository.findByIdsector(getUserDetails().getIdSector());
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();
		TrPuestoFirma firma = new TrPuestoFirma();
		
		parameters.put("P_SECTOR", getUserDetails().getIdSector());
		parameters.put("P_IMG", this.getUserDetails().getPathImgCab1());
		parameters.put("P_CAMPO1", conctb.getNomDep());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(getUserDetails().getIdSector(), 0L, ConstantsClaveEnnum.CLAVE_F01.getValue());
		parameters.put("P_L1", firma.getPuesto().getPuesto());
		parameters.put("P_N1", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(getUserDetails().getIdSector(), 0L, ConstantsClaveEnnum.CLAVE_F02.getValue());
		parameters.put("P_L2", firma.getPuesto().getPuesto());
		parameters.put("P_N2", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(getUserDetails().getIdSector(), 0L, ConstantsClaveEnnum.CLAVE_F03.getValue());
		parameters.put("P_L3", firma.getPuesto().getPuesto());
		parameters.put("P_N3", firma.getNombre());
		firma = puestosFirmasService.getFirmaBySectorAnioClave(getUserDetails().getIdSector(), 0L, ConstantsClaveEnnum.CLAVE_F04.getValue());
		parameters.put("P_L4", firma.getPuesto().getPuesto());
		parameters.put("P_N4", firma.getNombre());
		parameters.put("P_MES_INICIAL", mesInicial);
		parameters.put("P_MES_FINAL", mesFinal);
		parameters.put("P_SSSCTA", String.valueOf(partida));
		parameters.put("P_CUENTAS_CON_SALDO_CERO", cuentasConSaldoCero ? "S" : "N");

		return parameters;
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
	 * Creates the file pdf inline validate.
	 */
	public void createFilePdfInlineValidate() {
		if (validInputs()) {
			Integer monthValidate = mesFinal;
			Integer monthOpen = this.conctbRepository.findByIdsector(this.getUserDetails().getIdSector()).getMesemp();
			if (monthOpen < mesInicial) {
				monthValidate = mesInicial;
			} else if (monthOpen < mesFinal) {
				monthValidate = mesFinal;
			}
			if (this.validatePolicyService.isOpenMonth(monthValidate, null,
					this.getUserDetails().getIdSector()) == Boolean.TRUE) {
				createFilePdfInline();
				RequestContext.getCurrentInstance()
						.execute("$(PrimeFaces.escapeClientId('form1:panelReporte')).css('height', '45em');");
			}
			RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
		}
	}

	/**
	 * Valid inputs.
	 *
	 * @return true, if successful
	 */
	private boolean validInputs() {
		boolean flag = true;

		if (mesFinal < mesInicial) {
			flag = false;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"Mes inicial debe ser menor o igual al mes final.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		if (null == partida) {
			flag = false;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"Favor de capturar una Partida.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
		return flag;
	}

	

	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}

	/**
	 * Gets the mes inicial.
	 *
	 * @return the mes inicial
	 */
	public Integer getMesInicial() {
		return mesInicial;
	}

	/**
	 * Sets the mes inicial.
	 *
	 * @param mesInicial
	 *            the new mes inicial
	 */
	public void setMesInicial(Integer mesInicial) {
		this.mesInicial = mesInicial;
	}

	/**
	 * Gets the mes final.
	 *
	 * @return the mes final
	 */
	public Integer getMesFinal() {
		return mesFinal;
	}

	/**
	 * Sets the mes final.
	 *
	 * @param mesFinal
	 *            the new mes final
	 */
	public void setMesFinal(Integer mesFinal) {
		this.mesFinal = mesFinal;
	}

	/**
	 * Gets the partida.
	 *
	 * @return the partida
	 */
	public Integer getPartida() {
		return partida;
	}

	/**
	 * Sets the partida.
	 *
	 * @param partida
	 *            the new partida
	 */
	public void setPartida(Integer partida) {
		this.partida = partida;
	}

	/**
	 * Checks if is cuentas con saldo cero.
	 *
	 * @return true, if is cuentas con saldo cero
	 */
	public boolean isCuentasConSaldoCero() {
		return cuentasConSaldoCero;
	}

	/**
	 * Sets the cuentas con saldo cero.
	 *
	 * @param cuentasConSaldoCero
	 *            the new cuentas con saldo cero
	 */
	public void setCuentasConSaldoCero(boolean cuentasConSaldoCero) {
		this.cuentasConSaldoCero = cuentasConSaldoCero;
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

}
