package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.BooleanUtils;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.domain.Maycta;
import com.gem.sistema.business.predicates.MayctaPredicates;
import com.gem.sistema.business.repository.catalogs.MayctaRepository;
import com.gem.sistema.business.service.catalogos.MayCtaService;
import com.gem.sistema.web.util.FrontProperty;
import com.mysema.query.types.Predicate;

// TODO: Auto-generated Javadoc
/**
 * The Class CatalogLevelsAccountsMB.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@ManagedBean
@ViewScoped
public class CatalogLevelsAccountsMB extends CommonCatalogMB<Maycta> implements Serializable {
	
	/** Constante de la version del objeto. */
	private static final long serialVersionUID = 1L;	

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogLevelsAccountsMB.class);
	
	/** The may cta service. */
	@ManagedProperty(value = "#{mayCtaServiceImpl}")
	private MayCtaService mayCtaService;

	/**  Componente de servicio. */
	@ManagedProperty(value = "#{mayctaRepository}")
	private MayctaRepository mayctaRepository;
	
	/**  Campo requerido Clave de Contrato. */
	//@Value("${catalog.message.field.required.cuenta}")
	private static final String FIELD_REQUIRED_CUENTA= FrontProperty.getPropertyValue("catalog.message.field.required.cuenta");
	
	/**  Nombre del reporte en texto plano. */
	//@Value("${file.name.report.txt.nivelesCuentasMayor}")
	protected static final String REPORT_NAME_PLAIN_TEXT= FrontProperty.getPropertyValue("file.name.report.txt.nivelesCuentasMayor");
	
	/**  Encabezados reporte de texto plano. */
	//@Value("${header.text.plain.levels.accounts}")
	protected String HEADERS_REPORT_TEXT_PLAIN= FrontProperty.getPropertyValue("header.text.plain.levels.accounts");
	
	/**  Ruta donde se encuentra el archivo jasper del reporte. */
	//@Value("${view.report.path.jasper.catalog_levels_accounts}")
	private static final String REPORT_PATH_JASPER_FILE= FrontProperty.getPropertyValue("view.report.path.jasper.catalog_levels_accounts");
	
	/** The Constant ORDER_BY_FIELD_CUENTA. */
	/*
	 * Campo por el cual se ordenará la info
	 */
	private static final String ORDER_BY_FIELD_CUENTA ="cuenta";

	/**
	 * Sets the may cta service.
	 *
	 * @param mayCtaService the new may cta service
	 */
	public void setMayCtaService(MayCtaService mayCtaService) {
		this.mayCtaService = mayCtaService;
	}

	/**
	 * Sets the maycta repository.
	 *
	 * @param mayctaRepository the new maycta repository
	 */
	public void setMayctaRepository(MayctaRepository mayctaRepository) {
		this.mayctaRepository = mayctaRepository;
	}


	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct catalogLevelsAccountsMB  ");				
		setBeanFind(new Maycta());		
		setList(new ArrayList<Maycta>());
		setListNew(new ArrayList<Maycta>());
		setQueryOrderBy(Boolean.TRUE);
		this.reportNameTextPlain = REPORT_NAME_PLAIN_TEXT;
		this.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;
	}		

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogLevelsAccountsMB  ");					
		getBeanFind().setCuenta(null);			
		getBeanFind().setNommay(null);
		getBeanFind().setNivcta(null);
		getBeanFind().setNatcta(null);
		restartData();		
	}
	
	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {
		LOGGER.info(":: Editando Fila catalogLevelsAccountsMB  " + event.getObject());		
		final Maycta catalog = (Maycta) event.getObject();
		final StringBuilder errorMsg = new StringBuilder();
		if(mayCtaService.isValidPreviousLevel(catalog, errorMsg) && validateSaveOrUpdate(catalog)) {
			executeOperationSaveOrUpdate(catalog, mayctaRepository);
		} else {
			if(errorMsg.length() == ZERO) {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_UPDATE_UNIQUE.concat(FIELD_REQUIRED_CUENTA).
										  concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
			} else {
				generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR,errorMsg.toString());
			}
			activateRowEdit(catalog.getIndex());
		}						
	}     	
	
	/**
	 * Validate save or update.
	 *
	 * @param catalog the catalog
	 * @return true, if successful
	 */
	private boolean validateSaveOrUpdate(final Maycta catalog) {		
		final List<Maycta> validateUnique = mayctaRepository.findByCuenta(catalog.getCuenta());		
		return isValidSaveOrUpdate(validateUnique, catalog);		
	}

	/**
	 * Elimina un registro.
	 */
	public void delete() {
		LOGGER.info(":: Borrar registro catalogLevelsAccountsMB " + getBeanSelected());
		if(BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {
			mayctaRepository.delete(getBeanSelected());
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
		LOGGER.info(":: Buscar filas catalogLevelsAccountsMB " + getBeanFind());			
		addList((List<Maycta>) repositoryCustom.findByFiltersOrderBy(getBeanFind(), ORDER_BY_FIELD_CUENTA));		
		LOGGER.info(":: Resultado de busqueda catalogLevelsAccountsMB " + getList());
	}						

	/*
	 * (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@Override
	protected void consultAll() {		
		final Predicate predicate = MayctaPredicates.getNatctaIsNotEmpty();  
		setList((List<Maycta>)mayctaRepository.findAll(predicate, MayctaPredicates.getOrder()));
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
}

