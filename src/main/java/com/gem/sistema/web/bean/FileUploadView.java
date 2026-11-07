package com.gem.sistema.web.bean;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.repository.catalogs.RepositoryImpl;
import com.gem.sistema.load.fileupload.exceptions.ValidateFileException;
import com.gem.sistema.load.fileupload.model.FileUpload;
import com.gem.sistema.load.fileupload.service.InboundFileService;
import com.gem.sistema.load.fileupload.validators.FileContentValidator;

// TODO: Auto-generated Javadoc
/**
 * The Class FileUploadView.
 */
@Component(value="fileUploadView")
@ManagedBean
@SessionScoped
public class FileUploadView extends AbstractMB{
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryImpl.class);
		
	
	/** The Constant SUCCESS_PAGE. */
	private static final String SUCCESS_PAGE="/views/budget/expenses/uploadSuccess.xhtml?faces-redirect=true";
	
	/** The Constant ERROR_PAGE. */
	private static final String ERROR_PAGE = "/views/budget/expenses/init.xhtml?faces-redirect=true";
	
	/** The Constant DEFAULT_PATH. */
	private static final String DEFAULT_PATH ="/tmp/";
	
	/** The Constant CLAVE. */
	private static final String CLAVE ="xcargapree";
	
	/** The egresos csv validator. */
	@Autowired
    @Qualifier("egresosCsvValidator")
    private FileContentValidator egresosCsvValidator;
    
	
    /** The file service. */
    @Autowired
    private InboundFileService fileService;
	
	/** The file. */
	private UploadedFile file;
	
	/** The clave. */
	private String clave;
	
	/** The accion. */
	private String accion;
	
	/** The cve ok. */
	private Boolean cveOk;
	 
    /**
     * Gets the file.
     *
     * @return the file
     */
    public UploadedFile getFile() {
        return file;
    }
 
    /**
     * Sets the file.
     *
     * @param file the new file
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
    
     
    /**
     * Gets the clave.
     *
     * @return the clave
     */
    public String getClave() {
		return clave;
	}

	/**
	 * Sets the clave.
	 *
	 * @param clave the new clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	
	
	/**
	 * Gets the accion.
	 *
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * Sets the accion.
	 *
	 * @param accion the new accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * Upload.
	 *
	 * @return the string
	 */
	public String upload() {
		File newFile   = null;
		boolean isValid=Boolean.FALSE;
		
        if(file != null) {

        	newFile = new File( DEFAULT_PATH + UUID.randomUUID() +"-" + file.getFileName() );
        	
        	try {
				FileUtils.copyInputStreamToFile(file.getInputstream(), newFile);
				
				final com.gem.sistema.load.fileupload.model.FileUpload fileUpload = new FileUpload();
				
				fileUpload.setFile(newFile);

		        fileUpload.setNameOriginal(newFile.getName());

		        fileUpload.setNameReal(newFile.getName());
		        

		        isValid=this.egresosCsvValidator.isValid(fileUpload);
		        
		        if(isValid){
		        	this.fileService.validateContent(fileUpload, true, this.getUserDetails().getIdSector());
		        	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", file.getFileName() + " se ha cargado, verifique la salida");
			        FacesContext.getCurrentInstance().addMessage(null, message);
		        }else{
		        	 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", file.getFileName() + "  presenta errores de formato");
			         FacesContext.getCurrentInstance().addMessage(null, message);
		        }
		        
		        
		        
				
				
			} catch (IOException | ValidateFileException e) {
				 FacesMessage message = new FacesMessage("Error", file.getFileName() + " no se ha cargado.");
		            FacesContext.getCurrentInstance().addMessage(null, message);
			}catch(Exception e){
				FacesMessage message = new FacesMessage("Error", file.getFileName() + " no se ha cargado.");
	            FacesContext.getCurrentInstance().addMessage(null, message);
			}
        	
        	
			
        	
           
        }else {
        	FacesMessage message = new FacesMessage("Error", "Archivo es obligatorio.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        	
        }
        
        if(validaArchivo(newFile)) {
			return SUCCESS_PAGE;
			
		}
        
        return ERROR_PAGE;
        
    }
    
    /**
     * Login a la carga de archivos.
     * @param event .
     */
    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
        
        LOGGER.info("Recibiendo clave:" + clave);
        
        if(StringUtils.equals(clave, CLAVE)) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido",null);
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Clave Invalida");
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        cveOk = loggedIn;
        

        LOGGER.info("valor de cveOk:" + cveOk);
       
        context.addCallbackParam("loggedIn", loggedIn);
    }

	/**
	 * Gets the cve ok.
	 *
	 * @return the cve ok
	 */
	public Boolean getCveOk() {
		return cveOk;
	}

	/**
	 * Sets the cve ok.
	 *
	 * @param cveOk the new cve ok
	 */
	public void setCveOk(Boolean cveOk) {
		this.cveOk = cveOk;
	}   
	
	
	/**
	 * Valida archivo.
	 *
	 * @param newFile the new file
	 * @return true, if successful
	 */
	private boolean validaArchivo( File newFile ){
		String  name = newFile.getName();
		
		 LOGGER.info("Validando nombre de archivo" + name);
		
		if ( StringUtils.containsIgnoreCase(name, ".csv")){
			 LOGGER.info("Archvo si conteine .csv");
			return true;
		}
		
		return false;
	}
    
    
    
}
