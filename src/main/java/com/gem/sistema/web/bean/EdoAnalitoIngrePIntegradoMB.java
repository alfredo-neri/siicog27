package com.gem.sistema.web.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.data.PageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Integradoi;
import com.gem.sistema.business.predicates.IntegradoiPredicates;
import com.gem.sistema.business.repository.catalogs.IntegradoiRepository;
import com.gem.sistema.web.datamodel.CuentaDataModel;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class EdoAnalitoIngrePIntegradoMB.
 */
@ManagedBean(name = "edoAnalitoIngrePIntegradoMB")
@ViewScoped
public class EdoAnalitoIngrePIntegradoMB extends AbstractMB implements Serializable {

	/**
	 * Serial default.
	 */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EdoAnalitoIngrePIntegradoMB.class);

	/** Elemento form1. */
	// @Value("${view.catalog.form1}")
	protected static final String VIEW_CATALOG_FORM1 = FrontProperty.getPropertyValue("view.catalog.form1");

	/** Elemento form1. */
	// @Value("${view.catalog.objects}")
	protected static final String VIEW_CATALOG_OBJECTS = FrontProperty.getPropertyValue("view.catalog.objects");

	/** The Constant ADD_LABEL. */
	private static final String ADD_LABEL = "Adicionar";

	/** The Constant UPD_LABEL. */
	private static final String UPD_LABEL = "Modificar";

	/** Habilitar el modo de edicion. */
	protected static final String VIEW_CATALOG_FORM1_OBJECTS = FrontProperty
			.getPropertyValue("view.catalog.form1.objects");

	/** Longitud del primer nivel de cuentas. */
	public static final int LENGTH_FIRST_LEVEL = 4;

	/** Longitud del segundo nivel de cuentas. */
	public static final int LENGTH_SECOND_LEVEL = 10;

	/** Longitud del tercer nivel de cuentas. */
	public static final int LENGTH_THIRD_LEVEL = 15;

	/** Longitud del cuarto nivel de cuentas. */
	public static final int LENGTH_FOUR_LEVEL = 4;

	/** Longitud del tercer nivel de cuentas. */
	public static final int LENGTH_FIVE_LEVEL = 3;

	/** The cuenta data model. */
	@ManagedProperty(value = "#{cuentaDataModel}")
	private CuentaDataModel cuentaDataModel;

	/** The integradoi repository. */
	@ManagedProperty(value = "#{integradoiRepository}")
	private IntegradoiRepository integradoiRepository;

	/** The bean find. */
	private Cuenta beanFind;

	/** The row selected. */
	private Cuenta rowSelected;

	/** The integradoi selected. */
	private Integradoi integradoiSelected = new Integradoi();

	/** The edit. */
	private Boolean edit = Boolean.TRUE;

	/** The enable add. */
	private Boolean enableAdd = Boolean.TRUE;

	/** The label add. */
	private String labelAdd = ADD_LABEL;

	/**
	 * Gets the bean find.
	 *
	 * @return the bean find
	 */
	public Cuenta getBeanFind() {
		return beanFind;
	}

	/**
	 * Sets the bean find.
	 *
	 * @param beanFind the new bean find
	 */
	public void setBeanFind(Cuenta beanFind) {
		this.beanFind = beanFind;
	}

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct catalogAccountsMB ");
		Cuenta bean2find = new Cuenta();
		bean2find.setCuenta("8150");
		bean2find.setIdsector(Long.valueOf(this.getUserDetails().getIdSector()));
		this.cuentaDataModel.setBeanFind(bean2find);
		this.integradoiSelected = new Integradoi();

	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogAccountsMB  ");
		getBeanFind().setIdsector(Long.valueOf(this.getUserDetails().getIdSector()));
	}

	/**
	 * Gets the row selected.
	 *
	 * @return the rowSelected
	 */
	public Cuenta getRowSelected() {
		return rowSelected;
	}

	/**
	 * Sets the row selected.
	 *
	 * @param rowSelected            the rowSelected to set
	 */
	public void setRowSelected(final Cuenta rowSelected) {
		this.rowSelected = rowSelected;
	}

	/**
	 * Gets the cuenta data model.
	 *
	 * @return the cuenta data model
	 */
	public CuentaDataModel getCuentaDataModel() {
		return cuentaDataModel;
	}

	/**
	 * Sets the cuenta data model.
	 *
	 * @param cuentaDataModel the new cuenta data model
	 */
	public void setCuentaDataModel(CuentaDataModel cuentaDataModel) {
		Cuenta bean2find = new Cuenta();
		bean2find.setCuenta("8150");
		bean2find.setIdsector(Long.valueOf(this.getUserDetails().getIdSector()));
		cuentaDataModel.setBeanFind(bean2find);
		this.cuentaDataModel = cuentaDataModel;
	}

	/**
	 * Update.
	 *
	 * @param event the event
	 */
	public void update(PageEvent event) {
		// int var = event.getPage();
		this.integradoiSelected = null;
		this.reset();
	}

	/**
	 * On row select.
	 *
	 * @param selectEvent the select event
	 */
	public void onRowSelect(SelectEvent selectEvent) {
		Cuenta selectedCuenta = (Cuenta) selectEvent.getObject();
		if (this.getEdit() == Boolean.FALSE && this.enableAdd == Boolean.TRUE) {
			this.changeEdit(Boolean.FALSE);
			this.enableAdd = Boolean.FALSE;
		}
		this.doChangeCuenta(selectedCuenta);
	}

	/**
	 * Do change cuenta.
	 *
	 * @param cuenta the cuenta
	 */
	private void doChangeCuenta(Cuenta cuenta) {
		if (null != cuenta) {
			this.rowSelected = cuenta;
			this.integradoiSelected = this.integradoiRepository
					.findOne(IntegradoiPredicates.findByAccounCompositeInlcudeEmpty(cuenta));
			if (null != this.integradoiSelected) {
				this.labelAdd = UPD_LABEL;
			} else {
				this.integradoiSelected = new Integradoi();
				this.labelAdd = ADD_LABEL;
			}
			this.enableAdd = Boolean.FALSE;
		}
	}

	/**
	 * Adds the.
	 */
	public void add() {
		this.changeEdit(Boolean.TRUE);
		this.enableAdd = Boolean.TRUE;

	}

	/**
	 * Save.
	 */
	public void save() {
		this.integradoiSelected.setCuenta(rowSelected.getCuenta());
		this.integradoiSelected.setScta(rowSelected.getScuenta());
		this.integradoiSelected.setSscta(rowSelected.getSscuenta());
		this.integradoiSelected.setSsscta(rowSelected.getSsscuenta());
		this.integradoiSelected.setSssscta(rowSelected.getSssscuenta());
		this.integradoiSelected.setUserid(this.getUserDetails().getUsername());
		this.integradoiSelected.setIdsector(this.getUserDetails().getIdSector());
		this.integradoiSelected.setIdRef(0l);
		this.integradoiSelected = this.setValueNull(integradoiSelected);
		this.integradoiSelected = this.integradoiRepository.save(this.integradoiSelected);

		if (null != this.integradoiSelected) {
			this.labelAdd = UPD_LABEL;
		} else {
			this.integradoiSelected = new Integradoi();
			this.labelAdd = ADD_LABEL;
		}
		this.enableAdd = Boolean.FALSE;
		this.setEdit(Boolean.TRUE);

		FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY, "Registro editado");
		FacesContext.getCurrentInstance().addMessage("", errorMsg);
	}

	/**
	 * Reset.
	 */
	public void reset() {
		if (null != this.rowSelected) {
			this.integradoiSelected = this.integradoiRepository
					.findOne(IntegradoiPredicates.findByAccounCompositeInlcudeEmpty(this.rowSelected));
			if (null == this.integradoiSelected) {
				this.integradoiSelected = new Integradoi();
			}
		}
	}

	/**
	 * Cancel.
	 */
	public void cancel() {
		this.changeEdit(Boolean.FALSE);
		this.enableAdd = Boolean.FALSE;
		this.reset();
	}

	/**
	 * Change edit.
	 *
	 * @param active the active
	 */
	private void changeEdit(Boolean active) {
		this.edit = !active;
	}

	/**
	 * Gets the integradoi selected.
	 *
	 * @return the integradoi selected
	 */
	public Integradoi getIntegradoiSelected() {
		return integradoiSelected;
	}

	/**
	 * Sets the integradoi selected.
	 *
	 * @param integradoiSelected the new integradoi selected
	 */
	public void setIntegradoiSelected(Integradoi integradoiSelected) {
		this.integradoiSelected = integradoiSelected;
	}

	/**
	 * Gets the edits the.
	 *
	 * @return the edits the
	 */
	public Boolean getEdit() {
		return edit;
	}

	/**
	 * Sets the edits the.
	 *
	 * @param edit the new edits the
	 */
	public void setEdit(Boolean edit) {
		this.edit = edit;
	}

	/**
	 * Gets the label add.
	 *
	 * @return the label add
	 */
	public String getLabelAdd() {
		return labelAdd;
	}

	/**
	 * Sets the label add.
	 *
	 * @param labelAdd the new label add
	 */
	public void setLabelAdd(String labelAdd) {
		this.labelAdd = labelAdd;
	}

	/**
	 * Gets the integradoi repository.
	 *
	 * @return the integradoi repository
	 */
	public IntegradoiRepository getIntegradoiRepository() {
		return integradoiRepository;
	}

	/**
	 * Sets the integradoi repository.
	 *
	 * @param integradoiRepository the new integradoi repository
	 */
	public void setIntegradoiRepository(IntegradoiRepository integradoiRepository) {
		this.integradoiRepository = integradoiRepository;
	}

	/**
	 * Gets the enable add.
	 *
	 * @return the enable add
	 */
	public Boolean getEnableAdd() {
		return enableAdd;
	}

	/**
	 * Sets the enable add.
	 *
	 * @param enableAdd the new enable add
	 */
	public void setEnableAdd(Boolean enableAdd) {
		this.enableAdd = enableAdd;
	}

	/**
	 * Sets the value null.
	 *
	 * @param integradoi the integradoi
	 * @return the integradoi
	 */
	public Integradoi setValueNull(Integradoi integradoi) {
		Integradoi integrado = new Integradoi();
		integrado = integradoi;
		if (null == integrado.getAguaabonos1()) {
			integrado.setAguaabonos1(BigDecimal.ZERO);
		}
		if (null == integrado.getAguaabonos2()) {
			integrado.setAguaabonos2(BigDecimal.ZERO);
		}

		if (null == integrado.getAguaabonos3()) {
			integrado.setAguaabonos3(BigDecimal.ZERO);
		}

		if (null == integrado.getAguaabonos4()) {
			integrado.setAguaabonos4(BigDecimal.ZERO);
		}
		if (null == integrado.getAguaabonos5()) {
			integrado.setAguaabonos5(BigDecimal.ZERO);
		}

		if (null == integrado.getAguaabonos6()) {
			integrado.setAguaabonos6(BigDecimal.ZERO);
		}

		if (null == integrado.getAguaabonos7()) {
			integrado.setAguaabonos7(BigDecimal.ZERO);
		}
		if (null == integrado.getAguaabonos8()) {
			integrado.setAguaabonos8(BigDecimal.ZERO);
		}

		if (null == integrado.getAguaabonos9()) {
			integrado.setAguaabonos9(BigDecimal.ZERO);
		}

		if (null == integrado.getAguaabonos10()) {
			integrado.setAguaabonos10(BigDecimal.ZERO);
		}
		if (null == integrado.getAguaabonos11()) {
			integrado.setAguaabonos11(BigDecimal.ZERO);
		}

		if (null == integrado.getAguaabonos12()) {
			integrado.setAguaabonos12(BigDecimal.ZERO);
		}

		if (null == integrado.getAguaabonos13()) {
			integrado.setAguaabonos13(BigDecimal.ZERO);
		}

		if (null == integrado.getAguacargos1()) {
			integrado.setAguacargos1(BigDecimal.ZERO);
		}
		if (null == integrado.getAguacargos2()) {
			integrado.setAguacargos2(BigDecimal.ZERO);
		}
		if (null == integrado.getAguacargos3()) {
			integrado.setAguacargos3(BigDecimal.ZERO);
		}
		if (null == integrado.getAguacargos4()) {
			integrado.setAguacargos4(BigDecimal.ZERO);
		}
		if (null == integrado.getAguacargos5()) {
			integrado.setAguacargos5(BigDecimal.ZERO);
		}
		if (null == integrado.getAguacargos6()) {
			integrado.setAguacargos6(BigDecimal.ZERO);
		}
		if (null == integrado.getAguacargos7()) {
			integrado.setAguacargos7(BigDecimal.ZERO);
		}
		if (null == integrado.getAguacargos8()) {
			integrado.setAguacargos8(BigDecimal.ZERO);
		}
		if (null == integrado.getAguacargos9()) {
			integrado.setAguacargos9(BigDecimal.ZERO);
		}
		if (null == integrado.getAguacargos10()) {
			integrado.setAguacargos10(BigDecimal.ZERO);
		}
		if (null == integrado.getAguacargos11()) {
			integrado.setAguacargos11(BigDecimal.ZERO);
		}
		if (null == integrado.getAguacargos12()) {
			integrado.setAguacargos12(BigDecimal.ZERO);
		}
		if (null == integrado.getAguacargos13()) {
			integrado.setAguacargos13(BigDecimal.ZERO);
		}

		if (null == integrado.getDifabonos1()) {
			integrado.setDifabonos1(BigDecimal.ZERO);
		}

		if (null == integrado.getDifabonos2()) {
			integrado.setDifabonos2(BigDecimal.ZERO);
		}

		if (null == integrado.getDifabonos3()) {
			integrado.setDifabonos3(BigDecimal.ZERO);
		}

		if (null == integrado.getDifabonos4()) {
			integrado.setDifabonos4(BigDecimal.ZERO);
		}

		if (null == integrado.getDifabonos5()) {
			integrado.setDifabonos5(BigDecimal.ZERO);
		}

		if (null == integrado.getDifabonos6()) {
			integrado.setDifabonos6(BigDecimal.ZERO);
		}

		if (null == integrado.getDifabonos7()) {
			integrado.setDifabonos7(BigDecimal.ZERO);
		}

		if (null == integrado.getDifabonos8()) {
			integrado.setDifabonos8(BigDecimal.ZERO);
		}

		if (null == integrado.getDifabonos9()) {
			integrado.setDifabonos9(BigDecimal.ZERO);
		}

		if (null == integrado.getDifabonos10()) {
			integrado.setDifabonos10(BigDecimal.ZERO);
		}

		if (null == integrado.getDifabonos11()) {
			integrado.setDifabonos11(BigDecimal.ZERO);
		}

		if (null == integrado.getDifabonos12()) {
			integrado.setDifabonos12(BigDecimal.ZERO);
		}

		if (null == integrado.getDifabonos13()) {
			integrado.setDifabonos13(BigDecimal.ZERO);
		}

		if (null == integrado.getDifcargos1()) {
			integrado.setDifcargos1(BigDecimal.ZERO);
		}

		if (null == integrado.getDifcargos2()) {
			integrado.setDifcargos2(BigDecimal.ZERO);
		}

		if (null == integrado.getDifcargos3()) {
			integrado.setDifcargos3(BigDecimal.ZERO);
		}

		if (null == integrado.getDifcargos4()) {
			integrado.setDifcargos4(BigDecimal.ZERO);
		}

		if (null == integrado.getDifcargos5()) {
			integrado.setDifcargos5(BigDecimal.ZERO);
		}
		if (null == integrado.getDifcargos6()) {
			integrado.setDifcargos6(BigDecimal.ZERO);
		}
		if (null == integrado.getDifcargos7()) {
			integrado.setDifcargos7(BigDecimal.ZERO);
		}
		if (null == integrado.getDifcargos8()) {
			integrado.setDifcargos8(BigDecimal.ZERO);
		}
		if (null == integrado.getDifcargos9()) {
			integrado.setDifcargos9(BigDecimal.ZERO);
		}
		if (null == integrado.getDifcargos10()) {
			integrado.setDifcargos10(BigDecimal.ZERO);
		}
		if (null == integrado.getDifcargos11()) {
			integrado.setDifcargos11(BigDecimal.ZERO);
		}
		if (null == integrado.getDifcargos12()) {
			integrado.setDifcargos12(BigDecimal.ZERO);
		}
		if (null == integrado.getDifcargos13()) {
			integrado.setDifcargos13(BigDecimal.ZERO);
		}

		if (null == integrado.getDeporteabonos1()) {
			integrado.setDeporteabonos1(BigDecimal.ZERO);
		}

		if (null == integrado.getDeporteabonos2()) {
			integrado.setDeporteabonos2(BigDecimal.ZERO);
		}

		if (null == integrado.getDeporteabonos3()) {
			integrado.setDeporteabonos3(BigDecimal.ZERO);
		}

		if (null == integrado.getDeporteabonos4()) {
			integrado.setDeporteabonos4(BigDecimal.ZERO);
		}
		if (null == integrado.getDeporteabonos5()) {
			integrado.setDeporteabonos5(BigDecimal.ZERO);
		}
		if (null == integrado.getDeporteabonos6()) {
			integrado.setDeporteabonos6(BigDecimal.ZERO);
		}
		if (null == integrado.getDeporteabonos7()) {
			integrado.setDeporteabonos7(BigDecimal.ZERO);
		}
		if (null == integrado.getDeporteabonos8()) {
			integrado.setDeporteabonos8(BigDecimal.ZERO);
		}
		if (null == integrado.getDeporteabonos9()) {
			integrado.setDeporteabonos9(BigDecimal.ZERO);
		}
		if (null == integrado.getDeporteabonos10()) {
			integrado.setDeporteabonos10(BigDecimal.ZERO);
		}
		if (null == integrado.getDeporteabonos11()) {
			integrado.setDeporteabonos11(BigDecimal.ZERO);
		}
		if (null == integrado.getDeporteabonos12()) {
			integrado.setDeporteabonos12(BigDecimal.ZERO);
		}
		if (null == integrado.getDeporteabonos13()) {
			integrado.setDeporteabonos13(BigDecimal.ZERO);
		}

		if (null == integrado.getDeportecargos1()) {
			integrado.setDeportecargos1(BigDecimal.ZERO);
		}
		if (null == integrado.getDeportecargos2()) {
			integrado.setDeportecargos2(BigDecimal.ZERO);
		}
		if (null == integrado.getDeportecargos3()) {
			integrado.setDeportecargos3(BigDecimal.ZERO);
		}
		if (null == integrado.getDeportecargos4()) {
			integrado.setDeportecargos4(BigDecimal.ZERO);
		}
		if (null == integrado.getDeportecargos5()) {
			integrado.setDeportecargos5(BigDecimal.ZERO);
		}
		if (null == integrado.getDeportecargos6()) {
			integrado.setDeportecargos6(BigDecimal.ZERO);
		}
		if (null == integrado.getDeportecargos7()) {
			integrado.setDeportecargos7(BigDecimal.ZERO);
		}
		if (null == integrado.getDeportecargos8()) {
			integrado.setDeportecargos8(BigDecimal.ZERO);
		}
		if (null == integrado.getDeportecargos9()) {
			integrado.setDeportecargos9(BigDecimal.ZERO);
		}
		if (null == integrado.getDeportecargos10()) {
			integrado.setDeportecargos10(BigDecimal.ZERO);
		}
		if (null == integrado.getDeportecargos11()) {
			integrado.setDeportecargos11(BigDecimal.ZERO);
		}
		if (null == integrado.getDeportecargos12()) {
			integrado.setDeportecargos12(BigDecimal.ZERO);
		}

		if (null == integrado.getDeportecargos13()) {
			integrado.setDeportecargos13(BigDecimal.ZERO);
		}

		if (null == integrado.getAyttoabonos1()) {
			integrado.setAyttoabonos1(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttoabonos2()) {
			integrado.setAyttoabonos2(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttoabonos3()) {
			integrado.setAyttoabonos3(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttoabonos4()) {
			integrado.setAyttoabonos4(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttoabonos5()) {
			integrado.setAyttoabonos5(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttoabonos6()) {
			integrado.setAyttoabonos6(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttoabonos7()) {
			integrado.setAyttoabonos7(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttoabonos8()) {
			integrado.setAyttoabonos8(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttoabonos9()) {
			integrado.setAyttoabonos9(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttoabonos10()) {
			integrado.setAyttoabonos10(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttoabonos11()) {
			integrado.setAyttoabonos11(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttoabonos12()) {
			integrado.setAyttoabonos12(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttoabonos13()) {
			integrado.setAyttoabonos13(BigDecimal.ZERO);
		}

		if (null == integrado.getAyttocargos1()) {
			integrado.setAyttocargos1(BigDecimal.ZERO);
		}

		if (null == integrado.getAyttocargos2()) {
			integrado.setAyttocargos2(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttocargos3()) {
			integrado.setAyttocargos3(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttocargos4()) {
			integrado.setAyttocargos4(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttocargos5()) {
			integrado.setAyttocargos5(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttocargos6()) {
			integrado.setAyttocargos6(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttocargos7()) {
			integrado.setAyttocargos7(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttocargos8()) {
			integrado.setAyttocargos8(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttocargos9()) {
			integrado.setAyttocargos9(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttocargos10()) {
			integrado.setAyttocargos10(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttocargos11()) {
			integrado.setAyttocargos11(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttocargos12()) {
			integrado.setAyttocargos12(BigDecimal.ZERO);
		}
		if (null == integrado.getAyttocargos13()) {
			integrado.setAyttocargos13(BigDecimal.ZERO);
		}

		if (null == integrado.getMaviciabonos1()) {
			integrado.setMaviciabonos1(BigDecimal.ZERO);
		}

		if (null == integrado.getMaviciabonos2()) {
			integrado.setMaviciabonos2(BigDecimal.ZERO);
		}

		if (null == integrado.getMaviciabonos3()) {
			integrado.setMaviciabonos3(BigDecimal.ZERO);
		}
		if (null == integrado.getMaviciabonos4()) {
			integrado.setMaviciabonos4(BigDecimal.ZERO);
		}
		if (null == integrado.getMaviciabonos5()) {
			integrado.setMaviciabonos5(BigDecimal.ZERO);
		}
		if (null == integrado.getMaviciabonos6()) {
			integrado.setMaviciabonos6(BigDecimal.ZERO);
		}
		if (null == integrado.getMaviciabonos7()) {
			integrado.setMaviciabonos7(BigDecimal.ZERO);
		}
		if (null == integrado.getMaviciabonos8()) {
			integrado.setMaviciabonos8(BigDecimal.ZERO);
		}
		if (null == integrado.getMaviciabonos9()) {
			integrado.setMaviciabonos9(BigDecimal.ZERO);
		}
		if (null == integrado.getMaviciabonos10()) {
			integrado.setMaviciabonos10(BigDecimal.ZERO);
		}
		if (null == integrado.getMaviciabonos11()) {
			integrado.setMaviciabonos11(BigDecimal.ZERO);
		}
		if (null == integrado.getMaviciabonos12()) {
			integrado.setMaviciabonos12(BigDecimal.ZERO);
		}
		if (null == integrado.getMaviciabonos13()) {
			integrado.setMaviciabonos13(BigDecimal.ZERO);
		}

		if (null == integrado.getMavicicargos1()) {
			integrado.setMavicicargos1(BigDecimal.ZERO);
		}

		if (null == integrado.getMavicicargos2()) {
			integrado.setMavicicargos2(BigDecimal.ZERO);
		}

		if (null == integrado.getMavicicargos3()) {
			integrado.setMavicicargos3(BigDecimal.ZERO);
		}
		if (null == integrado.getMavicicargos4()) {
			integrado.setMavicicargos4(BigDecimal.ZERO);
		}
		if (null == integrado.getMavicicargos5()) {
			integrado.setMavicicargos5(BigDecimal.ZERO);
		}
		if (null == integrado.getMavicicargos6()) {
			integrado.setMavicicargos6(BigDecimal.ZERO);
		}
		if (null == integrado.getMavicicargos7()) {
			integrado.setMavicicargos7(BigDecimal.ZERO);
		}
		if (null == integrado.getMavicicargos8()) {
			integrado.setMavicicargos8(BigDecimal.ZERO);
		}
		if (null == integrado.getMavicicargos9()) {
			integrado.setMavicicargos9(BigDecimal.ZERO);
		}
		if (null == integrado.getMavicicargos10()) {
			integrado.setMavicicargos10(BigDecimal.ZERO);
		}
		if (null == integrado.getMavicicargos11()) {
			integrado.setMavicicargos11(BigDecimal.ZERO);
		}
		if (null == integrado.getMavicicargos12()) {
			integrado.setMavicicargos12(BigDecimal.ZERO);
		}
		if (null == integrado.getMavicicargos13()) {
			integrado.setMavicicargos13(BigDecimal.ZERO);
		}

		return integrado;
	}

}
