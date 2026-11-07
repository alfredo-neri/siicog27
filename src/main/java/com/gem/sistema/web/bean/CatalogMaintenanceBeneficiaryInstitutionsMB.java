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
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.bean.ViewScoped;


import org.apache.commons.lang3.BooleanUtils;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.domain.Catben;

import com.gem.sistema.business.repository.catalogs.CatbenRepository;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class CatalogMaintenanceBeneficiaryInstitutionsMB.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@ManagedBean
@ViewScoped
public class CatalogMaintenanceBeneficiaryInstitutionsMB extends CommonCatalogMB<Catben> implements Serializable {

	/** Constante de la version del objeto. */
	private static final long serialVersionUID = 1L;	

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogMaintenanceBeneficiaryInstitutionsMB.class);	
	
	/** The Constant CLVBEN. */
	private static final String CLVBEN = "clvben";

	/**  Expresion regular que debe cumplir el campo clvben. */
	private static final String REG_EXP_CLVBEN = "[0-9]{1}+([.][0-9]{4})?";
	
	/**  Operador mayor o igual. */
	private static final String OPERATOR_GREATHER_THAN_EQUAL = ">=";
	
	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = FrontProperty
			.getPropertyValue("catalog.report.procedure.name.generic");

	/**  Componente de repositorio. */
	@ManagedProperty(value = "#{catbenRepository}")
	private CatbenRepository catbenRepository;

	/**  Fila seleccionada de instituciones beneficiarias. */
	private Catben rowSelected;
	
	/**  Campo requerido Clveben. */
	//@Value("${catalog.message.field.required.clvben}")
	private static final String FIELD_REQUIRED_CLVBEN= FrontProperty.getPropertyValue("catalog.message.field.required.clvben");

	/**  Nombre del reporte en texto plano. */
	//@Value("${file.name.report.txt.institucionesBeneficiarias}")
	protected static final String REPORT_NAME_PLAIN_TEXT= FrontProperty.getPropertyValue("file.name.report.txt.institucionesBeneficiarias");
	
	/**  Encabezados reporte de texto plano. */
	//@Value("${header.text.plain.beneficiary.institutions}")
	protected static final String HEADERS_REPORT_TEXT_PLAIN= FrontProperty.getPropertyValue("header.text.plain.beneficiary.institutions");

	/**  Mensaje de error de formato campo Clave. */
	//@Value("${catalog.message.error.clvben.format}")
	protected String MESSGE_ERROR_CLVBEN_FORMAT= FrontProperty.getPropertyValue("catalog.message.error.clvben.format");
	
	/**  Mensaje de error de cantidad campo Clave. */
	//@Value("${catalog.message.error.clvben.zero}")
	protected static final String MESSGE_ERROR_CLVBEN_ZERO= FrontProperty.getPropertyValue("catalog.message.error.clvben.zero");
	
	/**  Ruta donde se encuentra el archivo jasper del reporte. */
	//@Value("${view.report.path.jasper.catalog_maintenance_beneficiary_institutions}")
	private static final String REPORT_PATH_JASPER_FILE= FrontProperty.getPropertyValue("view.report.path.jasper.catalog_maintenance_beneficiary_institutions");
	
	/** The csv by pl. */
	private StreamedContent csvByPl;
	
	/** Nombre del reporte en csv by PL-sql. */
	// @Value("${file.name.report.txt.intitucionesBeneficiarias}")
	protected static final String REPORT_NAME_PLAIN_CSV = "catalogMaintenanceBeneficiaryInstitutions.cvs";

 /**  Encabezados reporte de texto plano. */
	// @Value("${}header.csv.catalog.maintenance.beneficiary.institutions")
	protected static final String HEADERS_REPORT_CSV_PLAIN = FrontProperty
			.getPropertyValue("header.csv.catalog.maintenance.beneficiary.institutions");

	/** The Constant QUERY_BENEFIC. */
	protected static final String QUERY_BENEFIC = FrontProperty
			.getPropertyValue("catalog.report.csv.catalog.maintenance.beneficiary.institutions");
			
	
	/** Bean for autocomplete funtion. */
	
	private Catben catBenAut;
	
	/**
	 * Sets the catben repository.
	 *
	 * @param catbenRepository the new catben repository
	 */
	public void setCatbenRepository(CatbenRepository catbenRepository) {
		this.catbenRepository = catbenRepository;
	}
	
	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct catalogMaintenanceBeneficiaryInstitutionsMB  ");				
		setBeanFind(new Catben());		
		setList(new ArrayList<Catben>());
		setListNew(new ArrayList<Catben>());
		this.reportNameTextPlain = REPORT_NAME_PLAIN_TEXT;
		this.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;
		sSqlCsv = QUERY_BENEFIC;
		AND_ID_SECTOR = " order by clvben asc " ;
	}		

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogMaintenanceBeneficiaryInstitutionsMB  ");					
		getBeanFind().setClvben(null);			
		getBeanFind().setNomben(null);
		restartData();		
	}
	
	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {
		LOGGER.info(":: Editando Fila catalogMaintenanceBeneficiaryInstitutionsMB  " + event.getObject());		
		final Catben catalog = (Catben) event.getObject();
		final StringBuilder errorMsg = new StringBuilder();
		catalog.setClvben(catalog.getClvben().setScale(4, RoundingMode.HALF_UP));
		
		if(isValidFieldClvBen(catalog.getClvben(), errorMsg) && validateSaveOrUpdate(catalog)) {
			executeOperationSaveOrUpdate(catalog, catbenRepository);
		} else {
			if(errorMsg.length() == ZERO) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_UPDATE_UNIQUE.concat(FIELD_REQUIRED_CLVBEN).
						 concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
			} else {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, errorMsg.toString());
			}
			activateRowEdit(catalog.getIndex());
		}
	}     	
	
