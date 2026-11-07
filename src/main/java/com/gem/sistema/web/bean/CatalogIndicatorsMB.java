package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.MIN_LENGTH_FIELD_CAMPO8_INDICATORS;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.collections.ListUtils;
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

import com.gem.sistema.business.domain.Indicadores;
import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.predicates.IndicadorPredicate;
import com.gem.sistema.business.predicates.MuniNepPredicates;
import com.gem.sistema.business.repository.catalogs.IndicadoresRepository;
import com.gem.sistema.business.repository.catalogs.MuniNepRepository;
import com.gem.sistema.web.util.FrontProperty;
import com.mysema.query.types.Predicate;


// TODO: Auto-generated Javadoc
/**
 * The Class CatalogIndicatorsMB.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@ManagedBean
@ViewScoped
public class CatalogIndicatorsMB extends CommonCatalogMB<Indicadores> implements Serializable {

	/** Serial default. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = FrontProperty
			.getPropertyValue("catalog.report.procedure.name.generic");
	
	/** The csv by pl. */
	private StreamedContent csvByPl;

	/** Nombre del reporte en csv by PL-sql. */
	// @Value("${file.name.report.txt.estructuraProgramatica}")
	protected static final String REPORT_NAME_PLAIN_CSV = "catalogIndicators.csv";
	
	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.program.estructure}")
	protected static final String HEADERS_REPORT_CSV_PLAIN = FrontProperty
			.getPropertyValue("header.csv.catalog.indicators");

	/** The Constant QUERY_INDICATORS. */
	protected static final String QUERY_INDICATORS= FrontProperty
			.getPropertyValue("catalog.report.csv.catalog.indicators");


	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogIndicatorsMB.class);

	/** The Constant FIELD_ORDER_BY_CLVIND. */
	private static final String FIELD_ORDER_BY_CLVIND = "cveind";

	/** The Constant field_descripcion. */
	private static final String field_descripcion = FrontProperty.getPropertyValue("catalog.message.field.descripcion");

	/**
	 * Almacena los registros del catalogo de estructura programatica filtrados.
	 */
	private List<Muninep> filteredProgramStructureSelected;

	/** Registros del catalogo de estructura programatica. */
	private List<Muninep> programsStructure;

	/** Componente de servicio. */
	@ManagedProperty(value = "#{muniNepRepository}")
	private MuniNepRepository muniNepRepository;

	/**
	 * Registro de indicadores seleccionado.
	 */
	private Indicadores rowSelected;

	/** Fila seleccioanda del catalogo de estructura programatica. */

	private Muninep programStructureSelected;

	/** Componente de servicio. */
	@ManagedProperty(value = "#{indicadoresRepository}")
	private IndicadoresRepository indicadoresRepository;

	/** Campo requerido Clave de Contrato. */
	// @Value("${catalog.message.field.required.cveind}")
	private static final String FIELD_REQUIRED_CVEIND = FrontProperty
			.getPropertyValue("catalog.message.field.required.cveind");

	/** Nombre del reporte en texto plano. */
	// @Value("${file.name.report.txt.indicadores}")
	protected static final String REPORT_NAME_PLAIN_TEXT = FrontProperty
			.getPropertyValue("file.name.report.txt.indicadores");

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.indicators}")
	protected static final String HEADERS_REPORT_TEXT_PLAIN = FrontProperty
			.getPropertyValue("header.text.plain.indicators");

	/** The Constant QUERY_PROGRAMS. */
	protected static final String QUERY_PROGRAMS = FrontProperty
			.getPropertyValue("catalog.report.csv.catalog.indicators");

	
	
	/** Mensaje de error del campo programa. */
	// @Value("${catalog.message.error.clvfun}")
	protected static final String ERROR_MESSAGE_FIELD_CLVFUN = FrontProperty
			.getPropertyValue("catalog.message.error.clvfun");

	/** Mensaje de error del campo programa. */
	// @Value("${catalog.message.data.required.save}")
	protected static final String ERROR_MESSAGE_DATA_REQUIRED = FrontProperty
			.getPropertyValue("catalog.message.data.required.save");

	/** Mensaje de error para la longitud de clvfun. */
	// @Value("${catalog.message.error.clvfun.length}")
	protected static final String ERROR_MESSAGE_FIELD_CLVFUN_LENGTH = FrontProperty
			.getPropertyValue("catalog.message.error.clvfun.length");

	/** Campo clvefun. */
	// @Value("${view.catalog.indicators.cvefun}")
	protected static final String FIELD_CVEFUN = FrontProperty.getPropertyValue("view.catalog.indicators.cvefun");

	/** Ruta donde se encuentra el archivo jasper del reporte. */
	// @Value("${view.report.path.jasper.catalog_indicators}")
	private static final String REPORT_PATH_JASPER_FILE = FrontProperty
			.getPropertyValue("view.report.path.jasper.catalog_indicators");

	//private Indicadores indicadorAut;

	/**
	 * Sets the muni nep repository.
	 *
	 * @param muniNepRepository the new muni nep repository
	 */
	public void setMuniNepRepository(MuniNepRepository muniNepRepository) {
		this.muniNepRepository = muniNepRepository;
	}

	/**
	 * Sets the indicadores repository.
	 *
	 * @param indicadoresRepository the new indicadores repository
	 */
	public void setIndicadoresRepository(IndicadoresRepository indicadoresRepository) {
		this.indicadoresRepository = indicadoresRepository;
	}

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct catalogIndicatorsMB " + this + "::" + getBeanFind() + "::" + getList());

		setBeanFind(new Indicadores());
		setList(new ArrayList<Indicadores>());
		setListNew(new ArrayList<Indicadores>());
		setQueryOrderBy(Boolean.TRUE);
		this.reportNameTextPlain = REPORT_NAME_PLAIN_TEXT;
		this.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;
		sSqlCsv = QUERY_INDICATORS;
		AND_ID_SECTOR = " and idsector = " + this.getUserDetails().getIdSector();
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogIndicatorsMB  ");
		getBeanFind().setCveind(null);
		getBeanFind().setNomind(null);
		getBeanFind().setClvFun(null);
		getBeanFind().setPeriodo(null);
		restartData();
	}

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {
		LOGGER.info(":: Editando Fila catalogIndicatorsMB onRowEdit " + event.getObject());
		final Indicadores catalog = (Indicadores) event.getObject();
		final StringBuilder errorMsg = new StringBuilder();
		if(!catalog.getNomind().equals(null) || !catalog.getNomind().equals("") ) {
			if(!catalog.getPeriodo().equals(null) || !catalog.getPeriodo().equals("")) {
				if (isValidCapturedData(catalog, errorMsg) && validateUpdateOrSave(catalog)) {
		
					executeOperationSaveOrUpdate(catalog, indicadoresRepository);
				} else {
					if (errorMsg.length() == ZERO) {
						generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_UPDATE_UNIQUE
								.concat(FIELD_REQUIRED_CVEIND).concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
					} else {
						generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, errorMsg.toString());
					}
					activateRowEdit(catalog.getIndex());
				}
			} else {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "El Periodo es obligatorio");
			}
		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "El Nombre del Indicador no debe de estar vacio");
		}

	}

	/**
	 * Creates the row.
	 */
	public void createRow() {
		LOGGER.info(":: Editando Fila catalogIndicatorsMB createRow " + this.getBeanSelected());
		final Indicadores catalog = this.getRowSelected();
		catalog.setIdsector(this.getUserDetails().getIdSector());
		catalog.setUserid(this.getUserDetails().getUsername());
		catalog.setIdRef(0L);
		catalog.setDescripcion(field_descripcion);
		catalog.setClvDep("");
		catalog.setClvFin("");
		final StringBuilder errorMsg = new StringBuilder();
		if(catalog.getNomind() != null ){
			if(catalog.getPeriodo() != null){
				if (isValidCapturedData(catalog, errorMsg) && validateUpdateOrSave(catalog)) {
					executeOperationSaveOrUpdate(catalog, indicadoresRepository);
					restartDataOperationDelete();
				} else {
					if (errorMsg.length() == ZERO) {
						generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_UPDATE_UNIQUE
								.concat(FIELD_REQUIRED_CVEIND).concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
					} else {
						generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, errorMsg.toString());
					}
					activateRowEdit(catalog.getIndex());
				}
			} else {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "El periodo es obligatorio");
				activateRowEdit(catalog.getIndex());
			}
		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "El nombre del indicador es obligatorio");
			activateRowEdit(catalog.getIndex());
		}
	}

	/**
	 * Checks if is valid captured data.
	 *
	 * @param catalog the catalog
	 * @param errorMsg the error msg
	 * @return true, if is valid captured data
	 */
	// private boolean isValidFieldClvfun(final String clvfun, final
	// StringBuilder errorMsg) {
	// final boolean result;
	// if(clvfun.length()==LENGTH_CLVFUN) {
	// if(CollectionUtils.isEmpty(muniNepRepository.findByCampo0AndCampo1AndCampo2AndCampo3AndCampo4(clvfun.substring(0,2),
	// clvfun.substring(2,4), clvfun.substring(4,6), clvfun.substring(6,8),
	// clvfun.substring(8,10)))) {
	// result = Boolean.FALSE;
	// errorMsg.append(ERROR_MESSAGE_FIELD_CLVFUN);
	// } else {
	// result = Boolean.TRUE;
	// }
	// } else{
	// result = Boolean.FALSE;
	// errorMsg.append(ERROR_MESSAGE_FIELD_CLVFUN_LENGTH);
	// }
	// return result;
	// }

	/**
	 * 
	 * @param catalog
	 * @return
	 */
	private boolean isValidCapturedData(final Indicadores catalog, final StringBuilder errorMsg) {
		final boolean result;
		if (StringUtils.isEmpty(catalog.getClvFun())
				|| BooleanUtils.negate(catalog.getClvFun().length() == MIN_LENGTH_FIELD_CAMPO8_INDICATORS)) {
			errorMsg.append("Se debe capturar el campo: ClvFun");
			result = Boolean.FALSE;
		} else {
			result = CollectionUtils.isNotEmpty(muniNepRepository.findByCampo7(catalog.getClvFun()));
		}
		if (errorMsg.length() == ZERO && BooleanUtils.negate(result)) {
			errorMsg.append("La informacion capturada en ClvFun no existe en la Base de datos.");
		}
		return result;
	}

	/**
	 * Validate update or save.
	 *
	 * @param catalog the catalog
	 * @return true, if successful
	 */
	private boolean validateUpdateOrSave(final Indicadores catalog) {

		return isValidSaveOrUpdate(IteratorUtils.toList(indicadoresRepository
				.findByCveindAndIdsector(catalog.getCveind(),this.getUserDetails().getIdSector()).iterator()), catalog);
	}

	/**
	 * Elimina un registro.
	 */
	public void delete() {
		LOGGER.info(":: Borrar registro catalogIndicatorsMB " + getBeanSelected());
		if (BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {
			indicadoresRepository.delete(getBeanSelected());
		}
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_DELETED_RECORD);
		restartDataOperationDelete();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@Override
	protected void consultAll() {
		final Predicate predicate = IndicadorPredicate.findIdSector(this.getUserDetails().getIdSector());
		setList(IteratorUtils.toList(indicadoresRepository.findAll(predicate, sortByIdSectorAsc()).iterator()));
		restartFilteredList();
	}

	/**
	 * Sort by id sector asc.
	 *
	 * @return the sort
	 */
	private static Sort sortByIdSectorAsc() {
		return new Sort(Sort.Direction.ASC, "cveind");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultList()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void consultList() {
		LOGGER.info(":: Buscar filas catalogIndicatorsMB " + getBeanFind());
		this.getBeanFind().setIdsector(this.getUserDetails().getIdSector());
		addList((List<Indicadores>) repositoryCustom.findByFiltersOrderBy(getBeanFind(), FIELD_ORDER_BY_CLVIND));
		restartFilteredList();
		LOGGER.info(":: Resultado de busqueda catalogIndicatorsMB " + getList());
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
	 * Consult program structure.
	 */
	public void consultProgramStructure() {
		LOGGER.info(":: Consultar estructura programatica ");
		final Predicate predicate = MuniNepPredicates.getPredicateToIndicators2();
		programsStructure = (List<Muninep>) muniNepRepository.findAll(predicate);
		restartFilteredProgramStructure();
	}

	/**
	 * Restart filtered program structure.
	 */
	private void restartFilteredProgramStructure() {
		if (filteredProgramStructureSelected == null) {
			return;
		}
		filteredProgramStructureSelected.clear();
		filteredProgramStructureSelected.addAll(programsStructure);
	}

	/**
	 * Change program structure.
	 */
	public void changeProgramStructure() {
		LOGGER.info(":: Cambiar estructura programatica " + rowSelected + ":: ");
		rowSelected.setClvFun(programStructureSelected.getCampo8());
		activateRowEdit(rowSelected.getIndex());
		final RequestContext context = RequestContext.getCurrentInstance();
		context.update(VIEW_CATALOG_FORM1_OBJECTS + rowSelected.getIndex() + FIELD_CVEFUN);
	}

	/**
	 * To autocomplete Indicadores for cve.
	 *
	 * @param query the query
	 * @return the list
	 */
	public List<Indicadores> completeIndicadores(String query) {
		consultAll();
		List<Indicadores> allIndicadores = getList();
		List<Indicadores> filteredIndicador = new ArrayList<Indicadores>();
		String idIndicador = "";
		for (int i = 0; i < allIndicadores.size(); i++) {
			Indicadores skin = allIndicadores.get(i);
			idIndicador = String.valueOf(skin.getCveind());
			if (idIndicador != null && idIndicador.toLowerCase().startsWith(query)) {
				filteredIndicador.add(skin);
			}
		}
		return filteredIndicador;
	}

	/**
	 * To autocomplete Indicadores for cve.
	 *
	 * @param query the query
	 * @return the list
	 */
	public List<Indicadores> completeIndicadoresNombre(String query) {
		consultAll();
		List<Indicadores> allIndicadores = getList();
		List<Indicadores> filteredIndicador = new ArrayList<Indicadores>();
		for (int i = 0; i < allIndicadores.size(); i++) {
			Indicadores skin = allIndicadores.get(i);
			if (skin.getNomind() != null && skin.getNomind().toLowerCase().startsWith(query)) {
				filteredIndicador.add(skin);
			}
		}
		return filteredIndicador;
	}

	
	

	 /*
	public void findValuesCatalog1() {
		if (getBeanFind().getCveind() != null
				|| getBeanFind().getNomind() != null 
				|| getBeanFind().getPeriodo() != null) {
			//setBeanFind(getIndicadorAut());
			super.findValuesCatalog();
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Los campos de busquena no contienen información");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}
*/
	/**
 	 * To autocomplete Indicadores for Periodo.
 	 *
 	 * @param query the query
 	 * @return the list
 	 */
	public List<Indicadores> completeIndicadoresPeriodo(String query) {
		consultAll();
		List<Indicadores> allIndicadores = getList();
		List<Indicadores> filteredIndicador = new ArrayList<Indicadores>();
		for (int i = 0; i < allIndicadores.size(); i++) {
			Indicadores skin = allIndicadores.get(i);
			if (skin.getPeriodo() != null && skin.getPeriodo().toLowerCase().startsWith(query)) {
				filteredIndicador.add(skin);
			}
		}
		return filteredIndicador;
	}

	/**
	 * Activate row edit.
	 */
	public void activateRowEdit() {
		super.activateRowEdit(rowSelected.getIndex());
	}

	/**
	 * Gets the row selected.
	 *
	 * @return the rowSelected
	 */
	public Indicadores getRowSelected() {
		return rowSelected;
	}

	/**
	 * Sets the row selected.
	 *
	 * @param rowSelected            the rowSelected to set
	 */
	public void setRowSelected(final Indicadores rowSelected) {
		this.rowSelected = rowSelected;
	}

	/**
	 * Gets the program structure selected.
	 *
	 * @return the programStructureSelected
	 */
	public Muninep getProgramStructureSelected() {
		return programStructureSelected;
	}

	/**
	 * Sets the program structure selected.
	 *
	 * @param programStructureSelected            the programStructureSelected to set
	 */
	public void setProgramStructureSelected(final Muninep programStructureSelected) {
		if (BooleanUtils.negate(rowSelected == null) && BooleanUtils.negate(programStructureSelected == null)) {
			this.programStructureSelected = programStructureSelected;
		}
	}

	/**
	 * Gets the filtered program structure selected.
	 *
	 * @return the filteredProgramStructureSelected
	 */
	public List<Muninep> getFilteredProgramStructureSelected() {
		return filteredProgramStructureSelected;
	}

	/**
	 * Sets the filtered program structure selected.
	 *
	 * @param filteredProgramStructureSelected            the filteredProgramStructureSelected to set
	 */
	public void setFilteredProgramStructureSelected(List<Muninep> filteredProgramStructureSelected) {
		this.filteredProgramStructureSelected = filteredProgramStructureSelected;
	}

	/**
	 * Gets the programs structure.
	 *
	 * @return the programsStructure
	 */
	public List<Muninep> getProgramsStructure() {
		return programsStructure;
	}

	/**
	 * Sets the programs structure.
	 *
	 * @param programsStructure            the programsStructure to set
	 */
	public void setProgramsStructure(List<Muninep> programsStructure) {
		this.programsStructure = programsStructure;
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
	 * On clvdaa row dbl clck select.
	 */
	/*
	public Indicadores getIndicadorAut() {
		return indicadorAut;
	}

	public void setIndicadorAut(Indicadores indicadorAut) {
		this.indicadorAut = indicadorAut;
	}
*/
	public void onClvdaaRowDblClckSelect() {

		this.rowSelected.setClvFun(programStructureSelected.getCampo7());

		activateRowEdit(rowSelected.getIndex());

		final RequestContext context = RequestContext.getCurrentInstance();
		context.update(VIEW_CATALOG_FORM1_OBJECTS + rowSelected.getIndex() + ":clvFun");

		LOGGER.info(":: Cambiar estructura programatica_2***: " + rowSelected);

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
