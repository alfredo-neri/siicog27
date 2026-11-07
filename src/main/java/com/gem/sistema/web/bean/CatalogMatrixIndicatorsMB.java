package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.MIN_LENGTH_FIELD_CAMPO8_MATRIX_INDICATORS;
import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Mir;
import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.dto.IndCapturaDTO;
import com.gem.sistema.business.predicates.MirPredicates;
import com.gem.sistema.business.predicates.MuniNepPredicates;
import com.gem.sistema.business.repository.catalogs.MirRepository;
import com.gem.sistema.business.repository.catalogs.MuniNepRepository;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;
import com.gem.sistema.web.util.FrontProperty;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class CatalogMatrixIndicatorsMB.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@ManagedBean
@ViewScoped
public class CatalogMatrixIndicatorsMB extends CommonCatalogMB<Mir> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogMatrixIndicatorsMB.class);

	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = FrontProperty
			.getPropertyValue("catalog.report.procedure.name.generic");
	
	/** The csv by pl. */
	private StreamedContent csvByPl;

	/** Nombre del reporte en csv by PL-sql. */
	// @Value("${file.name.report.txt.estructuraProgramatica}")
	protected static final String REPORT_NAME_PLAIN_CSV = "catalogMatrixIndicators.csv";
	
	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.program.estructure}")
	protected static final String HEADERS_REPORT_CSV_PLAIN = FrontProperty
			.getPropertyValue("header.csv.catalog.matrix.indicators");

	/**
	 * Lista de programas.
	 */
	// private List<Muninep> programs;
	private List<Xcatpro> programs;

	/**
	 * Almacena los registros filtrados del catalogo de programas.
	 */
	// private List<Muninep> filteredPrograms;
	private List<Xcatpro> filteredPrograms;

	/** The active data. */
	private boolean activeData = Boolean.FALSE;

	/** Registro seleccionado. */
	private Mir rowSelected;

	/**
	 * Programa seleccionado.
	 */
	// private Muninep programSelected;
	private Xcatpro programSelected;
	
	/** Componente de servicio. */
	@ManagedProperty(value = "#{mirRepository}")
	private MirRepository mirRepository;

	/** Repositorio de muninep. */
	@ManagedProperty(value = "#{muniNepRepository}")
	private MuniNepRepository muniNepRepository;

	/** The xcatpro repository. */
	@ManagedProperty(value = "#{xcatproRepository}")
	private XcatproRepository xcatproRepository;

	/** Campo requerido Codigo. */
	// @Value("${catalog.message.field.required.codigo}")
	private static final String FIELD_REQUIRED_CODIGO = FrontProperty
			.getPropertyValue("catalog.message.field.required.codigo");

	/** Nombre del reporte en texto plano. */
	// @Value("${file.name.report.txt.matrizIndicadores}")
	protected static final String REPORT_NAME_PLAIN_TEXT = FrontProperty
			.getPropertyValue("file.name.report.txt.matrizIndicadores");

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.matrix.indicators}")
	protected static final String HEADERS_REPORT_TEXT_PLAIN = FrontProperty
			.getPropertyValue("header.text.plain.matrix.indicators");

	/** The Constant HEADERS_REPORT_CSV. */
	private static final String HEADERS_REPORT_CSV = FrontProperty
			.getPropertyValue("header.csv.catalog.matrix.indicators");

	/** The Constant QUERY_MATRIZ. */
	protected static final String QUERY_MATRIZ = FrontProperty
			.getPropertyValue("catalog.report.csv.catalog.matrix.indicators");

	/** Mensaje de error del campo programa. */
	// @Value("${catalog.message.error.programa}")
	protected static final String ERROR_MESSAGE_FIELD_PROGRAMA = FrontProperty
			.getPropertyValue("catalog.message.error.programa");

	/** Ruta donde se encuentra el archivo jasper del reporte. */
	// @Value("${view.report.path.jasper.catalog_matrix_indicators}")
	private static final String REPORT_PATH_JASPER_FILE = FrontProperty
			.getPropertyValue("view.report.path.jasper.catalog_matrix_indicators");

	/**
	 * Sets the mir repository.
	 *
	 * @param mirRepository the new mir repository
	 */
	public void setMirRepository(MirRepository mirRepository) {
		this.mirRepository = mirRepository;
	}

	/**
	 * Sets the muni nep repository.
	 *
	 * @param muniNepRepository the new muni nep repository
	 */
	public void setMuniNepRepository(MuniNepRepository muniNepRepository) {
		this.muniNepRepository = muniNepRepository;
	}

	/**
	 * Checks if is active data.
	 *
	 * @return true, if is active data
	 */
	public boolean isActiveData() {
		return activeData;
	}

	/**
	 * Sets the active data.
	 *
	 * @param activeData the new active data
	 */
	public void setActiveData(boolean activeData) {
		this.activeData = activeData;
	}

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct catalogMatrixIndicatorsMB " + this + "::" + getBeanFind() + "::" + getList());
		isActiveData();
		setBeanFind(new Mir());
		setList(new ArrayList<Mir>());
		setListNew(new ArrayList<Mir>());
		this.reportNameTextPlain = REPORT_NAME_PLAIN_TEXT;
		this.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;
		sSqlCsv = QUERY_MATRIZ;
		AND_ID_SECTOR= " ";
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogMatrixIndicatorsMB  ");
		getBeanFind().setPrograma(null);
		getBeanFind().setNivel(null);
		getBeanFind().setConsec(null);
		getBeanFind().setNomind(null);
		getBeanFind().setTipo(null);
		getBeanFind().setFrecuencia(null);
		getBeanFind().setCodigo(null);
		restartData();
	}

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {
		LOGGER.info(":: Editando Fila catalogMatrixIndicatorsMB  " + event.getObject());
		activeData = Boolean.FALSE;
		final Mir catalog = (Mir) event.getObject();
		final StringBuilder errorMsg = new StringBuilder();

		this.updateMir(catalog);

		if (isValidCapturedData(catalog, errorMsg) && validateUpdateOrSave(catalog)) {

			catalog.setCapturo(this.getUserDetails().getUsername());
			catalog.setFecha(Calendar.getInstance().getTime());

			// this.mirRepository.save(catalog);
			executeOperationSaveOrUpdate(catalog, mirRepository);
			// RequestContext.getCurrentInstance().execute("document.getElementById('form1:hideButton3').click();");

		} else {
			if (errorMsg.length() == ZERO) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_UPDATE_UNIQUE
						.concat(FIELD_REQUIRED_CODIGO).concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
			} else {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, errorMsg.toString());
			}
			activateRowEdit(catalog.getIndex());
		}
		activeData = Boolean.TRUE;
	}

	/**
	 * Update mir.
	 *
	 * @param catalog the catalog
	 * @return the mir
	 */
	private Mir updateMir(Mir catalog) {
		catalog.setCodigo(catalog.getPrograma() + catalog.getNivel()
				+ StringUtils.leftPad(StringUtils.EMPTY + catalog.getConsec(), 4, "0"));
		return catalog;
	}
	// /**
	// *
	// * @param programa
	// * @param errorMsg
	// * @return
	// */
	// private boolean isValidFieldPrograma(final String programa, final
	// StringBuilder errorMsg) {
	// final boolean result;
	//
	// if(CollectionUtils.isEmpty(muniNepRepository.findByCampo8(programa))) {
	// result = Boolean.FALSE;
	// errorMsg.append(ERROR_MESSAGE_FIELD_PROGRAMA);
	// } else {
	// result = Boolean.TRUE;
	// }
	// return result;
	// }

	/**
	 * Checks if is valid captured data.
	 *
	 * @param catalog the catalog
	 * @param errorMsg the error msg
	 * @return true, if is valid captured data
	 */
	private boolean isValidCapturedData(final Mir catalog, final StringBuilder errorMsg) {
		final boolean result;
		if (StringUtils.isEmpty(catalog.getPrograma())
				|| BooleanUtils.negate(catalog.getPrograma().length() == MIN_LENGTH_FIELD_CAMPO8_MATRIX_INDICATORS)) {
			errorMsg.append("Se debe capturar el campo: Programa, y debe tener una longitud de 8");
			result = Boolean.FALSE;
		} else {

			result = CollectionUtils.isNotEmpty(muniNepRepository.findByCampo7(catalog.getPrograma()));
		}
		if (errorMsg.length() == ZERO && BooleanUtils.negate(result)) {
			errorMsg.append("La informacion capturada en Programa no existe en la Base de datos.");
		}
		/**/

		return result;
	}

	/**
	 * Validate update or save.
	 *
	 * @param catalog the catalog
	 * @return true, if successful
	 */
	private boolean validateUpdateOrSave(final Mir catalog) {
		final List<Mir> validateUnique = mirRepository.findByCodigo(catalog.getCodigo());
		return isValidSaveOrUpdate(validateUnique, catalog);
	}

	/**
	 * Elimina un registro.
	 */
	public void delete() {
		LOGGER.info(":: Borrar registro catalogMatrixIndicatorsMB " + getBeanSelected());
		Integer bandera;
		if (BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {
			if (this.getBeanSelected().getNivel().equals("NF")) {
				bandera = mirRepository.isValidateCveIdInFinalidad(this.getBeanSelected().getCodigo());
				if (bandera == 0) {
					mirRepository.delete(getBeanSelected());
					restartDataOperationDelete();
					RequestContext.getCurrentInstance()
							.execute("document.getElementById('form1:hideButton2').click();");
				} else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"No se puede eliminar tiene dependencia en Finalidad");
					FacesContext.getCurrentInstance().addMessage(null, message);
				}
			} else if (this.getBeanSelected().getNivel().equals("NC")) {
				bandera = mirRepository.isValidateCveIdInFComponente(this.getBeanSelected().getCodigo());
				if (bandera == 0) {
					mirRepository.delete(getBeanSelected());
					restartDataOperationDelete();
					RequestContext.getCurrentInstance()
							.execute("document.getElementById('form1:hideButton2').click();");
				} else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"No se puede eliminar tiene dependencia en Componente");
					FacesContext.getCurrentInstance().addMessage(null, message);
				}
			} else if (this.getBeanSelected().getNivel().equals("NA")) {
				bandera = mirRepository.isValidateCveIdInActividad(this.getBeanSelected().getCodigo());
				if (bandera == 0) {
					mirRepository.delete(getBeanSelected());
					restartDataOperationDelete();
					RequestContext.getCurrentInstance()
							.execute("document.getElementById('form1:hideButton2').click();");
				} else {
					generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,
							"No se puede eliminar tiene dependencia en Actividad");
				}

			} else if (this.getBeanSelected().getNivel().equals("NP")) {
				bandera = mirRepository.isValidateCveIdInProposito(this.getBeanSelected().getCodigo());
				if (bandera == 0) {
					mirRepository.delete(getBeanSelected());
					restartDataOperationDelete();
					RequestContext.getCurrentInstance()
							.execute("document.getElementById('form1:hideButton2').click();");
				} else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"No se puede eliminar tiene dependencia en Proposito");
					FacesContext.getCurrentInstance().addMessage(null, message);
				}

			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultList()
	 */
	@Override
	protected void consultList() {
		LOGGER.info(":: Buscar filas catalogMatrixIndicatorsMB " + getBeanFind());
		addList(IteratorUtils.toList(
				mirRepository.findAll(MirPredicates.byComposite(this.getBeanFind()), MIR_PROGRAMA_ASC).iterator()));
		LOGGER.info(":: Resultado de busqueda catalogMatrixIndicatorsMB " + getList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@Override
	protected void consultAll() {
		setList(IteratorUtils.toList(mirRepository.findAll(MIR_PROGRAMA_ASC).iterator()));
	}

	/** The Constant MIR_PROGRAMA_ASC. */
	private static final Sort MIR_PROGRAMA_ASC = new Sort(Sort.Direction.ASC, "programa");

	/**
	 * Consult program structure.
	 */
	public void consultProgramStructure() {
		LOGGER.info(":: Consultar Programas ");
		programs = new ArrayList<Xcatpro>();
		List<Object> programasObj = xcatproRepository.findProgramasDialog(this.getUserDetails().getIdSector());
		for (Object o : programasObj) {
			Object obj[] = (Object[]) o;
			Xcatpro xcatpro = new Xcatpro();
			xcatpro.setClvfun((String) obj[0]);
			xcatpro.setNompro((String) obj[1]);
			programs.add(xcatpro);
		}

		restartFilteredPrograms();
	}

	/**
	 * Restart filtered programs.
	 */
	private void restartFilteredPrograms() {
		if (filteredPrograms == null) {
			return;
		}
		filteredPrograms.clear();
		filteredPrograms.addAll(programs);
	}

	/**
	 * Change program structure.
	 */
	public void changeProgramStructure() {
		LOGGER.info(":: Cambiar Programa ");
		rowSelected.setPrograma(programSelected.getClvfun());
		activateRowEdit(rowSelected.getIndex());
		final RequestContext context = RequestContext.getCurrentInstance();
		context.update(VIEW_CATALOG_FORM1_OBJECTS + rowSelected.getIndex() + ":programa");
	}

	/**
	 * Activate row edit.
	 */
	public void activateRowEdit() {
		super.activateRowEdit(rowSelected.getIndex());
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
	 * Gets the programs.
	 *
	 * @return the programs
	 */
	public List<Xcatpro> getPrograms() {
		return programs;
	}

	/**
	 * Sets the programs.
	 *
	 * @param programs            the programs to set
	 */
	public void setPrograms(List<Xcatpro> programs) {
		this.programs = programs;
	}

	/**
	 * Gets the filtered programs.
	 *
	 * @return the filteredPrograms
	 */
	public List<Xcatpro> getFilteredPrograms() {
		return filteredPrograms;
	}

	/**
	 * Sets the filtered programs.
	 *
	 * @param filteredPrograms            the filteredPrograms to set
	 */
	public void setFilteredPrograms(List<Xcatpro> filteredPrograms) {
		this.filteredPrograms = filteredPrograms;
	}

	/**
	 * Gets the row selected.
	 *
	 * @return the rowSelected
	 */
	public Mir getRowSelected() {
		return rowSelected;
	}

	/**
	 * Sets the row selected.
	 *
	 * @param rowSelected            the rowSelected to set
	 */
	public void setRowSelected(Mir rowSelected) {
		this.rowSelected = rowSelected;
	}

	/**
	 * Gets the program selected.
	 *
	 * @return the programSelected
	 */
	public Xcatpro getProgramSelected() {
		return programSelected;
	}

	/**
	 * Sets the program selected.
	 *
	 * @param programSelected            the programSelected to set
	 */
	public void setProgramSelected(final Xcatpro programSelected) {
		if (BooleanUtils.negate(rowSelected == null) && BooleanUtils.negate(programSelected == null)) {
			this.programSelected = programSelected;
		}
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

	/**
	 * Metodo para el doble click de programa.
	 *
	 * @param event the event
	 */
	public void onProgramStructureRowDblClckSelect(final SelectEvent event) {
		this.changeProgramStructure();
		// rest of your logic
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#onRowCancel(org.primefaces.event.RowEditEvent)
	 */
	@Override
	public void onRowCancel(RowEditEvent event) {
		restartData();
		super.onRowCancel(event);
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#insertRow()
	 */
	@Override
	public void insertRow() throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		super.insertRow();
	}

	/**
	 * Show succes delete.
	 */
	public void showSuccesDelete() {
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_DELETED_RECORD);
	}

	/**
	 * Show succes update.
	 */
	public void showSuccesUpdate() {
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_EDITION_SUCCESS);
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
