package com.gem.sistema.web.bean;

import com.gem.sistema.web.util.StreamedContents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.Util.fillZerosToRight;
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

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Catdep;
import com.gem.sistema.business.domain.Cuenta;
import com.gem.sistema.business.domain.Fuentef;
import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.domain.Xcatpro;
import com.gem.sistema.business.predicates.FuentefPredicates;
import com.gem.sistema.business.predicates.MuniNepPredicates;
import com.gem.sistema.business.predicates.XcatproPredicates;
import com.gem.sistema.business.repository.catalogs.CatdepRepository;
import com.gem.sistema.business.repository.catalogs.CuentaRepository;
import com.gem.sistema.business.repository.catalogs.FuentefRepository;
import com.gem.sistema.business.repository.catalogs.MuniNepRepository;
import com.gem.sistema.business.repository.catalogs.PpcappRepository;
import com.gem.sistema.business.repository.catalogs.XcatproRepository;
import com.gem.sistema.business.utils.ConvertCharsetUtils;
import com.gem.sistema.web.util.FrontProperty;
import com.querydsl.core.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class CatalogProgramsMB.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@Component("catalogProgramsMB")
@Scope("view")
public class CatalogProgramsMB extends CommonCatalogMB<Xcatpro> implements Serializable {

	/** The Constant charSetISO. */
	private final static String charSetISO = "ISO-8859-1";

	/** The Constant charSetUFT. */
	private final static String charSetUFT = "utf-8";

	/** The Constant DEFUALT_PATH_UPLOAD. */
	private static final String DEFUALT_PATH_UPLOAD = "/gem/upfiles/";

