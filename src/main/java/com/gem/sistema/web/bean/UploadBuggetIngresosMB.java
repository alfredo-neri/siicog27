package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.dao.PresupuestoJDBCTemplateDAO;
import com.gem.sistema.business.dto.PresupuestoDTO;
import com.gem.sistema.business.repository.catalogs.ParametrosRepository;
import com.gem.sistema.business.utils.ConvertCharsetUtils;
import com.gem.sistema.load.fileupload.model.FileUpload;
import com.gem.sistema.load.fileupload.service.OutboundFIleService;
import com.gem.sistema.load.fileupload.validators.FileContentValidator;

// TODO: Auto-generated Javadoc
/**
 * The Class UploadBuggetIngresosMB.
 */
@Component(value = "uploadBuggetIngresosMB")
@Scope("session")
// @ManagedBean
// @RequestScoped
public class UploadBuggetIngresosMB extends AbstractFileUploadBudgetFlow {

	/** The Constant DEFAULT_PATH. */
	private static final String DEFAULT_PATH = "/gem/";
	
	/** The Constant DEFAULT_PATH_ERRORES. */
	private static final String DEFAULT_PATH_ERRORES = "/gem/errores/";
	
	/** The Constant DEFAULT_PATH_OUTVALID. */
	private static final String DEFAULT_PATH_OUTVALID = "/gem/outIngre/";
	
	/** The Constant FILE_UPLOAD_REQUEST_PAGE. */
	private static final String FILE_UPLOAD_REQUEST_PAGE = "/views/budget/incomes/fileUploadRequest.xhtml";
	
	/** The Constant FILE_UPLOAD_RESPONSE_PAGE. */
	private static final String FILE_UPLOAD_RESPONSE_PAGE = "/views/budget/incomes/uploadSuccess.xhtml";
	
	/** The Constant INIT_PAGE. */
	private static final String INIT_PAGE = "/views/budget/incomes/init.xhtml";
	
	/** The Constant DEFAULT_KEY. */
	private static final String DEFAULT_KEY = "xcargaprei";
	
	/** The Constant DEFUALT_PATH_UPLOAD. */
	// private static final String DEFAULT_PATH_DOWNLOAD = "/tmp/downcvs/";
	private static final String DEFUALT_PATH_UPLOAD = "/gem/upfiles/";

	/** The valid format. */
	private boolean validFormat = Boolean.FALSE;
	
	/** The valid data. */
	private boolean validData = Boolean.FALSE;

	/** The file service. */
	@Autowired
	OutboundFIleService fileService;

	/** The ingresos csv validator. */
	@Autowired
	@Qualifier("ingresosCsvValidator")
	FileContentValidator ingresosCsvValidator;

	/** The presupuesto JDBC template DAO impl. */
	@Autowired
	PresupuestoJDBCTemplateDAO presupuestoJDBCTemplateDAOImpl;
	
	/** The parametros repository. */
	@Autowired
	private ParametrosRepository parametrosRepository;

	/**
	 * Gets the parametros repository.
	 *
	 * @return the parametros repository
	 */
	public ParametrosRepository getParametrosRepository() {
		return parametrosRepository;
	}

