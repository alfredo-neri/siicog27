package com.gem.sistema.web.bean;

import static com.gem.sistema.util.UtilFront.generateNotificationFront;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gem.sistema.business.domain.Cattpo;
import com.gem.sistema.business.domain.Polien;
import com.gem.sistema.business.dto.PolizaImportDTO;
import com.gem.sistema.business.predicates.PolienPredicates;
import com.gem.sistema.business.repository.catalogs.CattpoRepository;
import com.gem.sistema.business.repository.catalogs.ConctbRepository;
import com.gem.sistema.business.repository.catalogs.ParametrosRepository;
import com.gem.sistema.business.repository.catalogs.PolienRepository;
import com.gem.sistema.business.repository.catalogs.TcMesRepository;
import com.gem.sistema.business.service.catalogos.DataYearSystemService;
import com.gem.sistema.business.service.catalogos.ExtraeArchivoCuentaService;
import com.gem.sistema.business.service.catalogos.ImportarPolizaService;
import com.gem.sistema.business.service.catalogos.PolizaService;
import com.gem.sistema.business.service.reportador.ReportadorTXTServiceImpl;
import com.gem.sistema.business.utils.ConvertCharsetUtils;
import com.gem.sistema.web.util.FrontProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class ImportarPolizaMB.
 */
