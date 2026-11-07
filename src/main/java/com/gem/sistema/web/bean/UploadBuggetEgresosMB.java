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
import com.gem.sistema.business.domain.Paso;
import com.gem.sistema.business.dto.PresupuestoDTO;
import com.gem.sistema.business.repository.load.PasoRepository;
import com.gem.sistema.business.utils.ConvertCharsetUtils;
import com.gem.sistema.load.fileupload.model.FileUpload;
import com.gem.sistema.load.fileupload.service.InboundFileService;
import com.gem.sistema.load.fileupload.validators.FileContentValidator;
import com.gem.sistema.load.fileupload.validators.results.EgresResultBean;

// TODO: Auto-generated Javadoc
/**
 * The Class UploadBuggetEgresosMB.
 */
@Component(value = "uploadBuggetEgresosMB")
@Scope("session")
// @ManagedBean
// @SessionScoped
public class UploadBuggetEgresosMB extends AbstractFileUploadBudgetFlow {

	/** The Constant DEFAULT_PATH. */
	private static final String DEFAULT_PATH = "/tmp/";
	
	/** The Constant DEFAULT_PATH_ERRORES. */
	private static final String DEFAULT_PATH_ERRORES = "/gem/errores/";
	
	/** The Constant DEFAULT_PATH_OUTVALID. */
	private static final String DEFAULT_PATH_OUTVALID = "/gem/egresos/";
	
	/** The Constant FILE_UPLOAD_REQUEST_PAGE. */
	private static final String FILE_UPLOAD_REQUEST_PAGE = "/views/budget/expenses/fileUploadRequest.xhtml";
	
	/** The Constant FILE_UPLOAD_RESPONSE_PAGE. */
	private static final String FILE_UPLOAD_RESPONSE_PAGE = "/views/budget/expenses/uploadSuccess.xhtml";
	
	/** The Constant INIT_PAGE. */
	private static final String INIT_PAGE = "/views/budget/expenses/init.xhtml";
	
	/** The Constant DEFAULT_KEY. */
	private static final String DEFAULT_KEY = "xcargapree";
	
	/** The Constant DEFAULT_PATH_DOWNLOAD. */
	private static final String DEFAULT_PATH_DOWNLOAD = "/gem/downcvs/";

	/** The valid format. */
	private boolean validFormat = Boolean.FALSE;

	/** The valid data. */
	private boolean validData = Boolean.FALSE;

	/** The process accounts. */
	private boolean processAccounts = Boolean.FALSE;

	/** The egresos csv validator. */
	@Autowired
	@Qualifier("egresosCsvValidator")
	FileContentValidator egresosCsvValidator;

	/** The file service. */
	@Autowired
	InboundFileService fileService;

	/** The paso repository. */
	@Autowired
	PasoRepository pasoRepository;

