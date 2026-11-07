package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ONE;
import static com.gem.sistema.util.Constants.TWO;
import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Fuentef;
import com.gem.sistema.business.predicates.FuentefPredicates;
import com.gem.sistema.business.repository.catalogs.FuentefRepository;
import com.gem.sistema.business.repository.catalogs.RepositoryCustom;
import com.gem.sistema.util.UtilJPA;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class CatalogFinancialSourcesMB.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@ManagedBean
@ViewScoped
public class CatalogFinancialSourcesMB extends CommonCatalogMB<Fuentef> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogFinancialSourcesMB.class);

	/** The Constant EMPTY_LEVEL. */
	private static final String EMPTY_LEVEL = "00";

	/** The Constant FIRST_VALUE_LIGA. */
	private static final String FIRST_VALUE_LIGA = "101";

	/** The Constant SQL_MAX_LIGA. */
	private static final String SQL_MAX_LIGA = "SELECT max(f.liga) FROM Fuentef f ";

	/** The Constant PASS. */

	private static final String PASS = FrontProperty.getPropertyValue("catalog.maintenance.financial.source.pass");

	/** The Constant LEVEL_FOUR. */
	private static final int LEVEL_FOUR = 4;

	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = FrontProperty
			.getPropertyValue("catalog.report.procedure.name.generic");

	/** The csv by pl. */
	private StreamedContent csvByPl;

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.program.estructure}")
	protected static final String HEADERS_REPORT_CSV_PLAIN = FrontProperty
			.getPropertyValue("header.csv.catalog.financialSources");

	/** Nombre del reporte en csv by PL-sql. */
	// @Value("${file.name.report.txt.estructuraProgramatica}")
	protected static final String REPORT_NAME_PLAIN_CSV = "catalogFinancialSources.csv";

	/** Query del reporte en csv by PL-sql. */
	protected static final String QUERY_STRUCTURA = FrontProperty
			.getPropertyValue("catalog.report.csv.catalog.financialSources");

	/** The validate pass. */
	private String validatePass;

	/** The activa campos. */
	private boolean activaCampos = Boolean.TRUE;

	/** The active liga. */
	private boolean activeLiga = Boolean.TRUE;

	/** The active clve. */
	private boolean activeClve = Boolean.TRUE;

	/** The active nombre. */
	private boolean activeNombre = Boolean.TRUE;

	/** The max length. */
	private Integer maxLength;

	/** The patter expretion. */
	private String patterExpretion;

	/** The mask. */
	private String mask;

	/** The municipal. */
	private boolean municipal;

	/**
	 * Gets the patter expretion.
	 *
	 * @return the patter expretion
	 */
	public String getPatterExpretion() {
		return patterExpretion;
	}

	/**
	 * Sets the patter expretion.
	 *
	 * @param patterExpretion the new patter expretion
	 */
	public void setPatterExpretion(String patterExpretion) {
		this.patterExpretion = patterExpretion;
	}

	/**
	 * Gets the mask.
	 *
	 * @return the mask
	 */
	public String getMask() {
		return mask;
	}

	/**
	 * Sets the mask.
	 *
	 * @param mask the new mask
	 */
	public void setMask(String mask) {
		this.mask = mask;
	}

	/**
	 * Checks if is activa campos.
	 *
	 * @return true, if is activa campos
	 */
	public boolean isActivaCampos() {
		return activaCampos;
	}

	/**
	 * Sets the activa campos.
	 *
	 * @param activaCampos the new activa campos
	 */
	public void setActivaCampos(boolean activaCampos) {
		this.activaCampos = activaCampos;
	}

	/**
	 * Checks if is active liga.
	 *
	 * @return true, if is active liga
	 */
	public boolean isActiveLiga() {
		return activeLiga;
	}

	/**
	 * Sets the active liga.
	 *
	 * @param activeLiga the new active liga
	 */
	public void setActiveLiga(boolean activeLiga) {
		this.activeLiga = activeLiga;
	}

	/**
	 * Checks if is active clve.
	 *
	 * @return true, if is active clve
	 */
	public boolean isActiveClve() {
		return activeClve;
	}

	/**
	 * Sets the active clve.
	 *
	 * @param activeClve the new active clve
	 */
	public void setActiveClve(boolean activeClve) {
		this.activeClve = activeClve;
	}

	/**
	 * Checks if is active nombre.
	 *
	 * @return true, if is active nombre
	 */
	public boolean isActiveNombre() {
		return activeNombre;
	}

	/**
	 * Sets the active nombre.
	 *
	 * @param activeNombre the new active nombre
	 */
	public void setActiveNombre(boolean activeNombre) {
		this.activeNombre = activeNombre;
	}

	/**
	 * Checks if is municipal.
	 *
	 * @return true, if is municipal
	 */
	public boolean isMunicipal() {
		return municipal;
	}

	/**
	 * Sets the municipal.
	 *
	 * @param municipal the new municipal
	 */
	public void setMunicipal(boolean municipal) {
		this.municipal = municipal;
	}

	/**
	 * Gets the max length.
	 *
	 * @return the max length
	 */
	public Integer getMaxLength() {
		return maxLength;
	}

	/**
	 * Sets the max length.
	 *
	 * @param maxLength the new max length
	 */
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	/** Componente de servicio. */
	@ManagedProperty(value = "#{fuentefRepository}")
	private FuentefRepository fuentefRepository;

	/** The repository custom. */
	@ManagedProperty(value = "#{repositoryImpl}")
	protected RepositoryCustom repositoryCustom;

	/** Campo requerido Clave de Contrato. */
	// @Value("${catalog.message.field.required.clvfte}")
	private static final String FIELD_REQUIRED_CLVFTE = FrontProperty
			.getPropertyValue("catalog.message.field.required.clvfte");

	/** The Constant FIELD_REQUIRED_LIGA. */
	private static final String FIELD_REQUIRED_LIGA = FrontProperty
			.getPropertyValue("catalog.message.field.required.liga");

	/** Nombre del reporte en texto plano. */
	// @Value("${file.name.report.txt.fuentesFinanciamiento}")
	protected static final String REPORT_NAME_PLAIN_TEXT = FrontProperty
			.getPropertyValue("file.name.report.txt.fuentesFinanciamiento");

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.financial.sources}")
	protected static final String HEADERS_REPORT_TEXT_PLAIN = FrontProperty
			.getPropertyValue("header.text.plain.financial.sources");

	/** Ruta donde se encuentra el archivo jasper del reporte. */
	// @Value("${view.report.path.jasper.catalog_financial_sources}")
	private static final String REPORT_PATH_JASPER_FILE = FrontProperty
			.getPropertyValue("view.report.path.jasper.catalog_financial_sources");

	/**
	 * Sets the fuentef repository.
	 *
	 * @param fuentefRepository the new fuentef repository
	 */
	public void setFuentefRepository(FuentefRepository fuentefRepository) {
		this.fuentefRepository = fuentefRepository;
	}

	/**
	 * Gets the repository custom.
	 *
	 * @return the repository custom
	 */
	public RepositoryCustom getRepositoryCustom() {
		return repositoryCustom;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#setRepositoryCustom(com.gem.sistema.business.repository.catalogs.RepositoryCustom)
	 */
	public void setRepositoryCustom(RepositoryCustom repositoryCustom) {
		this.repositoryCustom = repositoryCustom;
	}

	/**
	 * Gets the fuentef repository.
	 *
	 * @return the fuentef repository
	 */
	public FuentefRepository getFuentefRepository() {
		return fuentefRepository;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#getValidatePass()
	 */
	public String getValidatePass() {
		return validatePass;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#setValidatePass(java.lang.String)
	 */
	public void setValidatePass(String validatePass) {
		this.validatePass = validatePass;
	}

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct ");
		setBeanFind(new Fuentef());
		setList(new ArrayList<Fuentef>());
		setListNew(new ArrayList<Fuentef>());
		this.reportNameTextPlain = REPORT_NAME_PLAIN_TEXT;
		this.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;
		sSqlCsv = QUERY_STRUCTURA;
		AND_ID_SECTOR = " and idsector = " + this.getUserDetails().getIdSector();
		this.setValidatePass("");

		maxLength = 1 == this.getUserDetails().getIdSector() ? 6 : 8;
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogFinancialSources ");
		getBeanFind().setClvfte(null);
		getBeanFind().setNombref(null);
		restartData();

		municipal = getUserDetails().getIdSector() == 1;
	}

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {
		LOGGER.info(":: Editando Fila catalogFinancialSources " + event.getObject());
		activeLiga = Boolean.TRUE;
		activeClve = Boolean.TRUE;

		final Fuentef catalog = (Fuentef) event.getObject();
		catalog.setIdsector(this.getUserDetails().getIdSector());
		catalog.setUserid(this.getUserDetails().getUsername());
		catalog.setFeccap(Calendar.getInstance().getTime());
		catalog.setCapfuen(this.getUserDetails().getUsername());

		List<Fuentef> liga = fuentefRepository.findByLigaAndIdsector(catalog.getLiga(),
				this.getUserDetails().getIdSector());
		if (liga.size() == 0 || catalog.getId() > 0) {
			List<Fuentef> cveft = fuentefRepository.findByClvfteAndIdsector(catalog.getClvfte(),
					this.getUserDetails().getIdSector());
			if (cveft.size() == 0 || catalog.getId() > 0) {
				Boolean isLigaCentral = (this.getUserDetails().getIdSector() == 2);
				String msgError = null;
				if (isLigaCentral && NumberUtils.isDigits(catalog.getLiga())
						&& NumberUtils.toInt(catalog.getLiga()) < 101) {
					msgError = "El campo liga debe ser mayor o igual a 101";
				}
				Boolean isLigaMunicipal = (this.getUserDetails().getIdSector() == 1);
				if (isLigaCentral && NumberUtils.isDigits(catalog.getLiga())
						&& NumberUtils.toInt(catalog.getLiga()) < 1) {
					msgError = "El campo liga debe ser mayor o igual a 1";
				}

				if (msgError == null && (isLigaCentral || isLigaMunicipal)) {
					if (validateUpdateOrSave(catalog)) {
						/*
						 * if (this.getUserDetails().getIdSector() == 1) {
						 * //catalog.setLiga(StringUtils.leftPad(catalog.getLiga
						 * (), 3, "0")); }
						 */
						decideLastLevel(catalog);
						catalog.setIdRef((long) 0);
						catalog.setNivfte(saveLevel(catalog.getClvfte(), this.getUserDetails().getIdSector()));
						executeOperationSaveOrUpdate(catalog, fuentefRepository);
						this.setActiveLiga(Boolean.TRUE);
						restartData();
						RequestContext.getCurrentInstance()
								.execute("document.getElementById('form1:hideButton').click();");
					} else {
						generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_UPDATE_UNIQUE
								.concat(FIELD_REQUIRED_CLVFTE).concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
						activateRowEdit(catalog.getIndex());
						this.setActiveLiga(Boolean.FALSE);
					}
				} else {

					generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, msgError);
					activateRowEdit(catalog.getIndex());
					this.setActiveLiga(Boolean.FALSE);
				}

			} else {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_UPDATE_UNIQUE
						.concat(FIELD_REQUIRED_CLVFTE).concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
				activateRowEdit(catalog.getIndex());
				this.setActiveLiga(Boolean.FALSE);
			}
		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_UPDATE_UNIQUE
					.concat(FIELD_REQUIRED_LIGA).concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
			this.activeLiga = Boolean.FALSE;
			activateRowEdit(catalog.getIndex());
			// this.setActiveLiga(Boolean.FALSE);
		}
	}

	/**
	 * On row edit init.
	 *
	 * @param event the event
	 */
	public void onRowEditInit(final RowEditEvent event) {
		final Fuentef catalog = (Fuentef) event.getObject();
		activateRowEdit(catalog.getIndex());
	}

	/**
	 * Clean txt.
	 */
	public void cleanTxt() {
		this.getBeanFind().setClvfte(null);
		this.getBeanFind().setLiga(null);
		this.getBeanFind().setNombref(null);
	}

	/**
	 * Decide last level.
	 *
	 * @param catalog the catalog
	 */
	private void decideLastLevel(final Fuentef catalog) {
		final int level = getFieldNivFte(catalog.getClvfte());
		/*
		 * if (level == LEVEL_FOUR) { String fieldLiga = (String)
		 * repositoryCustom.obtainOne(SQL_MAX_LIGA); if
		 * (StringUtils.isEmpty(fieldLiga)) { fieldLiga = FIRST_VALUE_LIGA; }
		 * else { fieldLiga = String.valueOf(Integer.valueOf(fieldLiga) + ONE);
		 * } LOGGER.info(":: LIGA: " + fieldLiga); catalog.setLiga(fieldLiga); }
		 */
		catalog.setNivfte(level);
	}

	/**
	 * Gets the field niv fte.
	 *
	 * @param clvfte the clvfte
	 * @return the field niv fte
	 */
	private int getFieldNivFte(final String clvfte) {
		int result = ONE;
		for (int index = TWO; index <= clvfte.length() - TWO; index += TWO) {
			if (!clvfte.substring(index, index + TWO).equals(EMPTY_LEVEL)) {
				result++;
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
	private boolean validateUpdateOrSave(final Fuentef catalog) {
		LOGGER.info("catalog.getClvfte(): " + catalog.getClvfte());
		// if (this.getUserDetails().getIdSector() == 1) {
		catalog.setLiga(StringUtils.leftPad(catalog.getLiga(), 3, "0"));
		// }

		/*
		 * final List<Fuentef> validateUnique =
		 * fuentefRepository.findAll(FuentefPredicates
		 * .byIdSectorAndLiga(this.getUserDetails().getIdSector(),
		 * catalog.getLiga(), catalog.getClvfte()));
		 */
		List<Fuentef> validateUnique = fuentefRepository.findByClvfteAndIdsector(catalog.getClvfte(),
				this.getUserDetails().getIdSector());

		return (isValidSaveOrUpdate(validateUnique, catalog));
	}

	/**
	 * Elimina un registro.
	 */
	public void delete() {
		LOGGER.info(":: Borrar registro catalogFinancialSources " + getBeanSelected());
		if (BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {
			fuentefRepository.delete(getBeanSelected());
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
		LOGGER.info(":: Buscar filas catalogFinancialSources " + getBeanFind());
		this.getBeanFind().setIdsector(this.getUserDetails().getIdSector());
		addList(IteratorUtils.toList(
				fuentefRepository.findAll(FuentefPredicates.byComposite(this.getBeanFind()), FUENTEF_ASC).iterator()));
		restartFilteredList();
		LOGGER.info(":: Resultado de busqueda catalogFinancialSources " + getList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void consultAll() {
		setList(IteratorUtils.toList(fuentefRepository
				.findAll(FuentefPredicates.byIdSector(this.getUserDetails().getIdSector()), FUENTEF_ASC).iterator()));
		restartFilteredList();
	}

	/** The Constant FUENTEF_ASC. */
	private static final Sort FUENTEF_ASC = new Sort(Sort.Direction.ASC, "liga", "clvfte");

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

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#onRowCancel(org.primefaces.event.RowEditEvent)
	 */
	@Override
	public void onRowCancel(RowEditEvent event) {
		this.setActiveLiga(Boolean.TRUE);
		restartData();
		super.onRowCancel(event);
	}

	/**
	 * Save rows.
	 */
	public void saveRows() {
		LOGGER.info("entra a crear una nueva linea....");
		Fuentef fuentef = this.getBeanSelected();
		fuentef = (Fuentef) UtilJPA.fillPropertyStringIfNull(fuentef);
		fuentef.setIdsector(this.getUserDetails().getIdSector());
		fuentef.setUserid(this.getUserDetails().getUsername());
		fuentef.setCapfuen(this.getUserDetails().getUsername());
		fuentef.setFeccap(Calendar.getInstance().getTime());

		if (validateUpdateOrSave(fuentef)) {

			decideLastLevel(fuentef);
			executeOperationSaveOrUpdate(fuentef, fuentefRepository);

		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_UPDATE_UNIQUE
					.concat(FIELD_REQUIRED_CLVFTE).concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
			activateRowEdit(fuentef.getIndex());
		}
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
			activeLiga = Boolean.FALSE;
			activeClve = Boolean.FALSE;
			if (this.getUserDetails().getIdSector() == 1) {
				mask = "999999";

			} else if (this.getUserDetails().getIdSector() == 2) {
				mask = "99999999";

			}

		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Password incorrecto");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		this.setValidatePass("");

	}

	/**
	 * Save level.
	 *
	 * @param cveFuente the cve fuente
	 * @param idSector the id sector
	 * @return the integer
	 */
	public Integer saveLevel(String cveFuente, Integer idSector) {
		Integer level = null;
		if (cveFuente.length() == 8 && idSector == 2) {
			if (cveFuente.substring(2, 8).equals("00000")) {
				level = 1;
			} else if (cveFuente.substring(3, 8).equals("0000")) {
				level = 2;
			} else if (cveFuente.substring(4, 8).equals("000")) {
				level = 3;
			} else if (cveFuente.substring(5, 8).equals("00")) {
				level = 4;
			} else {
				level = 4;
			}
		} else if (cveFuente.length() >= 5 && idSector == 1) {
			level = 4;
		}
		return level;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#insertNewRow()
	 */
	@Override
	public void insertNewRow() throws InstantiationException, IllegalAccessException {
		this.setActiveLiga(Boolean.FALSE);
		super.insertNewRow();
	}

	/**
	 * Show succes message.
	 */
	public void showSuccesMessage() {
		consultList();
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_EDITION_SUCCESS);
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

		MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("i_header", HEADERS_REPORT_CSV_PLAIN)
				.addValue("i_query", this.getFileCsvByPl() + " order by liga, clvfte")
				.addValue("i_file_name", REPORT_NAME_PLAIN_CSV);

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
