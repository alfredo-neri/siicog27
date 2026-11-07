package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang3.BooleanUtils;

import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.domain.Catprv;
import com.gem.sistema.business.predicates.CatprvPredicate;
import com.gem.sistema.business.repository.catalogs.CatprvRepository;
import com.gem.sistema.web.util.FrontProperty;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class CatalogMaintenanceSuppliersContractorsMB.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@ManagedBean
@ViewScoped
public class CatalogMaintenanceSuppliersContractorsMB extends CommonCatalogMB<Catprv> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogMaintenanceSuppliersContractorsMB.class);

	/** Operador mayor que. */
	private static final String OPERATOR_GREATHER_THAN = ">";

	/** Operador mayor que. */
	private static final String OPERATOR_EQUAL_GREATHER_THAN = ">=";

	/** Operador mayor que. */
	private static final String OPERATOR_LIKE = " LIKE ";

	/** Campo por el que se ordenan los datos. */
	private static final String ORDER_BY_FIELD_CLVPRV = "clvprv";

	/** The error message. */
	private String errorMessage;

	/** Componente de servicio. */
	@ManagedProperty(value = "#{catprvRepository}")
	private CatprvRepository catprvRepository;

	/** Define si la operacion es insertar o actualizar. */
	private boolean isUpdateFrontOperationSave;

	/** The error msg. */
	private StringBuilder errorMsg;

	/** The title active. */
	private boolean titleActive = Boolean.TRUE;

	/**
	 * Checks if is title active.
	 *
	 * @return true, if is title active
	 */
	public boolean isTitleActive() {
		return titleActive;
	}

	/**
	 * Sets the title active.
	 *
	 * @param titleActive the new title active
	 */
	public void setTitleActive(boolean titleActive) {
		this.titleActive = titleActive;
	}

	/** The insert active. */
	private boolean insertActive = Boolean.TRUE;

	/** Fila seleccionada. */
	private Catprv rowSelected;

	/** Campo requerido Clave Proveedor. */
	// @Value("${catalog.message.field.required.clvprv}")
	private static final String FIELD_REQUIRED_CLVPRV = FrontProperty
			.getPropertyValue("catalog.message.field.required.clvprv");

	/** The Constant FIELD_DUPL_CLVPRV. */
	private static final String FIELD_DUPL_CLVPRV = FrontProperty
			.getPropertyValue("catalog.message.field.dupplicate.clvprv");

	/** Nombre del reporte en texto plano. */
	// @Value("${file.name.report.txt.proveedoresContratistas}")
	protected static final String REPORT_NAME_PLAIN_TEXT = FrontProperty
			.getPropertyValue("file.name.report.txt.proveedoresContratistas");

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.suppliers.contractors}")
	protected static final String HEADERS_REPORT_TEXT_PLAIN = FrontProperty
			.getPropertyValue("header.text.plain.suppliers.contractors");

	/** Ruta donde se encuentra el archivo jasper del reporte. */
	// @Value("${view.report.path.jasper.catalog_maintenance_suppliers_contractors}")
	private static final String REPORT_PATH_JASPER_FILE = FrontProperty
			.getPropertyValue("view.report.path.jasper.catalog_maintenance_suppliers_contractors");

	/**
	 * Sets the catprv repository.
	 *
	 * @param catprvRepository the new catprv repository
	 */
	public void setCatprvRepository(CatprvRepository catprvRepository) {
		this.catprvRepository = catprvRepository;
	}

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct catalogProgramsMB ");
		setBeanFind(new Catprv());
		setList(new ArrayList<Catprv>());
		setListNew(new ArrayList<Catprv>());
		setQueryOrderBy(Boolean.TRUE);
		this.reportNameTextPlain = REPORT_NAME_PLAIN_TEXT;
		this.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogProgramsMB  ");
		getBeanFind().setIdsector(this.getUserDetails().getIdSector());
		getBeanFind().setClvprv(null);
		getBeanFind().setTipprv(null);
		getBeanFind().setNomprv(null);
		restartData();
	}

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {
		LOGGER.info(":: Editando Fila SuppliersContractorsMB  " + event.getObject());

		final Catprv catalog = (Catprv) event.getObject();
		catalog.setIdsector(this.getUserDetails().getIdSector());
		catalog.setUserid(this.getUserDetails().getUsername());
		if (validateUpdateOrSave(catalog)) {

			catalog.setCapprv(this.getUserDetails().getUsername());
			catalog.setFeccap(Calendar.getInstance().getTime());
			executeOperationSaveOrUpdate(catalog, catprvRepository);
			this.getBeanFind().setIdsector(this.getUserDetails().getIdSector());
			restartData();

			RequestContext.getCurrentInstance().execute("document.getElementById('form1:hideButton3').click();");
			titleActive = Boolean.TRUE;
			return;
		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,
					String.format(FIELD_DUPL_CLVPRV, catalog.getClvprv()));
			activateRowEdit(catalog.getIndex());
			titleActive = Boolean.FALSE;
		}
	}

	/**
	 * Validate update or save.
	 *
	 * @param catalog the catalog
	 * @return true, if successful
	 */
	private boolean validateUpdateOrSave(final Catprv catalog) {
		final List<Catprv> validateUnique = catprvRepository.findByClvprvAndIdsector(catalog.getClvprv(),
				this.getUserDetails().getIdSector());
		return isValidSaveOrUpdate(validateUnique, catalog);
	}

	/**
	 * Elimina un registro.
	 */
	public void delete() {
		LOGGER.info(":: Borrar registro catalogProgramsMB " + getBeanSelected());
		this.getBeanFind().setIdsector(this.getUserDetails().getIdSector());
		if (BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {

			catprvRepository.delete(getBeanSelected());
		}
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_DELETED_RECORD);

		restartDataOperationDelete();
		RequestContext.getCurrentInstance().execute("document.getElementById('form1:hideButton3').click();");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultList()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void consultList() {
		LOGGER.info(":: Buscar filas catalogProgramsMB {}", getBeanFind());
		getBeanFind().setIdsector(this.getUserDetails().getIdSector());
		// getBeanFind().setClvprv(clvprv);
		// addList((List<Catprv>)
		// repositoryCustom.findByFiltersOrderBy(getBeanFind(),
		// ORDER_BY_FIELD_CLVPRV, OPERATOR_GREATHER_THAN));
		Predicate predicate = CatprvPredicate.findByIdSector(getBeanFind());
		// addList((List<Catprv>)
		// repositoryCustom.findByFiltersOrderBy(predicate,
		// ORDER_BY_FIELD_CLVPRV, OPERATOR_EQUAL_GREATHER_THAN));
		addList(IteratorUtils.toList(catprvRepository.findAll(predicate, BY_CLVPRV).iterator()));

		restartFilteredList();
		LOGGER.info(":: Resultado de busqueda catalogProgramsMB {}", getList());
	}

	/** The Constant BY_CLVPRV. */
	private static final Sort BY_CLVPRV = new Sort(Sort.Direction.ASC, "clvprv");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@Override
	protected void consultAll() {
		setList(catprvRepository.findByIdsectorOrderByClvprv(this.getUserDetails().getIdSector()));
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
	public Catprv getRowSelected() {
		return rowSelected;
	}

	/**
	 * Sets the row selected.
	 *
	 * @param rowSelected            the rowSelected to set
	 */
	public void setRowSelected(Catprv rowSelected) {
		this.rowSelected = rowSelected;
	}

	/**
	 * Gets the file pdf.
	 *
	 * @return the file pdf
	 */
	public StreamedContent getFilePdf() {
		LOGGER.info(":: Generar reporte de PDF ");
		return super.getFilePdf(REPORT_PATH_JASPER_FILE, REPORT_NAME_PLAIN_TEXT);
	}

	/**
	 * Gets the file xls.
	 *
	 * @return the file xls
	 */
	public StreamedContent getFileXls() {
		LOGGER.info(":: Generar reporte de Excel ");
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
		this.setTitleActive(Boolean.TRUE);
		restartData();
		super.onRowCancel(event);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#insertRow()
	 */
	@Override
	public void insertRow() throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		this.setTitleActive(Boolean.FALSE);
		super.insertRow();
	}

	/**
	 * Show succes message.
	 */
	public void showSuccesMessage() {
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_EDITION_SUCCESS);
	}

}
