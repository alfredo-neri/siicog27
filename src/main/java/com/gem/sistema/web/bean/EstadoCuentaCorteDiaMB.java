package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.predicates.CuentaPredicates;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.service.catalogos.GeneratePreviewService;
import com.gem.sistema.business.service.catalogos.ValidatePolicyService;
import com.gem.sistema.business.service.reportador.ReportValidationException;
import com.gem.sistema.business.utils.ConvertCharsetUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class EstadoCuentaCorteDiaMB.
 */
@ManagedBean
@ViewScoped
public class EstadoCuentaCorteDiaMB extends GenericExecuteProcedure {

	/** The Constant charSetISO. */
	private final static String charSetISO = "ISO-8859-1";

	/** The Constant charSetUFT. */
	private final static String charSetUFT = "utf-8";

	/** The Constant FOCUS_BY_ROWID. */
	private static final String FOCUS_BY_ROWID = "PrimeFaces.focus('form1:preViewTxt');";

	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = "SP_GENERA_TXT_CUENTAPORDIA";

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

	/** The file txt. */
	private StreamedContent fileTxt;

	/** The txt preview. */
	private StringBuilder txtPreview;

	/** The b pre view. */
	private Boolean bPreView = Boolean.FALSE;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct EstadoCuentaCorteDiaMB ");
		cuenta = new Cuenta();
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
		addToBitacora(true);
		this.getCuenta().setIdsector(Long.valueOf(this.getUserDetails().getIdSector()));
		Cuenta account = null;
		if (null != cuenta.getCuenta()) {
			account = this.getCuentaRepository().findOne(CuentaPredicates.validateExistAccount(cuenta));
			if (null != account) {
				if (this.validatePolicyService.isOpenMonth(mes, null,
						this.getUserDetails().getIdSector()) == Boolean.TRUE) {
					out = executePlService.callProcedure(PROCEDURE_NAME, this.getParametersReports());
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
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Favor de capturar una cuenta.");
		}
		addToBitacora(false);
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
		parameter.addValue("i_order", "FECPOL");

		return parameter;
	}

	/** The input file. */
	InputStream inputFile = null;

	/** The input file 2. */
	InputStream inputFile2 = null;

	/** The new file. */
	File newFile = null;

	/** The target. */
	File target = null;

	/** The file name out. */
	String fileNameOut = "";

	/**
	 * Pre view.
	 */
	public void preView() {
		addToBitacora(true);
		this.getCuenta().setIdsector(Long.valueOf(this.getUserDetails().getIdSector()));
		Cuenta account = null;
		if (null != cuenta.getCuenta()) {
			account = this.getCuentaRepository().findOne(CuentaPredicates.validateExistAccount(cuenta));
			if (null != account) {
				this.setbPreView(Boolean.TRUE);

				try {
					out = this.executePlService.callProcedure(PROCEDURE_NAME, this.getParametersReports());
				} catch (ReportValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (Integer.valueOf(out.get("O_COD_ERROR").toString()) == 0) {
					generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, out.get("O_MSG_ERROR").toString());
				} else {
					fileNameOut = "/gem/reportes/" + UUID.randomUUID() + ".txt";
					newFile = new File(out.get("O_RUTA_FILE").toString() + "/" + out.get("O_NAME_FILE").toString());
					target = new File(fileNameOut);
					try {
						ConvertCharsetUtils.transform(newFile, charSetUFT, target, charSetISO);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					txtPreview = this.generatePreviewService.generatePreview(fileNameOut);
					RequestContext.getCurrentInstance().execute(FOCUS_BY_ROWID);
				}
			} else {
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "La cuenta no existe");
			}
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!", "Favor de capturar una cuenta.");
		}
		addToBitacora(false);
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
	 * Gets the charsetiso.
	 *
	 * @return the charsetiso
	 */
	public static String getCharsetiso() {
		return charSetISO;
	}

	/**
	 * Gets the charsetuft.
	 *
	 * @return the charsetuft
	 */
	public static String getCharsetuft() {
		return charSetUFT;
	}

	/**
	 * Gets the focus by rowid.
	 *
	 * @return the focus by rowid
	 */
	public static String getFocusByRowid() {
		return FOCUS_BY_ROWID;
	}

	public ValidatePolicyService getValidatePolicyService() {
		return validatePolicyService;
	}

	public void setValidatePolicyService(ValidatePolicyService validatePolicyService) {
		this.validatePolicyService = validatePolicyService;
	}

}