	/**
	 * Sets the parametros repository.
	 *
	 * @param parametrosRepository the new parametros repository
	 */
	public void setParametrosRepository(ParametrosRepository parametrosRepository) {
		this.parametrosRepository = parametrosRepository;
	}

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadBuggetIngresosMB.class);

	/**
	 * Retrieve error document csv.
	 *
	 * @param filename the filename
	 * @return the streamed content
	 */
	public StreamedContent retrieveErrorDocumentCsv(String filename) {
		LOGGER.info("Recibiendo Archivo" + filename);
		try {
			File file = new File(filename);
			return new DefaultStreamedContent(new FileInputStream(file), "text/csv", file.getName());
		} catch (FileNotFoundException e) {
			LOGGER.error("Error Descargando Archivo" + e);
			e.printStackTrace();
		}
		return null;
	}

	/** The Constant charSetISO. */
	private final static String charSetISO = "ISO-8859-1";
	
	/** The Constant charSetUFT. */
	private final static String charSetUFT = "UTF-8";

	/**
	 * Upload.
	 *
	 * @return the string
	 */
	public String upload() {
		File newFile = null;
		File target = null;
		String newName = "";
		String newName2 = "";
		errorFileName = null;
		LOGGER.info("archivo ::: " + file.getFileName());
		if (StringUtils.isNotEmpty(file.getFileName())) {
			if (StringUtils.equals(accion, "R")) {

				if (file != null) {

					//
					try {
						newName = UUID.randomUUID() + "-" + file.getFileName();
						newName2 = UUID.randomUUID() + "-" + file.getFileName();

						newFile = new File(DEFUALT_PATH_UPLOAD + newName);
						target = new File(DEFUALT_PATH_UPLOAD + newName2);
						FileUtils.copyInputStreamToFile(file.getInputstream(), newFile);
						FileUtils.copyInputStreamToFile(file.getInputstream(), target);
						ConvertCharsetUtils.transform(newFile, charSetISO, target, charSetUFT);

						if (validaArchivo(newFile)) {
							if (applyContenFileValidators(newFile)) {

								// saveFile(DEFUALT_PATH_UPLOAD,
								// file.getFileName(), file.getInputstream());
								PresupuestoDTO presupuesto = new PresupuestoDTO();

								presupuesto.setiPprocesar(0); // this.processAccounts?1:0
								presupuesto.setiIdUser(this.getUserDetails().getUsername());
								presupuesto.setiIdSector(this.getUserDetails().getIdSector());
								presupuesto.setiIdRef(
										0 /* this.getUserDetails().get */); // falta
								presupuesto.setiFileName(newName);
								presupuesto.setiPathFile(DEFUALT_PATH_UPLOAD);
								presupuesto.setTipo("R");
								presupuestoJDBCTemplateDAOImpl.cargaArchivosIngresos(presupuesto);

								FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succesful",
										file.getFileName() + " se ha cargado, verifique la salida");
								FacesContext.getCurrentInstance().addMessage(null, message);
								// falta ver como recuperar el archivo !!!!

								this.setErrorFileName(inputPath() + File.separator + presupuesto.getoPathFile());
								this.setDefaulPathErrores(inputPath() + File.separator);

								return getFileUploadResponseFile();
							} else {
								// Hubo errores
								this.setErrorFileName(DEFAULT_PATH_ERRORES + newName);
								FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
										file.getFileName() + "  presenta errores de formato");
								FacesContext.getCurrentInstance().addMessage(null, message);

								return getFileUploadRequestPage();
							}

						} else {

							FacesMessage message = new FacesMessage("Error",
									file.getFileName() + " Tipo de archivo incorrecto.");
							FacesContext.getCurrentInstance().addMessage(null, message);
						}

					} catch (IOException e) {
						LOGGER.error(StringUtils.EMPTY);
						LOGGER.error(StringUtils.EMPTY);
						LOGGER.error(StringUtils.EMPTY);
						LOGGER.error(e.getMessage(), e);
						LOGGER.error(StringUtils.EMPTY);
						LOGGER.error(StringUtils.EMPTY);
						LOGGER.error(StringUtils.EMPTY);
						LOGGER.error(StringUtils.EMPTY);

						FacesMessage message = new FacesMessage("Error", file.getFileName() + " No ha sido cargado.");
						FacesContext.getCurrentInstance().addMessage(null, message);
					}
				}

			} else {

				try {
					if (file != null) {
						newName = file.getFileName();
						newFile = new File(DEFUALT_PATH_UPLOAD + newName);

						FileUtils.copyInputStreamToFile(file.getInputstream(), newFile);
						if (validaArchivo(newFile)) {
							PresupuestoDTO presupuesto = new PresupuestoDTO();
							saveFile(DEFUALT_PATH_UPLOAD, file.getFileName(), file.getInputstream());

							presupuesto.setiPprocesar(1); // this.processAccounts?1:0
							presupuesto.setiIdUser(this.getUserDetails().getUsername());
							presupuesto.setiIdSector(this.getUserDetails().getIdSector());
							presupuesto
									.setiIdRef(0 /* this.getUserDetails().get */); // falta
							presupuesto.setiFileName(file.getFileName());
							presupuesto.setiPathFile(DEFUALT_PATH_UPLOAD);
							presupuesto.setTipo("C");
							presupuestoJDBCTemplateDAOImpl.cargaArchivosIngresos(presupuesto);

							FacesMessage message = new FacesMessage("Info", presupuesto.getoMsgError());
							FacesContext.getCurrentInstance().addMessage(null, message);
						} else {
							FacesMessage message = new FacesMessage("Error",
									file.getFileName() + " Tipo de archivo incorrecto.");
							FacesContext.getCurrentInstance().addMessage(null, message);
						}
					} else {
						LOGGER.info("entra 2");
						FacesMessage message = new FacesMessage("Error!", "Seleccione un árchivo");

						FacesContext.getCurrentInstance().addMessage(null, message);
					}

				} catch (IOException e) {
					LOGGER.info("entra 3");
					FacesMessage message = new FacesMessage("Error!", "Seleccione un árchivo");
					message.setSeverity(FacesMessage.SEVERITY_ERROR);

					FacesContext.getCurrentInstance().addMessage(null, message);
					e.printStackTrace();
				}

			}
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error", "Seleccione un archivo por favor");
		}

		return getFileUploadRequestPage();
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.AbstractFileUploadBudgetFlow#getDefaulPath()
	 */
	@Override
	public String getDefaulPath() {
		return DEFAULT_PATH;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.AbstractFileUploadBudgetFlow#getDefaulPathErrores()
	 */
	@Override
	public String getDefaulPathErrores() {

		if (this.validFormat == Boolean.FALSE) {
			return DEFAULT_PATH_ERRORES;
		} else {
			return DEFAULT_PATH_OUTVALID;
		}

	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.AbstractFileUploadBudgetFlow#getFileUploadRequestPage()
	 */
	@Override
	public String getFileUploadRequestPage() {
		// TODO Auto-generated method stub
		return FILE_UPLOAD_REQUEST_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.AbstractFileUploadBudgetFlow#getFileUploadResponseFile()
	 */
	@Override
	public String getFileUploadResponseFile() {
		// TODO Auto-generated method stub
		return FILE_UPLOAD_RESPONSE_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.AbstractFileUploadBudgetFlow#getInitPage()
	 */
	@Override
	public String getInitPage() {
		// TODO Auto-generated method stub
		return INIT_PAGE;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.AbstractFileUploadBudgetFlow#getDefaultClave()
	 */
	@Override
	public String getDefaultClave() {
		// TODO Auto-generated method stub
		return DEFAULT_KEY;
	}

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.AbstractFileUploadBudgetFlow#applyContenFileValidators(java.io.File)
	 */
	@Override
	public boolean applyContenFileValidators(File newFile) {
		final FileUpload fileUpload = new FileUpload();
		//
		fileUpload.setFile(newFile);

		fileUpload.setNameOriginal(newFile.getName());

		fileUpload.setNameReal(newFile.getName());

		fileUpload.setErrorPath(DEFAULT_PATH_ERRORES);
		fileUpload.setOutPutPath(DEFAULT_PATH_OUTVALID);

		try {

			this.validFormat = ingresosCsvValidator.isValid(fileUpload);
			return validFormat;
			// return true;
			/*
			 * if(validFormat){
			 * validData=this.fileService.validateContent(fileUpload,
			 * Long.valueOf(this.getUserDetails().getIdSector())); FacesMessage
			 * message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito",
			 * super.getFile().getFileName() +
			 * " se ha cargado, verifique la salida");
			 * FacesContext.getCurrentInstance().addMessage(null, message);
			 * this.setErrorFileName(DEFAULT_PATH_OUTVALID +
			 * fileUpload.getNameReal());
			 * this.setDefaulPathErrores(DEFAULT_PATH_OUTVALID);
			 * 
			 * if(validData==Boolean.TRUE){ super.setReviewed(Boolean.TRUE);
			 * }else{ super.setReviewed(Boolean.FALSE); }
			 * 
			 * 
			 * return validData;
			 * 
			 * }else{ super.setReviewed(Boolean.FALSE);
			 * this.setErrorFileName(DEFAULT_PATH_ERRORES +
			 * fileUpload.getNameReal()); FacesMessage message = new
			 * FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
			 * super.getFile().getFileName() + "  presenta errores de formato");
			 * FacesContext.getCurrentInstance().addMessage(null, message);
			 * return validFormat;
			 * 
			 * }
			 */

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gem.sistema.web.bean.AbstractFileUploadBudgetFlow#loadDataIntoTable()
	 */
	@Override
	public void loadDataIntoTable() {
		this.fileService.loadIngresoToTable();

	}

	/**
	 * Save file.
	 *
	 * @param path the path
	 * @param filename the filename
	 * @param input the input
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void saveFile(String path, String filename, InputStream input) throws IOException {
		// String filename = FilenameUtils.getName(file.getFileName());
		// InputStream input = file.getInputstream();
		OutputStream output = new FileOutputStream(new File(path, filename));

		try {
			IOUtils.copy(input, output);
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
		}
	}

	/**
	 * Input path.
	 *
	 * @return the string
	 */
	public String inputPath() {
		return parametrosRepository.getValorByCv("FIL_INGRESOS");
	}

}
