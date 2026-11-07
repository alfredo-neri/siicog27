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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.BooleanUtils;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.domain.Variables;
import com.gem.sistema.business.repository.catalogos.VariablesRepository;
import com.gem.sistema.business.repository.catalogs.FtecnicaDdRepository;
import com.gem.sistema.business.repository.catalogs.FtecnicasdRepository;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class CatalogIndicatorVariablesMB.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@ManagedBean
@ViewScoped
public class CatalogIndicatorVariablesMB extends CommonCatalogMB<Variables> implements Serializable {

	/** Constante de la version del objeto. */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogIndicatorVariablesMB.class);

	/** Componente de servicio. */
	@ManagedProperty(value = "#{variablesRepository}")
	private VariablesRepository variablesRepository;

	@ManagedProperty(value = "#{ftecnicaDdRepository}")
	private FtecnicaDdRepository ftecnicaDdRepository;

	@ManagedProperty(value = "#{ftecnicasdRepository}")
	private FtecnicasdRepository ftecnicasdRepository;

	/** Nombre del reporte en texto plano. */
	// @Value("${file.name.report.txt.variablesIndicadores}")
	protected static final String REPORT_NAME_PLAIN_TEXT = FrontProperty
			.getPropertyValue("file.name.report.txt.variablesIndicadores");

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.indicators.variables}")
	protected static final String HEADERS_REPORT_TEXT_PLAIN = FrontProperty
			.getPropertyValue("header.text.plain.indicators.variables");

	/** Ruta donde se encuentra el archivo jasper del reporte. */
	// @Value("${view.report.path.jasper.catalog_indicator_variables}")
	private static final String REPORT_PATH_JASPER_FILE = FrontProperty
			.getPropertyValue("view.report.path.jasper.catalog_indicator_variables");

	/**
	 * Sets the variables repository.
	 *
	 * @param variablesRepository the new variables repository
	 */
	public void setVariablesRepository(VariablesRepository variablesRepository) {
		this.variablesRepository = variablesRepository;
	}

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct catalogIndicatorVariablesMB  ");
		setBeanFind(new Variables());
		setList(new ArrayList<Variables>());
		setListNew(new ArrayList<Variables>());
		this.reportNameTextPlain = REPORT_NAME_PLAIN_TEXT;
		this.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogIndicatorVariablesMB  ");
		getBeanFind().setCvevar(null);
		getBeanFind().setNomvar(null);
		restartData();
	}

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {
		LOGGER.info(":: Editando Fila catalogIndicatorVariablesMB  " + event.getObject());
		final Variables catalog = (Variables) event.getObject();

		if (catalog.getNumvar() == null) {
			catalog.setNumvar(variablesRepository.getNextNumvar());
		}

		if (validateSaveOrUpdate(catalog)) {

			catalog.setFeccap(Calendar.getInstance().getTime());
			catalog.setUsuario(this.getUserDetails().getUsername());
			executeOperationSaveOrUpdate(catalog, variablesRepository);
		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_UPDATE_UNIQUE
					.concat("Número de variable").concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
			activateRowEdit(catalog.getIndex());
		}
	}

	/**
	 * Validate save or update.
	 *
	 * @param catalog the catalog
	 * @return true, if successful
	 */
	private boolean validateSaveOrUpdate(final Variables catalog) {

		if (catalog.getId() > ZERO) {

			Variables oldVariable = variablesRepository.findOne(catalog.getId());
			if (oldVariable.getNumvar() == catalog.getNumvar()) {
				return true;
			} else if (variablesRepository.countByNumvar(catalog.getNumvar()) < 1) {
				return true;
			} else {
				return false;
			}
		} else if (variablesRepository.countByNumvar(catalog.getNumvar()) < 1)
			return true;

		return false;
	}

	/**
	 * Elimina un registro.
	 */
	public void delete() {
		LOGGER.info(":: Borrar registro catalogIndicatorVariablesMB " + getBeanSelected());

		Integer countFtecnicasd = ftecnicasdRepository.countByNumvar(getBeanSelected().getNumvar());
		Integer countFtecnicadd = ftecnicaDdRepository.countByNumvar(getBeanSelected().getNumvar());

		if (BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO) && countFtecnicadd < 1
				&& countFtecnicasd < 1) {
			variablesRepository.delete(getBeanSelected());
			generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_DELETED_RECORD);
		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "El Número de variable "
					+ getBeanSelected().getNumvar() + " ya es utilizada y no se puede eliminar.");
		}
		restartDataOperationDelete();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultList()
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void consultList() {
		LOGGER.info(":: Buscar filas catalogIndicatorVariablesMB " + getBeanFind());
		addList((List<Variables>) repositoryCustom.findByFilters(getBeanFind()));
		LOGGER.info(":: Resultado de busqueda catalogIndicatorVariablesMB " + getList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@Override
	protected void consultAll() {
		setList(variablesRepository.findByOrderByNumvarAsc());
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

	public FtecnicaDdRepository getFtecnicaDdRepository() {
		return ftecnicaDdRepository;
	}

	public void setFtecnicaDdRepository(FtecnicaDdRepository ftecnicaDdRepository) {
		this.ftecnicaDdRepository = ftecnicaDdRepository;
	}

	public FtecnicasdRepository getFtecnicasdRepository() {
		return ftecnicasdRepository;
	}

	public void setFtecnicasdRepository(FtecnicasdRepository ftecnicasdRepository) {
		this.ftecnicasdRepository = ftecnicasdRepository;
	}

	public VariablesRepository getVariablesRepository() {
		return variablesRepository;
	}

}
