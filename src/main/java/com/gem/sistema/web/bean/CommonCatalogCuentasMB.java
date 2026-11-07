package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ONE;
import static com.gem.sistema.util.Constants.PARAMETER_WHERE_CLAUSE;
import static com.gem.sistema.util.Constants.TXT_EXTENSION;
import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.clearFields;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static com.gem.sistema.util.UtilJPACuentas.addQualifiersLikeFieldsString;
import static com.gem.sistema.util.UtilJPACuentas.clearQualifiersLikeFieldsString;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.util.CollectionUtils;

import com.gem.sistema.business.domain.AbstractEntity;
import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.RepositoryCustom;
import com.gem.sistema.util.Constants;
import com.gem.sistema.util.UtilReportCuentas;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonCatalogCuentasMB.
 *
 * @author Juan Carlos Pedraza Alcala
 * @param <T> the generic type
 */
public abstract class CommonCatalogCuentasMB<T extends AbstractEntity> extends AbstractMB {

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonCatalogCuentasMB.class);

	/**
	 * Variable para la ruta de los subreportes en Jasper.
	 */
	private static final String SUBREPORT_DIR = "SUBREPORT_DIR";

	/** The password. */
	private String password = "xnatgas16";

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the validate pass.
	 *
	 * @return the validate pass
	 */
	public String getValidatePass() {
		return validatePass;
	}

	/**
	 * Sets the validate pass.
	 *
	 * @param validatePass the new validate pass
	 */
	public void setValidatePass(String validatePass) {
		this.validatePass = validatePass;
	}

	/** The validate pass. */
	private String validatePass;

	/** Lista de registros consultados. */
	private List<T> list;

	/** Registros filtrados. */
	private List<T> filteredList;

	/** Lista de registros seleccionados. */
	private List<T> listNew;

	/** Consulta generica. */
	@ManagedProperty(value = "#{repositoryImpl}")
	protected RepositoryCustom repositoryCustom;
	
	@ManagedProperty(value = "#{conctbRepository}")
	protected ConctbRepository conctbRepository;

	/** Uitleria de reportes. */
	@ManagedProperty(value = "#{utilReportCuentas}")
	protected UtilReportCuentas utilReportCuenta;

	/** Objeto para realizar una busqueda. */
	private T beanFind;

	/** Objeto seleccionado en la tabla. */
	private T beanSelected;

	/** Mensaje de insercion de registro. */
	// @Value("${catalog.message.success.insert}")
	protected static final String MESSAGE_SUCCESS_INSERT = FrontProperty
			.getPropertyValue("catalog.message.success.insert");

	/** Mensaje edicion cancelada. */
	// @Value("${catalog.message.edited.record}")
	protected static final String MESSAGE_EDITION_SUCCESS = FrontProperty
			.getPropertyValue("catalog.message.edited.record");

	/** Mensaje edicion cancelada. */
	// @Value("${catalog.message.edition.canceled}")
	protected static final String MESSAGE_EDITION_CANCELED = FrontProperty
			.getPropertyValue("catalog.message.edition.canceled");

	/** Mensaje registro eliminado. */
	// @Value("${catalog.message.deleted.record}")
	protected static final String MESSAGE_DELETED_RECORD = FrontProperty
			.getPropertyValue("catalog.message.deleted.record");

	/** Mensaje no se encontraron registros. */
	// @Value("${catalog.message.norecords.found}")
	protected static final String MESSAGE_NO_RECORDS_FOUND = FrontProperty
			.getPropertyValue("catalog.message.norecords.found");

	/** Mensaje se encontraron registros. */
	// @Value("${catalog.message.records.found}")
	protected static final String MESSAGE_RECORDS_FOUND = FrontProperty
			.getPropertyValue("catalog.message.records.found");

	/** Mensaje Info. */
	// @Value("${catalog.message.info}")
	protected static final String MESSAGE_INFO = FrontProperty.getPropertyValue("catalog.message.info");

	/** Mensaje Error. */
	// @Value("${catalog.message.error}")
	protected static final String MESSAGE_ERROR = FrontProperty.getPropertyValue("catalog.message.error");

	/** Mensaje error insertar. */
	// @Value("${catalog.message.error.insert}")
	protected static final String MESSAGE_ERROR_INSERT = FrontProperty.getPropertyValue("catalog.message.error.insert");

	/** Mensaje campo unico. */
	// @Value("${catalog.message.error.update.unique}")
	protected static final String MESSAGE_ERROR_UPDATE_UNIQUE = FrontProperty
			.getPropertyValue("catalog.message.error.update.unique");

	/** Mensaje campo unico. */
	// @Value("${catalog.message.error.update.unique.complement}")
	protected static final String MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT = " "
			+ FrontProperty.getPropertyValue("catalog.message.error.update.unique.complement");

	/** Tipo de contenido texto plano. */
	// @Value("${type.content.text.plain}")
	protected static final String TYPE_CONTENT_TEXT_PLAIN = FrontProperty.getPropertyValue("type.content.text.plain");

	/** The Constant TYPE_CONTENT_CSV. */
	protected static final String TYPE_CONTENT_CSV = FrontProperty.getPropertyValue("type.content.csv");

	/** Separador de columnas del reporte de texto plano. */
	// @Value("${separator.cols}")
	protected static final String SEPARATOR_COLS = FrontProperty.getPropertyValue("separator.cols");

	/** Separador de columnas del reporte de texto plano. */
	// @Value("${separator.cols}")
	protected static final String SEPARATOR_COLS_CSV = FrontProperty.getPropertyValue("separator.cols.csv");

	/** Ruta donde se encuentra el archivo jasper del reporte de programas. */
	// @Value("${view.report.path.jasper}")
	protected static final String REPORT_PATH_JASPER = FrontProperty.getPropertyValue("view.report.path.jasper");

	/** Habilitar el modo de edicion. */
	// @Value("${view.edit.row.activate.icon.pencil}")
	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL = FrontProperty
			.getPropertyValue("view.edit.row.activate.icon.pencil");

	/** Habilitar el modo de edicion. */
	// @Value("${view.edit.row.activate.icon.pencil.complement}")
	private static final String VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT = FrontProperty
			.getPropertyValue("view.edit.row.activate.icon.pencil.complement");

	/** Habilitar el modo de edicion. */
	// @Value("${view.catalog.form1.objects}")
	protected static final String VIEW_CATALOG_FORM1_OBJECTS = FrontProperty
			.getPropertyValue("view.catalog.form1.objects");

	/** Elemento form1. */
	// @Value("${view.catalog.form1}")
	protected static final String VIEW_CATALOG_FORM1 = FrontProperty.getPropertyValue("view.catalog.form1");

	/** Elemento form1. */
	// @Value("${view.catalog.objects}")
	protected static final String VIEW_CATALOG_OBJECTS = FrontProperty.getPropertyValue("view.catalog.objects");

	/** Nombre del reporte en texto plano. */
	protected String reportNameTextPlain;

	/** Encabezados del reporte en texto plano. */
	protected String headersReportTextPlain;

	/** The headers report CSV. */
	protected String headersReportCSV;

	/** The is query order by. */
	private boolean isQueryOrderBy;

	/**
	 * Sets the repository custom.
	 *
	 * @param repositoryCustom the new repository custom
	 */
	public void setRepositoryCustom(RepositoryCustom repositoryCustom) {
		this.repositoryCustom = repositoryCustom;
	}

	/*
	 * public void setUtilReport(UtilReportCuentas utilReportCuenta) {
	 * this.utilReportCuenta = utilReportCuenta; }
	 */

	/**
	 * Gets the util report cuenta.
	 *
	 * @return the util report cuenta
	 */
	public UtilReportCuentas getUtilReportCuenta() {
		return utilReportCuenta;
	}

	/**
	 * Sets the util report cuenta.
	 *
	 * @param utilReportCuenta the new util report cuenta
	 */
	public void setUtilReportCuenta(UtilReportCuentas utilReportCuenta) {
		this.utilReportCuenta = utilReportCuenta;
	}

	/**
	 * Insercion de un nuevo registro.
	 *
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@SuppressWarnings("unchecked")
	public void insertRow() throws InstantiationException, IllegalAccessException {
		LOGGER.info(":: Insercion de un nuevo registro ");
		T first;
		if (BooleanUtils.negate(CollectionUtils.isEmpty(getList()))) {
			first = getList().get(ZERO);
			if (BooleanUtils.negate(first == null) && first.getId().intValue() == ZERO) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_INSERT);
				return;
			}
		}
		first = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[ZERO])
				.newInstance();
		first.setId(new Long(ZERO));
		first.setIndex(ZERO);
		getList().add(ZERO, first);

		fixIndexAfterInsertRow();
		restartFilteredList();
		executeFirtsPage();
		addNewOriginalList();
	}

	/**
	 * Insert new row.
	 *
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	@SuppressWarnings("unchecked")
	public void insertNewRow() throws InstantiationException, IllegalAccessException {
		LOGGER.info(":: Insercion de un nuevo registro ");
		if (password.equals(validatePass)) {
			T first;
			if (BooleanUtils.negate(CollectionUtils.isEmpty(getList()))) {
				first = getList().get(ZERO);
				if (BooleanUtils.negate(first == null) && first.getId().intValue() == ZERO) {
					generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_INSERT);
					return;
				}
			}
			first = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[ZERO])
					.newInstance();
			first.setId(new Long(ZERO));
			first.setIndex(ZERO);
			getList().add(ZERO, first);

			fixIndexAfterInsertRow();
			restartFilteredList();
			executeFirtsPage();
			addNewOriginalList();
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No password incorrecto");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	/**
	 * Execute firts page.
	 */
	public void executeFirtsPage() {
		final DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent(VIEW_CATALOG_FORM1).findComponent(VIEW_CATALOG_FORM1_OBJECTS);
		dataTable.setFirst(ZERO);
		LOGGER.info(":: Tabla : {}", dataTable);
	}

	/**
	 * Fix index after insert row.
	 */
	private void fixIndexAfterInsertRow() {
		if (getList().size() > ONE) {
			for (final T bean : getList().subList(ONE, getList().size())) {
				bean.setIndex(bean.getIndex() + ONE);
			}
		}
	}

	/**
	 * Muestra un mensaje al cancelar la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowCancel(final RowEditEvent event) {
		LOGGER.info(":: Cancelar edicion ");
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_EDITION_CANCELED);
	}

	/**
	 * Consulta de acuerdo a los filtros de busqueda.
	 */
	public void findValuesCatalog() {
		LOGGER.info(":: En buscar elementos del catalogo {}", this.beanFind);
		if (BooleanUtils.negate(isValidRequest())) {
			return;
		}
		addQualifiersLikeFieldsString(getBeanFind());
		consultList();
		validateListEmpty();
		getListNew().clear();
		clearQualifiersLikeFieldsString(getBeanFind());
	}

	/**
	 * Actualiza la informacion para el Front-End, que se acaba de persistir.
	 *
	 * @param catalog the catalog
	 * @param isSave the is save
	 */
	protected void refreshRowsSaveOrUpdate(final T catalog, final Boolean isSave) {
		if (BooleanUtils.negate(isSave) && getListNew().isEmpty()) {
			executeOperationsUpdateRow();
		} else {
			if (isSave) {
				getListNew().add(catalog);
			}
			LOGGER.info("::::::::::: IS ORDER BY: {} :: {} ", isQueryOrderBy, isSave);
			// if(isQueryOrderBy) {
			consultListUpdateRowOrderBy(isSave);
			// } else {
			// consultListUpdateRow();
			// }
		}
	}

	/**
	 * Execute operations update row.
	 */
	private void executeOperationsUpdateRow() {
		final T first = getList().get(ZERO);
		LOGGER.info(":: Actualizando Registro: {} ", first);
		consultList();
		LOGGER.info(":: Lista despes de Actualizar Registro: {} :: {}", getList(), first.getId());
		if (first.getId() == ZERO) {
			getList().add(ZERO, first);
			fixIndexAfterInsertRow();
			restartFilteredList();
			activateRowEdit(ZERO);
		}
		RequestContext.getCurrentInstance().update("form1:objects");
	}

	/**
	 * Reordena los registros para mostrar al inicio los que se acaban de
	 * insertar.
	 *
	 * @param isSave            determina si se trata de una operacion save o update
	 */
	protected void consultListUpdateRowOrderBy(final Boolean isSave) {
		consultAll();
		if (isSave) {
			getListNew().get(getListNew().size() - ONE).setId(getIdNewRow(isSave));
		}
		int auxIndex = ZERO;
		final List<Long> listId = new ArrayList<Long>();
		for (int indexNew = getListNew().size() - ONE; indexNew >= ZERO; indexNew--) {
			if (listId.size() == getListNew().size()) {
				break;
			}
			for (int index = getList().size() - ONE; index >= ZERO; index--) {
				if (BooleanUtils.negate(listId.contains(getList().get(index).getId()))
						&& getList().get(index).getId().equals(getListNew().get(indexNew).getId())) {

					listId.add(getList().get(index).getId());
					getList().remove(index);
					getList().add(auxIndex, getListNew().get(indexNew));
					auxIndex++;
					break;
				}
			}
		}

		fixIndexAnteriorRows();
		restartFilteredList();

		RequestContext.getCurrentInstance().update("form1:objects");
		LOGGER.info(":: \n\n\n\nResultado de busqueda catalogDependenciesAuxiliaryMB(Update) {} \n\n\n\n", getList());
	}

	/**
	 * Obtiene el ID del registro que se acaba de persistir.
	 *
	 * @param isSave the is save
	 * @return the id new row
	 */
	private Long getIdNewRow(final Boolean isSave) {
		Long result = null;
		for (final T row : getList()) {
			if (result == null || result.compareTo(row.getId()) < ZERO) {
				result = row.getId();
			}
		}
		return result;
	}

	/**
	 * Reordena los registros para mostrar al inicio los que se acaban de
	 * insertar.
	 */
	protected void consultListUpdateRow() {
		consultAll();
		LOGGER.info(":: Buscar filas (Update) {} :: {} ", getList(), getListNew());
		final List<T> aux = new ArrayList<T>();
		int index;
		int size = getList().size();
		for (index = ONE; index <= getListNew().size(); index++) {
			aux.add(getList().get(size - index));
			getList().remove(size - index);
		}
		for (index = ZERO; index < aux.size(); index++) {
			getList().add(index, aux.get(index));
			getList().get(index).setIndex(index);
		}
		fixIndexAnteriorRows();
		restartFilteredList();
		LOGGER.info(":: Resultado de busqueda catalogDependenciesAuxiliaryMB(Update) {} ", getList());
	}

	/**
	 * Reorganiza el indice de los registros.
	 */
	protected void fixIndexAnteriorRows() {
		int index = ONE;
		for (final T row : getList()) {
			if (index > getListNew().size()) {
				row.setIndex(row.getIndex() + ONE);
			}
			index++;
		}
	}

	/**
	 * **************************************** CUENTAS *****************************************************************************.
	 */
	/**
	 * Reordena los registros en Cuentas, para mostrar al inicio los que se
	 * acaban de insertar
	 */
	protected void updateRowsMaintenanceAccount() {
		consultAll();
		LOGGER.info(":: Buscar filas (Update) {} :: {}", getList(), getListNew().size());
		final List<T> aux = new ArrayList<T>();
		int index;
		int size = getList().size();
		for (index = ZERO; index < getListNew().size(); index++) {
			aux.add(getList().get(size - (getListNew().size() - index)));
		}
		for (index = ZERO; index < aux.size(); index++) {
			getList().remove(aux.get(index));
			getList().add(index, aux.get(index));
			getList().get(index).setIndex(index);
		}
		fixIndexForNewRows();
		restartFilteredList();
		LOGGER.info(":: Resultado de busqueda catalogDependenciesAuxiliaryMB(Update)2 {}", getList());
	}

	/**
	 * Reorganiza el indice de los registros, para cuentas.
	 */
	protected void fixIndexForNewRows() {
		int index = ONE;
		for (final T row : getList()) {
			if (index > getListNew().size()) {
				row.setIndex(row.getIndex() + getListNew().size());
			}
			index++;
		}
	}

	/**
	 * ***********************************************************************************************************************.
	 */

	/**
	 *  
	 */
	public void findAllValuesCatalog() {
		LOGGER.info(":: En buscar todos los elementos del catalogo ");
		clearFields(getBeanFind());
		consultAll();
		validateListEmpty();
		getListNew().clear();
	}

	/**
	 * Adds the list.
	 *
	 * @param list the list
	 */
	protected void addList(final List<T> list) {
		if (list == null) {
			consultAll();
		} else {
			setList(list);
		}
	}

	/**
	 * Validate list empty.
	 */
	protected void validateListEmpty() {
		if (getList().isEmpty()) {
			generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_NO_RECORDS_FOUND);
		}
		// else {
		// generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO,
		// MESSAGE_RECORDS_FOUND);
		// }
	}

	/**
	 * Restart data.
	 */
	protected void restartData() {
		// consultAll();
		findValuesCatalog();
		getListNew().clear();
	}

	/**
	 * Restart data operation delete.
	 */
	protected void restartDataOperationDelete() {
		getList().clear();
		consultList();
		getListNew().clear();
	}

	/**
	 * Execute operation save or update.
	 *
	 * @param catalog the catalog
	 * @param repository the repository
	 */
	protected void executeOperationSaveOrUpdate(final T catalog, final PagingAndSortingRepository<T, Long> repository) {
		final Boolean isSave = catalog.getId().intValue() == ZERO ? Boolean.TRUE : Boolean.FALSE;
		LOGGER.info(":: Salvar/Actualizar {} :: {} :: {}", isSave, repository, catalog);
		repository.save(catalog);
//		refreshRowsSaveOrUpdate(catalog, isSave);
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_EDITION_SUCCESS);
	}

	/**
	 * Checks if is valid save or update.
	 *
	 * @param validateUnique the validate unique
	 * @param catalog the catalog
	 * @return true, if is valid save or update
	 */
	protected boolean isValidSaveOrUpdate(final List<T> validateUnique, final T catalog) {
		final Boolean isUnique = CollectionUtils.isEmpty(validateUnique);
		return (BooleanUtils.negate(catalog.getId().intValue() == ZERO) && isUnique)
				|| (BooleanUtils.negate(CollectionUtils.isEmpty(validateUnique))
						&& validateUnique.get(ZERO).getId().equals(catalog.getId()))
				|| isUnique;
	}

	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public StreamedContent getFile() {
		LOGGER.info(":: Generando reporte de texto plano ");
		return utilReportCuenta.generateContentReportPlainText(getList(), headersReportTextPlain,
				TYPE_CONTENT_TEXT_PLAIN, reportNameTextPlain, TXT_EXTENSION, SEPARATOR_COLS);

//		final StreamedContent result;
//		if (CollectionUtils.isEmpty(getList())) {
//			generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, "No hay registros para exportar.");
//			result = null;
//		} else {
//			result = utilReportCuenta.generateContentReportPdf(getParametersReport(),
//					REPORT_PATH_JASPER.concat(pathReport), reportName);
//		}
//		return result;
	}

	/**
	 * Gets the csv file.
	 *
	 * @return the file
	 */
	public StreamedContent getCsvFile() {
		LOGGER.info(":: Generando reporte de texto plano ");
		return utilReportCuenta.generateContentReportCSV(getList(), headersReportCSV, TYPE_CONTENT_CSV,
				reportNameTextPlain, Constants.CSV_EXTENSION, SEPARATOR_COLS_CSV);
	}

	/**
	 * Gets the file pdf.
	 *
	 * @param pathReport the path report
	 * @param reportName the report name
	 * @return the file pdf
	 */
	public StreamedContent getFilePdf(final String pathReport, final String reportName) {
		LOGGER.info(":: Generar reporte de PDF ");
		final StreamedContent result;
//		if (CollectionUtils.isEmpty(getList())) {
//			generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, "No hay registros para exportar.");
//			result = null;
//		} else {
			result = utilReportCuenta.generateContentReportPdf(getParametersReport(),
					REPORT_PATH_JASPER.concat(pathReport), reportName);
//		}
		return result;
	}

	/**
	 * Gets the file xls.
	 *
	 * @param pathReport the path report
	 * @param reportName the report name
	 * @return the file xls
	 */
	public StreamedContent getFileXls(final String pathReport, final String reportName) {
		LOGGER.info(":: Generar reporte de Excel {} :: {}", pathReport, reportName);
		final StreamedContent result;
//		if (CollectionUtils.isEmpty(getList())) {
//			generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, "No hay registros para exportar.");
//			result = null;
//		} else {
			result = utilReportCuenta.generateContentReporXls(getParametersReport(), getList(),
					REPORT_PATH_JASPER.concat(pathReport), reportName);
//		}
		return result;
	}
	
	/** The Constant PHEADER1. */
	protected static final String PHEADER1 = FrontProperty
			.getPropertyValue("pHeader1");
	
	/** The Constant PHEADER2. */
	protected static final String PHEADER2 = FrontProperty
			.getPropertyValue("pHeader2");
	
	/** The Constant PHEADER3. */
	protected static final String PHEADER3 = FrontProperty
			.getPropertyValue("pHeader3");
	
	/** The Constant PHEADER4. */
	protected static final String PHEADER4 = FrontProperty
			.getPropertyValue("pHeader4");

	/**
	 * Gets the parameters report.
	 *
	 * @return the parameters report
	 */
	protected Map<String, Object> getParametersReport() {
		final Map<String, Object> result = new HashMap<String, Object>();
		final FacesContext context = FacesContext.getCurrentInstance();
		final ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
		
		Conctb  conctb = conctbRepository.findByIdsector(this.getUserDetails().getIdSector());
		
//		utilReportCuenta.putGeneralParametersReport(result);
		result.put(PARAMETER_WHERE_CLAUSE, utilReportCuenta.generateWhereClause(getBeanFind()));
//		result.put(SUBREPORT_DIR, servletContext.getRealPath(REPORT_PATH_JASPER).concat(SEPARATOR_FILE_SYSTEM_DIR));
		// TODO Esto va a salir de la sesion.
		result.put("pLogoName", this.getUserDetails().getPathImgCab1());
		result.put("pTitulo1", StringUtils.EMPTY);
		
		result.put("imagePathLeft", conctb.getImagePathLeft());
		result.put("imagePathRigth", conctb.getImagePathRigth());
		result.put("dependenciaName", conctb.getNomDep());
		
		if (this.getUserDetails().getIdSector() == 2) {
			result.put("pTitulo2", StringUtils.EMPTY);
			result.put("pHeader1", PHEADER1);
			result.put("pHeader2", PHEADER2);
			result.put("pHeader3", PHEADER3);
			result.put("pHeader4", PHEADER4);			
		} else {
			result.put("pTitulo2", this.getUserDetails().getMunicipio());
			result.put("pHeader1", StringUtils.EMPTY);
			result.put("pHeader2", StringUtils.EMPTY);
			result.put("pHeader3", StringUtils.EMPTY);
			result.put("pHeader4", StringUtils.EMPTY);
		}
		return result;
	}

	/**
	 * Activa el modo de edicion en una fila.
	 * 
	 * @param index
	 *            fila a ser activada.
	 */
	public void activateRowEdit(final int index) {
		LOGGER.info(":: Cerrar cuadro de dialogo ");
		final StringBuilder text = new StringBuilder();
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL);
		text.append(index);
		text.append(VIEW_EDIT_ROW_ACTIVATE_PENCIL_COMPLEMENT);
		RequestContext.getCurrentInstance().execute(text.toString());
	}

	/**
	 * Restart filtered list.
	 */
	protected void restartFilteredList() {
		if (getFilteredList() == null) {
			return;
		}
		getFilteredList().clear();
		getFilteredList().addAll(getList());
	}

	/**
	 * Gets the bean find.
	 *
	 * @return the beanFind
	 */
	public T getBeanFind() {
		return beanFind;
	}

	/**
	 * Sets the bean find.
	 *
	 * @param beanFind            the beanFind to set
	 */
	public void setBeanFind(T beanFind) {
		this.beanFind = beanFind;
	}

	/**
	 * Gets the bean selected.
	 *
	 * @return the beanSelected
	 */
	public T getBeanSelected() {
		return beanSelected;
	}

	/**
	 * Sets the bean selected.
	 *
	 * @param beanSelected            the beanSelected to set
	 */
	public void setBeanSelected(T beanSelected) {
		this.beanSelected = beanSelected;
	}

	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * Sets the list.
	 *
	 * @param list            the list to set
	 */
	public void setList(final List<T> list) {
		if (BooleanUtils.negate(CollectionUtils.isEmpty(list))) {
			int index = ZERO;
			for (final T element : list) {
				element.setIndex(index++);
			}
		}
		this.list = list;
	}

	/**
	 * Gets the list new.
	 *
	 * @return the listNew
	 */
	public List<T> getListNew() {
		return listNew;
	}

	/**
	 * Gets the filtered list.
	 *
	 * @return the filteredList
	 */
	public List<T> getFilteredList() {
		return filteredList;
	}

	/**
	 * Sets the filtered list.
	 *
	 * @param filteredList            the filteredList to set
	 */
	public void setFilteredList(final List<T> filteredList) {
		this.filteredList = filteredList;
	}

	/**
	 * Sets the list new.
	 *
	 * @param listNew            the listNew to set
	 */
	public void setListNew(final List<T> listNew) {
		this.listNew = listNew;
	}

	/**
	 * Checks if is query order by.
	 *
	 * @return the isQueryOrderBy
	 */
	public boolean isQueryOrderBy() {
		return isQueryOrderBy;
	}

	/**
	 * Sets the query order by.
	 *
	 * @param isQueryOrderBy            the isQueryOrderBy to set
	 */
	public void setQueryOrderBy(boolean isQueryOrderBy) {
		this.isQueryOrderBy = isQueryOrderBy;
	}

	/**
	 * Consult list.
	 */
	protected abstract void consultList();

	/**
	 * Consult all.
	 */
	protected abstract void consultAll();

	/**
	 * Checks if is valid request.
	 *
	 * @return true, if is valid request
	 */
	protected abstract boolean isValidRequest();

	/**
	 * Adds the new original list.
	 */
	protected abstract void addNewOriginalList();

	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

}
