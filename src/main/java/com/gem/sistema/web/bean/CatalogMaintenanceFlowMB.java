package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Catflu;
import com.gem.sistema.business.predicates.CatfluPredicates;
import com.gem.sistema.business.repository.catalogs.CatfluRepository;
import com.gem.sistema.business.service.catalogos.FlowService;
import com.gem.sistema.util.UtilJPA;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class CatalogMaintenanceFlowMB.
 *
 * @author Juan Carlos Pedraza Alcalao
 */

@ManagedBean(name = "catalogMaintenanceFlowMB")
@ViewScoped
public class CatalogMaintenanceFlowMB extends CommonCatalogMB<Catflu> implements Serializable {

	/** Constante de la version del objeto. */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogMaintenanceFlowMB.class);

	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = FrontProperty
			.getPropertyValue("catalog.report.procedure.name.generic");

	/** The Constant QUERY_MAINTENANCE. */
	protected static final String QUERY_MAINTENANCE = FrontProperty
			.getPropertyValue("catalog.report.csv.catalog.maintenance.catalog.flow");

	/** Componente de repositorio. */
	@ManagedProperty(value = "#{catfluRepository}")
	private CatfluRepository catfluRepository;

	/** Componente de servicio. */
	@ManagedProperty(value = "#{flowServiceImpl}")
	private FlowService flowService;

	/** Nombre del reporte en texto plano. */
	// @Value("${file.name.report.txt.cuentas}")
	private static final String PASS = FrontProperty.getPropertyValue("catalog.maintenance.flow.pass");

	/** Campo requerido Clave de Contrato. */
	// @Value("${catalog.message.field.required.clvflu}")
	private static final String FIELD_REQUIRED_CLVFLU = FrontProperty
			.getPropertyValue("catalog.message.field.required.clvflu");

	/** Nombre del reporte en texto plano. */
	// @Value("${file.name.report.txt.mantenimientoCatalogoFlujo}")
	protected static final String REPORT_NAME_PLAIN_TEXT = FrontProperty
			.getPropertyValue("file.name.report.txt.mantenimientoCatalogoFlujo");

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.maintenance.flow}")
	protected static final String HEADERS_REPORT_TEXT_PLAIN = FrontProperty
			.getPropertyValue("header.text.plain.maintenance.flow");

	/** Campo por el que se ordenará la consulta de datos. */
	// private static final String ORDER_BY_FIELD_CLVFLU = "clvflu";

	/**
	 * Ruta donde se encuentra el archivo jasper del reporte
	 */
	// @Value("${view.report.path.jasper.catalog_maintenance_catalog_flow}")
	private static final String REPORT_PATH_JASPER_FILE = FrontProperty
			.getPropertyValue("view.report.path.jasper.catalog_maintenance_catalog_flow");

	/** The csv by pl. */
	private StreamedContent csvByPl;

	/** Nombre del reporte en csv by PL-sql. */
	// @Value("${file.name.report.txt.estructuraProgramatica}")
	protected static final String REPORT_NAME_PLAIN_CSV = "catalogMaintenanceCatalogFlow.csv";

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.program.estructure}")
	protected static final String HEADERS_REPORT_CSV_PLAIN = FrontProperty
			.getPropertyValue("header.csv.catalog.maintenance.catalog.flow");

	/** The Constant QUERY_STRUCTURA. */
	protected static final String QUERY_STRUCTURA = FrontProperty
			.getPropertyValue("catalog.report.csv.strutura.programatica");
	
	/** Bean for autocomplete function. */
	private Catflu catFluAut;

	/** The validate pass. */
	private String validatePass;

	/**
	 * Sets the catflu repository.
	 *
	 * @param catfluRepository the new catflu repository
	 */
	public void setCatfluRepository(CatfluRepository catfluRepository) {
		this.catfluRepository = catfluRepository;
	}

	/**
	 * Sets the flow service.
	 *
	 * @param flowService the new flow service
	 */
	public void setFlowService(FlowService flowService) {
		this.flowService = flowService;
	}

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct catalogMaintenanceCatalogFlowMB: {} , REPORT_NAME_PLAIN_TEXT = {}", this,
				CatalogMaintenanceFlowMB.REPORT_NAME_PLAIN_TEXT);
		setBeanFind(new Catflu());
		setList(new ArrayList<Catflu>());
		setListNew(new ArrayList<Catflu>());
		setQueryOrderBy(Boolean.TRUE);
		sSqlCsv = QUERY_STRUCTURA;
		this.reportNameTextPlain = REPORT_NAME_PLAIN_TEXT;
		this.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;
		this.setValidatePass("");
		sSqlCsv = QUERY_MAINTENANCE;
		AND_ID_SECTOR = " and idsector = " + this.getUserDetails().getIdSector() + " ORDER BY clvflu ASC ";
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogMaintenanceCatalogFlowMB  :{} ", this);
		getBeanFind().setGruflu(null);
		getBeanFind().setNomflu(null);
		restartData();
	}

	/** The Constant DF. */
	private static final DecimalFormat DF = new DecimalFormat("00.00");

	/** The error msg. */
	private StringBuilder errorMsg;

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {
		LOGGER.info(":: Editando Fila catalogMaintenanceCatalogFlowMB  " + event.getObject());
		Catflu catalog = (Catflu) event.getObject();
		catalog = (Catflu) UtilJPA.fillPropertyStringIfNull(catalog);
		catalog.setIdsector(this.getUserDetails().getIdSector());
		catalog.setUserid(this.getUserDetails().getUsername());
		String formated = DF.format(catalog.getClvflu());
		if (StringUtils.isEmpty(catalog.getGruflu())) {
			catalog.setGruflu(StringUtils.substringBefore(formated, "."));
		}
		if (StringUtils.isEmpty(catalog.getSguflu())) {
			catalog.setSguflu(StringUtils.substringAfter(formated, "."));
		}
		// catalog.setClvflu(new BigDecimal(catalog.getClvflu()).setScale(2,
		// RoundingMode.HALF_UP).toString());

		errorMsg = new StringBuilder();
		StringBuilder msg2 = new StringBuilder();

		// if (NumberUtils.createLong(catalog.getGruflu()) > 0) {
		if (vaalidateSaveOrUpdate(catalog)) {
			if (flowService.isValidPreviousLevel(catalog, msg2)) {
				catalog.setMesflu1(BigDecimal.ZERO);
				catalog.setMesflu2(BigDecimal.ZERO);
				catalog.setMesflu3(BigDecimal.ZERO);
				catalog.setMesflu4(BigDecimal.ZERO);
				catalog.setMesflu5(BigDecimal.ZERO);
				catalog.setMesflu6(BigDecimal.ZERO);
				catalog.setMesflu7(BigDecimal.ZERO);
				catalog.setMesflu8(BigDecimal.ZERO);
				catalog.setMesflu9(BigDecimal.ZERO);
				catalog.setMesflu10(BigDecimal.ZERO);
				catalog.setMesflu11(BigDecimal.ZERO);
				catalog.setMesflu12(BigDecimal.ZERO);
				catalog.setMesflu13(BigDecimal.ZERO);
				catalog.setMesflu14(BigDecimal.ZERO);
				catalog.setMesflu15(BigDecimal.ZERO);
				catalog.setMesflu16(BigDecimal.ZERO);
				catalog.setIdRef(0L);
				executeOperationSaveOrUpdate(catalog, catfluRepository);
				restartData();
			} else {
				this.errorMsg.append(msg2);
			}
		} else {
			this.errorMsg.append(MESSAGE_ERROR_UPDATE_UNIQUE.concat(FIELD_REQUIRED_CLVFLU)
					.concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
		}
		// } else {
		// this.errorMsg.append("La clave del grupo debe ser mayor a cero");
		// }

		if (CollectionUtils.isNotEmpty(this.getList())) {
			this.getList().get(ZERO).setGruflu(StringUtils.EMPTY);
			this.getList().get(ZERO).setSguflu(StringUtils.EMPTY);
		}
		catalog.setGruflu(StringUtils.EMPTY);
		catalog.setSguflu(StringUtils.EMPTY);

	}

	// private boolean isUpdateFrontOperationSave;

	/**
	 * Update view rows auto generated.
	 */
	public void updateViewRowsAutoGenerated() {
		LOGGER.info(":: Actualiza los registros autogenerados ");
		if (null != errorMsg && errorMsg.length() > ZERO) {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_INFO, errorMsg.toString());
			errorMsg.delete(ZERO, errorMsg.length());
			activateRowEdit(0);
		} else {

			// if (this.getBeanSelected().getId().intValue() == ZERO) {
			this.findAllValuesCatalog();
			generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_EDITION_SUCCESS);
			RequestContext.getCurrentInstance().execute("PF('objectsWdt').unselectAllRows();");

			// }
			// else {
			// activateRowEdit(0);
			// }
		}

	}

	/**
	 * Creates the row.
	 */
	public void createRow() {
		LOGGER.info("entra a crear una nueva linea....");
		Catflu catalog = this.getBeanSelected();
		catalog = (Catflu) UtilJPA.fillPropertyStringIfNull(catalog);
		catalog.setIdsector(this.getUserDetails().getIdSector());
		catalog.setUserid(this.getUserDetails().getUsername());
		String formated = DF.format(catalog.getClvflu());
		if (StringUtils.isEmpty(catalog.getGruflu())) {
			catalog.setGruflu(StringUtils.substringBefore(formated, "."));
		}
		if (StringUtils.isEmpty(catalog.getSguflu())) {
			catalog.setSguflu(StringUtils.substringAfter(formated, "."));
		}
		// catalog.setClvflu(new BigDecimal(catalog.getClvflu()).setScale(2,
		// RoundingMode.HALF_UP).toString());

		errorMsg = new StringBuilder();
		StringBuilder msg2 = new StringBuilder();
		if (vaalidateSaveOrUpdate(catalog)) {
			if (flowService.isValidPreviousLevel(catalog, msg2)) {
				executeOperationSaveOrUpdate(catalog, catfluRepository);
				restartData();
			} else {
				this.errorMsg.append(msg2);
			}
		} else {
			this.errorMsg.append(MESSAGE_ERROR_UPDATE_UNIQUE.concat(FIELD_REQUIRED_CLVFLU)
					.concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
		}

		catalog.setGruflu(StringUtils.EMPTY);
		catalog.setSguflu(StringUtils.EMPTY);
		this.getList().get(ZERO).setGruflu(StringUtils.EMPTY);
		this.getList().get(ZERO).setSguflu(StringUtils.EMPTY);
	}

	/**
	 * Vaalidate save or update.
	 *
	 * @param catalog the catalog
	 * @return true, if successful
	 */
	private boolean vaalidateSaveOrUpdate(final Catflu catalog) {
		final List<Catflu> validateUnique = catfluRepository.findByClvfluAndIdsector(catalog.getClvflu(),
				this.getUserDetails().getIdSector());
		return isValidSaveOrUpdate(validateUnique, catalog);
	}

	/**
	 * Elimina un registro.
	 */
	public void delete() {
		LOGGER.info(":: Borrar registro catalogMaintenanceCatalogFlowMB " + getBeanSelected());
		if (BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {
			catfluRepository.delete(getBeanSelected());
		}
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_DELETED_RECORD);
		restartDataOperationDelete();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultList()
	 */
	// @SuppressWarnings("unchecked")
	@Override
	protected void consultList() {
		LOGGER.info(":: Buscar filas catalogMaintenanceCatalogFlowMB: {} ", this);
		// this.getBeanFind().setIdsector(this.getUserDetails().getIdSector());
		if (StringUtils.isEmpty(this.getBeanFind().getNomflu())) {
			this.getBeanFind().setNomflu(StringUtils.EMPTY);
		}

		String clveflustr = null == this.getBeanFind().getClvflu() ? StringUtils.EMPTY
				: this.getBeanFind().getClvflu().toString();
		addList(this.catfluRepository.getByClvfluAndNomflu(StringUtils.strip(clveflustr, "0") + "%",
				"%" + this.getBeanFind().getNomflu() + "%", this.getUserDetails().getIdSector()));
		LOGGER.info(":: Resultado de busqueda catalogMaintenanceCatalogFlowMB " + getList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@Override
	protected void consultAll() {
		LOGGER.info(":: Antes de buscar datos con orderBy clvflu ");
		setList(IteratorUtils.toList(catfluRepository
				.findAll(CatfluPredicates.byIdSector(this.getUserDetails().getIdSector()), SORT_CLVFLU).iterator()));
	}

	/** The Constant SORT_CLVFLU. */
	private static final Sort SORT_CLVFLU = new Sort(Sort.Direction.ASC, "clvflu");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#isValidRequest()
	 */
	@Override
	protected boolean isValidRequest() {
		// final Boolean result;
		// if(StringUtils.isEmpty(getBeanFind().getGruflu())) {
		// generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "El campo
		// Grupo es requerido para la busqueda.");
		// result = Boolean.FALSE;
		// } else {
		// result = Boolean.TRUE;
		// }
		return Boolean.TRUE;
	}

	/**
	 * To autocomplete CatFlw for cve.
	 *
	 * @param query the query
	 * @return the list
	 */
	public List<Catflu> completeCatfluClv(String query) {
		consultAll();
		List<Catflu> allCatflu = getList();
		List<Catflu> filteredCatflu = new ArrayList<>();
		for (int i = 0; i < allCatflu.size(); i++) {
			Catflu skin = allCatflu.get(i);
			if (skin.getGruflu() != null && skin.getGruflu().toLowerCase().startsWith(query)) {
				filteredCatflu.add(skin);
			}
		}
		return filteredCatflu;
	}

	/**
	 * To autocomplete CatFlw for name.
	 *
	 * @param query the query
	 * @return the list
	 */
	public List<Catflu> completeCatfluName(String query) {
		LOGGER.info(":: completeCatfluName {} ", this);
		consultAll();
		List<Catflu> allCatflu = getList();
		List<Catflu> filteredCatflu = new ArrayList<>();
		for (int i = 0; i < allCatflu.size(); i++) {
			Catflu skin = allCatflu.get(i);
			if (skin.getNomflu() != null && skin.getNomflu().toLowerCase().startsWith(query)) {
				filteredCatflu.add(skin);
			}
		}
		return filteredCatflu;
	}

	/**
	 * Gets the file pdf.
	 *
	 * @return the file pdf
	 */
	public StreamedContent getFilePdf() {
		LOGGER.info(":: Generar reporte de PDF ");
		this.getBeanFind().setIdsector(this.getUserDetails().getIdSector());
		return super.getFilePdf(REPORT_PATH_JASPER_FILE, REPORT_NAME_PLAIN_TEXT);
	}

	/**
	 * Gets the file xls.
	 *
	 * @return the file xls
	 */
	public StreamedContent getFileXls() {
		LOGGER.info(":: Generar reporte de Excel ");
		this.getBeanFind().setIdsector(this.getUserDetails().getIdSector());
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

	/**
	 * Gets the cat flu aut.
	 *
	 * @return the cat flu aut
	 */
	public Catflu getCatFluAut() {
		return catFluAut;
	}

	/**
	 * Sets the cat flu aut.
	 *
	 * @param catFluAut the new cat flu aut
	 */
	public void setCatFluAut(Catflu catFluAut) {
		this.catFluAut = catFluAut;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#insertRow()
	 */
	@Override
	public void insertRow() throws InstantiationException, IllegalAccessException {

		LOGGER.info(":: Insercion de un nuevo registro ");
		if (PASS.equals(this.validatePass)) {

			super.insertRow();
			RequestContext.getCurrentInstance().execute(
					"jQuery('.ui-datatable-data tr').first().find('span.ui-icon-pencil').each(function(){jQuery(this).click()});");
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Password incorrecto");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		this.setValidatePass("");

	}

	/**
	 * Gets the validate pass.
	 *
	 * @return the validatePass
	 */
	public String getValidatePass() {
		return validatePass;
	}

	/**
	 * Sets the validate pass.
	 *
	 * @param validatePass            the validatePass to set
	 */
	public void setValidatePass(String validatePass) {
		this.validatePass = validatePass;
	}

	/**
	 * Gets the catflu repository.
	 *
	 * @return the catfluRepository
	 */
	public CatfluRepository getCatfluRepository() {
		return catfluRepository;
	}

	/**
	 * Gets the flow service.
	 *
	 * @return the flowService
	 */
	public FlowService getFlowService() {
		return flowService;
	}

	/**
	 * Show succes message.
	 */
	public void showSuccesMessage() {
		consultList();
		// generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO,
		// MESSAGE_EDITION_SUCCESS);
	}

	// public void showErrorMessage() {
	// generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,
	// this.errorMessage);
	// }

	/**
	 * Show cancel edition.
	 */
	public void showCancelEdition() {
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_EDITION_CANCELED);
	}

	/**
	 * Gets the csv by pl.
	 *
	 * @return the csv by pl
	 */
	public StreamedContent getCsvByPl() {
		return csvByPl;
	}

	/**
	 * Sets the csv by pl.
	 *
	 * @param csvByPl the new csv by pl
	 */
	public void setCsvByPl(StreamedContent csvByPl) {
		this.csvByPl = csvByPl;
	}

	/** The stream. */
	InputStream stream = null;
	
	/** The out. */
	Map<String, Object> out;;

	/**
	 * Gets the file csv pl.
	 *
	 * @return the file csv pl
	 */
	public void getFileCsvPl() {

		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("i_header", HEADERS_REPORT_CSV_PLAIN).addValue("i_query", this.getFileCsvByPl())

				.addValue("i_file_name", REPORT_NAME_PLAIN_CSV + ".csv");

		out = new HashMap<String, Object>();
		out = this.getExecutePlService().callProcedure(PROCEDURE_NAME, parametros);

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				stream = new FileInputStream(new File(out.get("O_FULL_FILE_PATH").toString()));
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			csvByPl = new DefaultStreamedContent(stream, "application/csv", out.get("O_FULL_FILE_PATH").toString());
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSJ_ERROR").toString());
		}

	}
}
