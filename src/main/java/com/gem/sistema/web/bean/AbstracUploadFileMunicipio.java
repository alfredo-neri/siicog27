package com.gem.sistema.web.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gem.sistema.business.service.catalogos.ParametroService;
import com.gem.sistema.business.service.catalogos.ValidateContentTypeServiceImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstracUploadFileMunicipio.
 */
public abstract class AbstracUploadFileMunicipio extends AbstractMB {
	
	/** The Constant logger. */
	/*
	 * implematacion de log
	 *
	 */
	private static final Logger logger = LoggerFactory.getLogger(AbstracUploadFileMunicipio.class);
	
	/** The defaul path. */
	private String defaulPath;
	
	/** The defaul path errores. */
	private String defaulPathErrores ; 
	
	/** The file upload request page. */
	private String fileUploadRequestPage;
	
	/** The file upload response file. */
	private String fileUploadResponseFile;
	
	/** The init page. */
	private String initPage;
	
	/** The default clave. */
	private String defaultClave;
	
	/** The reviewed. */
	private boolean reviewed;
	
	/** The Constant content. */
	private static final String content = "CSV_TYPE_CONTENT";
	
	/** The parametro service. */
	@Autowired
	@Qualifier("parametroServiceImpl")
	private ParametroService parametroService;
	
	/**
	 * Gets the parametro service.
	 *
	 * @return the parametro service
	 */
	public ParametroService getParametroService() {
		return parametroService;
	}

	/**
	 * Sets the parametro service.
	 *
	 * @param parametroService the new parametro service
	 */
	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public static String getContent() {
		return content;
	}

	/**
	 * Gets the mimetypefile.
	 *
	 * @return the mimetypefile
	 */
	public static MimetypesFileTypeMap getMimetypefile() {
		return mimeTypeFile;
	}

	/** The Constant mimeTypeFile. */
	private static final MimetypesFileTypeMap mimeTypeFile = null;
	

	/** The accion. */
	private String accion;
	
	/** The clave. */
	private String clave;

	/** The error file name. */
	private String errorFileName;
	
	/** The out put file name. */
	private String outPutFileName;

	/** The file. */
	private UploadedFile file;
	
	/** The valida type. */
	private boolean validaType = Boolean.FALSE;
	
	/** The valida contet. */
	private ValidateContentTypeServiceImpl validaContet;

	

	/**
	 * Gets the valida contet.
	 *
	 * @return the valida contet
	 */
	public ValidateContentTypeServiceImpl getValidaContet() {
		return validaContet;
	}

	/**
	 * Sets the valida contet.
	 *
	 * @param validaContet the new valida contet
	 */
	public void setValidaContet(ValidateContentTypeServiceImpl validaContet) {
		this.validaContet = validaContet;
	}

