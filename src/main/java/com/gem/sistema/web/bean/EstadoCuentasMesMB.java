package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.predicates.CuentaPredicates;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.business.utils.ConvertCharsetUtils;
import com.gem.sistema.business.service.catalogos.GeneratePreviewService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.google.common.base.Strings;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadoCuentasMesMB.
 */
@ManagedBean
@ViewScoped
public class EstadoCuentasMesMB extends GenericExecuteProcedure {

	private static final String DOWNLOAD_TXT = " jQuery('#form1\\\\:downTxt').click();";

	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_CUENTAALMES";

	/** The Constant PROCEDURE_NAME_POR_MES. */
	private static final String PROCEDURE_NAME_POR_MES = "SP_GENERA_TXT_CUENTAALMES_ESPECIAL";

	/** The generate preview service. */
	@ManagedProperty("#{generatePreviewService}")
	private GeneratePreviewService generatePreviewService;

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

	/** The cuentas. */
	private List<Cuenta> cuentas;

	/** The cuenta mayor. */
	private String cuentaMayor;

	/** The nombre cuenta mayor. */
	private String nombreCuentaMayor;

	/** The file txt. */
	private StreamedContent fileTxt;

	/** The txt preview. */
	private StringBuilder txtPreview;

	/** The bandera. */
	private Boolean bandera;

	/** The b pre view. */
	private Boolean bPreView = Boolean.FALSE;

	/** The Constant charSetISO. */
	private final static String charSetISO = "ISO-8859-1";

	/** The Constant charSetUFT. */
	private final static String charSetUFT = "utf-8";

	/** The Constant FOCUS_IN_PREVIEW. */
	private static final String FOCUS_IN_PREVIEW = "PrimeFaces.focus('form1:preViewTxt');";

	/** The Constant SECTOR_MUNICIPAL. */
	private static final Integer SECTOR_MUNICIPAL = 1;

	/** The Constant SECTOR_CENTRAL. */
	private static final Integer SECTOR_CENTRAL = 2;

	/** The bandera M. */
	private boolean banderaM = Boolean.FALSE;

	/** The bandera C. */
	private boolean banderaC = Boolean.FALSE;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct EstadoCuentasAlMesMB ");

		cuenta = new Cuenta();
		orderBy = "FECPOL";
		bandera = Boolean.FALSE;

