/**
 * 
 */
package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gem.sistema.business.dto.EdoAEPImportDTO;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.ParametrosRepository;
import com.gem.sistema.business.service.catalogos.EdoAEPImportService;
import com.gem.sistema.business.service.catalogos.PathFileSystemserviceImpl;
import com.gem.sistema.load.fileupload.exceptions.ValidateFileException;
import com.gem.sistema.load.fileupload.model.FileUpload;
import com.gem.sistema.load.fileupload.validators.FileContentValidator;

// TODO: Auto-generated Javadoc
/**
 * The Class EdoAnalitoIngrePIntegradoImpMB.
 *
 * @author Gerardo CUERO
 */
@ManagedBean(name = "edoAnalitoIngrePIntegradoImpMB")
@ViewScoped
public class EdoAnalitoIngrePIntegradoImpMB extends AbstractMB {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(EdoAnalitoIngrePIntegradoImpMB.class);

	/** The Constant PARAMETER_TYPE. */
	private static final String PARAMETER_TYPE = "CSV";

	/** The Constant PATH_PARM. */
	private static final String PATH_PARM = "IMPORT_INTEGRADOI";

	/** The Constant DEFAULT_PATH_ERRORES. */
	private static final String DEFAULT_PATH_ERRORES = "/gem/errores/";

	/** The Constant DEFAULT_PATH_OUTVALID. */
	private static final String DEFAULT_PATH_OUTVALID = "/gem/ingresos/";

	/** The active download error file. */
	private Boolean activeDownloadErrorFile = Boolean.FALSE;

	/** The error file name. */
	protected String errorFileName;

	/** The organismo. */
	private String organismo = "0";

	/** The mes. */
	private String mes = "01";

	/** The path file service. */
	@ManagedProperty("#{pathFileSystemservice}")
	private PathFileSystemserviceImpl pathFileService;

	/** The parametros repository. */
	@ManagedProperty("#{parametrosRepository}")
	private ParametrosRepository parametrosRepository;

	/** The edo AI presup integra csv validator. */
	@ManagedProperty("#{edoAIPresupIntegraCsvValidator}")
	private FileContentValidator edoAIPresupIntegraCsvValidator;

	/** The edo AEP import service. */
	@ManagedProperty("#{edoAEPImportService}")
	private EdoAEPImportService edoAEPImportService;

	/** The conctb repositpry. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepositpry;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		this.organismo = "0";
	}

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

	/**
	 * Gets the path file service.
	 *
	 * @return the path file service
	 */
	public PathFileSystemserviceImpl getPathFileService() {
		return pathFileService;
	}

	/**
	 * Sets the path file service.
	 *
	 * @param pathFileService the new path file service
	 */
	public void setPathFileService(PathFileSystemserviceImpl pathFileService) {
		this.pathFileService = pathFileService;
	}

	/**
	 * Gets the organismo.
	 *
	 * @return the organismo
	 */
	public String getOrganismo() {
		return organismo;
	}

	/**
	 * Sets the organismo.
	 *
	 * @param organismo the new organismo
	 */
	public void setOrganismo(String organismo) {
		this.organismo = organismo;
	}

	/**
	 * Gets the mes.
	 *
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * Sets the mes.
	 *
	 * @param mes the new mes
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/**
	 * Gets the edo AI presup integra csv validator.
	 *
	 * @return the edo AI presup integra csv validator
	 */
	public FileContentValidator getEdoAIPresupIntegraCsvValidator() {
		return edoAIPresupIntegraCsvValidator;
	}

	/**
	 * Sets the edo AI presup integra csv validator.
	 *
	 * @param edoAIPresupIntegraCsvValidator the new edo AI presup integra csv validator
	 */
	public void setEdoAIPresupIntegraCsvValidator(FileContentValidator edoAIPresupIntegraCsvValidator) {
		this.edoAIPresupIntegraCsvValidator = edoAIPresupIntegraCsvValidator;
	}

	/**
	 * Gets the edo AEP import service.
	 *
	 * @return the edo AEP import service
	 */
	public EdoAEPImportService getEdoAEPImportService() {
		return edoAEPImportService;
	}

	/**
	 * Sets the edo AEP import service.
	 *
	 * @param edoAEPImportService the new edo AEP import service
	 */
	public void setEdoAEPImportService(EdoAEPImportService edoAEPImportService) {
		this.edoAEPImportService = edoAEPImportService;
	}

	/**
	 * Instantiates a new edo analito ingre P integrado imp MB.
	 */
	public EdoAnalitoIngrePIntegradoImpMB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the conctb repositpry.
	 *
	 * @return the conctb repositpry
	 */
	public ConctbRepository getConctbRepositpry() {
		return conctbRepositpry;
	}

	/**
	 * Sets the conctb repositpry.
	 *
	 * @param conctbRepositpry the new conctb repositpry
	 */
	public void setConctbRepositpry(ConctbRepository conctbRepositpry) {
		this.conctbRepositpry = conctbRepositpry;
	}

