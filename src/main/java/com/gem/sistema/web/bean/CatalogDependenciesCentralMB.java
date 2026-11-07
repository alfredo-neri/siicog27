package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;
import static jakarta.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Catdaa;
import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Catdgm;
import com.gem.sistema.business.domain.Polide;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.predicates.DependencePredicates;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.PolideRepository;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;
import com.gem.sistema.business.service.catalogos.DependenciesService;
import com.gem.sistema.business.utils.ConvertCharsetUtils;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class CatalogDependenciesCentralMB.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@Component("catalogDependenciesCentralMB")
@Scope("view")
public class CatalogDependenciesCentralMB extends CommonCatalogMB<Catdep> implements Serializable {

	/** The Constant charSetISO. */
	private final static String charSetISO = "ISO-8859-1";

	/** The Constant charSetUFT. */
	private final static String charSetUFT = "utf-8";

	/** The Constant DEFUALT_PATH_UPLOAD. */
	private static final String DEFUALT_PATH_UPLOAD = "/gem/upfiles/";

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogDependenciesCentralMB.class);

	/** Longitud de la clave de dependencia. */
	// private static final int LENGTH_CLVDEP = 10;

	private static final String OPERATOR_EQUAL = "=";

	/** The Constant CLVDEP. */
	private static final String CLVDEP = "clvdep";

	/** The new name. */
	private String newName;

	/** The new name 2. */
	private String newName2;

	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = FrontProperty
			.getPropertyValue("catalog.report.procedure.name.generic");

	/** The csv by pl. */
	private StreamedContent csvByPl;

	/** The txt by pl. */
	private StreamedContent txtByPl;

	/** Encabezados reporte de texto plano csv. */
	// @Value("${header.text.plain.program.estructure}")
	protected static final String HEADERS_REPORT_CSV_PLAIN = "Clave,Nombre,Nivel";

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.program.estructure}")
	protected static final String HEADERS_REPORT_TXT_PLAIN = "|Clave|Nombre|Nivel|";

	/** Nombre del reporte en csv by PL-sql. */
	// @Value("${file.name.report.txt.estructuraProgramatica}")
	protected static final String REPORT_NAME_PLAIN_CSV = "DependenciasC.csv";

	/** Nombre del reporte en txt by PL-sql. */
	// @Value("${file.name.report.txt.estructuraProgramatica}")
	protected static final String REPORT_NAME_PLAIN_TXT = "DependenciasC.txt";

	/** Query del reporte en csv by PL-sql. */
	protected static final String QUERY_STRUCTURA_CSV = "SELECT '''' || CLVDEP || ',\"' || NOMDEP ||'\",\"' || NVL(ULTNIV,'') || '\"' FROM CATDEP";

	/** Query del reporte en TXT by PL-sql. */
	protected static final String QUERY_STRUCTURA_TXT = "SELECT CLVDEP || '|' || NOMDEP || '|' || NVL(ULTNIV,'') FROM CATDEP ";

	/** Registros filtrados. */
	private List<Catdgm> filteredDependenciesGeneral;

	/** Registros filtrados. */
	private List<Catdaa> filteredDependenciesAuxiliary;

	/**
	 * Dependencias generales.
	 */
	private List<Catdgm> dependenciesGeneral;

	/**
	 * Dependencias auxiliares.
	 */
	private List<Catdaa> dependenciesAuxiliary;

	/** Servicios de dependencias central. */
	@Value("#{dependenciesServiceImpl}")
	private DependenciesService dependenciesService;

	/** Repositorio de Catdep. */
	@Value("#{catdepRepository}")
	private CatdepRepository catdepRepository;

	/** Componente de servicio. */
	@Value("#{xcatproRepository}")
	private XcatproRepository xcatproRepository;

	/** The polide repository. */
	@Value("#{polideRepository}")
	private PolideRepository polideRepository;

	/** The insert. */
	private Boolean insert;

	/** Registro seleccionado. */
	private Catdep rowSelected;

	/** Campo requerido clvdep. */
	// @Value("${catalog.message.field.required.clvdep}")
	// private static final String FIELD_REQUIRED_CLVDEP = FrontProperty
	// .getPropertyValue("catalog.message.field.required.clvdep");

	/**
	 * Nombre del reporte en texto plano
	 */
	// @Value("${file.name.report.txt.dependencias}")
	protected static final String REPORT_NAME_PLAIN_TEXT = FrontProperty
			.getPropertyValue("file.name.report.txt.dependencias");

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.dependencies}")
	protected static final String HEADERS_REPORT_TEXT_PLAIN = FrontProperty
			.getPropertyValue("header.text.plain.dependencies");

	/** The Constant HEADERS_REPORT_CSV. */
	protected static final String HEADERS_REPORT_CSV = FrontProperty.getPropertyValue("header.csv.dependencies");

	/** Clvdep formato erroneo. */
	// @Value("${catalog.message.error.clvdep.zeroleft}")
	private static final String MESSAGE_ERROR_CLVDEP_ZEROLEFT = FrontProperty
			.getPropertyValue("catalog.message.error.clvdep.zeroleft");

	/** Ruta donde se encuentra el archivo jasper del reporte. */
	// @Value("${view.report.path.jasper.catalog_dependencies_central}")
	private static final String REPORT_PATH_JASPER_FILE = FrontProperty
			.getPropertyValue("view.report.path.jasper.catalog_dependencies_central");

	/** The Constant MESSAGE_ERROR_HAS_SLEVEL. */
	private static final String MESSAGE_ERROR_HAS_SLEVEL = FrontProperty
			.getPropertyValue("catalog.message.error.clvdep.hasslevel");

	/** id sector = 2 centrales. */
	// private static final int ID_SECTOR = 2;

	/**
	 * Add property for autocomplete function
	 * 
	 */
	private Catdep catdepAutoComplete;

	/** The error message. */
	private String errorMessage;

	/**
	 * Sets the dependencies service.
	 *
	 * @param dependenciesService the new dependencies service
	 */
	public void setDependenciesService(DependenciesService dependenciesService) {
		this.dependenciesService = dependenciesService;
	}

	/**
	 * Sets the catdep repository.
	 *
	 * @param catdepRepository the new catdep repository
	 */
	public void setCatdepRepository(CatdepRepository catdepRepository) {
		this.catdepRepository = catdepRepository;
	}

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct catalogDependenciesMB " + this + "::" + getBeanFind() + "::" + getList());
		setBeanFind(new Catdep());
		setList(new ArrayList<Catdep>());
		setListNew(new ArrayList<Catdep>());
		this.reportNameTextPlain = REPORT_NAME_PLAIN_TEXT;
		this.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;
		this.headersReportCSV = HEADERS_REPORT_CSV;
		AND_ID_SECTOR = "  AND IDSECTOR = " + this.getUserDetails().getIdSector();
		sSqlCsv = "";
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogDependenciesMB  ");
		getBeanFind().setClvdep(null);
		getBeanFind().setNomdep(null);
		restartData();
	}

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {
		final Catdep catalog = (Catdep) event.getObject();
		// catalog.setClvdep(fillZerosToRight(catalog.getClvdep().toUpperCase(),
		// LENGTH_CLVDEP));

		LOGGER.info(":: Editando Fila catalogDependenciesMB  " + rowSelected.getIndex() + "::" + catalog);
		if (catalog.getId() != null && catalog.getId() != ZERO) {
			LOGGER.info("******************Es edicion");
			if (tieneProgramas(catalog) || tienePolizas(catalog)) {
				this.errorMessage = "Hay programas o polizas asociados a esta dependencia";

				activateRowEdit(catalog.getIndex());
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, this.errorMessage);
				return;
			}
//			if (tieneSucesores(catalog)) {
//				if (catalog.getUltniv().toUpperCase().equals("S")) {
//					this.errorMessage = "La clave de la dependencia tiene sucesores";
//					PrimeFaces.current()
//							.execute("document.getElementById('form1:hideButton3').click();");
//					activateRowEdit(catalog.getIndex());
//					generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, this.errorMessage);
//					return;
//				}
//			}
		}
		final StringBuilder errorMsg = new StringBuilder();
		String idUSer = getUserDetails().getUsername();
		Integer idSector = getUserDetails().getIdSector();

		LOGGER.info("idUser:: " + idUSer);
		LOGGER.info("idSECTOTR:: " + idSector);
		// Clvdep debe ser unica
		if (isValidCapturedData(catalog.getClvdep(), errorMsg) && isValidSaveOrUpdate(catalog)) {
			catalog.setUserid(idUSer);
			catalog.setIdsector(idSector);
			catalog.setCapdep(idUSer);
			catalog.setFeccap(new Date());
			catalog.setIdRef(this.getUserDetails().getIdMunicipio());
			// catalog.setClvdep(fillZerosToRight(catalog.getClvdep(),
			// LENGTH_CLVDEP));
			executeOperationSaveOrUpdate(catalog, catdepRepository);
			PrimeFaces.current().executeScript("document.getElementById('form1:hideButton2').click();");
			// restartData();
		} else {
			if (errorMsg.length() == ZERO) {
				this.errorMessage = MESSAGE_ERROR_UPDATE_UNIQUE.concat("Clave ")
						.concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT);
			} else {
				this.errorMessage = errorMsg.toString();

			}

			activateRowEdit(catalog.getIndex());
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, this.errorMessage);
		}
	}

	/** The selected index. */
	private int selectedIndex;

	/**
	 * Formatea clavedep.
	 */
	public void formateaClavedep() {

		String clv = this.getList().get(0).getClvdep();
		if (clv != null && clv.length() > 0) {
			StringBuilder sb = new StringBuilder(clv.toUpperCase());
			for (int i = clv.length(); i < 15; i++) {
				sb.append("0");
			}
			this.getList().get(0).setClvdep(sb.toString());
		}
	}

	/**
	 * Tiene programas.
	 *
	 * @param catalog the catalog
	 * @return the boolean
	 */
	private Boolean tieneProgramas(Catdep catalog) {
		List<Xcatpro> programas = xcatproRepository.findByClvdep(catalog.getClvdep());
		if (programas != null && programas.size() > 0) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * Tiene polizas.
	 *
	 * @param catalog the catalog
	 * @return the boolean
	 */
	private Boolean tienePolizas(Catdep catalog) {
		List<Polide> polizas = polideRepository.findByScta(catalog.getClvdep());
		if (polizas != null && polizas.size() > 0) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * Tiene sucesores.
	 *
	 * @param catalog the catalog
	 * @return the boolean
	 */
	private Boolean tieneSucesores(Catdep catalog) {
		Boolean tiene = dependenciesService.tieneSucesores(catalog, getList());
		return tiene;
	}

	/**
	 * Valida ceros a la izquierda.
	 *
	 * @param clvdep   the clvdep
	 * @param errorMsg the error msg
	 * @return true, if is valid captured data
	 */
	private boolean isValidCapturedData(final String clvdep, final StringBuilder errorMsg) {
		boolean result;
		if (clvdep.indexOf(String.valueOf(ZERO)) == ZERO) {
			errorMsg.append(MESSAGE_ERROR_CLVDEP_ZEROLEFT);
			result = Boolean.FALSE;
		} else {
			result = Boolean.TRUE;
		}
		return result;
	}

	/**
	 * Checks if is valid save or update.
	 *
	 * @param catalog the catalog
	 * @return true, if is valid save or update
	 */
	private boolean isValidSaveOrUpdate(final Catdep catalog) {
		final List<Catdep> validateUnique = catdepRepository.findByClvdep(catalog.getClvdep());
		return isValidSaveOrUpdate(validateUnique, catalog);
	}

	/**
	 * Elimina un registro.
	 */
	public void delete() {
		LOGGER.info(":: Borrar registro catalogDependenciesMB " + getBeanSelected());
		if (this.hasSubLeves(getBeanSelected())) {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_HAS_SLEVEL);
			return;
		}
		if (tieneProgramas(getBeanSelected()) || tienePolizas(getBeanSelected())) {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,
					"Hay programas o polizas asociados a esta dependencia");
			return;
		}
		if (BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {
			catdepRepository.delete(getBeanSelected());
		}
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_DELETED_RECORD);
		// restartDataOperationDelete();
		findValuesCatalog();
	}

	/**
	 * Checks for sub leves.
	 *
	 * @param catalog the catalog
	 * @return true, if successful
	 */
	private boolean hasSubLeves(Catdep catalog) {
		String cvedep2find = StringUtils.stripEnd(catalog.getClvdep(), "0") + "%";
		return this.catdepRepository.count(DependencePredicates.getPredicatetLikeCveDep(cvedep2find)) > 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultList()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void consultList() {
		LOGGER.info(":: Buscar filas catalogDependenciesMB {} ", getBeanFind());
		this.getBeanFind().setIdsector(getUserDetails().getIdSector());
		addList((List<Catdep>) repositoryCustom.findByFiltersOrderBy(getBeanFind(), CLVDEP, OPERATOR_EQUAL));
		restartFilteredList();
		LOGGER.info(":: Resultado de busqueda catalogDependenciesMB {} ", getList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@Override
	protected void consultAll() {
		setList(catdepRepository.findAllByIdsectorOrderByClvdep(this.getUserDetails().getIdSector()));
		restartFilteredList();
	}

	// private static final Sort CATDEP_CLV = Sort.by(Sort.Direction.ASC,
	// "clvdep");

	/**
	 * To autocomplete CatDep for cve.
	 *
	 * @param query the query
	 * @return the list
	 */
	public List<Catdep> completeCatDep(String query) {
		consultAll();
		List<Catdep> allDependencias = getList();
		List<Catdep> filteredDep = new ArrayList<>();
		for (int i = 0; i < allDependencias.size(); i++) {
			Catdep skin = allDependencias.get(i);

			if (skin.getClvdep() != null && skin.getClvdep().toLowerCase().startsWith(query)) {
				filteredDep.add(skin);
			}
		}
		return filteredDep;
	}

	/**
	 * To autocomplete CatDep for name.
	 *
	 * @param query the query
	 * @return the list
	 */
	public List<Catdep> completeCatDepName(String query) {
		consultAll();
		List<Catdep> allDependencias = getList();
		List<Catdep> filteredDep = new ArrayList<>();
		for (int i = 0; i < allDependencias.size(); i++) {
			Catdep skin = allDependencias.get(i);
			if (skin.getNomdep() != null && skin.getNomdep().toLowerCase().startsWith(query)) {
				filteredDep.add(skin);
			}
		}
		return filteredDep;
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
	 * Gets the dependencies general.
	 *
	 * @return the dependenciesGeneral
	 */
	public List<Catdgm> getDependenciesGeneral() {
		return dependenciesGeneral;
	}

	/**
	 * Sets the dependencies general.
	 *
	 * @param dependenciesGeneral the dependenciesGeneral to set
	 */
	public void setDependenciesGeneral(List<Catdgm> dependenciesGeneral) {
		this.dependenciesGeneral = dependenciesGeneral;
	}

	/**
	 * Gets the dependencies auxiliary.
	 *
	 * @return the dependenciesAuxiliary
	 */
	public List<Catdaa> getDependenciesAuxiliary() {
		return dependenciesAuxiliary;
	}

	/**
	 * Sets the dependencies auxiliary.
	 *
	 * @param dependenciesAuxiliary the dependenciesAuxiliary to set
	 */
	public void setDependenciesAuxiliary(List<Catdaa> dependenciesAuxiliary) {
		this.dependenciesAuxiliary = dependenciesAuxiliary;
	}

	/**
	 * Gets the row selected.
	 *
	 * @return the rowSelected
	 */
	public Catdep getRowSelected() {
		return rowSelected;
	}

	/**
	 * Sets the row selected.
	 *
	 * @param rowSelected the rowSelected to set
	 */
	public void setRowSelected(Catdep rowSelected) {
		this.rowSelected = rowSelected;
	}

	/**
	 * Gets the filtered dependencies general.
	 *
	 * @return the filteredDependenciesGeneral
	 */
	public List<Catdgm> getFilteredDependenciesGeneral() {
		return filteredDependenciesGeneral;
	}

	/**
	 * Sets the filtered dependencies general.
	 *
	 * @param filteredDependenciesGeneral the filteredDependenciesGeneral to set
	 */
	public void setFilteredDependenciesGeneral(List<Catdgm> filteredDependenciesGeneral) {
		this.filteredDependenciesGeneral = filteredDependenciesGeneral;
	}

	/**
	 * Gets the filtered dependencies auxiliary.
	 *
	 * @return the filteredDependenciesAuxiliary
	 */
	public List<Catdaa> getFilteredDependenciesAuxiliary() {
		return filteredDependenciesAuxiliary;
	}

	/**
	 * Sets the filtered dependencies auxiliary.
	 *
	 * @param filteredDependenciesAuxiliary the filteredDependenciesAuxiliary to set
	 */
	public void setFilteredDependenciesAuxiliary(List<Catdaa> filteredDependenciesAuxiliary) {
		this.filteredDependenciesAuxiliary = filteredDependenciesAuxiliary;
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
		this.getBeanFind().setIdsector(getUserDetails().getIdSector());
		return super.getFilePdf(REPORT_PATH_JASPER_FILE, REPORT_NAME_PLAIN_TEXT);
	}

	/**
	 * Gets the file xls.
	 *
	 * @return the file xls
	 */
	public StreamedContent getFileXls() {
		LOGGER.info(":: Generar reporte de Excel ");
		this.getBeanFind().setIdsector(getUserDetails().getIdSector());
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

	/**
	 * Gets the catdep auto complete.
	 *
	 * @return the catdep auto complete
	 */
	public Catdep getCatdepAutoComplete() {
		return catdepAutoComplete;
	}

	/**
	 * Sets the catdep auto complete.
	 *
	 * @param catdepAutoComplete the new catdep auto complete
	 */
	public void setCatdepAutoComplete(Catdep catdepAutoComplete) {
		this.catdepAutoComplete = catdepAutoComplete;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#insertRow()
	 */
	@Override
	public void insertRow() throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		super.insertRow();
		this.setInsert(Boolean.TRUE);
	}

	/**
	 * Gets the insert.
	 *
	 * @return the insert
	 */
	public Boolean getInsert() {
		return insert;
	}

	/**
	 * Sets the insert.
	 *
	 * @param insert the insert to set
	 */
	public void setInsert(Boolean insert) {
		this.insert = insert;
	}

	/**
	 * Gets the xcatpro repository.
	 *
	 * @return the xcatpro repository
	 */
	public XcatproRepository getXcatproRepository() {
		return xcatproRepository;
	}

	/**
	 * Sets the xcatpro repository.
	 *
	 * @param xcatproRepository the new xcatpro repository
	 */
	public void setXcatproRepository(XcatproRepository xcatproRepository) {
		this.xcatproRepository = xcatproRepository;
	}

	/**
	 * Gets the polide repository.
	 *
	 * @return the polide repository
	 */
	public PolideRepository getPolideRepository() {
		return polideRepository;
	}

	/**
	 * Sets the polide repository.
	 *
	 * @param polideRepository the new polide repository
	 */
	public void setPolideRepository(PolideRepository polideRepository) {
		this.polideRepository = polideRepository;
	}

	/**
	 * Show succes message.
	 */
	public void showSuccesMessage() {
		consultList();
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_EDITION_SUCCESS);
	}

	/**
	 * Show error message.
	 */
	public void showErrorMessage() {
		activateRowEdit(this.selectedIndex);
		generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, this.errorMessage);
	}

	/**
	 * Show cancel edition.
	 */
	public void showCancelEdition() {
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_EDITION_CANCELED);
	}

	/**
	 * Gets the txt by pl.
	 *
	 * @return the txt by pl
	 */
	public StreamedContent getTxtByPl() {
		return txtByPl;
	}

	/**
	 * Sets the txt by pl.
	 *
	 * @param txtByPl the new txt by pl
	 */
	public void setTxtByPl(StreamedContent txtByPl) {
		this.txtByPl = txtByPl;
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
	Map<String, Object> out;

	/**
	 * Gets the file csv pl.
	 *
	 * @return the file csv pl
	 */
	public void getFileCsvPl() {
		sSqlCsv = QUERY_STRUCTURA_CSV;
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("i_header", HEADERS_REPORT_CSV_PLAIN)
				.addValue("i_query", this.getFileCsvByPl() + " ORDER BY CLVDEP ASC")
				.addValue("i_file_name", REPORT_NAME_PLAIN_CSV);

		out = new HashMap<String, Object>();
		out = this.getExecutePlService().callProcedure(PROCEDURE_NAME, parametros);

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				try {
					fileNameOut = DEFUALT_PATH_UPLOAD + "dependencias.csv";
					newFile = new File(out.get("O_FULL_FILE_PATH").toString());
					target = new File(fileNameOut);

					ConvertCharsetUtils.transform(newFile, charSetUFT, target, charSetISO);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				stream = new FileInputStream(target);
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			csvByPl = StreamedContents.of(stream, "application/csv", fileNameOut, null);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", fileNameOut);
		}
	}

	/** The input file. */
	InputStream inputFile = null;

	/** The input file 2. */
	InputStream inputFile2 = null;

	/** The new file. */
	File newFile = null;

	/** The target. */
	File target = null;

	/** The file name out. */
	String fileNameOut = "";

	/**
	 * Gets the file txt pl.
	 *
	 * @return the file txt pl
	 */
	public void getFileTxtPl() {
		sSqlCsv = QUERY_STRUCTURA_TXT;
		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("i_header", HEADERS_REPORT_TXT_PLAIN)
				.addValue("i_query", this.getFileCsvByPl() + " ORDER BY CLVDEP ASC")
				.addValue("i_file_name", REPORT_NAME_PLAIN_TXT);

		out = new HashMap<String, Object>();
		out = this.getExecutePlService().callProcedure(PROCEDURE_NAME, parametros);

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {

				try {
					fileNameOut = DEFUALT_PATH_UPLOAD + "dependencias.txt";
					newFile = new File(out.get("O_FULL_FILE_PATH").toString());
					target = new File(fileNameOut);

					ConvertCharsetUtils.transform(newFile, charSetUFT, target, charSetISO);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				stream = new FileInputStream(target);
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			txtByPl = StreamedContents.of(stream, "application/txt", fileNameOut, null);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", fileNameOut);
		}
	}

}
