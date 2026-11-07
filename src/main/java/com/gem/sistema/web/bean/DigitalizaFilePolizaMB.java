package com.gem.sistema.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static jakarta.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.gem.sistema.business.domain.Conctb;
import com.gem.sistema.business.domain.TcParametro;
import com.gem.sistema.business.domain.TcRegistraArchivoDigital;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.FirmasRepository;
import com.gem.sistema.business.repository.catalogs.ParametrosRepository;
import com.gem.sistema.business.repository.catalogs.TcRegistraArchivoDigitalRepository;
import com.gem.sistema.business.service.catalogos.ConsultaPolizaSirveImpl;
import com.gem.sistema.business.service.catalogos.PathFileSystemserviceImpl;
import com.gem.sistema.web.security.model.GemUser;

// TODO: Auto-generated Javadoc
/**
 * The Class DigitalizaFilePolizaMB.
 */
@Component("digitalizaFilePolizaMB")
@Scope("view")
public class DigitalizaFilePolizaMB {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DigitalizaFilePolizaMB.class);

	/** The tipo. */
	// private UploadedFile file;
	String tipo = "";
	
	/** The mes. */
	Integer mes = null;
	
	/** The numero. */
	Integer numero = null;
	
	/** The cve parametro. */
	String cveParametro = "TIF";

	/** The path file service. */
	@Value("#{pathFileSystemservice}")
	private PathFileSystemserviceImpl pathFileService;

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
	 * Gets the cve parametro.
	 *
	 * @return the cve parametro
	 */
	public String getCveParametro() {
		return cveParametro;
	}

	/**
	 * Sets the cve parametro.
	 *
	 * @param cveParametro the new cve parametro
	 */
	public void setCveParametro(String cveParametro) {
		this.cveParametro = cveParametro;
	}

	/** The path file. */
	private String pathFile = "";

	/** The registra reporoty. */
	@Value("#{tcRegistraArchivoDigitalRepository}")
	private TcRegistraArchivoDigitalRepository registraReporoty;

	/** The parametros repository. */
	@Value("#{parametrosRepository}")
	private ParametrosRepository parametrosRepository;

	/** The conctb repository. */
	@Value("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The cosulta impl. */
	@Value("#{consultaPolizaSirveImpl}")
	private ConsultaPolizaSirveImpl cosultaImpl;

	/**
	 * Gets the cosulta impl.
	 *
	 * @return the cosulta impl
	 */
	public ConsultaPolizaSirveImpl getCosultaImpl() {
		return cosultaImpl;
	}

	/**
	 * Sets the cosulta impl.
	 *
	 * @param cosultaImpl the new cosulta impl
	 */
	public void setCosultaImpl(ConsultaPolizaSirveImpl cosultaImpl) {
		this.cosultaImpl = cosultaImpl;
	}

	/**
	 * Gets the path file.
	 *
	 * @return the path file
	 */
	public String getPathFile() {
		return pathFile;
	}

	/**
	 * Sets the path file.
	 *
	 * @param pathFile the new path file
	 */
	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
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
	 * Gets the registra reporoty.
	 *
	 * @return the registra reporoty
	 */
	public TcRegistraArchivoDigitalRepository getRegistraReporoty() {
		return registraReporoty;
	}

	/**
	 * Sets the registra reporoty.
	 *
	 * @param registraReporoty the new registra reporoty
	 */
	public void setRegistraReporoty(TcRegistraArchivoDigitalRepository registraReporoty) {
		this.registraReporoty = registraReporoty;
	}

	/**
	 * Gets the conctb repository.
	 *
	 * @return the conctb repository
	 */
	public ConctbRepository getConctbRepository() {
		return conctbRepository;
	}

	/**
	 * Sets the conctb repository.
	 *
	 * @param conctbRepository the new conctb repository
	 */
	public void setConctbRepository(ConctbRepository conctbRepository) {
		this.conctbRepository = conctbRepository;
	}

	/*
	 * public UploadedFile getFile() { return file; }
	 * 
	 * public void setFile(UploadedFile file) { this.file = file; }
	 */

	/**
	 * getUserDetails Metodo para obtener el usuario logueado.
	 *
	 * @return the user details
	 */
	public GemUser getUserDetails() {
		if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails) {
			return (GemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} else {
			return null;
		}
	}

	/**
	 * Handle file upload.
	 *
	 * @param event the event
	 */
	// public void upload(){
	public void handleFileUpload(FileUploadEvent event) {
		LOGGER.info("contet Type ::: " + event.getFile().getContentType());
		// LOGGER.info("contet Type DB ::: " +
		// parametrosRepository.getValorByCv(cveParametro));
		TcRegistraArchivoDigital tcArchivo = new TcRegistraArchivoDigital();
		// setPathFile(pathFileService.getParhFile("/tmp/archivoDigital/"));
		//pathFile = "/gem/archivoDigital/";
		pathFile = System.getProperty("jboss.home.dir") + File.separator + "welcome-content";
		String fileName = event.getFile().getFileName();

		if (!"".equals(fileName)) {
			try {
				pathFileService.getParhFile(pathFile);

				if (isValidateContext(getCveParametro(), event.getFile())) {
					String[] fileData = fileName.split("_");
					if (isValidateFormatFile(fileName.toString())) {
						String anioFiscal;

						tipo = fileData[0];
						mes = Integer.parseInt(fileData[1]);
						numero = Integer.parseInt(fileData[2].substring(fileData[2].length() - 5));
						// Integer cuentaPoliza =
						// registraReporoty.isPoliTrue(String.valueOf(mes),
						// numero, tipo);

						List<TcRegistraArchivoDigital> imgPol = registraReporoty.findAllByMesAndNumeroAndTipoAndSectorId(
								String.valueOf(mes), numero, tipo, this.getUserDetails().getIdSector());

						Integer cuentaPoliza = imgPol.size();

						if (registraReporoty.getCountByPoliza(mes, numero, tipo, this.getUserDetails().getIdSector()) > 0) {
							if (cuentaPoliza == 0) {
								// obtener anio fiscal
								List<Conctb> findAllByIdsectorAndIdref = conctbRepository
										.findAllByIdsectorAndIdRef(getUserDetails().getIdSector(), 0);
								if (findAllByIdsectorAndIdref.size() > 0) {
									anioFiscal = findAllByIdsectorAndIdref.get(0).getAnoemp().toString();
								} else {
									generateNotificationFront(SEVERITY_INFO, "Error",
											" No se encontro el registro del año fiscal.");
									return;
								}

								tcArchivo.setMes(String.valueOf(mes));
								tcArchivo.setTipo(tipo);
								tcArchivo.setNumero(numero);
								tcArchivo.setNombreArchivo(fileName);
								tcArchivo.setPathFile(pathFile);
								tcArchivo.setAnio(anioFiscal);
								tcArchivo.setUserId(getUserDetails().getUsername());
								tcArchivo.setSectorId(getUserDetails().getIdSector());
								tcArchivo.setIdRef(0);
								saveFile(pathFile, fileName, event.getFile().getInputStream());
								registraReporoty.save(tcArchivo);
								generateNotificationFront(SEVERITY_INFO, "Info",
										" La poliza se digitalizo correctamente");
							} else {
								saveFile(pathFile, fileName, event.getFile().getInputStream());
								TcRegistraArchivoDigital img = imgPol.get(0);
								img.setNombreArchivo(fileName);
								registraReporoty.save(img);
								generateNotificationFront(SEVERITY_INFO, "Info",
										" Se actualizo la poliza digitalizada");
							}
						} else {
							generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error", " La poliza no se encuentra en la base");

						}
					} else {
						generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error",
								"Nombre de archivo incorrecto, favor de validarlo");

					}
				} else {

					generateNotificationFront(FacesMessage.SEVERITY_ERROR, "Error", "Solo permite archivo .tiff");
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

	/*
	 * @PostConstruct public void mkDir(){ File carpeta = new
	 * File("/gem/archivoDigital"); carpeta.mkdirs(); }
	 */

	/**
	 * Checks if is validate context.
	 *
	 * @param cveParametro the cve parametro
	 * @param uploadFile the upload file
	 * @return true, if is validate context
	 */
	public boolean isValidateContext(String cveParametro, UploadedFile uploadFile) {
		String content = parametrosRepository.getValorByCv(cveParametro);

		if (uploadFile.getContentType().matches(content)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if is validate format file.
	 *
	 * @param fileName the file name
	 * @return true, if is validate format file
	 */
	public boolean isValidateFormatFile(String fileName) {
		String expreguar = parametrosRepository.getValorByCv("ERTF");
		String nameFile = fileName.substring(0, fileName.length() - 4);
		Pattern pattern = Pattern.compile(expreguar);
		Matcher matcher = pattern.matcher(nameFile);
		if (matcher.find()) {
			return true;
		} else
			return false;

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
		// InputStream input = file.getInputStream();
		OutputStream output = new FileOutputStream(new File(path, filename));

		try {
			IOUtils.copy(input, output);
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
		}
	}

}
