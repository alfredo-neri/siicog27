package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Firmas;
import com.gem.sistema.business.domain.TrPuestoFirma;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.service.catalogos.PuestosFirmasService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.util.ConstantsClaveEnnum;
import com.google.common.base.Strings;

// TODO: Auto-generated Javadoc
/**
 * The Class IntegracionSaldosMB.
 */
@ManagedBean
@ViewScoped
public class IntegracionSaldosMB extends BaseDirectReport {

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

	/** The clasificacion. */
	private String clasificacion = "FECPOL";

	/** The selected cuenta. */
	private Cuenta selectedCuenta;

	/** The cuentas. */
	private List<Cuenta> cuentas;

	/** The cuenta mayor. */
	private String cuentaMayor;

	/** The nombre cuenta mayor. */
	private String nombreCuentaMayor;
	
	@ManagedProperty("#{puestosFirmasService}")
	private PuestosFirmasService puestosFirmasService;
		
	public PuestosFirmasService getPuestosFirmasService() {
		return puestosFirmasService;
	}

	public void setPuestosFirmasService(PuestosFirmasService puestosFirmasService) {
		this.puestosFirmasService = puestosFirmasService;
	}
	
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;
	
	

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		LOGGER.info(":: En postconsruct IntegracionSaldosMB ");
		// reportId = 2;
		jasperReporteName = "IntegracionSaldos";
		endFilename = jasperReporteName + ".pdf";

