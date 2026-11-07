package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.gem.sistema.business.repository.catalogs.ParametrosRepository;
import com.gem.sistema.business.service.callsp.ExecutePlService;

import com.gem.sistema.load.fileupload.exceptions.ValidateFileException;
import com.gem.sistema.load.fileupload.model.FileUpload;
import com.gem.sistema.load.fileupload.validators.FileContentValidator;

// TODO: Auto-generated Javadoc
/**
 * The Class Reporte108CargaAutomaticaMB.
 */
@ManagedBean(name = "reporte108CargaAutomaticaMB")
@ViewScoped
public class Reporte108CargaAutomaticaMB extends AbstractMB {

	/** The egresos csv validator. */
	@ManagedProperty("#{importarPolizaCsvValidator}")
	private FileContentValidator egresosCsvValidator;

	/** The Constant PROCEDURE_NAME. */
	private static final String PROCEDURE_NAME = "SP_CARGA_EMERGENCIAS";

	/** The validate. */
	private boolean validate = Boolean.TRUE;

	/** The size. */
	private boolean size;

	/** The trimestre. */
	private Integer trimestre;

	/** The list tri. */
	private List<Integer> listTri;

	/**
	 * Gets the trimestre.
	 *
	 * @return the trimestre
	 */
	public Integer getTrimestre() {
		return trimestre;
	}

	/**
	 * Sets the trimestre.
	 *
	 * @param trimestre
	 *            the new trimestre
	 */
	public void setTrimestre(Integer trimestre) {
		this.trimestre = trimestre;
	}

	/**
	 * Gets the list tri.
	 *
	 * @return the list tri
	 */
	public List<Integer> getListTri() {
		return listTri;
	}

	/**
	 * Sets the list tri.
	 *
	 * @param listTri
	 *            the new list tri
	 */
	public void setListTri(List<Integer> listTri) {
		this.listTri = listTri;
	}

	/** The parametros repository. */
	@ManagedProperty("#{parametrosRepository}")
	private ParametrosRepository parametrosRepository;

	/** The execute pl service. */
	@ManagedProperty("#{executePlService}")
	protected ExecutePlService executePlService;

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
	 * @param parametrosRepository
	 *            the new parametros repository
	 */
	public void setParametrosRepository(ParametrosRepository parametrosRepository) {
		this.parametrosRepository = parametrosRepository;
	}

	/**
	 * Checks if is validate.
	 *
	 * @return true, if is validate
	 */
	public boolean isValidate() {
		return validate;
	}

	/**
	 * Sets the validate.
	 *
	 * @param validate
	 *            the new validate
	 */
	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	/**
	 * Checks if is size.
	 *
	 * @return true, if is size
	 */
	public boolean isSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size
	 *            the new size
	 */
	public void setSize(boolean size) {
		this.size = size;
	}

	/**
	 * Gets the egresos csv validator.
	 *
	 * @return the egresos csv validator
	 */
	public FileContentValidator getEgresosCsvValidator() {
		return egresosCsvValidator;
	}

	/**
	 * Sets the egresos csv validator.
	 *
	 * @param egresosCsvValidator
	 *            the new egresos csv validator
	 */
	public void setEgresosCsvValidator(FileContentValidator egresosCsvValidator) {
		this.egresosCsvValidator = egresosCsvValidator;
	}

	/**
	 * Gets the execute pl service.
	 *
	 * @return the execute pl service
	 */
	public ExecutePlService getExecutePlService() {
		return executePlService;
	}

	/**
	 * Sets the execute pl service.
	 *
	 * @param executePlService
	 *            the new execute pl service
	 */
	public void setExecutePlService(ExecutePlService executePlService) {
		this.executePlService = executePlService;
	}

	// @PostConstruct
	/*
	 * public void init() { listTri = new ArrayList<Integer>(); for (int i = 1;
	 * i <= 4; i++) { listTri.add(i); } trimestre = listTri.get(0); }
	 */

	/** The out parameters. */
	Map<String, Object> outParameters = null;

	/**
	 * Handle file upload.
	 *
	 * @param event
	 *            the event
	 */
	public void handleFileUpload(FileUploadEvent event) {
		try {
			inputFile(event.getFile().getFileName(), event.getFile().getInputstream());
			final FileUpload fileUpload = new FileUpload();
			File newFile = new File(getPath("RUTA_REPORTES") + File.separator + event.getFile().getFileName());
			fileUpload.setFile(newFile);
			size = siZeFile(newFile);
			if (this.size) {
				try {
					this.validate = this.egresosCsvValidator.isValid(fileUpload);
				} catch (ValidateFileException e) {
					// // TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (this.validate) {

					// if (tsuldo.getoCodError() > 0) {
					// generateNotificationFront(SEVERITY_INFO, "",
					// tsuldo.getoMsgError());
					// } else {
					// generateNotificationFront(SEVERITY_INFO, "",
					// tsuldo.getoMsgError());
					// }
					MapSqlParameterSource parameterSource = new MapSqlParameterSource();
					parameterSource.addValue("i_trimestre", notNullTrim(trimestre))
							.addValue("i_id_sector", this.getUserDetails().getIdSector())
							.addValue("i_user", this.getUserDetails().getUsername())
							.addValue("i_file_name", event.getFile().getFileName());
					outParameters = this.executePlService.callProcedure(PROCEDURE_NAME, parameterSource);
					if (Integer.valueOf(outParameters.get("O_COD_ERROR").toString()) == 1) {
						generateNotificationFront(SEVERITY_INFO, "", outParameters.get("O_MSG_ERROR").toString());
					} else {
						generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error!",
								outParameters.get("O_MSG_ERROR").toString());
					}
				} else {
					generateNotificationFront(SEVERITY_INFO, "", "El archivo contiene errores");
				}
			} else {
				generateNotificationFront(SEVERITY_INFO, "", parametrosRepository.getValorByCv("FILE_SIZE"));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Input file.
	 *
	 * @param filename
	 *            the filename
	 * @param input
	 *            the input
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void inputFile(String filename, InputStream input) throws IOException {
		OutputStream output = new FileOutputStream(new File(getPath("RUTA_REPORTES"), filename));

		try {
			IOUtils.copy(input, output);
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
		}
	}

	/**
	 * Gets the path.
	 *
	 * @param cvePath
	 *            the cve path
	 * @return the path
	 */
	public String getPath(String cvePath) {
		return parametrosRepository.getValorByCv(cvePath);
	}

	/**
	 * Si ze file.
	 *
	 * @param file
	 *            the file
	 * @return true, if successful
	 */
	public boolean siZeFile(File file) {
		if (file.length() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Integer notNullTrim(Integer trimestre) {
		if (null == trimestre)
			trimestre = 1;

		return trimestre;
	}

}