@ManagedBean(name = "importarPolizaMB")
@ViewScoped
public class ImportarPolizaMB extends AbstractMB implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AfectacionPolizaMB.class);

	/** The Constant charSetISO. */
	private final static String charSetISO = "ISO-8859-1";

	/** The Constant charSetUFT. */
	private final static String charSetUFT = "UTF-8";

	/** Ruta donde se encuentra el archivo jasper del reporte de cuentas. */
	// @Value("${view.report.path.jasper.poliza}")
	private static final String REPORT_PATH_JASPER_POLICY = FrontProperty
			.getPropertyValue("view.report.path.jasper.poliza");

	/** Nombre del reporte en texto plano. */
	// @Value("${file.name.report.txt.poliza}")
	private static final String REPORT_NAME = FrontProperty.getPropertyValue("file.name.report.txt.poliza");

	/** Mensaje Error. */
	// @Value("${catalog.message.error}")
	protected static final String MESSAGE_ERROR = FrontProperty.getPropertyValue("catalog.message.error");

	/** The poliza importar DTO. */
	private PolizaImportDTO polizaImportarDTO;

	/** The file. */
	private UploadedFile file;

	/** The clave. */
	private String clave;

	/** The obj disabled. */
	private boolean objDisabled;

	/** The desabilitar. */
	private boolean desabilitar;

	/** The list mes. */
	private List<String> listMes;

	/** The lis cattpo. */
	private List<Cattpo> lisCattpo;

	/** The i mes. */
	private Integer iMes;

	/** The i tipo. */
	private String iTipo = "11";

	/** The i numero. */
	private Integer iNumero;

	/** The i feccha. */
	private Date iFeccha;

	/** The i boxes. */
	private Integer iBoxes = 0;

	/** The i procesar. */
	private String iProcesar;

	String fileCuenta;
	/** The size. */
	private boolean size;

	/** The file name. */
	private String fileName;

	/** The mes aux. */
	private Integer mesAux;

	/** The anio aux. */
	private Integer anioAux;

	/** The repetir poliza. */
	private boolean repetirPoliza;

	private boolean render = Boolean.TRUE;

	/**
	 * Checks if is repetir poliza.
	 *
	 * @return true, if is repetir poliza
	 */
	public boolean isRepetirPoliza() {
		return repetirPoliza;
	}

	/**
	 * Sets the repetir poliza.
	 *
	 * @param repetirPoliza the new repetir poliza
	 */
	public void setRepetirPoliza(boolean repetirPoliza) {
		this.repetirPoliza = repetirPoliza;
	}

	/** The streamed content. */
	private StreamedContent streamedContent;

	/** The poliza service. */
	@ManagedProperty("#{polizaService}")
	private PolizaService polizaService;

	/** The parametros repository. */
	@ManagedProperty("#{parametrosRepository}")
	private ParametrosRepository parametrosRepository;

	/** The tc mes repository. */
	@ManagedProperty("#{tcMesRepository}")
	private TcMesRepository tcMesRepository;

	/** The cattpo repositry. */
	@ManagedProperty("#{cattpoRepository}")
	private CattpoRepository cattpoRepositry;

	/** The importar poliza service. */
	@ManagedProperty("#{importarPolizaServiceImpl}")
	private ImportarPolizaService importarPolizaService;

	/** The conctb repository. */
	@ManagedProperty("#{conctbRepository}")
	private ConctbRepository conctbRepository;

	/** The polien repository. */
	@ManagedProperty("#{polienRepository}")
	private PolienRepository polienRepository;

	/** The year service. */
	@ManagedProperty("#{dataYearSystemService}")
	private DataYearSystemService yearService;

	@ManagedProperty("#{extraeArchivoCuentaService}")
	private ExtraeArchivoCuentaService extraeArchivoCuentaService;

	/** The download file. */
	private StreamedContent downloadFile;

	/**
	 * Gets the tc mes repository.
	 *
	 * @return the tc mes repository
	 */
	public TcMesRepository getTcMesRepository() {
		return tcMesRepository;
	}

	/**
	 * Sets the tc mes repository.
	 *
	 * @param tcMesRepository the new tc mes repository
	 */
	public void setTcMesRepository(TcMesRepository tcMesRepository) {
		this.tcMesRepository = tcMesRepository;
	}

	/**
	 * Gets the poliza importar DTO.
	 *
	 * @return the poliza importar DTO
	 */
	public PolizaImportDTO getPolizaImportarDTO() {
		return polizaImportarDTO;
	}

	/**
	 * Sets the poliza importar DTO.
	 *
	 * @param polizaImportarDTO the new poliza importar DTO
	 */
	public void setPolizaImportarDTO(PolizaImportDTO polizaImportarDTO) {
		this.polizaImportarDTO = polizaImportarDTO;
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
	 * Checks if is obj disabled.
	 *
	 * @return true, if is obj disabled
	 */
	public boolean isObjDisabled() {
		return objDisabled;
	}

	/**
	 * Sets the obj disabled.
	 *
	 * @param objDisabled the new obj disabled
	 */
	public void setObjDisabled(boolean objDisabled) {
		this.objDisabled = objDisabled;
	}

	/**
	 * Gets the list mes.
	 *
	 * @return the list mes
	 */
	public List<String> getListMes() {
		return listMes;
	}

	/**
	 * Gets the lis cattpo.
	 *
	 * @return the lis cattpo
	 */
	public List<Cattpo> getLisCattpo() {
		return lisCattpo;
	}

	/**
	 * Sets the lis cattpo.
	 *
	 * @param lisCattpo the new lis cattpo
	 */
	public void setLisCattpo(List<Cattpo> lisCattpo) {
		this.lisCattpo = lisCattpo;
	}

	/**
	 * Sets the list mes.
	 *
	 * @param listMes the new list mes
	 */
	public void setListMes(List<String> listMes) {
		this.listMes = listMes;
	}

	/**
	 * Gets the cattpo repositry.
	 *
	 * @return the cattpo repositry
	 */
	public CattpoRepository getCattpoRepositry() {
		return cattpoRepositry;
	}

	/**
	 * Sets the cattpo repositry.
	 *
	 * @param cattpoRepositry the new cattpo repositry
	 */
	public void setCattpoRepositry(CattpoRepository cattpoRepositry) {
		this.cattpoRepositry = cattpoRepositry;
	}

	/**
	 * Gets the i mes.
	 *
	 * @return the i mes
	 */
	public Integer getiMes() {
		return iMes;
	}

	/**
	 * Sets the i mes.
	 *
	 * @param iMes the new i mes
	 */
	public void setiMes(Integer iMes) {
		this.iMes = iMes;
	}

	/**
	 * Gets the i tipo.
	 *
	 * @return the i tipo
	 */
	public String getiTipo() {
		return iTipo;
	}

	/**
	 * Sets the i tipo.
	 *
	 * @param iTipo the new i tipo
	 */
	public void setiTipo(String iTipo) {
		this.iTipo = iTipo;
	}

	/**
	 * Gets the i numero.
	 *
	 * @return the i numero
	 */
	public Integer getiNumero() {
		return iNumero;
	}

	/**
	 * Sets the i numero.
	 *
	 * @param iNumero the new i numero
	 */
	public void setiNumero(Integer iNumero) {
		this.iNumero = iNumero;
	}

	/**
	 * Gets the i feccha.
	 *
	 * @return the i feccha
	 */
	public Date getiFeccha() {
		return iFeccha;
	}

	/**
	 * Sets the i feccha.
	 *
	 * @param iFeccha the new i feccha
	 */
	public void setiFeccha(Date iFeccha) {
		this.iFeccha = iFeccha;
	}

	/**
	 * Gets the i boxes.
	 *
	 * @return the i boxes
	 */
	public Integer getiBoxes() {
		return iBoxes;
	}

	/**
	 * Sets the i boxes.
	 *
	 * @param iBoxes the new i boxes
	 */
	public void setiBoxes(Integer iBoxes) {
		this.iBoxes = iBoxes;
	}

	/**
	 * Gets the i procesar.
	 *
	 * @return the i procesar
	 */
	public String getiProcesar() {
		return iProcesar;
	}

	/**
	 * Sets the i procesar.
	 *
	 * @param iProcesar the new i procesar
	 */
	public void setiProcesar(String iProcesar) {
		this.iProcesar = iProcesar;
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
	 * @param size the new size
	 */
	public void setSize(boolean size) {
		this.size = size;
	}

	/**
	 * Gets the importar poliza service.
	 *
	 * @return the importar poliza service
	 */
	public ImportarPolizaService getImportarPolizaService() {
		return importarPolizaService;
	}

	/**
	 * Sets the importar poliza service.
	 *
	 * @param importarPolizaService the new importar poliza service
	 */
	public void setImportarPolizaService(ImportarPolizaService importarPolizaService) {
		this.importarPolizaService = importarPolizaService;
	}

	/**
	 * Gets the download file.
	 *
	 * @return the download file
	 */
	public StreamedContent getDownloadFile() {
		return downloadFile;
	}

	/**
	 * Sets the download file.
	 *
	 * @param downloadFile the new download file
	 */
	public void setDownloadFile(StreamedContent downloadFile) {
		this.downloadFile = downloadFile;
	}

	/**
	 * Checks if is desabilitar.
	 *
	 * @return true, if is desabilitar
	 */
	public boolean isDesabilitar() {
		return desabilitar;
	}

	/**
	 * Sets the desabilitar.
	 *
	 * @param desabilitar the new desabilitar
	 */
	public void setDesabilitar(boolean desabilitar) {
		this.desabilitar = desabilitar;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the mes aux.
	 *
	 * @return the mes aux
	 */
	public Integer getMesAux() {
		return mesAux;
	}

	/**
	 * Sets the mes aux.
	 *
	 * @param mesAux the new mes aux
	 */
	public void setMesAux(Integer mesAux) {
		this.mesAux = mesAux;
	}

	/**
	 * Gets the anio aux.
	 *
	 * @return the anio aux
	 */
	public Integer getAnioAux() {
		return anioAux;
	}

	/**
	 * Sets the anio aux.
	 *
	 * @param anioAux the new anio aux
	 */
	public void setAnioAux(Integer anioAux) {
		this.anioAux = anioAux;
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

	/**
	 * Gets the year service.
	 *
	 * @return the year service
	 */
	public DataYearSystemService getYearService() {
		return yearService;
	}

	/**
	 * Sets the year service.
	 *
	 * @param yearService the new year service
	 */
	public void setYearService(DataYearSystemService yearService) {
		this.yearService = yearService;
	}

	/** The file pdf path. */
	private String filePdfPath = "/tmp/m-dpolizasd.pdf";

	/**
	 * Gets the file pdf path.
	 *
	 * @return the file pdf path
	 */
	public String getFilePdfPath() {
		return filePdfPath;
	}

	/**
	 * Sets the file pdf path.
	 *
	 * @param filePdfPath the new file pdf path
	 */
	public void setFilePdfPath(String filePdfPath) {
		this.filePdfPath = filePdfPath;
	}

	/** The render pdf. */
	private boolean renderPdf = Boolean.FALSE;

	/**
	 * Checks if is render pdf.
	 *
	 * @return true, if is render pdf
	 */
	public boolean isRenderPdf() {
		return renderPdf;
	}

	/**
	 * Gets the streamed content.
	 *
	 * @return the streamed content
	 */
	public StreamedContent getStreamedContent() {
		return streamedContent;
	}

	/**
	 * Sets the streamed content.
	 *
	 * @param streamedContent the new streamed content
	 */
	public void setStreamedContent(StreamedContent streamedContent) {
		this.streamedContent = streamedContent;
	}

	/**
	 * Gets the poliza service.
	 *
	 * @return the poliza service
	 */
	public PolizaService getPolizaService() {
		return polizaService;
	}

	/**
	 * Sets the poliza service.
	 *
	 * @param polizaService the new poliza service
	 */
	public void setPolizaService(PolizaService polizaService) {
		this.polizaService = polizaService;
	}

	/**
	 * Sets the render pdf.
	 *
	 * @param renderPdf the new render pdf
	 */
	public void setRenderPdf(boolean renderPdf) {
		this.renderPdf = renderPdf;
	}

	/**
	 * Gets the polien repository.
	 *
	 * @return the polien repository
	 */
	public PolienRepository getPolienRepository() {
		return polienRepository;
	}

	/**
	 * Sets the polien repository.
	 *
	 * @param polienRepository the new polien repository
	 */
	public void setPolienRepository(PolienRepository polienRepository) {
		this.polienRepository = polienRepository;
	}

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {

		listMes = importarPolizaService.getMesActivo(this.getUserDetails().getIdSector());
		lisCattpo = cattpoRepositry.findAll();

		for (Cattpo c : lisCattpo) {
			LOGGER.info("polizaMantenimientoMB" + c.getTippol());
		}
		if (!CollectionUtils.isEmpty(lisCattpo)) {
			this.iTipo = lisCattpo.get(0).getTippol();
		}

		if (!CollectionUtils.isEmpty(lisCattpo)) {
			this.iMes = Integer.valueOf(listMes.get(0));

		}

		if (StringUtils.isNotBlank(String.valueOf(this.iMes)) && StringUtils.isNotBlank(this.iTipo)) {
			iNumero = polienRepository.countPoliza(iMes, this.iTipo, this.getUserDetails().getIdSector());
			if (iNumero == null) {
				iNumero = 1;
			}
		}

		objDisabled = Boolean.TRUE;
		desabilitar = Boolean.TRUE;

	}

	public void updateNumPoliza() {
		if (StringUtils.isNotBlank(String.valueOf(this.iMes)) && StringUtils.isNotBlank(this.iTipo)) {
			iNumero = polienRepository.countPoliza(iMes, this.iTipo, this.getUserDetails().getIdSector());
			if (iNumero == null) {
				iNumero = 1;
			}
		}
	}

	public void renderElements() {
		render = Boolean.FALSE;
		if (iBoxes < 15) {
			render = Boolean.TRUE;
		}
	}

	/**
	 * Upload.
	 */
	public void upload() {
		Polien validacion;
		Calendar caledar = Calendar.getInstance();
		caledar.setTime(iFeccha);
		mesAux = caledar.get(Calendar.MONTH) + 1;
		anioAux = yearService.getYear(this.getUserDetails().getIdSector());
		File source = null;
		File target = null;
		String fileName1 = "";
		String fileName2 = "";
		String pathFiles = "";
		if (mesAux == iMes || iBoxes < 16) {
			if (anioAux == caledar.get(Calendar.YEAR)) {
				if (file != null) {

					try {
						if (file.getFileName() != null) {
							if (file.getFileName().equals(this.getPath("TIPO_POLIZA"))
									|| (file.getFileName().equals("cuenta.txt") && iBoxes == 15)) {
								pathFiles = getPath("CARG_ARCHIVO");
								fileCuenta = file.getFileName();
								fileName1 = file.getFileName();
								fileName2 = UUID.randomUUID() + "-" + file.getFileName();
								source = new File(pathFiles + "/" + fileName1);
								target = new File(pathFiles + "/" + fileName2);
								inputFile(fileName1, file.getInputstream());
								inputFile(fileName2, file.getInputstream());
								ConvertCharsetUtils.transform(source, charSetISO, target, charSetUFT);
								File newFile = new File(getPath("CARG_ARCHIVO") + File.separator + fileName2);

								size = siZeFile(newFile);
								if (this.size) {
//									if (this.file.getSize() <= 8000000l) {
									PolizaImportDTO importDTO = new PolizaImportDTO();
									importDTO.setiProcesar(iProcesar);
									importDTO.setiBoxes(iBoxes);
									importDTO.setiFechaPol(iFeccha);
									importDTO.setiFileName(fileCuenta.equals("cuenta.txt")
											? extraeArchivoCuentaService.generateNewFile(fileName2,
													this.getUserDetails().getUsername())
											: fileName2);
									importDTO.setiPathFile(getPath("CARG_ARCHIVO"));
									importDTO.setiMes(String.valueOf(iMes));
									importDTO.setiTipoL(iTipo);
									importDTO.setiNumpol(iNumero);
									importDTO.setRepetirPoliza(repetirPoliza ? 1 : 0);

									PolizaImportDTO impo = importarPolizaService.execuet(importDTO,
											this.getUserDetails().getIdSector(), this.getUserDetails().getUsername());
									setFileName(impo.getoFleName());
									if (impo.getoCodError() > 0) {
										desabilitar = Boolean.FALSE;

										generateNotificationFront(SEVERITY_INFO, "", impo.getoMsgError());
									} else {
										desabilitar = Boolean.TRUE;
										if (file.getFileName().equals("cuenta.txt")) {
											this.setFileName(impo.getoMsgError());
											desabilitar = Boolean.FALSE;
										}

										generateNotificationFront(SEVERITY_INFO, "Error", impo.getoMsgError());
									}
//									} else {
//										generateNotificationFront(SEVERITY_INFO, "Error",
//												"El tamaño de archivo revasa el permitido");
//									}
								} else {
									generateNotificationFront(SEVERITY_INFO, "Error",
											"  EL archivo no puede estar vacio");
								}
							} else {
								generateNotificationFront(SEVERITY_INFO, "Error",
										" EL archivo debe llamarse poliza.csv");
							}
						} else {
							generateNotificationFront(SEVERITY_INFO, "Error", " Seleccione un archivo.");
						}
					} catch (IOException e) {
						e.printStackTrace();
						generateNotificationFront(SEVERITY_INFO, "Error", " Seleccione un archivo.");
					}
				} else {
					generateNotificationFront(SEVERITY_INFO, "Error", " Seleccione un archivo.");
				}
			} else {
				generateNotificationFront(SEVERITY_INFO, "Error",
						" el año de la fecha es diferente al año de la poliza");
			}
		} else {
			generateNotificationFront(SEVERITY_INFO, "Error", " el mes de la fecha es diferente al mes de la poliza");
		}
		
	}

	/**
	 * Check clave.
	 *
	 * @return true, if successful
	 */
	public boolean checkClave() {
		if (getPath("PASS_IMPORTAR").equals(clave)) {
			objDisabled = Boolean.FALSE;
			return true;
		} else {
			objDisabled = Boolean.TRUE;
			generateNotificationFront(SEVERITY_INFO, "Error", " Contraseña incorrecta");
			return false;
		}

	}

	/**
	 * Gets the path.
	 *
	 * @param cvePath the cve path
	 * @return the path
	 */
	public String getPath(String cvePath) {
		return parametrosRepository.getValorByCv(cvePath);
	}

	/**
	 * Input file.
	 *
	 * @param filename the filename
	 * @param input    the input
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void inputFile(String filename, InputStream input) throws IOException {
		OutputStream output = new FileOutputStream(new File(getPath("CARG_ARCHIVO"), filename));

		try {
			IOUtils.copy(input, output);
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(output);
		}
	}

	/**
	 * Si ze file.
	 *
	 * @param file the file
	 * @return true, if successful
	 */
	public boolean siZeFile(File file) {
		if (file.length() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Down load.
	 */
	public void downLoad() {
		/*
		 * FileInputStream fileInputStream = null; OutputStream output = null; try {
		 * 
		 * File fileToSend = new File(); byte[] ingresoRespFile = new byte[(int)
		 * fileToSend.length()]; fileInputStream = new FileInputStream(fileToSend);
		 * fileInputStream.read(ingresoRespFile);
		 * 
		 * FacesContext facesContext = FacesContext.getCurrentInstance();
		 * ExternalContext externalContext = facesContext.getExternalContext();
		 * HttpServletResponse response = (HttpServletResponse)
		 * externalContext.getResponse();
		 * 
		 * String fileName = fileToSend.getName(); fileToSend.delete();
		 * response.reset(); response.setContentType("text/plain");
		 * response.setCharacterEncoding(ReportadorTXTServiceImpl.CHARSET);
		 * response.setHeader("Content-disposition", "attachment; filename=\"" +
		 * fileName + "\"");
		 * 
		 * output = response.getOutputStream(); output.write(ingresoRespFile);
		 * facesContext.responseComplete(); } catch (IOException e) {
		 * e.printStackTrace(); } finally { if (null != fileInputStream) { try {
		 * fileInputStream.close(); } catch (IOException e) {
		 * LOGGER.error(e.getMessage(), e); } }
		 * 
		 * if (null != output) { try { output.close(); } catch (IOException e) {
		 * LOGGER.error(e.getMessage(), e); } } }
		 */

		String dowloadPAth = fileCuenta.equals("cuenta.txt") ? getFileName()
				: getPath("TMP_POLIZAS") + File.separator + getFileName();
		dowloadFile(dowloadPAth);
	}

	/**
	 * Validate.
	 *
	 * @return true, if successful
	 */
	private boolean validate() {
		return Boolean.TRUE;
	}

	/**
	 * Button action.
	 *
	 * @param actionEvent the action event
	 */
	public void buttonAction(ActionEvent actionEvent) {

		if (validate()) {
			this.setRenderPdf(Boolean.TRUE);
			this.streamedContent = new DefaultStreamedContent(this.polizaService.getReportByTipopolMespolConpol(
					REPORT_PATH_JASPER_POLICY, REPORT_NAME, "escudo_ecatepec.png", iTipo, iMes, iNumero, iNumero,
					getUserDetails().getIdSector(), this.getUserDetails().getUsername()));
			try {
				this.filePdfPath = this.polizaService.savePDFFile(REPORT_PATH_JASPER_POLICY, REPORT_NAME,
						"escudo_ecatepec.png", iTipo, iMes, iNumero, iNumero, getUserDetails().getIdSector(),
						this.getUserDetails().getUsername());
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				generateNotificationFront(SEVERITY_ERROR, "ERROR", "Error al generar el archivo PDF.");
			}
		} else {
			generateNotificationFront(SEVERITY_ERROR, MESSAGE_ERROR, "Error al capturar los parametros.");
		}
	}

	/**
	 * Csv.
	 */
	public void csv() {
		String tipo = "csv";
		PolizaImportDTO importDTO = new PolizaImportDTO();
		importDTO.setHeader("");
		importDTO.setQuery("");
		importDTO.setiMes(String.valueOf(iMes));
		importDTO.setiTipoL(iTipo);
		importDTO.setiNumpol(iNumero);
		importDTO.setiIdSector(this.getUserDetails().getIdSector());
		PolizaImportDTO importar = importarPolizaService.exporReport(importDTO, tipo);
		// fileName = importar.getoFleName();
		dowloadFile(importar.getoFleName());
	}

	/**
	 * Txt.
	 */
	public void txt() {
		String tipo = "txt";
		PolizaImportDTO importDTO = new PolizaImportDTO();
		importDTO.setHeader("");
		importDTO.setQuery("");
		importDTO.setiMes(String.valueOf(iMes));
		importDTO.setiTipoL(iTipo);
		importDTO.setiNumpol(iNumero);
		importDTO.setiIdSector(this.getUserDetails().getIdSector());
		PolizaImportDTO importar = importarPolizaService.exporReport(importDTO, tipo);
		// fileName = importar.getoFleName();
		dowloadFile(importar.getoFleName());
	}

	/**
	 * Excel.
	 */
	public void excel() {

		PolizaImportDTO importDTO = new PolizaImportDTO();
		importDTO.setiMes(String.valueOf(iMes));
		importDTO.setiTipoL(iTipo);
		importDTO.setiNumpol(iNumero);
		importDTO.setiIdSector(this.getUserDetails().getIdSector());
		PolizaImportDTO importar = importarPolizaService.genarteExcel(importDTO);
		dowloadFile(importar.getoFleName());

	}

	/**
	 * Dowload file.
	 *
	 * @param fullNamePath the full name path
	 */
	public void dowloadFile(String fullNamePath) {
		FileInputStream fileInputStream = null;
		OutputStream output = null;
		try {

			File fileToSend = new File(fullNamePath);
			byte[] ingresoRespFile = new byte[(int) fileToSend.length()];
			fileInputStream = new FileInputStream(fileToSend);
			fileInputStream.read(ingresoRespFile);

			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

			fileName = "";
			fileName = fileToSend.getName();
			fileToSend.delete();
			response.reset();
			response.setContentType("text/plain");
			response.setCharacterEncoding(ReportadorTXTServiceImpl.CHARSET);
			response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");

			output = response.getOutputStream();
			output.write(ingresoRespFile);
			facesContext.responseComplete();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != fileInputStream) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}

			if (null != output) {
				try {
					output.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * Find conpol.
	 */
	public void findConpol() {
		iNumero = polienRepository.countPoliza(this.iMes, this.iTipo, this.getUserDetails().getIdSector());
		if (iNumero == null) {
			iNumero = 1;
		}
	}

	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}

	public ExtraeArchivoCuentaService getExtraeArchivoCuentaService() {
		return extraeArchivoCuentaService;
	}

	public void setExtraeArchivoCuentaService(ExtraeArchivoCuentaService extraeArchivoCuentaService) {
		this.extraeArchivoCuentaService = extraeArchivoCuentaService;
	}
	
	

}
