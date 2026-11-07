package com.gem.sistema.web.bean;

import static com.gem.sistema.util.Constants.ZERO;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.BooleanUtils;
import org.primefaces.event.RowEditEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.domain.Cpd;
import com.gem.sistema.business.repository.catalogs.CpdRepository;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class CatalogTopicsProgressMB.
 *
 * @author Juan Carlos Pedraza Alcala
 */
@ManagedBean
@ViewScoped
public class CatalogTopicsProgressMB extends CommonCatalogMB<Cpd> implements Serializable {
	
	/** Constante de la version del objeto. */
	private static final long serialVersionUID = 1L;	

	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CatalogTopicsProgressMB.class);		

	/**  Componente de servicio. */
	@ManagedProperty(value = "#{cpdRepository}")
	private CpdRepository cpdRepository;			

	/**  Nombre del reporte en texto plano. */
	//@Value("${file.name.report.txt.temasDesarrollo}")
	protected String REPORT_NAME_PLAIN_TEXT= FrontProperty.getPropertyValue("file.name.report.txt.temasDesarrollo");
	
	/**  Encabezados reporte de texto plano. */
	//@Value("${header.text.plain.theme.growth}")
	protected static final String HEADERS_REPORT_TEXT_PLAIN= FrontProperty.getPropertyValue("header.text.plain.theme.growth");

	
	/**
	 * Sets the cpd repository.
	 *
	 * @param cpdRepository the new cpd repository
	 */
	public void setCpdRepository(CpdRepository cpdRepository) {
		this.cpdRepository = cpdRepository;
	}

	/**
	 * Inicializa los objetos.
	 */
	@PostConstruct
	public void init() {
		LOGGER.info(":: En postconsruct catalogTopicsProgressMB  ");								
		setList(new ArrayList<Cpd>());
		setListNew(new ArrayList<Cpd>());
		this.reportNameTextPlain = REPORT_NAME_PLAIN_TEXT;
		this.headersReportTextPlain = HEADERS_REPORT_TEXT_PLAIN;
	}		

	/**
	 * Realiza las operaciones necesarias al cargar la pagina.
	 */
	public void onPageLoad() {
		LOGGER.info(":: Antes de cargar la pagina catalogTopicsProgressMB  ");
		restartData();		
	}


	/**
	 * Persiste la edicion de un registro.
	 *
	 * @param event the event
	 */
	public void onRowEdit(final RowEditEvent event) {
		LOGGER.info(":: Editando Fila catalogTopicsProgressMB  " + event.getObject());		
		final Cpd catalog = (Cpd) event.getObject();
		executeOperationSaveOrUpdate(catalog, cpdRepository);
	}     	

	/**
	 * Elimina un registro.
	 */
	public void delete() {
		LOGGER.info(":: Borrar registro catalogTopicsProgressMB " + getBeanSelected());
		if(BooleanUtils.negate(getBeanSelected().getId().intValue() == ZERO)) {
			cpdRepository.delete(getBeanSelected());
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
		LOGGER.info(":: Buscar filas catalogTopicsProgressMB " + getBeanFind());		
		consultAll();			
		LOGGER.info(":: Resultado de busqueda catalogTopicsProgressMB " + getList());
	}						

	/*
	 * (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#consultAll()
	 */
	@Override
	protected void consultAll() {
		setList(cpdRepository.findAll());
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
	
	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.CommonCatalogMB#onRowCancel(org.primefaces.event.RowEditEvent)
	 */
	@Override
	public void onRowCancel(RowEditEvent event) {
		restartData();
		super.onRowCancel(event);
	}
}