//	/**
//	 * 
//	 * @param clvben
//	 * @return
//	 */
//	private BigDecimal formatCveben(final BigDecimal clvben) {
//		StringBuilder aux = new StringBuilder(clvben.toString());		
//		for(final char var: StringUtils.reverse(clvben.toString()).toCharArray()) {
//			if(var != '0') {				
//				break;
//			} else {
//				aux = aux.replace(aux.toString().length() - ONE, aux.toString().length(), EMPTY);
//			}
//		}
//		return new BigDecimal(aux.toString());
//	}
	
	/**
 * Checks if is valid field clv ben.
 *
 * @param clvben the clvben
 * @param errorMsg the error msg
 * @return true, if is valid field clv ben
 */
	private boolean isValidFieldClvBen(final BigDecimal clvben, final StringBuilder errorMsg) {
		LOGGER.info(":: Valor de CveBen: " + clvben);
		final boolean result;		
		final Pattern pattern = Pattern.compile(REG_EXP_CLVBEN);
		final Matcher matcher = pattern.matcher(clvben.toString());		
		final BigDecimal zero = new BigDecimal("0.0000");
		if(clvben.compareTo(zero) == ZERO) {
			errorMsg.append(MESSGE_ERROR_CLVBEN_ZERO);
			result = Boolean.FALSE;			
		} else if(BooleanUtils.negate(matcher.matches())) {
			errorMsg.append(MESSGE_ERROR_CLVBEN_FORMAT);
			result = Boolean.FALSE;			
		}		
		else {
			result = Boolean.TRUE;
		}		
		return result;		
	}

	/**
	 * Validate save or update.
	 *
	 * @param catalog the catalog
	 * @return true, if successful
	 */
	private boolean validateSaveOrUpdate(final Catben catalog) {		
		final List<Catben> validateUnique = catbenRepository.findByClvben(catalog.getClvben());		
		return isValidSaveOrUpdate(validateUnique, catalog);		
	}
	
	/**
	 * Elimina un registro.
	 */
	public void delete() {
		LOGGER.info(":: Borrar registro catalogMaintenanceBeneficiaryInstitutionsMB " + getBeanSelected());
		if(BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {
			catbenRepository.delete(getBeanSelected());
		}		
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_DELETED_RECORD);
		restartDataOperationDelete();		
	}	
							
	
	/*
	 * (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultList()
	 */
	@Override
	@SuppressWarnings("unchecked")		
	protected void consultList() {						
		LOGGER.info(":: Buscar filas catalogMaintenanceBeneficiaryInstitutionsMB " + getBeanFind());		
		addList((List<Catben>) repositoryCustom.findByFiltersOrderBy(getBeanFind(), CLVBEN, OPERATOR_GREATHER_THAN_EQUAL));
		LOGGER.info(":: Resultado de busqueda catalogMaintenanceBeneficiaryInstitutionsMB " + getList());
	}				

	
	/**
	 * To autocomplete CatBen for cve	.
	 *
	 * @param query the query
	 * @return the list
	 */
	public List<Catben> completeCatBenCve(String query) {
		consultAll();
		List<Catben> allCatBen = getList();
		List<Catben> filteredCatBen = new ArrayList<>();
		String clveCat = "";
		for (int i = 0; i < allCatBen.size(); i++) {
			Catben skin = allCatBen.get(i);
			clveCat = skin.getClvben().toString();
			if (clveCat != null && clveCat.toLowerCase().startsWith(query)) {
				filteredCatBen.add(skin);
			}
		}
		return filteredCatBen;
	}
	
	
	/**
	 * To autocomplete CatBen for name	.
	 *
	 * @param query the query
	 * @return the list
	 */
	public List<Catben> completeCatBenName(String query) {
		consultAll();
		List<Catben> allCatBen = getList();
		List<Catben> filteredCatBen = new ArrayList<>();
		
		for (int i = 0; i < allCatBen.size(); i++) {
			Catben skin = allCatBen.get(i);
			if (skin.getNomben() != null && skin.getNomben().toLowerCase().startsWith(query)) {
				filteredCatBen.add(skin);
			}
		}
		return filteredCatBen;
	}
	/*
	 * (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@Override
	protected void consultAll() {	
		setList(catbenRepository.findAllByOrderByClvben());
	}

	/*
	 * (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#isValidRequest()
	 */
	@Override
	protected boolean isValidRequest() {
		return Boolean.TRUE;
	}

	/**
	 * Gets the row selected.
	 *
	 * @return the rowSelected
	 */
	public Catben getRowSelected() {
		return rowSelected;
	}

	/**
	 * Sets the row selected.
	 *
	 * @param rowSelected the rowSelected to set
	 */
	public void setRowSelected(Catben rowSelected) {
		this.rowSelected = rowSelected;
	}
	
	/*
	 * (non-Javadoc)
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

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#onRowCancel(org.primefaces.event.RowEditEvent)
	 */
	@Override
	public void onRowCancel(RowEditEvent event) {
		restartData();
		super.onRowCancel(event);
	}

	/**
	 * Gets the cat ben aut.
	 *
	 * @return the cat ben aut
	 */
	public Catben getCatBenAut() {
		return catBenAut;
	}

	/**
	 * Sets the cat ben aut.
	 *
	 * @param catBenAut the new cat ben aut
	 */
	public void setCatBenAut(Catben catBenAut) {
		this.catBenAut = catBenAut;
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
	
	/** The parametros. */
	MapSqlParameterSource parametros = null;
	
	/**
	 * Gets the file csv pl.
	 *
	 * @return the file csv pl
	 */
	public void getFileCsvPl() {

		parametros = new MapSqlParameterSource();
		parametros.addValue("i_header", HEADERS_REPORT_CSV_PLAIN).addValue("i_query", this.getFileCsvByPl())

				.addValue("i_file_name", REPORT_NAME_PLAIN_CSV );

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