	/**
	 * Serial default.
	 */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogProgramsMB.class);

	/**
	 * Numero de caracteres de cvefuen.
	 */
	private static final int NUM_CARACTER_CVE_FUEN = 3;

	/** Longitud de la clave de dependencia. */
	private static final int LENGTH_CLVDEP = 10;

	/** Longitud del campo clvfun. */
	private static final int LENGTH_CLVFUN = 10;

	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = FrontProperty
			.getPropertyValue("catalog.report.procedure.name.generic");

	/** The csv by pl. */
	private StreamedContent csvByPl;

	/** Nombre del reporte en csv by PL-sql. */
	// @Value("${file.name.report.txt.estructuraProgramatica}")
	protected static final String REPORT_NAME_PLAIN_CSV = "catalogPrograms.csv";

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.program.estructure}")
	protected static final String HEADERS_REPORT_CSV_PLAIN = FrontProperty
			.getPropertyValue("header.csv.catalog.programs");

	/** The Constant QUERY_STRUCTURA. */
	protected static final String QUERY_STRUCTURA = FrontProperty
			.getPropertyValue("catalog.report.csv.catalog.programs");

	/** Registros del catalogo de dependencias. */
	private List<Catdep> dependencies;

	/**
	 * Almacena los registros del catalogo de dependencias filtrados.
	 */
	private List<Catdep> filteredDependencies;

	/** Registros del catalogo de estructura programatica. */
	private List<Muninep> programsStructure;

	/**
	 * Almacena los registros del catalogo de estructura programatica filtrados.
	 */
	private List<Muninep> filteredProgramStructure;

	/** Registros del catalogo de fuentes de financiamiento. */
	private List<Fuentef> financialSources;

	/**
	 * Almacena los registros del catalogo de fuentes de financiamiento filtrados.
	 */
	private List<Fuentef> filteredFinancialSources;

	/** Componente de servicio. */
	@Value("#{xcatproRepository}")
	private XcatproRepository xcatproRepository;

	/** Componente de servicio. */
	@Value("#{muniNepRepository}")
	private MuniNepRepository muniNepRepository;

	/** Componente de servicio. */
	@Value("#{catdepRepository}")
	private CatdepRepository catdepRepository;

	/** Componente de servicio. */
	@Value("#{fuentefRepository}")
	private FuentefRepository fuentefRepository;

	/** Componente de servicio. */
	@Value("#{cuentaRepository}")
	private CuentaRepository cuentaRepository;

	@Value("#{ppcappRepository}")
	private PpcappRepository ppcappRepository;

	/** Fila seleccionada del catalogo de programas. */
	private Xcatpro rowSelected;

	/** Fila seleccioanda del catalogo de dependencias. */
	private Catdep dependenceSelected;

	/** Fila seleccioanda del catalogo de estructura programatica. */
	private Muninep programStructureSelected;

	/** Fila seleccionada del catalogo de fuentes de financiamiento. */
	private Fuentef financialSourcesSelected;

	/** The buffer. */
	private Xcatpro buffer;

	/** The bandera. */
	private boolean bandera = Boolean.FALSE;

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

	/** Nombre del reporte en texto plano. */
	// @Value("${file.name.report.txt.programas}")
	private String REPORT_NAME = FrontProperty.getPropertyValue("file.name.report.txt.programas");

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.programs}")
	private static final String HEADERS_REPORT_TEXT_PLAIN = FrontProperty
			.getPropertyValue("header.text.plain.programs");

	/** The Constant HEADERS_REPORT_CSV. */
	private static final String HEADERS_REPORT_CSV = FrontProperty.getPropertyValue("header.csv.programs");

	/** The Constant QUERY_PROGRAMS. */
	protected static final String QUERY_PROGRAMS = FrontProperty
			.getPropertyValue("catalog.report.csv.catalog.programs");

	/** Ruta donde se encuentra el archivo jasper del reporte de programas. */
	// @Value("${view.report.path.jasper.programs}")
	private static final String REPORT_PATH_JASPER_PROGRAMS = FrontProperty
			.getPropertyValue("view.report.path.jasper.programs");

	/**
	 * Mensaje una dependencia no puede estar asociada al mismo programa dos veces.
	 */
	// @Value("${catalog.message.dependence.same.program}")
	private static final String MESSAGE_DEPENDENCE_SAME_PROGRAM = FrontProperty
			.getPropertyValue("catalog.message.dependence.same.program");

	/** Mensaje el valor de clvpro no se puede repetir. */
	// @Value("${catalog.message.clvpro.not.repeat}")
	private static final String MESSAGE_CLVPRO_NOT_REPEAT = FrontProperty
			.getPropertyValue("catalog.message.clvpro.not.repeat");

	/** Mensaje de error pantallas de ayuda. */
	// @Value("${catalog.message.data.required.save}")
	protected static final String ERROR_MESSAGE_DATA_REQUIRED = FrontProperty
			.getPropertyValue("catalog.message.data.required.save");

	/** Bean for search autocomplete. */
	private Xcatpro catProgramAut;

	/**
	 * Sets the xcatpro repository.
	 *
	 * @param xcatproRepository the new xcatpro repository
	 */
	public void setXcatproRepository(XcatproRepository xcatproRepository) {
		this.xcatproRepository = xcatproRepository;
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
	 * Sets the catdep repository.
	 *
	 * @param catdepRepository the new catdep repository
	 */
	public void setCatdepRepository(CatdepRepository catdepRepository) {
		this.catdepRepository = catdepRepository;
	}

	/**
	 * Sets the fuentef repository.
	 *
	 * @param fuentefRepository the new fuentef repository
	 */
	public void setFuentefRepository(FuentefRepository fuentefRepository) {
		this.fuentefRepository = fuentefRepository;
	}

	/**
	 * Gets the buffer.
	 *
	 * @return the buffer
	 */
	public Xcatpro getBuffer() {
		return buffer;
	}

	/**
	 * Sets the buffer.
	 *
	 * @param buffer the new buffer
	 */
	public void setBuffer(Xcatpro buffer) {
		this.buffer = buffer;
	}

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct catalogProgramsMB ");
		setBeanFind(new Xcatpro());
		setList(new ArrayList<Xcatpro>());
		setListNew(new ArrayList<Xcatpro>());
		setQueryOrderBy(Boolean.TRUE);
		this.reportNameTextPlain = REPORT_NAME;
		this.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;
		this.headersReportCSV = HEADERS_REPORT_CSV;
		sSqlCsv = QUERY_PROGRAMS;
		AND_ID_SECTOR = "and sectorid = " + this.getUserDetails().getIdSector() + " order by 1";
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogProgramsMB  ");
		getBeanFind().setClvdep(null);
		getBeanFind().setClvfun(null);
		getBeanFind().setNompro(null);

		restartData();
	}

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {

		final Xcatpro catalog = (Xcatpro) event.getObject();

		LOGGER.info(":: Editando Fila catalogProgramsMB " + catalog);
		final StringBuilder errorMsg = new StringBuilder();
		boolean validation = true;
		catalog.setSectorid(this.getUserDetails().getIdSector());
		if (isValidCapturedData(catalog, errorMsg)) {
			if (bandera == Boolean.FALSE) {
				if (isProgramAsociatedWithDependenceAndClvproIsUnique(catalog)) {
					Cuenta findFirst = cuentaRepository.findFirstBySscuentaAndCuentaEq5500Or82(catalog.getClvfun(),
							catalog.getClvfin(), catalog.getClvdep());

					/*
					 * List<Cuenta> listaTmp = new ArrayList<Cuenta>();
					 * 
					 * for (Cuenta cuenta : listCta) { if
					 * (cuenta.getSscuenta().startsWith(catalog.getClvfun())) {
					 * listaTmp.add(cuenta); } } for (Cuenta cuenta : listaTmp) { if
					 * (cuenta.getCuenta().equals("5500") || cuenta.getCuenta().startsWith("82")) {
					 * validation = false; break; } else { validation = true; } }
					 */

					if (findFirst == null) {
						validation = true;

					} else {
						validation = false;
					}

					if (validation) {
						catalog.setSectorid(this.getUserDetails().getIdSector());
						catalog.setFeccap(new Date());
						catalog.setIdRef(0L);
						catalog.setUserid(this.getUserDetails().getUsername());
						catalog.setClvdep(fillZerosToRight(catalog.getClvdep().toString(), LENGTH_CLVDEP));
						Muninep muninep = muniNepRepository.findByCampo7(catalog.getClvfun()).get(0);
						if (muninep != null) {
							catalog.setNompro(muninep.getCampo6());
						} else {
							catalog.setNompro("");
						}
						catalog.setUltniv("S");
						executeOperationSaveOrUpdate(catalog, xcatproRepository);
					} else {
						generateNotificationFront(SEVERITY_ERROR,
								"EL PROGRAMA NO SE PUEDE MODIFICAR PORQUE TIENE CUENTAS DE EGRESOS", "");
					}

				} else {
					activateRowEdit(catalog.getIndex());
				}
			} else {
				onRowCancel(event);
				generateNotificationFront(SEVERITY_ERROR,
						"EL PROGRAMA NO SE PUEDE MODIFICAR PORQUE TIENE CUENTAS DE EGRESOS", "");
				bandera = Boolean.FALSE;
				// PrimeFaces.current()
				// .execute(String.format(CANCEL_CLVFLU_ROW_JQUERY,
				// catalog.getIndex()));
			}
		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_INFO, errorMsg.toString());
			activateRowEdit(catalog.getIndex());
		}
	}

	/**
	 * Formatear clv dep.
	 *
	 * @param index the index
	 */
	public void formatearClvDep(Integer index) {
		Xcatpro catalog = this.getList().get(index);
		if (catalog.getClvdep() != null) {
			catalog.setClvdep(
					fillZerosToRight(this.getList().get(index).getClvdep().toString().toUpperCase(), LENGTH_CLVDEP));
		}
	}

	/**
	 * Checks if is program asociated with dependence and clvpro is unique.
	 *
	 * @param catalog the catalog
	 * @return true, if is program asociated with dependence and clvpro is unique
	 */
	private boolean isProgramAsociatedWithDependenceAndClvproIsUnique(final Xcatpro catalog) {

		boolean isValidClvdepClvFun = Boolean.FALSE;
		for (final Xcatpro element : getList()) {
			if (BooleanUtils.negate(element.getId() == ZERO) && element.getId().equals(catalog.getId()) && element
					.getClvpro().equals(catalog.getClvdep().concat(catalog.getClvfun()).concat(catalog.getClvfin()))) {
				return Boolean.TRUE;
			}
			if (BooleanUtils.negate(element.getId() == ZERO) && element.getId().equals(catalog.getId())
					&& element.getClvdep().equals(catalog.getClvdep())
					&& element.getClvfun().equals(catalog.getClvfun())
					&& element.getClvfin().equals(catalog.getClvfin())) {
				isValidClvdepClvFun = Boolean.TRUE;
				break;
			}
		}

		boolean result = Boolean.TRUE;
		Predicate predicate = XcatproPredicates.existClvdepRelatedToProgramAndFin(catalog.getClvdep(),
				catalog.getClvfun(), catalog.getClvfin(), catalog.getSectorid());

		if (isValidClvdepClvFun || xcatproRepository.count(predicate) == ZERO) {
			catalog.setClvpro(catalog.getClvdep().concat(catalog.getClvfun()).concat(catalog.getClvfin()));
			predicate = XcatproPredicates.existClvPro(catalog.getClvpro(), catalog.getSectorid());
			if (xcatproRepository.count(predicate) > ZERO) {
				result = Boolean.FALSE;
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_INFO, MESSAGE_CLVPRO_NOT_REPEAT);
			}
		} else {
			result = Boolean.FALSE;
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_INFO, MESSAGE_DEPENDENCE_SAME_PROGRAM);
		}
		return result;
	}

	/**
	 * Checks if is valid captured data.
	 *
	 * @param catalog  the catalog
	 * @param errorMsg the error msg
	 * @return true, if is valid captured data
	 */
	private boolean isValidCapturedData(final Xcatpro catalog, final StringBuilder errorMsg) {
		LOGGER.info(":: Es valida la info capturada: " + catalog);

		final boolean result;
		if (StringUtils.isEmpty(catalog.getClvfun())
				|| BooleanUtils.negate(catalog.getClvfun().length() == LENGTH_CLVFUN)
				|| StringUtils.isEmpty(catalog.getClvdep()) || StringUtils.isEmpty(catalog.getClvfin())) {
			result = Boolean.FALSE;
			errorMsg.append("Se deben capturar los campos: ClvFun, ClvDep y Clvfin");
		} else {
			List<Catdep> deps = catdepRepository.findByClvdep(catalog.getClvdep());

			result = CollectionUtils.isNotEmpty(deps) && deps.get(0).getUltniv().toUpperCase().equals("S") &&

					CollectionUtils.isNotEmpty(
							muniNepRepository.findByCampo0AndCampo1AndCampo2AndCampo3AndCampo4AndIdsector(
									catalog.getClvfun().substring(0, 2), catalog.getClvfun().substring(2, 4),
									catalog.getClvfun().substring(4, 6), catalog.getClvfun().substring(6, 8),
									catalog.getClvfun().substring(8, 10), catalog.getSectorid()))
					&&

					CollectionUtils.isNotEmpty(fuentefRepository.findByClvfteAndIdsector(catalog.getClvfin(),
							this.getUserDetails().getIdSector()));

			if (CollectionUtils.isNotEmpty(deps) && deps.get(0).getUltniv().toUpperCase().equals("N")) {
				errorMsg.append("La dependencia capturada no es de ultimo nivel");
			}
		}
		if (errorMsg.length() == ZERO && BooleanUtils.negate(result)) {
			errorMsg.append("La Estructura Programatica no existe en la Base de datos.");
		}
		return result;
	}

	/**
	 * Elimina un registro.
	 */
	public void delete() {
		LOGGER.info(":: Borrar registro catalogProgramsMB " + getBeanSelected());
		if (BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {
			//
			List<Cuenta> listCta = cuentaRepository.findAllByIdsector(new Long(this.getUserDetails().getIdSector()),
					CUENTA_CTA_ASC);

			List<Cuenta> listaTmp = new ArrayList<Cuenta>();
			boolean validation = true;
			for (Cuenta cuenta : listCta) {
				if (cuenta.getSscuenta().equals(getBeanSelected().getClvfun() + getBeanSelected().getClvfin())
						&& cuenta.getScuenta().equals(getBeanSelected().getClvdep())) {
					listaTmp.add(cuenta);
				}
			}

			for (Cuenta cuenta : listaTmp) {
				if (cuenta.getCuenta().startsWith("5") || cuenta.getCuenta().startsWith("8217")) {
					validation = false;
					break;
				} else {
					validation = true;
				}
			}

			if (validation) {

				if (this.getUserDetails().getIdSector() == 1) {

					List<Object> capps = ppcappRepository.findByClvdepClvnepClvfuen(getBeanSelected().getClvdep(),
							getBeanSelected().getClvfun(), getBeanSelected().getClvfin());

					if (capps.isEmpty()) {
						xcatproRepository.delete(getBeanSelected());
						generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_DELETED_RECORD);
						restartDataOperationDelete();
					} else {
						generateNotificationFront(SEVERITY_ERROR,
								"El Programa no se puede eliminar porque tiene Metas registradas.", "");
					}

				} else {
					xcatproRepository.delete(getBeanSelected());
					generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_DELETED_RECORD);
					restartDataOperationDelete();
				}

			} else {
				generateNotificationFront(SEVERITY_ERROR,
						"EL PROGRAMA NO SE PUEDE ELIMINAR PORQUE TIENE CUENTAS DE EGRESOS", "");
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
		getBeanFind().setSectorid(getUserDetails().getIdSector());
		LOGGER.info(":: Buscar filas catalogProgramsMB " + getBeanFind());
		addList(IteratorUtils.toList(this.xcatproRepository
				.findAll(XcatproPredicates.existClvDep(getBeanFind()), XCATPRO_CTA_ASC).iterator()));
		restartFilteredList();
		validateAddFinancialSources();
		LOGGER.info(":: Resultado de busqueda catalogProgramsMB " + getList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@Override
	protected void consultAll() {
		List<Xcatpro> listFilter = new ArrayList<Xcatpro>();
		listFilter = xcatproRepository.findBySectorid(getUserDetails().getIdSector(), XCATPRO_CTA_ASC);
		setList(listFilter);
		restartFilteredList();
		validateAddFinancialSources();
	}

	/** The Constant XCATPRO_CTA_ASC. */
	private static final Sort XCATPRO_CTA_ASC = Sort.by(Sort.Direction.ASC, "clvdep", "clvfun", "clvfin");

	/** The Constant CUENTA_CTA_ASC. */
	private static final Sort CUENTA_CTA_ASC = Sort.by(Sort.Direction.ASC, "cuenta");

	/**
	 * Validate add financial sources.
	 */
	private void validateAddFinancialSources() {
		if (BooleanUtils.negate(CollectionUtils.isEmpty(getList()))
				&& StringUtils.isEmpty(getList().get(ZERO).getClvfin())) {
			addFinancialSources();
		}
	}

	/**
	 * Adds the financial sources.
	 */
	private void addFinancialSources() {
		for (final Xcatpro element : getList()) {
			element.setClvfin(element.getClvpro().substring(element.getClvpro().length() - NUM_CARACTER_CVE_FUEN,
					element.getClvpro().length()));
			element.setClvpro(
					element.getClvpro().substring(ZERO, element.getClvpro().length() - NUM_CARACTER_CVE_FUEN));
		}
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
	 * Consult dependencies.
	 */
	public void consultDependencies() {
		LOGGER.info(":: Consultar dependencias ");
		dependencies = new ArrayList<Catdep>();
		for (Catdep catdep : catdepRepository.findAll()) {
			if (catdep.getIdsector().equals(Integer.valueOf(this.getUserDetails().getIdSector()))
					&& catdep.getUltniv().toUpperCase().equals("S")) {
				dependencies.add(catdep);
			}
		}
		restartFilteredDependencies();
		LOGGER.info(":: Dependencias " + dependencies);
	}

	/**
	 * Restart filtered dependencies.
	 */
	private void restartFilteredDependencies() {
		if (filteredDependencies == null) {
			filteredDependencies = new ArrayList<>();
		}
		filteredDependencies.clear();
		filteredDependencies.addAll(dependencies);
	}

	/**
	 * Change dependence.
	 */
	public void changeDependence() {
		if (null != dependenceSelected) {
			LOGGER.info(":: Cambiar dependencia " + dependenceSelected + "::" + rowSelected);
			rowSelected.setClvdep(dependenceSelected.getClvdep());

			// final PrimeFaces context =
			// PrimeFaces.current();
			// context.ajax().update(VIEW_CATALOG_FORM1_OBJECTS +
			// rowSelected.getIndex() +
			// ":clvdep");
		}

		activateRowEdit(rowSelected.getIndex());
		final PrimeFaces context = PrimeFaces.current();
		context.ajax().update(VIEW_CATALOG_FORM1_OBJECTS + rowSelected.getIndex() + ":clvfuen");
	}

	/**
	 * Consult program structure.
	 */
	public void consultProgramStructure() {
		LOGGER.info(":: Consultar estructura programatica ");
		programsStructure = new ArrayList<Muninep>();
		//final Predicate predicate = MuniNepPredicates.getPredicateToPrograms();
		for (Muninep muninep : muniNepRepository.getListSubPrograma(this.getUserDetails().getIdSector())) {
			//if (muninep.getIdsector().equals(Integer.valueOf(this.getUserDetails().getIdSector()))) {
				programsStructure.add(muninep);
			//}
		}
		restartFilteredProgramStructure();
	}

	/**
	 * Restart filtered program structure.
	 */
	private void restartFilteredProgramStructure() {
		if (filteredProgramStructure == null) {
			filteredProgramStructure = new ArrayList<>();
		}
		filteredProgramStructure.clear();
		filteredProgramStructure.addAll(programsStructure);
	}

	/**
	 * Change program structure.
	 */
	public void changeProgramStructure() {
		LOGGER.info(":: Cambiar estructura programatica " + programStructureSelected + "::" + rowSelected);
		if (programStructureSelected != null) {
			rowSelected.setClvfun(programStructureSelected.getCampo0() + programStructureSelected.getCampo1()
					+ programStructureSelected.getCampo2() + programStructureSelected.getCampo3()
					+ programStructureSelected.getCampo4() + programStructureSelected.getCampo5());

			rowSelected.setCampo0(programStructureSelected.getCampo0());
			rowSelected.setCampo1(programStructureSelected.getCampo1());
			rowSelected.setCampo2(programStructureSelected.getCampo2());
			rowSelected.setCampo3(programStructureSelected.getCampo3());
			rowSelected.setCampo4(programStructureSelected.getCampo4());
			rowSelected.setCampo5(programStructureSelected.getCampo5());

			rowSelected.setNompro(programStructureSelected.getCampo6());

		}
		activateRowEdit(rowSelected.getIndex());
		final PrimeFaces context = PrimeFaces.current();
		context.ajax().update(VIEW_CATALOG_FORM1_OBJECTS + rowSelected.getIndex() + ":clvfun");
		context.ajax().update(VIEW_CATALOG_FORM1_OBJECTS + rowSelected.getIndex() + ":nompro");
		LOGGER.info(":: Cambiar estructura programatica_2***: " + rowSelected);
	}

	/**
	 * Consult financial sources.
	 */
	public void consultFinancialSources() {
		LOGGER.info(":: Consultar fuentes de financiamiento " + rowSelected);
		financialSources = new ArrayList<Fuentef>();
		final Predicate predicate = FuentefPredicates.elegibleToPrograms();
		for (Fuentef fuentef : fuentefRepository.findAll(predicate)) {
			if (fuentef.getIdsector().equals(Integer.valueOf(this.getUserDetails().getIdSector()))) {
				financialSources.add(fuentef);
			}
		}
		restartFilteredFinancialSources();
	}

	/**
	 * Restart filtered financial sources.
	 */
	private void restartFilteredFinancialSources() {
		if (filteredFinancialSources == null) {
			filteredFinancialSources = new ArrayList<>();
		}
		filteredFinancialSources.clear();
		filteredFinancialSources.addAll(financialSources);
	}

	/**
	 * Change financial sources.
	 */
	public void changeFinancialSources() {
		if (null != financialSourcesSelected) {
			LOGGER.info(":: Cambiar Fuentes de Financiamiento " + rowSelected.getIndex());
			rowSelected.setClvfin(financialSourcesSelected.getClvfte());
		}
		activateRowEdit(rowSelected.getIndex());
		final PrimeFaces context = PrimeFaces.current();
		context.ajax().update(VIEW_CATALOG_FORM1_OBJECTS + rowSelected.getIndex() + ":clvfuen");
	}

	/**
	 * To autocomplete cat progra for cve.
	 *
	 * @param query the query
	 * @return the list
	 */
	public List<Xcatpro> completeProgramsCve(String query) {
		consultAll();
		List<Xcatpro> allPrograms = getList();
		List<Xcatpro> filteredPrograms = new ArrayList<>();
		for (int i = 0; i < allPrograms.size(); i++) {
			Xcatpro skin = allPrograms.get(i);
			if (skin.getClvdep() != null && skin.getClvdep().toLowerCase().startsWith(query)) {
				filteredPrograms.add(skin);
			}
		}
		return filteredPrograms;
	}

	/**
	 * To autocomplete cat progra for name.
	 *
	 * @param query the query
	 * @return the list
	 */
	public List<Xcatpro> completeProgramsName(String query) {
		consultAll();
		List<Xcatpro> allPrograms = getList();
		List<Xcatpro> filteredPrograms = new ArrayList<>();
		for (int i = 0; i < allPrograms.size(); i++) {
			Xcatpro skin = allPrograms.get(i);
			if (skin.getNompro() != null && skin.getNompro().toLowerCase().startsWith(query)) {
				filteredPrograms.add(skin);
			}
		}
		return filteredPrograms;
	}

	/**
	 * Gets the file pdf.
	 *
	 * @return the file pdf
	 */
	public StreamedContent getFilePdf() {
		LOGGER.info(":: Generar reporte de PDF ");
		getBeanFind().setSectorid(this.getUserDetails().getIdSector());
		return super.getFilePdf(REPORT_PATH_JASPER_PROGRAMS, REPORT_NAME);
	}

	/**
	 * Gets the file xls.
	 *
	 * @return the file xls
	 */
	public StreamedContent getFileXls() {
		LOGGER.info(":: Generar reporte de Excel ");
		getBeanFind().setSectorid(this.getUserDetails().getIdSector());
		return super.getFileXls(REPORT_PATH_JASPER_PROGRAMS, REPORT_NAME);
	}

	/**
	 * Activate row edit.
	 */
	public void activateRowEdit() {
		super.activateRowEdit(rowSelected.getIndex());
	}

	/**
	 * Gets the dependencies.
	 *
	 * @return the dependencies
	 */
	public List<Catdep> getDependencies() {
		return dependencies;
	}

	/**
	 * Sets the dependencies.
	 *
	 * @param dependencies the dependencies to set
	 */
	public void setDependencies(List<Catdep> dependencies) {
		this.dependencies = dependencies;
	}

	/**
	 * Gets the dependence selected.
	 *
	 * @return the dependenceSelected
	 */
	public Catdep getDependenceSelected() {
		return dependenceSelected;
	}

	/**
	 * Sets the dependence selected.
	 *
	 * @param dependenceSelected the dependenceSelected to set
	 */
	public void setDependenceSelected(final Catdep dependenceSelected) {
		LOGGER.info(":: Dependencia seleccionada: " + dependenceSelected);
		if (BooleanUtils.negate(rowSelected == null) && BooleanUtils.negate(dependenceSelected == null)) {
			this.dependenceSelected = dependenceSelected;
		}
	}

	/**
	 * Gets the row selected.
	 *
	 * @return the rowSelected
	 */
	public Xcatpro getRowSelected() {
		return rowSelected;
	}

	/**
	 * Sets the row selected.
	 *
	 * @param rowSelected the rowSelected to set
	 */
	public void setRowSelected(Xcatpro rowSelected) {
		this.rowSelected = rowSelected;
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
	 * @param programsStructure the programsStructure to set
	 */
	public void setProgramsStructure(List<Muninep> programsStructure) {
		this.programsStructure = programsStructure;
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
	 * @param programStructureSelected the programStructureSelected to set
	 */
	public void setProgramStructureSelected(final Muninep programStructureSelected) {
		LOGGER.info(":: Programa seleccionada: " + programStructureSelected);
		if (BooleanUtils.negate(rowSelected == null) && BooleanUtils.negate(programStructureSelected == null)) {
			this.programStructureSelected = programStructureSelected;
		}
	}

	/**
	 * Gets the financial sources.
	 *
	 * @return the financialSources
	 */
	public List<Fuentef> getFinancialSources() {
		return financialSources;
	}

	/**
	 * Sets the financial sources.
	 *
	 * @param financialSources the financialSources to set
	 */
	public void setFinancialSources(List<Fuentef> financialSources) {
		this.financialSources = financialSources;
	}

	/**
	 * Gets the financial sources selected.
	 *
	 * @return the financialSourcesSelected
	 */
	public Fuentef getFinancialSourcesSelected() {
		return financialSourcesSelected;
	}

	/**
	 * Sets the financial sources selected.
	 *
	 * @param financialSourcesSelected the financialSourcesSelected to set
	 */
	public void setFinancialSourcesSelected(final Fuentef financialSourcesSelected) {
		LOGGER.info(":: Fuente de Financiamiento seleccionada: " + financialSourcesSelected);
		if (BooleanUtils.negate(rowSelected == null) && BooleanUtils.negate(financialSourcesSelected == null)) {
			this.financialSourcesSelected = financialSourcesSelected;
		}
	}

	/**
	 * Gets the filtered dependencies.
	 *
	 * @return the filteredDependencies
	 */
	public List<Catdep> getFilteredDependencies() {
		return filteredDependencies;
	}

	/**
	 * Sets the filtered dependencies.
	 *
	 * @param filteredDependencies the filteredDependencies to set
	 */
	public void setFilteredDependencies(List<Catdep> filteredDependencies) {
		this.filteredDependencies = filteredDependencies;
	}

	/**
	 * Gets the filtered program structure.
	 *
	 * @return the filteredProgramStructureSelected
	 */
	public List<Muninep> getFilteredProgramStructure() {
		return filteredProgramStructure;
	}

	/**
	 * Sets the filtered program structure.
	 *
	 * @param filteredProgramStructure the new filtered program structure
	 */
	public void setFilteredProgramStructure(List<Muninep> filteredProgramStructure) {
		this.filteredProgramStructure = filteredProgramStructure;
	}

	/**
	 * Gets the filtered financial sources.
	 *
	 * @return the filteredFinancialSources
	 */
	public List<Fuentef> getFilteredFinancialSources() {
		return filteredFinancialSources;
	}

	/**
	 * Sets the filtered financial sources.
	 *
	 * @param filteredFinancialSources the filteredFinancialSources to set
	 */
	public void setFilteredFinancialSources(List<Fuentef> filteredFinancialSources) {
		this.filteredFinancialSources = filteredFinancialSources;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#addNewOriginalList()
	 */
	@Override
	protected void addNewOriginalList() {
		this.getList().get(0).setClvdep("");

	}

	/**
	 * On clvdep row dbl clck select.
	 *
	 * @param event the event
	 */
	public void onClvdepRowDblClckSelect(final SelectEvent event) {
		if (null != dependenceSelected) {
			this.changeDependence();
		}
		// rest of your logic
	}

	/**
	 * On clvfun row dbl clck select.
	 *
	 * @param event the event
	 */
	public void onClvfunRowDblClckSelect(final SelectEvent event) {
		this.changeProgramStructure();
		// rest of your logic
	}

	/**
	 * On clvfuen row dbl clck select.
	 *
	 * @param event the event
	 */
	public void onClvfuenRowDblClckSelect(final SelectEvent event) {
		this.changeFinancialSources();
		// rest of your logic
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
	 * Gets the cat program aut.
	 *
	 * @return the cat program aut
	 */
	public Xcatpro getCatProgramAut() {
		return catProgramAut;
	}

	/**
	 * Sets the cat program aut.
	 *
	 * @param catProgramAut the new cat program aut
	 */
	public void setCatProgramAut(Xcatpro catProgramAut) {
		this.catProgramAut = catProgramAut;
	}

	/**
	 * Gets the cuenta repository.
	 *
	 * @return the cuentaRepository
	 */
	public CuentaRepository getCuentaRepository() {
		return cuentaRepository;
	}

	/**
	 * Sets the cuenta repository.
	 *
	 * @param cuentaRepository the cuentaRepository to set
	 */
	public void setCuentaRepository(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}

	/**
	 * Locked data.
	 */
	public void lockedData() {
		rowSelected.getClvdep();
	}

	/** The Constant CANCEL_CLVFLU_ROW_JQUERY. */
	private static final String CANCEL_CLVFLU_ROW_JQUERY = "jQuery('[id^=form1\\\\:objects\\\\:%1$s] span.ui-icon-close').last().click();";

	/** The Constant UPDATE_MESSAGE_JQUERY. */
	private static final String UPDATE_MESSAGE_JQUERY = "jQuery('[id^=form1\\\\:dataupdate]').click();";

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onInitRowEdit(final RowEditEvent event) {
		if (null != event.getObject()) {
			Xcatpro xCatpro = (Xcatpro) event.getObject();
			if (null != xCatpro.getId() && xCatpro.getId() != 0) {
				Cuenta findFirst = cuentaRepository.findFirstBySscuentaAndCuenta(
						xCatpro.getClvfun() + "" + xCatpro.getClvfin(), xCatpro.getClvdep(),
						this.getUserDetails().getIdSector());
				if (findFirst != null) {
					bandera = Boolean.TRUE;
					// onRowCancel(event);

				}
			}

		}

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
				try {
					fileNameOut = DEFUALT_PATH_UPLOAD + "programas.csv";
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
			generateNotificationFront(FacesMessage.SEVERITY_INFO, "Info!", out.get("O_MSJ_ERROR").toString());
		}

	}

	public PpcappRepository getPpcappRepository() {
		return ppcappRepository;
	}

	public void setPpcappRepository(PpcappRepository ppcappRepository) {
		this.ppcappRepository = ppcappRepository;
	}

}
