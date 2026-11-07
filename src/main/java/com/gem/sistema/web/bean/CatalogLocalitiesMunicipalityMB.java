package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;

import com.gem.sistema.business.domain.Catmun;
import com.gem.sistema.business.domain.Localidades;
import com.gem.sistema.business.predicates.LocalidadesPredicates;
import com.gem.sistema.business.repository.catalogs.CatmunRepository;
import com.gem.sistema.business.repository.catalogs.LocalidadesRepository;
import com.gem.sistema.web.util.FrontProperty;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class CatalogLocalitiesMunicipalityMB.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@ManagedBean
@ViewScoped
public class CatalogLocalitiesMunicipalityMB extends CommonCatalogMB<Localidades> implements Serializable {

	/** Serial default. */
	private static final long serialVersionUID = 1L;

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogLocalitiesMunicipalityMB.class);

	/** Campo por el que se ordenará la consulta de datos. */
	private static final String ORDER_BY_FIELD_CLVMUN = "cveMun";

	/** Componente de servicio. */
	@ManagedProperty(value = "#{localidadesRepository}")
	private LocalidadesRepository localidadesRepository;

	/** Componente de repositorio. */
	@ManagedProperty(value = "#{catmunRepository}")
	private CatmunRepository catmunRepository;

	/** Fila seleccionada. */
	private Localidades rowSelected;

	/** Fila seleccionada de catalogo de municipios. */
	private Catmun localitySelected;

	/** Lista de localidades. */
	private List<Catmun> localities;
	
	
	/**  Registros filtrados. */
	private List<Catmun> filteredLocalitiesMunicipality;
	

	

	/**
	 * Gets the filtered localities municipality.
	 *
	 * @return the filtered localities municipality
	 */
	public List<Catmun> getFilteredLocalitiesMunicipality() {
		return filteredLocalitiesMunicipality;
	}

	/**
	 * Sets the filtered localities municipality.
	 *
	 * @param filteredLocalitiesMunicipality the new filtered localities municipality
	 */
	public void setFilteredLocalitiesMunicipality(List<Catmun> filteredLocalitiesMunicipality) {
		this.filteredLocalitiesMunicipality = filteredLocalitiesMunicipality;
	}

	/** Nombre del reporte en texto plano. */
	//@Value("${file.name.report.txt.localidadesAyuntamiento}")
	protected static final String REPORT_NAME_PLAIN_TEXT= FrontProperty.getPropertyValue("file.name.report.txt.localidadesAyuntamiento");

	/** Encabezados reporte de texto plano. */
	//@Value("${header.text.plain.localities.municipality}")
	protected String HEADERS_REPORT_TEXT_PLAIN= FrontProperty.getPropertyValue("header.text.plain.localities.municipality");

	/** The Constant ERROR_MESSAGE_CVEMUN_ZERO. */
	//@Value("${catalog.message.error.localities.cvemun.zero}")
	private static final String ERROR_MESSAGE_CVEMUN_ZERO= FrontProperty.getPropertyValue("catalog.message.error.localities.cvemun.zero");

	/** The Constant ERROR_MESSAGE_CVELOC_ZERO. */
	//@Value("${catalog.message.error.localities.cveloc.zero}")
	private static final String ERROR_MESSAGE_CVELOC_ZERO= FrontProperty.getPropertyValue("catalog.message.error.localities.cveloc.zero");

	/** The Constant ERROR_MESSAGE_UNIQUE. */
	//@Value("${catalog.message.error.localities.unique}")
	private static final String ERROR_MESSAGE_UNIQUE= FrontProperty.getPropertyValue("catalog.message.error.localities.unique");

	/** The Constant ERROR_MESSAGE_DATA_REQUIRED. */
	//@Value("${catalog.message.data.required.save}")
	private static final String ERROR_MESSAGE_DATA_REQUIRED= FrontProperty.getPropertyValue("catalog.message.data.required.save");

	/** Ruta donde se encuentra el archivo jasper del reporte. */
	//@Value("${view.report.path.jasper.catalog_localities_municipality}")
	private static final String REPORT_PATH_JASPER_FILE= FrontProperty.getPropertyValue("view.report.path.jasper.catalog_localities_municipality");

	/**
	 * Sets the localidades repository.
	 *
	 * @param localidadesRepository the new localidades repository
	 */
	public void setLocalidadesRepository(LocalidadesRepository localidadesRepository) {
		this.localidadesRepository = localidadesRepository;
	}

	/**
	 * Sets the catmun repository.
	 *
	 * @param catmunRepository the new catmun repository
	 */
	public void setCatmunRepository(CatmunRepository catmunRepository) {
		this.catmunRepository = catmunRepository;
	}

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(
				":: En postconsruct catalogLocalitiesMunicipalityMB " + this + "::" + getBeanFind() + "::" + getList());
		setBeanFind(new Localidades());
		setList(new ArrayList<Localidades>());
		setListNew(new ArrayList<Localidades>());
		setQueryOrderBy(Boolean.TRUE);
		this.reportNameTextPlain = REPORT_NAME_PLAIN_TEXT;
		this.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;
	}

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogLocalitiesMunicipalityMB  ");
		getBeanFind().setCveMun(null);
		getBeanFind().setCveLoc(null);
		getBeanFind().setNomLoc(null);
		restartData();
	}

	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {	
		LOGGER.info(":: Editando Fila catalogLocalitiesMunicipalityMB  " + event.getObject());
		final StringBuilder errorMsg = new StringBuilder();
		final Localidades catalog = (Localidades) event.getObject();
		catalog.setCapturo(this.getUserDetails().getUsername());
		catalog.setFeccap(GregorianCalendar.getInstance().getTime());
		if (isValidCapturedData(catalog, errorMsg) && isValidIntegrity(catalog, errorMsg)) {
			
//				executeOperationSaveOrUpdate(catalog, localidadesRepository);
			this.localidadesRepository.save(catalog);
			this.consultList();
			RequestContext.getCurrentInstance().execute("document.getElementById('form1:hideButton3').click();");
			
		} else {
			if (errorMsg.length() == ZERO) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, ERROR_MESSAGE_DATA_REQUIRED);
			} else {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, errorMsg.toString());
			}
			activateRowEdit(catalog.getIndex());
		}
	}

	/**
	 * Checks if is valid integrity.
	 *
	 * @param catalog the catalog
	 * @param errorMsg the error msg
	 * @return true, if is valid integrity
	 */
	private boolean isValidIntegrity(final Localidades catalog, final StringBuilder errorMsg) {
		boolean isValid = Boolean.TRUE;
		
		final Predicate predicate = LocalidadesPredicates.isCveMunAndClvLocUnique(catalog.getCveMun(),
				catalog.getCveLoc());
		if (localidadesRepository.exists(predicate) && (catalog.getId() == null || catalog.getId() == 0)) {
			errorMsg.append(ERROR_MESSAGE_UNIQUE);
			isValid = Boolean.FALSE;
		} else {
			isValid = Boolean.TRUE;
		}
		
		if (BooleanUtils.negate(catalog.getId() == ZERO)) {
			Localidades aux = null;
			for (final Localidades loc : getList()) {
				if (loc.getId().equals(catalog.getId())) {
					aux = loc;
					break;
				}
			}
			if (catalog.getIndex() == aux.getIndex()) {
				return isValid;
			}
		}
		
		return isValid;
	}

	/**
	 * Elimina un registro.
	 */
	public void delete() {
		LOGGER.info(":: Borrar registro catalogLocalitiesMunicipalityMB " + getBeanSelected());
		if (BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {
			localidadesRepository.delete(getBeanSelected());
		}
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_DELETED_RECORD);
		restartDataOperationDelete();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultList()
	 */
	@Override
	protected void consultList() {
		LOGGER.info(":: Buscar filas catalogLocalitiesMunicipalityMB " + getBeanFind());
		addList(IteratorUtils.toList(this.localidadesRepository.findAll(LocalidadesPredicates.byComposite(getBeanFind()), BY_CVEMUN_CVELOV).iterator()));
		restartFilteredList();
		LOGGER.info(":: Resultado de busqueda catalogLocalitiesMunicipalityMB " + getList());
	}
	
	/** The by cvemun cvelov. */
	Sort BY_CVEMUN_CVELOV = new Sort(Sort.Direction.ASC, "cveMun", "cveLoc");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@Override
	protected void consultAll() {
		setList(localidadesRepository.findAllByOrderByCveMunAscCveLocAsc());
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
	 * Checks if is valid captured data.
	 *
	 * @param catalog the catalog
	 * @param errorMsg the error msg
	 * @return true, if is valid captured data
	 */
	private boolean isValidCapturedData(final Localidades catalog, final StringBuilder errorMsg) {
		final boolean result;
		if (catalog.getCveMun() == null || catalog.getCveMun().intValue() == ZERO) {
			errorMsg.append("Se debe capturar el campo: Municipio ");
			result = Boolean.FALSE;
		} else {
			result = CollectionUtils.isNotEmpty(catmunRepository.findByClvmun(catalog.getCveMun()));
		}
		if (errorMsg.length() == ZERO && BooleanUtils.negate(result)) {
			errorMsg.append("La informacion capturada no existe en la Base de datos.");
		}
		if (this.localidadesRepository.count(
				LocalidadesPredicates.isCveMunAndClvLocUnique(catalog.getCveMun(), catalog.getCveLoc())) > 0) {
			errorMsg.append("La informacion ya fue capturada previamente.");
		}
		return result;
	}

	/**
	 * Consult localities.
	 */
	public void consultLocalities() {
		LOGGER.info(":: Consultar localidades ");
		localities = catmunRepository.findAll();
		restartFilteredLocalitiesMunicipality();
		LOGGER.info(":: Municipios: " + localities);
	}

	/**
	 * Restart filtered localities municipality.
	 */
	private void restartFilteredLocalitiesMunicipality() {
		
		if(BooleanUtils.negate(filteredLocalitiesMunicipality == null) && 
				BooleanUtils.negate(localities == null)) {
			filteredLocalitiesMunicipality.clear();
			filteredLocalitiesMunicipality.addAll(localities);
		}

	}
	
	/**
	 * Change locality.
	 */
	public void changeLocality() {
		LOGGER.info(":: Cambiar Localidad ");
		rowSelected.setCveMun(localitySelected.getClvmun());
		activateRowEdit(rowSelected.getIndex());
	}

	/**
	 * Activate row edit.
	 */
	public void activateRowEdit() {
		super.activateRowEdit(rowSelected.getIndex());
	}

	/**
	 * Gets the locality selected.
	 *
	 * @return the locality selected
	 */
	public Catmun getLocalitySelected() {
		return localitySelected;
	}

	/**
	 * Sets the locality selected.
	 *
	 * @param localitySelected the new locality selected
	 */
	public void setLocalitySelected(final Catmun localitySelected) {
		if (BooleanUtils.negate(rowSelected == null) && BooleanUtils.negate(localitySelected == null)) {
			this.localitySelected = localitySelected;
		}
	}

	/**
	 * Gets the row selected.
	 *
	 * @return the row selected
	 */
	public Localidades getRowSelected() {
		return rowSelected;
	}

	/**
	 * Sets the row selected.
	 *
	 * @param rowSelected the new row selected
	 */
	public void setRowSelected(final Localidades rowSelected) {
		this.rowSelected = rowSelected;
	}

	/**
	 * Gets the localities.
	 *
	 * @return the localities
	 */
	public List<Catmun> getLocalities() {
		return localities;
	}

	/**
	 * Sets the localities.
	 *
	 * @param localities the new localities
	 */
	public void setLocalities(final List<Catmun> localities) {
		this.localities = localities;
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
	 * Evento doble click.
	 *
	 * @param event the event
	 */
	public void onClvmunRowDblClckSelect(final SelectEvent event) {
		this.changeLocality();
		// rest of your logic
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#onRowCancel(org.primefaces.event.RowEditEvent)
	 */
	@Override
	public void onRowCancel(RowEditEvent event) {

//		super.onRowCancel(event);
		restartData();
	}
	
	/**
	 * Show canceled message.
	 */
	public void showCanceledMessage(){
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_EDITION_CANCELED); 
	}
	
	/**
	 * Show succes message.
	 */
	public void showSuccesMessage(){
		generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_EDITION_SUCCESS);	
	}
	
}