	/**
	 * Retrieve error document.
	 *
	 * @param filename the filename
	 * @return the streamed content
	 */
	public StreamedContent retrieveErrorDocument(String filename) {
		logger.info("Recibiendo Archivo" + filename);
		try {
			File file = new File(filename);
			return new DefaultStreamedContent(new FileInputStream(file), "text/plain", file.getName());
		} catch (FileNotFoundException e) {
			logger.error("Error Descargando Archivo" + e);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Validate context.
	 */
	public void validateContext(){
		File newFile = null;
		String newName="";
	
		try {
			
				if(file.getFileName() != null) {
					newName= file.getFileName();
					newFile = new File(newName);
						if(validaArchivo(newFile)){
					    	
							upload();
					    } else {
					    	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", file.getFileName() + " Solo se permiten archivos csv");
							FacesContext.getCurrentInstance().addMessage(null, message);
					    }
				} else {
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", file.getFileName() + " Debe seleecionar una archivo");
					FacesContext.getCurrentInstance().addMessage(null, message);
				}
				
		} catch (Exception  io) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", file.getFileName() + " Solo se permiten archivos csv");
			 FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	/**
	 * Upload.
	 *
	 * @return the string
	 */
	public String upload() {
		File newFile = null;
		String newName="";
		errorFileName = null;
		logger.info("archivo ::: " + file.getFileName());
		
			if (file != null) {

				
//
				try {
					newName = getDefaulPath() + UUID.randomUUID() + "-" + file.getFileName();
					
					newName = FilenameUtils.removeExtension(newName).concat(".txt");
					
					
					newFile = new File("/gem/upfiles", newName);
					
					
					FileUtils.copyInputStreamToFile(file.getInputstream(), newFile);
					if(file != null ){
						//newFile = new File(getDefaulPath() + UUID.randomUUID() + "-" + FilenameUtils.removeExtension(file.getFileName()).concat(".txt"));
						
						
					    applyContenFileValidators(new File(file.getFileName().toString()));
					} else{
						     FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", file.getFileName() + " Seleccioneun arhivo por favor");
							 FacesContext.getCurrentInstance().addMessage(null, message);
					}
							
					return getFileUploadResponseFile();
						
					
					

				} catch (IOException e) {
					FacesMessage message = new FacesMessage("Error", file.getFileName() + " No ha sido cargado.");
					FacesContext.getCurrentInstance().addMessage(null, message);
				}	
			}
			
		
			
		

		
		return null;
	}

	/**
	 * Login a la carga de archivos.
	 *
	 * @return the string
	 */
	public String checkClave() {

		logger.info("Recibiendo clave:" + clave);
		this.errorFileName="";
		this.outPutFileName="";

		if (StringUtils.equals(getClave(), getDefaultClave())) {
			accion = getClave();
			return getFileUploadRequestPage();
		} else {
			 FacesMessage message = new FacesMessage("Error", "Clave inválida");
	            FacesContext.getCurrentInstance().addMessage(null, message);
			return getInitPage();
		}

	}

	/** The Constant CSV_CONTENT. */
	private static final String CSV_CONTENT = "application/vnd.ms-excel";
	
	/**
	 * Valida archivo.
	 *
	 * @param newFile the new file
	 * @return true, if successful
	 */
	private boolean validaArchivo( File newFile ){
		return StringUtils.containsIgnoreCase(CSV_CONTENT, file.getContentType());
		
	}
	
	
	/**
	 * Procesar archivo.
	 *
	 * @return the string
	 */
	public String procesarArchivo(){
		
		return "";
	}

	/**
	 * Sets the defaul path.
	 *
	 * @param defaulPath the new defaul path
	 */
	public void setDefaulPath(String defaulPath) {
		this.defaulPath = defaulPath;
	}

	/**
	 * Sets the defaul path errores.
	 *
	 * @param defaulPathErrores the new defaul path errores
	 */
	public void setDefaulPathErrores(String defaulPathErrores) {
		this.defaulPathErrores = defaulPathErrores;
	}

	/**
	 * Sets the file upload request page.
	 *
	 * @param fileUploadRequestPage the new file upload request page
	 */
	public void setFileUploadRequestPage(String fileUploadRequestPage) {
		this.fileUploadRequestPage = fileUploadRequestPage;
	}

	/**
	 * Sets the file upload response file.
	 *
	 * @param fileUploadResponseFile the new file upload response file
	 */
	public void setFileUploadResponseFile(String fileUploadResponseFile) {
		this.fileUploadResponseFile = fileUploadResponseFile;
	}

	/**
	 * Sets the inits the page.
	 *
	 * @param initPage the new inits the page
	 */
	public void setInitPage(String initPage) {
		this.initPage = initPage;
	}

	/**
	 * Sets the default clave.
	 *
	 * @param defaultClave the new default clave
	 */
	public void setDefaultClave(String defaultClave) {
		this.defaultClave = defaultClave;
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
	 * Sets the clave.
	 *
	 * @param clave the new clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Sets the error file name.
	 *
	 * @param errorFileName the new error file name
	 */
	public void setErrorFileName(String errorFileName) {
		this.errorFileName = errorFileName;
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
	 * Gets the logger.
	 *
	 * @return the logger
	 */
	public static Logger getlogger() {
		return logger;
	}

	/**
	 * Gets the defaul path.
	 *
	 * @return the defaul path
	 */
	public abstract String getDefaulPath() ;

	/**
	 * Gets the defaul path errores.
	 *
	 * @return the defaul path errores
	 */
	public abstract String getDefaulPathErrores() ;

	/**
	 * Gets the file upload request page.
	 *
	 * @return the file upload request page
	 */
	public abstract String getFileUploadRequestPage() ;

	/**
	 * Gets the file upload response file.
	 *
	 * @return the file upload response file
	 */
	public abstract String getFileUploadResponseFile() ;

	/**
	 * Gets the inits the page.
	 *
	 * @return the inits the page
	 */
	public abstract String getInitPage();

	/**
	 * Gets the default clave.
	 *
	 * @return the default clave
	 */
	public abstract String getDefaultClave() ;

	/**
	 * Gets the accion.
	 *
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
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
	 * Gets the error file name.
	 *
	 * @return the error file name
	 */
	public String getErrorFileName() {
		return errorFileName;
	}

	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public UploadedFile getFile() {
		return file;
	}
	
	/**
	 * Apply conten file validators.
	 *
	 * @param file the file
	 * @return true, if successful
	 */
	public abstract boolean applyContenFileValidators(File file);
	
	
	/**
	 * Load data into table.
	 */
	public abstract void loadDataIntoTable();

	/**
	 * Gets the out put file name.
	 *
	 * @return the outPutFileName
	 */
	public String getOutPutFileName() {
		return outPutFileName;
	}

	/**
	 * Sets the out put file name.
	 *
	 * @param outPutFileName the outPutFileName to set
	 */
	public void setOutPutFileName(String outPutFileName) {
		this.outPutFileName = outPutFileName;
	}

	/**
	 * Checks if is reviewed.
	 *
	 * @return the reviewed
	 */
	public boolean isReviewed() {
		return reviewed;
	}

	/**
	 * Sets the reviewed.
	 *
	 * @param reviewed the reviewed to set
	 */
	public void setReviewed(boolean reviewed) {
		this.reviewed = reviewed;
	}	

}
