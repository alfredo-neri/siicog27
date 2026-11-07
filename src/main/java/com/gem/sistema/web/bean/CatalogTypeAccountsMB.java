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

import com.gem.sistema.business.domain.Tipcta;
import com.gem.sistema.business.repository.catalogs.TipctaRepository;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * Controlador del catalogo de Cuentas.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@ManagedBean
@ViewScoped
public class CatalogTypeAccountsMB extends CommonCatalogMB<Tipcta> implements Serializable {

	/**  Constante de la version del objeto. */
	private static final long serialVersionUID = 1L;
	
	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogTypeAccountsMB.class);		

	/**  Componente de servicio. */
	@ManagedProperty(value = "#{tipctaRepository}")
	private TipctaRepository tipctaRepository;

	/**  Campo requerido Clave de Contrato. */
	//@Value("${catalog.message.field.required.clvtip}")
	private static final String FIELD_REQUIRED_CLVTIP= FrontProperty.getPropertyValue("catalog.message.field.required.clvtip");
	
	/**  Nombre del reporte en texto plano. */
	//@Value("${file.name.report.txt.tipoCuentas}")
	protected static final String REPORT_NAME_PLAIN_TEXT= FrontProperty.getPropertyValue("file.name.report.txt.tipoCuentas");
	
	/**  Encabezados reporte de texto plano. */
	//@Value("${header.text.plain.catalog.accounts}")
	protected static final String HEADERS_REPORT_TEXT_PLAIN= FrontProperty.getPropertyValue("header.text.plain.catalog.accounts");
	
	/**  Ruta donde se encuentra el archivo jasper del reporte. */
	//@Value("${view.report.path.jasper.catalog_accounts}")
	private static final String REPORT_PATH_JASPER_FILE= FrontProperty.getPropertyValue("view.report.path.jasper.catalog_accounts");
	
	
	/**
	 * Sets the tipcta repository.
	 *
	 * @param tipctaRepository the new tipcta repository
	 */
	public void setTipctaRepository(TipctaRepository tipctaRepository) {
		this.tipctaRepository = tipctaRepository;
	}
		
	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
    public void init() {
		LOGGER.info(":: En postconsruct ");				
		setList(new ArrayList<Tipcta>());
		setListNew(new ArrayList<Tipcta>());
		setBeanFind(new Tipcta());
		this.reportNameTextPlain = REPORT_NAME_PLAIN_TEXT;
		this.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;
    }		
	
	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */	
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogTypeAccountsMB ");
		restartData();		
	}
		
	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {
		LOGGER.info(":: Editando Fila catalogTypeAccountsMB " + event.getObject());		
		final Tipcta catalog = (Tipcta) event.getObject();
		if(isValidUpdateOrSave(catalog)) {
			executeOperationSaveOrUpdate(catalog, tipctaRepository);
		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, MESSAGE_ERROR_UPDATE_UNIQUE.concat(FIELD_REQUIRED_CLVTIP).
																	 concat(MESSAGE_ERROR_UPDATE_UNIQUE_COMPLEMENT));
			activateRowEdit(catalog.getIndex());
		}						
	}     	
	
	
	
	/**
	 * Checks if is valid update or save.
	 *
	 * @param catalog the catalog
	 * @return true, if is valid update or save
	 */
	private boolean isValidUpdateOrSave(final Tipcta catalog) {
		final List<Tipcta> validateUnique = tipctaRepository.findByClvtip(catalog.getClvtip());
		return isValidSaveOrUpdate(validateUnique, catalog);				
	}

    /**
     * Elimina un registro.
     */
    public void delete() {
    	LOGGER.info(":: Borrar registro catalogTypeAccountsMB " + getBeanSelected());
    	if(BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {
    		tipctaRepository.delete(getBeanSelected());
    	}    	
    	generateNotificationFront(SEVERITY_INFO, MESSAGE_INFO, MESSAGE_DELETED_RECORD);
    	restartDataOperationDelete();    	
    }

    /*
     * (non-Javadoc)
     * @see com.gem.sistema.web.bean.CommonCatalogMB#consultList()
     */
	@Override
	protected void consultList() {
		consultAll();				
	}	
	
	/*
	 * (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@Override
	protected void consultAll() {
		LOGGER.info(":: Consultar todo catalogTypeAccountsMB ");		
		setList(tipctaRepository.findAll());
	}

	/*
	 * (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#isValidRequest()
	 */
	@Override
	protected boolean isValidRequest() {
		return Boolean.TRUE;
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

}
