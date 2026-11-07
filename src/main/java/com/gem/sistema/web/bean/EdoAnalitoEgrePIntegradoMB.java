package com.gem.sistema.web.bean;

import java.io.Serializable;

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

import com.gem.sistema.business.domain.Integradoe;
import com.gem.sistema.business.domain.Natgas;
import com.gem.sistema.business.predicates.IntegradoePredicates;
import com.gem.sistema.business.repository.catalogs.IntegradoeRepository;
import com.gem.sistema.web.datamodel.NatgasCustomDataModel;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class EdoAnalitoEgrePIntegradoMB.
 */
@ManagedBean(name = "edoAnalitoEgrePIntegradoMB")
@ViewScoped
public class EdoAnalitoEgrePIntegradoMB extends AbstractMB implements Serializable {

	/**
	 * Serial default.
	 */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EdoAnalitoEgrePIntegradoMB.class);

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

	/** The natgas custom data model. */
	@ManagedProperty(value = "#{natgasCustomDataModel}")
	private NatgasCustomDataModel natgasCustomDataModel;

	/** The integradoe repository. */
	@ManagedProperty(value = "#{integradoeRepository}")
	private IntegradoeRepository integradoeRepository;

	/** The bean find. */
	private Natgas beanFind;

	/** The row selected. */
	private Natgas rowSelected;

	/** The integradoe selected. */
	private Integradoe integradoeSelected;

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
	public Natgas getBeanFind() {
		return beanFind;
	}

	/**
	 * Sets the bean find.
	 *
	 * @param beanFind the new bean find
	 */
	public void setBeanFind(Natgas beanFind) {
		this.beanFind = beanFind;
	}

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct catalogAccountsMB ");
		Natgas cuenta2find = new Natgas();
		cuenta2find.setIdsector(this.getUserDetails().getIdSector());
		setBeanFind(cuenta2find);
		this.natgasCustomDataModel.setBeanFind(cuenta2find);
		this.integradoeSelected = new Integradoe();
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogAccountsMB  ");
		getBeanFind().setIdsector(this.getUserDetails().getIdSector());
	}

	/**
	 * Gets the row selected.
	 *
	 * @return the rowSelected
	 */
	public Natgas getRowSelected() {
		return rowSelected;
	}

	/**
	 * Sets the row selected.
	 *
	 * @param rowSelected            the rowSelected to set
	 */
	public void setRowSelected(final Natgas rowSelected) {
		this.rowSelected = rowSelected;
	}

	/**
	 * Gets the natgas custom data model.
	 *
	 * @return the cuentaDataModel
	 */
	public NatgasCustomDataModel getNatgasCustomDataModel() {
		return natgasCustomDataModel;
	}

	/**
	 * Sets the natgas custom data model.
	 *
	 * @param cuentaDataModel            the cuentaDataModel to set
	 */
	public void setNatgasCustomDataModel(NatgasCustomDataModel cuentaDataModel) {
		Natgas bean2find = new Natgas();
		bean2find.setIdsector(this.getUserDetails().getIdSector());
		cuentaDataModel.setBeanFind(bean2find);
		;
		this.natgasCustomDataModel = cuentaDataModel;
	}

	/**
	 * Update.
	 *
	 * @param event the event
	 */
	public void update(PageEvent event) {
		// int var = event.getPage();
		this.integradoeSelected = null;
		this.reset();
	}

	/**
	 * On row select.
	 *
	 * @param selectEvent the select event
	 */
	public void onRowSelect(SelectEvent selectEvent) {
		Natgas selectedNatgas = (Natgas) selectEvent.getObject();
		if (this.getEdit() == Boolean.FALSE && this.enableAdd == Boolean.TRUE) {
			this.changeEdit(Boolean.FALSE);
			this.enableAdd = Boolean.FALSE;
		}
		this.doChangeNatgas(selectedNatgas);
	}

	/**
	 * Do change natgas.
	 *
	 * @param natgas the natgas
	 */
	private void doChangeNatgas(Natgas natgas) {
		if (null != natgas) {
			this.rowSelected = natgas;
			this.integradoeSelected = this.integradoeRepository
					.findOne(IntegradoePredicates.findByPartida(natgas.getClvgas()));
			if (null != this.integradoeSelected) {
				this.labelAdd = UPD_LABEL;
			} else {
				this.integradoeSelected = new Integradoe();
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

		this.getIntegradoeSelected().setUserid(this.getUserDetails().getUsername());
		this.getIntegradoeSelected().setIdsector(this.getUserDetails().getIdSector());
		this.getIntegradoeSelected().setIdRef(0l);
		this.getIntegradoeSelected().setPartida(rowSelected.getClvgas());

		this.integradoeSelected = this.integradoeRepository.save(this.integradoeSelected);
		this.labelAdd = UPD_LABEL;
		this.edit = Boolean.TRUE;
		this.enableAdd = Boolean.FALSE;
		FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, StringUtils.EMPTY, "Registro editado");
		FacesContext.getCurrentInstance().addMessage("", errorMsg);
	}

	/**
	 * Reset.
	 */
	public void reset() {
		if (null != this.rowSelected) {
			this.integradoeSelected = this.integradoeRepository
					.findOne(IntegradoePredicates.findByPartida(this.rowSelected.getClvgas()));
			if (null == this.integradoeSelected) {
				this.integradoeSelected = new Integradoe();
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
	 * Gets the integradoe selected.
	 *
	 * @return the integradoe selected
	 */
	public Integradoe getIntegradoeSelected() {
		return integradoeSelected;
	}

	/**
	 * Sets the integradoe selected.
	 *
	 * @param integradoeSelected the new integradoe selected
	 */
	public void setIntegradoeSelected(Integradoe integradoeSelected) {
		this.integradoeSelected = integradoeSelected;
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
	 * Gets the integradoe repository.
	 *
	 * @return the integradoe repository
	 */
	public IntegradoeRepository getIntegradoeRepository() {
		return integradoeRepository;
	}

	/**
	 * Sets the integradoe repository.
	 *
	 * @param integradoeRepository the new integradoe repository
	 */
	public void setIntegradoeRepository(IntegradoeRepository integradoeRepository) {
		this.integradoeRepository = integradoeRepository;
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

}