	/**
	 * Handle file upload.
	 *
	 * @param event the event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		LOGGER.info("contet Type ::: " + event.getFile().getContentType());

		String pathFile = "/gem/archivoDigital/";

		String fileName = event.getFile().getFileName();

		if (!StringUtils.EMPTY.equals(fileName)) {
			try {
				pathFile = pathFileService.getParhFile(PATH_PARM);

				if (isValidateContext(PARAMETER_TYPE, event.getFile())) {

					if (isValidateFormatFile(fileName.toString())) {

						String fileNameProcess = FilenameUtils.getBaseName(fileName) + UUID.randomUUID().toString()
								+ FilenameUtils.EXTENSION_SEPARATOR_STR + FilenameUtils.getExtension(fileName);
						String fullPath = pathFile + File.separator + fileNameProcess;
						File file2Process = new File(fullPath);
						FileUtils.writeByteArrayToFile(file2Process, event.getFile().getContents());

						final FileUpload fileUpload = new FileUpload();
						fileUpload.setFile(file2Process);
						fileUpload.setNameOriginal(fileName.toString());
						fileUpload.setNameReal(file2Process.getName());
						fileUpload.setErrorPath(DEFAULT_PATH_ERRORES);
						fileUpload.setOutPutPath(DEFAULT_PATH_OUTVALID);
						try {
							if (edoAIPresupIntegraCsvValidator.isValid(fileUpload)) {
								EdoAEPImportDTO edoAEPImportDTO = new EdoAEPImportDTO(Integer.valueOf(this.getMes()),
										Integer.valueOf(this.getOrganismo()), this.getUserDetails().getIdSector(),
										this.getUserDetails().getUsername(), fileNameProcess);
								edoAEPImportDTO = this.edoAEPImportService.doImportIngreso(edoAEPImportDTO);
								if (edoAEPImportDTO.getCodError().equals(1)) {
									generateNotificationFront(FacesMessage.SEVERITY_INFO, "Exito",
											edoAEPImportDTO.getMsgError());
								} else {
									this.setErrorFileName(pathFile + File.separator + edoAEPImportDTO.getFullFile());
									generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
											edoAEPImportDTO.getMsgError());
								}
							} else {
								this.setErrorFileName(DEFAULT_PATH_ERRORES + fileNameProcess);
								FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
										FilenameUtils.getName(fileName) + "  presenta errores de formato");
								FacesContext.getCurrentInstance().addMessage(null, message);

							}
						} catch (ValidateFileException e) {
							generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error", e.getCause().getMessage());
						}
					} else {
						generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
								"Nombre de archivo incorrecto, favor de validarlo");

					}
				} else {

					generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error", "Solo permite archivo .csv o .txt");
				}

			} catch (IOException e) {
				LOGGER.error("handleFileUpload", e);
				generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
						" Hubo un problema al momento de guardar el archivo.");
			}
		} else {
			generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error", "Seleccione un archivo por favor");
		}

	}

	/**
	 * Checks if is validate context.
	 *
	 * @param cveParametro the cve parametro
	 * @param uploadFile the upload file
	 * @return true, if is validate context
	 */
	public boolean isValidateContext(String cveParametro, UploadedFile uploadFile) {
		String content = parametrosRepository.getValorByCv(cveParametro);
		return uploadFile.getContentType().matches(content);
	}

	/** The Constant DEC_FORMATTER. */
	private static final DecimalFormat DEC_FORMATTER = new DecimalFormat("000");

	/** The ano emp. */
	Integer anoEmp;

	/**
	 * Checks if is validate format file.
	 *
	 * @param fileName the file name
	 * @return true, if is validate format file
	 */
	public boolean isValidateFormatFile(String fileName) {
		anoEmp = conctbRepositpry.findByIdsector(this.getUserDetails().getIdSector()).getAnoemp();
		String file2validate = this.organismo + DEC_FORMATTER.format(this.getUserDetails().getIdMunicipio()) + anoEmp
				+ this.getMes() + "i.txt";
		String fileCSV2validate = this.organismo + DEC_FORMATTER.format(this.getUserDetails().getIdMunicipio()) + anoEmp
				+ this.getMes() + "i.csv";
		return file2validate.equalsIgnoreCase(fileName) || fileCSV2validate.equalsIgnoreCase(fileName);
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
	 * Gets the error file name.
	 *
	 * @return the error file name
	 */
	public String getErrorFileName() {
		return errorFileName;
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
	 * Gets the active download error file.
	 *
	 * @return the active download error file
	 */
	public Boolean getActiveDownloadErrorFile() {
		return activeDownloadErrorFile;
	}

	/**
	 * Sets the active download error file.
	 *
	 * @param activeDownloadErrorFile the new active download error file
	 */
	public void setActiveDownloadErrorFile(Boolean activeDownloadErrorFile) {
		this.activeDownloadErrorFile = activeDownloadErrorFile;
	}

}