		if (this.getUserDetails().getIdSector() == SECTOR_MUNICIPAL) {
			banderaM = Boolean.TRUE;
		} else if (this.getUserDetails().getIdSector() == SECTOR_CENTRAL) {
			banderaM = Boolean.FALSE;
		}
	}

	/** The out. */
	Map<String, Object> out;

	/** The stream. */
	InputStream stream = null;

	/*
	 * (non-Javadoc)
	 *
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#downLoadFile()
	 */
	@Override
	public void downLoadFile() throws ReportValidationException {
		this.getCuenta().setIdsector(Long.valueOf(this.getUserDetails().getIdSector()));
		Cuenta account = null;
		if (null != cuenta.getCuenta()) {
			account = this.getCuentaRepository().findOne(CuentaPredicates.validateExistAccount(cuenta));
			if (null != account) {
				if (this.validatePolicyService.isOpenMonth(mes, null,
						this.getUserDetails().getIdSector()) == Boolean.TRUE) {
					if (this.orderBy.equals("FECPOL, MESPOL"))
						out = this.executePlService.callProcedure(PROCEDURE_NAME_POR_MES, this.getParametersReports());
					else
						out = this.executePlService.callProcedure(PROCEDURE_NAME, this.getParametersReports());

					if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
						try {
							stream = new FileInputStream(new File(
									out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString()));
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						fileTxt = new DefaultStreamedContent(stream, "application/txt",
								out.get("O_NAME_FILE").toString());
						generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!",
								out.get("O_MSG_ERROR").toString());
					} else {
						generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
								out.get("O_MSG_ERROR").toString());
					}
				}
			} else {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "La cuenta no existe");
			}
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"Favor de capturar o seleccionar una cuenta.");
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#getParametersReports()
	 */
	@Override
	public SqlParameterSource getParametersReports() throws ReportValidationException {
		MapSqlParameterSource parameter = new MapSqlParameterSource();

		parameter.addValue("i_mes", mes);
		parameter.addValue("i_cuenta", cuenta.getCuenta());
		parameter.addValue("i_scta", cuenta.getScuenta() != null ? cuenta.getScuenta() : "");
		parameter.addValue("i_sscta", cuenta.getSscuenta() != null ? cuenta.getSscuenta() : "");
		parameter.addValue("i_ssscta", cuenta.getSsscuenta() != null ? cuenta.getSsscuenta() : "");
		parameter.addValue("i_sssscta", cuenta.getSssscuenta() != null ? cuenta.getSssscuenta() : "");
		parameter.addValue("i_idsector", this.getUserDetails().getIdSector());
		parameter.addValue("i_order", orderBy);
		return parameter;
	}

	/**
	 * Pre view.
	 */
	public void preView() {
		File newFile;
		File target;
		String fileNameOut = "";

		// this.setbPreView(Boolean.TRUE);

		this.getCuenta().setIdsector(Long.valueOf(this.getUserDetails().getIdSector()));
		Cuenta account = null;
		if (null != cuenta.getCuenta()) {
			account = this.getCuentaRepository().findOne(CuentaPredicates.validateExistAccount(cuenta));
			if (null != account) {
				this.setbPreView(Boolean.TRUE);

				try {
					if (this.orderBy.equals("FECPOL, MESPOL"))
						out = this.executePlService.callProcedure(PROCEDURE_NAME_POR_MES, this.getParametersReports());
					else
						out = this.executePlService.callProcedure(PROCEDURE_NAME, this.getParametersReports());
				} catch (ReportValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (Integer.valueOf(out.get("O_COD_ERROR").toString()) == 0) {
					generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, out.get("O_MSG_ERROR").toString());
				} else {
					// txtPreview = this.generatePreviewService.generatePreview(
					// out.get("O_RUTA_FILE").toString() + "/" +
					// out.get("O_NAME_FILE").toString());

					fileNameOut = out.get("O_RUTA_FILE").toString() + UUID.randomUUID() + ".txt";
					newFile = new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString());
					target = new File(fileNameOut);

					try {
						ConvertCharsetUtils.transform(newFile, charSetUFT, target, charSetISO);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					txtPreview = this.generatePreviewService.generatePreview(fileNameOut);
					RequestContext.getCurrentInstance().execute(FOCUS_IN_PREVIEW);
				}
			} else {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "La cuenta no existe");
			}
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
					"Favor de capturar o seleccionar una cuenta.");
		}
	}

	public void downloadTxt() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			RequestContext.getCurrentInstance().execute(DOWNLOAD_TXT);
		}
	}

	public void viewPrev() {
		if (this.validatePolicyService.isOpenMonth(mes, null, this.getUserDetails().getIdSector()) == Boolean.TRUE) {
			this.preView();
		}
	}

	/**
	 * Gets the txt preview.
	 *
	 * @return the txt preview
	 */
	public StringBuilder getTxtPreview() {
		return txtPreview;
	}

	/**
	 * Sets the txt preview.
	 *
	 * @param txtPreview the new txt preview
	 */
	public void setTxtPreview(StringBuilder txtPreview) {
		this.txtPreview = txtPreview;
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
		return Strings.isNullOrEmpty(cuenta.getCuenta()) && Strings.isNullOrEmpty(cuenta.getScuenta())
				&& Strings.isNullOrEmpty(cuenta.getSscuenta()) && Strings.isNullOrEmpty(cuenta.getSscuenta())
				&& Strings.isNullOrEmpty(cuenta.getSscuenta());
	}

	/**
	 * Creates the file txt inline validate.
	 */
	public void createFileTxtInlineValidate() {
		if (!validateCuenta()) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"La cuenta no existe en pólizas");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			RequestContext.getCurrentInstance().execute("$(PrimeFaces.escapeClientId('form1:donwloadTxt')).click();");
		}
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
		if (!validateCuenta()) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, StringUtils.EMPTY,
					"La cuenta no existe");
			FacesContext.getCurrentInstance().addMessage(null, message);
			RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
		} else {
			// createFilePdfInline();
			RequestContext.getCurrentInstance().execute("PF('blockUIWidget').unblock();");
			RequestContext.getCurrentInstance()
					.execute("$(PrimeFaces.escapeClientId('form1:panelReporte')).css('height', '57em');");
		}
	}

	/**
	 * Valida cta.
	 *
	 * @param cuenta the cuenta
	 * @return the boolean
	 */
	public Boolean validaCta(String cuenta) {

		if (cuenta != "" && cuenta != null) {
			bandera = Boolean.TRUE;
		} else {
			bandera = Boolean.FALSE;
		}

		return bandera;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.gem.sistema.web.bean.GenericExecuteProcedure#getExecutePlService()
	 */
	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	/**
	 * Gets the file txt.
	 *
	 * @return the file txt
	 */
	public StreamedContent getFileTxt() {
		return fileTxt;
	}

	/**
	 * Sets the file txt.
	 *
	 * @param fileTxt the new file txt
	 */
	public void setFileTxt(StreamedContent fileTxt) {
		this.fileTxt = fileTxt;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.gem.sistema.web.bean.GenericExecuteProcedure#setExecutePlService(com.
	 * gem.sistema.business.service.callsp.ExecutePlService)
	 */
	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
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

	/**
	 * Gets the bandera.
	 *
	 * @return the bandera
	 */
	public Boolean getBandera() {
		return bandera;
	}

	/**
	 * Sets the bandera.
	 *
	 * @param bandera the new bandera
	 */
	public void setBandera(Boolean bandera) {
		this.bandera = bandera;
	}

	/**
	 * Gets the b pre view.
	 *
	 * @return the b pre view
	 */
	public Boolean getbPreView() {
		return bPreView;
	}

	/**
	 * Sets the b pre view.
	 *
	 * @param bPreView the new b pre view
	 */
	public void setbPreView(Boolean bPreView) {
		this.bPreView = bPreView;
	}

	/**
	 * Gets the generate preview service.
	 *
	 * @return the generate preview service
	 */
	public GeneratePreviewService getGeneratePreviewService() {
		return generatePreviewService;
	}

	/**
	 * Sets the generate preview service.
	 *
	 * @param generatePreviewService the new generate preview service
	 */
	public void setGeneratePreviewService(GeneratePreviewService generatePreviewService) {
		this.generatePreviewService = generatePreviewService;
	}

	/**
	 * Checks if is bandera M.
	 *
	 * @return true, if is bandera M
	 */
	public boolean isBanderaM() {
		return banderaM;
	}

	/**
	 * Sets the bandera M.
	 *
	 * @param banderaM the new bandera M
	 */
	public void setBanderaM(boolean banderaM) {
		this.banderaM = banderaM;
	}

	/**
	 * Checks if is bandera C.
	 *
	 * @return true, if is bandera C
	 */
	public boolean isBanderaC() {
		return banderaC;
	}

	/**
	 * Sets the bandera C.
	 *
	 * @param banderaC the new bandera C
	 */
	public void setBanderaC(boolean banderaC) {
		this.banderaC = banderaC;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}
}
