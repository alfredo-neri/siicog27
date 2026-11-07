package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.BooleanUtils;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Obras1;
import com.gem.sistema.business.repository.catalogs.Obras1Repository;
import com.gem.sistema.web.util.FrontProperty;


// TODO: Auto-generated Javadoc
/**
 * The Class CatalogContractsMB.
 *
 * @author Juan Carlos Pedraza Alcala
 */

@ManagedBean
@ViewScoped
public class CatalogContractsMB extends CommonCatalogMB<Obras1> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogContractsMB.class);

	/** The Constant FIELD_ORDER_BY_CLVCTO. */
	private static final String FIELD_ORDER_BY_CLVCTO = "clvcto";

	/** The Constant FIELD_ORDER_BY_CLVPRV. */
	private static final String FIELD_ORDER_BY_CLVPRV = "clvprv";

	/** Componente de servicio. */
	@ManagedProperty(value = "#{obras1Repository}")
	private Obras1Repository obras1Repository;

	/** The row selected. */
	private Obras1 rowSelected;

	/** Campo requerido Clave de Contrato. */
	// @Value("${catalog.message.field.required.clvcto}")
	private static final String FIELD_REQUIRED_CLVCTO = FrontProperty
			.getPropertyValue("catalog.message.field.required.clvcto");

	/** Nombre del reporte en texto plano. */
	// @Value("${file.name.report.txt.mantenimientoContratos}")
	protected static final String REPORT_NAME_PLAIN_TEXT = FrontProperty
			.getPropertyValue("file.name.report.txt.mantenimientoContratos");

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.contracts}")
	protected static final String HEADERS_REPORT_TEXT_PLAIN = FrontProperty
			.getPropertyValue("header.text.plain.contracts");

	/** Mensaje de error de fechas. */
	// @Value("${catalog.message.error.fefcto}")
	protected static final String ERROR_MESSAGE_DATES = FrontProperty.getPropertyValue("catalog.message.error.fefcto");
	
	/** The Constant ERROR_MESSAGE_DATES2. */
	protected static final String ERROR_MESSAGE_DATES2 = FrontProperty.getPropertyValue("catalog.message.error.fefcto2");

	/** Mensaje de error de fechas. */
	// @Value("${view.radiobutton.order.contract}")
	protected static final String VIEW_ORDER_CLVCTO = FrontProperty.getPropertyValue("view.radiobutton.order.contract");

	/** Variable que indica el tipo de ordenamiento. */
	private String typeOrder;

	/** Ruta donde se encuentra el archivo jasper del reporte. */
	// @Value("${view.report.path.jasper.catalog_maintenance_contracts}")
	private static final String REPORT_PATH_JASPER_FILE = FrontProperty
			.getPropertyValue("view.report.path.jasper.catalog_maintenance_contracts");

	/**
	 * Sets the obras 1 repository.
	 *
	 * @param obras1Repository the new obras 1 repository
	 */
	public void setObras1Repository(Obras1Repository obras1Repository) {
		this.obras1Repository = obras1Repository;
	}

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {

		LOGGER.info(":: En postconsruct catalogContractsMB " + this + "::" + getBeanFind() + "::" + getList());
		setBeanFind(new Obras1());
		setList(new ArrayList<Obras1>());
		setListNew(new ArrayList<Obras1>());
		setQueryOrderBy(Boolean.TRUE);
		this.reportNameTextPlain = REPORT_NAME_PLAIN_TEXT;
		this.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;

	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogContractsMB  ");
		getBeanFind().setClvcto(null);
		getBeanFind().setNomobr(null);
		restartData();
	}

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {
		LOGGER.info(":: Editando Fila catalogContractsMB  " + event.getObject());
		final Obras1 catalog = (Obras1) event.getObject();

		final StringBuilder errorMsg = new StringBuilder();
		if (isValidFieldFefcto(catalog.getFefcto(), catalog.getFeicto(), errorMsg, catalog.getFeccap()) && validateUpdateOrSave(catalog)) {
			if (catalog.getMoncto() == null) {
				catalog.setMoncto(BigDecimal.ZERO);
			}
			executeOperationSaveOrUpdate(catalog, obras1Repository);
			// restartDataOperationDelete();
		} else {
			if (errorMsg.length() == ZERO) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_UPDATE_UNIQUE
						.concat(FIELD_REQUIRED_CLVCTO).concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
			} else {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, errorMsg.toString());
			}
			activateRowEdit(catalog.getIndex());
		}
	}

	/**
	 * Checks if is valid field fefcto.
	 *
	 * @param fefcto the fefcto
	 * @param feicto the feicto
	 * @param errorMsg the error msg
	 * @param feccap the feccap
	 * @return true, if is valid field fefcto
	 */
	private boolean isValidFieldFefcto(final Date fefcto, final Date feicto, final StringBuilder errorMsg,
			Date feccap) {
		final boolean result;
		if (fefcto == null || feicto == null) {
			return Boolean.TRUE;
		}
		if (feicto.compareTo(fefcto) >= ZERO) {
			result = Boolean.FALSE;
			errorMsg.append(ERROR_MESSAGE_DATES);
		} else {
			if (Calendar.getInstance().getTime().before(feccap)) {
				result = Boolean.FALSE;
				errorMsg.append(ERROR_MESSAGE_DATES2);
			} else {
				result = Boolean.TRUE;
			}
		}
		return result;
	}

	/**
	 * Validate update or save.
	 *
	 * @param catalog the catalog
	 * @return true, if successful
	 */
	private boolean validateUpdateOrSave(final Obras1 catalog) {
		final List<Obras1> validateUnique = obras1Repository.findByClvcto(catalog.getClvcto());
		return isValidSaveOrUpdate(validateUnique, catalog);
	}

	/**
	 * Elimina un registro.
	 */
	public void delete() {
		LOGGER.info(":: Borrar registro catalogContractsMB " + getBeanSelected());
		if (BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {
			obras1Repository.delete(getBeanSelected());
		}
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_DELETED_RECORD);
		restartDataOperationDelete();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultList()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void consultList() {
		this.getBeanFind().setFeccap(null);
		this.getBeanFind().setFecpol(null);
		this.getBeanFind().setFefcto(null);
		this.getBeanFind().setFeicto(null);
		LOGGER.info(":: Buscar filas catalogContractsMB ");
		if (typeOrder == null || typeOrder.equals(VIEW_ORDER_CLVCTO)) {
			addList((List<Obras1>) repositoryCustom.findByFiltersOrderBy(getBeanFind(), FIELD_ORDER_BY_CLVCTO));
		} else {

			addList((List<Obras1>) repositoryCustom.findByFiltersOrderBy(getBeanFind(), FIELD_ORDER_BY_CLVPRV));
		}
		// restartFilteredList();
		LOGGER.info(":: Resultado de busqueda catalogContractsMB " + getList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@Override
	protected void consultAll() {
		LOGGER.info(":: Buscar filas catalogContractsMB All ");
		if (typeOrder == null || typeOrder.equals(VIEW_ORDER_CLVCTO)) {
			setList(obras1Repository.findAllByOrderByClvctoAsc());
		} else {
			setList(obras1Repository.findAllByOrderByClvprvAsc());
		}
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
	 * Gets the row selected.
	 *
	 * @return the rowSelected
	 */
	public Obras1 getRowSelected() {
		return rowSelected;
	}

	/**
	 * Sets the row selected.
	 *
	 * @param rowSelected            the rowSelected to set
	 */
	public void setRowSelected(final Obras1 rowSelected) {
		this.rowSelected = rowSelected;
	}

	/**
	 * Gets the type order.
	 *
	 * @return the typeOrder
	 */
	public String getTypeOrder() {
		return typeOrder;
	}

	/**
	 * Sets the type order.
	 *
	 * @param typeOrder            the typeOrder to set
	 */
	public void setTypeOrder(final String typeOrder) {
		this.typeOrder = typeOrder;
	}

	/**
	 * Gets the file pdf.
	 *
	 * @return the file pdf
	 */
	public StreamedContent getFilePdf() {
		LOGGER.info(":: Generar reporte de PDF ");
		this.getBeanFind().setFeccap(null);
		this.getBeanFind().setFecpol(null);
		this.getBeanFind().setFefcto(null);
		this.getBeanFind().setFeicto(null);
		return super.getFilePdf(REPORT_PATH_JASPER_FILE, REPORT_NAME_PLAIN_TEXT);
	}

	/**
	 * Gets the file xls.
	 *
	 * @return the file xls
	 */
	public StreamedContent getFileXls() {
		LOGGER.info(":: Generar reporte de Excel ");
		this.getBeanFind().setFeccap(null);
		this.getBeanFind().setFecpol(null);
		this.getBeanFind().setFefcto(null);
		this.getBeanFind().setFeicto(null);
		return super.getFileXls(REPORT_PATH_JASPER_FILE, REPORT_NAME_PLAIN_TEXT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#addNewOriginalList()
	 */
	@Override
	protected void addNewOriginalList() {

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#onRowCancel(org.primefaces.event.RowEditEvent)
	 */
	@Override
	public void onRowCancel(RowEditEvent event) {
		restartData();
		super.onRowCancel(event);
	}

	/**
	 * On row cancel.
	 */
	public void onRowCancel() {
		restartData();
		LOGGER.info(":: Cancelar edicion ");
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_EDITION_CANCELED);
	}

}
