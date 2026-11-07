package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.Serializable;
import java.util.ArrayList;
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

import com.gem.sistema.business.domain.Catdaa;
import com.gem.sistema.business.repository.catalogs.CatdaaRepository;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class CatalogDependenciesAuxiliaryMB.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@ManagedBean
@ViewScoped
public class CatalogDependenciesAuxiliaryMB extends CommonCatalogMB<Catdaa> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogDependenciesAuxiliaryMB.class);

	/** Componente de servicio. */
	@ManagedProperty(value = "#{catdaaRepository}")
	private CatdaaRepository catdaaRepository;

	@ManagedProperty(value = "#{catdepRepository}")
	private CatdepRepository catdepRepository;

	/** Campo requerido Clave de Contrato. */
	// @Value("${catalog.message.field.required.clave}")
	private static final String FIELD_REQUIRED_CLAVE = FrontProperty
			.getPropertyValue("catalog.message.field.required.clave");

	/** Nombre del reporte en texto plano. */
	// @Value("${file.name.report.txt.dependenciasAuxiliaresAdm}")
	protected static final String REPORT_NAME_PLAIN_TEXT = FrontProperty
			.getPropertyValue("file.name.report.txt.dependenciasAuxiliaresAdm");

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.dependencies.auxiliary}")
	protected static final String HEADERS_REPORT_TEXT_PLAIN = FrontProperty
			.getPropertyValue("header.text.plain.dependencies.auxiliary");

	/** The Constant HEADERS_REPORT_CSV. */
	protected static final String HEADERS_REPORT_CSV = FrontProperty
			.getPropertyValue("header.csv.dependencies.auxiliary");

	/** The Constant ORDER_BY_FIELD_CLAVE. */
	/*
	 * Campo por el cual se ordenará la info
	 */
	private static final String ORDER_BY_FIELD_CLAVE = "clave";

	/** Ruta donde se encuentra el archivo jasper del reporte. */
	// @Value("${view.report.path.jasper.catalog_dependencies_auxiliary}")
	private static final String REPORT_PATH_JASPER_FILE = FrontProperty
			.getPropertyValue("view.report.path.jasper.catalog_dependencies_auxiliary");

	/**
	 * Sets the catdaa repository.
	 *
	 * @param catdaaRepository the new catdaa repository
	 */
	public void setCatdaaRepository(CatdaaRepository catdaaRepository) {
		this.catdaaRepository = catdaaRepository;
	}

	public void setCatdepRepository(CatdepRepository catdepRepository) {
		this.catdepRepository = catdepRepository;
	}

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(
				":: En postconsruct catalogDependenciesAuxiliaryMB " + this + "::" + getBeanFind() + "::" + getList());
		setBeanFind(new Catdaa());
		setList(new ArrayList<Catdaa>());
		setListNew(new ArrayList<Catdaa>());
		setQueryOrderBy(Boolean.TRUE);
		this.reportNameTextPlain = REPORT_NAME_PLAIN_TEXT;
		this.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;
		this.headersReportCSV = HEADERS_REPORT_CSV;
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogDependenciesAuxiliaryMB ");
		getBeanFind().setClave(null);
		getBeanFind().setNombre(null);
		restartData();
	}

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {
		LOGGER.info(":: Editando Fila catalogDependenciesAuxiliaryMB  " + event.getObject());
		final StringBuilder errorMsg = new StringBuilder();
		final Catdaa catalog = (Catdaa) event.getObject();
		
		if(catalog.getId() != null || catdepRepository.countByDepAuxiliar(this.getUserDetails().getIdSector(), catalog.getClave())<1) {
		
		if (validateSaveOrUpdate(catalog)) {
			executeOperationSaveOrUpdate(catalog, catdaaRepository);
		} else {
			if (errorMsg.length() == ZERO) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_UPDATE_UNIQUE
						.concat(FIELD_REQUIRED_CLAVE).concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
			} else {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, errorMsg.toString());
			}
			activateRowEdit(catalog.getIndex());
		}
		}else {
			generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, "La dependencia no puede ser editada");
			restartDataOperationDelete();
		}
	}

	/**
	 * Validate save or update.
	 *
	 * @param catalog the catalog
	 * @return true, if successful
	 */
	private boolean validateSaveOrUpdate(final Catdaa catalog) {
		final List<Catdaa> validateUnique = catdaaRepository.findByClave(catalog.getClave());
		return isValidSaveOrUpdate(validateUnique, catalog);
	}

	/**
	 * Elimina un registro.
	 */
	public void delete() {
		LOGGER.info(":: Borrar registro catalogDependenciesAuxiliaryMB ");
		if (BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {
			if (catdepRepository.countByDepAuxiliar(this.getUserDetails().getIdSector(),
					getBeanSelected().getClave()) < 1) {
				catdaaRepository.delete(getBeanSelected());
				generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_DELETED_RECORD);
			} else {
				generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, "La Dependencia no puede ser eliminada.");
			}

		}

		restartDataOperationDelete();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@Override
	protected void consultAll() {
		setList(catdaaRepository.findAllByOrderByClave());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultList()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void consultList() {
		LOGGER.info(":: Buscar filas catalogDependenciesAuxiliaryMB " + getBeanFind());
		addList((List<Catdaa>) repositoryCustom.findByFiltersOrderBy(getBeanFind(), ORDER_BY_FIELD_CLAVE));
		LOGGER.info(":: Resultado de busqueda catalogDependenciesAuxiliaryMB " + getList());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#addNewOriginalList()
	 */
	@Override
	protected void addNewOriginalList() {

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
	 * @see
	 * com.gem.sistema.web.bean.CommonCatalogMB#onRowCancel(org.primefaces.event.
	 * RowEditEvent)
	 */
	@Override
	public void onRowCancel(RowEditEvent event) {
		restartData();
		super.onRowCancel(event);
	}

}
