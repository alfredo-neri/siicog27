package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.BooleanUtils;
import org.hsqldb.lib.StringUtil;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.domain.Catflu;
import com.gem.sistema.business.domain.Muninep;
import com.gem.sistema.business.domain.Natgas;
import com.gem.sistema.business.repository.catalogs.NatgasRepository;
import com.gem.sistema.business.service.catalogos.NatureExpenditureService;
import com.gem.sistema.util.UtilJPA;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class CatalogNatureExpenditureMB.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@ManagedBean
@ViewScoped
public class CatalogNatureExpenditureMB extends CommonCatalogMB<Natgas> implements Serializable {

	/** Constante de la version del objeto. */
	private static final long serialVersionUID = 1L;

	/** The Constant FIELD_REQUIRED_CLVFGAS. */
	private static final String FIELD_REQUIRED_CLVFGAS = FrontProperty
			.getPropertyValue("catalog.message.field.required.clvgas");

	/** The Constant PASS. */
	private static final String PASS = FrontProperty.getPropertyValue("catalog.maintenance.nature.expenditur.pass");

	/** The validate pass. */
	private String validatePass;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogNatureExpenditureMB.class);

	/** Identifica que es de ultimo nivel. */
	private static final String LAST_LEVEL = "S";
	
/** The Constant OPERATOR_EQUAL. */
private static final String OPERATOR_EQUAL = "=";
	
	/** The Constant CLVGAS. */
	private static final String CLVGAS = "clvgas";

	/** Componente de repositorio. */
	@ManagedProperty(value = "#{natgasRepository}")
	private NatgasRepository natgasRepository;
	
	


	/** Componente de servicio. */
	@ManagedProperty(value = "#{natureExpenditureServiceImpl}")
	private NatureExpenditureService natureExpenditureService;

	/** Campo requerido Clave de Contrato. */
	// @Value("${catalog.message.field.required.clvgas}")
	private static final String FIELD_REQUIRED_CLVGAS = FrontProperty
			.getPropertyValue("catalog.message.field.required.clvgas");

	/** Nombre del reporte en texto plano. */
	// @Value("${file.name.report.txt.catalogoNaturalezaGasto}")
	protected static final String REPORT_NAME_PLAIN_TEXT = FrontProperty
			.getPropertyValue("file.name.report.txt.catalogoNaturalezaGasto");

	/** Encabezados reporte de texto plano. */
	// @Value("${header.text.plain.nature.expenditure}")
	protected static final String HEADERS_REPORT_TEXT_PLAIN = FrontProperty
			.getPropertyValue("header.text.plain.nature.expenditure");
	
	/** The Constant HEADERS_REPORT_CSV. */
	protected static final String HEADERS_REPORT_CSV = FrontProperty
			.getPropertyValue("header.csv.nature.expenditure");

	/** Ruta donde se encuentra el archivo jasper del reporte. */
	// @Value("${view.report.path.jasper.catalog_maintenance_nature_expenditure}")
	private static final String REPORT_PATH_JASPER_FILE = FrontProperty
			.getPropertyValue("view.report.path.jasper.catalog_maintenance_nature_expenditure");

	/**
	 * Sets the natgas repository.
	 *
	 * @param natgasRepository the new natgas repository
	 */
	public void setNatgasRepository(NatgasRepository natgasRepository) {
		this.natgasRepository = natgasRepository;
	}

	/**
	 * Sets the nature expenditure service.
	 *
	 * @param natureExpenditureService the new nature expenditure service
	 */
	public void setNatureExpenditureService(NatureExpenditureService natureExpenditureService) {
		this.natureExpenditureService = natureExpenditureService;
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
		LOGGER.info(":: En postconsruct catalogNatureExpenditureMB ");
		setBeanFind(new Natgas());
		setList(new ArrayList<Natgas>());
		setListNew(new ArrayList<Natgas>());
		this.reportNameTextPlain = REPORT_NAME_PLAIN_TEXT;
		this.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;
		this.headersReportCSV = HEADERS_REPORT_CSV;
		this.setValidatePass("");
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogNatureExpenditureMB  ");
		getBeanFind().setClvgas(null);
		getBeanFind().setNomgas(null);
		restartData();
	}

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {
//		LOGGER.info(":: Editando Fila catalogNatureExpenditureMB  " + event.getObject());
//		final Natgas catalog = (Natgas) event.getObject();
//		final StringBuilder errorMsg = new StringBuilder();
//
//		if (isValidUpdateOrSave(catalog) && natureExpenditureService.isValidPreviousLevel(catalog, errorMsg)) {
//
//			decideLastLevel(catalog);
//			executeOperationSaveOrUpdate(catalog, natgasRepository);
//
//		} else {
//			if (errorMsg.length() == ZERO) {
//				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_UPDATE_UNIQUE
//						.concat(FIELD_REQUIRED_CLVGAS).concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
//			} else {
//				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, errorMsg.toString());
//			}
//			activateRowEdit(catalog.getIndex());
//		}
		saveRows();
	}

	/**
	 * Decide last level.
	 *
	 * @param catalog the catalog
	 */
	private void decideLastLevel(final Natgas catalog) {
		if (catalog.getClvgas().indexOf(String.valueOf(ZERO)) == -1) {
			catalog.setIndcap(LAST_LEVEL);
		}
	}

	/**
	 * Checks if is valid update or save.
	 *
	 * @param catalog the catalog
	 * @return true, if is valid update or save
	 */
	private boolean isValidUpdateOrSave(final Natgas catalog) {
		final List<Natgas> validateUnique = natgasRepository.findByClvgasAndIdsector(catalog.getClvgas(), this.getUserDetails().getIdSector());
		return isValidSaveOrUpdate(validateUnique, catalog);
	}

	/**
	 * Elimina un registro.
	 */
	public void delete() {
		LOGGER.info(":: Borrar registro catalogNatureExpenditureMB " + getBeanSelected());
		if (BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {
			natgasRepository.delete(getBeanSelected());
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
	@Override
	protected void consultList() {		
		LOGGER.info(":: Buscar filas catalogNatureExpenditureMB {} ", getBeanFind());		
		getBeanFind().setIdsector(getUserDetails().getIdSector());
		addList((List<Natgas>) repositoryCustom.findByFiltersOrderBy(getBeanFind(), CLVGAS, OPERATOR_EQUAL));	
		restartFilteredList();
		LOGGER.info(":: Resultado de busqueda catalogNatureExpenditureMB {}", getList());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@Override
	protected void consultAll() {	
		setList( (List<Natgas>) natgasRepository.findAllByIdsectorOrderByClvgas(this.getUserDetails().getIdSector()));
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

	/**
	 * Gets the file pdf.
	 *
	 * @return the file pdf
	 */
	public StreamedContent getFilePdf() {
		LOGGER.info(":: Generar reporte de PDF ");
		getBeanFind().setIdsector(getUserDetails().getIdSector());
		return super.getFilePdf(REPORT_PATH_JASPER_FILE, REPORT_NAME_PLAIN_TEXT);
	}

	/**
	 * Gets the file xls.
	 *
	 * @return the file xls
	 */
	public StreamedContent getFileXls() {
		LOGGER.info(":: Generar reporte de Excel ");
		getBeanFind().setIdsector(getUserDetails().getIdSector());
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
	 * Save rows.
	 */
	public void saveRows() {
		LOGGER.info("entra a crear una nueva linea....");
		Natgas natgas = this.getBeanSelected();
		
		natgas = (Natgas) UtilJPA.fillPropertyStringIfNull(natgas);
		natgas.setIdsector(this.getUserDetails().getIdSector());
		natgas.setUserid(this.getUserDetails().getUsername());
		natgas.setCapgas(this.getUserDetails().getUsername());
		natgas.setFeccap(Calendar.getInstance().getTime());
		if (natgas.getClvgas().substring(3, 4).equals("0")) {
			natgas.setIndcap("S");
		} else {
			natgas.setIndcap("N");
		}
		// catalog.setClvflu(new BigDecimal(catalog.getClvflu()).setScale(2,
		// RoundingMode.HALF_UP).toString());

		final StringBuilder errorMsg = new StringBuilder();
		if (isValidUpdateOrSave(natgas) && natureExpenditureService.isValidPreviousLevel(natgas, errorMsg)) {
			executeOperationSaveOrUpdate(natgas, natgasRepository);
		} else {
			if (errorMsg.length() == ZERO) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_UPDATE_UNIQUE
						.concat(FIELD_REQUIRED_CLVFGAS).concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
			} else {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, errorMsg.toString());
			}
			activateRowEdit(natgas.getIndex());
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
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No password incorrecto");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		this.setValidatePass("");
	}
	
	


}