	/** The presupuesto JDBC template DAO impl. */
	@Autowired
	PresupuestoJDBCTemplateDAO presupuestoJDBCTemplateDAOImpl;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadBuggetEgresosMB.class);

	/* (non-Javadoc)
	 * @see com.gem.sistema.web.bean.AbstractFileUploadBudgetFlow#getDefaulPath()
	 */
	@Override
	public String getDefaulPath() {
		return DEFAULT_PATH;
	}

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
		LOGGER.info("archivo ::: " + file);
		if (StringUtils.isNotEmpty(file.getFileName().toString())) {
			if (StringUtils.equals(accion, "R")) {
				if (file != null) {
					try {
						// newName = UUID.randomUUID() + "-" +
						// file.getFileName();
						newName = UUID.randomUUID() + "" + file.getFileName();
						newName2 = UUID.randomUUID() + "-" + file.getFileName();

						newFile = new File(DEFAULT_PATH_DOWNLOAD + newName);
						target = new File(DEFAULT_PATH_DOWNLOAD + newName2);
						FileUtils.copyInputStreamToFile(file.getInputstream(), newFile);
//						FileUtils.copyInputStreamToFile(file.getInputstream(), target);
//						ConvertCharsetUtils.transform(newFile, charSetISO, target, charSetUFT);

						if (validaArchivo(newFile)) {
							if (applyContenFileValidators(newFile)) {
								PresupuestoDTO presupuesto = new PresupuestoDTO();

								presupuesto.setiPprocesar(this.processAccounts ? 1 : 0);
								presupuesto.setiIdUser(this.getUserDetails().getUsername());
								presupuesto.setiIdSector(this.getUserDetails().getIdSector());
								presupuesto.setiIdRef(
										0 /* this.getUserDetails().get */); // falta
								presupuesto.setiFileName(newName);
								presupuesto.setiPathFile(DEFAULT_PATH_DOWNLOAD);
								presupuesto.setTipo("R");
								presupuestoJDBCTemplateDAOImpl.cargaArchivosEgresos(presupuesto);

								FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succesful",
										file.getFileName() + " se ha cargado, verifique la salida");
								FacesContext.getCurrentInstance().addMessage(null, message);
								// falta ver como recuperar el archivo !!!!

								this.setErrorFileName(DEFAULT_PATH_OUTVALID + presupuesto.getoFleName() /* newName */); // falta
								// this.setDefaulPathErrores(DEFAULT_PATH_OUTVALID);

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
									file.getFileName() + " Tipo de archivo incorrecto");
							FacesContext.getCurrentInstance().addMessage(null, message);
						}

					} catch (IOException e) {
						FacesMessage message = new FacesMessage("Error", file.getFileName() + " No ha sido cargado.");
						FacesContext.getCurrentInstance().addMessage(null, message);
					}
				}

			} else {
				try {
					if (file != null) {
						// this.loadDataIntoTable();
						newName = UUID.randomUUID() + "-" + file.getFileName();
						// newName= file.getFileName();
						newFile = new File(DEFAULT_PATH_DOWNLOAD + newName);

						FileUtils.copyInputStreamToFile(file.getInputstream(), newFile);

						if (validaArchivo(newFile)) {
							if (applyContenFileValidators(newFile)) {
								saveFile(DEFAULT_PATH_DOWNLOAD, file.getFileName(), file.getInputstream());
								PresupuestoDTO presupuesto = new PresupuestoDTO();

								presupuesto.setiPprocesar(this.processAccounts ? 1 : 0);
								presupuesto.setiIdUser(this.getUserDetails().getUsername());
								presupuesto.setiIdSector(this.getUserDetails().getIdSector());
								presupuesto.setiIdRef(
										0 /* this.getUserDetails().get */); // falta
								presupuesto.setiFileName(file.getFileName());
								presupuesto.setiPathFile(DEFAULT_PATH_DOWNLOAD);
								presupuesto.setTipo("C");
								presupuestoJDBCTemplateDAOImpl.cargaArchivosEgresos(presupuesto);

								LOGGER.info("entra 1");
								FacesMessage message = new FacesMessage(presupuesto.getoMsgError());
								FacesContext.getCurrentInstance().addMessage(null, message);
							}
						}
					} else {
						LOGGER.info("entra 2");
						FacesMessage message = new FacesMessage("Error!", "Seleccione un árchivo");

						FacesContext.getCurrentInstance().addMessage(null, message);
					}

				} catch (Exception e) {
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

		fileUpload.setFile(newFile);

		fileUpload.setNameOriginal(newFile.getName());

		fileUpload.setNameReal(newFile.getName());
		fileUpload.setErrorPath(DEFAULT_PATH_ERRORES);

		fileUpload.setOutPutPath(DEFAULT_PATH_OUTVALID);

		try {

			this.validFormat = this.egresosCsvValidator.isValid(fileUpload);
			return validFormat;
			/*
			 * 
			 * if(validFormat){
			 * validData=this.fileService.validateContent(fileUpload,
			 * this.processAccounts, this.getUserDetails().getIdSector());
			 * 
			 * FacesMessage message = new
			 * FacesMessage(FacesMessage.SEVERITY_INFO, "Succesful",
			 * super.getFile().getFileName() +
			 * " se ha cargado, verifique la salida");
			 * FacesContext.getCurrentInstance().addMessage(null, message);
			 * this.setErrorFileName(DEFAULT_PATH_OUTVALID +
			 * fileUpload.getNameReal());
			 * this.setDefaulPathErrores(DEFAULT_PATH_OUTVALID); return
			 * validData;
			 * 
			 * }else{ this.setErrorFileName(DEFAULT_PATH_ERRORES +
			 * fileUpload.getNameReal()); FacesMessage message = new
			 * FacesMessage(FacesMessage.SEVERITY_ERROR, "Succesful",
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
		//
		Paso paso = null;

		for (EgresResultBean e : fileService.getResults()) {
			paso = new Paso();

			paso.setClave(e.getDependencia());
			paso.setPartida(e.getPartida());
			paso.setPrograma(e.getFinun());
			paso.setAuto11(new java.math.BigDecimal(e.getEg1()));
			paso.setAuto12(new java.math.BigDecimal(e.getEg2()));
			paso.setAuto13(new java.math.BigDecimal(e.getEg3()));
			paso.setAuto14(new java.math.BigDecimal(e.getEg4()));
			paso.setAuto15(new java.math.BigDecimal(e.getEg5()));
			paso.setAuto16(new java.math.BigDecimal(e.getEg6()));
			paso.setAuto17(new java.math.BigDecimal(e.getEg7()));
			paso.setAuto18(new java.math.BigDecimal(e.getEg8()));
			paso.setAuto19(new java.math.BigDecimal(e.getEg9()));
			paso.setAuto110(new java.math.BigDecimal(e.getEg10()));
			paso.setAuto111(new java.math.BigDecimal(e.getEg11()));
			paso.setAuto112(new java.math.BigDecimal(e.getEg12()));
			paso.setAuto113(new java.math.BigDecimal(e.getSuma()));

			this.pasoRepository.save(paso);

		}

	}

	/**
	 * Checks if is process accounts.
	 *
	 * @return the processAccounts
	 */
	public boolean isProcessAccounts() {
		return processAccounts;
	}

	/**
	 * Sets the process accounts.
	 *
	 * @param processAccounts            the processAccounts to set
	 */
	public void setProcessAccounts(boolean processAccounts) {
		this.processAccounts = processAccounts;
	}

	/**
	 * Gets the file service.
	 *
	 * @return the file service
	 */
	public InboundFileService getFileService() {
		return fileService;
	}

	/**
	 * Sets the file service.
	 *
	 * @param fileService the new file service
	 */
	public void setFileService(InboundFileService fileService) {
		this.fileService = fileService;
	}

	/**
	 * Gets the paso repository.
	 *
	 * @return the paso repository
	 */
	public PasoRepository getPasoRepository() {
		return pasoRepository;
	}

	/**
	 * Sets the paso repository.
	 *
	 * @param pasoRepository the new paso repository
	 */
	public void setPasoRepository(PasoRepository pasoRepository) {
		this.pasoRepository = pasoRepository;
	}

	/**
	 * Gets the presupuesto JDBC template DAO impl.
	 *
	 * @return the presupuesto JDBC template DAO impl
	 */
	public PresupuestoJDBCTemplateDAO getPresupuestoJDBCTemplateDAOImpl() {
		return presupuestoJDBCTemplateDAOImpl;
	}

	/**
	 * Sets the presupuesto JDBC template DAO impl.
	 *
	 * @param presupuestoJDBCTemplateDAOImpl the new presupuesto JDBC template DAO impl
	 */
	public void setPresupuestoJDBCTemplateDAOImpl(PresupuestoJDBCTemplateDAO presupuestoJDBCTemplateDAOImpl) {
		this.presupuestoJDBCTemplateDAOImpl = presupuestoJDBCTemplateDAOImpl;
	}

}