		cuenta = new Cuenta();

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.gem.sistema.web.bean.BaseDirectReport#getParametersReports()
	 */
	public Map<String, Object> getParametersReports() {
		TrPuestoFirma firma = null;
		Integer sector = this.getUserDetails().getIdSector();
		Conctb conctb = conctbRepository.findByIdsector(sector);
		Map<String, Object> parameters = new java.util.HashMap<String, Object>();

		parameters.put("P_SECTOR", sector);
		parameters.put("P_MES", mes);
		parameters.put("P_IMG", getUserDetails().getPathImgCab1());
		parameters.put("P_CAMPO1", this.getUserDetails().getMunicipio());
		parameters.put("P_CAMPO2", conctb.getRfc());
		parameters.put("P_CAMPO3", conctb.getAnoemp().toString());
		parameters.put("P_CUENTA", cuenta.getCuenta());
		parameters.put("P_SCTA", cuenta.getScuenta());
		parameters.put("P_SSCTA", cuenta.getSscuenta());
		parameters.put("P_SSSCTA", cuenta.getSsscuenta());
		parameters.put("P_SSSSCTA", cuenta.getSssscuenta());
		if(sector==1){
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F27.getValue());
			parameters.put("firmaP1", firma.getPuesto().getPuesto());
			parameters.put("firmaN1", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F28.getValue());
			parameters.put("firmaP2", firma.getPuesto().getPuesto());
			parameters.put("firmaN2", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F05.getValue());
			parameters.put("firmaP3", firma.getPuesto().getPuesto());
			parameters.put("firmaN3", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F03.getValue());
			parameters.put("firmaP4", firma.getPuesto().getPuesto());
			parameters.put("firmaN4", firma.getNombre());			
		}else{
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F05.getValue());
			parameters.put("firmaP1", firma.getPuesto().getPuesto());
			parameters.put("firmaN1", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F27.getValue());
			parameters.put("firmaP2", firma.getPuesto().getPuesto());
			parameters.put("firmaN2", firma.getNombre());
			firma = this.puestosFirmasService.getFirmaBySectorAnioClave(sector, 0L, ConstantsClaveEnnum.CLAVE_F04.getValue());
			parameters.put("firmaP3", firma.getPuesto().getPuesto());
			parameters.put("firmaN3", firma.getNombre());
			parameters.put("firmaP4", "");
			parameters.put("firmaN4", "");
			
		}
		
		parameters.put("P_CLASIFICACION", clasificacion);
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
	 * Buscar.
	 */
	public void buscar() {
		cuentas = cuentaRepository.findAllByCuentaAndNomctaAndIdsector(cuentaMayor, StringUtils.EMPTY,
				StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY,
				((Integer) getUserDetails().getIdSector()).longValue());
	}

	/**
	 * Left pad.
	 */
	public void leftPad() {
		String value = cuenta.getScuenta();
		if (StringUtils.isNotEmpty(value)) {
			cuenta.setScuenta(StringUtils.leftPad(value, 15, StringUtils.EMPTY + ZERO));
		}

		value = cuenta.getSscuenta();
		if (StringUtils.isNotEmpty(value)) {
			cuenta.setSscuenta(StringUtils.leftPad(value, 20, StringUtils.EMPTY + ZERO));
		}

		value = cuenta.getSsscuenta();
		if (StringUtils.isNotEmpty(value)) {
			cuenta.setSsscuenta(StringUtils.leftPad(value, 4, StringUtils.EMPTY + ZERO));
		}

		value = cuenta.getSssscuenta();
		if (StringUtils.isNotEmpty(value)) {
			cuenta.setSssscuenta(StringUtils.leftPad(value, 3, StringUtils.EMPTY + ZERO));
		}

		obtenerDatosCuentaInformada();
	}

	/**
	 * Obtener datos cuenta informada.
	 */
	private void obtenerDatosCuentaInformada() {
		if (!todosVacios()) {
			List<Cuenta> listaCuenta = cuentaRepository.findAllByCuentaAndNomctaAndIdsector(cuenta.getCuenta(),
					cuenta.getScuenta(), cuenta.getSscuenta(), cuenta.getSsscuenta(), cuenta.getSssscuenta(),
					((Integer) getUserDetails().getIdSector()).longValue());
			Cuenta cta = listaCuenta.isEmpty() ? null : listaCuenta.get(0);
			if (Objects.nonNull((cta))) {
				cuenta.setNomcta(cta.getNomcta());
				;
			} else {
				cuenta.setNomcta("");
			}
		}
	}

	/**
	 * On row select.
	 */
	public void onRowSelect() {
		if (Objects.nonNull(selectedCuenta)) {
			this.cuenta.setCuenta(selectedCuenta.getCuenta());
			this.cuenta.setScuenta(selectedCuenta.getScuenta());
			this.cuenta.setSscuenta(selectedCuenta.getSscuenta());
			this.cuenta.setSsscuenta(selectedCuenta.getSsscuenta());
			this.cuenta.setSssscuenta(selectedCuenta.getSssscuenta());
		}
	}

	/**
	 * Todos vacios.
	 *
	 * @return true, if successful
	 */
	private boolean todosVacios() {
		return Strings.isNullOrEmpty(cuenta.getCuenta()) || Strings.isNullOrEmpty(cuenta.getScuenta());
	}

	/**
	 * Creates the file txt inline validate.
	 */
	public void createFileTxtInlineValidate() {
		/*
		 * if (!validateCuenta()) { FacesMessage message = new
		 * FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
		 * "La cuenta no existe"); FacesContext.getCurrentInstance().addMessage(null,
		 * message); } else {
		 */
		RequestContext.getCurrentInstance().execute("$(PrimeFaces.escapeClientId('form1:donwloadTxt')).click();");
		// }
	}

	/**
	 * Validate cuenta.
	 *
	 * @return true, if successful
	 */
	private boolean validateCuenta() {
		boolean isValid = StringUtils.isNotBlank(cuenta.getCuenta());

		if (!isValid) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"Por favor debe informar una cuenta.");
			FacesContext.getCurrentInstance().addMessage(null, message);
			RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
		} else {
			List<Cuenta> lista = cuentaRepository.findAllByCuentaAndNomctaAndIdsector(cuenta.getCuenta(),
					cuenta.getScuenta(), cuenta.getSscuenta(), cuenta.getSsscuenta(), cuenta.getSssscuenta(),
					((Integer) getUserDetails().getIdSector()).longValue());
			if (lista.isEmpty()) {
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
						"La cuenta no existe");
				FacesContext.getCurrentInstance().addMessage(null, message);
				RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
				isValid = false;
			}
		}

		return isValid;
	}

	/**
	 * Creates the file pdf inline validate.
	 */
	public void createFilePdfInlineValidate() {
		// if (validateCuenta()) {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			createFilePdfInline();
			RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
			RequestContext.getCurrentInstance()
					.execute("$(PrimeFaces.escapeClientId('form1:panelReporte')).css('height', '57em');");
		}
		// RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
		// }
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
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentas the new cuentas
	 */
	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
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
	 * Gets the clasificacion.
	 *
	 * @return the clasificacion
	 */
	public String getClasificacion() {
		return clasificacion;
	}

	/**
	 * Sets the clasificacion.
	 *
	 * @param clasificacion the new clasificacion
	 */
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
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
