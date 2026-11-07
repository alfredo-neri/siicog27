package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.repository.catalogs.MuniNepRepository;
import com.gem.sistema.business.service.catalogos.DependenciesService;
import com.gem.sistema.business.utils.ConvertCharsetUtils;
import com.gem.sistema.web.security.model.GemUser;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class CatalogProgramStructureMB.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@ManagedBean
@ViewScoped
public class CatalogProgramStructureMB extends CommonCatalogMB<Muninep> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogProgramStructureMB.class);

	/** The Constant charSetISO. */
	private final static String charSetISO = "ISO-8859-1";

	/** The Constant charSetUFT. */
	private final static String charSetUFT = "utf-8";

	/** The Constant DEFUALT_PATH_UPLOAD. */
	private static final String DEFUALT_PATH_UPLOAD = "/gem/upfiles/";

	/** The Constant FIELD_CAMPO00_00. */
	private static final String FIELD_CAMPO00_00 = "00";

	/** The Constant OPERATOR_EQUAL. */
	private static final String OPERATOR_EQUAL = "=";

	/** The Constant CAMPO7. */
	private static final String CAMPO7 = "campo7";

	/** The Constant PASS. */
	private static final String PASS = FrontProperty.getPropertyValue("catalog.program.structure.pass");

	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = FrontProperty
			.getPropertyValue("catalog.report.procedure.name.generic");

	/** The validate pass. */
	private String validatePass;

	/** The csv by pl. */
	private StreamedContent csvByPl;

	private GemUser gemUser;
	
	private String columnWidth = "width:60%";

	/** Componente de servicio. */
	@ManagedProperty(value = "#{muniNepRepository}")
	private MuniNepRepository muniNepRepository;

	/** Componente de servicio. */
	@ManagedProperty(value = "#{dependenciesServiceImpl}")
	private DependenciesService dependenciesService;

	/** Nombre del reporte en texto plano. */
	// @Value("${file.name.report.txt.estructuraProgramatica}")
	protected static final String REPORT_NAME_PLAIN_TEXT = FrontProperty
			.getPropertyValue("file.name.report.txt.estructuraProgramatica");

	/** Nombre del reporte en csv by PL-sql. */
	// @Value("${file.name.report.txt.estructuraProgramatica}")
	protected static final String REPORT_NAME_PLAIN_CSV = FrontProperty
			.getPropertyValue("file.name.report.txt.estructuraProgramatica");

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.program.estructure}")
	protected static final String HEADERS_REPORT_TEXT_PLAIN = FrontProperty
			.getPropertyValue("header.text.plain.program.estructure");

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.program.estructure}")
	protected static final String HEADERS_REPORT_CSV_PLAIN = FrontProperty
			.getPropertyValue("header.text.plain.program.estructure.csv");

	/** Mensaje de error campo0. */
	// @Value("${catalog.message.error.campo0.zero}")
	protected static final String MESSAGE_ERROR_CAMPO0_ZERO = FrontProperty
			.getPropertyValue("catalog.message.error.campo0.zero");

	/** The Constant MESSAGE_ERROR_CAMPO6. */
	protected static final String MESSAGE_ERROR_CAMPO6 = FrontProperty.getPropertyValue("catalog.message.error.campo6");

	/** The Constant QUERY_STRUCTURA. */
	protected static final String QUERY_STRUCTURA = FrontProperty
			.getPropertyValue("catalog.report.csv.strutura.programatica");

	/** Ruta donde se encuentra el archivo jasper del reporte. */
	// @Value("${view.report.path.jasper.catalog_program_structure}")
	private static final String REPORT_PATH_JASPER_FILE = FrontProperty
			.getPropertyValue("view.report.path.jasper.catalog_program_structure");

	/**
	 * Sets the muni nep repository.
	 *
	 * @param muniNepRepository the new muni nep repository
	 */
	public void setMuniNepRepository(MuniNepRepository muniNepRepository) {
		this.muniNepRepository = muniNepRepository;
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

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct catalogProgramStructureMB " + this + "::" + getBeanFind() + "::" + getList());
		setList(new ArrayList<Muninep>());
		setListNew(new ArrayList<Muninep>());
		setBeanFind(new Muninep());
		this.reportNameTextPlain = REPORT_NAME_PLAIN_TEXT;
		this.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;

		this.sSqlCsv = QUERY_STRUCTURA;
		this.AND_ID_SECTOR = " and idsector = " + this.getUserDetails().getIdSector();

		this.setValidatePass("");

		gemUser = this.getUserDetails();
		if(gemUser.getIdSector() == 1) {
			columnWidth = "width:20%";
		}

	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogProgramStructureMB  ");
		restartData();
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
			this.validatePass = null;
			RequestContext.getCurrentInstance().execute(
					"jQuery('.ui-datatable-data tr').first().find('span.ui-icon-pencil').each(function(){jQuery(this).click()});");
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No password incorrecto");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		this.setValidatePass("");
	}

	/**
	 * Append status.
	 *
	 * @param sb    the sb
	 * @param field the field
	 * @return the string builder
	 */
	private StringBuilder appendStatus(StringBuilder sb, String field) {
		if (StringUtils.isNotEmpty(field)) {
			sb.append("1");
		} else {
			sb.append("0");
		}
		return sb;
	}

	/**
	 * Checks if is valid capture.
	 *
	 * @param catalog the catalog
	 * @return true, if is valid capture
	 */
	private boolean isValidCapture(Muninep catalog) {
		StringBuilder sb = new StringBuilder();
		sb = this.appendStatus(sb, catalog.getCampo0());
		sb = this.appendStatus(sb, catalog.getCampo1());
		sb = this.appendStatus(sb, catalog.getCampo2());
		sb = this.appendStatus(sb, catalog.getCampo3());
		sb = this.appendStatus(sb, catalog.getCampo4());
		sb = this.appendStatus(sb, catalog.getCampo6());
		return sb.toString().matches("1((00001)|(10001)|(11001)|(11101)|(11111))");
	}

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {
		LOGGER.info(":: Editando Fila catalogProgramStructureMB  " + event.getObject());
		final Muninep catalog = (Muninep) event.getObject();
		final StringBuilder errorMsg = new StringBuilder();
		if (this.isValidCapture(catalog)) {
			if (/*
				 * isValidFieldCampo0(catalog.getCampo0(), errorMsg) &&
				 */ isValidfieldCampoX(catalog.getCampo0(), "FN", errorMsg)
					&& isValidfieldCampoX(catalog.getCampo1(), "Fun", errorMsg)
					&& isValidfieldCampoX(catalog.getCampo2(), "SF", errorMsg)
					&& isValidfieldCampoX(catalog.getCampo3(), "Prog", errorMsg)
					&& isValidfieldCampoX(catalog.getCampo4(), "SP", errorMsg)
					&& isValidfieldCampoX(catalog.getCampo5(), "Proy", errorMsg)
					&& isValidFieldCampo6(catalog.getCampo6(), errorMsg)) {
				int idSector = getUserDetails().getIdSector();
				catalog.setIdsector(idSector);
				if (StringUtils.isEmpty(catalog.getCampo1())) {
					catalog.setCampo1(StringUtils.EMPTY);
				}
				if (StringUtils.isEmpty(catalog.getCampo2())) {
					catalog.setCampo2(StringUtils.EMPTY);
				}
				if (StringUtils.isEmpty(catalog.getCampo3())) {
					catalog.setCampo3(StringUtils.EMPTY);
				}
				if (StringUtils.isEmpty(catalog.getCampo4())) {
					catalog.setCampo4(StringUtils.EMPTY);
				}
				if (StringUtils.isEmpty(catalog.getCampo5())) {
					catalog.setCampo5(StringUtils.EMPTY);
				}
				if (StringUtils.isEmpty(catalog.getCampo6())) {
					catalog.setCampo6(StringUtils.EMPTY);
				}
				if (StringUtils.isEmpty(catalog.getCampo7())) {
					catalog.setCampo7(StringUtils.EMPTY);
				}
				if (StringUtils.isEmpty(catalog.getCampo8())) {
					catalog.setCampo8(StringUtils.EMPTY);
				}
				if (StringUtils.isEmpty(catalog.getCampo9())) {
					catalog.setCampo9(StringUtils.EMPTY);
				}
				String campo7 = catalog.getCampo0() + catalog.getCampo1() + catalog.getCampo2() + catalog.getCampo3()
						+ catalog.getCampo4() + catalog.getCampo5();

				if(catalog.getId() == null||catalog.getId()==0) {
					
				
				List<Muninep> existentes = muniNepRepository.findByCampo7AndIdsector(campo7,
						this.getUserDetails().getIdSector());
				if (existentes != null && !existentes.isEmpty()) {
					generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,
							"Ya existe un registro con la misma configuración, no puede haber duplicados.");
					activateRowEdit(catalog.getIndex());
					return;
				}

				catalog.setCampo7(campo7);
				if (!dependenciesService.isValidPreviousLevel(catalog, errorMsg, this.getUserDetails().getIdSector())) {
					generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, errorMsg.toString());
					activateRowEdit(catalog.getIndex());
					return;
				}
				}
				catalog.setUserid(this.getUserDetails().getUsername());
				catalog.setIdRef(0L);

				LOGGER.info("antes de guardar: " + catalog);

				executeOperationSaveOrUpdate(catalog, muniNepRepository);
				consultList();
				restartFilteredList();
				RequestContext.getCurrentInstance().execute("document.getElementById('form1:hideButton').click();");
			} else {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, errorMsg.toString());

			}
		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,
					"Favor de capturar en orden de FN, Fun, SF, Prog, Sp y Proy");
			activateRowEdit(catalog.getIndex());
		}
	}

	/**
	 * Checks if is validfield campo X.
	 *
	 * @param campo    the campo
	 * @param nombre   the nombre
	 * @param errorMsg the error msg
	 * @return true, if is validfield campo X
	 */
	private boolean isValidfieldCampoX(final String campo, String nombre, final StringBuilder errorMsg) {
		if (campo == null || StringUtils.isEmpty(campo)) {
			return true;
		}
		if (campo.trim().length() != 2) {
			errorMsg.append("El valor del campo " + nombre + " debe constar de dos caracteres alfanuméricos. ");
			return false;
		}

		if (FIELD_CAMPO00_00.equals(campo)) {
			errorMsg.append("El campo " + nombre + " no puede ser 00. ");
			return false;
		}

		return true;
	}

	/**
	 * Checks if is valid field campo 6.
	 *
	 * @param campo0   the campo 0
	 * @param errorMsg the error msg
	 * @return true, if is valid field campo 6
	 */
	/*
	 * private boolean isValidFieldCampoX(final String campo0, final StringBuilder
	 * errorMsg) { final boolean result; if (campo0.equals(FIELD_CAMPO00_00)) {
	 * errorMsg.append(MESSAGE_ERROR_CAMPO0_ZERO); result = Boolean.FALSE; } else {
	 * result = Boolean.TRUE; } return result; }
	 */

	/**
	 * 
	 * @param campo0
	 * @param errorMsg
	 * @return
	 */
	private boolean isValidFieldCampo6(final String campo0, final StringBuilder errorMsg) {
		final boolean result;
		if (StringUtils.isEmpty(campo0)) {
			errorMsg.append(MESSAGE_ERROR_CAMPO6);
			result = Boolean.FALSE;
		} else {
			result = Boolean.TRUE;
		}
		return result;
	}

	/**
	 * Elimina un registro.
	 */
	public void delete() {
		LOGGER.info(":: Borrar registro catalogProgramStructureMB " + getBeanSelected());
		if (BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {
			muniNepRepository.delete(getBeanSelected());
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
	protected void consultList() {
		LOGGER.info(":: Buscar filas catalogProgramStructureMB " + getBeanFind());
		getBeanFind().setIdsector(this.getUserDetails().getIdSector());
		addList((List<Muninep>) repositoryCustom.findByFiltersOrderBy(getBeanFind(), CAMPO7, OPERATOR_EQUAL));
		restartFilteredList();
		// LOGGER.info(":: Resultado de busqueda catalogProgramStructureMB " +
		// getList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@Override
	protected void consultAll() {
		// final Predicate predicate =
		// MuniNepPredicates.isMuniNepNotEmpty(this.getUserDetails().getIdSector());
		setList((List<Muninep>) muniNepRepository.findAllByIdsectorOrderByCampo7(this.getUserDetails().getIdSector()));
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
		getBeanFind().setIdsector(this.getUserDetails().getIdSector());
		return super.getFilePdf(REPORT_PATH_JASPER_FILE, REPORT_NAME_PLAIN_TEXT);
	}

	/**
	 * Gets the file csv.
	 *
	 * @return the file csv
	 */
	public StreamedContent getFileCsv() {
		LOGGER.info(":: Generar reporte de PDF ");
		getBeanFind().setIdsector(this.getUserDetails().getIdSector());
		return super.getCsvFile();
	}

	/** The stream. */
	InputStream stream = null;

	/** The out. */
	Map<String, Object> out;

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
	 * Gets the file csv pl.
	 *
	 * @return the file csv pl
	 */
	public void getFileCsvPl() {

		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("i_header", HEADERS_REPORT_CSV_PLAIN)
				.addValue("i_query", this.getFileCsvByPl() + " order by campo0,campo1,campo2,campo3,campo4,campo5")
				.addValue("i_file_name", REPORT_NAME_PLAIN_CSV + ".csv");

		out = new HashMap<String, Object>();
		out = this.getExecutePlService().callProcedure(PROCEDURE_NAME, parametros);

		if (Integer.valueOf(out.get("O_COD_ERROR").toString()) > 0) {
			try {
				try {
					fileNameOut = DEFUALT_PATH_UPLOAD + "estructura_Programatica.csv";
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
			csvByPl = new DefaultStreamedContent(stream, "application/csv", fileNameOut);
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSJ_ERROR").toString());
		}

	}

	/**
	 * Gets the file xls.
	 *
	 * @return the file xls
	 */
	public StreamedContent getFileXls() {
		LOGGER.info(":: Generar reporte de Excel ");
		getBeanFind().setIdsector(this.getUserDetails().getIdSector());
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
	 * Show succes message.
	 */
	public void showSuccesMessage() {
		consultList();
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_EDITION_SUCCESS);
	}

	/**
	 * Sets the dependencies service.
	 *
	 * @param dependenciesService the new dependencies service
	 */
	public void setDependenciesService(DependenciesService dependenciesService) {
		this.dependenciesService = dependenciesService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#getValidatePass()
	 */
	public String getValidatePass() {
		return validatePass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.web.bean.CommonCatalogMB#setValidatePass(java.lang.String)
	 */
	public void setValidatePass(String validatePass) {
		this.validatePass = validatePass;
	}

	public GemUser getGemUser() {
		return gemUser;
	}

	public void setGemUser(GemUser gemUser) {
		this.gemUser = gemUser;
	}

	public String getColumnWidth() {
		return columnWidth;
	}

	public void setColumnWidth(String columnWidth) {
		this.columnWidth = columnWidth;
	}

}
