package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.domain.AbstractEntity;
import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.Tsueldo;
import com.gem.sistema.business.predicates.SueldosPredicate;
import com.gem.sistema.business.repository.catalogs.SueldoRepository;
import com.gem.sistema.util.Constants;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class SalaryScaleManualMB.
 */
@Component(value = "salaryScaleManualMB")
@SessionScoped
public class SalaryScaleManualMB extends CommonCatalogMB<Tsueldo> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SalaryScaleManualMB.class);
	
	/** The b edit. */
	private boolean bEdit = Boolean.FALSE;

	/** Repositorio de Tsueldo. */
	@Autowired
	private SueldoRepository sueldoRepositorio;
	


	/** Registro seleccionado. */
	private Tsueldo rowSelected;

	/** Campo requerido clvdep. */
	// @Value("${catalog.message.field.required.clvdep}")
	private static final String FIELD_REQUIRED_CLVDEP = FrontProperty
			.getPropertyValue("catalog.message.field.required.clvdep");
	
	

	/**
	 * Checks if is b edit.
	 *
	 * @return true, if is b edit
	 */
	public boolean isbEdit() {
		return bEdit;
	}

	/**
	 * Sets the b edit.
	 *
	 * @param bEdit the new b edit
	 */
	public void setbEdit(boolean bEdit) {
		this.bEdit = bEdit;
	}

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct salaryScaleManualMB " + this + "::" + getBeanFind() + "::" + getList());
		setBeanFind(new Tsueldo());
		setList(new ArrayList<Tsueldo>());
		setListNew(new ArrayList<Tsueldo>());
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina salaryScaleManualMB  ");
		getBeanFind().setCvepuesto(null);
		getBeanFind().setNompuesto(null);
		restartData();
		this.setList(this.getList());
	}
	
	/**
	 * Muestra un mensaje al cancelar la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowCancel(final RowEditEvent event) {
		LOGGER.info(":: Cancelar edicion ");
		this.consultList();
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_EDITION_CANCELED);
	}

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {
		final Tsueldo catalog = (Tsueldo) event.getObject();
		LOGGER.info(":: Editando Fila salaryScaleManualMB  " + rowSelected.getIndex() + "::" + catalog);
		final StringBuilder errorMsg = new StringBuilder();

		if (isValidCapturedData(catalog, errorMsg) && isValidSaveOrUpdate(catalog, errorMsg)) {
			catalog.setCapturo(this.getUserDetails().getUsername());
			catalog.setFeccap(Calendar.getInstance().getTime());
			catalog.setIdRef(0l);
			catalog.setIdUser(this.getUserDetails().getUsername());
			catalog.setSectorId(this.getUserDetails().getIdSector());
			executeOperationSaveOrUpdate(catalog, sueldoRepositorio);
			this.consultList();
			RequestContext.getCurrentInstance().execute("jQuery('#form1\\\\:hideButton3').click();");
		} else {
			if (errorMsg.length() == ZERO) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_UPDATE_UNIQUE
						.concat(FIELD_REQUIRED_CLVDEP).concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
			} else {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, errorMsg.toString());
			}
			activateRowEdit(catalog.getIndex());
		}
	}
	
	/**
	 * On init row edit.
	 *
	 * @param event the event
	 */
	public void onInitRowEdit(final RowEditEvent event) {
		if (null != event.getObject()) {
			final Tsueldo catalog = (Tsueldo) event.getObject();
			if (null != catalog.getId() && catalog.getId() != 0) {
				this.setbEdit(Boolean.TRUE);
			} else {
				this.setbEdit(Boolean.FALSE);
			}
		}
	}

	/**
	 * Properties not null.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 */
	boolean propertiesNotNull(AbstractEntity entity) {
		boolean toReturn = Boolean.TRUE;
		try {
			Map<String, Object> describedMap = PropertyUtils.describe(entity);

			Set<Entry<String, Object>> entrySet = describedMap.entrySet();
			for (Entry<String, Object> entry : entrySet) {
				if (null == entry.getValue()) {
					toReturn = Boolean.FALSE;
					break;
				} else {
					if (entry.getValue() instanceof String && StringUtils.isEmpty(entry.getValue().toString())) {
						toReturn = Boolean.FALSE;
						break;
					}
				}

			}

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return toReturn;

	}

	/**
	 * Checks if is valid captured data.
	 *
	 * @param catalog the catalog
	 * @param errorMsg the error msg
	 * @return true, if is valid captured data
	 */
	private boolean isValidCapturedData(final Tsueldo catalog, final StringBuilder errorMsg) {

		if (StringUtils.isEmpty(catalog.getCvepuesto()) || StringUtils.isEmpty(catalog.getNompuesto())) {
			errorMsg.append("Se deben capturar los campos: CvePuesto y Puesto Funcional");
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	/**
	 * Checks if is valid save or update.
	 *
	 * @param catalog the catalog
	 * @param errorMsg the error msg
	 * @return true, if is valid save or update
	 */
	private boolean isValidSaveOrUpdate(final Tsueldo catalog, final StringBuilder errorMsg) {
		if ((catalog.getNconfianza() != null && catalog.getNconfianza() != Constants.ZERO)
				|| (catalog.getNsindicalizados() != null && catalog.getNsindicalizados() != Constants.ZERO)
				|| (catalog.getNeventual() != null && catalog.getNeventual() != Constants.ZERO)) {
			Integer suma = Constants.ZERO;
			if (catalog.getNconfianza() != null) {
				suma += catalog.getNconfianza();
			}
			if (catalog.getNsindicalizados() != null) {
				suma += catalog.getNsindicalizados();
			}
			if (catalog.getNeventual() != null) {
				suma += catalog.getNeventual();
			}
			if (suma <= 0) {
				errorMsg.append("La suma de Confiana, Sindicalizado y Eventual debe ser diferente de cero");
			}
		} else {
			errorMsg.append("Los campos de Confianza, Sindicalizados o Eventual tienen que ser capturados");
		}

		if ((catalog.getDietas() != null && catalog.getDietas() != Constants.ZERO)
				|| (catalog.getSbase() != null && catalog.getSbase() != Constants.ZERO)
				|| (catalog.getSbaseeven() != null && catalog.getSbaseeven() != Constants.ZERO)
				|| (catalog.getCompensacion() != null && catalog.getCompensacion() != Constants.ZERO)
				|| (catalog.getGratificacion() != null && catalog.getGratificacion() != Constants.ZERO)
				|| (catalog.getOpercepciones() != null && catalog.getOpercepciones() != Constants.ZERO)
				|| (catalog.getAguinaldo() != null && catalog.getAguinaldo() != Constants.ZERO)
				|| (catalog.getAguinaldoeven() != null && catalog.getAguinaldoeven() != Constants.ZERO)
				|| (catalog.getPv() != null && catalog.getPv() != Constants.ZERO)) {

			Double suma = 0d;
			if (catalog.getDietas() != null) {
				suma += catalog.getDietas();
			}
			if (catalog.getSbase() != null) {
				suma += catalog.getSbase();
			}
			if (catalog.getSbaseeven() != null) {
				suma += catalog.getSbaseeven();
			}
			if (catalog.getCompensacion() != null) {
				suma += catalog.getCompensacion();
			}
			if (catalog.getGratificacion() != null) {
				suma += catalog.getGratificacion();
			}
			if (catalog.getOpercepciones() != null) {
				suma += catalog.getOpercepciones();
			}
			if (catalog.getAguinaldo() != null) {
				suma += catalog.getAguinaldo();
			}
			if (catalog.getAguinaldoeven() != null) {
				suma += catalog.getAguinaldoeven();
			}
			if (catalog.getPv() != null) {
				suma += catalog.getPv();
			}
			if (suma <= 0) {
				errorMsg.append(
						"La suma de Dietas, Sdo Base, SBaseEven, Compensación, Gratificación, Otras Percep, Aguinaldo, Aguinaldo Even y PrimaV debe ser diferente de cero");
			}

		} else {
			errorMsg.append(
					"Los campos de Dietas, Sdo Base, SBaseEven, Compensación, Gratificación, Otras Percep, Aguinaldo, Aguinaldo Even o PrimaV tienen que ser capturados");
		}

		//if (catalog.getId() == null || catalog.getId() == 0) {

			if (this.sueldoRepositorio.exists(SueldosPredicate.findByCvepuesto(catalog.getCvepuesto())) && this.isbEdit() == Boolean.FALSE) {
				errorMsg.append("La Clave del puesto ya fue previamente registrada");
			}

		//}

		return errorMsg.length() == Constants.ZERO;
	}

	/**
	 * Elimina un registro.
	 */
	public void delete() {
		LOGGER.info(":: Borrar registro salaryScaleManualMB " + getBeanSelected());
		if (BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {
			sueldoRepositorio.delete(getBeanSelected());
		}
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_DELETED_RECORD);
		restartDataOperationDelete();
		this.setList(this.getList());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultList()
	 */
	@Override
	protected void consultList() {
		LOGGER.info(":: Buscar filas catalogDependenciesMB " + getBeanFind());
		String cvepuesto = "%" + (getBeanFind().getCvepuesto() == null ? StringUtils.EMPTY
				: getBeanFind().getCvepuesto().trim()) + "%";
		String nompuesto = "%" + (getBeanFind().getNompuesto() == null ? StringUtils.EMPTY
				: getBeanFind().getNompuesto().trim()) + "%";

		addList(this.sueldoRepositorio.findNativeOrderByCvepuestoNompuesto(cvepuesto, nompuesto));
		restartFilteredList();
		LOGGER.info(":: Resultado de busqueda catalogDependenciesMB " + getList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@Override
	protected void consultAll() {
		setList(this.sueldoRepositorio.findNativeAllOrderByCvepuestoNompuesto());
		restartFilteredList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#isValidRequest()
	 */
	@Override
	protected boolean isValidRequest() {
		return Boolean.TRUE;
	}

	/**
	 * Activate row edit.
	 */
	public void activateRowEdit() {
		super.activateRowEdit(rowSelected.getIndex());
	}

	/**
	 * Gets the row selected.
	 *
	 * @return the rowSelected
	 */
	public Tsueldo getRowSelected() {
		return rowSelected;
	}

	/**
	 * Sets the row selected.
	 *
	 * @param rowSelected            the rowSelected to set
	 */
	public void setRowSelected(Tsueldo rowSelected) {
		this.rowSelected = rowSelected;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#addNewOriginalList()
	 */
	@Override
	protected void addNewOriginalList() {

	}

}
