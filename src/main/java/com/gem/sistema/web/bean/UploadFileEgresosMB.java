package com.gem.sistema.web.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.File;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import org.primefaces.model.file.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class UploadFileEgresosMB.
 */
@Component("uploadFileEgresosMB")
@Scope("view")
public class UploadFileEgresosMB extends AbstractMB{
	
	/** Constante para utilizar el log de la aplicacion. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PolizaCopiarMB.class);
	
	/** The file. */
	private UploadedFile file;
	
	/** The Constant pathUploadFile. */
	private static final String pathUploadFile = "/tmp/uploadfiles";
	
	/** The Constant pathDownlodFile. */
	private static final String pathDownlodFile = "/tmp/egresos";

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
	 * Inits the.
	 */
	@PostConstruct
	public void init(){
		generateFolders(pathUploadFile);
		generateFolders(pathDownlodFile);
		
	}
	
	/**
	 * Upload.
	 */
	public void upload(){
		File newFile = new File(file.getFileName());
		if(isFileSelect(newFile)){
			LOGGER.info("hola::..");
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Seleccione un archivo por favor!");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
	}
	
	/**
	 * Checks if is file select.
	 *
	 * @param file the file
	 * @return true, if is file select
	 */
	public boolean isFileSelect(File file){
		if(file.getName().equals(null)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Generate folders.
	 *
	 * @param pathFile the path file
	 */
	public void generateFolders(String pathFile){
		File newFolders = new File(pathFile);
		newFolders.mkdirs();
	}
	

}
