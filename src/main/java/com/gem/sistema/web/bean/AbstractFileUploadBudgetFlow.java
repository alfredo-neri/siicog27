package com.gem.sistema.web.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gem.sistema.business.repository.catalogs.RepositoryImpl;
import com.gem.sistema.business.service.catalogos.ParametroService;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractFileUploadBudgetFlow.
 */
public abstract class AbstractFileUploadBudgetFlow extends AbstractMB {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryImpl.class);

	/** The defaul path. */
	protected String defaulPath;
	
	/** The defaul path errores. */
	protected String defaulPathErrores;
	
	/** The file upload request page. */
	protected String fileUploadRequestPage;
	
	/** The file upload response file. */
	protected String fileUploadResponseFile;
	
	/** The init page. */
	protected String initPage;
	
	/** The default clave. */
	protected String defaultClave;
	
	/** The reviewed. */
	protected boolean reviewed;

	/** The accion. */
	protected String accion;

	/** The clave. */
	protected String clave;

	/** The error file name. */
	protected String errorFileName;

	/** The out put file name. */
	protected String outPutFileName;

	/** The file. */
	protected UploadedFile file;

	/** The parametro service. */
	@Autowired
	@Qualifier("parametroServiceImpl")
	private ParametroService parametroService;

	/**
	 * Retrieve error document.
	 *
	 * @param filename the filename
	 * @return the streamed content
	 */
	public StreamedContent retrieveErrorDocument(String filename) {
		LOGGER.info("Recibiendo Archivo" + filename);
		try {
			File file = new File(filename);
			return new DefaultStreamedContent(new FileInputStream(file), "text/plain", file.getName());
		} catch (FileNotFoundException e) {
			LOGGER.error("Error Descargando Archivo" + e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Login a la carga de archivos.
	 *
	 * @return the string
	 */
	public String checkClave() {

		LOGGER.info("Recibiendo clave:" + clave);
		this.errorFileName = "";
		this.outPutFileName = "";

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
	private static final String CSV_CONTENT = "CSV_TYPE_CONTENT";

	/**
	 * Valida archivo.
	 *
	 * @param newFile the new file
	 * @return true, if successful
	 */
	protected boolean validaArchivo(File newFile) {
		String ext = FilenameUtils.getExtension(newFile.getName());
		return this.parametroService.isInValues(CSV_CONTENT, file.getContentType())
				&& StringUtils.containsIgnoreCase("csv", ext);

	}

	/**
	 * Procesar archivo.
	 *
	 * @return the string
	 */
	public String procesarArchivo() {

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
	public static Logger getLogger() {
		return LOGGER;
	}

	/**
	 * Gets the defaul path.
	 *
	 * @return the defaul path
	 */
	public abstract String getDefaulPath();

	/**
	 * Gets the defaul path errores.
	 *
	 * @return the defaul path errores
	 */
	public abstract String getDefaulPathErrores();

	/**
	 * Gets the file upload request page.
	 *
	 * @return the file upload request page
	 */
	public abstract String getFileUploadRequestPage();

	/**
	 * Gets the file upload response file.
	 *
	 * @return the file upload response file
	 */
	public abstract String getFileUploadResponseFile();

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
	public abstract String getDefaultClave();

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
	 * @param outPutFileName            the outPutFileName to set
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
	 * @param reviewed            the reviewed to set
	 */
	public void setReviewed(boolean reviewed) {
		this.reviewed = reviewed;
	}

	/**
	 * Gets the parametro service.
	 *
	 * @return the parametroService
	 */
	public ParametroService getParametroService() {
		return parametroService;
	}

	/**
	 * Sets the parametro service.
	 *
	 * @param parametroService            the parametroService to set
	 */
	public void setParametroService(ParametroService parametroService) {
		this.parametroService = parametroService;
	}

}
