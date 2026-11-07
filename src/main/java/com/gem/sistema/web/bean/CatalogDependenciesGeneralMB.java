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

import com.gem.sistema.business.domain.Catdgm;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.CatdgmRepository;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class CatalogDependenciesGeneralMB.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@ManagedBean
@ViewScoped
public class CatalogDependenciesGeneralMB extends CommonCatalogMB<Catdgm> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogDependenciesGeneralMB.class);

	/** Componente de servicio. */
	@ManagedProperty(value = "#{catdgmRepository}")
	private CatdgmRepository catdgmRepository;

	@ManagedProperty(value = "#{catdepRepository}")
	private CatdepRepository catdepRepository;

	/** Campo requerido Clave. */
	// @Value("${catalog.message.field.required.clave}")
	private static final String FIELD_REQUIRED_CLAVE = FrontProperty
			.getPropertyValue("catalog.message.field.required.clave");

	/** Nombre del reporte en texto plano. */
	// @Value("${file.name.report.txt.dependenciasGenerales}")
	protected static final String REPORT_NAME_PLAIN_TEXT = FrontProperty
			.getPropertyValue("file.name.report.txt.dependenciasGenerales");

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.dependencies.auxiliary.general}")
	protected static final String HEADERS_REPORT_TEXT_PLAIN = FrontProperty
			.getPropertyValue("header.text.plain.dependencies.auxiliary.general");

	/** The Constant HEADERS_REPORT_CSV. */
	protected static final String HEADERS_REPORT_CSV = FrontProperty
			.getPropertyValue("header.csv.dependencies.auxiliary.general");

	/** Campo por el cual se ordenará la info. */
	private static final String ORDER_BY_FIELD_CLAVE = "clave";

	/** Ruta donde se encuentra el archivo jasper del reporte. */
	// @Value("${view.report.path.jasper.catalog_dependencies_general}")
	private static final String REPORT_PATH_JASPER_FILE = FrontProperty
			.getPropertyValue("view.report.path.jasper.catalog_dependencies_general");

	/**
	 * Sets the catdgm repository.
	 *
	 * @param catdgmRepository the new catdgm repository
	 */
	public void setCatdgmRepository(CatdgmRepository catdgmRepository) {
		this.catdgmRepository = catdgmRepository;
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
				":: En postconsruct catalogDependenciesGeneralMB " + this + "::" + getBeanFind() + "::" + getList());
		setBeanFind(new Catdgm());
		setList(new ArrayList<Catdgm>());
		setListNew(new ArrayList<Catdgm>());
		setQueryOrderBy(Boolean.TRUE);
		this.reportNameTextPlain = REPORT_NAME_PLAIN_TEXT;
		this.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;
		this.headersReportCSV = HEADERS_REPORT_CSV;
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogDependenciesGeneralMB  ");
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
		LOGGER.info(":: Editando Fila catalogDependenciesGeneralMB  " + event.getObject());
		final StringBuilder errorMsg = new StringBuilder();
		final Catdgm catalog = (Catdgm) event.getObject();

		if (catdepRepository.countByfirst3chars(this.getUserDetails().getIdSector(), catalog.getClave()) > 0) {
			generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, "La Dependencia No puede ser editada");
		} else {

			if (validateUpdateOrSave(catalog)) {
				executeOperationSaveOrUpdate(catalog, catdgmRepository);
			} else {
				if (errorMsg.length() == ZERO) {
					generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_UPDATE_UNIQUE
							.concat(FIELD_REQUIRED_CLAVE).concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
				} else {
					generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, errorMsg.toString());
				}
				activateRowEdit(catalog.getIndex());
			}
		}
		restartDataOperationDelete();
	}

	/**
	 * Validate update or save.
	 *
	 * @param catalog the catalog
	 * @return true, if successful
	 */
	private boolean validateUpdateOrSave(final Catdgm catalog) {
		final List<Catdgm> validateUnique = catdgmRepository.findByClave(catalog.getClave());
		return isValidSaveOrUpdate(validateUnique, catalog);
	}

	/**
	 * Elimina un registro.
	 */
	public void delete() {
		if (BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {
			if (catdepRepository.countByfirst3chars(this.getUserDetails().getIdSector(),
					getBeanSelected().getClave()) < 1) {
				catdgmRepository.delete(getBeanSelected());
				generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_DELETED_RECORD);
			} else {
				generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, "La Dependencia no se puede eliminar");
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
		setList(catdgmRepository.findAllByOrderByClave());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultList()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void consultList() {
		LOGGER.info(":: Buscar filas catalogDependenciesGeneralMB " + getBeanFind());
		addList((List<Catdgm>) repositoryCustom.findByFiltersOrderBy(getBeanFind(), ORDER_BY_FIELD_CLAVE));
		LOGGER.info(":: Resultado de busqueda catalogDependenciesGeneralMB " + getList());
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
